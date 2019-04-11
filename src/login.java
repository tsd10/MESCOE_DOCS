

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_Url = "jdbc:mysql://localhost/ds";
		final String USER = "root";
		final String PASS = "omkar_";
		try {
			Class.forName(JDBC_Driver);
			Connection con= DriverManager.getConnection(DB_Url,USER,PASS);
			PreparedStatement p;
			p = con.prepareStatement("select * from airline where uname= ? and upass=?");
			p.setString(1, uname);
			p.setString(2, upass);
			ResultSet rs = p.executeQuery();
			if(rs.next())
			{
					String name =rs.getString("name");
					int id = rs.getInt("ID");
					Cookie ck = new Cookie("name",uname);
					response.addCookie(ck);
					HttpSession session=request.getSession(true);
					session.setAttribute("uname", uname);
					session.setAttribute("ID",id);
						//Connection cond=DriverManager.getConnection("jdbc:mysql://localhost:3306/ds","root","omkar_");
					 	java.util.Date date=new java.util.Date();
			            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			            java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
			            session.setAttribute("da", sqlDate);
						session.setAttribute("login",sqlTime);
			            //PreparedStatement ps=con.prepareStatement("insert into log (id,da,login) values(?,?,?)");
			            //ps.setInt(1,id);
			            //ps.setDate(2,sqlDate);
			            //ps.setTimestamp(2,sqlTime);
			            //ps.executeUpdate();     
			            
					 
				 	out.print("<html>\n" + 
				 			"<head>\n" + 
				 			"<meta charset=\"UTF-8\">\n" + 
				 			"<title>Insert title here</title>\n" + 
				 			"</head>\n" + 
				 			"<body  background=\"sky1.jpg\">\n" + 
				 			"\n" + 
				 			"<marquee Direction=\"right\"><b>ONLINE AIRLINE RESERVATION SYSTEM</b><img height=\"36\" src=\"airplane.png\"></marquee>\n" + 
				 			"	<table align=\"center\">\n" + 
				 			"		<div align=\"top-left\">\n" + 
				 			"			WELCOME .. \n" + 
				 			"		</div>\n" + 
				 			"		<div align=\"right\">\n" + 
				 			"		<form action=\"logout\" method=\"get\">\n" + 
				 			"			LOGOUT<input type=\"submit\" value=\"LOG OUT\">\n" + 
				 			"		</form>\n" + 
				 			"		</div>\n" + 
				 			"		<div align=\"center\">\n" + 
				 			"			<a href=\"booking.html\">GO FOR THE BOOKING !!!AND ENJOY THE HOLIDAY !!! WE ARE HERE TO MAKE YOUR HOLIDAYS BEAUTIFUL</a></br>\n" + 
				 			"			<a href=\"cancelation.html\">ANY PROBLEM !!! OK !!GO FOR THE CANCELATION </a></br>\n" + 
				 			"			<a href=\"re.html\">ARE YOU IN HURRY! RESHEDULE IT </a></br> \n" + 
				 			"		</div>\n" + 
				 			"		\n" + 
				 			"	</table>\n" + 
				 			"	\n" + 
				 			"	\n" + 
				 			"​\n" + 
				 			"</body>\n" + 
				 			"</html>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			

			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
