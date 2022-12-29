package psp.practicas.practica4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
	/*
	 * Configuración server y Cliente
	 */
    private final int PUERTO = 1234; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    
    public Conexion(boolean server) throws IOException {//Constructor
        if(server == true) {
            ss = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            //cs = new Socket(); //Socket para el cliente
        } else {
            cs = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
        }
    }
}
