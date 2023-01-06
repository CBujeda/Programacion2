package psp.practicas.practica4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import psp.practicas.practica4.client.MainClient;
import psp.practicas.practica4.server.MainServer;

public class Start {

	/**
	 * Pre:
	 * Post: Metodo de inicio con opciones
	 * 
	 * Si se ejecuta con parametros EX:
	 * 		1| 	java -jar aeropuerto.jar s		// Ejecuta servidor
	 *  	2| 	java -jar aeropuerto.jar c		// Ejecuta cliente
	 */
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		//List<String> list = Arrays.asList(args);		// Obtenemos argumentos (Opcional)       
		while(true) {
			String dt = "";
			if(args.length > 0) {
				dt = args[0];	// obtenemos argumentos
			}else {
				System.out.print("Iniciar: ");
				dt = sc.nextLine();
			}
			if(dt.equalsIgnoreCase("s")) {
				MainServer.main(args);
				break;
			}else if(dt.equalsIgnoreCase("c")) {
				MainClient.main(args);
				break;
			}else if(dt.equalsIgnoreCase("exit")){
				break;
			}else{
				System.out.println("Comando invalido");
			}
		}
	}
}
