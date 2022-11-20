package psp.practicas.practica3;
import java.util.concurrent.Semaphore;

/*
 * Objeto el cual actua como semáforo
 */
public class Semaforo extends Semaphore {

	private static final long serialVersionUID = 3074389134310216331L;

	public Semaforo(int permits) {
		super(permits);
	}
	
	public Semaforo(int permits, boolean fair) {
		super(permits, fair);
	}

}
