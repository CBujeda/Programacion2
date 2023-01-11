package psp.practicas.practica5.servers.balanceador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import psp.practicas.practica5.Config;




public class Connect extends Thread implements Config {
	 
	
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

	/**
	 * Pre:
	 * Post: Metodo el cual genera una nueva conexion
	 */
	@Override
	public void run() {
		//super.run();
		this.ocupated = false;
		this.finalizated = false;
		try {
			info("Esperando..."); //Esperando conexión
            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
            
            this.ocupated = true;
            info("Cliente en línea");
        	
            DataInputStream in = new DataInputStream(cs.getInputStream());
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            write(out,"Conexion establecida");
            write(out,"-----------------------");
            write(out,"Cliente " + this.idClient);
            info("Cliente " + this.idClient);
            String mensaje = "";
        	while(true) {
	            //Se le envía un mensaje al cliente usando su flujo de salida
        		
        		write(out,"Linea de comandos:");
	        		while(true) {
		        		write(out,"------Commands------\n"
		        				+ "● PostNote (PN / [\"\",\"\"])\n"
		        				+ "● RemoveNote (RN / [\"\",\"\"])\n"
		        				+ "● ReadNote (ReadN / [\"\",\"\"]): ");
		        		mensaje = cinput(out,in);
		        		System.out.println(mensaje);
		        		String[] dt = mensaje.split("/");
		        		if(dt.length == 2) {
		        			String dta = dt[1].replaceAll("\"", "");
		        			dta = dta.replaceAll("\\[", "");
		        			dta = dta.replaceAll("\\]", "");
		        			String[] dta2 = dta.split(",");
		        			if(dta2.length <= 6) {
		        				for(int i = 0; i < dta2.length;i++) {
		        					info(dta2[i]);
		        				}
		        				
		        				break;
		        			}else {
		        				write(out,"Se han excedido el limite");
		        			}
		        			
		        			
		        		}else {
		        			write(out,"Comando invalido");
		        		}
		        		
		        		
		        		
	        		}
	        		write(out,"Desea guardar otra nota? S/N");
	        		mensaje = cinput(out,in);
	        		if(mensaje.equalsIgnoreCase("N")) {
	        			closeConexion(out,cs);
	        			break;
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
