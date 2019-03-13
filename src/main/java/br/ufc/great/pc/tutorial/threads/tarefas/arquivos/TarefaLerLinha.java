package br.ufc.great.pc.tutorial.threads.tarefas.arquivos;

import java.io.IOException;
import java.nio.channels.Pipe.SinkChannel;

import br.ufc.great.pc.tutorial.threads.recurso.arquivos.BufferFile;

public class TarefaLerLinha implements Runnable{
	private String name;
	private BufferFile myBufferFile;
	
	public TarefaLerLinha(BufferFile myBufferFile, String name) {
		this.myBufferFile = myBufferFile;
		this.name = name;
	}

	/**
	 * Search a string pattern in text line, if pattern is in line then print line
	 * @param pattern string to search in line
	 */
	public synchronized void readLineInFile() {
		int lineNumber = 1;
		try {
			//faz a leitura de um arquivo texto 
			String line;
			if((line = myBufferFile.getBuffer().readLine()) != null) {
				//percorre o arquivo linha a linha
				System.out.println("percorrendo o arquivo na linha " + lineNumber + "...");
				lineNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Ler linha");
		readLineInFile();
	}

}
