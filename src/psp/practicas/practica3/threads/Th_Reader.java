package psp.practicas.practica3.threads;

import psp.practicas.practica3.Semaforo;
import psp.practicas.practica3.colario.Cola;

public class Th_Reader  extends Thread  {

	private Cola c;
	private Semaforo sReader;
	private Semaforo sMutex;
	
	public Th_Reader(Cola c) {
		this.c = c;
	}
	
	public Th_Reader(Cola c, Semaforo sReader, Semaforo sMutex) {
		this.c = c;
		this.sReader = sReader;
		this.sMutex = sMutex;
	}
	
	
	@Override
	public void run() {
		
	}
	
}
