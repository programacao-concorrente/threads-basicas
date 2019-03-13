package br.ufc.great.pc.tutorial.threads.tarefas.matrizes;

import br.ufc.great.pc.tutorial.threads.utils.GeraInteiroRandomico;

public class TarefaPopulaMatrizCom1 implements Runnable {

	private int[][] matriz;
	private int[][] resultado;
	
	
	public int[][] getMatrizPopulada() {
		return resultado;
	}

	public void setResultado(int[][] resultado) {
		this.resultado = resultado;
	}

	public TarefaPopulaMatrizCom1(int[][] matriz) {
		this.matriz = matriz;
	}

	/**
	 * Dada uma matriz lxc (linhas x colunas) popula essa matriz com 1.
	 * @param matriz Matriz lxc (linhas x colunas)
	 * @return matriz populada o número 1
	 */
	public static int[][] populaMatrizCom1(int[][] matriz) {	
		int linhas = matriz[0].length;
		int colunas = matriz[1].length;
		
		//alimenta a matriz com valores aleatorios
		for(int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				matriz[i][j] = 1;
			}
		}
		
		return matriz;
	}

	/**
	 * Ação principal do runnable
	 */
	@Override
	public void run() {
		resultado = populaMatrizCom1(matriz);
	}

}