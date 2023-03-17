package bpinheiro;

public class ByteBuffer {

	private int currentPos;
	private byte [] byteArray;

	public ByteBuffer(int initialSize) {
		byteArray = new byte[initialSize];
		reset();
	}

	public ByteBuffer() {
		this(100);
	}

	public void addStringZ(String string) {
		addString(string);
		addInt1(0);
	}

	public void addString(String string) {
		add(string.getBytes());
	}

	public void addString(String string, int size) {
		add(ByteTools.stringToByte(string, size));
	}

	public void addInt3(int value){
		add(ByteTools.int3ToByte(value));
	}


	public void addInt4(int... value){
		for (int i : value)addInt4(i);
	}

	public void addInt4(int value){
		add(ByteTools.int4ToByte(value));
	}
	
	public void addInt4Inv(int value) {
		add(ByteTools.int4ToByteInv(value));
	}

	public void addInt1(int value){
		add((byte)value);
	}

	public void addInt2(int value){
		add(ByteTools.int2ToByte(value));
	}
	
	public void addInt2Inv(int value){
		add(ByteTools.int2ToByteInv(value));
	}

	public void addLong4(long nh) {
		byte[] d = new byte[4];
		ByteTools.long4ToByte(nh, d, 0);
		add(d);
	}
	
	public void addLong4Inv(long nh) {
		byte[] d = new byte[4];
		ByteTools.long4ToByteInv(nh, d, 0);
		add(d);
	}

	public void reset() {
		currentPos = 0;
	}

	public void add(byte... b){
		for(byte c : b){
			add(c);
		}
	}

	public void addEmpty (int size) {
		if (size > 0) {
			add(new byte[size]);
		}
	}

	public void add( int offset, int length, byte... b) {
		for (int i = offset ; i < length && i < b.length ; i++ ) {
			add(b[i]);
		}
	}

	public void add (byte b) {
		verify();
		byteArray[currentPos++] = b;
	}

	public byte [] getBytes() {
		byte [] result = new byte[currentPos];
		System.arraycopy(byteArray, 0, result, 0, currentPos);
		return  result;
	}
	
	
	public int getCurrentPos() {
		return currentPos;
	}

	private void verify(){
		if (currentPos >= byteArray.length) {
			byte [] newByteArray = new byte[ (byteArray.length + 1) * 2];
			System.arraycopy(byteArray, 0, newByteArray, 0, byteArray.length);
			byteArray = newByteArray;
		}
	}
}
