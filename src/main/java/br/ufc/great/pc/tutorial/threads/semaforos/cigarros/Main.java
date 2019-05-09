package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static ExecutorService application;
	public static boolean control=true;
	
	public static void main(String[] args) throws InterruptedException {
		Semaphore agentSemaphore = new Semaphore(1);
		Semaphore mutex = new Semaphore(1);
		Semaphore tobacco = new Semaphore(0);
		Semaphore paper = new Semaphore(0);
		Semaphore match = new Semaphore(0);
		Semaphore tobaccoSem = new Semaphore(0);
		Semaphore paperSem = new Semaphore(0);
		Semaphore matchSem = new Semaphore(0);
	
		Boolean isMatch = new Boolean(false);
		Boolean isTobacco = new Boolean(false);
		Boolean isPaper = new Boolean(false);

		application = Executors.newCachedThreadPool();
		application.execute(new AgentA(agentSemaphore, tobacco, paper, match));
		application.execute(new AgentB(agentSemaphore, tobacco, paper, match));
		application.execute(new AgentC(agentSemaphore, tobacco, paper, match));

		application.execute(new PusherA(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex));
		application.execute(new PusherB(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex));
		application.execute(new PusherC(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex));
		
		application.execute(new SmokerWithMatch(tobaccoSem, paperSem, matchSem, agentSemaphore));
		application.execute(new SmokerWithPaper(tobaccoSem, paperSem, matchSem, agentSemaphore));
		application.execute(new SmokerWithTobacco(tobaccoSem, paperSem, matchSem, agentSemaphore));

		if (application.awaitTermination(6, TimeUnit.SECONDS)) {
		} 

	}

}