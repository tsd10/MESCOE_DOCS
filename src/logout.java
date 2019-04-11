

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		java.util.Date date=new java.util.Date();
		HttpSession session = request.getSession();
		int id=(int) session.getAttribute("ID");
		java.sql.Date da= (Date) session.getAttribute("da");
		java.sql.Timestamp login =(Timestamp) session.getAttribute("login");
		java.sql.Timestamp logout=new java.sql.Timestamp(date.getTime());
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/ds";
		final String USER = "root";
		final String PASS = "omkar_";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement s;
			s=conn.prepareStatement("insert into log(id,da,login,logout) values(?,?,?,?)");
			s.setInt(1, id);
			s.setDate(2, da);
			s.setTimestamp(3, login);
			s.setTimestamp(4, logout);
			int i=s.executeUpdate();
			session.invalidate();
			response.sendRedirect("index.html");
			
			
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
