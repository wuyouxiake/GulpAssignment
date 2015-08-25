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
@WebServlet("/GetMyReview")
public class GetMyReview extends HttpServlet {
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
			if(user_id==null || user_id.equals("")){
				String content="Please sign in!";
				response.setContentType("text/html");
				request.getSession().setAttribute("content", content);
				getServletContext().getRequestDispatcher("/error.jsp")
				.forward(request, response);
			}else{
				conn = DBConnection.getConnection();
				String sql = "select * from review where user_id = "+user_id+" order by rating";
				PreparedStatement preStatement = conn.prepareStatement(sql);
				ResultSet result = preStatement.executeQuery();
				String restaurant_name;
			
				while (result.next()) {
					restaurant_name=result.getString("restaurant_name");

					fullList += ("<tr><td><a href=GetReview?restaurant_name=" + restaurant_name+">"+restaurant_name+"</a>"
							+ "</td><td>" + result.getString("rating")
							+ "</td><td>" + result.getString("comments")
							+ "</td><td>" + result.getString("review_date")
							+ "</td></tr>");
					// System.out.println(fullList);
				}
				// Set response content type
				response.setContentType("text/html");

				// Actual logic goes here.
				// PrintWriter out = response.getWriter();
				// message = "Hello";
				// out.println("&lt;h1&gt;" + message + "&lt;/h1&gt;");
				request.setAttribute("fullList", fullList);

				getServletContext().getRequestDispatcher("/MyReview.jsp")
						.forward(request, response);
				fullList = "";
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void destroy() {
		// do nothing.
	}

}
