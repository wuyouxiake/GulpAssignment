

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

/**
 * Servlet implementation class GetReview
 */
@WebServlet("/GetReview")
public class GetReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String restaurant_name;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		restaurant_name = request.getParameter("restaurant_name");
		
		Connection conn = DBConnection.getConnection();
		
		String sql = "select * from restaurant where restaurant_name = '"+restaurant_name+"'";
		System.out.println(sql);
		String rating = null, description = null, address = null, num_of_review = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			while(result.next()){
				rating = result.getString("rating");
				description = result.getString("description");
				address = result.getString("address");
				num_of_review = result.getString("num_of_review");
			}
			String content = "<tr><td>"+restaurant_name+"</td><td>"+rating+"</td></tr><tr><td colspan=2>"+
			description+"</tr><tr><td>"+address+"</td><td>"+num_of_review+"</td></tr>";
			// Set response content type
			response.setContentType("text/html");

			// Actual logic goes here.
			// PrintWriter out = response.getWriter();
			// message = "Hello";
			// out.println("&lt;h1&gt;" + message + "&lt;/h1&gt;");
			request.setAttribute("content", content);
			conn.close();
			getServletContext().getRequestDispatcher("/output.jsp")
					.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
