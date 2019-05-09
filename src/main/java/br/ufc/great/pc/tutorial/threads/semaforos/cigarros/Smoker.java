package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.concurrent.Semaphore;

public abstract class Smoker {
	protected Semaphore tobaccoSem;
	protected Semaphore paperSem;
	protected Semaphore matchSem;
	protected Semaphore agentSemaphore;
	
	public Smoker(Semaphore tobaccoSem, Semaphore paperSem, Semaphore matchSem,Semaphore agentSemaphore) {
		super();
		this.tobaccoSem = tobaccoSem;
		this.paperSem = paperSem;
		this.matchSem = matchSem;
		this.agentSemaphore=agentSemaphore;
	}
	public abstract void makeCigarette();
}
