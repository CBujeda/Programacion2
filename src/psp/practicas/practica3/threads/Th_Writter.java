package psp.practicas.practica3.threads;

import psp.practicas.practica3.Semaforo;
import psp.practicas.practica3.colario.Cola;
import psp.practicas.practica3.colario.Node;

public class Th_Writter  extends Thread  {

	private Cola c;

	private int id;
	
	public Th_Writter(Cola c) {
		this.c = c;
	}

	
	@Override
	public void run() {
		System.out.println("Inicio Writter: " + this.id);
		for(int i = 0; i < 8 ; i++) {
			c.push(new Node("Id Thred: " + this.id + " Num: " + i));
			System.out.println("Writter: " + "Id Thred: " + this.id + " Num: " + i);
		}
		
		
	}   
}
