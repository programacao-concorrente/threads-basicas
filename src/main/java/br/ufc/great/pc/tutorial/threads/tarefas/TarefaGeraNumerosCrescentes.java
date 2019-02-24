package br.ufc.great.pc.tutorial.threads.tarefas;

public class TarefaGeraNumerosCrescentes implements Runnable{
	String nome; 
	int quantidade; 
	
	/**
	 * Gera uma sequência de números crescentes
	 * @param nome Nome da tarefa
	 * @param quantidade interações que serão feitas na sequência
	 */
	public TarefaGeraNumerosCrescentes(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public String getNome() {
		return this.nome;
	}
		
	@Override
	public void run() {
		for(int i=1; i<quantidade+1; i++) {
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
