package psp.practicas.practica5.servers.sdata.Tuplas;

/*
 * Clase la cual almacenar los datos
 */
public class Tupla {
	
	// En futuro cambiar syncroniced por semaphores

	private String[] data;
	
	public  Tupla(String[] data) {
		this.data = data;
	}

	public synchronized String[] getData() {
		return data;
	}

	public synchronized void setData(String[] data) {
		this.data = data;
	}
	
}
