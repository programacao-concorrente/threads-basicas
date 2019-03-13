package br.ufc.great.pc.tutorial.threads.tarefas.matrizes;

import br.ufc.great.pc.tutorial.threads.utils.GeraInteiroRandomico;

public class TarefaPopulaMatriz implements Runnable {

	private int[][] matriz;
	private int[][] resultado;
	private String nome;
	
	public int[][] getMatrizPopulada() {
		return resultado;
	}

	public void setResultado(int[][] resultado) {
		this.resultado = resultado;
	}
	
	public TarefaPopulaMatriz(int[][] matriz, String nome) {
		this.matriz = matriz;
		this.nome = nome;
	}

	/**
	 * Dada uma matriz lxc (linhas x colunas) popula essa matriz com inteiros (entre 1 e 100) randomicos.
	 * @param matriz Matriz lxc (linhas x colunas)
	 * @return matriz populada com inteiros randomicos entre 1 e 100
	 */
	public int[][] populaMatriz(int[][] matriz) {	
		int linhas = matriz[0].length;
		int colunas = matriz[1].length;
		
		//alimenta a matriz com valores aleatorios
		for(int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				new GeraInteiroRandomico();
				matriz[i][j] = GeraInteiroRandomico.geraInteiroRandomicoNoIntervalo(1, 100);
			}
		}
		
		return matriz;
	}

	/**
	 * Ação principal desse Runnable
	 */
	@Override
	public void run() {
		System.out.println("Thread " + nome + " iniciada");
		resultado = populaMatriz(matriz);
	}
}
