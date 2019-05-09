package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

public class AgentB extends Agent implements Runnable {

	public AgentB(Semaphore agentSemaphore, Semaphore tobacco, Semaphore paper, Semaphore match) {
		super(agentSemaphore, tobacco, paper, match);
	}

	@Override
	public void run() {
		while (Main.control) {
			agentSemaphore.waits();
			mutex2.waits();
			if (Main.control){
				System.out.println("Agente B");
			}
			match.signals();
			paper.signals();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("...");
		}

	}

}
