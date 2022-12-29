package psp.practicas.practica4.server;

import java.io.IOException;

public class MainServer {
	
	public static void main(String[] args) {
		System.out.println("Iniciando Servidor");
		try {
			Server s = new Server();
			s.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
