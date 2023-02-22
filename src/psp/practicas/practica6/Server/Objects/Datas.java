package psp.practicas.practica6.Server.Objects;

import java.util.ArrayList;

public class Datas {

	private ArrayList<Sala> s;
	
	public Datas() {
		super();
		this.s = new ArrayList<Sala>();
	}

	
	public synchronized void addSala(String name,String code) {
		s.add(new Sala(name,code));
	}
	
	public synchronized ArrayList<String> getAllSalaStr() {
		ArrayList<String> salasStr = new ArrayList<String>();
		salasStr.add("█████████████████► SALAS ◄█████████████████");
		if(s.size() == 0) {
			salasStr.add("█► ♦ No hay chats disponibles ¡Crea Uno! ♦");
		}
		for(int i = 0; i < s.size();i++) {
			salasStr.add("█► " + s.get(i).getName());
		}
		salasStr.add("█████████████████▬═▬═▬═▬═▬█████████████████");
		return salasStr;
	}
}
