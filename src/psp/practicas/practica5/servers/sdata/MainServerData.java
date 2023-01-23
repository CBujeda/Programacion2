package psp.practicas.practica5.servers.sdata;

public class MainServerData {

	public static void main(String[] args) {
		String dt = "-1";
		if(args.length > 0) {
			dt = args[0];	// obtenemos argumentos
		}
		int dta = -1;
		try {
		  dta = Integer.parseInt(dt);
		} catch(Exception e){
			dta = -1;
			System.out.println("Error al parsear datos \n"+ e);
		}
		ServerData sd = new ServerData(dta);
		sd.startSD();
	}
}
