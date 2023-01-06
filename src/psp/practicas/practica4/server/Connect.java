package psp.practicas.practica4.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import psp.practicas.practica4.Config;

public class Connect extends Thread implements Config {

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

	/**
	 * Pre:
	 * Post: Metodo el cual genera una nueva conexion
	 */
	@Override
	public void run() {
		//super.run();
		this.ocupated = false;
		this.finalizated = false;
		int plazas = 0;
		try {
			info("Esperando..."); //Esperando conexión
            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
            
            this.ocupated = true;
            info("Cliente en línea");
        	
            DataInputStream in = new DataInputStream(cs.getInputStream());
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            write(out,"Conexion establecida");
            write(out,"-----------------------");
            write(out,"INICIO COMPRA:Cliente " + this.idClient);
            info("INICIO COMPRA:Cliente " + this.idClient);
            String mensaje = "";
        	while(true) {
	            //Se le envía un mensaje al cliente usando su flujo de salida
        		
        		write(out,"BIENVENIDO AL SERVICIO");
        		if(dt.totalOcupated()) {
        			write(out,"VUELO COMPLETO");
        			closeConexion(out,cs);
        			break;
        		}else {
	        		while(true) {
		        		write(out,"PLAZAS DISPONIBLES: ");
		        		write(out,dt.getplazStr() + "" + dt.getplazOcupStr());
		        		//write(out,"Petición recibida y aceptada");
		        		
		        		mensaje = cinput(out,in);
		        		int response = dt.isReservada(mensaje);
		        		if(response == 0) {
		        			write(out,"Reservando plaza...");
		        			boolean very = dt.reservar(mensaje);
		        			if(very) {
		        				write(out,"Plaza "+mensaje+" reservada correctamente");
		        				plazas++;
		        			}else {
		        				write(out,"[ERROR] - > Se produjo un error al reservar una plaza");
		        			}
		        			break;
		        		}else if(response == 1) {
		        			write(out,"La plaza " + mensaje +" esta ocupada.");
		        			write(out,dt.getplazOcupStr());
		        			
		        		}else if(response == -1) {
		        			write(out,"La plaza " + mensaje +" no existe");
		        		}
	        		}
	        		write(out,"Reservas: " + plazas + " CLIENTE: " + this.idClient);
	        		write(out,"Desea reservar otra plaza? S/N");
	        		mensaje = cinput(out,in);
	        		if(mensaje.equalsIgnoreCase("N")) {
	        			closeConexion(out,cs);
	        			break;
	        		}
        		}
        	}
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	/**
	 * Pre:
	 * Post: Mensaje de texto
	 */
	private void info(String txt) {
		if(Config.colors) {
			System.out.print("\033[1;32m");
		}
		System.out.print("[INFO] ID{"+this.idClient+"}");
		if(Config.colors) {
			System.out.print("\033[1;35m");
		}
		System.out.print("-> ");
		if(Config.colors) {
			System.out.print("\033[1;37m");
		}
		System.out.println(txt);
	}
	
	/**
	 * Pre:
	 * Post: Cerrar conexion
	 */
	private void closeConexion(DataOutputStream out,Socket cs) {
		close(out);
		info("Fin de la conexión");
    	try {
			cs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.finalizated = true;
    	
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual indica al cliente que se espera una entrada
	 */
	private String cinput(DataOutputStream out,DataInputStream in) {
		write(out,"input","I");
		return read(in);
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual espera una lectura
	 */
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
	
	/**
	 * Pre:
	 * Post: Metodo el cual indica al cliente terminar la conexion
	 */
	private void close(DataOutputStream out) {
		write(out,"close","C");
	}

	/**
	 * Pre:
	 * Post: Metodo el cual envia un mensaje al cliente, se le puede indicar tipo
	 */
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
	

	/*
	 * Metodos de verificacion
	 */
	public synchronized boolean isOcupated() { 
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
