package ad.ejercicios.ejercicio_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
public class SQLS_Microsoft {
	
	
	final private String user = "clara";
	final private String pass = "root";
	final private String host = "localhost";//DESKTOP-RAHMIUV
	final private String db = "alumnos";
	final private String url = "jdbc:sqlserver//"+ host + 
					";encrypt=true;databaseName="+db;
	private Connection connect = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;
	
	////---------------------
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public void makeQuery() {
		Connection conn = null;
		try {
			Class.forName(driver);
			  String connectionUrl = // :1433
		                "jdbc:sqlserver://DESKTOP-RAHMIUV/USUARIO:1433;databaseName=alumnos;integratedSecurity=true";
		                //+ "database=alumnos;"
		                //+ "user=clara;"
		                //+ "password=root;";
			  System.out.println("XDD");
			  conn = DriverManager.getConnection(connectionUrl);
			  System.out.println("pasa");
			/*
			System.out.println("aaaaa");
			
			SQLServerDataSource ds = new SQLServerDataSource();  
			ds.setUser("clara");  
			ds.setPassword("root");  
			ds.setServerName("localhost");  
			ds.setPortNumber(1433);
			ds.setDatabaseName("alumnos");  
			System.out.println("eeee");
			conn = ds.getConnection();
			System.out.println("xxxxxx");
			*/
			//conn = DriverManager.getConnection(url,user,pass);
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
