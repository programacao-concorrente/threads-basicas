package br.ufc.great.pc.tutorial.threads.tarefas;

import java.util.Random;

import br.ufc.great.pc.tutorial.threads.recurso.Buffer;

public class Produtor extends Thread {
	  private Buffer buffer;

	  public Produtor(Buffer buffer) {
	    this.buffer = buffer;
	  }

	  public void run() {
	    Random rand = new Random();
	  
	    while (true) {
	      int n = rand.nextInt();
	      buffer.produce(n);
	    }
	    
	  }
	  
	}
