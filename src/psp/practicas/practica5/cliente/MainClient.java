package psp.practicas.practica5.cliente;

import java.io.IOException;
import java.util.ArrayList;


public class MainClient {

	/*
	 * Pre:
	 * Post: Metodo el cual inicia un cliente
	 */
	public static void main(String[] args) {
		try {
			Client c = new Client();
			c.startClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
