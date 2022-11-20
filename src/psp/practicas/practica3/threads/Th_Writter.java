package psp.practicas.practica3.threads;

import psp.practicas.practica3.Datas;
import psp.practicas.practica3.Semaforo;
import psp.practicas.practica3.colario.Cola;
import psp.practicas.practica3.colario.Node;
/*
 * Objeto thread el cual escribe en la cola.
 */
public class Th_Writter  extends Thread  {

	private Cola c;
	private int id;
	private Semaforo mutex;
	private Semaforo existData;
	private Semaforo existSize;
	
	public Th_Writter(Datas data,int id) {	//construct data
		this.id = id;
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
		System.out.println("Inicio Writter: " + this.id);
		for(int i = 0; i < 8; i++) {
			try {
				this.existSize.acquire();	//Get ticket
				this.mutex.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			c.push(new Node("Id Thread (w):" + this.id + " Num: " + i));
			System.out.println("Writer: " + "Id Thred (w): " + this.id + " Num: " + i);
			this.mutex.release();			//Return Ticket
			this.existData.release();
		}
	}   
}
