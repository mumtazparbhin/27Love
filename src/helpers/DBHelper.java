package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Measurement;

public class DBHelper {
	static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	static final String JDBC_URL = "jdbc:derby:27thLoveDB;create=true";
	static Connection con = null;
	
	static {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(JDBC_URL);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean initializeDB(){
		try {	
			con.createStatement().execute("create table customer(fname varchar(30), lname varchar(30), unique_name varchar(30), phone varchar(30), measurement_date varchar(30))");
			con.createStatement().execute("create table customer_order (unique_name varchar(30), item varchar (45), order_date varchar(30), delivery_date varchar(30), cost varchar(20), amount_paid varchar(20))");
			con.createStatement().execute("create table measurement(name varchar(30))");
			System.out.println("Tables created succesfully");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void addMeasurements(Measurement m){
		try {
			PreparedStatement st = con.prepareStatement("insert into measurement values (?)");
			st.setString(1, m.getName());
			st.execute();
			System.out.println("Measurements added successfully to the table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
