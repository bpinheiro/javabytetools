package bpinheiro;

public class ByteArray {

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
	
	public static byte[] union(byte[] arr1, byte[] arr2) {
		byte[] arr = new byte[ arr1.length + arr2.length ];
		System.arraycopy(arr1, 0, arr, 0		  , arr1.length);
		System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
		return arr;
	}
}	
