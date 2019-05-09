package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

public class AgentA extends Agent implements Runnable {
	public AgentA(Semaphore agentSemaphore, Semaphore tobacco, Semaphore paper, Semaphore match) {
		super(agentSemaphore, tobacco, paper, match);
	}

	@Override
	public void run() {
		while (Main.control) {
			agentSemaphore.waits();
			mutex2.waits();
			try {	
				if (Main.control){
					System.out.println("Agente A");
					tobacco.signals();
				}
				paper.signals();
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("...");
		}

	}

}
