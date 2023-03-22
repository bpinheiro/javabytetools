package bpinheiro;

import java.util.Arrays;

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
	
	private static int signedIntConvert(int n, int size) {
		int bit = 0x80, mask = 0xFF;
		switch (size) {
			case 2: bit = 0x8000    ; mask = 0xFFFF;     break;
			case 3: bit = 0x800000  ; mask = 0xFFFFFF;   break;
			case 4: bit = 0x80000000; mask = 0xFFFFFFFF; break;
		}
		return ((n & bit) >  0 ) ? (n-(mask)-1) : n;
	}
	
	public static int byteToSignedInt(byte[] data, int offset, int size, boolean littleEndian) {
		int val = byteToInt(data, offset, size, littleEndian);
		return signedIntConvert(val, size);
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

	
	private static long signedLongConvert(long n, int size) {
		long bit = 0x80, mask = 0xFFL;
		switch (size) {
			case 2: bit = 0x8000L;		      mask = 0xFFFFL;             break;
			case 3: bit = 0x800000L;	   	  mask = 0xFFFFFFL;           break;
			case 4: bit = 0x80000000L; 		  mask = 0xFFFFFFFFL;         break;
			case 5: bit = 0x8000000000L; 	  mask = 0xFFFFFFFFFFL;       break;
			case 6: bit = 0x800000000000L; 	  mask = 0xFFFFFFFFFFFFL;     break;
			case 7: bit = 0x80000000000000L;  mask = 0xFFFFFFFFFFFFFFL;   break;
			case 8: bit = 0x8000000000000000L;mask = 0xFFFFFFFFFFFFFFFFL; break;
		}
		return ((n & bit) >  0 ) ? (n - mask -1) : n;
	}

	
	public static long byteToSignedLong(byte[] data, int offset, int size, boolean littleEndian) {
		long val = byteToInt(data, offset, size, littleEndian);
		return signedLongConvert(val, size);
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
	
	public static String byteToHex(byte[] b, int offset){
		return byteToHex(b, offset, b.length - offset);
	}

	public static String byteToHex(byte[] b,String separador){
		return byteToHex(b, 0, b.length, separador);
	}

	public static String byteToHex(byte[] b, int offset, int size){
		return byteToHex(b, offset, size, " ");
	}
	
	public static String byteToHex(byte[] b, int offset, int size, String separador){
		StringBuilder ret = new StringBuilder();
		for (int i=offset; i<offset+size; i++){
			if (i > offset) ret.append(separador);
			ret.append(String.format("%02X", b[i]));
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
		return Arrays.equals(array1, array2);
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
