package br.ufc.great.pc.tutorial.threads.tarefas.arquivos;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufc.great.pc.tutorial.threads.recurso.arquivos.BufferFile;

public class TarefaProcuraPadrao implements Runnable{
	private String name;
	private String pattern;
	private BufferFile myBufferFile;
	
	public TarefaProcuraPadrao(String pattern, BufferFile myBufferFile, String name) {
		this.pattern = pattern;
		this.myBufferFile = myBufferFile;
		this.name = name;
	}
	
	/**
	 * check if a word is in full string
	 * @param fullString complete string to search
	 * @param partWord part of word it will be searched
	 * @return true if patter is match
	 */
	private boolean isContainExactWord(String fullString, String partWord){
	    String pattern = "\\b"+partWord+"\\b";
	    
	    Pattern p=Pattern.compile(pattern);
	    Matcher m=p.matcher(fullString);
	    
	    return m.find();
	}
	
	/**
	 * Check if patter is in line of text file
	 * @param pattern pattern to search
	 * @param line line of text file
	 * @return true if patter is match
	 */
	private synchronized void findPatternInBuffer(String pattern) {
		String line1 = null;
		try {
			while (myBufferFile.getBuffer() != null) {
				line1 = myBufferFile.getBuffer().readLine();
				
				if (line1 != null) {
					if (isContainExactWord(line1, pattern)) {
						System.out.println(line1);
					}					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Procura padrão");
		findPatternInBuffer(pattern);
	}

}