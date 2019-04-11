

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUP
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("na");
		String uname=request.getParameter("una");
		String upass=request.getParameter("upa");
		String mobile=request.getParameter("umo");
		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	     final String DB_URL="jdbc:mysql://localhost/ds";
	     final String USER = "root";
	     final String PASS = "omkar_";
	     response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	     
	     try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement s;
			s=conn.prepareStatement("insert into airline(name,uname,upass) values(?,?,?)");
			s.setString(1, name);
			s.setString(2, uname);
			s.setString(3, upass);
			int i=s.executeUpdate();
			out.print("\n" + 
					"<html>\n" + 
					"<head>\n" + 
					"<meta charset=\"UTF-8\">\n" + 
					"<title>Insert title here</title>\n" + 
					"</head>\n" + 
					"<body  background=\"sky1.jpg\">\n" + 
					"\n" + 
					"<marquee Direction=\"right\"><b>ONLINE AIRLINE RESERVATION SYSTEM</b><img height=\"36\" src=\"airplane.png\"></marquee>\n" + 
					"	<table align=\"center\">\n" + 
					"		<tr>\n" + 
					"			<td> WELCOME TO WORLDS BEST ONLINE AIRLINE RESERVATION SYSTEM </td>\n" + 
					"			<td> HAPPY JOURNEY!!! </td>\n" + 
					"		</tr>\n" + 
					"		\n" + 
					"	</table>\n" + 
					"		<table align=\"center\">\n" + 
					"		<tr>\n" + 
					"			<td>LOGIN FOR FLIGHT BOOKING</td>\n" + 
					"			<td> <a href=\"index.html\"> LOGIN</a></td>\n" + 
					"		</tr>\n" + 
					"		\n" + 
					"	</table>\n" + 
					"	\n" + 
					"	\n" + 
					"​\n" + 
					"</body>\n" + 
					"</html>");
	     
	     } 
	     catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}

	     
	}

}
