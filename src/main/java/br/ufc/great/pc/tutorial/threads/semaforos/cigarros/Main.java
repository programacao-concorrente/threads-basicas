package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.concurrent.Semaphore;

import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.agentes.GenericAgent;
import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.pushers.PusherA;
import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.pushers.PusherB;
import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.pushers.PusherC;
import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.smokers.SmokerWithMatch;
import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.smokers.SmokerWithPaper;
import br.ufc.great.pc.tutorial.threads.semaforos.cigarros.smokers.SmokerWithTobacco;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
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

		Thread agente = new Thread(new GenericAgent(agentSemaphore, tobacco, paper, match), "Agente");
		Thread pusherA = new Thread(new PusherA(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex), "PucherA");
		Thread pusherB = new Thread(new PusherB(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex), "PucherA");
		Thread pusherC = new Thread(new PusherC(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem, paperSem, matchSem, mutex), "PucherA");
		Thread smokerA = new Thread(new SmokerWithMatch(tobaccoSem, paperSem, matchSem, agentSemaphore),"SmokerWithMatch");
		Thread smokerB = new Thread(new SmokerWithPaper(tobaccoSem, paperSem, matchSem, agentSemaphore),"SmokerWithPaper");
		Thread smokerC = new Thread(new SmokerWithTobacco(tobaccoSem, paperSem, matchSem, agentSemaphore),"SmokerWithTobacco");
		
		agente.start();
		pusherA.start();
		pusherB.start();
		pusherC.start();
		smokerA.start();
		smokerB.start();
		smokerC.start();

	}

}