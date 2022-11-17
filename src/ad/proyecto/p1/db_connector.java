package ad.proyecto.p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db_connector {

	
	/*
	 * Pre:
	 * Post: Método con el cual creamos conexión y mostramos query
	 */
	public void makeQuery() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
			  "jdbc:mysql://aws-eu-west-2.connect.psdb.cloud/conectors?sslMode=VERIFY_IDENTITY",
			  "n0tlys88islald25vpiw",
			  "	pscale_pw_13HBBZFSdK8RH3hTb8GXd0rmzSQRHrc8ScBMxgqVA33");

			if(conn != null) {									// Conexión exitosa ?
				System.out.println("Conexion Exitosa");
				Statement stmt = conn.createStatement();
				String select = "select * "
							  + "from clientes "; //"SELECT * FROM alumno"
				
				/*
				 * 	`id` int NOT NULL AUTO_INCREMENT,
	`Nombre` varchar(45),
	`Apellido` varchar(45),
	`CIF` varchar(45),
	`id_empresa` int NOT NULL,
				 */
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
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {}
			}else {
				System.out.println("Conexion Fallida");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
