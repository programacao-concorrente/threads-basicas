package br.ufc.great.pc.tutorial.threads.semaforos.cigarros.util;

public class Semaphores {
	public volatile int initial;
	private static int lock=1;
	public Semaphores(int initial) {
		this.initial = initial;
	}
	public void waits(){
		while(initial<=0){
			
		};
		if(initial>0)initial--;
	}
	public  void signals(){
		initial++;

	}
}
