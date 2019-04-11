

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
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class confirm
 */
@WebServlet("/confirm")
public class confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session  = request.getSession(false);
		int id = (int) session.getAttribute("ID");
		String source =(String) session.getAttribute("source");
		String destination =(String) session.getAttribute("destination");
		String jdate =(String) session.getAttribute("jdate");
		String time =(String) session.getAttribute("time");
		String classs =(String) session.getAttribute("class");
		int seats = (int) session.getAttribute("seats");
		String fs= Integer.toString(seats);
		String tcost =(String) session.getAttribute("tcost");
		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	     final String DB_URL="jdbc:mysql://localhost/ds";
	     final String USER = "root";
	     final String PASS = "omkar_";
	     
	     try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement s;
			s=conn.prepareStatement("insert into booking values(?,?,?,?,?,?,?,?)");
			s.setInt(1, id);
			s.setString(2, source);
			s.setString(3, destination);
			s.setString(4, jdate);
			s.setString(5, time);
			s.setString(6, classs);
			s.setString(7, fs);
			s.setString(8, tcost);
			int i=s.executeUpdate();
			
			URL obj = new URL("http://192.168.43.96:8080/DS/test?id="+id+"&source="+source+"&destination="+destination+"&jdate="+jdate+"&time="+time+"&class="+classs+"&seats="+seats);
	     	URLConnection obj2 = obj.openConnection();
	        InputStream is =obj2.getInputStream();
	        BufferedReader in =new BufferedReader(new InputStreamReader(is));
	        String str;
	        String responseS = null;
	        while((str=in.readLine())!=null)
	        {
	        	responseS+=str+"\n";	
	        }
	        in.close();
	        out.write(responseS);
	        out.print("<html>\n" + 
	        		"<head>\n" + 
	        		"<meta charset=\"UTF-8\">\n" + 
	        		"<title>Insert title here</title>\n" + 
	        		"</head>\n" + 
	        		"<body  background=\"sky1.jpg\">\n" + 
	        		"\n" + 
	        		"<marquee Direction=\"right\"><b>ONLINE AIRLINE RESERVATION SYSTEM</b><img height=\"36\" src=\"airplane.png\"></marquee>\n" + 
	        		"       <div align=\"center\">\n" + 
	        		"	You Have Successfully Booked Your Journey<br>\n" + 
	        		"	There is Successful Payment Transaction from your added account\n" + 
	        		"<br>Your Booking ID is"+id+
	        		"	</div>\n" + 
	        		"</form>\n" +
	        		"<a href=\"logout\"> LOGOUT </a>"+
	        		"</body>\n" + 
	        		"</html>");
	     
	     } 
	     catch (ClassNotFoundException | SQLException e) {
			
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
