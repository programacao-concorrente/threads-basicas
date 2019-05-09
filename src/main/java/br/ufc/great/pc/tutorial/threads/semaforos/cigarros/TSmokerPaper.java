package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.concurrent.Semaphore;

public class TSmokerPaper extends Thread {

	public int tableUsed;
	public int item;
	public int[] generatedItem = new int[2];
	public int generated;
	public String[] items = new String[3];
	public Semaphore table = new Semaphore(1);
	public Semaphore smokerPaper = new Semaphore(1);
	public Semaphore agentProc = new Semaphore(1);

	public TSmokerPaper(int tableUsed, int item, int[] generatedItem, int generated, String[] items, Semaphore table, Semaphore smokerPaper, Semaphore agentProc) {
		this.tableUsed = tableUsed;
		this.item = item;
		this.generatedItem = generatedItem;
		this.generated = generated;
		this.items = items;
		this.table = table;
		this.smokerPaper =  smokerPaper;
		this.agentProc = agentProc;
	}

	public TSmokerPaper() {
		
	}

	void process_smoker(){
		while(true){
			try {
				this.smokerPaper.acquire();
				this.table.acquire();
				if(tableUsed==0){ //table livre
					if(generated==1 && generatedItem[0]==1 && generatedItem[1] == 3){
						System.out.println("smoker paper completed his smoking");
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
