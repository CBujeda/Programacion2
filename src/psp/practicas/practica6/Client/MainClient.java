package psp.practicas.practica6.Client;

import java.io.IOException;

public class MainClient {

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
