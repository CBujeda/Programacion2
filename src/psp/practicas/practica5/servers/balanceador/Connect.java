package psp.practicas.practica5.servers.balanceador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import psp.practicas.practica5.Config;

public class Connect extends Thread implements Config {

	private ServerSocket ss;
	protected Socket cs;
	private boolean ocupated;
	private boolean finalizated;
	private int idClient;
	private boolean pause;
	
	public Connect(ServerSocket ss, Socket cs, int maxID) {
		this.pause = false;
		this.ss = ss;
		this.cs = cs;
		this.idClient = maxID + 1;
	}

	/**
	 * Pre: Post: Metodo el cual genera una nueva conexion
	 */
	@Override
	public void run() {
		// super.run();
		this.ocupated = false;
		this.finalizated = false;
		try {
			info("Esperando..."); // Esperando conexión
			this.cs = this.ss.accept(); // Accept comienza el socket y espera una conexión desde un cliente

			this.ocupated = true;
			info("Cliente en línea");

			DataInputStream in = new DataInputStream(this.cs.getInputStream());
			DataOutputStream out = new DataOutputStream(this.cs.getOutputStream());
			write(out, "Conexion establecida");
			write(out, "-----------------------");
			write(out, "Cliente " + this.idClient);
			info("Cliente " + this.idClient);

			String mensaje = "";
			while (true) {
				// Se le envía un mensaje al cliente usando su flujo de salida

				write(out, "Linea de comandos:");
				while (true) {
					write(out, "------Commands------\n" + "● PostNote (PN / [\"\",\"\"])\n"
							+ "● RemoveNote (RN / [\"\",\"\"])\n" + "● ReadNote (ReadN / [\"\",\"\"]): ");
					mensaje = cinput(out, in);
					System.out.println(mensaje);
					String[] dt = mensaje.split("/");
					if (dt.length == 2) {
						String dta = dt[1].replaceAll("\"", "");
						dta = dta.replaceAll("\\[", "");
						dta = dta.replaceAll("\\]", "");	// Eliminamos formato
						String[] dta2 = dta.split(",");
						if (dta2.length <= 6) {
							for (int i = 0; i < dta2.length; i++) {
								info(dta2[i]);
							}
							String result = "NO DATA";
							if(pause == false) {
								result = "entra 1";
								if (dta2.length <= 3) {	// Servidores 1 / 12
									result = "entra 2";
									String result1 = clientSDActuator(Config.portSD1, dt[0], dta);	
									String result2 = clientSDActuator(Config.portSD12, dt[0], dta);
									if(!result1.equalsIgnoreCase("error")) {
										result = result1;
									}
									if(!result2.equalsIgnoreCase("error")) {
										result = result2;
									}
									if(result1.equalsIgnoreCase("error") && result2.equalsIgnoreCase("error")) {
										result = "error";
									}
								} else if (dta2.length <= 5) {	//Servers 2
									result = clientSDActuator(Config.portSD2, dt[0], dta);
								} else if (dta2.length <= 6) {	// Servers 3
									result = clientSDActuator(Config.portSD3, dt[0], dta);
								}
							}else {
								// Cod 5 = servers en pausa
								result = "{COD: 5} Servidores en espera..";
							}
							System.out.println(result);
							if(result.equalsIgnoreCase("error")) {
								result = "Ha ocurrido un error en el servidor, "
										+ "Se esta realizando copia de seguridad"
										+ "\n Sentimos las molestias";
							}
							write(out, result);
							break;
						} else {
							write(out, "Se han excedido el limite");
						}

					} else {
						write(out, "Comando invalido");
					}

				}
				write(out, "Desea enviar otro comando de nota? S/N");
				mensaje = cinput(out, in);
				if (mensaje.equalsIgnoreCase("N")) {
					closeConexion(out, cs);
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String clientSDActuator(int port, String command, String data) {
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
			e.printStackTrace();
			result = "error";
		} catch (IOException e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}

	/**
	 * Pre: Post: Mensaje de texto
	 */
	private void info(String txt) {
		if (Config.colors) {
			System.out.print("\033[1;32m");
		}
		System.out.print("[INFO] ID{" + this.idClient + "}");
		if (Config.colors) {
			System.out.print("\033[1;35m");
		}
		System.out.print("-> ");
		if (Config.colors) {
			System.out.print("\033[1;37m");
		}
		System.out.println(txt);
	}

	/**
	 * Pre: Post: Cerrar conexion
	 */
	private void closeConexion(DataOutputStream out, Socket cs) {
		close(out);
		info("Fin de la conexión");
		try {
			cs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.finalizated = true;

	}

	/**
	 * Pre: Post: Metodo el cual indica al cliente que se espera una entrada
	 */
	private String cinput(DataOutputStream out, DataInputStream in) {
		write(out, "input", "I");	// Escribimos timpo input
		return read(in);
	}

	/**
	 * Pre: Post: Metodo el cual espera una lectura
	 */
	private String read(DataInputStream in) { // Mejorar
		try {
			return in.readUTF();
		} catch (IOException e) {
			System.out.println("Fallo al leer datos, Rompiendo conexion | Client: " + this.idClient);
			e.printStackTrace();
			this.finalizated = true;
			this.stop();
			return "error";
		}
	}

	/**
	 * Pre: Post: Metodo el cual indica al cliente terminar la conexion
	 */
	private void close(DataOutputStream out) {
		write(out, "close", "C");
	}

	/**
	 * Pre: Post: Metodo el cual envia un mensaje al cliente, se le puede indicar
	 * tipo
	 */
	private void write(DataOutputStream out, String msg) {
		write(out, "msg", msg);
	}
	
	/*
	 * Pre:
	 * Post: metodo el cual envia un mensaje
	 */
	private void write(DataOutputStream out, String type, String msg) {
		try {
			msg = msg.replaceAll(";", "");
			type = type.replaceAll(";", "");
			out.writeUTF(type + ";" + msg);
		} catch (IOException e) {
			System.out.println("Fallo al escribir datos, Rompiendo conexion | Client: " + this.idClient);
			e.printStackTrace();
			this.finalizated = true;
			this.stop(); // Revisar error al petar cliente
		}

	}

	/*
	 * Pre:
	 * Post: Metodo toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	/*
	 * Metodos de verificacion
	 */
	public synchronized boolean isOcupated() {
		return this.ocupated;
	}
	
	/*
	 * Pre:
	 * Post: Establecemos el servicio como ocupado
	 */
	public void setOcupated(boolean ocupated) {
		this.ocupated = ocupated;
	}
	
	/*
	 * Pre:
	 * Post: Metodo verifica si la conexion ha finalizado
	 */
	public synchronized boolean isFinalizated() {
		return this.finalizated;
	}
	
	/*
	 * Pre:
	 * Post: Establecemos el servicio como finalizado
	 */
	public void setFinalizated(boolean finalizated) {
		this.finalizated = finalizated;
	}

	/*
	 * Pre:
	 * Post: Metodo el cual Obtiene el id del cliente
	 */
	public synchronized int getIdClient() {
		return idClient;
	}
	/*
	 * Pre:
	 * Post: Metodo el cual establece el id del cliente
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public synchronized boolean isPause() {
		return pause;
	}

	public synchronized void setPause(boolean pause) {
		this.pause = pause;
	}
	
	

}
