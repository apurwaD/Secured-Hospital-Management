

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class UpdateDetails
 */
@WebServlet("/UpdateDetails")
public class UpdateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// TODO Auto-generated method stub
			try {
				HttpSession sess=request.getSession(true);
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
					selectSQL = "select * from TreatmentNDPatient where DOC_ID=? AND firstname=?";
					ps = db.con.prepareStatement(selectSQL);
					ps.setString(1, empID);
					ps.setString(2, patientFirstName);
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
					if(rs.getDate("DateFrom")!=null){
						dateFrom = rs.getDate("DateFrom");
					}
					else{
						java.util.Calendar calenderDate = java.util.Calendar.getInstance();
						java.util.Date utilDate = calenderDate.getTime();
						java.sql.Date sqlDate = new Date(utilDate.getTime());
						dateFrom=sqlDate;
					}
					if(rs.getDate("DateFrom")!=null){
						dateTo = rs.getDate("DateTo");
					}
					else{
						java.util.Calendar calenderDate = java.util.Calendar.getInstance();
						java.util.Date utilDate = calenderDate.getTime();
						java.sql.Date sqlDate = new Date(utilDate.getTime());
						dateTo=sqlDate;
					}
					if(rs.getString("TREATMENTDESC")!=null){
						desc = rs.getString("TREATMENTDESC");
					}
					else{
						desc="";
					}
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
				String url = "/updateDoctorPage.jsp";
				// dispatch the request to the jsp page
				ServletContext sc = getServletContext();
				//RequestDispatcher rd = sc.getRequestDispatcher(url);
				//request.setAttribute("resultset", treatment);
				//rd.forward(request, response);
				sess.setAttribute("resultset",treatment);
				response.sendRedirect("updateDoctorPage.jsp");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
			try {
				DbConnection db = new DbConnection();
				db.connect();
				String patientId = request.getParameter("patientId");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String dateFrom = request.getParameter("dateFrom");
				String dateTo = request.getParameter("dateTo");
				String treatMent = request.getParameter("treatmentDesc");
				String bloodGroup = request.getParameter("bloodGroup");
				String allergy = request.getParameter("allergy");
				String age = request.getParameter("age");
				int ageInt=Integer.parseInt(age);
				String tid= request.getParameter("treatmentId");
				System.out.println("Date" +dateFrom);
				
				 SimpleDateFormat smFormat = new SimpleDateFormat("yyyy-MM-dd");
			        java.util.Date parsed = (java.util.Date) smFormat.parse(dateFrom);
			        System.out.println(parsed.toString());
			        java.sql.Date sqldateFrom = new java.sql.Date(parsed.getTime());
			        
			         parsed = (java.util.Date) smFormat.parse(dateTo);
			        java.sql.Date sqldateTo = new java.sql.Date(parsed.getTime());
				
				String updateSQLtreatment = "UPDATE treatment SET dateFrom = ?, dateTo=?, treatmentdesc=? WHERE T_ID = ?";
				PreparedStatement preparedStatement = db.con.prepareStatement(updateSQLtreatment);
				preparedStatement.setDate(1, sqldateFrom);
				preparedStatement.setDate(2, sqldateTo);
				preparedStatement.setString(3, treatMent);
				preparedStatement.setString(4, tid);
				preparedStatement .executeUpdate();
				
				String updateSQLpatient = "UPDATE patient SET firstName = ?, lastName =?,age=?, bloodgroup=?,allergy=? WHERE patientid = ?";
				preparedStatement = db.con.prepareStatement(updateSQLpatient);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setInt(3, ageInt);
				preparedStatement.setString(4, bloodGroup);
				preparedStatement.setString(5, allergy);
				preparedStatement.setString(6, patientId);
				preparedStatement .executeUpdate();
				response.getWriter().print("Successfully updated patient's details");
			} catch (ParseException e) {
				response.getWriter().print("Enter date in YYYY-MM-DD format");
			}
			catch (Exception e) {
				response.getWriter().print("Error encountered while updating details");
			}
		
	}

}
