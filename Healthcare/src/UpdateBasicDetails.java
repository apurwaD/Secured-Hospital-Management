

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseConnection.DbConnection;

/**
 * Servlet implementation class UpdateBasicDetails
 */
@WebServlet("/UpdateBasicDetails")
public class UpdateBasicDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBasicDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DbConnection db = new DbConnection();
		db.connect();
		String patientId = request.getParameter("patientId");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String apt = request.getParameter("apt");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		int zipcodeInt= Integer.parseInt(zipcode);
		
		String updateSQLpatient = "UPDATE patient SET firstname = ?, lastname=?, apartment=?,street=?, city=?, state=?,zipcode=? WHERE patientid = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = db.con.prepareStatement(updateSQLpatient);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, apt);
			preparedStatement.setString(4, street);
			preparedStatement.setString(5, city);
			preparedStatement.setString(6, state);
			preparedStatement.setInt(7, zipcodeInt);
			preparedStatement.setString(8, patientId);
			preparedStatement .executeUpdate();
			response.getWriter().print("Successfully updated patient's details");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
