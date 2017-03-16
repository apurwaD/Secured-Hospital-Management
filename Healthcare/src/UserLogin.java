/*  
 *
 * 
 * @version   : UserLogin.java,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseConnection.DbConnection;
import DatabaseConnection.Encryption;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	Map<String, Integer> noOfInvalid = new HashMap<String, Integer>();
	int noInvalid = 0;
	private static final long serialVersionUID = 1L;
	String sessionId;

	/**
	 * @see HttpServlet#HttpServlet()
	 *
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		sessionId = request.getSession().getId();
		int num = 0;
		String username = request.getParameter("username");
		sessionId = username;
		System.out.println(sessionId + " num: " + num);
		int numAttempts = 0;
		boolean sessionExist = false;

		try {
			DbConnection db = new DbConnection();
			db.connect();
			Encryption en = new Encryption();
			HttpSession sess = request.getSession(true);
			PreparedStatement ps;
			boolean donotallow = false;
			String selectSession = "select * from sessiontrack where sessionid=?";
			ps = db.con.prepareStatement(selectSession);
			ps.setString(1, sessionId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				sessionExist = true;
				numAttempts = rs.getInt("attempts");
				System.out.println("no of wrong attempts"+numAttempts);
				if (numAttempts > 3) {
					Date currentTime = new Date(System.currentTimeMillis());
					Timestamp timestamp = new Timestamp(currentTime.getTime());
					Timestamp timestampDb = rs.getTimestamp("datetime");
					long t1 = timestamp.getTime();
					long t2 = timestampDb.getTime();
					if (t1 < t2) {
						donotallow = true;
					}
					else{
						numAttempts=0;
						Date timePlus10 = new Date(
								System.currentTimeMillis() + 30 * 60 * 1000);
						Timestamp timestamp2 = new Timestamp(timePlus10.getTime());
						if (sessionExist) {
							System.out.println("updating t1<t2"+timePlus10);
							String updateSQLpatient = "UPDATE sessiontrack SET attempts = ?, dateTime=? where sessionid=?";
							ps = db.con.prepareStatement(updateSQLpatient);
							ps.setInt(1, 0);
							ps.setTimestamp(2, timestamp2);
							ps.setString(3, sessionId);
							ps.executeUpdate();
						}
					}
				}
			}

			if (!donotallow) {
				response.setContentType("text/plain");
				String password = request.getParameter("password");
				String newpassword = en.encryptAdmin(username, password);

				String selectSQL = "select * from EMPLOYEE where username=? AND password=?";

				ps = db.con.prepareStatement(selectSQL);
				ps.setString(1, username);
				ps.setString(2, newpassword);
				rs = ps.executeQuery();
				if (rs.next()) {
					Date timePlus10 = new Date(
							System.currentTimeMillis() + 30 * 60 * 1000);
					Timestamp timestamp = new Timestamp(timePlus10.getTime());
					if (sessionExist) {
						System.out.println("updating"+timePlus10);
						String updateSQLpatient = "UPDATE sessiontrack SET attempts = ?, dateTime=? where sessionid=?";
						ps = db.con.prepareStatement(updateSQLpatient);
						ps.setInt(1, 0);
						ps.setTimestamp(2, timestamp);
						ps.setString(3, sessionId);
						ps.executeUpdate();
					}
					System.out.println("successful");
					String id = rs.getString("empId");
					String role = rs.getString("role");
					if (role.equalsIgnoreCase("doctor")) {
						sess.setAttribute("employeeID", id);
						sess.setAttribute("username", username);
						sess.setAttribute("role", "doctor");
						response.sendRedirect("DoctarPage.jsp");

					} else {
						if (role.equalsIgnoreCase("recep")) {
							sess.setAttribute("employeeID", id);
							sess.setAttribute("username", username);
							sess.setAttribute("role", "recep");
							response.sendRedirect("receptionistPage.jsp");
						} else {
							if (role.equalsIgnoreCase("nurse")) {
								sess.setAttribute("employeeID", id);
								sess.setAttribute("username", username);
								sess.setAttribute("role", "nurse");
								response.sendRedirect("nursePage.jsp");

							} else {
								if (role.equalsIgnoreCase("admin")) {
									sess.setAttribute("role", "admin");
									String url = "/adminPage.jsp?username="
											+ username;
									ServletContext sc = getServletContext();
									RequestDispatcher rd = sc
											.getRequestDispatcher(url);
									rd.forward(request, response);
								}
							}
						}
					}

				} else {
					Date timePlus10 = new Date(
							System.currentTimeMillis() + 30 * 60 * 1000);
					Timestamp timestamp = new Timestamp(timePlus10.getTime());
					if (sessionExist) {
						System.out.println("updating"+timePlus10);
						String updateSQLpatient = "UPDATE sessiontrack SET attempts = ?, dateTime=? where sessionid=?";
						ps = db.con.prepareStatement(updateSQLpatient);
						ps.setInt(1, ++numAttempts);
						ps.setTimestamp(2, timestamp);
						ps.setString(3, sessionId);
						ps.executeUpdate();
					} else {
						System.out.println("inserting"+timePlus10);
						String insertIntoTable = "INSERT into sessiontrack(sessionid, attempts, datetime) VALUES(?,?,?)";
						ps = db.con.prepareStatement(insertIntoTable);
						ps.setString(1, sessionId);
						ps.setInt(2, ++numAttempts);
						ps.setTimestamp(3, timestamp);
						ps.executeUpdate();
					}

					response.sendRedirect("invalidUser.jsp");
				}
			} else {
				response.sendRedirect("accessDenied.jsp");
			}
		} catch (SQLException e) {
			response.getWriter().print("Error occured");
			e.printStackTrace();
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			response.getWriter().print("Error occured");
			e.printStackTrace();
		}
	}

}
