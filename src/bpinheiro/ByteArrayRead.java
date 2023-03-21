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
	
	
	/**
	 * Leitura de um arquivo binário
	 * @param file
	 * @throws IOException
	 */
	public ByteArrayRead(File file) throws IOException{
		this.data = readFile(file);
		this.p=0;
	}
	
	public ByteArrayRead(DataInputStream inputStream) throws IOException{
		this.data = readInputStream(inputStream);
		this.p=0;
	}

	
	public ByteArrayRead(byte[] data) {
		this.data = data;
		this.p=0;
	}

	public ByteArrayRead(byte[] data, int offset) {
		this.data = data;
		this.p= offset;
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

	/*
	public int readInt2Signed(){
		int d =  ByteTools.byteToInt2(data,p, false);
		p+=2;
		return ( ( d & 0x8000) > 0 ) ?  ( d - 0xFFFF - 1 ) : d;
	}
	
	public int readInt4Signed(){
		int d =  ByteTools.byteToInt4(data,p, false);
		p+=4;
		return ( ( d & 0x80000000) > 0 ) ?  ( d - 0xFFFFFFFF - 1 ) : d;
	}
	*/
	
	/**
	 * Check if has data in buffer
	 * @return
	 */
	public boolean hasData() {
		return p < data.length;
	}

	/**
	 * Calcula quanto falta para acabar
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
		return (data[p++] == 1 );
	}
	
	/**
	 * Jump n bytrss 
	 * @param qtd  
	 */
	public void readEmpty(int qtd){
		p+=qtd;
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
		System.arraycopy(data,p,array,0,qtd);
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
		int qtd = data.length - p;
		return readArray(qtd);
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
	    	byte c = data[p++];
	    	if(c !=0x00)str.append((char)c);
	    }
	    return str.toString();
	}

	public String readString() {
		StringBuffer str = new StringBuffer();
		byte c;
		while( (c = data[p++]) != 0) {
			str.append((char)c);
		}
		return str.toString();
	}

	/**
	 * Le carectees ascii especiais com acento ou nao
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
	        if(av > 0 ){
	        	count = inputStream.read(data);
	        } else {
	        	count = -1;
	        }
	    }
	    return baos.toByteArray();
	}

	public int getOffset() {
		return p;
	}

	public void setOffset(int ptr) {
		p = ptr;
	}
}
