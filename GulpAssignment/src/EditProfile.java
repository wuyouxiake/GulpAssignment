

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String user_id="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			HttpSession session = request.getSession(true);
			user_id = (String)session.getAttribute("user_id");
			if(user_id==null || user_id.equals("")){
				String content="Please sign in!";
				response.setContentType("text/html");
				request.getSession().setAttribute("content", content);
				getServletContext().getRequestDispatcher("/error.jsp")
				.forward(request, response);
			}else{
				System.out.println("Came to Else");
				Connection conn = DBConnection.getConnection();	
				
				String sql ="Select * from users where id="+user_id;
				PreparedStatement preStatement = conn.prepareStatement(sql);
				ResultSet result = preStatement.executeQuery();
				result.next();
				String email = result.getString("email");
				String message= "Your Email: <form action=ChangeEmail><input name=\"email\" value=\""+email+"\">"
						+ "<input type=submit></form>";
		
				response.setContentType("text/html");
				request.setAttribute("message", message);

				getServletContext().getRequestDispatcher("/EditEmail.jsp")
						.forward(request, response);				
				}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
