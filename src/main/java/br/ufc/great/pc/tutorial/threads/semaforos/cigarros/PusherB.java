package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

public class PusherB extends Pusher implements Runnable {

	public PusherB(boolean isMatch, boolean isTobacco, boolean isPaper,
			Semaphore tobacco, Semaphore paper, Semaphore match,
			Semaphore tobaccoSem, Semaphore paperSem, Semaphore matchSem,
			Semaphore mutex) {
		
		super(isMatch, isTobacco, isPaper, tobacco, paper, match, tobaccoSem,
				paperSem, matchSem, mutex);
	}

	@Override
	public void scheduleSmoker() {
	}

	@Override
	public void run() {
		while (true) {
			paper.waits();
			mutex.waits();
			if (isTobacco) {
				Pusher.isTobacco = false;
				matchSem.signals();

			} else if (isMatch) {
				Pusher.isMatch = false;
				tobaccoSem.signals();
			} else {
				Pusher.isPaper = true;
			}
			mutex.signals();
		}
	}
}
