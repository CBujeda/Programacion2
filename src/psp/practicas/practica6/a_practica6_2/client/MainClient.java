package psp.practicas.practica6.a_practica6_2.client;

import java.io.IOException;


public class MainClient {

	/*
	 * Pre:
	 * Post: Metodo con el cual creamos un nuevo cliente y lo iniciamos
	 */
	public static void main(String[] args) {
		Client c;
		try {
			c = new Client();
			c.startClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
