package psp.practicas.practica4.server;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import psp.practicas.practica4.server.avion.Plaza;

public class Data {

	private ArrayList<Plaza[]> plaz ;
	
	private int totalPlaz;
	private int actualOcup;
	private Semaphore s_getPlazStr;
	private Semaphore s_getPlazOcup;
	private Semaphore s_reservar;
	private Semaphore s_IsReservada;
	
	public Data() {
		
		this.s_getPlazStr = new Semaphore(1,true);
		this.s_getPlazOcup = new Semaphore(1,true);
		this.s_reservar = new Semaphore(1,true);
		this.s_IsReservada = new Semaphore(1,true);
		plaz = new ArrayList<Plaza[]>();
		for(int i = 0; i < 4; i++) {
			plaz.add(new Plaza[4]);
			for(int e = 0; e < plaz.get(plaz.size()-1).length; e++) {
				plaz.get(plaz.size()-1)[e] = new Plaza(i,e);
				this.totalPlaz++;
			}
		}
	}
	
	/**
	 * Pre:
	 * Post: Metodo con el cual obtienes las plazas como string
	 */
	//synchronized
	public String getplazStr() {
		try {
			this.s_getPlazStr.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String dt = "\n";
		for(int i = 0; i < plaz.size();i++) {	
			for(int e = 0; e < plaz.get(i).length; e++) {
				dt = dt +" [" + plaz.get(i)[e].getPlaza() + "] ";
			}
			dt = dt + "\n";
		}
		s_getPlazStr.release();
		return dt;
	}
	
	/**
	 * Pre:
	 * Post: Metodo con el cual obtienes las plazas
	 * 		 en estilo ocupadas en string
	 */
	//synchronized
	public  String getplazOcupStr() {
		try {
			this.s_getPlazOcup.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		this.s_getPlazOcup.release();
		return dt;
	}
	
	/**
	 * Pre:
	 * Post: Metodo con el cual reservas una plaza
	 */
	//synchronized
	public boolean reservar(String f) {
		try {
			this.s_reservar.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i = 0; i < plaz.size();i++) {	
			for(int e = 0; e < plaz.get(i).length; e++) {
				if(plaz.get(i)[e].getPlaza().equalsIgnoreCase(f)) {
					plaz.get(i)[e].setReserved(true);
					this.actualOcup++;
					return true;
				}
			}
		}
		this.s_reservar.release();
		
		return false;
		
	}
	
	/**
	 * Pre:
	 * Post: Metodo con el cual se verifica si una plaza
	 * 		 ya esta reservada
	 */
	//synchronized
	public int isReservada(String f) {
		try {
			this.s_IsReservada.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			s_IsReservada.release();
			return -1;
		}else if(reserved == false ) {
			s_IsReservada.release();
			return 0;
			
		}else if(reserved == true) {
			s_IsReservada.release();
			return 1;
		}else {
			s_IsReservada.release();
			return -2;
		}
		
	}
	
	/**
	 * Pre:
	 * Post: Metodo con el cual retorna si estan todas las plazas
	 * 		 ocupadas
	 */
	public synchronized boolean totalOcupated() {
		if(totalPlaz == actualOcup) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
}
