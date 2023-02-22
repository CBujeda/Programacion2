package psp.practicas.practica6.a_practica6_2.server.objects;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import psp.practicas.practica6.utils.rsaUtil.Cifrator;

public class Usuario {

	
	private int id;
	private String name;
	private DataInputStream in;
	private DataOutputStream out;
	private Cifrator clientCrypter;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(int id, String name, DataInputStream in, DataOutputStream out) {
		super();
		this.id = id;
		this.name = name;
		this.in = in;
		this.out = out;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataInputStream getIn() {
		return in;
	}
	public void setIn(DataInputStream in) {
		this.in = in;
	}
	public DataOutputStream getOut() {
		return out;
	}
	public void setOut(DataOutputStream out) {
		this.out = out;
	}
	public Cifrator getClientCrypter() {
		return clientCrypter;
	}
	public void setClientCrypter(Cifrator clientCrypter) {
		this.clientCrypter = clientCrypter;
	}
	
	
	
	//DataInputStream in = new DataInputStream(cs.getInputStream());
	//DataOutputStream out = new DataOutputStream(cs.getOutputStream());
	
	
}
