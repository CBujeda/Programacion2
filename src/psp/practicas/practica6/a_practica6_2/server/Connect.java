package psp.practicas.practica6.a_practica6_2.server;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import psp.practicas.practica6.Config;
import psp.practicas.practica6.a_practica6_2.server.objects.Sala;
import psp.practicas.practica6.a_practica6_2.server.objects.Salas;
import psp.practicas.practica6.a_practica6_2.server.objects.Usuario;
import psp.practicas.practica6.utils.rsaUtil.Cifrator;

public class Connect extends Thread implements Config {

	private ServerSocket ss;
	protected Socket cs;
	private boolean ocupated;
	private boolean finalizated;
	private int idClient;
	private Cifrator crServer;
	private Cifrator crClient;
    private String serverPublicKey;
    private String serverPrivateKey;
    private boolean startCryptTell;
    private Salas salas;
    
	public Connect(ServerSocket ss, Socket cs, int maxID,Salas s) {
		this.crServer = new Cifrator();
		this.crServer.genKeys();
		this.crClient = new Cifrator();
		this.startCryptTell = false;
		this.serverPublicKey = crServer.getPublickey();
		this.serverPrivateKey = crServer.getPrivatekey();
		this.ss = ss;
		this.cs = cs;
		this.idClient = maxID + 1;
		this.salas = s;

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
			
			Usuario user = new Usuario();
			user.setIn(in);
			user.setOut(out);
			
			// Enviar clave publica
			// Obtenemos clave publica
			write(out,"pubkey" ,this.serverPublicKey);
			String keyClient = read(in);
			this.crClient.setPublicKey(keyClient);
			this.startCryptTell = true;
			write(out,"Conexion establecida");
			write(out, "-----------------------");
			String mensaje = "";
			write(out, "BIENVENIDO A JAVA CHAT");
			write(out, " CLIENTE: " + this.idClient);
			help(out);
			while (true) {
				// Se le envía un mensaje al cliente usando su flujo de salida
				mensaje = cinput(out, in);
				
				if(mensaje.equalsIgnoreCase("UN")) {
					
				}else if(mensaje.equalsIgnoreCase("CR")){
					msgCreator(out);
					write(out,"└▐► Name:");
					String name = cinput(out, in);
					write(out,"└▐► Pass:");
					String pass = cinput(out, in);
					write(out,name + ":" + pass);
					while(true) {
						write(out,"► Desea Crearla? S/N");
						String qcrate = cinput(out, in);
						if(qcrate.equalsIgnoreCase("S")) {
							Sala s = new Sala();
							s.addUser(user);
							s.setName(name);
							s.setPass(pass);
							salas.addNewSala(s);
							connectToChat(s,out,in);
							//break;
						}
					}
				}else if(mensaje.equalsIgnoreCase("LI")){
					list(out);
				}else if(mensaje.equalsIgnoreCase("H")){
					help(out);
				}else if(mensaje.equalsIgnoreCase("S")) {
					closeConexion(out, cs);
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void connectToChat(Sala s,DataOutputStream out,DataInputStream in) {
		startChat(out);
		while(true) {
			String mensajeChat = read(in);//(out, in);
			System.out.println(mensajeChat);
			
			
		}
	}
	
	private void startChat(DataOutputStream out) {
		write(out,"chat","C");
	}
	
	private void msgCreator(DataOutputStream out) {
		write(out,"▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		write(out,"█████►    SALA CREATOR    ◄█████");
		write(out,"▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	}
	
	private void list(DataOutputStream out) {
		ArrayList<String> s = salas.getAllSalaStr();
		for(int i = 0; i < s.size(); i++) {
			write(out,s.get(i));
		}
	}
	
	
	private void help(DataOutputStream out) {
		write(out,"███████████████████████► Comandos utiles ◄███████████████████████");
		write(out,"█ [UN]   -  Unirse a una sala de chat ya creada anteriormente.  █");
		write(out,"█ [CR]   -  Crear una nueva sala de chat.                       █");
		write(out,"█ [LI]   -  Listar las salas disponibles.                       █");
		write(out,"█ [H ]   -  Esta Lista                                          █");
		write(out,"█ [S ]   -  Salir                                               █");
		write(out,"█████████████████████████████████████████████████████████████████");
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
		write(out, "input", "I");
		return read(in);
	}

	/**
	 * Pre: Post: Metodo el cual espera una lectura
	 */
	private String read(DataInputStream in) { // Mejorar
		try {
			
			//startCryptTell
			String getData = in.readUTF();
			if(startCryptTell) {
				getData = crServer.decrypt(getData);
			}
			
			return getData;
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
			
			String data = type + ";" + msg;
			if(startCryptTell) {
				data = crClient.crypt(data);
			}
			//startCryptTell
			
			out.writeUTF(data);
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
