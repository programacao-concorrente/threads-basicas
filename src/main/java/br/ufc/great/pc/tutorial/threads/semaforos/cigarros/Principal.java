package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Principal{

	static int tableUsed=1;
	static int[] generatedItem = new int[2];
	static int generated;

	static String[] items = new String[3];

	static Semaphore table = new Semaphore(1);
	static int item;

	static Semaphore smokerTobaco = new Semaphore(1);
	static Semaphore smokerPaper = new Semaphore(1);
	static Semaphore smokerMatch = new Semaphore(1);
	static Semaphore agentProc = new Semaphore(1);

	static void init() {
		items[0] = "tobaco";
		items[1] = "paper";
		items[2] = "match";
	}
	
	private void agent(){
		while(true){
			try {
				//sleep(1); //processo dorme 1 segundo
				this.agentProc.acquire();

				Random ramdomic = null;
				item = ramdomic.nextInt() % 3; //numero randomico entre 1 e 3
				item = item + 1;
				System.out.println("item " + item);

				if(item==3){ //matches
					this.table.acquire();
					if(tableUsed==1){ 
						generatedItem[0]=1;    //item 1 (tobaco)
						generatedItem[1]=2;    //item 2 (paper)
						System.out.println("Agent is prodused " + items[0] + " " + items[1]); 
						generated=1;
						tableUsed=0;           //libera a mesa
						this.smokerMatch.release(); //acorda o processo smoker_match
					}
					this.table.release();
				}else if (item==2){ //paper
					this.table.acquire();
					if(tableUsed==1){ 
						generatedItem[0]=1;    //item 1 (tobaco)
						generatedItem[1]=3;    //item 3 (match)
						System.out.println("Agent is prodused " + items[0] + " " + items[2]); 
						generated=1;
						tableUsed=0;           //libera a mesa
						this.smokerPaper.release(); //acorda o processo smoker_paper
					}
					this.table.release();
				}else{ // tobaco
					this.table.acquire();
					if(tableUsed==1){ 
						generatedItem[0]=2; //item 2 (paper)
						generatedItem[1]=3; //item 3 (match)
						System.out.println("Agent is prodused " + items[1] + " " + items[2]); 
						generated=1;
						tableUsed=0; //libera a mesa
						this.smokerTobaco.release(); //acorda o processo smoker_tobaco
					}
					this.table.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void process_smoker1(){
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

	void process_smoker2(){
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

	void process_smoker3(){
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

	public static void executa(){
		init();
				
		TSmokerMatch smokerMatch1 = new TSmokerMatch();
		TSmokerPaper smokerPaper1 = new TSmokerPaper();
		TSmokerTobaco smokerTobaco1 = new TSmokerTobaco();
		
		while(true){
			try {
				//sleep(1); //processo dorme 1 segundo
				agentProc.acquire();

				Random ramdomic = new Random();
				item = ramdomic.nextInt(3); //numero randomico entre 1 e 3
				item = item + 1;
				System.out.println("item " + item);

				if(item==3){ //matches
					table.acquire();
					
					if(tableUsed==1){ 
						generatedItem[0]=1;    //item 1 (tobaco)
						generatedItem[1]=2;    //item 2 (paper)
						System.out.println("Agent is prodused " + items[0] + " " + items[1]); 
						generated=1;
						tableUsed=0;           //libera a mesa
						smokerMatch.release(); //acorda o processo smoker_match
				
						smokerMatch1.tableUsed = tableUsed;
						smokerMatch1.item = item;
						smokerMatch1.generatedItem = generatedItem;
						smokerMatch1.generated = generated;
						smokerMatch1.items = items;
						smokerMatch1.table = table;
						smokerMatch1.smokerMatch = smokerMatch;
						smokerMatch1.agentProc = agentProc;
						smokerMatch1.start();
						tableUsed = smokerMatch1.tableUsed;
						item = smokerMatch1.item;
						generatedItem = smokerMatch1.generatedItem;
						generated = smokerMatch1.generated;
						items = smokerMatch1.items;
						table = smokerMatch1.table;
						smokerPaper = smokerMatch1.smokerMatch; 
						agentProc = smokerMatch1.agentProc;
					}
					table.release();
				}else if (item==2){ //paper
					table.acquire();
					if(tableUsed==1){ 
						generatedItem[0]=1;    //item 1 (tobaco)
						generatedItem[1]=3;    //item 3 (match)
						System.out.println("Agent is prodused " + items[0] + " " + items[2]); 
						generated=1;
						tableUsed=0;           //libera a mesa
						smokerPaper.release(); //acorda o processo smoker_paper
						
						smokerPaper1.tableUsed = tableUsed;
						smokerPaper1.item = item;
						smokerPaper1.generatedItem = generatedItem;
						smokerPaper1.generated = generated;
						smokerPaper1.items = items;
						smokerPaper1.table = table;
						smokerPaper1.smokerPaper = smokerPaper;
						smokerPaper1.agentProc = agentProc;
						smokerPaper1.start();
						tableUsed = smokerPaper1.tableUsed;
						item = smokerPaper1.item;
						generatedItem = smokerPaper1.generatedItem;
						generated = smokerPaper1.generated;
						items = smokerPaper1.items;
						table = smokerPaper1.table;
						smokerPaper = smokerPaper1.smokerPaper; 
						agentProc = smokerPaper1.agentProc;
					}
					table.release();
				}else{ // tobaco
					table.acquire();
					if(tableUsed==1){ 
						generatedItem[0]=2; //item 2 (paper)
						generatedItem[1]=3; //item 3 (match)
						System.out.println("Agent is prodused " + items[1] + " " + items[2]); 
						generated=1;
						tableUsed=0; //libera a mesa
						smokerTobaco.release(); //acorda o processo smoker_tobaco
						
						smokerTobaco1.tableUsed = tableUsed;
						smokerTobaco1.item = item;
						smokerTobaco1.generatedItem = generatedItem;
						smokerTobaco1.generated = generated;
						smokerTobaco1.items = items;
						smokerTobaco1.table = table;
						smokerTobaco1.smokerTobaco = smokerTobaco;
						smokerTobaco1.agentProc = agentProc;
						smokerTobaco1.start();
						
						tableUsed = smokerTobaco1.tableUsed;
						item = smokerTobaco1.item;
						generatedItem = smokerTobaco1.generatedItem;
						generated = smokerTobaco1.generated;
						items = smokerTobaco1.items;
						table = smokerTobaco1.table;
						smokerTobaco = smokerTobaco1.smokerTobaco; 
						agentProc = smokerTobaco1.agentProc;
					}
					table.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String args[]) {
		executa();
	}
}