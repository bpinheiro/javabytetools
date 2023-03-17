package bpinheiro;

public class ByteTools {

	private ByteEnum type;

	public ByteTools(ByteEnum type) {
		this.type = type;
	}

	/**
	 * Retorna o array de bytes formatado em decimal, separado
	 * por espa?o em branco, a partir da posi??o inicial solicitada,
	 * na quantidade informada.
	 * @param b Array de byte a ser formatado
	 * @param offset Posi??o inicial para iniciar a contar para o retorno.
	 * @param tam N?mero de bytes a ser formatado.
	 * @return Retorna o array de bytes formatado em decimal.
	 */
	public static String byteToDec(byte[] b, int offset, int tam){
		return byteToDec(b, offset, tam, " ", 3);
	}

	/**
	 * Retorna o array de bytes formatado em decimal, separado
	 * por espa?o em branco, a partir da posi??o inicial solicitada,
	 * na quantidade informada. Offset = 0
	 * @param b Array de byte a ser formatado
	 * @param tam N?mero de bytes a ser formatado.
	 * @return Retorna o array de bytes formatado em decimal.
	 */
	public static String byteToDec(byte[] b, int tam){
		return byteToDec(b, 0, tam, " ", 3);
	}

	/**
	 * Retorna o array de bytes formatado em decimal, separado
	 * pelo valor escolhido, a partir da posi??o inicial solicitada,
	 * na quantidade informada.
	 * @param b Array de byte a ser formatado
	 * @param offset Posi??o inicial para iniciar a contar para o retorno.
	 * @param tam N?mero de bytes a ser formatado.
	 * @param separador Separador para a formata??o do texto
	 * @param printSize Quantidade de caracteres que um byte deve possuir
	 * @return Retorna o array de bytes formatado em decimal.
	 */
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

	/**
	 * Retorna um sub-array do array informado, iniciando na posi??o
	 * inicial solicitada, indo at? o final do mesmo.
	 * @param arr Array para adquirir o sub-array
	 * @param offset Posi??o inicial para iniciar o novo array.
	 * @return Retorna um sub-array do array informado.
	 */
	public static byte[] subarray(byte[] arr, int offset) {
		return subarray(arr, offset, arr.length - offset);
	}

	/**
	 * Retorna um sub-array do array informado, iniciando na posi??o
	 * inicial solicitada, contando a quantidade de bytes at? o tamanho
	 * especificado.
	 * @param arr Array para adquirir o sub-array
	 * @param offset Posi??o inicial para iniciar o novo array.
	 * @param tam Tamanho para o novo array a ser formado.
	 * @return Retorna um sub-array do array informado.
	 */
	public static byte[] subarray(byte[] arr, int offset, int tam) {
		byte[] nova = new byte[tam];
		System.arraycopy(arr, offset, nova, 0, tam);
		return nova;
	}

	/**
	 * Uni dois arrays, formando um com os dados dos outros dois.
	 * @param arr1 Primeiro array para ser unido.
	 * @param arr2 Segundo array para ser unido
	 * @return Um array, com o primeiro seguido do segundo.
	 */
	public static byte[] union(byte[] arr1, byte[] arr2) {
		byte[] arr = new byte[ arr1.length + arr2.length ];
		System.arraycopy(arr1, 0, arr, 0		  , arr1.length);
		System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
		return arr;
	}

	/**
	 * Uni dois arrays, formando um com os dados dos outros dois, considerando
	 * as posi??es inicias de ambos, e a quantidade que ser? utilizada de cada
	 * qual.
	 * @param arr1 Primeiro array para ser unido.
	 * @param posIni1 Posi??o incial do primeiro array.
	 * @param tam1 Quantidade de bytes do primeiro array que deve ser utilizada.
	 * @param arr2 Segundo array para ser unido
	 * @param posIni1 Posi??o incial do segundo array.
	 * @param tam1 Quantidade de bytes do segundo array que deve ser utilizada.
	 * @return Um array, com os dados do primeiro seguido dos dados do segundo.
	 */
	public static byte[] union(byte[] arr1, int posIni1, int tam1, byte[] arr2, int posIni2, int tam2) {
		byte[] arr = new byte[ tam1 + tam2 ];
		System.arraycopy(arr1, posIni1, arr, 0, tam1);
		System.arraycopy(arr2, posIni2, arr, tam1, tam2);
		return arr;
	}

	
	/**
	 * Transforma uma string em um vetor de bytes.
	 * @param buffer Buffer que ir? receber os bytes formatados
	 * @param posIniBuffer Posi??o inicial do buffer a ser preenchido
	 * @param s String a ser transformada
	 * @param offset Posi??o inicial da string a ser considerada
	 * @param tam Quantidades de bytes a ser transformada
	 */
	public static void parseString(byte[] buffer, int posIniBuffer, String s, int offset, int tam) {
		for(int i=offset; i<offset+tam; i++)
			buffer[posIniBuffer+i-offset] = (byte)s.charAt(i);
	}

