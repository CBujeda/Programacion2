package psp.practicas.practica4.client;

import java.io.IOException;

public class MainClient {

	public static void main(String[] args) {
		System.out.println("Iniciando Cliente");
		try {
			Client c = new Client();
			c.startClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
