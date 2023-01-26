package psp.practicas.practica5.servers.balanceador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import psp.practicas.practica5.Config;

public class CopySystem extends Thread implements Config {

	private Linda l;
	//private ServerSocket ss;
	//protected Socket cs;
	
	public CopySystem() {
		super();
	}
	
	public void setLinda(Linda l) {
		this.l = l;
	}

	@Override
	public void run() {
		super.run();
		while(true) {
			String very = clientSDActuator(Config.portSD1);
			System.out.println(very);
			String very2 = clientSDActuator(Config.portSD12);
			System.out.println(very2);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	public String clientSDActuator(int port) {
		String result = "";
		try {
			Socket cs = new Socket(Config.hostServers, port);

			// Canal para recibir mensajes (entrada)
			DataInputStream in = new DataInputStream(cs.getInputStream());
			// Canal para enviar mensajes (salida)
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());
			while (true) {
				String smsg = in.readUTF();
				out.writeUTF("verify");
				cs.close();
			}

		} catch (UnknownHostException e) {
			result = "error";
		} catch (IOException e) {
			result = "error";
		}
		return result;
	}


	/*
	public void stopLinda() {
		ArrayList<Connect> lc = l.getConexions();
		for(int i  = 0; i < lc.size(); i++) {
			lc.get(i).suspend();
	
		}
	}
	
	public void restartLinda() {
		ArrayList<Connect> lc = l.getConexions();
		for(int i  = 0; i < lc.size(); i++) {
			lc.get(i).resume();

		}
	}
	*/
}
