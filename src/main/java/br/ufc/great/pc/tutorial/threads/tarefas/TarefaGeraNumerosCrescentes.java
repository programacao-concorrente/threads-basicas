package br.ufc.great.pc.tutorial.threads.tarefas;

public class TarefaGeraNumerosCrescentes implements Runnable{
	String nome; 
	int quantidade; 
	
	public TarefaGeraNumerosCrescentes(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	@Override
	public void run() {
		for(int i=1; i<quantidade+1; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Na thread " + nome + " a contagem é: " + i);
		}
		System.out.println("A thread " + nome + " terminou!");
	}

}
