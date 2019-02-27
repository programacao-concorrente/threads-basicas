package br.ufc.great.pc.tutorial.threads.matrizes.produto;

import java.util.Random;

import br.ufc.great.pc.tutorial.threads.tarefas.TarefaPopulaMatriz;
import br.ufc.great.pc.tutorial.threads.utils.BarraProgresso;

public class MultiplicacaoSequencial {	
	public static void mostraConteudoDaMatriz(int[][] matriz) {
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
	 * Dada as matrizes a e b, calcular o produto
	 * @param a matriz de inteiro
	 * @param b matriz de inteiro
	 * @return produto entre as matrizes a e b
	 */
	public static int[][] calculaProduto(int[][] a, int[][] b) {

	    if (a[0].length != b.length) throw new RuntimeException("Dimensões inconsistentes. Impossível multiplicar as matrizes");

	    int[][] resultado = new int[ a.length ][ b[0].length ];

	    for (int i = 0; i < a.length; i++)
	        for (int j = 0; j < b[0].length; j++) {
	            int somatoria = 0;
	            for (int k = 0; k < a[0].length; k++) {
	                int produto = a[i][k] * b[k][j];
	                somatoria = somatoria + produto;
	            }
	            resultado[i][j] = somatoria ;
	        }
	    return resultado ;
	}

	/**
	 * Dada as matrizes a e b, calcular o produto
	 * @param a matriz de inteiro
	 * @param b matriz de inteiro
	 * @return produto entre as matrizes a e b
	 */
	public static int[][] calculoSimplificadoDoProduto(int[][] a, int[][] b) {

	    if (a[0].length != b.length) throw new RuntimeException("Dimensões inconsistentes. Impossível multiplicar as matrizes");

	    int[][] resultado = new int[ a.length ][ b[0].length ];

	    for (int i = 0; i < a.length; i++)
	        for (int j = 0; j < b[0].length; j++) {
	            for (int k = 0; k < a[0].length; k++) {
	                int produto = a[i][k] * b[k][j];
	                resultado[i][j] = resultado[i][j] + produto;
	            }
	        }
	    return resultado ;
	}
	
	
	public static void main(String[] args) {
		int linhas=100;
		int colunas=100;
		
		int[][] matrizA = new int[linhas][colunas];
		int[][] matrizB = new int[linhas][colunas]; 
		int[][] matrizC = new int[linhas][colunas];
		
		System.out.println("Matriz A");
		TarefaPopulaMatriz tarefaPopulaMatrizA = new TarefaPopulaMatriz(matrizA);  
		Thread threadPopulaMatrizA = new Thread(tarefaPopulaMatrizA, "Popula Matriz A");
		threadPopulaMatrizA.start();
		
		System.out.println("Matriz B");
		TarefaPopulaMatriz tarefaPopulaMatrizB = new TarefaPopulaMatriz(matrizB);  
		Thread threadPopulaMatrizB = new Thread(tarefaPopulaMatrizB, "Popula Matriz B");
		threadPopulaMatrizB.start();
		
		//Garante que a thread  main (principal) vai rodar até o fim. 
		try {
			threadPopulaMatrizA.join();						
			threadPopulaMatrizB.join();
			
			BarraProgresso barra1 = new BarraProgresso();
			System.out.println(Thread.currentThread() + " populando as matrizes...");
			synchronized (barra1) {							
				barra1.mostraBarraDeProgressoEmTexto();
			}
			
			matrizA = tarefaPopulaMatrizA.getMatrizPopulada(); 
			matrizB = tarefaPopulaMatrizB.getMatrizPopulada();  
			
			//Só faz o cálculo do produto entre as matrizes A e B quando os processos de população das matrizes for concluído
			if (!threadPopulaMatrizA.isAlive() && !threadPopulaMatrizB.isAlive()) {
				System.out.println("Calculando o produto entre as matrizes A e B...");
				matrizC = calculaProduto(matrizA, matrizB);
				System.out.println("Matriz C: ");
				mostraConteudoDaMatriz(matrizC);
			}
			System.out.println("Thread : " + Thread.currentThread() + " terminou!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
								
	}

}
