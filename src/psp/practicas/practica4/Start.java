package psp.practicas.practica4;

import java.io.IOException;
import java.util.Scanner;

import psp.practicas.practica4.client.MainClient;
import psp.practicas.practica4.server.MainServer;

public class Start {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("Iniciar: ");
			String dt = sc.nextLine();
			if(dt.equalsIgnoreCase("s")) {
				MainServer.main(args);
				break;
			}else if(dt.equalsIgnoreCase("c")) {
				MainClient.main(args);
				break;
			}else if(dt.equalsIgnoreCase("exit")){
				
			}else{
				System.out.println("Comando invalido");
			}
		}
	}
}
