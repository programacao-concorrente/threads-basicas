package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SmokerWithTobacco extends Smoker implements Runnable {

	public SmokerWithTobacco(Semaphore tobaccoSem, Semaphore paperSem, Semaphore matchSem, Semaphore agentSemaphore) {
		super(tobaccoSem, paperSem, matchSem, agentSemaphore);
	}

	@Override
	public void makeCigarette() {
		if (Main.control) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Smoker with tobacco making cigarette "+dateFormat.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("SmokerWithTobacco fumando...");
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void run() {
		while (Main.control) {
			try {
				tobaccoSem.acquire();
				makeCigarette();
				System.out.println("Weakup Agent...");
				Agent.mutex2.release();
				agentSemaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
