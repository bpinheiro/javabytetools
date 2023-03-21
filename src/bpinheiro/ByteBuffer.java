package bpinheiro;

public class ByteBuffer {

	private int currentPos;
	private byte [] array;
	private boolean littleEndian;

	public ByteBuffer(int initialSize, boolean littleEndian) {
		this.array 		  = new byte[initialSize];
		this.littleEndian = littleEndian;
		reset();
	}

	public ByteBuffer() {
		this(100, true);
	}

	public void addStringZ(String string) {
		addString(string);
		add((byte)0);
	}

	public void addString(String string) {
		add(string.getBytes());
	}

	public void addString(String string, int size) {
		add(ByteArray.stringToByte(string, size));
	}

	public void addInt(int value, int typeSize){
		add(ByteArray.intToByte(value, typeSize, this.littleEndian));
	}

	public void addLong(long value, int typeSize) {
		add(ByteArray.longToByte(value, typeSize, this.littleEndian));
	}

	public void addEmpty(int size) {
		if (size > 0) {
			add(new byte[size]);
		}
	}

	public void reset() {
		this.currentPos = 0;
	}

	public void add(byte b) {
		verify();
		this.array[this.currentPos++] = b;
	}

	public void add(byte... b) {
		for (byte c : b) { add(c); }
	}

	public void add( int offset, int length, byte... b) {
		for (int i = offset ; i < length && i < b.length ; i++ ) {
			add(b[i]);
		}
	}

	public byte [] getBytes() {
		byte [] result = new byte[currentPos];
		System.arraycopy(array, 0, result, 0, currentPos);
		return  result;
	}
	
	public int getCurrentPos() {
		return currentPos;
	}

	private void verify(){
		if (currentPos >= array.length) {
			byte [] newByteArray = new byte[ (array.length + 1) * 2];
			System.arraycopy(array, 0, newByteArray, 0, array.length);
			array = newByteArray;
		}
	}
}