package psp.practicas.practica4.server;

import java.util.ArrayList;

import psp.practicas.practica4.server.avion.Plaza;

public class Data {

	private ArrayList<Plaza[]> plaz ;
	
	private int totalPlaz;
	private int actualOcup;
	
	public Data() {
		plaz = new ArrayList<Plaza[]>();
		for(int i = 0; i < 4; i++) {
			plaz.add(new Plaza[4]);
			for(int e = 0; e < plaz.get(plaz.size()-1).length; e++) {
				plaz.get(plaz.size()-1)[e] = new Plaza(i,e);
				this.totalPlaz++;
			}
		}
	}
	
	public synchronized String getplazStr() {	// Usar semaforos
		String dt = "\n";
		for(int i = 0; i < plaz.size();i++) {	
			for(int e = 0; e < plaz.get(i).length; e++) {
				dt = dt +" [" + plaz.get(i)[e].getPlaza() + "] ";
			}
			dt = dt + "\n";
		}
		return dt;
	}
	
	public synchronized String getplazOcupStr() { // Usar semaforos
		String dt = "\n";
		for(int i = 0; i < plaz.size();i++) {	
			for(int e = 0; e < plaz.get(i).length; e++) {
				if(plaz.get(i)[e].isReserved()) {
					dt = dt +" [X ] ";
				}else {
					dt = dt +" [L ] ";
				}
			}
			dt = dt + "\n";
		}
		return dt;
	}
	
	public synchronized boolean reservar(String f) {
		for(int i = 0; i < plaz.size();i++) {	
			for(int e = 0; e < plaz.get(i).length; e++) {
				if(plaz.get(i)[e].getPlaza().equalsIgnoreCase(f)) {
					plaz.get(i)[e].setReserved(true);
					this.actualOcup++;
					return true;
				}
			}
		}
		return false;
		
	}
	
	public synchronized int isReservada(String f) {
		boolean reserved = false;
		boolean exists = false;
		for(int i = 0; i < plaz.size();i++) {	
			for(int e = 0; e < plaz.get(i).length; e++) {
				if(plaz.get(i)[e].getPlaza().equalsIgnoreCase(f)) {
					reserved = plaz.get(i)[e].isReserved();
					exists = true;
				}
			}
		}
		if(exists == false) {
			return -1;
		}else if(reserved == false ) {
			return 0;
			
		}else if(reserved == true) {
			return 1;
		}else {
			return -2;
		}
	}
	
	public synchronized boolean totalOcupated() {
		if(totalPlaz == actualOcup) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
}
