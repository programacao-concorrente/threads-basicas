package br.ufc.great.pc.tutorial.threads.utils;

public class GeraInteiroRandomico {
	public static int geraInteiroRandomicoNoIntervalo(int minimo, int maximo) {
		int numero;
		
		numero = minimo + (int)(Math.random()* maximo);
				
		return numero; 
	}
}
