package br.ufc.great.pc.tutorial.threads.tarefas;

public class TarefaGeraNumerosDecrescentes implements Runnable{
	String nome; 
	int quantidade;
	
	/**
	 * Gera uma sequência de números decrescentes
	 * @param nome Nome da tarefa
	 * @param quantidade interações que serão feitas na sequência de números
	 */
	public TarefaGeraNumerosDecrescentes(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return this.nome;
	}
	
	@Override
	public void run() {
		for(int i=quantidade; i>0; i--) {
			System.out.println("Na thread " + nome + " a contagem é: " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		System.out.println("A thread " + nome + " terminou!");
	}

}
