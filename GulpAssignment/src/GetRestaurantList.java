import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Servlet implementation class GetList
 */
@WebServlet("/GetRestaurantList")
public class GetRestaurantList extends HttpServlet {
	private String fullList;
	private Connection conn =null;
	
	
	public void init() throws ServletException {
		// Do required initialization
		fullList = "";
	}

	@SuppressWarnings("null")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		
			HttpSession session = request.getSession(true);
			String user_id = (String)session.getAttribute("user_id");
	
			conn = DBConnection.getConnection();
			String sql = "select restaurant_name, rating, num_of_review from restaurant order by rating desc";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
			String restaurant_name;
		
			while (result.next()) {
				restaurant_name=result.getString("restaurant_name");
				user_id=result.getString("user_id");
				fullList += ("<tr><td><a href=GetReview?restaurant_name=" + restaurant_name+">"+restaurant_name+"</a>"
						+ "</td><td>" + result.getString("rating")
						+ "</td><td>" + result.getString("num_of_review")
						+ "<td><a href=new_review.jsp?restaurant_name=" + restaurant_name+"&user_id="+user_id+">Add Review</a></td>" + "</tr>");
				// System.out.println(fullList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		// PrintWriter out = response.getWriter();
		// message = "Hello";
		// out.println("&lt;h1&gt;" + message + "&lt;/h1&gt;");
		request.setAttribute("fullList", fullList);

		getServletContext().getRequestDispatcher("/RestaurantList.jsp")
				.forward(request, response);
		fullList = "";
	}

	public void destroy() {
		// do nothing.
	}

}
