package psp.practicas.practica4.server;

import java.util.ArrayList;

import psp.practicas.practica4.server.avion.Plaza;

public class Data {

	private ArrayList<Plaza[]> plaz ;

	public Data() {
		plaz = new ArrayList<Plaza[]>();
		for(int i = 0; i < 4; i++) {
			plaz.add(new Plaza[4]);
			for(int e = 0; e < plaz.get(plaz.size()-1).length; e++) {
				plaz.get(plaz.size()-1)[e] = new Plaza(i,e);
			}
		}
	}
	
	public String getplazStr() {
		String dt = "\n";
		for(int i = 0; i < plaz.size();i++) {	
			for(int e = 0; e < plaz.get(i).length; e++) {
				dt = dt +" [" + plaz.get(i)[e].getPlaza() + "] ";
			}
			dt = dt + "\n";
		}
		return dt;
	}
	
	public String getplazOcupStr() {
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
	
	
}
