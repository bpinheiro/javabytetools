package bpinheiro;

public class ByteTools {

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

	
	public static void intToByte(int val, byte[] ret, int offset, int size, boolean littleEndian) {
		int shift = 0;
		int max   = ((size-1)*8);
		for ( int i = 0; i < size; i++ ) {
			shift = (i*8);
			if (!littleEndian) { shift = max - shift; }
			ret[offset+i] = (byte) ( (val >> shift) & 0xFF ); 
		}
	}
	
	
	/**
	 * Transforma um inteiro em um vetor de quatro bytes, seguindo da primeira
	 * ? ?ltima posi??o do array com os bytes menos significativos para
	 * o mais significativo.
	 * @param val Inteiro a ser transformado.
	 * @return Array de byte com o valor inteiro.
	 */
	public static byte[] int4ToByte(int val) {
		byte[] ret = new byte[4];
		ret[0] = (byte) ( (val) & 255 );
		ret[1] = (byte) ( (val >>> 8) & 255 );
		ret[2] = (byte) ( (val >>> 16) & 255 );
		ret[3] = (byte) ( (val >>> 24) & 255 );
		return ret;
	}

	public static byte[] int4ToByteInv(int val) {
		byte[] ret = new byte[4];
		ret[3] = (byte) ( (val) & 255 );
		ret[2] = (byte) ( (val >>> 8) & 255 );
		ret[1] = (byte) ( (val >>> 16) & 255 );
		ret[0] = (byte) ( (val >>> 24) & 255 );
		return ret;
	}

	
	
	
	public static void int4ToByte(int val, byte[] ret, int offset) {
		ret[offset] = (byte) ( (val) & 255 );
		ret[offset+1] = (byte) ( (val >>> 8) & 255 );
		ret[offset+2] = (byte) ( (val >>> 16) & 255 );
		ret[offset+3] = (byte) ( (val >>> 24) & 255 );
	}


