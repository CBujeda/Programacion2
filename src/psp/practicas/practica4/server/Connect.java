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
	private Data dt;
	public Connect(ServerSocket ss, Socket cs,int maxID,Data dt) {
		this.ss = ss;
		this.cs = cs;
		this.idClient = maxID + 1;
		this.dt = dt;
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
            System.out.println("INICIO COMPRA:Cliente " + this.idClient);
            String mensaje = "";
        	while(true) {
	            //Se le envía un mensaje al cliente usando su flujo de salida
        		
        		write(out,"BIENVENIDO AL SERVICIO");
        		while(true) {
	        		write(out,"PLAZAS DISPONIBLES: ");
	        		write(out,dt.getplazStr() + "" + dt.getplazOcupStr());
	        		//write(out,"Petición recibida y aceptada");
	        		
	        		mensaje = cinput(out,in);
	                System.out.println("Mensaje recibido -> " + mensaje);
	                
	                
	                break;
        		}
	            
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
	
	private String read(DataInputStream in) {	// Mejorar
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
			this.stop();	// Revisar error al petar cliente
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
