package ad.ejercicios.ejercicio_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;


public class SQLS_MySql {

	
	
	final private String user = "root";
	final private String pass = "root";
	final private String host = "localhost:3306/alumnos";
	final private String url = "jdbc:mysql://"+ host + "?useSSL=false";
	private Connection connect = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	
	////---------------------
	String driver = "com.mysql.jdbc.Driver";
	
	public void makeQuery() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);
			if(conn != null) {
				System.out.println("Conexion Exitosa");
				Statement stmt = conn.createStatement();
				String select = "select a.id_alumno,a.nombre,a.apellido,c.nombre as \"nameC\",c.Descripccion as \"descC\" "
							  + "from alumno a,clase c "
							  + "where a.id_clase = c.id_clase;"; //"SELECT * FROM alumno"
				ResultSet rs = stmt.executeQuery(select);
				System.out.printf("| %-10s | %-10s | %-10s | %-10s  %-32s |%n",
									"ID","Name","Last Name","Class","Desc Class");
				while (rs.next()) {
					long id = rs.getLong("id_alumno");
					String name = rs.getString("nombre");
					String lname = rs.getString("apellido");
					String nameClass = rs.getString("nameC");
					String descClass = rs.getString("descC");
					System.out.printf("| %-10s | %-10s | %-10s | %-10s  %-32s |%n"
									  ,id , name , lname, nameClass,descClass);
				}
			}else {
				System.out.println("Conexion Fallida");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			} if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {}
	}
	
}
