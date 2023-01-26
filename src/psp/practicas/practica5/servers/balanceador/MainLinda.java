package psp.practicas.practica5.servers.balanceador;

public class MainLinda {

	
	public static void main(String[] args) {
		Linda l = new Linda();
		l.start();
		CopySystem csys = new CopySystem();
		csys.setLinda(l);
		csys.start();
	}
}
