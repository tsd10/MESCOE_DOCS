

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class booking
 */
@WebServlet("/booking")
public class booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public booking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_Url = "jdbc:mysql://localhost/ds";
		final String USER = "root";
		final String PASS = "omkar_";
		int tcost=0;
		int charges = 0;
				response.setContentType("text/html");
				PrintWriter out= response.getWriter();
				String source = request.getParameter("start");
				String destination = request.getParameter("end");
				String type = request.getParameter("type");
				String jdate = request.getParameter("jdate");
				String timing = request.getParameter("timing");
				int seats = Integer.parseInt(request.getParameter("seats"));
				HttpSession session =request.getSession(false);
				String uname=(String) session.getAttribute("uname");
				session.setAttribute("source", source);
				session.setAttribute("destination", destination);
				session.setAttribute("jdate", jdate);
				session.setAttribute("time", timing);
				session.setAttribute("class", type);
				session.setAttribute("seats", seats);
				session.setAttribute("tcost", "3000");
				int id = (int) session.getAttribute("ID");
				Cookie ck[]=request.getCookies();
				String name = ck[0].getValue();
				System.out.println(source);
				
				try {
					
					Class.forName(JDBC_Driver);
					Connection con= DriverManager.getConnection(DB_Url,USER,PASS);
					PreparedStatement p;
					p = con.prepareStatement("select * from airline where uname= ? and upass=?");
					p.setString(1, source);
					p.setString(2, destination);
					ResultSet rs = p.executeQuery();
					if(rs.next())
					{
						 charges = rs.getInt("charges");
						 System.out.println(charges);
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				charges=1000;
				if(type.matches("business"))
				{
					tcost=(int) (1.5*charges*seats);
					System.out.println(tcost);
				}
				else
				{
					tcost=(int) (charges*seats);
					System.out.println(tcost);
				}
				
					out.print("<html>\n" + 
							"<head>\n" + 
							"<meta charset=\"UTF-8\">\n" + 
							"<title>Insert title here</title>\n" + 
							"</head>\n" + 
							"<body  background=\"sky1.jpg\">\n" + 
							"\n" + 
							"<marquee Direction=\"right\"><b>ONLINE AIRLINE RESERVATION SYSTEM</b><img height=\"36\" src=\"airplane.png\"></marquee>\n" + 
							"<form action=\"confirm\" method=\"GET\">\n" + 
							"<table>\n" + 
							"		<tr><td>Your Starting Location</td>\n" + 
							"			<td>"+source+"</td>\n" + 
							"		</tr>\n" + 
							"		<tr><td>Your Destination Location</td>\n" + 
							"			<td>"+destination+"</td>\n" + 
							"		</tr>\n" + 
							"		<tr><td>Total Number of seats</td>\n" + 
							"			<td>"+seats+"</td>\n" + 
							"		</tr>\n" + 
							"		<tr><td>Selected Flight Class</td>\n" + 
							"			<td>"+type+"</td>\n" + 
							"		</tr>\n" + 
							"		<tr><td>Total Charges in Rs</td>\n" + 
							"			<td>"+tcost+"</td>\n" + 
							"		</tr>\n" + 
							"			<tr>DO YOU WANT TO CONTINUE<input type=\"submit\" value=\"confirm\"></tr> 			\n" + 
							"	</table>\n" + 
							"	\n" + 
							"</form>\n" + 
							"</body>\n" + 
							"</html>");
				


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
