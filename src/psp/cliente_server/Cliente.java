package psp.cliente_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente extends Conexion {
    public Cliente() throws IOException {
    	super("cliente");
    } //Se usa el constructor para cliente de Conexion

    public void startClient() {//Método para iniciar el cliente
        try {
        	// Canal para recibir mensajes (entrada)
        	DataInputStream in = new DataInputStream(cs.getInputStream());
        	// Canal para enviar mensajes (salida)
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            
            String mensaje = in.readUTF();
            System.out.println(mensaje);
           
            mensaje = "XD que pasa...";
            System.out.println("Mensaje enviado -> " + mensaje);
            out.writeUTF(mensaje);
            cs.close();//Fin de la conexión
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
