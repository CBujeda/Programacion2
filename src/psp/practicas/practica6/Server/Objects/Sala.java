package psp.practicas.practica6.Server.Objects;

import java.util.ArrayList;

public class Sala {
	
	private String name;
	private String code;
	private ArrayList<Usuario> usuarios;
	public Sala() {
		super();
	}
	
	public Sala(String name) {
		super();
		this.name = name;
	}
	
	public Sala(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public Sala(String name, String code, ArrayList<Usuario> usuarios) {
		super();
		this.name = name;
		this.code = code;
		this.usuarios = usuarios;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
