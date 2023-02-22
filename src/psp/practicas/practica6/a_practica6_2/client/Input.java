package psp.practicas.practica6.a_practica6_2.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import psp.practicas.practica6.Config;
import psp.practicas.practica6.utils.rsaUtil.Cifrator;

public class Input extends Thread implements Config{

	Scanner sc;
	DataOutputStream out;
	Cifrator crServer;
	private boolean active;
	
	public Input() {
		super();
		 this.sc = new Scanner(System.in);
		 this.active = true;
		// TODO Auto-generated constructor stub
	}
	public Input(DataOutputStream out,Cifrator crServer) {
		super();
		 this.sc = new Scanner(System.in);
		 this.out = out;
		 this.crServer = crServer;
		 this.active = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		super.run();
		
		while(this.active) {
			String getInput = "";
			getInput = this.sc.nextLine();
			if(getInput.length() <= 140) {
				write(this.sc,this.out,this.crServer,getInput);
			}else {
				System.err.println("Sobrepaso el limite de 140 caracteres");
			}
		}
		//sc.close();
	}
	
	public void closeThread() {
		this.active = false;
	}
	
	public String write(Scanner sc,DataOutputStream out,Cifrator crServer, String w) {
        try {
        	w = crServer.crypt(w);
			out.writeUTF(w);
			return w;
		} catch (IOException e) {
			e.printStackTrace();
			return "error";	
		}
	}

}
