package bpinheiro;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ByteArrayRead {
	
	private byte data[];
	private int p;
	private int backupP;
	private boolean littleEndian;
	
	
	/**
	 * Read a binary file
	 * @param file
	 * @throws IOException
	 */
	public ByteArrayRead(File file, boolean littleEndian) throws IOException{
		this.data = readFile(file);
		this.littleEndian = littleEndian;
		this.p=0;
	}
	
	public ByteArrayRead(DataInputStream inputStream, boolean littleEndian) throws IOException{
		this.data = readInputStream(inputStream);
		this.littleEndian = littleEndian;
		this.p=0;
	}

	
	public ByteArrayRead(byte[] data, int offset, boolean littleEndian) {
		this.data = data;
		this.littleEndian = littleEndian;
		this.p= offset;
	}

	public ByteArrayRead(byte[] data, boolean littleEndian) {
		this(data, 0, littleEndian);
	}

	public ByteArrayRead(byte[] data) {
		this(data, 0, true);
	}
	
	/**
	 * Read 1 char byte size
	 * @return car
	 */
	public char readChar(){
		return (char)data[p++];
	}

	
	/**
	 * Goto a new pointer, save current pointer before
	 * @param newP
	 */
	public void goTo(int newP) {
		backupP = p;
		p = newP;
	}
	
	/**
	 * Back to original pointer position
	 * ao zero
	 */
	public void back(){
		p = backupP;
	}

	public byte readByte(){
		return data[p++];
	}
	
	
	public int readInt(int size) {
		int val = ByteArray.byteToInt(this.data, this.p, size, this.littleEndian);
		this.p+=size;
		return val;
	}

	public int readSignedInt(int size) {
		int val = ByteArray.byteToSignedInt(this.data, this.p, size, this.littleEndian);
		this.p+=size;
		return val;
	}

	public long readLong(int size) {
		long val = ByteArray.byteToInt(this.data, this.p, size, this.littleEndian);
		this.p+=size;
		return val;
	}

	public long readSignedLong(int size) {
		long val = ByteArray.byteToSignedInt(this.data, this.p, size, this.littleEndian);
		this.p+=size;
		return val;
	}
	
	
	/**
	 * Check if has data in buffer
	 * @return
	 */
	public boolean hasData() {
		return p < data.length;
	}

	/**
	 * Calculate how much to finish
	 * @return
	 */
	public int calcData() {
		return (data.length - p);
	}


	/**
	 * check if has size of data that i needed
	 */
	public boolean hasData(int size) {
		return (p+size) < data.length;
	}
	
	/**
	 * Read 1 byte boolean value ( 1: true, 0: false ) 
	 * @return
	 */
	public boolean readBoolean() {
		return (this.data[this.p++] == 1 );
	}
	
	/**
	 * Jump n bytrss 
	 * @param qtd  
	 */
	public void readEmpty(int qtd){
		this.p+=qtd;
	}
	
	/**
	 * Read 16 bytes for md5 check
	 * @return
	 */
	public byte[] readMd5(){
		return readArray(16);
	}
	
	public byte[] readArray(int qtd){
		byte array[] = new byte[qtd];
		System.arraycopy(this.data,this.p, array, 0, qtd);
		p+=qtd;
		return array;
	}
	
	public byte[] readArrayInv(int qtd) {
		byte array[] = readArray(qtd);
		byte rev[] = new byte[array.length]; 
		int j = 0;
		for (int i = (array.length-1) ; i >= 0 ; i--)rev[j++] = array[i];
		return rev;
	}

	
	public byte[] readAll() {
		int qtd = this.data.length - this.p;
		return readArray(qtd);
	}
	
	public int getOffset() {
		return p;
	}

	public void setOffset(int ptr) {
		this.p = ptr;
	}
	
	public String readLine() {
		byte CR = 0x0D;
		byte LF = 0x0A;
		byte d;
		StringBuffer buffer = new StringBuffer();
		while( (d = readByte()) != LF) {
			if(d != CR)buffer.append((char)d);
		}
		return buffer.toString();
	}

	public String readString(int qtd) {
	    StringBuffer str = new StringBuffer();
	    for (int i = 0; i < qtd; i++){
	    	byte c = this.data[this.p++];
	    	if(c !=0x00)str.append((char)c);
	    }
	    return str.toString();
	}

	public String readString() {
		StringBuffer str = new StringBuffer();
		byte c;
		while( (c = data[p++]) != 0) { str.append((char)c); }
		return str.toString();
	}

	/**
	 * Read special ascii chars like accents for example
	 * @param maxSize - desejavel o tamanho aproximado da string
	 * @return
	 */
	public String readStringUtf8(int maxSize) {
		byte temp[] = new byte[maxSize];
		int ptr=0;
		byte c;
		while( ((c = data[p++]) != 0) && (ptr < maxSize)) { temp[ptr++] = c; }
		byte finalData[] = new byte[ptr];
		System.arraycopy(temp, 0, finalData, 0, finalData.length);
		return new String(finalData, Charset.forName("UTF-8"));
	}

	public String readStringInv(int qtd){
	    StringBuffer str = new StringBuffer();
	    byte data[] = readArray(qtd);	    
	    for (int i = data.length-1 ; i >= 0 ; i--){
	    	char c = (char) data[i];
	    	if(c == 0x20)c = '_';
	    	str.append(c);
	    }
	    return str.toString();
	}

	public byte[] readFile(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    DataOutputStream dos = new DataOutputStream(baos);
	    byte[] data = new byte[4096];
	    int count = inputStream.read(data);
	    while(count != -1){
	        dos.write(data, 0, count);
	        count = inputStream.read(data);
	    }
	    inputStream.close();
	    return baos.toByteArray();
	}


	public byte[] readInputStream(DataInputStream inputStream) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    DataOutputStream dos = new DataOutputStream(baos);
	    byte[] data = new byte[128];
	    int count = inputStream.read(data);
	    while(count != -1){
	        dos.write(data, 0, count);
	        int av = inputStream.available();
	        if (av > 0 ) {
	        	count = inputStream.read(data);
	        } else {
	        	count = -1;
	        }
	    }
	    return baos.toByteArray();
	}
}