	/**
	 * Transforma um array de strings com valores hexadecimais em um array de bytes
	 * @param base Base para transforma??o do n?mero
	 * @param buffer Buffer que ir? receber os bytes formatados
	 * @param posIniBuffer Posi??o inicial do buffer a ser preenchido
	 * @param offset Primeira posi??o para iniciar a transforma??o
	 * @param tam Quantidade de posi??es para serem transformadas
	 */
	public static void parseNumericArray(int base, byte[] buffer, int posIniBuffer, String[] pureBytes, int offset, int tam) {
		for(int i=offset; i<offset+tam; i++)
			buffer[posIniBuffer+i-offset] = (byte)Integer.parseInt( (pureBytes[i]), base );
	}

	/**
	 * Transforma uma string com dados num?ricos em um array de byte,
	 * considerando que cada byte possui um n?mero definido de caracteres.
	 * @param base Base para transforma??o do n?mero
	 * @param buffer Buffer que ir? receber os bytes formatados
	 * @param posIniBuffer Posi??o inicial do buffer a ser preenchido
	 * @param data String com os valores a serem transformados
	 * @param posSize Tamanho que cada posi??o possuir
	 * @param offset Primeira posi??o para iniciar a transforma??o
	 * @param tam Quantidade de posi??es para serem transformadas
	 */
	public static void parseNumericPosicional(int base, byte[] buffer, int posIniBuffer, String data, int posSize, int offset, int tam) {
		for(int i=0; i<tam; i++)
			buffer[posIniBuffer+i] = (byte)Integer.parseInt( data.substring( offset + (i*posSize), offset + ((i+1)*posSize) ).trim(), base );
	}

	/**
	 * Transforma um inteiro em um vetor de dois bytes, seguindo da primeira
	 * ? ?ltima posi??o do array com os bytes menos significativos para
	 * o mais significativo.
	 * @param val Inteiro a ser transformado.
	 * @return Array de byte com o valor inteiro.
	 */
	public static byte[] int2ToByte(int val) {
		byte[] ret = new byte[2];
		ret[0] = (byte) ( (val) & 255 );
		ret[1] = (byte) ( (val >>> 8) & 255 );
		return ret;
	}

	public static void int2ToByte(int val, byte[] ret, int offset) {
		ret[offset]   = (byte) ( (val) & 255 );
		ret[offset+1] = (byte) ( (val >>> 8) & 255 );
	}

	public static byte[] int2ToByteInv(int val){
		byte[] ret = new byte[2];
		ret[1] = (byte) ( (val) & 255 );
		ret[0] = (byte) ( (val >>> 8) & 255 );
		return ret;
	}

	public static int byteToInt3(byte[] bts, int offset) {
		int ret;
		ret =  ((bts[offset+2] & 0xFF) << 16);
		ret |= ((bts[offset+1] & 0xFF) << 8);
		ret |= (bts[offset]    & 0xFF);
		return ret;
		
	}

	public static int byteToInt3Signed (byte[] bts, int offset) {
		int number = byteToInt3(bts, offset);
		if((number & (1 << 23)) > 0 ){ number |= 0xff000000; }
		return number;
	}

	/**
	 * Cria um inteiro a partir de dois bytes de um vetor de bytes,
	 * iniciando a contagem a partir da posi??o inicial requisitada,
	 * tendo o primeiro byte como menos significativo, at? o ?ltimo
	 * byte, que deve ser o mais significativo.
	 * @param bts Vetor de bytes com o n?mero a ser transformado.
	 * @param offset Posi??o inicial para pegar o n?mero.
	 * @return Inteiro de dois bytes.
	 */
	public static int byteToInt2(byte[] bts, int offset) {
		return byteToInt2(bts, offset, false);
	}

	public static int byteToInt2(byte[] bts, int offset, boolean hilo) {
		int ret = 0;
		if (hilo) {
			ret = ((bts[offset++] & 0xFF) << 8);
			ret |= (bts[offset] & 0xFF);
		} else {
			ret = (bts[offset++] & 0xFF);
			ret |=((bts[offset] & 0xFF) << 8);
		}
		return ret;
	}


	public static byte[] int3ToByte(int val) {
		byte[] ret = new byte[3];
		ret[0] = (byte) ( (val) & 255 );
		ret[1] = (byte) ( (val >>> 8) & 255 );
		ret[2] = (byte) ( (val >>> 16) & 255 );
		return ret;
	}


	public static byte[] int3ToByteInv(int val) {
		byte[] ret = new byte[3];
		ret[2] = (byte) ( (val) & 255 );
		ret[1] = (byte) ( (val >>> 8) & 255 );
		ret[0] = (byte) ( (val >>> 16) & 255 );
		return ret;
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
