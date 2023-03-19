package bpinheiro;

import java.util.Arrays;

public class Examples {

	public static void main(String[] args) {
		
		byte[] data = {0x12, 0x34};
		System.out.println("Data {0x00, 0x01} is: " + ByteTools.byteToInt(data, 0, 2, true)  + " is 256 in little endian");
		System.out.println("Data {0x00, 0x01} is: " + ByteTools.byteToInt(data, 0, 2, false) + " is 1 in big endian");

		byte[] result = new byte[2];
		int value = 0x1234;
		ByteTools.intToByte(value, result, 0, 2, true);
		System.out.println("Value: " + value + " is: " + Arrays.toString(result) + " in little endian");
	}
}
