package br.ufc.great.pc.tutorial.threads.quicksort;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class AnimationTester {
	  public static void main(String[] args) {
	    //Cria um Frame para acomodar os componentes
		JFrame frame = new JFrame();
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    ArrayComponent panel = new ArrayComponent();
	    frame.add(panel, BorderLayout.CENTER);

	    frame.setSize(800, 300);
	    frame.setVisible(true);

	    Double[] values = new Double[100];
	    for (int i = 0; i < values.length; i++)
	      values[i] = Math.random() * panel.getHeight();

	    final Sorter sorter = new Sorter(values, panel);

	    Thread sorterThread = new Thread(sorter);
	    sorterThread.start();
	  }
	}

