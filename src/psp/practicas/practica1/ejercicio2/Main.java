package psp.practicas.practica1.ejercicio2;
/**
 * Programa java el cual crea 10 threads los cuales muestran
 * (Yo soy {num del thread}) y tienen un tiempo de espera y
 * repeticiones aleatorios con un valor entre dos numeros
 * @author Clara Bujeda Muñoz
 */
public class Main {
	
	/**
	 * Pre:
	 * Post: Metodo principal el cual crea e inica diferentes threads
	 */
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
	
	/**
	 * Pre:
	 * Post: Metodo el cual genera un valor random entre do
	 */
	public static int getRandomInt(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

}
