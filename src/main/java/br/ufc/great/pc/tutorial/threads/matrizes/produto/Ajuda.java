package br.ufc.great.pc.tutorial.threads.matrizes.produto;

public class Ajuda {
	public void mostra() {
		System.out.println("------ Ajuda do Multiplica-Matrizes com Threads --------");
		System.out.println("1. Executando o jar");
		System.out.println("$java -jar nome-projeto.jar");
		System.out.println("");
		System.out.println("2. Opções extras");
		System.out.println("a) Definindo o tamanho da matriz");
		System.out.println("-s Define o tamanho da matriz lxc");
		System.out.println("Exemplo:");
		System.out.println("matriz-produto.jar -s 3"); 
		System.out.println("Define uma matriz 3x3");
		System.out.println("b) Mostra o help do programa");
		System.out.println("-h mostra o arquivo de ajuda");
		System.out.println("");
		System.out.println("Obs: ");
		System.out.println("Caso não seja passado nenhum parâmetro, o tamanho da matriz será 100x100");
		System.out.println("e ela será preenchida com valores inteiros randomicos entre 1 e 100");
		System.out.println("");
		System.out.println("Dúvidas ou sugestões entrar em contato com armando@ufpi.edu.br");
	}
}
