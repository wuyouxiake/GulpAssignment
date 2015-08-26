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
	
			if(user_id==null){
				response.setContentType("text/html");
				getServletContext().getRequestDispatcher("/error.jsp")
				.include(request, response);
			}
			conn = DBConnection.getConnection();
			String sql = "select * from restaurant order by rating desc";
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String restaurant_name=result.getString("restaurant_name");
				System.out.println(restaurant_name);
				String description=result.getString("description");
				String address=result.getString("address");
				user_id=(String) request.getSession().getAttribute("user_id");
				fullList += ("<tr><td><a href=GetReview?restaurant_name=" + restaurant_name.replaceAll(" ", "%20")+">"+restaurant_name+"</a>"
						+ "</td><td>" + result.getString("rating")
						+ "</td><td>" + result.getString("num_of_review")
						+ "</td></tr>"
						+ "<tr><td colspan=2><b>Description</b></td><td><b>Address</b></td></tr>"
						+ "<tr><td colspan=2 >" + result.getString("description")+"</td>"
						+ "<td >" + result.getString("address")+"</td></tr>"
						+ "<tr><td colspan=3><a href=new_review.jsp?restaurant_name=" + restaurant_name+">Add Review</a></td>" + "</tr><tr><td colspan= 3></td></tr>");
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
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/RestaurantList.jsp")
				.forward(request, response);
		fullList = "";
		
	}

	public void destroy() {
		// do nothing.
	}

}
