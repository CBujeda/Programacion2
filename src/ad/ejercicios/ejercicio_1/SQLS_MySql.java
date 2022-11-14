package ad.ejercicios.ejercicio_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 * Objeto con el cual generamos una conexión a mysql
 *
 */
public class SQLS_MySql {
	
	final private String user = "root";
	final private String pass = "root";
	final private String host = "localhost:3306/alumnos";
	final private String url = "jdbc:mysql://"+ host + "?useSSL=false";
	
	////---------------------
	String driver = "com.mysql.jdbc.Driver";	// Declaramos driver a usar
	Connection conn = null;
	/*
	 * Pre:
	 * Post: Método con el cual creamos conexión y mostramos query
	 */
	public void makeQuery() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);	// Creamos nueva conexión
			if(conn != null) {									// Conexión exitosa ?
				System.out.println("Conexion Exitosa");
				Statement stmt = conn.createStatement();
				String select = "select a.id_alumno,a.nombre,a.apellido,c.nombre as \"nameC\",c.Descripccion as \"descC\" "
							  + "from alumno a,clase c "
							  + "where a.id_clase = c.id_clase;"; //"SELECT * FROM alumno"
				ResultSet rs = stmt.executeQuery(select);
				System.out.printf("| %-10s | %-10s | %-10s | %-10s  %-32s |%n",
									"ID","Name","Last Name","Class","Desc Class");
				while (rs.next()) {								//Recorremos datos
					long id = rs.getLong("id_alumno");
					String name = rs.getString("nombre");
					String lname = rs.getString("apellido");
					String nameClass = rs.getString("nameC");
					String descClass = rs.getString("descC");
					System.out.printf("| %-10s | %-10s | %-10s | %-10s  %-32s |%n"	// Mostramos query
									  ,id , name , lname, nameClass,descClass);
				}
				rs.close();
			}else {
				System.out.println("Conexion Fallida");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Pre:
	 * Post: Método con el cual cerramos conexiones
	 */
	public void close() {	//Cerramos conexiones
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {}
	}
	
}
