package br.ufc.great.pc.tutorial.threads.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class ManipulaArquivos {
	boolean fileExiste;
	long fileLength;
	BufferedReader buffer;
	
	public boolean isFileExiste() {
		return fileExiste;
	}

	public void setFileExiste(boolean fileExiste) {
		this.fileExiste = fileExiste;
	}

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public BufferedReader getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedReader buffer) {
		this.buffer = buffer;
	}

	/**
	 * List all files and directories in current directory
	 * @param workDirectory current directory
	 */
	public void listFilesInCurrentDirectory(String workDirectory) {
		File dir = new File(workDirectory);

	    // Create a file filter to exclude any .SYS file
	    FileFilter filter = file -> {
	      if (file.isFile()) {
	        String fileName = file.getName().toLowerCase();
	        if (fileName.endsWith(".sys")) {
	          return false;
	        }
	      }
	      return true;
	    };

	    File[] list = dir.listFiles(filter);

	    for (File f : list) {
	      if (f.isFile()) {
	        System.out.println(f.getPath() + "  (File)");
	      } else if (f.isDirectory()) {
	        System.out.println(f.getPath() + "  (Directory)");
	      }
	    }
		
	}

	/**
	 * Read line by line of Text File
	 * @param fileExiste check if file exists
	 * @param fileLength calculate the size of files in bytes
	 * @param buffer bufferStream to record the file
	 * @return int the Length of lines in Text File
	 */
	public int readTextFileLineByLine() {
		int lengthLines = 0;
		
		try {
			//faz a leitura de um arquivo texto 
			if (fileExiste) {
				if (fileLength > 0) {
					String line;
					while((line = buffer.readLine()) != null) {
						System.out.println(line);
						lengthLines++;
					}
				}else {
					System.out.println("O arquivo está vazio!");
				}
			}else {
				System.out.println("O arquivo não existe!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lengthLines;
	}

	/**
	 * Read line by line of Text File
	 * @param fileExiste check if file exists
	 * @param fileLength calculate the size of files in bytes
	 * @param buffer bufferStream to record the file
	 */
	public void readTextFileLineByLineInSequence() {
		try {
			//faz a leitura de um arquivo texto 
			if (fileExiste) {
				if (fileLength > 0) {
					String line;
					while((line = buffer.readLine()) != null) {
						System.out.println(line);
					}
				}else {
					System.out.println("O arquivo está vazio!");
				}
			}else {
				System.out.println("O arquivo não existe!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Search a string pattern in text line, if pattern is in line then print line
	 * @param pattern string to search in line
	 */
	public void findPatternInFile(String pattern) {
		try {
			//faz a leitura de um arquivo texto 
			if (fileExiste) {
				if (fileLength > 0) {
					String line;
					while((line = buffer.readLine()) != null) {
						if (findPatternInLine(pattern, line)) {
							System.out.println(line);
						}
					}
				}else {
					System.out.println("O arquivo está vazio!");
				}
			}else {
				System.out.println("O arquivo não existe!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if patter is in line of text file
	 * @param pattern pattern to search
	 * @param line line of text file
	 * @return true if patter is match
	 */
	private boolean findPatternInLine(String pattern, String line) {
		boolean checkPattern=false;
		
		if (line.indexOf(pattern) >= 0) {
			checkPattern = true;
		}
		return checkPattern;
	}
	
	
}