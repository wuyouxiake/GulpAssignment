

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession(true);
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
			String name, email, zipcode;
			name = request.getParameter("name");
			email = request.getParameter("email");
			zipcode = request.getParameter("zipcode");
			
			String sql = "insert into review values(null, "+name+","+email+","+ zipcode+")";
			preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Set response content type
		response.setContentType("text/html");

		String content="Successful!";
		request.setAttribute("content", content);

		getServletContext().getRequestDispatcher("/successful.jsp")
				.forward(request, response);
		
	}

}
