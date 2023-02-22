package psp.practicas.practica6.a_practica6_2.server.objects;

import java.util.ArrayList;

public class Salas {

	private ArrayList<Sala> salas;
	
	
	
	public Salas() {
		super();
		salas  = new ArrayList<Sala>();
	}



	public synchronized boolean addNewSala(Sala s) {
		boolean exists = false;
		for(int i = 0; i < salas.size(); i++) {
			if(salas.get(i).getName().equalsIgnoreCase(s.getName())) {
				exists = true;
			}
		}
		if(exists == false) {
			salas.add(s);
			return true;
		}else {
			return false;
		}
	}
	
	public synchronized Sala getSala(String name) {
		Sala s = null;
		for(int i = 0; i < salas.size(); i++) {
			if(salas.get(i).getName().equalsIgnoreCase(name)) {
				s = salas.get(i);
				break;
			}
		}
		
		return s;
	}
	
	public synchronized ArrayList<String> getAllSalaStr() {
		ArrayList<String> salasStr = new ArrayList<String>();
		salasStr.add("█████████████████► SALAS ◄█████████████████");
		if(salas.size() == 0) {
			salasStr.add("█► ♦ No hay chats disponibles ¡Crea Uno! ♦");
		}
		for(int i = 0; i < salas.size();i++) {
			salasStr.add("█► " + salas.get(i).getName());
		}
		salasStr.add("█████████████████▬═▬═▬═▬═▬█████████████████");
		return salasStr;
	}
	
	
	
}
