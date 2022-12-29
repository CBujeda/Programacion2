package psp.practicas.practica4.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import psp.practicas.practica4.Conexion;



public class Client extends Conexion  {

	public Client() throws IOException {
		super(false);
		// TODO Auto-generated constructor stub
	}
	
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
		            if(smsg != "") {
		            	String [] dsmsg = smsg.split(";");
		            	if(dsmsg.length == 2) {
		            		if(dsmsg[0].equalsIgnoreCase("input")) {
		            			cmsg = write(sc, out);
		            		}else if(dsmsg[0].equalsIgnoreCase("msg")) {
		            			System.out.println("[SERVER] > " + dsmsg[1]);
		            			
		            		}
		            	}
		            }
		            if(cmsg.equalsIgnoreCase("exit")) {
		            	cs.close();
		            }
		            
		            
	        	}
	        	
	        }
	        catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 	public String write(Scanner sc,DataOutputStream out) {
	 		System.out.print("[CLIENT] > ");
            try {
            	String w = sc.nextLine();
				out.writeUTF(w);
				return w;
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
				
			}
	 	}

}
