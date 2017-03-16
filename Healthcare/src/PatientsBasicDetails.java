/*  
 *
 * 
 * @version   : PatientsBasicDetails.java,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */

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
 * Servlet implementation class PatientsBasicDetails
 */
@WebServlet("/PatientsBasicDetails")
public class PatientsBasicDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientsBasicDetails() {
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
		DbConnection db = new DbConnection();
		db.connect();
		String admin = request.getParameter("empId");
		String patientFirstName = request.getParameter("firstName");
		String patientLastName = request.getParameter("lastName");
		String patientApt = request.getParameter("city");
		String patientStreet = request.getParameter("street");
		String patientCity = request.getParameter("city");
		String patientState = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String docId = request.getParameter("docId");
		int patientZipcode= Integer.parseInt(zipcode);
		
		
		PreparedStatement preparedStatement = null;
		int maxId = 1;
		String selectMax = "select count(patientID) as maxPatientId from patient order by patientid";
		try {
			preparedStatement = db.con.prepareStatement(selectMax);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maxIdStr = rs.getString("maxpatientid");
				maxId = Integer.parseInt(maxIdStr);
				maxId = maxId + 1;
				System.out.println("MaxPatient:" +maxId);
			}
			String insertIntoTable;
			System.out.println("Is is" +admin);
			if (admin.equals("admin")) {
				System.out.println("yes admin" +maxId);
				String bloodgroup = request.getParameter("bloodgroup");
				String allergy = request.getParameter("allergy");
				String age = request.getParameter("age");
				int patientAge= Integer.parseInt(age);

				insertIntoTable = "INSERT INTO Patient(PatientID, firstname, lastname, apartment, street, city, state, zipcode,age, bloodgroup, allergy) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

				preparedStatement = db.con.prepareStatement(insertIntoTable);
				preparedStatement.setString(1, maxId + "");
				preparedStatement.setString(2, patientFirstName);
				preparedStatement.setString(3, patientLastName);
				preparedStatement.setString(4, patientApt);
				preparedStatement.setString(5, patientStreet);
				preparedStatement.setString(6, patientCity);
				preparedStatement.setString(7, patientState);
				preparedStatement.setInt(8, patientZipcode);
				preparedStatement.setInt(9, patientAge);
				preparedStatement.setString(10, bloodgroup);
				preparedStatement.setString(11, allergy);
				preparedStatement.executeUpdate();

			} else {
				insertIntoTable = "INSERT INTO Patient(PatientID, firstname, lastname, apartment, street, city, state, zipcode) VALUES(?,?,?,?,?,?,?,?)";
				System.out.println("inserting recep");
				PreparedStatement preparedStatement2  = db.con.prepareStatement(insertIntoTable);
				preparedStatement2.setString(1, maxId + "");
				preparedStatement2.setString(2, patientFirstName);
				preparedStatement2.setString(3, patientLastName);
				preparedStatement2.setString(4, patientApt);
				preparedStatement2.setString(5, patientStreet);
				preparedStatement2.setString(6, patientCity);
				preparedStatement2.setString(7, patientState);
				preparedStatement2.setInt(8, patientZipcode);
				preparedStatement2.executeUpdate();
			}
			int maxTId = 1;
			selectMax = "select count(t_id) as maxtid from treatment order by t_id";

			preparedStatement = db.con.prepareStatement(selectMax);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String maxIdStr = rs.getString("maxtid");
				maxTId = Integer.parseInt(maxIdStr);
				maxTId = maxTId + 1;
			}
			String insertIntoTreatment = "INSERT INTO Treatment(T_ID,patientid_id, Doc_ID) VALUES(?,?,?)";
			preparedStatement = db.con.prepareStatement(insertIntoTreatment);
			preparedStatement.setString(1, maxTId + "");
			preparedStatement.setString(2, maxId + "");
			preparedStatement.setString(3, docId);
			preparedStatement.executeUpdate();
			response.getWriter().print("Successfully inserted patient's details");

		} catch (SQLException e) {

		}

	}
}
