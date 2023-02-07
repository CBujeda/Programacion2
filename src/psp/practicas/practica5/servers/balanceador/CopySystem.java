package psp.practicas.practica5.servers.balanceador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import psp.practicas.practica5.Config;

/*
 * Objeto thread de copiado de servidores
 * Metodo de funcionamiento:
 * 	Ejecutara una peticion a servidor de datos 1 y 2 esperando entre cada
 * 	ejecucion un tiempo establecido, en caso de detectar uno de dichos servidores
 * 	caidos lo detectara como caido, en caso de que dicho servidor vuelva a iniciarse y
 * 	se detecte como caido, se borraran todos los datos de este ya que puede existir una
 * 	mala transferencia, se pausaran todas las peticiones ejercidas por los clientes y por
 * 	ultima instancia se obtendran los datos del servidor no caido y se insertaran en el 
 *  servidor que se reinicio.
 */
public class CopySystem extends Thread implements Config {

	private Linda l;
	private boolean caidoS1;
	private boolean caidoS2;

	// private ServerSocket ss;
	// protected Socket cs;

	public CopySystem() {
		super();
	}
	/*
	 * Pre:
	 * Psot: Metodo constructor
	 */
	public void setLinda(Linda l) {
		caidoS1 = false;
		caidoS2 = false;
		this.l = l;
	}

	/*
	 * Pre:
	 * Post: Metodo de inicio el cual se encarga de verificar y copiar
	 * 		 en caso que uno de los servidores haya caido
	 */
	@Override
	public void run() {
		super.run();
		while (true) {
			String very = clientSDActuator(Config.portSD1, "verify", "v");
			if (very.equalsIgnoreCase("EC")) {
				this.caidoS1 = true;
			} else if (this.caidoS1 == true) {
				// Iniciar copia S2 -> S1
				//System.out.println("S2 -> S1");
				if (this.caidoS2 == true) {
					System.out.println("{ERROR} - > Server 1 y dos se cayeron por lo que no se comprende la copia");	// En caso de dos servers caidos
					caidoS1 = false;
					caidoS2 = false;
				} else {
					copy("s2>s1");
				}

			}
			//System.out.println("S1:   ->" + very);
			String very2 = clientSDActuator(Config.portSD12, "verify", "v");
			if (very2.equalsIgnoreCase("EC")) {
				this.caidoS2 = true;
			} else if (this.caidoS2 == true) {
				// Iniciar copia S1 -> S2
				//System.out.println("S1 -> S2");
				if (this.caidoS1 == true) {
					System.out.println("{ERROR} - > Server 1 y dos se cayeron por lo que no se comprende la copia");	// En caso de dos servers caidos
					caidoS1 = false;
					caidoS2 = false;
				} else {
					copy("s1>s2");
				}

			}
			//System.out.println("S1.2: ->" + very2);
			try {
				Thread.sleep(2000);	// Tiempo determinado 2s
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * Pre:
	 * Post: Metodo el cual ejecuta el sistema de copiado entre servidores
	 * 		 Es necesario indicar la direccion de copia
	 */
	public void copy(String direccion) {
		ArrayList<Connect> lc = l.getConexions();
		for (int i = 0; i < lc.size(); i++) {
			lc.get(i).setPause(true);
		}
		if (direccion.equalsIgnoreCase("s2>s1")) {
			String dta = clientSDActuator(Config.portSD12, "getAllData", "gad");
			//System.out.println(dta);
			clientSDActuator(Config.portSD1, "deleteAll", "da");
			String[] dtaArr = dta.split("/");
			for (int p = 0; p < dtaArr.length; p++) {
				//System.out.println(dtaArr[p]);
				clientSDActuator(Config.portSD1, "PN", dtaArr[p]);
			}
			this.caidoS1 = false;
		} else if (direccion.equalsIgnoreCase("s1>s2")) {
			String dta = clientSDActuator(Config.portSD1, "getAllData", "gad");
			//System.out.println(dta);
			clientSDActuator(Config.portSD12, "deleteAll", "da");
			String[] dtaArr = dta.split("/");
			for (int p = 0; p < dtaArr.length; p++) {
				//System.out.println(dtaArr[p]);
				clientSDActuator(Config.portSD12, "PN", dtaArr[p]);
			}
			this.caidoS2 = false;
		}
		for (int i = 0; i < lc.size(); i++) {
			lc.get(i).setPause(false);
		}
	}

	/*
	 * Pre:
	 * Post: Metodo el cual envia una cadena al servidor
	 */
	public String clientSDActuator(int port, String command) {
		return clientSDActuator(port, command, "");
	}
	public String clientSDActuator(int port, String command, String data) {
		// String command = "verify";
		String result = "";
		try {
			Socket cs = new Socket(Config.hostServers, port);
			// Canal para recibir mensajes (entrada)
			DataInputStream in = new DataInputStream(cs.getInputStream());
			// Canal para enviar mensajes (salida)
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			String cmsg = "";
			while (true) {
				String smsg = in.readUTF();
				if (smsg != "") {
					String[] dsmsg = smsg.split(";");
					if (dsmsg.length == 2) {
						if (dsmsg[0].equalsIgnoreCase("input")) {
							try {
								out.writeUTF(command + "/" + data);
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else if (dsmsg[0].equalsIgnoreCase("msg")) {
							result = dsmsg[1];

						} else if (dsmsg[0].equalsIgnoreCase("close")) {
							cs.close();
							break;
						}
					}
				}
				if (cmsg.equalsIgnoreCase("exit")) {
					cs.close();
				}
			}

		} catch (UnknownHostException e) {
			// e.printStackTrace();
			result = "EC";
		} catch (IOException e) {
			// e.printStackTrace();
			result = "EC";
		}
		return result;
	}
}
