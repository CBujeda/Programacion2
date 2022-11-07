package psp.practicas.practica3;

import psp.practicas.practica3.colario.Cola;

public class Datas {
	
	private Semaforo mutex;
	private Semaforo existData;
	private Semaforo existSize;
	private Cola c;
	public Datas(int size) {
		super();
		this.mutex = new Semaforo(1,true);
		this.existData = new Semaforo (0,true);
		this.existSize = new Semaforo(size,true);
		this.c = new Cola(size);
	}
	public Semaforo getMutex() {
		return mutex;
	}
	public void setMutex(Semaforo mutex) {
		this.mutex = mutex;
	}
	public Semaforo getExistData() {
		return existData;
	}
	public void setExistData(Semaforo existData) {
		this.existData = existData;
	}
	public Semaforo getExistSize() {
		return existSize;
	}
	public void setExistSize(Semaforo existSize) {
		this.existSize = existSize;
	}
	public Cola getC() {
		return c;
	}
	public void setC(Cola c) {
		this.c = c;
	}
	

}
