package br.ufc.great.pc.tutorial.threads.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import br.ufc.great.pc.tutorial.threads.utils.ManipulaArquivos;

public class SearchPatternInFileInSequence {
	static String workDirectory = System.getProperty("user.dir");
	static String fileToTest = "teste.txt"; 
	static ManipulaArquivos fileManipulator = new ManipulaArquivos();
	static BufferedReader buffer;
	static FileReader fileReader;
	static File file = null;	
	static boolean fileExiste = false;
	static long fileLength = 0;			
	
	public static void init() {
		//Inicializa as variáveis e objetos necessários para manipular o arquivo que será testado.			
		try {
			file = new File(fileToTest);
			fileExiste = file.exists();
			fileLength = file.length();
			fileReader = new FileReader(file);
			buffer = new BufferedReader(fileReader);
			
			//Inicializa o fileManipulator
			fileManipulator.setBuffer(buffer);
			fileManipulator.setFileExiste(fileExiste);
			fileManipulator.setFileLength(fileLength);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void showFileDetails(String workDirectory, File file) {
		//Mostra os detalhes do arquivo testado
		System.out.println("O diretório corrente é: " + workDirectory);		
		System.out.println("Caminho absoluto do arquivo: " + file.getName() + " é: " + file.getAbsolutePath());
		System.out.println("O tamanho do arquivo " + file.getName() + " é: " + file.length() );		
	}
	
	public static void main(String[] args) {		
		System.out.println("Procura um padrão em um arquivo");
		
		try {
			init();
			showFileDetails(workDirectory, file);
			//Faz a busca da palavra jar em cada linha do arquivo
			fileManipulator.findPatternInFile("jar");
		}
		catch (Exception e) {
			System.out.println("Erro ao manipular arquivo.");
		}
		
	}
}