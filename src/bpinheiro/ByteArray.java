package bpinheiro;

public class ByteArray {

	/**
	 * Convert a array of bytes into integer variable  
	 * @param data - array with values
	 * @param offset - initial part of array
	 * @param size - conversion size
	 * @param littleEndian - conversion type
	 * @return
	 */
	public static int byteToInt(byte[] data, int offset, int size, boolean littleEndian) {
		int ret   = 0;
		int shift = 0;
		int max   = ((size-1)*8);
		for ( int i = 0; i < size; i++ ) {
			shift = (i*8);
			if (!littleEndian) { shift = max - shift; }
			ret |= ((data[offset+i] & 0xFF) << shift);
		}
		return ret;
	}
	
	/**
	 * Convert a array of bytes into a long variable  
	 * @param data - array with values
	 * @param offset - initial part of array
	 * @param size - conversion size
	 * @param littleEndian - conversion type
	 * @return
	 */
	public static long byteToLong(byte[] data, int offset, int size, boolean littleEndian) {
		long ret   = 0;
		long shift = 0;
		long max   = ((size-1)*8);
		for ( int i = 0; i < size; i++ ) {
			shift = (i*8);
			if (!littleEndian) { shift = max - shift; }
			ret |= ((data[offset+i] & 0xFFL) << shift);
		}
		return ret;
	}
	
	public static byte[] intToByte(int val, int size, boolean littleEndian) {
		byte[] ret = new byte[size];
		int shift = 0;
		int max   = ((size-1)*8);
		for ( int i = 0; i < size; i++ ) {
			shift = (i*8);
			if (!littleEndian) { shift = max - shift; }
			ret[i] = (byte) ( (val >> shift) & 0xFF ); 
		}
		return ret;
	}
	
	public static byte[] longToByte(long val, int size, boolean littleEndian) {
		byte[] ret = new byte[size];
		long shift = 0;
		long max   = ((size-1)*8);
		for ( int i = 0; i < size; i++ ) {
			shift = (i*8);
			if (!littleEndian) { shift = max - shift; }
			ret[i] = (byte) ( (val >> shift) & 0xFFL ); 
		}
		return ret;
	}
	
	
	public static String byteToHex(byte b) {
		byte bb[] = {b};
		return byteToHex(bb, 0, bb.length);
	}

	public static String byteToHex(byte[] b){
		return byteToHex(b, 0, b.length);
	}
	
	/**
	 * Retorna o array de bytes formatado em hexadecimal, separado
	 * por espa?o em branco, a partir da posi??o inicial solicitada.
	 * @param b Array de byte a ser formatado
	 * @param offset Posi??o inicial para iniciar a contar para o retorno.
	 * @return Retorna o array de bytes formatado em hexadecimal.
	 */
	public static String byteToHex(byte[] b, int offset){
		return byteToHex(b, offset, b.length - offset);
	}

	/**
	 * Retorna o array de bytes formatado em hexadecimal, separado
	 * por espa?o em branco, a partir da posi??o inicial solicitada.
	 * @param b Array de byte a ser formatado
	 * @param separador Separador para a formata??o do texto
	 * @return Retorna o array de bytes formatado em hexadecimal.
	 */
	public static String byteToHex(byte[] b,String separador){
		return byteToHex(b, 0, b.length, separador);
	}
	/**
	 * Retorna o array de bytes formatado em hexadecimal, separado
	 * por espa?o em branco, a partir da posi??o inicial solicitada,
	 * na quantidade informada.
	 * @param b Array de byte a ser formatado
	 * @param offset Posi??o inicial para iniciar a contar para o retorno.
	 * @return Retorna o array de bytes formatado em hexadecimal.
	 */
	public static String byteToHex(byte[] b, int offset, int size){
		return byteToHex(b, offset, size, " ");
	}
	
	/**
	 * Retorna o array de bytes formatado em hexadecimal, separado
	 * pelo valor escolhido, a partir da posi??o inicial solicitada,
	 * na quantidade informada.
	 * @param b Array de byte a ser formatado
	 * @param offset Posi??o inicial para iniciar a contar para o retorno.
	 * @param separador Separador para a formata??o do texto
	 * @return Retorna o array de bytes formatado em hexadecimal.
	 */
	public static String byteToHex(byte[] b, int offset, int size, String separador){
		StringBuilder ret = new StringBuilder();
		for (int i=offset; i<offset+size; i++){
			String aux = Integer.toHexString( b[i] & 0x0FF );
			if(aux.length() < 2) aux = "0" + aux;
			if(aux.length() < 2) aux = "0" + aux;
			if(i > offset) ret.append(separador);
			ret.append(aux.toUpperCase());
		}
		return ret.toString();
	}

	
	public static String byteToDec(byte[] b, int offset, int tam){
		return byteToDec(b, offset, tam, " ", 3);
	}
	
	public static String byteToDec(byte[] b, int offset, int tam, String separador, int printSize){
		boolean first = true;
		StringBuilder ret = new StringBuilder();
		for (int i=offset; i<offset+tam; i++){
			StringBuilder aux = new StringBuilder();
			aux.append("").append((b[i] & 0x0FF));
			if (aux.length() < printSize) aux.append("0").append(aux);
			if (aux.length() < printSize) aux.append("0").append(aux);
			if (aux.length() < printSize) aux.append("0").append(aux);
			if(first) first=false;
			else ret.append(separador);
			ret.append(aux);
		}
		return ret.toString();
	}
	
	public static String bytetToString(byte[] nameArray, int size) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < size; i++){
			if(nameArray[i] >= 0x20 && nameArray[i] <= 128){
				b.append((char)nameArray[i]);
			}
		}
		return b.toString();
	}
	
	public static byte[] union(byte[] arr1, byte[] arr2) {
		byte[] arr = new byte[ arr1.length + arr2.length ];
		System.arraycopy(arr1, 0, arr, 0		  , arr1.length);
		System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
		return arr;
	}
	
	public static boolean isArraysEquals(byte[] array1, byte[] array2) {
		if(array1.length != array2.length )return false;
		for (int i = 0; i < array2.length; i++)if(array2[i] != array1[i])return false;
		return true;
	}

	public static boolean isEmpty(byte[] data) {
		return isEmpty(data, (byte)0);
	}
	
	public static boolean isEmpty(byte[] data, byte especific) {
		if(data == null || data.length == 0)return true;
		for (int i = 0; i < data.length; i++) {
			if(data[i] != especific)return false;
		}
		return true;
	}
	
	public static byte[] stringToByte(String value, int maxSize) {
		byte[] result = new byte[maxSize];
		byte[] strData = value.getBytes();
		System.arraycopy(strData,0,result, 0,(strData.length > maxSize) ? maxSize : strData.length);
		return result;
	}

}	
