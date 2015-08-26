

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
@WebServlet("/UpdateRestaurant")
public class UpdateRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRestaurant() {
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
			String choice = request.getParameter("action");
			String r_name = request.getParameter("r_name");
			String new_name = request.getParameter("new_name");
			String new_address = request.getParameter("new_address");
			
			if(!DBConnection.hasRestaurant(r_name, conn)){
				String content="Restaurant does not exist!";
				response.setContentType("text/html");
				request.setAttribute("content", content);
				getServletContext().getRequestDispatcher("/error.jsp")
				.include(request, response);
				content="";
			}else{
				if(choice.equals("update_name")){
					String sql1 = "update restaurant set restaurant_name = '"+new_name+"' where restaurant_name= '"+r_name+"'";
					preStatement = conn.prepareStatement(sql1);
					preStatement.executeQuery();
					String sql2 = "update review set restaurant_name = '"+new_name+"' where restaurant_name= '"+r_name+"'";
					preStatement = conn.prepareStatement(sql2);
					preStatement.executeQuery();
				}else{
					String sql = "update restaurant set address = '"+new_address+"' where restaurant_name= '"+r_name+"'";
					preStatement = conn.prepareStatement(sql);
					preStatement.executeQuery();
				}
					// Set response content type
					response.setContentType("text/html");

					String content="Restaurant information updated successfully!";
					request.setAttribute("content", content);
					getServletContext().getRequestDispatcher("/successful.jsp")
							.forward(request, response);
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
}
