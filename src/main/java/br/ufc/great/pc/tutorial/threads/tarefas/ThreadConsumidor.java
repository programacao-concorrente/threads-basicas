package br.ufc.great.pc.tutorial.threads.tarefas;

import br.ufc.great.pc.tutorial.threads.recurso.Buffer;

public class ThreadConsumidor extends Thread {
	  private Buffer buffer;

	  public ThreadConsumidor(Buffer buffer) {
	    this.buffer = buffer;
	  }

	  public void run() {
	      buffer.consume();
	  }
	}