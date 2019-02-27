package br.ufc.great.pc.tutorial.threads.utils;

public class BarraProgresso {
	
	public synchronized void mostraBarraDeProgressoSimplificada(float total){				
		
		for(float i=1; i < total+1; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}					
					
			float x = (i/total) * 100;			
			String numero = String.format("%.2f", x); 
			
			if (x < 100) {
				System.out.print(numero + "% \r");
			}else {
				System.out.print("\r"+numero + "%");
			}
		}
		System.out.println("\n");		
	}
	
	public synchronized void mostraBarraDeProgressoEmTexto() {
		char[] animationChars = new char[]{'|', '/', '-', '\\'};

        for (int i = 0; i <= 100; i++) {
            System.out.print("Processing: " + i + "% " + animationChars[i % 4] + "\r");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done!             ");

	}
	
}