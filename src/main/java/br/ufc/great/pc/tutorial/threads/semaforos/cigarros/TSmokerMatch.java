package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.concurrent.Semaphore;

public class TSmokerMatch extends Thread {

	public int tableUsed;
	public int item;
	public int[] generatedItem = new int[2];
	public int generated;
	public String[] items = new String[3];
	public Semaphore table = new Semaphore(1);
	public Semaphore smokerMatch = new Semaphore(1);
	public Semaphore agentProc = new Semaphore(1);

	public TSmokerMatch(int tableUsed, int item, int[] generatedItem, int generated, String[] items, Semaphore table, Semaphore smokerMatch, Semaphore agentProc) {
		this.tableUsed = tableUsed;
		this.item = item;
		this.generatedItem = generatedItem;
		this.generated = generated;
		this.items = items;
		this.table = table;
		this.smokerMatch =  smokerMatch;
		this.agentProc = agentProc;
	}

	public TSmokerMatch() {
		
	}
	
	private void process_smoker(){
		while(true){
			try {
				this.smokerMatch.acquire();
				this.table.acquire();
				if(tableUsed==0){ //table livre
					if(generated==1 && generatedItem[0]==1 && generatedItem[1] == 2){
						System.out.println("smoker match completed his smoking");
						tableUsed=1;
						generated=0;
					}
				}
				this.table.release();
				this.agentProc.release(); //acorda o processo agente
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		this.process_smoker();
	}
}
