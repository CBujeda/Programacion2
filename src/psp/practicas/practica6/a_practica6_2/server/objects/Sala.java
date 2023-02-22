package psp.practicas.practica6.a_practica6_2.server.objects;

import java.util.ArrayList;

public class Sala {

	private int contUsers;
	private String name;
	private String pass;
	private ArrayList<Usuario> usuarios;
	
	
	public Sala() {
		super();
		usuarios = new ArrayList<Usuario>();
		// TODO Auto-generated constructor stub
	}
	public Sala(String name, String pass, ArrayList<Usuario> usuarios) {
		super();
		this.contUsers = 0;
		this.name = name;
		this.pass = pass;
		this.usuarios = usuarios;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public synchronized void addUser(Usuario u) {
		this.contUsers++;
		this.usuarios.add(u);
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public int getContUsers() {
		return contUsers;
	}
	public void setContUsers(int contUsers) {
		this.contUsers = contUsers;
	}
	
	
}
