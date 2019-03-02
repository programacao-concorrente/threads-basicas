package br.ufc.great.pc.tutorial.threads.recurso;

import java.util.LinkedList;
import java.util.List;

public class Buffer {
	private List<Integer> data; 
	private boolean empty;
	private int index;
	private int size;

	public Buffer(int size) {
		this.empty = true;
		this.index = 0;
		this.size = size;
		this.data = new LinkedList<>();
	}

	/**
	 * Produz um valor do buffer, ou seja, adiciona um elemento no vetor de inteiros
	 * @param newData
	 */
	public synchronized void produce(int newData) {
		String threadName = Thread.currentThread().getName();
		
		//checa se a lista está vazia
		if (data.isEmpty()) {
			data.add(newData); 
			index = 0;  
			empty = false;
			notifyAll();;
			System.out.println("Thread : " + threadName);
			System.out.println("Produced: " + newData + " on position: " + index);
			return;
		}
		
		//checa se tem espaço na lista
		if (data.size() <= size) {
			data.add(newData); 
			index = data.size() - 1;  
			empty = false;
			notifyAll();;
			System.out.println("Thread : " + threadName);
			System.out.println("Produced: " + newData + " on position: " + index);	
			return;
		}

		//checa se a lista está cheia
		if (data.size()==size) {
			try {
				System.out.println("Thread : " + threadName + " esperando... ");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Consome um valor do buffer
	 * @return 
	 * @return
	 */
	public synchronized void consume() {
		String threadName = Thread.currentThread().getName();
		
		//Se a lista estiver vazia
		if (this.empty) {
			try {
				System.out.println("Thread " + threadName + " esperando...");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
		synchronized (threadName) {
			//Se tiver pelo menos um elemento na lista pode consumir
			if (data.size() > 1) {
				this.data.remove(index);
				index = data.size() - 1;
				this.empty = false;
				this.notify();
				System.out.println("Thread: " + threadName);
				System.out.println("Consumed:" + data.get(index));			
			}			
		}
	}
}