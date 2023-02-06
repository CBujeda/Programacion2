package psp.practicas.practica5.servers.balanceador;

public class MainLinda {

	/*
	 * Pre:
	 * Post: Metodo el cual inicia el servidor linda y el sistema de copiado
	 */
	public static void main(String[] args) {
		Linda l = new Linda();
		l.start();
		CopySystem csys = new CopySystem();
		csys.setLinda(l);
		csys.start();
	}
}
