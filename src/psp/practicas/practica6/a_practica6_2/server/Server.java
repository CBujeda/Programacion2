package psp.practicas.practica6.a_practica6_2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import psp.practicas.practica6.Config;
import psp.practicas.practica6.a_practica6_2.server.objects.Salas;

public class Server extends Thread implements Config{
	
	/*
	 * Almacen de Conexiones el cual almacena las referencias de los threads
	 * para su posterior control efectivo
	 */
	private ArrayList<Connect> lc = new ArrayList<Connect>();
	int maxID = 0;	// Id de conexion
	
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente


    private Salas s;
 
    /*
     * Pre:
     * Post: Método constructor
     */
	public Server() {
		this.s = new Salas();
		try {
			ss = new ServerSocket(Config.port_server);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Pre:
	 * Post: Metodo de carga de thread
	 */
	@Override
	public void run() {
		startServer();	// Iniciamos servidor
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual inicia el servidor linda
	 */
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
		this.lc.add(new Connect(this.ss, this.cs,maxID,s));
		this.lc.get(this.lc.size()-1).start();
	}

	/*
	 * Pre:
	 * Post: Metodo con el cual obtenemos las direcciones de los threads
	 * 		 Este metodo es usado por CopySystem para poner en modo pausa
	 * 		 los threads de conexion
	 */
	public synchronized ArrayList<Connect> getConexions() {
		return this.lc;
	}
}