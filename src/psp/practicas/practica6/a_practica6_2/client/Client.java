package psp.practicas.practica6.a_practica6_2.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import psp.practicas.practica6.Config;
import psp.practicas.practica6.utils.rsaUtil.Cifrator;

public class Client  implements Config {
	//private boolean colors = true;
	private String publicKeyServer;
	
	private String publicKeyClient;
	private String privateKeyClient;
	private Cifrator crClient;
	private Cifrator crServer;
	private boolean startCyphredTell;
	
	protected Socket cs;
	public Client() throws IOException {
		cs = new Socket(Config.ip_server, Config.port_server);
		startCyphredTell = false;
		crClient = new Cifrator();
		crClient.genKeys();
		crServer = new Cifrator();
		// TODO Auto-generated constructor stub
	}
	/*
	 * Pre:
	 * Post: Metodo de Conexion con el servidor y muestreo (Terminal tonta)
	 */
	 public void startClient() {//Método para iniciar el cliente
	        try {
	        	Scanner sc = new Scanner(System.in);
	        	// Canal para recibir mensajes (entrada)
	        	DataInputStream in = new DataInputStream(cs.getInputStream());
	        	// Canal para enviar mensajes (salida)
	            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
	            String cmsg = "";
	            
	            
	        	while(true) {

		            String smsg = in.readUTF();
		            if(startCyphredTell) {
		            	smsg = crClient.decrypt(smsg);
        			}
		            if(smsg != "") {
		            	String [] dsmsg = smsg.split(";");
		            	if(dsmsg.length == 2) {
		            		if(dsmsg[0].equalsIgnoreCase("input")) {
		            			cmsg = write(sc, out);
		            		}else if(dsmsg[0].equalsIgnoreCase("msg")) {
		            			if(Config.colors) {
		            				System.out.print("\033[1;36m");
		            			}
		            			String data = dsmsg[1];
		            			System.out.println("[SERVER] > " + data);	
		            		}else if(dsmsg[0].equalsIgnoreCase("pubkey")) {
		            			this.publicKeyServer = dsmsg[1];
		            			crServer.setPublicKey(this.publicKeyServer);
		            			out.writeUTF(crClient.getPublickey());
		            			startCyphredTell = true;
		            		}else if(dsmsg[0].equalsIgnoreCase("chat")) {
		            			Input i = new Input(out,crServer);
		        	            i.start();
		            			while(true) {
		            				String mensajeChat = in.readUTF();
		            				if(startCyphredTell) {
		        		            	String mCDecrypt = crClient.decrypt(mensajeChat);
		        		            	System.out.println(mCDecrypt);
		        		            	
		                			}
		            			}
		            		}else if(dsmsg[0].equalsIgnoreCase("close")) {
		            			cs.close();
		            		}
		            	}
		            }
		            if(cmsg.equalsIgnoreCase("exit") ) {
		            	cs.close();
		            }
	        	}
	        }
	        catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 	/*
	 	 * Pre:
	 	 * Post: Metodo de escritura en el cliente.
	 	 */
	 	public String write(Scanner sc,DataOutputStream out) {
	 		if(Config.colors) {
	 			System.out.print("\033[1;33m");
	 		}
	 		System.out.print("[CLIENT] > ");
	 		if(Config.colors) {
	 			System.out.print("\033[1;32m");
	 		}
            try {
            	String w = sc.nextLine();
            	if(startCyphredTell) {
            		w = crServer.crypt(w);
            	}
				out.writeUTF(w);
				return w;
			} catch (IOException e) {
				e.printStackTrace();
				return "error";	
			}
		}
}
