package psp.practicas.practica5.servers.balanceador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import psp.practicas.practica5.Config;

public class Linda implements Config{
	
	private ArrayList<Connect> lc = new ArrayList<Connect>();
	int maxID = 0;
	
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
 
	public Linda() {
		try {
			ss = new ServerSocket(Config.portLinda);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startServer() {
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
		this.lc.add(new Connect(this.ss, this.cs,maxID));
		this.lc.get(this.lc.size()-1).start();
	}
}
