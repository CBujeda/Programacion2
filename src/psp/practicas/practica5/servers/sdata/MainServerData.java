package psp.practicas.practica5.servers.sdata;

/*
 * Clase inicializadora del servidor de datos
 */
public class MainServerData {

	/*
	 * Pre:
	 * Post: Metodo main el cual inicia el servidor de datos
	 * 
	 * 	CODS:
	 * 		1		--> Server 1
	 * 		12		--> Server 1 - Replica
	 * 		2		--> Server 2
	 * 		3		--> Server 3
	 */
	public static void main(String[] args) {
		String dt = "1";
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
