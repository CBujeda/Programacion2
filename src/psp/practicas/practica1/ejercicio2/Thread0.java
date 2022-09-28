package psp.practicas.practica1.ejercicio2;

/**
 * Objeto Thread el cual al cargar muestra (Soy {mensaje})
 * en un tiempo y veces asignados
 */
public class Thread0 extends Thread {
	private String mensaje;
	private int retardo;
	private int veces;
	
	public Thread0(String mensaje, int retardo, int veces) {
		this.mensaje = mensaje;
		this.retardo = retardo;
		this.veces = veces;
	}
	
	/**
	 * Pre: ---
	 * Post: Metodo el cual se ejecuta al iniciar
	 * 		 el thread
	 */
	public void run() {
		for(int i = 1; i <= veces; i++) {
			System.out.println(mensaje);
			try {
				Thread.sleep(retardo); // Espera {milisegundos}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}