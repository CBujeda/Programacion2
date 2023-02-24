package psp.practicas.practica6.a_practica6_2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import psp.practicas.practica6.Config;
import psp.practicas.practica6.a_practica6_2.server.objects.Sala;
import psp.practicas.practica6.a_practica6_2.server.objects.Salas;
import psp.practicas.practica6.a_practica6_2.server.objects.Usuario;
import psp.practicas.practica6.utils.IdGen;
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
	private Usuario user;
	private IdGen idgen;
	private Semaphore sem;
	private boolean disconnect;

	public Connect(ServerSocket ss, Socket cs, int maxID, Salas s, IdGen idgen, Semaphore sem) {
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
		this.user = new Usuario();
		this.idgen = idgen;
		this.sem = sem;
		this.disconnect = false;
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

			user.setIn(in);
			user.setOut(out);

			// Enviar clave publica
			// Obtenemos clave publica
			write(out, "pubkey", this.serverPublicKey);
			String keyClient = read(in);
			this.crClient.setPublicKey(keyClient);
			this.startCryptTell = true;
			user.setClientCrypter(crClient);
			write(out, "Conexion establecida");
			write(out, "-----------------------");
			String mensaje = "";
			write(out, "BIENVENIDO A JAVA CHAT");
			write(out, " CLIENTE: " + this.idClient);
			help(out);
			while (true) {
				// Se le envía un mensaje al cliente usando su flujo de salida
				mensaje = cinput(out, in);

				if (mensaje.equalsIgnoreCase("UN")) {
					userCreator(out);
					write(out, "└▐► UserName:");
					String nameUser = cinput(out, in);
					user.setName(nameUser);
					chatConnector(out);
					write(out, "└▐► Name:");
					String name = cinput(out, in);
					write(out, "└▐► Pass:");
					String pass = cinput(out, in);
					write(out, "Connectando a: " + name + ":" + pass);
					Sala s = this.salas.getSala(name.toLowerCase());
					if (s != null) {
						s.addUser(user);
						if (s.getPass().equals(pass)) {
							connectToChat(s, user, out, in);
						}
					} else {
						write(out, "La sala marcada no existe");
					}

				} else if (mensaje.equalsIgnoreCase("CR")) {
					// IdGen idgen = new IdGen();
					userCreator(out);
					write(out, "└▐► UserName:");
					String nameUser = cinput(out, in);
					user.setName(nameUser);
					msgCreator(out);
					write(out, "└▐► Name:");
					String name = cinput(out, in);
					write(out, "└▐► Pass:");
					String pass = cinput(out, in);
					while (true) {
						write(out, "► Desea Crearla? S/N");
						String qcrate = cinput(out, in);
						if (qcrate.equalsIgnoreCase("S")) {
							Sala s = new Sala();
							name = name.toLowerCase();
							name = name + "#" + idgen.getAleatorygen(10);
							s.addUser(user);
							s.setName(name);
							s.setPass(pass);
							salas.addNewSala(s);
							write(out, "Creando: " + name + ":" + pass);
							connectToChat(s, user, out, in);
							if (this.disconnect == true) {
								break;
							}
						}
					}
				} else if (mensaje.equalsIgnoreCase("LI")) {
					list(out);
				} else if (mensaje.equalsIgnoreCase("H")) {
					help(out);
				} else if (mensaje.equalsIgnoreCase("S")) {
					closeConexion(out, cs);
					break;
				}
				if (this.disconnect == true) {
					break;
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Pre: Post: Metodo de inicio de metodo de envio tipo chat
	 */
	private void connectToChat(Sala s, Usuario user, DataOutputStream out, DataInputStream in) {
		startChat(out);
		while (true) {
			if (disconnect == false) {
				String mensajeChat = read(in);
				boolean toME = false;
				boolean viewCommands = false;

				if (mensajeChat.contains("/")) {
					if (mensajeChat.equalsIgnoreCase("/disconnect")) {
						this.disconnect = true;
					}

					if (mensajeChat.equalsIgnoreCase("/help")) {
						toME = true;
						viewCommands = true;
					} else if (mensajeChat.contains("/e:")) {
						if (mensajeChat.equalsIgnoreCase("/e:girl")) {
							mensajeChat = "👧";
						} else if (mensajeChat.equalsIgnoreCase("/e:heart")) {
							mensajeChat = "❤";
						}
					} else if (mensajeChat.contains("/a:")) {
						if (mensajeChat.equalsIgnoreCase("/a:face")) {
							mensajeChat = "  █ █\r\n" + " ▄   ▄\r\n" + "  ▀▀▀";
						}
					}
					chatWrite(out, mensajeChat, user);
				}
				if (toME == false) {
					try {
						this.sem.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					for (int i = 0; i < s.getUsuarios().size(); i++) {

						if (!this.disconnect) {
							if (user.getId() != s.getUsuarios().get(i).getId()) {
								DataOutputStream outOth = s.getUsuarios().get(i).getOut();
								chatWrite(outOth, "[ " + user.getName() + " ] \n" + mensajeChat,
										s.getUsuarios().get(i));
							}
						} else {
							if (user.getId() == s.getUsuarios().get(i).getId()) {
								s.getUsuarios().remove(i);
							}
						}
					}
					if (this.disconnect) {
						closeConexion(out, cs);
					}

					this.sem.release();
				} else {
					if (viewCommands) {
						sendCahtCommandHelp(out, user);
					}
				}
			}else {
				break;
			}
		}
	}

	/*
	 * Pre: Post: Metodo que muestra los comandos de chat
	 */
	private void sendCahtCommandHelp(DataOutputStream out, Usuario u) {
		chatWrite(out, "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄", user);
		chatWrite(out, "█████► COMANDOS ◄█████", user);
		chatWrite(out, "█► /help", user);
		chatWrite(out, "█► /disconnect", user);
		chatWrite(out, "█████►  EMOJIS  ◄█████", user);
		chatWrite(out, "█► /e:girl", user);
		chatWrite(out, "█► /e:heart", user);
		chatWrite(out, "█████► STIKERS  ◄█████", user);
		chatWrite(out, "█► /a:face", user);
		chatWrite(out, "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀", user);
		// = "\n"
		// + "/e:girl\n"
		// + "/e:heart\n";
	}

	private void startChat(DataOutputStream out) {
		write(out, "chat", "C");
	}

	/*
	 * Pre: Post: Metodos con los cuales mostramos un mensaje
	 */
	private void msgCreator(DataOutputStream out) {
		write(out, "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		write(out, "█████►    SALA CREATOR    ◄█████");
		write(out, "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	}

	private void chatConnector(DataOutputStream out) {
		write(out, "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		write(out, "█████►    SALA CONNECTION    ◄█████");
		write(out, "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	}

	private void userCreator(DataOutputStream out) {
		write(out, "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
		write(out, "█████►    PROFILE CREATOR    ◄█████");
		write(out, "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
	}

	/*
	 * Pre: Post: Metodo con el cual listamos las salas disponibles
	 */
	private void list(DataOutputStream out) {
		ArrayList<String> s = salas.getAllSalaStr();
		for (int i = 0; i < s.size(); i++) {
			write(out, s.get(i));
		}
	}

	/*
	 * Pre: Post: metodo con el cual mostramos los comandos del programa
	 */
	private void help(DataOutputStream out) {
		write(out, "███████████████████████► Comandos utiles ◄███████████████████████");
		write(out, "█ [UN]   -  Unirse a una sala de chat ya creada anteriormente.  █");
		write(out, "█ [CR]   -  Crear una nueva sala de chat.                       █");
		write(out, "█ [LI]   -  Listar las salas disponibles.                       █");
		write(out, "█ [H ]   -  Esta Lista                                          █");
		write(out, "█ [S ]   -  Salir                                               █");
		write(out, "█████████████████████████████████████████████████████████████████");
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

			// startCryptTell
			String getData = in.readUTF();
			if (startCryptTell) {
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

	/*
	 * Pre: Post: Metodo con el cual escribimos en un tipo chat
	 */
	private void chatWrite(DataOutputStream out, String msg, Usuario user) {
		try {
			String data = msg;
			if (startCryptTell) {
				data = user.getClientCrypter().crypt(data);
			}

			out.writeUTF(data);
		} catch (IOException e) {
			System.out.println("Fallo al escribir datos, Rompiendo conexion | Client: " + this.idClient);
			e.printStackTrace();
			this.finalizated = true;
			this.stop(); // Revisar error al petar cliente
		}
	}

	/*
	 * Pre: Post: Metodo con el cual escribimos de forma usual
	 */
	private void write(DataOutputStream out, String type, String msg) {
		try {
			String data;

			msg = msg.replaceAll(";", "");
			type = type.replaceAll(";", "");

			data = type + ";" + msg;

			if (startCryptTell) {
				data = crClient.crypt(data);
			}
			// startCryptTell

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
