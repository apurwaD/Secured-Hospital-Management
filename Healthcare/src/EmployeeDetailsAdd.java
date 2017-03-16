import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseConnection.DbConnection;
import DatabaseConnection.Encryption;

/**
 * Servlet implementation class EmployeeDetailsAdd
 */
@WebServlet("/EmployeeDetailsAdd")
public class EmployeeDetailsAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeDetailsAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
		if (role.equals("doctor")) {
			response.sendRedirect("addEmployeeDoctor.jsp");

		}
		if (role.equals("nurse")) {
			response.sendRedirect("addEmployeeNurse.jsp");

		}
		if (role.equals("recep")) {
			response.sendRedirect("addEmployeeRecep.jsp");
		}
		if (role.equals("admin")) {
			response.sendRedirect("addEmployeeAdmin.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnection db = new DbConnection();
		db.connect();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String role = request.getParameter("role");
		Encryption en = new Encryption();
		try {
		password=en.encryptAdmin(userName, password);
		
		PreparedStatement preparedStatement = null;
		
		String selectuserName= "select * from employee where username=?";
		preparedStatement = db.con.prepareStatement(selectuserName);
		preparedStatement.setString(1, userName);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			response.getWriter().print("username already exist");
		}

		else{
		int maxId = 1;
		String selectMax = "select count(*) as maxempid from employee order by empid";
		
			preparedStatement = db.con.prepareStatement(selectMax);
			 rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maxIdStr = rs.getString("maxempid");
				maxId = Integer.parseInt(maxIdStr);
				maxId = maxId + 1;
				System.out.println("MaxEMP:" + maxId);
			}

			String insertIntoEmployee = "INSERT INTO employee(empid, username, password, role) VALUES(?,?,?,?)";
			preparedStatement = db.con.prepareStatement(insertIntoEmployee);
			preparedStatement.setString(1, "" + maxId);
			preparedStatement.setString(2, userName);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, role);
			preparedStatement.executeUpdate();
			

			if (role.equals("doctor")) {
				String dept = request.getParameter("dept");
				insertIntoEmployee = "INSERT INTO Doctor(d_id, firstname, lastname, department) VALUES(?,?,?,?)";
				preparedStatement = db.con.prepareStatement(insertIntoEmployee);
				preparedStatement.setString(1, "" + maxId);
				preparedStatement.setString(2, firstName);
				preparedStatement.setString(3, lastName);
				preparedStatement.setString(4, dept);
				preparedStatement.executeUpdate();
				response.getWriter().print("Successfully created and entered Doctor's details");
			} else {
				if (role.equals("nurse")) {
					String docID = request.getParameter("docId");
					String dept = request.getParameter("dept");
					insertIntoEmployee = "INSERT INTO Nurse(n_id, firstname, lastname, superviser_id, department) VALUES(?,?,?,?,?)";
					preparedStatement = db.con
							.prepareStatement(insertIntoEmployee);
					preparedStatement.setString(1, "" + maxId);
					preparedStatement.setString(2, firstName);
					preparedStatement.setString(3, lastName);
					preparedStatement.setString(4, docID);
					preparedStatement.setString(5, dept);
					preparedStatement.executeUpdate();
					response.getWriter().print("Successfully created and entered Nurse's details");
				} else {
					if (role.equals("recep")) {
						insertIntoEmployee = "INSERT INTO Receptionist(r_id, firstname, lastname) VALUES(?,?,?)";
						preparedStatement = db.con
								.prepareStatement(insertIntoEmployee);
						preparedStatement.setString(1, "" + maxId);
						preparedStatement.setString(2, firstName);
						preparedStatement.setString(3, lastName);
						preparedStatement.executeUpdate();
						response.getWriter().print("Successfully created and entered Receptionist's details");
					}else{
						if (role.equals("admin")) {
							insertIntoEmployee = "INSERT INTO Admin(a_id, firstname, lastname) VALUES(?,?,?)";
							preparedStatement = db.con
									.prepareStatement(insertIntoEmployee);
							preparedStatement.setString(1, "" + maxId);
							preparedStatement.setString(2, firstName);
							preparedStatement.setString(3, lastName);
							preparedStatement.executeUpdate();
							response.getWriter().print("Successfully created and entered Admin's details");
						}
					}
					
				}
			}
		}
		} catch (SQLException e) {
			response.getWriter().print("Error occured while creating employee record");
			e.printStackTrace();
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchProviderException e1) {
			response.getWriter().print("Error occured while creating employee record");
			e1.printStackTrace();
		}
		
	}

}
