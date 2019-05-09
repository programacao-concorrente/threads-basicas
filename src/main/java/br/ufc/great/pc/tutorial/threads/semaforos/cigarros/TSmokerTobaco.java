package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.concurrent.Semaphore;

public class TSmokerTobaco extends Thread {

	public int tableUsed;
	public int item;
	public int[] generatedItem = new int[2];
	public int generated;
	public String[] items = new String[3];
	public Semaphore table = new Semaphore(1);
	public Semaphore smokerTobaco = new Semaphore(1);
	public Semaphore agentProc = new Semaphore(1);

	public TSmokerTobaco(int tableUsed, int item, int[] generatedItem, int generated, String[] items, Semaphore table, Semaphore smokerTobaco, Semaphore agentProc) {
		this.tableUsed = tableUsed;
		this.item = item;
		this.generatedItem = generatedItem;
		this.generated = generated;
		this.items = items;
		this.table = table;
		this.smokerTobaco =  smokerTobaco;
		this.agentProc = agentProc;
	}

	public TSmokerTobaco() {
		
	}
	
	void process_smoker(){
		while(true){
			try {
				this.smokerTobaco.acquire();
				this.table.acquire();
				if(tableUsed==0){ //table livre
					if(generated==1 && generatedItem[0]==2 && generatedItem[1] == 3){
						System.out.println("smoker tobaco completed his smoking");
						tableUsed=1;
						generated=0;
					}
				}
				this.table.release();
				this.agentProc.release();
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
