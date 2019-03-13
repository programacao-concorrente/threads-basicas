package br.ufc.great.pc.tutorial.threads.arquivos;

import java.io.File;

import br.ufc.great.pc.tutorial.threads.recurso.arquivos.BufferFile;
import br.ufc.great.pc.tutorial.threads.tarefas.arquivos.TarefaLerLinha;
import br.ufc.great.pc.tutorial.threads.tarefas.arquivos.TarefaProcuraPadrao;

public class FindingPatternsInFileConcurrent {
	static String workDirectory = System.getProperty("user.dir");

	public static void showFileDetails(String workDirectory, File file) {
		//Mostra os detalhes do arquivo testado
		System.out.println("O diretório corrente é: " + workDirectory);		
		System.out.println("Caminho absoluto do arquivo: " + file.getName() + " é: " + file.getAbsolutePath());
		System.out.println("O tamanho do arquivo " + file.getName() + " é: " + file.length() );		
	}

	public static void main(String[] args) {
		//Resource that will be shared
		BufferFile bufferFile = new BufferFile();
		String pattern = "jar";
		
		//Inicializa o bufferFile
		bufferFile.init();
		//Mostra detalhes do arquivo do bufferFile
		showFileDetails(workDirectory, bufferFile.getFile());
		
		TarefaLerLinha tarefaLerLinha = new TarefaLerLinha(bufferFile, "Tarefa ler linha");
		Thread threadLerLinha = new Thread(tarefaLerLinha);
		
		TarefaProcuraPadrao tarefaProcuraPadrao = new TarefaProcuraPadrao(pattern, bufferFile, "Tarefa procura padrão");
		Thread threadProcuraPadrao = new Thread(tarefaProcuraPadrao);
		
		threadLerLinha.start();
		threadProcuraPadrao.start();
		
	}
}
