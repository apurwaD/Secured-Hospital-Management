

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseConnection.DbConnection;

/**
 * Servlet implementation class AdminaddBasic
 */
@WebServlet("/AdminaddBasic")
public class AdminaddBasic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminaddBasic() {
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
		// TODO Auto-generated method stub
				DbConnection db = new DbConnection();
				db.connect();
				
				String patientFirstName = request.getParameter("firstName");
				String patientLastName = request.getParameter("lastName");
				String patientApt = request.getParameter("city");
				String patientStreet = request.getParameter("street");
				String patientCity= request.getParameter("city");
				String patientState = request.getParameter("state");
				String patientZipcode = request.getParameter("zipcode");
				String docId= request.getParameter("docId");
				
				PreparedStatement preparedStatement = null;
				int maxId=1;
				String selectMax ="select max(patientID) as maxPatientId from patient";
				try {
					preparedStatement= db.con.prepareStatement(selectMax);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						String maxIdStr=rs.getString("maxpatientid");
						maxId=Integer.parseInt(maxIdStr);
						maxId=maxId+1;
					}
				
				String insertIntoTable = "INSERT INTO Patient(PatientID, firstname, lastname, apartment, street, city, state, zipcode) VALUES(?,?,?,?,?,?,?,?)";
				
					preparedStatement = db.con.prepareStatement(insertIntoTable);
					preparedStatement.setString(1, maxId+"");
					preparedStatement.setString(2, patientFirstName);
					preparedStatement.setString(3, patientLastName);
					preparedStatement.setString(4, patientApt);
					preparedStatement.setString(5, patientStreet);
					preparedStatement.setString(6, patientCity);
					preparedStatement.setString(7, patientState);
					preparedStatement.setString(8, patientZipcode);
					preparedStatement.executeUpdate();
					
					int maxTId=1;
					 selectMax ="select max(t_id) as maxtid from treatment";

							preparedStatement= db.con.prepareStatement(selectMax);
							 rs = preparedStatement.executeQuery();
							if(rs.next()){
								String  maxIdStr=rs.getString("maxtid");
								maxTId=Integer.parseInt(maxIdStr);
								maxTId=maxId+1;
							}
					String insertIntoTreatment = "INSERT INTO Treatment(T_ID,patientid_id, Doc_ID) VALUES(?,?,?)";
					preparedStatement = db.con.prepareStatement(insertIntoTreatment);
					preparedStatement.setString(1, maxTId+"");
					preparedStatement.setString(2, maxId+"");
					preparedStatement.setString(3, docId);
					preparedStatement.executeUpdate();
					
					response.getWriter().print("Successfully inserted patient's details");
					
					}catch(SQLException e)	{
						
					}

	}

}
