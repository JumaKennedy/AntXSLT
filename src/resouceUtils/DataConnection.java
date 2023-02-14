package resouceUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.mysql.jdbc.Statement;

public class DataConnection {
	
	public DataConnection() {
		super();
	}
	
	// for oracle TNS connection using Oracle_home variable
	public static void setTnsAdmin() {
	    String tnsAdmin = System.getenv("TNS_ADMIN");
	    if (tnsAdmin == null) {
	        String oracleHome = System.getenv("ORACLE_HOME");
	        if (oracleHome == null) {
	            return; //failed to find any useful env variables
	        }
	        tnsAdmin = oracleHome + File.separatorChar + "network" + File.separatorChar + "admin";
	    }
	    System.setProperty("oracle.net.tns_admin", tnsAdmin);
	    //System.out.println("oracle_home is :: " + tnsAdmin);
	}

	public static Connection getConnection() {
		//System.setProperty("oracle.net.tns_admin", "C:/Oracle11g32bit/product/11.2.0/client_1/network/admin");
		//setTnsAdmin();
	    //String dbURL = "jdbc:oracle:thin:FDBDBA@//eagnmnmed15b:1521/tfdb.usps.gov";
		String data_base = "my_db";
		String dbURL = "jdbc:mysql://localhost:3306/"+data_base+"";
	    
	    try {
            
			//Class.forName("oracle.jdbc.driver.OracleDriver");
	    	Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Missing Oracle JDBC Driver");
			e.printStackTrace();
			
		}

		System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(dbURL,"root", "Adm1n");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			//return;

		}

		if (connection != null) {
			System.out.println("You made it, you are now connected to MYSQL @ " + data_base);
			 return connection;
		} else {
			System.out.println("Failed to make database connection!");
		}
		return connection;
	}
	
}