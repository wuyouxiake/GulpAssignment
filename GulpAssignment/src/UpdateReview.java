

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
@WebServlet("/UpdateReview")
public class UpdateReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBConnection.getConnection();
			String new_rating, new_comments, restaurant_name, user_id;
			new_rating = request.getParameter("rating");
			new_comments = request.getParameter("comments");
			restaurant_name = request.getParameter("restaurant_name");
			user_id = request.getParameter("user_id");
			PreparedStatement preStatement;
			
			String sql2 = "select rating, num_of_review from restaurant where restaurant_name='"+restaurant_name+"'";
			preStatement = conn.prepareStatement(sql2);
			ResultSet result = preStatement.executeQuery();
			result.next();
			double total=Double.parseDouble(result.getString("rating")) * Integer.parseInt(result.getString("num_of_review"));
			
			String sql3 = "select rating from review where user_id="+user_id+" and restaurant_name='"+restaurant_name+"'"; 
			preStatement = conn.prepareStatement(sql3);
			ResultSet result1 = preStatement.executeQuery();
			result1.next();
			String old_rating=result1.getString("rating");
			
			total=total-Double.parseDouble(old_rating)+Double.parseDouble(new_rating);
			double rating = total/Integer.parseInt(result.getString("num_of_review"));
			
				String sql= "update review set rating="+new_rating+", comments='"+new_comments+"' , "
						+ "review_date=SYSDATE where user_id="+user_id+" and restaurant_name='"+restaurant_name+"'";            
				
				preStatement = conn.prepareStatement(sql);
				preStatement.executeQuery();
				
				String sql4= "update restaurant set rating="+rating+" where restaurant_name='"+restaurant_name+"'";            
				
				preStatement = conn.prepareStatement(sql4);
				preStatement.executeQuery();
				
				String content="Successful!";
				request.setAttribute("content", content);

				getServletContext().getRequestDispatcher("/successful.jsp")
						.forward(request, response);
			
			
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
