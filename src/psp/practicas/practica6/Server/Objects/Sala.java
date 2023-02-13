package psp.practicas.practica6.Server.Objects;

import java.util.ArrayList;

public class Sala {
	
	private String code;
	private ArrayList<Usuario> usuarios;
	public Sala() {
		this.code = "";
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public Sala(String code) {
		this.code = code;
		this.usuarios = new ArrayList<Usuario>();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
