package br.ufc.great.pc.tutorial.threads.semaforos.cigarros.agentes;

import java.util.concurrent.Semaphore;

import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.Main;

public class AgentA extends Agent implements Runnable {
	public AgentA(Semaphore agentSemaphore, Semaphore tobacco, Semaphore paper, Semaphore match) {
		super(agentSemaphore, tobacco, paper, match);
	}

	@Override
	public void run() {
		while (Main.control) {
			try {
				agentSemaphore.acquire();
				mutex2.acquire();
				try {	
					if (Main.control){
						System.out.println("Agente A");
						tobacco.release();
					}
					paper.release();
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
