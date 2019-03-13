package br.ufc.great.pc.tutorial.threads.matrizes.produto;

import br.ufc.great.pc.tutorial.threads.tarefas.matrizes.TarefaPopulaMatriz;
import br.ufc.great.pc.tutorial.threads.utils.BarraProgresso;

public class MultiplicacaoSequencial {	
	public static ProdutoDuasMatrizes produtoDuasMatrizes = new ProdutoDuasMatrizes();
	public static Ajuda ajuda = new Ajuda();
	private static int linhas=100;
	private static int colunas=100;		
	
	public static int checaParametros(String[] args) {
		int tamanho=2;
		
		switch (args[0]) {
		case "-s":
			//existe o segundo argumento
			if (!args[1].isEmpty()) {
				try {
					String tamanhoMatriz = args[1];
					tamanho = Integer.parseInt(tamanhoMatriz);
				}catch (Exception e) {
					System.out.println("É preciso definir o tamanho da matriz como um numero inteiro maior que 2!");
				}
			}else {
				throw new RuntimeException("É preciso definir o tamanho da matriz como um numero inteiro maior que 2!");
			}
			break;
		case "-h":
			ajuda.mostra();
			System.exit(0);
			break;
		default:
			throw new RuntimeException("Use os parâmetros -s N para definir o tamanho da matriz!");
		}
		return tamanho;
	}
	
	public static void main(String[] args) {
		//Checa se existem argumentos na execução do programa
		if (args.length > 0) {
			if (checaParametros(args) >= 2) {
				linhas = checaParametros(args);
				colunas = linhas;
			}
		}
		
		//Cria as matrizes do programa
		int[][] matrizA = new int[linhas][colunas];
		int[][] matrizB = new int[linhas][colunas]; 
		int[][] matrizC = new int[linhas][colunas];
		
		//Cria e inicia uma thread para popular a matrizA
		TarefaPopulaMatriz tarefaPopulaMatrizA = new TarefaPopulaMatriz(matrizA, "Popula matrizA");  
		Thread threadPopulaMatrizA = new Thread(tarefaPopulaMatrizA, "Popula Matriz A");
		threadPopulaMatrizA.start();
		
		//Cria e inicia uma thread para popular a matrizB
		TarefaPopulaMatriz tarefaPopulaMatrizB = new TarefaPopulaMatriz(matrizB, "Popula matrizB");  
		Thread threadPopulaMatrizB = new Thread(tarefaPopulaMatrizB, "Popula Matriz B");
		threadPopulaMatrizB.start();
		
		//Garante que a thread  main (principal) vai rodar até o fim.
		//Para este caso especifico, existem 3 threads, a thread principal (main), uma thread para popular a matrizA e outra thread para popular a matrizB
		try {
			threadPopulaMatrizA.join();						
			threadPopulaMatrizB.join();
			
			//Mostra uma barra de progresso enquanto as threads populam as matrizes
			BarraProgresso barra1 = new BarraProgresso();
			System.out.println(Thread.currentThread() + " populando as matrizes...");
			synchronized (barra1) {							
				barra1.mostraBarraDeProgressoEmTexto();
			}
			
			matrizA = tarefaPopulaMatrizA.getMatrizPopulada(); 
			matrizB = tarefaPopulaMatrizB.getMatrizPopulada();  
			
			//Só faz o cálculo do produto entre as matrizes A e B quando os processos de população das matrizes for concluído
			if (!threadPopulaMatrizA.isAlive() && !threadPopulaMatrizB.isAlive()) {
				System.out.println(" A thread " + threadPopulaMatrizA.getName() + " terminou.");
				System.out.println(" A thread " + threadPopulaMatrizB.getName() + " terminou.");
				System.out.println("Calculando o produto entre as matrizes A e B...");
				matrizC = produtoDuasMatrizes.calculaProduto(matrizA, matrizB);
				System.out.println("Matriz C (Matriz Produto): ");
				produtoDuasMatrizes.mostraConteudoDaMatriz(matrizC);
			}
			System.out.println("Thread : " + Thread.currentThread() + " terminou!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
								
	}

}