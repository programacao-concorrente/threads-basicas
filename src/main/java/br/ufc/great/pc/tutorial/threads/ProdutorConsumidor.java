package br.ufc.great.pc.tutorial.threads;

import br.ufc.great.pc.tutorial.threads.recurso.Buffer;
import br.ufc.great.pc.tutorial.threads.tarefas.*;

public class ProdutorConsumidor {

	public static void main(String[] args) {
		int tamanhoDoBuffer = 6; 
		Buffer buffer = new Buffer(tamanhoDoBuffer);
		
	    Produtor p = new Produtor(buffer);
	    Consumidor c1 = new Consumidor(buffer);
	    Consumidor c2 = new Consumidor(buffer);
	    Consumidor c3 = new Consumidor(buffer);
	    Consumidor c4 = new Consumidor(buffer);
	    Consumidor c5 = new Consumidor(buffer);
	    Consumidor c6 = new Consumidor(buffer);
	    
	    p.start();
	    c1.start();
	    c2.start();
	    c3.start();
	    c4.start();
	    c5.start();
	    c6.start();
	    
	  }
	}
