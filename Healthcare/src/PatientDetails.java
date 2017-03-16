/*  
 *
 * 
 * @version   : PatientDetails.java,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseConnection.DbConnection;
import DatabaseConnection.Treatment;

/**
 * Servlet implementation class PatientDetails
 */
@WebServlet("/PatientDetails")
public class PatientDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 *
	 */
	public PatientDetails() {
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
		try {
			HttpSession sess = request.getSession(true);
			DbConnection db = new DbConnection();
			db.connect();
			String empID = request.getParameter("empId");
			String selectSQL = "";
			PreparedStatement ps;
			System.out.println("What is it" + empID);
			if (empID.equals("admin")) {
				selectSQL = "select * from TreatmentNDPatient";
				ps = db.con.prepareStatement(selectSQL);
			} else {
				String selectRole = "select * from employee where empid=?";
				ps = db.con.prepareStatement(selectRole);
				ps.setString(1, empID);
				ResultSet rs = ps.executeQuery();
				String role = "";
				if (rs.next()) {
					role = rs.getString("role");
				}
				if (role.equals("nurse")) {
					String selectSupervisor = "select * from nurse where n_id=?";
					ps = db.con.prepareStatement(selectSupervisor);
					ps.setString(1, empID);
					rs = ps.executeQuery();
					String supervisor = "";
					if (rs.next()) {
						supervisor = rs.getString("SUPERVISER_ID");
						System.out.println("supervisor" + supervisor);
					}
					selectSQL = "select * from TreatmentNDPatient where DOC_ID=?";
					ps = db.con.prepareStatement(selectSQL);
					ps.setString(1, supervisor);

				} else {
					selectSQL = "select * from TreatmentNDPatient where DOC_ID=?";
					ps = db.con.prepareStatement(selectSQL);
					ps.setString(1, empID);
				}
			}

			ResultSet rs = ps.executeQuery();
			HashMap<String, Treatment> treatment = new HashMap<String, Treatment>();
			String patientId;
			Date dateFrom;
			Date dateTo;
			String desc;
			String treatmentID;
			String firstName;
			String lastName;
			String age;
			String bloodgroup;
			String allergy;

			while (rs.next()) {
				Treatment details = new Treatment();
				treatmentID = rs.getString("T_ID");
				patientId = rs.getString("PATIENTID_ID");
				dateFrom = rs.getDate("DateFrom");
				dateTo = rs.getDate("DateTo");
				desc = rs.getString("TREATMENTDESC");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastname");
				age = rs.getString("age");
				bloodgroup = rs.getString("bloodgroup");
				allergy = rs.getString("allergy");
				details.setPatientID(patientId);
				details.setTreatmentId(treatmentID);
				details.setDateFrom(dateFrom);
				details.setDateTo(dateTo);
				details.setDescription(desc);
				details.setFirstName(firstName);
				details.setLastName(lastName);
				details.setAge(age);
				details.setBloodgroup(bloodgroup);
				details.setAllergy(allergy);
				treatment.put(treatmentID, details);
			}
			String url = "/viewPatientDetails.jsp?lookup=yes";
			ServletContext sc = getServletContext();
			sess.setAttribute("resultset", treatment);
			response.sendRedirect("viewPatientDetails.jsp?lookup=yes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession sess = request.getSession(true);
			DbConnection db = new DbConnection();
			db.connect();
			String empID = request.getParameter("empId");
			System.err.println(empID);
			PreparedStatement ps;
			String selectSQL;
			String patientFirstName = request.getParameter("patientName");
			if (empID.equals("admin")) {
				selectSQL = "select * from TreatmentNDPatient where firstname=?";
				ps = db.con.prepareStatement(selectSQL);
				ps.setString(1, patientFirstName);
			} else {
				
				String selectRole = "select * from employee where empid=?";
				ps = db.con.prepareStatement(selectRole);
				ps.setString(1, empID);
				ResultSet rs = ps.executeQuery();
				String role = "";
				if (rs.next()) {
					role = rs.getString("role");
				}
				if (role.equals("nurse")) {
					String selectSupervisor = "select * from nurse where n_id=?";
					ps = db.con.prepareStatement(selectSupervisor);
					ps.setString(1, empID);
					rs = ps.executeQuery();
					String supervisor = "";
					if (rs.next()) {
						supervisor = rs.getString("SUPERVISER_ID");
						System.out.println("supervisor" + supervisor);
					}
					selectSQL = "select * from TreatmentNDPatient where DOC_ID=? AND firstname=?";
					ps = db.con.prepareStatement(selectSQL);
					ps.setString(1, supervisor);
					ps.setString(2, patientFirstName);

				} else {
					selectSQL = "select * from TreatmentNDPatient where DOC_ID=? AND firstname=?";
					ps = db.con.prepareStatement(selectSQL);
					ps.setString(1, empID);
					ps.setString(2, patientFirstName);
				}
/*				selectSQL = "select * from TreatmentNDPatient where DOC_ID=? AND firstname=?";
				ps = db.con.prepareStatement(selectSQL);
				ps.setString(1, empID);
				ps.setString(2, patientFirstName);*/
			}
			ResultSet rs = ps.executeQuery();
			HashMap<String, Treatment> treatment = new HashMap<String, Treatment>();
			String patientId;
			Date dateFrom;
			Date dateTo;
			String desc;
			String treatmentID;
			String firstName;
			String lastName;
			String age;
			String bloodgroup;
			String allergy;

			while (rs.next()) {
				Treatment details = new Treatment();
				treatmentID = rs.getString("T_ID");
				patientId = rs.getString("PATIENTID_ID");
				dateFrom = rs.getDate("DateFrom");
				dateTo = rs.getDate("DateTo");
				desc = rs.getString("TREATMENTDESC");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastname");
				age = rs.getString("age");
				bloodgroup = rs.getString("bloodgroup");
				allergy = rs.getString("allergy");
				details.setPatientID(patientId);
				details.setTreatmentId(treatmentID);
				details.setDateFrom(dateFrom);
				details.setDateTo(dateTo);
				details.setDescription(desc);
				details.setFirstName(firstName);
				details.setLastName(lastName);
				details.setAge(age);
				details.setBloodgroup(bloodgroup);
				details.setAllergy(allergy);
				treatment.put(treatmentID, details);
			}

			// dispatch the request to the jsp page
			sess.setAttribute("resultset", treatment);
			response.sendRedirect("viewPatientDetails.jsp?lookup=yes");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
