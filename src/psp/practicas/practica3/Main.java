package psp.practicas.practica3;

import psp.practicas.practica3.colario.Cola;
import psp.practicas.practica3.colario.Node;

public class Main {

	public static void main(String[] arg){
		System.out.println("==========SEMAFOROS==========");
		Cola c  = new Cola();
		c.push(new Node("Hola"));
		c.push(new Node("Adios"));
		c.show();
		c.pop();
		c.show();
		
	}
}
