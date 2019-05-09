package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.concurrent.Semaphore;

public class AgentC extends Agent implements Runnable {

	public AgentC(Semaphore agentSemaphore, Semaphore tobacco, Semaphore paper, Semaphore match) {
		super(agentSemaphore, tobacco, paper, match);
	}

	@Override
	public void run() {
		while (Main.control) {
			try {
				agentSemaphore.acquire();
				mutex2.acquire();
				if (Main.control){
					System.out.println("Agente C");
				}
				tobacco.release();
				match.release();
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
		}
	}

}
