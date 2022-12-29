package psp.practicas.practica4.server;

import java.io.IOException;
import java.util.ArrayList;

import psp.practicas.practica4.Conexion;



public class Server extends Conexion  {

	private ArrayList<Connect> lc = new ArrayList<Connect>();
	private Data dt;
	int maxID = 0;
	
	public Server() throws IOException {
		super(true);
		
		dt = new Data();
		
	}
	
	public void startServer() {
		System.out.println(dt.getplazStr());
		System.out.println(dt.getplazOcupStr());
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
	
	public void createConexion() {
		this.lc.add(new Connect(this.ss, this.cs,maxID));
		this.lc.get(this.lc.size()-1).start();
	}
	
	public void connect() {}
}
