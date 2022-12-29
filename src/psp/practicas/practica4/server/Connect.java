package psp.practicas.practica4.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect extends Thread {

	private ServerSocket ss; 
	protected Socket cs;
	private boolean ocupated;
	private boolean finalizated;
	private int idClient;
	public Connect(ServerSocket ss, Socket cs,int maxID) {
		this.ss = ss;
		this.cs = cs;
		this.idClient = maxID + 1;
	}

	@Override
	public void run() {
		//super.run();
		this.ocupated = false;
		this.finalizated = false;
		try {
			System.out.println("Esperando..."); //Esperando conexión
            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
            
            this.ocupated = true;
            System.out.println("Cliente en línea");
        	
            DataInputStream in = new DataInputStream(cs.getInputStream());
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            write(out,"Conexion establecida");
            write(out,"-----------------------");
            write(out,"INICIO COMPRA:Cliente " + this.idClient);
        	while(true) {
	            //Se le envía un mensaje al cliente usando su flujo de salida
        		write(out,"Petición recibida y aceptada");
        		
        		String mensaje = cinput(out,in);
                System.out.println("Mensaje recibido -> " + mensaje);
	           
	            
	            if(mensaje.equalsIgnoreCase("exit")) {
	            	System.out.println("Fin de la conexión");
	            	cs.close();
	            	this.finalizated = true;
	            	break;
	            }
	            
        	}
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	private String cinput(DataOutputStream out,DataInputStream in) {
		write(out,"input","D");
		return read(in);
	}
	
	private String read(DataInputStream in) {
		try {
			return in.readUTF();
		} catch (IOException e) {
			System.out.println("Fallo al leer datos, Rompiendo conexion | Client: " + this.idClient);
			e.printStackTrace();
			this.finalizated = true;
			this.stop();
			return "error";
		}
		
	}
	

	
	private void write(DataOutputStream out,String msg) { write(out,"msg",msg);}
	private void write(DataOutputStream out,String type,String msg) {
		try {
			msg = msg.replaceAll(";","");
			type = type.replaceAll(";", "");
			out.writeUTF(type+";" + msg);
		} catch (IOException e) {
			System.out.println("Fallo al escribir datos, Rompiendo conexion | Client: " + this.idClient);
			e.printStackTrace();
			this.finalizated = true;
			this.stop();
		}
        
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	


	public synchronized boolean isOcupated() { 	// Reahacer con semaforos
		return this.ocupated;
	}

	public void setOcupated(boolean ocupated) {
		this.ocupated = ocupated;
	}

	public synchronized boolean isFinalizated() {
		return this.finalizated;
	}

	public void setFinalizated(boolean finalizated) {
		this.finalizated = finalizated;
	}

	public synchronized int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	
	
}
