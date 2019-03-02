package br.ufc.great.pc.tutorial.threads.tarefas;

import br.ufc.great.pc.tutorial.threads.recurso.Buffer;

public class Consumidor extends Thread {
	  private Buffer buffer;

	  public Consumidor(Buffer buffer) {
	    this.buffer = buffer;
	  }

	  public void run() {
	      buffer.consume();
	  }
	}