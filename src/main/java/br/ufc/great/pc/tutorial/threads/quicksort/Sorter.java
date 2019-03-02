package br.ufc.great.pc.tutorial.threads.quicksort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Sorter implements Runnable {
	  private Double[] values;
	  private ArrayComponent panel;

	  public Sorter(Double[] values, ArrayComponent panel) {
	    this.values = values;
	    this.panel = panel;
	  }

	  public void run() {
	  Comparator<Double> comp = new Comparator<Double>() {
		  public int compare(Double d1, Double d2) {
			  try {
				  Thread.sleep(100);
			  } catch (Exception exception) {
				  System.out.println(exception);
			  }
			  panel.setValues(values, d1, d2);
			  return d1.compareTo(d2);
		  }
	  };
	    
	    Collections.sort(Arrays.asList(values), comp);
	    panel.setValues(values, null, null);
	  }
	}
