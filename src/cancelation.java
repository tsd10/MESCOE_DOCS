

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cancelation
 */
@WebServlet("/cancelation")
public class cancelation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cancelation() 
    {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bid = Integer.parseInt(request.getParameter("bid"));
		//System.out.println(bid);
		//String bid=request.getParameter("bid"); 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	     final String DB_URL="jdbc:mysql://localhost/ds";
	     final String USER = "root";
	     final String PASS = "omkar_";
	     try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement s;
			s=conn.prepareStatement("delete from booking where id =?");
			s.setInt(1, bid);
			System.out.println(bid);
			int rs = s.executeUpdate();
			URL obj = new URL("http://192.168.43.96:8080/DS/test1?id="+bid);
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
	        		"You Successfully Cancelled your Booking"+
	        		"<br>Your Booking ID was"+bid+
	        		"	</div>\n" + 
	        		"</form>\n" +
	        		"<a href=\"logout\"> LOGOUT </a>"+
	        		"</body>\n" + 
	        		"</html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     	out.print("");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
