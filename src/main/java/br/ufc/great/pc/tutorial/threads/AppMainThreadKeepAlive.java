package br.ufc.great.pc.tutorial.threads;

import br.ufc.great.pc.tutorial.threads.tarefas.TarefaGeraNumerosCrescentes;
import br.ufc.great.pc.tutorial.threads.tarefas.TarefaGeraNumerosDecrescentes;
import br.ufc.great.pc.tutorial.threads.utils.Sound;

public class AppMainThreadKeepAlive 
{
	static String threadPrincipal = Thread.currentThread().getName();
	
    public static void main( String[] args )
    {    	     
        System.out.println( "Java Oracle Tutorial Threads" ); 
        System.out.println("Thread: " + threadPrincipal + " iniciou!"); 
        
    	//usandoIsAliveParaManterThreadPrincipal();
        usandoJoinParaManterThreadPrincipal();
        //setandoPrioridadesNasThreads();
        
        System.out.println("Thread " + Thread.currentThread().getName() + " terminou!");
               
    }

    /**
     * Manter a thread principal ativa mesmo com as threads filhas ativas, para isso, usa a técnica de um loop checando se ainda tem 
     * pelo menos uma thread filha ativa. 
     */
	private static void usandoIsAliveParaManterThreadPrincipal() {
		//Cria a tarefa e a thread para gerar números crescentes
        TarefaGeraNumerosCrescentes geraNumerosCrescentes = new TarefaGeraNumerosCrescentes("Gera Numeros Crescentes", 10); 
    	Thread threadGeraNumerosCrescentes = new Thread(geraNumerosCrescentes); 

    	//Cria a tarefa e a thread para gerar números decrescentes
    	TarefaGeraNumerosDecrescentes geraNumerosDecrescentes = new TarefaGeraNumerosDecrescentes("Gera Numeros Decrecentes", 20); 
    	Thread threadGeraNumerosDecrescentes = new Thread(geraNumerosDecrescentes); 

    	//inicializa as threads das tarefas
    	threadGeraNumerosCrescentes.start();
    	threadGeraNumerosDecrescentes.start();

    	//laco para manter a thread main (principal) ativa enquanto houver thread ativa
        do {
        	System.out.println("Thread " + Thread.currentThread().getName() + " ativa.");
        	tocaSom(1);
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}        	
        }while(threadGeraNumerosCrescentes.isAlive() || threadGeraNumerosDecrescentes.isAlive());
	}

	/**
	 * Manter a thread principal ativa durante a execução das threads filhas, para isso, usa o método join das threads.
	 */
	private static void usandoJoinParaManterThreadPrincipal() {
		//Cria a tarefa e a thread para gerar números crescentes
        TarefaGeraNumerosCrescentes geraNumerosCrescentes = new TarefaGeraNumerosCrescentes("Gera Numeros Crescentes", 10); 
    	Thread threadGeraNumerosCrescentes = new Thread(geraNumerosCrescentes, geraNumerosCrescentes.getNome()); 

    	//Cria a tarefa e a thread para gerar números decrescentes
    	TarefaGeraNumerosDecrescentes geraNumerosDecrescentes = new TarefaGeraNumerosDecrescentes("Gera Numeros Decrecentes", 20); 
    	Thread threadGeraNumerosDecrescentes = new Thread(geraNumerosDecrescentes, geraNumerosDecrescentes.getNome()); 

    	//inicializa as threads das tarefas
    	threadGeraNumerosCrescentes.start();
    	threadGeraNumerosDecrescentes.start();

    	tocaSom(1);
    	
    	try {
			threadGeraNumerosCrescentes.join();
			System.out.println("A thread " + threadGeraNumerosCrescentes.getName()+ " is joined!");
			threadGeraNumerosDecrescentes.join();
			System.out.println("A thread " + threadGeraNumerosDecrescentes.getName() + " is joined!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	tocaSom(1);
	}
	
	/**
	 * Manter a thread principal ativa durante a execução das threads filhas, além disso, 
	 * seta as prioridades das threads para indicar quais as threds serão mais importantes durante a execução das mesmas. 
	 * A prioridade das threads varia de 1 a 10, de menor prioridade a mais alta prioridade.
	 */
	private static void setandoPrioridadesNasThreads() {
		//Cria a tarefa e a thread para gerar números crescentes
        TarefaGeraNumerosCrescentes geraNumerosCrescentes = new TarefaGeraNumerosCrescentes("Gera Numeros Crescentes", 10); 
    	Thread threadGeraNumerosCrescentes = new Thread(geraNumerosCrescentes, geraNumerosCrescentes.getNome()); 

    	//Cria a tarefa e a thread para gerar números decrescentes
    	TarefaGeraNumerosDecrescentes geraNumerosDecrescentes = new TarefaGeraNumerosDecrescentes("Gera Numeros Decrecentes", 20); 
    	Thread threadGeraNumerosDecrescentes = new Thread(geraNumerosDecrescentes, geraNumerosDecrescentes.getNome()); 

    	//Alterando as prioridades das Threads
    	threadGeraNumerosCrescentes.setPriority(Thread.NORM_PRIORITY-3);
    	threadGeraNumerosDecrescentes.setPriority(Thread.NORM_PRIORITY+5);    	
    	
    	//inicializa as threads das tarefas
    	threadGeraNumerosCrescentes.start();
    	threadGeraNumerosDecrescentes.start();

    	tocaSom(1);
    	
    	//fazendo um join das threads filhas para manter a thread principal ativa
    	try {
			threadGeraNumerosCrescentes.join();
			System.out.println("A thread " + threadGeraNumerosCrescentes.getName()+ " is joined!");
			threadGeraNumerosDecrescentes.join();
			System.out.println("A thread " + threadGeraNumerosDecrescentes.getName() + " is joined!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	tocaSom(1);
	}

	/**
	 * Emite um som n vezes e ao final toca um som mais longo para indicar final.
	 * @param quantidade Quantidade de vezes que o som será repetido
	 */
	private static void tocaSom(int quantidade) {
		try {
        	System.out.println("Toca o som DJ!");
        	for(int i=1; i<quantidade+1; i++) {
        		Sound.tone(500,100);
        		System.out.println("Thread " + Thread.currentThread().getName() + " tocando " + i + "...");
        		Thread.sleep(100);        		
        	}
    		Sound.tone(1000,100);
    		Thread.sleep(100);        	
        }catch (Exception e) {
        	e.printStackTrace();
        }
	}
		
}
