package br.ufc.great.pc.tutorial.threads.recurso.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class BufferFile {
	private String nameFileToTest = "teste.txt"; 
	private File file = null;
	private BufferedReader buffer;
	private FileReader fileReader;
		
	public String getFileToTest() {
		return nameFileToTest;
	}

	public void setFileToTest(String fileToTest) {
		this.nameFileToTest = fileToTest;
	}

	public BufferedReader getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedReader buffer) {
		this.buffer = buffer;
	}

	public FileReader getFileReader() {
		return fileReader;
	}

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void init() {
		//Inicializa as variáveis e objetos necessários para manipular o arquivo que será testado.			
		try {
			file = new File(nameFileToTest);
			fileReader = new FileReader(file);
			buffer = new BufferedReader(fileReader);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