	public static byte[] intToByte (int val) {
		byte[] bytes = new byte[4];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)(val & 255);
			val >>>= 8;
		}
		return bytes;
	}
	/**
	 * Cria um inteiro a partir de quatro bytes de um vetor de bytes,
	 * iniciando a contagem a partir da posi??o inicial requisitada,
	 * tendo o primeiro byte como menos significativo, at? o ?ltimo
	 * byte, que deve ser o mais significativo.
	 * @param bts Vetor de bytes com o n?mero a ser transformado.
	 * @param offset Posi??o inicial para pegar o n?mero.
	 * @return Inteiro de quatro bytes.
	 */
	public static int byteToInt4(byte[] bts, int offset) {
		return byteToInt4(bts, offset, false);
	}


	public static int byteToInt4(byte[] bts, int offset, boolean hilo) {
		int ret = 0;
		if (hilo){
			ret = (bts[offset++]   & 0xFF) << 24;
			ret |= ((bts[offset++] & 0xFF) << 16);
			ret |= ((bts[offset++] & 0xFF) << 8);
			ret |= (bts[offset]    & 0xFF);
		} else {
			ret = (bts[offset++]   & 0xFF);
			ret |= ((bts[offset++] & 0xFF) << 8);
			ret |= ((bts[offset++] & 0xFF) << 16);
			ret |= ((bts[offset]   & 0xFF) << 24);
		}
		return ret;
	}


	/**
	 * Transforma um inteiro em um vetor de oito bytes, seguindo da primeira
	 * ? ?ltima posi??o do array com os bytes menos significativos para
	 * o mais significativo..
	 * @param val Inteiro a ser transformado.
	 * @return Array de byte com o valor inteiro.
	 */
	public static byte[] longToByte(long val) {
		byte[] ret = new byte[8];
		ret[0] = (byte) ( (val) & 255 );
		ret[1] = (byte) ( (val >>> 8) & 255 );
		ret[2] = (byte) ( (val >>> 16) & 255 );
		ret[3] = (byte) ( (val >>> 24) & 255 );
		ret[4] = (byte) ( (val >>> 32) & 255 );
		ret[5] = (byte) ( (val >>> 40) & 255 );
		ret[6] = (byte) ( (val >>> 48) & 255 );
		ret[7] = (byte) ( (val >>> 56) & 255 );
		return ret;
	}

	public static byte[] long4ToByte(long val, byte[] ret , int offset) {
		ret[offset+0] = (byte) ( (val) & 255 );
		ret[offset+1] = (byte) ( (val >>> 8) & 255 );
		ret[offset+2] = (byte) ( (val >>> 16) & 255 );
		ret[offset+3] = (byte) ( (val >>> 24) & 255 );
		return ret;
	}
	
	public static byte[] long4ToByteInv(long val, byte[] ret , int offset) {
		ret[offset+3] = (byte) ( (val) & 255 );
		ret[offset+2] = (byte) ( (val >>> 8) & 255 );
		ret[offset+1] = (byte) ( (val >>> 16) & 255 );
		ret[offset+0] = (byte) ( (val >>> 24) & 255 );
		return ret;
	}
	

	public static byte[] longToByteInv(long val) {
		byte[] ret = new byte[8];
		ret[7] = (byte) ( (val) & 255 );
		ret[6] = (byte) ( (val >>> 8) & 255 );
		ret[5] = (byte) ( (val >>> 16) & 255 );
		ret[4] = (byte) ( (val >>> 24) & 255 );
		ret[3] = (byte) ( (val >>> 32) & 255 );
		ret[2] = (byte) ( (val >>> 40) & 255 );
		ret[1] = (byte) ( (val >>> 48) & 255 );
		ret[0] = (byte) ( (val >>> 56) & 255 );
		return ret;
	}

	/**
	 * Cria um inteiro a partir de oito bytes de um vetor de bytes,
	 * iniciando a contagem a partir da posi??o inicial requisitada,
	 * tendo o primeiro byte como menos significativo, at? o ?ltimo
	 * byte, que deve ser o mais significativo.
	 * @param bts Vetor de bytes com o n?mero a ser transformado.
	 * @param offset Posi??o inicial para pegar o n?mero.
	 * @return Inteiro de oito bytes.
	 */
	public static long byteToLong(byte[] bts, int offset) {
		long ret =  (bts[offset++] & 0xFFL);
		ret |= ((bts[offset++] & 0xFFL) << 8 );
		ret |= ((bts[offset++] & 0xFFL) << 16);
		ret |= ((bts[offset++] & 0xFFL) << 24);

		long aux = (bts[offset++] & 0xFFL); aux <<= 32; ret |= aux;
		aux = (bts[offset++] & 0xFFL); aux <<= 40; ret |= aux;
		aux = (bts[offset++] & 0xFFL); aux <<= 48; ret |= aux;
		aux = (bts[offset] & 0xFFL); aux <<= 56; ret |= aux;
		return ret;
	}

	public static long byteToLong4(byte[] bts, int offset) {
		long ret = 0;
		ret =  ((bts[offset++] & 0xFFL) << 0 );
		ret |= ((bts[offset++] & 0xFFL) << 8 );
		ret |= ((bts[offset++] & 0xFFL) << 16);
		ret |= ((bts[offset] & 0xFFL) << 24);
		return ret;
	}

	public static long byteToLongN(byte[] bts, int offset, int n) {
		long ret = 0;
		for ( int i = offset; i < offset+n; i++ ) {
			ret |= ((bts[i] & 0xFFL) << i*8);
		}
		return ret;
	}

	public static byte[] stringToByte(String value, int maxSize) {
		byte[] result = new byte[maxSize];
		byte[] strData = value.getBytes();
		System.arraycopy(strData,0,result, 0,(strData.length > maxSize) ? maxSize : strData.length);
		return result;
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
	
	public static String bytetToString(byte[] nameArray) {
		return bytetToString(nameArray, nameArray.length);
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
}
