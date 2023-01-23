package psp.practicas.practica5.servers.balanceador;

public class MainLinda {

	
	public static void main(String[] args) {
		Linda l = new Linda();
		l.startServer();
		CopySystem csys = new CopySystem();
		csys.start();
	}
}
