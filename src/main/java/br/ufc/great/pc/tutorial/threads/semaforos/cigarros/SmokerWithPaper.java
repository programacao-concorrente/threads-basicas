package br.ufc.great.pc.tutorial.threads.semaforos.cigarros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

public class SmokerWithPaper extends Smoker implements Runnable {

	public SmokerWithPaper(Semaphore tobaccoSem, Semaphore paperSem, Semaphore matchSem, Semaphore agentSemaphore) {
		super(tobaccoSem, paperSem, matchSem, agentSemaphore);
	}

	@Override
	public void makeCigarette() {
		if (Main.control) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Smoker with paper making cigarette "+dateFormat.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("SmokerWithPaper fumando...");
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while (Main.control) {
			paperSem.waits();
			makeCigarette();
			System.out.println("Weakup Agent...");
			Agent.mutex2.signals();
			agentSemaphore.signals();
		}
	}

}
