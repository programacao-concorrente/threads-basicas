package br.ufc.great.pc.tutorial.threads;

import br.ufc.great.pc.tutorial.threads.recurso.Buffer;
import br.ufc.great.pc.tutorial.threads.tarefas.*;

public class ProdutorConsumidor {
	static int tamanhoBuffer=10;
	
	public static void checaParametros(String[] args) {			
		switch (args[0]) {
		case "-s":
			//existe o segundo argumento
			if (!args[1].isEmpty()) {
				try {
					String buffer = args[1];
					tamanhoBuffer = Integer.parseInt(buffer);
				}catch (Exception e) {
					System.out.println("É preciso definir o tamanho do Buffer!");
				}
			}else {
				throw new RuntimeException("É preciso definir o tamanho do Buffer");
			}
			break;
		case "-h":
			System.exit(0);
			break;
		default:
			throw new RuntimeException("Use os parâmetros -s N para definir o tamanho do Buffer!");
		}
	}
	
	public static void main(String[] args) {
		//Checa se existem argumentos na execução do programa
		if (args.length > 0) {
			checaParametros(args); 
		}
		
		Buffer buffer = new Buffer(tamanhoBuffer);
		
	    ThreadProdutor p = new ThreadProdutor(buffer);
	    p.setName("Produtor");
	    
	    ThreadConsumidor c1 = new ThreadConsumidor(buffer);
	    c1.setName("Consumidor 1");
	    
	    ThreadConsumidor c2 = new ThreadConsumidor(buffer);
	    c2.setName("Consumidor 2");
	    
	    ThreadConsumidor c3 = new ThreadConsumidor(buffer);
	    c3.setName("Consumidor 3");
	    
	    ThreadConsumidor c4 = new ThreadConsumidor(buffer);
	    c4.setName("Consumidor 4");
	    
	    ThreadConsumidor c5 = new ThreadConsumidor(buffer);
	    c5.setName("Consumidor 5");
	    
	    ThreadConsumidor c6 = new ThreadConsumidor(buffer);
	    c6.setName("Consumidor 6");
	    
	    ThreadConsumidor c7 = new ThreadConsumidor(buffer);
	    c7.setName("Consumidor 7");
	    
	    ThreadConsumidor c8 = new ThreadConsumidor(buffer);
	    c8.setName("Consumidor 8");
	    
	    ThreadConsumidor c9 = new ThreadConsumidor(buffer);
	    c9.setName("Consumidor 9");
	    
	    ThreadConsumidor c10 = new ThreadConsumidor(buffer);
	    c10.setName("Consumidor 10"); 
	    
	    p.start();
	    
	    c1.start();
	    c2.start();
	    c3.start();
	    c4.start();
	    c5.start();
	    c6.start();
	    c7.start();
	    c8.start();
	    c9.start();
	    c10.start();

	  }
	}
