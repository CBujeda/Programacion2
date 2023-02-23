package psp.practicas.practica6;

import psp.practicas.practica6.a_practica6_2.client.MainClient;
import psp.practicas.practica6.a_practica6_2.server.MainServer;
public class Main {

	public static void main(String[] args) {
		if(args.length > 0) {
			String opt = args[0];
			if(opt.equalsIgnoreCase("c")) {
				MainClient.main(args);
			}else if(opt.equalsIgnoreCase("s")) {
				MainServer.main(args);
			}
			
		}
		
	}
}
