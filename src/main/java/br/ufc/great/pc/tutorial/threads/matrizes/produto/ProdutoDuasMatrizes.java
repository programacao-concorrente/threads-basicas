package br.ufc.great.pc.tutorial.threads.matrizes.produto;

public class ProdutoDuasMatrizes {
	/**
	 * Dada uma matriz lxc mostra seu conteudo
	 * @param matriz matriz lxc (linhas x colunas)
	 */
	public void mostraConteudoDaMatriz(int[][] matriz) {
		int linhas = matriz[0].length;
		int colunas = matriz[1].length;
		
		//Imprime os valores da matriz
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				System.out.println("m["+i + "," + j + "]: "+ matriz[i][j]);
			}
		}
	}
	
	/**
	 * Dada as matrizes m e n, onde m e n tem os mesmos tamanhos, calcular o produto
	 * @param m matriz de inteiro lxc
	 * @param n matriz de inteiro lxc
	 * @return produto entre as matrizes m e n
	 */
	public int[][] calculaProduto(int[][] m, int[][] n) {

	    if (m[0].length != n.length) throw new RuntimeException("Dimensões inconsistentes. Impossível multiplicar as matrizes");

	    int[][] resultado = new int[ m.length ][ n[0].length ];

	    for (int i = 0; i < m.length; i++)
	        for (int j = 0; j < n[0].length; j++) {
	            int somatoria = 0;
	            for (int k = 0; k < m[0].length; k++) {
	                int produto = m[i][k] * n[k][j];
	                somatoria = somatoria + produto;
	            }
	            resultado[i][j] = somatoria ;
	        }
	    return resultado ;
	}

	/**
	 * Dada as matrizes m e n, calcular o produto de forma simplificada, ou seja, sem acumular o somatório das linhas
	 * @param m matriz de inteiro lxc
	 * @param n matriz de inteiro lxc
	 * @return produto entre as matrizes m e n
	 */
	public int[][] calculoSimplificadoDoProduto(int[][] m, int[][] n) {

	    if (m[0].length != n.length) throw new RuntimeException("Dimensões inconsistentes. Impossível multiplicar as matrizes");

	    int[][] resultado = new int[ m.length ][ n[0].length ];

	    for (int i = 0; i < m.length; i++)
	        for (int j = 0; j < n[0].length; j++) {
	            for (int k = 0; k < m[0].length; k++) {
	                int produto = m[i][k] * n[k][j];
	                resultado[i][j] = resultado[i][j] + produto;
	            }
	        }
	    return resultado ;
	}

}