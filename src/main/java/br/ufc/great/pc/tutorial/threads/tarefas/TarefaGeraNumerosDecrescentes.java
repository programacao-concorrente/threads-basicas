package br.ufc.great.pc.tutorial.threads.tarefas;

public class TarefaGeraNumerosDecrescentes implements Runnable{
	String nome; 
	int quantidade;
	
	public TarefaGeraNumerosDecrescentes(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	@Override
	public void run() {
		for(int i=quantidade; i>0; i--) {
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
