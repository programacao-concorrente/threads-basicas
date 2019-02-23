package br.ufc.great.pc.tutorial.threads;

import java.time.Duration;
import java.time.Instant;

import br.ufc.great.pc.tutorial.threads.tarefas.TarefaGeraNumerosCrescentes;
import br.ufc.great.pc.tutorial.threads.tarefas.TarefaGeraNumerosDecrescentes;
import br.ufc.great.pc.tutorial.threads.utils.Sound;

public class App 
{
	static String threadPrincipal = Thread.currentThread().getName();
	
    public static void main( String[] args )
    {    	     
        System.out.println( "Java Oracle Tutorial Threads" ); 
        System.out.println("Thread: " + threadPrincipal + " iniciou!"); 
        System.out.println("----------- Execução contínua -----------");    
        
        Instant start1 = Instant.now();
        //your code        
        fluxoNormal();        
        Instant end1 = Instant.now();
        Duration timeElapsed1 = Duration.between(start1, end1);
        System.out.println("Time taken in normal flow: "+ timeElapsed1.toMillis() +" milliseconds");
        
        System.out.println();
        System.out.println("----------- Execução concorrente -----------");
        Instant start2 = Instant.now();
        //your code        
        fluxoEmParalelo(); 
        System.out.println("Termino no método main.");
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        System.out.println("Time taken in parallel flow: "+ timeElapsed2.toMillis() +" milliseconds");

    }

	private static void fluxoNormal() {		                    
        fazContagem(20);
        tocaSom(20);
	}

	private static void fazContagem(int quantidade) {
		//Contagem crescente
		for(int i=1; i < quantidade+1; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Na thread " + Thread.currentThread().getName() + " a contagem crescente é: " + i);
		}

		//Contagem decrescente
		for(int i=quantidade; i > 0; i--) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Na thread " + Thread.currentThread().getName() + " a contagem é decrescente: " + i);
		}

	}

	private static void tocaSom(int quantidade) {
		try {
        	System.out.println("Toca o som DJ!");
        	for(int i=1; i<quantidade+1; i++) {
        		Sound.tone(500,100);
        		Thread.sleep(100);
        		System.out.println("Thread " + Thread.currentThread().getName() + " tocando " + i + "...");
        	}
    		Sound.tone(1000,100);
    		Thread.sleep(500);        	
        }catch (Exception e) {
        	e.printStackTrace();
        }
	}
		
	private static void fluxoEmParalelo() {                    
        System.out.println("Thread : " + Thread.currentThread().getName() + " em ação!");
    	
    	TarefaGeraNumerosCrescentes geraNumerosCrescentes = new TarefaGeraNumerosCrescentes("Gera Numeros Crescentes", 20); 
    	Thread threadGeraNumerosCrescentes = new Thread(geraNumerosCrescentes); 

    	TarefaGeraNumerosDecrescentes geraNumerosDecrescentes = new TarefaGeraNumerosDecrescentes("Gera Numeros Decrecentes", 20); 
    	Thread threadGeraNumerosDecrescentes = new Thread(geraNumerosDecrescentes); 

    	threadGeraNumerosCrescentes.start();
    	threadGeraNumerosDecrescentes.start();
 
        tocaSom(20);
        System.out.println("Thread " + Thread.currentThread().getName() + " terminou.");
	}	

}
