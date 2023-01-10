package psp.practicas.practica5.servers.sdata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import psp.practicas.practica5.Config;
import psp.practicas.practica5.servers.sdata.Tuplas.Tupla;

public class ServerData implements Config {
	
	ArrayList<Tupla> l = new ArrayList<Tupla>();

	private ServerSocket ss; 
	protected Socket cs;
	int type;
	public ServerData(int type) {
		this.type = type;
		int port = 5001;
		if(type == 1) {
			port = Config.portSD1;
		}else if(type == 2) {
			port = Config.portSD2;
		}else if(type == 3) {
			port = Config.portSD3;
		}else if(type == 12) {
			port = Config.portSD12;
		}
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private ArrayList<ConnectSD> lc = new ArrayList<ConnectSD>();
	int maxID = 0;

	
	public void startSD() {
		while(true) {
			if(this.lc.size() == 0) {
				createConexion();
				this.maxID++;
			}
			if(this.lc.get(this.lc.size()-1).isOcupated() == true) {
				createConexion();
				this.maxID++;
			}
			for(int i = 0; i < lc.size(); i++) {
				if(lc.get(i).isFinalizated()) {
					lc.remove(i);
				}
			}
		}
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual añade una conexion
	 */
	public void createConexion() {
		this.lc.add(new ConnectSD(this.ss, this.cs,maxID));
		this.lc.get(this.lc.size()-1).start();
	}
	
	public void addTupla(String[] data) {
		this.l.add(new Tupla(data));
		
	}

	
}
