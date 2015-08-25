

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String name, email, zipcode, password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession(true);
//		String user_id = (String)session.getAttribute("user_id");
//		if(user_id==null)
//		{
//			getServletContext().getRequestDispatcher("/error.jsp")
//			.include(request, response);
//		
//		}
		
		PreparedStatement preStatement;
		try {
			Connection conn = DBConnection.getConnection();
			
			name = request.getParameter("name");
			email = request.getParameter("email");
			zipcode = request.getParameter("zipcode");
			password = request.getParameter("password");
			if(DBConnection.hasAccount(name, email, conn)){
				String content="Account already exists!";
				response.setContentType("text/html");
				request.setAttribute("content", content);
				getServletContext().getRequestDispatcher("/error.jsp")
				.include(request, response);
				content="";
			}else{
				String sql = "insert into users values(null, '"+ name +"' ,'"+email+"' ,"+ zipcode+",'"+password+"')";
				System.out.println(sql);
				preStatement = conn.prepareStatement(sql);
				preStatement.executeQuery();
				String sql1 = "select id from users where user_name = '"+name+"'";
				System.out.println(sql1);
				preStatement = conn.prepareStatement(sql1);
				ResultSet rst = preStatement.executeQuery();
				rst.next();
				request.getSession().setAttribute("user_id",rst.getString("id"));
				// Set response content type
				response.setContentType("text/html");

				String content="Successful!";
				request.setAttribute("content", content);

				getServletContext().getRequestDispatcher("/successful.jsp")
						.forward(request, response);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
