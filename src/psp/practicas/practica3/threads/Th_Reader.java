package psp.practicas.practica3.threads;

import psp.practicas.practica3.Datas;
import psp.practicas.practica3.Semaforo;
import psp.practicas.practica3.colario.Cola;
import psp.practicas.practica3.colario.Node;
/*
 * Objeto thread el cual lee datos de la cola
 */
public class Th_Reader  extends Thread  {

	private Cola c;
	private Semaforo mutex;
	private Semaforo existData;
	private Semaforo existSize;
	public Th_Reader(Datas data) {	// Construct the data
		this.c = data.getC();
		this.existData = data.getExistData();
		this.existSize = data.getExistSize();
		this.mutex = data.getMutex();
	}

	
	/*
	 * RUN
	 */
	@Override
	public void run() {
		System.out.println("Inicio Reader: ?");
		for(int i = 0; i < 6; i++) {
			try {
				this.existData.acquire();	//get ticket
				this.mutex.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Node n = c.pop();
			System.out.println("Reader: " + n.getTexto());
			this.mutex.release();			//return ticket
			this.existSize.release();
		}
	}
	
}
