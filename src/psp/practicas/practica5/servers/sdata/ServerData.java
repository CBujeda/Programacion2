package psp.practicas.practica5.servers.sdata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import psp.practicas.practica5.Config;
import psp.practicas.practica5.servers.sdata.Tuplas.Tupla;
/*
 * Objeto del servidor de datos
 */
public class ServerData implements Config {

	/*
	 * Almacenador de tuplas
	 */
	ArrayList<Tupla> l = new ArrayList<Tupla>();

	private ServerSocket ss;
	protected Socket cs;
	int type;

	/*
	 * Pre:
	 * Post: Metodo constructor en el cual se indicara el tipo de servidor de datos que ejecutara
	 */
	public ServerData(int type) {
		this.type = type;
		int port = 5001;
		info("[T] : {" + type + "}");
		if (type == 1) {
			port = Config.portSD1;
		} else if (type == 2) {
			port = Config.portSD2;
		} else if (type == 3) {
			port = Config.portSD3;
		} else if (type == 12) {
			port = Config.portSD12;
		}
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Almacenamiento de threads de conexiones
	 */
	private ArrayList<ConnectSD> lc = new ArrayList<ConnectSD>();
	int maxID = 0;

	/*
	 * Pre:
	 * Post: Metodo con el cual Iniciamos el sistema se threads para la espera
	 * 		 de un hipotetico cliente
	 */
	public void startSD() {
		while (true) {
			if (this.lc.size() == 0) {
				createConexion();
				this.maxID++;
			}
			if (this.lc.get(this.lc.size() - 1).isOcupated() == true) {
				createConexion();
				this.maxID++;
			}
			for (int i = 0; i < lc.size(); i++) {
				if (lc.get(i).isFinalizated()) {
					lc.remove(i);
				}
			}
		}
	}

	/**
	 * Pre: Post: Metodo el cual añade una conexion
	 */
	public synchronized void createConexion() {			//Usamos syncronized ya que accedeb varios threads
		this.lc.add(new ConnectSD(this.ss, this.cs, maxID));
		this.lc.get(this.lc.size() - 1).setDS(l);
		this.lc.get(this.lc.size() - 1).start();
	}

	public synchronized void addTupla(String[] data) {	// Usamos synchronized ya que acceden varios threads
		this.l.add(new Tupla(data));
	}

	/**
	 * Pre: Post: Mensaje de texto
	 */
	private void info(String txt) {
		if (Config.colors) {
			System.out.print("\033[1;32m");
		}
		System.out.print("[INFO] SD SERVER ");
		if (Config.colors) {
			System.out.print("\033[1;35m");
		}
		System.out.print("-> ");
		if (Config.colors) {
			System.out.print("\033[1;37m");
		}
		System.out.println(txt);
	}

}
