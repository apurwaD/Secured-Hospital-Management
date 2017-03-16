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
import javax.servlet.http.HttpSession;

import sun.security.util.Password;
import DatabaseConnection.DbConnection;
import DatabaseConnection.Encryption;

/**
 * Servlet implementation class UpdateOwnDetails
 */
@WebServlet("/UpdateOwnDetails")
public class UpdateOwnDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateOwnDetails() {
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
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(true);
		DbConnection db = new DbConnection();
		Encryption en = new Encryption();
		db.connect();
		response.setContentType("text/plain");
		String empId = request.getParameter("empId");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String confirmpassword = request.getParameter("confirmpassword");

		System.out.println("id" + empId + " password" + oldpassword);
		String selectSQL = "select * from EMPLOYEE where empId=?";
		PreparedStatement ps;
		try {
			ps = db.con.prepareStatement(selectSQL);
			ps.setString(1, empId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String storedPassword = rs.getString("password");
				String username = rs.getString("username");
				String decryptPassword = en.decryptUser(username,
						storedPassword);
				System.out.println("decrupted password :" + decryptPassword);
				if (oldpassword.equals(decryptPassword)) {
					String updatePassword = "UPDATE employee SET password = ? WHERE empid = ?";
					PreparedStatement preparedStatement = db.con
							.prepareStatement(updatePassword);
					newpassword=en.encryptAdmin(username, newpassword);
					preparedStatement.setString(1, newpassword);
					preparedStatement.setString(2, empId);
					preparedStatement.executeUpdate();
					response.getWriter().print("Successfully updated password");
				} else {
					sess.setAttribute("empId", empId);
					response.sendRedirect("invalidpassword.jsp");
				}
			} else {
				sess.setAttribute("empId", empId);
				response.sendRedirect("invalidpassword.jsp");
			}
		} catch (SQLException e) {
			response.getWriter().print("Error occured while creating employee record");
			e.printStackTrace();
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
