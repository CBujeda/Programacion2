package psp.practicas.practica3.threads;

import psp.practicas.practica3.Semaforo;
import psp.practicas.practica3.colario.Cola;
import psp.practicas.practica3.colario.Node;

public class Th_Reader  extends Thread  {

	private Cola c;

	
	public Th_Reader(Cola c) {
		this.c = c;
	}

	
	
	@Override
	public void run() {

		System.out.println("Inicio Writter: ?");
		for(int i = 0; i < 6; i++) {
			Node n = c.pop();
			System.out.println("Reader: " + n.getTexto());
		}
	}
	
}
