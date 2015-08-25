

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
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBConnection.getConnection();
			String rating, comments, restaurant_name, user_id;
			rating = request.getParameter("rating");
			comments = request.getParameter("comments");
			restaurant_name = request.getParameter("restaurant_name");
			user_id = request.getParameter("user_id");
			
			if(DBConnection.hasReview(restaurant_name, user_id, conn)){
				String content="You have already reviewed this restaurant!";
				response.setContentType("text/html");
				request.setAttribute("content", content);
				getServletContext().getRequestDispatcher("/error.jsp")
				.include(request, response);
				content="";
			}else{
				String sql = "insert into review values(null, "+user_id+",'"+restaurant_name+"',"+ rating+", '"+comments+"', SYSDATE)";
				PreparedStatement preStatement;
				preStatement = conn.prepareStatement(sql);
				ResultSet result = preStatement.executeQuery();
				
				String sql1 = "select num_of_review, rating from restaurant where restaurant_name = '"+restaurant_name+"'";
				PreparedStatement preStatement1;
				preStatement1 = conn.prepareStatement(sql1);
				ResultSet result1 = preStatement1.executeQuery();
				result1.next();
				double tempRating = Double.parseDouble(result1.getString("rating"));
				int tempNum = Integer.parseInt(result1.getString("num_of_review"));
				double newRating = ((tempRating * tempNum)+Double.parseDouble(rating))/(tempNum+1);
				tempNum++;
				
				String sql2 = "update restaurant set rating = "+newRating+", num_of_review="+tempNum+" where restaurant_name ='"+restaurant_name+"'";
				response.setContentType("text/html");
				PreparedStatement preStatement2;
				preStatement2 = conn.prepareStatement(sql2);
				preStatement2.executeQuery();
				
				String content="Successful!";
				request.setAttribute("content", content);

				getServletContext().getRequestDispatcher("/successful.jsp")
						.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Set response content type
				
	

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
		
		doGet(request, response);
	}
}
