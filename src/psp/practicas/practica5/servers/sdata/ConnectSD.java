package psp.practicas.practica5.servers.sdata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import psp.practicas.practica5.Config;
import psp.practicas.practica5.servers.sdata.Tuplas.Tupla;

public class ConnectSD extends Thread implements Config {

	private ArrayList<Tupla> tp;
	private ServerSocket ss;
	protected Socket cs;
	private boolean ocupated;
	private boolean finalizated;
	private int idClient;
	/*
	 * Letras a verificar
	 */
	private String[] leters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	public ConnectSD(ServerSocket ss, Socket cs, int maxID) {
		this.ss = ss;
		this.cs = cs;
		this.idClient = maxID + 1;
	}

	// Set DataSource
	public void setDS(ArrayList<Tupla> tuplasdt) {
		this.tp = tuplasdt;
	}

	/**
	 * Pre: Post: Metodo el cual genera una nueva conexion
	 */
	@Override
	public void run() {
		// super.run();
		this.ocupated = false;
		this.finalizated = false;
		int plazas = 0;
		try {
			info("Esperando..."); // Esperando conexión
			cs = ss.accept(); // Accept comienza el socket y espera una conexión desde un cliente

			this.ocupated = true;
			info("Cliente en línea");

			DataInputStream in = new DataInputStream(cs.getInputStream());
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			info("Linda Server ID:" + this.idClient);
			String mensaje = "";
			while (true) {
				String command = cinput(out, in); // commando
				System.out.println(command);
				String[] cdta = command.split("/");
				if (cdta.length == 2) {
					String codta = cdta[0];
					codta = codta.replaceAll(" ", "");
					String dtdta = cdta[1];
					String[] dtdtaSp = dtdta.split(",");
					System.out.println(codta);
					// tp.add(new Tupla(dtdtaSp));
					// Dicho if controla todo lo que tiene que ver con PN (Insert Note)
					if (codta.equalsIgnoreCase("PN")) {
						tp.add(new Tupla(dtdtaSp));
						mensaje = "Inserccion exitosa";
						
					// Dicho if controla todo lo que tiene que ver con RN (Remove Note)
					} else if (codta.equalsIgnoreCase("RN")) {
						for (int i = 0; i < tp.size(); i++) {
							boolean equals = true;
							String[] tupla = tp.get(i).getData();
							if (tupla.length == dtdtaSp.length) {
								for (int b = 0; b < tupla.length; b++) {
									boolean isIngnore = false;
									// Ingnoramos los que sean ?A - ?Z
									for (int l = 0; l < this.leters.length; l++) {
										String varIngnore = "?" + this.leters[l];
										if (varIngnore.equals(dtdtaSp[b].replaceAll(" ", ""))) {
											isIngnore = true;
										}
									}
									if (!isIngnore) {
										// Filtramos datos
										if (!tupla[b].replaceAll(" ", "")
												.equalsIgnoreCase(dtdtaSp[b].replaceAll(" ", ""))) {
											equals = false;
										}
									}
								}
							} else {
								equals = false;
								mensaje = "No se ha encontrado la tupla: " + dtdta;
							}
							if (equals == true) {
								tp.remove(i);
								mensaje = "Se ha eliminado la tupla: " + dtdta;
								break;
							}
						}

					// Dicho if controla todo lo que tiene que ver con ReadN (Read Note)
					}else if(codta.equalsIgnoreCase("ReadN")) {
						System.out.println("Entra a readn");
						for (int i = 0; i < tp.size(); i++) {	// Comparamos todos los datos
							boolean equals = true;
							String[] tupla = tp.get(i).getData();
							if (tupla.length == dtdtaSp.length) {
								for (int b = 0; b < tupla.length; b++) {
									boolean isIngnore = false;
									// Ingnoramos los que sean ?A - ?Z
									for (int l = 0; l < this.leters.length; l++) {
										String varIngnore = "?" + this.leters[l];
										if (varIngnore.equals(dtdtaSp[b].replaceAll(" ", ""))) {
											System.out.println("Encontrado" + varIngnore);
											isIngnore = true;
										}
									}
									// SI no es ?A - ?Z filtramos
									if (!isIngnore) {
										// Filtramos
										if (!tupla[b].replaceAll(" ", "")
												.equalsIgnoreCase(dtdtaSp[b].replaceAll(" ", ""))) {
											equals = false;
										}
									}
								}
							} else {
								equals = false;
								mensaje = "No se encontro la busqueda";
							}
							if (equals == true) {
								// Maquetamos los datos
								String data = "[";
								for(int d = 0; d < tp.get(i).getData().length;d++) {
									data = data + tp.get(i).getData()[d];
									if(d < tp.get(i).getData().length-1) {
										data = data + ",";
									}
								}
								data = data + "]";
								// Enviamos el resultado
								mensaje = "Se ha encontrado el siguiente mensaje: \n "
										+ "		> " + data ;
								break;

							}
						}
					}
					
					
					
					for (int i = 0; i < dtdtaSp.length; i++) {
						System.out.println(dtdtaSp[i]);
					}

				}
				viewData();
				// if(command == "exit") {
				write(out, mensaje);
				closeConexion(out, cs);
				break;
				// }
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void viewData() {
		String data = "\n";
		for (int i = 0; i < tp.size(); i++) {
			data = data + "[";
			for (int b = 0; b < tp.get(i).getData().length; b++) {
				data = data + tp.get(i).getData()[b] + ", ";

			}
			data = data + "] \n";
		}
		info(data);

	}

	/**
	 * Pre: Post: Mensaje de texto
	 */
	private void info(String txt) {
		if (Config.colors) {
			System.out.print("\033[1;32m");
		}
		System.out.print("[INFO] ID LINDA{" + this.idClient + "}");
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
		write(out, "input", "I");
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

	public void setOcupated(boolean ocupated) {
		this.ocupated = ocupated;
	}

	public synchronized boolean isFinalizated() {
		return this.finalizated;
	}

	public void setFinalizated(boolean finalizated) {
		this.finalizated = finalizated;
	}

	public synchronized int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

}
