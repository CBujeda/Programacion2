package psp.practicas.practica5;

import java.util.Scanner;

import psp.practicas.practica5.cliente.MainClient;
import psp.practicas.practica5.servers.balanceador.MainLinda;
import psp.practicas.practica5.servers.sdata.MainServerData;

/*
 * Clase de inicio de la aplicación
 */
public class Main {
	/*
	 * Pre: 
	 * Post: Metodo con el cual podemos indicar el servidor a iniciar
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String dt = "";
			if (args.length > 0) {
				dt = args[0]; // obtenemos argumentos
			} else {
				System.out.println("Cliente 		 = c 	\n" + "Server Linda 	         = sl	\n"
						+ "Server datos 1   	 = sd1	\n" + "Server datos 2 		 = sd2	\n"
						+ "Server datos 3 		 = sd3	\n" + "Server datos copia 1     = sd12	\n");
				System.out.print("Iniciar: ");
				dt = sc.nextLine();
			}
			if (dt.equalsIgnoreCase("c")) {
				MainClient.main(args);
			} else if (dt.equalsIgnoreCase("sl")) {
				MainLinda.main(args);
			} else if (dt.equalsIgnoreCase("sd1")) {
				String[] table = { "1" };
				MainServerData.main(table);
			} else if (dt.equalsIgnoreCase("sd2")) {
				String[] table = { "2" };
				MainServerData.main(table);
			} else if (dt.equalsIgnoreCase("sd3")) {
				String[] table = { "3" };
				MainServerData.main(table);
			} else if (dt.equalsIgnoreCase("sd12")) {
				String[] table = { "12" };
				MainServerData.main(table);
			}
		}
	}
}
