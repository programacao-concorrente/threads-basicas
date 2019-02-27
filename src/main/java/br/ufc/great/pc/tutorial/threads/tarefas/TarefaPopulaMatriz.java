package br.ufc.great.pc.tutorial.threads.tarefas;

import br.ufc.great.pc.tutorial.threads.utils.GeraInteiroRandomico;

public class TarefaPopulaMatriz implements Runnable {

	private int[][] matriz;
	private int[][] resultado;
	
	public int[][] getMatrizPopulada() {
		return resultado;
	}

	public void setResultado(int[][] resultado) {
		this.resultado = resultado;
	}
	
	public TarefaPopulaMatriz(int[][] matriz) {
		this.matriz = matriz;
	}

	public int[][] populaMatriz(int[][] matriz) {	
		int linhas = matriz[0].length;
		int colunas = matriz[1].length;
		
		//alimenta a matriz com valores aleatorios
		for(int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				matriz[i][j] = new GeraInteiroRandomico().geraInteiroRandomicoNoIntervalo(1, 100);
			}
		}
		
		return matriz;
	}

	@Override
	public void run() {
		resultado = populaMatriz(matriz);
		
	}
}
