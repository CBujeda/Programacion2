package psp.practicas.practica3.threads;

import psp.practicas.practica3.Semaforo;
import psp.practicas.practica3.colario.Cola;
import psp.practicas.practica3.colario.Node;

public class Th_Writter  extends Thread  {

	private Cola c;
	private Semaforo sWritter;
	private Semaforo sMutex;
	private int id;
	
	public Th_Writter(Cola c) {
		this.c = c;
	}
	public Th_Writter(Cola c, Semaforo sWritter, Semaforo sMutex,int id) {
		this.c = c;
		this.sWritter = sWritter;
		this.sMutex = sMutex;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 8 ; i++) {
			c.push(new Node("Hola " + this.id));
		}
		
		
	}   
}
