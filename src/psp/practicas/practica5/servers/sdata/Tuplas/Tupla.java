package psp.practicas.practica5.servers.sdata.Tuplas;

/*
 * Objeto la cual almacena los datos
 */
public class Tupla {

	// Almacen de Strings
	private String[] data;
	
	public  Tupla(String[] data) {
		this.data = data;
	}

	/*
	 * Metodo con el cual obtenemos los datos por thread
	 */
	public synchronized String[] getData() {
		return data;
	}
	
	/*
	 * Pre:
	 * Post: Metodo con el cual inertamos los datos desde un thread por vez
	 */
	public synchronized void setData(String[] data) {
		this.data = data;
	}
	
}
