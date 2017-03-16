/*  
 *
 * 
 * @version   : DbConnection.java,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */

package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public Connection con;

	/*
	 * fuction : connect to the database
	 * 
	 * @return : none
	 */
	public void connect() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connect to oracle database with username and password
			con = DriverManager.getConnection(url, "SYSTEM", "healthaas");
			System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {

		try {
			con.close();
			System.out.println("disconnected from DB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}