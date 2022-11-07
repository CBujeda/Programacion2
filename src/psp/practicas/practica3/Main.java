package psp.practicas.practica3;

import psp.practicas.practica3.colario.Cola;
import psp.practicas.practica3.threads.Th_Reader;
import psp.practicas.practica3.threads.Th_Writter;

public class Main {

	/**
	 * Pre:
	 * Post: ES - 
	 * 		 EN -
	 */
	public static void main(String[] arg){
		System.out.println("==========SEMAFOROS==========");
		Cola c  = new Cola(2);
		Th_Writter[] tw = new Th_Writter[4];
		Th_Reader[] tr = new Th_Reader[5];
		Semaforo sWritter = new Semaforo(8);
		Semaforo sReader = new Semaforo(6);
		Semaforo sMutex = new Semaforo(1);
		
		
		for(int i = 0; i < tw.length; i++) {	//Add writer thread to array
			tw[i] = new Th_Writter(c,sWritter,sMutex,(i+1));
		}
		for(int i = 0; i < tr.length; i++) {	//Add reader thread to array
			tr[i] = new Th_Reader(c,sReader,sMutex);
		}
		for(int i = 0; i < tr.length; i++) {	//Add reader thread to array
			tr[i].start();
		}
		for(int i = 0; i < tr.length; i++) {	//Add reader thread to array
			try {
				tr[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		c.show();
	}
}
