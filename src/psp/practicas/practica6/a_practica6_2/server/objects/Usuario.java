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
	public synchronized void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized DataInputStream getIn() {
		return in;
	}
	public synchronized void setIn(DataInputStream in) {
		this.in = in;
	}
	public synchronized DataOutputStream getOut() {
		return out;
	}
	public synchronized void setOut(DataOutputStream out) {
		this.out = out;
	}
	public synchronized Cifrator getClientCrypter() {
		return clientCrypter;
	}
	public synchronized void setClientCrypter(Cifrator clientCrypter) {
		this.clientCrypter = clientCrypter;
	}
	
	
	
	//DataInputStream in = new DataInputStream(cs.getInputStream());
	//DataOutputStream out = new DataOutputStream(cs.getOutputStream());
	
	
}
