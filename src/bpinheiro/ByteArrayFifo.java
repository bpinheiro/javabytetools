package bpinheiro;

public class ByteArrayFifo {

	private int pRead;
	private int pWrite;
	private int maxSize;
	private byte[][] array;
	private final Object object = new Object();
	
	public ByteArrayFifo(int maxSize) {
		
		this.array = new byte[maxSize][];
		this.pRead=0;
		this.pWrite=0;
		this.maxSize = maxSize;
	}
	
	/**
	 * Adiciona elemento na lista
	 * @param data
	 */
	public void add(byte[] data){
		synchronized(object) {
			this.array[pWrite++ % maxSize] = data;
		}
	}
	
	/**
	 * Retira elemento do array
	 * @return
	 */
	public byte[] get(){
		if(pWrite == pRead){
			return null;
		} else {
			synchronized(object  ){
				return this.array[pRead++ % maxSize];
			}
		}
	}
	
	/**
	 * Verifica se tem dado
	 * @return
	 */
	public boolean avalible(){
		return pRead != pWrite;
	}
}
