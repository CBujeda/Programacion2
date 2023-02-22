package psp.practicas.practica6.utils;

import java.util.ArrayList;

public class IdGen {

	private ArrayList<String> used;
	private boolean enableAntyDouble;
	public IdGen() {
		super();
		used = new ArrayList<String>();
		enableAntyDouble = false;
		// TODO Auto-generated constructor stub
	}
	
	public void enableAntyUsed() {
		this.enableAntyDouble = true;
	}
	
	
	private String[] charactersArray() {
		String[] characters =  {"1","2","3","4","5",
				                "6","7","8","9","a",
				                "b","c","d","e","f",
				                "g","h","i","j","k",
				                "l","m","n","o","p",
				                "q","r","s","t","v",
				                "w","x","y","z","0"};
		return characters;
	}
	public String getAleatorygen(int size) {return getAleatorygen(size,charactersArray());}
	public String getAleatorygen(int size,String[] characters) {
		String cadena = "";
		for(int i = 0; i < size;i++) {
			int random = (int) (Math.random() * ((characters.length-1) - 0)) + 0;
			cadena = cadena + characters[random];		
		}
		if(enableAntyDouble) {
			if(isInUsed(cadena)) {
				cadena = getAleatorygen(size,characters);
			}
			used.add(cadena);
		}
		return cadena;
	}
	
	public boolean isInUsed(String cadena) {
		for(int i = 0; i < used.size();i++) {
			if(cadena.equals(used.get(i))) {
				return true;
			}
		}
		return false;
	}
}
