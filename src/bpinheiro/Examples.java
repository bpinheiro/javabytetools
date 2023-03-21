package bpinheiro;

public class Examples {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int number;
		int check;
		byte[] data;
		byte[] res;
		
		long numberL;
		long checkL;

		//--------------------------------------------------------
		// From array returns a integer value
		//--------------------------------------------------------
		
		//Test with 1 byte, it makes no difference little endian or big endian 
		data   = new byte[]{(byte)0xF0};
		check  = 240;
		number = ByteArray.byteToInt(data, 0, 1, true);
		System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in little endian is: " + number + " valid: " + (number == check));
		
		//Test with 2 byte in little endian format 
		data = new byte[]{0x12, 0x34};
		check = 13330;
		number  = ByteArray.byteToInt(data, 0, 2, true);
		System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in little endian is: " + number + " valid: " + (number == check));

		//Test with 2 byte in big endian format 
		check   = 4660;
		number  = ByteArray.byteToInt(data, 0, 2, false);
		System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in big endian is: " + number + " valid: " + (number == check));

		
		//Test with 4 byte in little endian format 
		data = new byte[]{0x12, 0x34, 0x56, 0x78};
		check = 2018915346;
		number  = ByteArray.byteToInt(data, 0, 4, true);
		System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in little endian is: " + number + " valid: " + (number == check));
		
		//Test with 4 byte in big endian format 
		check   = 305419896;
		number  = ByteArray.byteToInt(data, 0, 4, false);
		System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in big endian is: " + number + " valid: " + (number == check));

		//--------------------------------------------------------
		// From array returns a long value
		//--------------------------------------------------------
		data   = new byte[]{0x12, 0x34, 0x56, 0x78, (byte)0x9A, (byte)0xBC, (byte)0xCD, (byte)0x01};
		checkL  = 129967335893513234L;
		numberL = ByteArray.byteToLong(data, 0, 8, true);
		System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in little endian is: " + numberL + " valid: " + (numberL == checkL));
		
		checkL  = 1311768467463785729L;
		numberL = ByteArray.byteToLong(data, 0, 8, false);
		System.out.println("Array [" + ByteArray.byteToHex(data, ",") + "] in big endian is: " + numberL + " valid: " + (numberL == checkL));
		
	
		//--------------------------------------------------------
		// From integer value return a array
		//--------------------------------------------------------

		//Test with 1 byte, it makes no difference little endian or big endian 
		number = 240;
		data   = new byte[]{(byte)0xF0};
		res    = ByteArray.intToByte(number, 1, true);
		System.out.println("Number: " + number + " is array [" + ByteArray.byteToHex(res, ",") +  "] valid: " + ByteArray.isArraysEquals(data, res));

		//Test with 2 byte in little endian format   
		number = 3456;
		data   = new byte[]{(byte)0x80, 0x0D};
		res    = ByteArray.intToByte(number, 2, true);
		System.out.println("Number: " + number + " is array [" + ByteArray.byteToHex(res, ",") +  "] in little endian valid: " + ByteArray.isArraysEquals(data, res));

		//Test with 2 byte in little endian format   
		data   = new byte[]{0x0D, (byte)0x80};
		res    = ByteArray.intToByte(number, 2, false);
		System.out.println("Number: " + number + " is array [" + ByteArray.byteToHex(res, ",") +  "] in big endian valid: " + ByteArray.isArraysEquals(data, res));

		//Test with 4 byte in little endian format   
		number = 0x125764AC;
		data   = new byte[]{(byte)0xAC, 0x64, 0x57, 0x12};
		res    = ByteArray.intToByte(number, 4, true);
		System.out.println("Number: " + number + " is array [" + ByteArray.byteToHex(res, ",") +  "] in little endian valid: " + ByteArray.isArraysEquals(data, res));

		//Test with 4 byte in little endian format   
		data   = new byte[]{0x12, 0x57, 0x64, (byte)0xAC};
		res    = ByteArray.intToByte(number, 4, false);
		System.out.println("Number: " + number + " is array [" + ByteArray.byteToHex(res, ",") +  "] in big endian valid: " + ByteArray.isArraysEquals(data, res));

		
		//--------------------------------------------------------
		// Appends types into array
		//--------------------------------------------------------
		//by default configured for litle endian
		ByteBuffer buffer = new ByteBuffer(100, true);
		buffer.addInt(0x1234, 2);
		buffer.addLong(0x567890, 4);
		buffer.addStringZ("Test string");
		byte[] result = buffer.getBytes();
		res           = new byte[] {0x34,0x12,(byte)0x90,0x78,0x56,0x00,0x54,0x65,0x73,0x74,0x20,0x73,0x74,0x72,0x69,0x6E,0x67,0x00};
		System.out.println("Values: [" + ByteArray.byteToHex(result, ",") + "] check: " + ByteArray.isArraysEquals(res, result));

	}
}
