package ad.ejercicios.ejercicio_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class SQLS_Microsoft {
	
	
	private Connection connect = null;
	private ResultSet resultSet = null;

	//// ---------------------
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public void makeQuery() {
		Connection conn = null;
		try {
			Class.forName(driver);
			String connectionUrl = // DESKTOP-RAHMIUV:1433
					"jdbc:sqlserver://localhost:1433;" 
							+ "databaseName=alumnos;"
							+ "encrypt=false;" 
							+ "user=sa;" 
							+ "password=123456;"
							+ "loginTimeout=30";
			conn = DriverManager.getConnection(connectionUrl);
			if (conn != null) {
				System.out.println("Conexion Exitosa");
				Statement stmt = conn.createStatement();
				String select = "select a.id_alumno,a.nombre,a.apellido,c.nombre as \"nameC\",c.Descripccion as \"descC\" "
						+ "from alumno a,clase c " + "where a.id_clase = c.id_clase;"; // "SELECT * FROM alumno"
				ResultSet rs = stmt.executeQuery(select);
				System.out.printf("| %-10s | %-10s | %-10s | %-10s  %-32s |%n", "ID", "Name", "Last Name", "Class",
						"Desc Class");
				while (rs.next()) {
					long id = rs.getLong("id_alumno");
					String name = rs.getString("nombre");
					String lname = rs.getString("apellido");
					String nameClass = rs.getString("nameC");
					String descClass = rs.getString("descC");
					System.out.printf("| %-10s | %-10s | %-10s | %-10s  %-32s |%n", id, name, lname, nameClass,
							descClass);
				}
			} else {
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
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
		}
	}
}
