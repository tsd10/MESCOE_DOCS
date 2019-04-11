

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ReplicationMySQLConnection;

/**
 * Servlet implementation class re
 */
@WebServlet("/re")
public class re extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public re() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(false);
		int id = (int) session.getAttribute("ID");
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_Url = "jdbc:mysql://localhost/ds";
		final String USER = "root";
		final String PASS = "omkar_";
		String date=request.getParameter("jdate");
		String timing = request.getParameter("timing");
		
		try {
			Class.forName(JDBC_Driver);
			Connection con= DriverManager.getConnection(DB_Url,USER,PASS);
			PreparedStatement p;
			p = con.prepareStatement("update booking SET jdate=?,time=? where id=? ");
			p.setString(1,date);
			p.setString(2, timing);
			p.setInt(3, id);
			p.executeUpdate();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<html>\n" + 
	        		"<head>\n" + 
	        		"<meta charset=\"UTF-8\">\n" + 
	        		"<title>Insert title here</title>\n" + 
	        		"</head>\n" + 
	        		"<body  background=\"sky1.jpg\">\n" + 
	        		"\n" + 
	        		"<marquee Direction=\"right\"><b>ONLINE AIRLINE RESERVATION SYSTEM</b><img height=\"36\" src=\"airplane.png\"></marquee>\n" + 
	        		"       <div align=\"center\">\n" + 
	        		"	You Have Successfully Reshedule Your Journey<br>\n" + 
	        		"	There is Successful ID generation for resheduling\n" + 
	        		"<br>Your Booking ID is"+id+
	        		"	</div>\n" + 
	        		"</form>\n" +
	        		"<a href=\"logout\"> LOGOUT </a>"+
	        		"</body>\n" + 
	        		"</html>");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
