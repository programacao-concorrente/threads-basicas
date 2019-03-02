package br.ufc.great.pc.tutorial.threads.quicksort;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

class ArrayComponent extends JComponent {
	private Double marked1;
	private Double marked2;
	private Double[] values;

	/**
	 * Método que será concorrido com a ordenação
	 */
	public synchronized void paintComponent(Graphics g) {
		if (values == null)
			return;

		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth() / values.length;

		for (int i = 0; i < values.length; i++) {
			Double v = values[i];
			Rectangle2D bar = new Rectangle2D.Double(width * i, 0, width, v);
			if (v == marked1 || v == marked2)
				g2.fill(bar);
			else
				g2.draw(bar);
		}
	}

	public synchronized void setValues(Double[] values, Double marked1, Double marked2) {
		this.values = (Double[]) values.clone();
		this.marked1 = marked1;
		this.marked2 = marked2;
		repaint();
	}

}

