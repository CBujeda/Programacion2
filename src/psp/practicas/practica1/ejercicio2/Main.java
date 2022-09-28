package psp.practicas.practica1.ejercicio2;

public class Main {
	
	public static void main(String[] arg) {
		
		Thread0[] t = new Thread0[10];
		for(int i = 0; i < t.length ; i++) {
			t[i] = new Thread0("Soy " + (i+1),getRandomInt(300,100),getRandomInt(15,5));
		}
		for(int i = 0; i < t.length;i++) { t[i].start(); } // inicamos thread
		for(int i = 0; i < t.length;i++) {
			try { t[i].join(); // dejamos main en espera
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Fin");
	}
	
	public static int getRandomInt(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

}
