

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class booking
 */
@WebServlet("/bookingj")
public class bookingj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookingj() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String start= request.getParameter("start");
		String end = request.getParameter("end");
		String type = request.getParameter("type");
		String date = request.getParameter("jdate");
		String timing = request.getParameter("jtiming");
	
		
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_Url = "jdbc:mysql://localhost/ds";
		final String USER = "root";
		final String PASS = "omkar_";
		
		HttpSession session=request.getSession(false);  
		String name=(String) session.getAttribute("name");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body bgcolor=\"orange\">");
		out.println("<h3 align=\"center\"><b>ONLINE AIRLINE RESERVATION SYSTEM</h3>\n" + 
				   "<marquee Direction=\"right\"><img src=\"index.jpeg\"></marquee>");
		out.println("<div style=\"text-align:center\">");
		out.println("Booking of Flight for"+"<b>"+"</b>");
		out.println("</div>");
		out.println("<div style=\"text-align:center\">");
		out.println("Journey from \t"+start+"\t to \t"+end+"\t on \t"+date+"\t is confirmed \t"+"\t at \t"+timing +"\t chrges Rs 1500\t"+"for"+name);
		out.println("</div>");
		out.println("</body>");
		out.println("</htmml>");
	}

}
