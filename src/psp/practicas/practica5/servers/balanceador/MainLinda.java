package psp.practicas.practica5.servers.balanceador;

public class MainLinda {

	/*
	 * Pre:
	 * Post: Metodo el cual inicia el servidor linda y el sistema de copiado
	 */
	public static void main(String[] args) {
		Linda l = new Linda();	// iniciamos linda
		l.start();
		CopySystem csys = new CopySystem();	// Iniciamos el sistema de copia
		csys.setLinda(l);					// Establecemos el servidor linda que el sistema de copiado usara
		csys.start();
	}
}
