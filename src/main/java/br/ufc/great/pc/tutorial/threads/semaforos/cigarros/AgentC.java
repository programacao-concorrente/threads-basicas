package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

public class AgentC extends Agent implements Runnable {

	public AgentC(Semaphore agentSemaphore, Semaphore tobacco, Semaphore paper, Semaphore match) {
		super(agentSemaphore, tobacco, paper, match);
	}

	@Override
	public void run() {
		while (Main.control) {
			agentSemaphore.waits();
			mutex2.waits();
			if (Main.control){
				System.out.println("Agente C");
			}
			tobacco.signals();
			match.signals();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("...");			
			
		}
	}

}
