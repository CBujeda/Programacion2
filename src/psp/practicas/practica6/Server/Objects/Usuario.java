package psp.practicas.practica6.Server.Objects;

public class Usuario {

	private String name;
	private String publickey;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String name, String publickey) {
		super();
		this.name = name;
		this.publickey = publickey;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublickey() {
		return publickey;
	}
	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	
	
	
}
