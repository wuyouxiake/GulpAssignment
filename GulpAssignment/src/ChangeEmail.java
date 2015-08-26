

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
 * Servlet implementation class ChangeEmail
 */
@WebServlet("/ChangeEmail")
public class ChangeEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
      static String user_id="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeEmail() {
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
				String email = request.getParameter("email");

				Connection conn = DBConnection.getConnection();	
				
				String sql ="Update users set email ='"+email+"' where id="+user_id;
				PreparedStatement preStatement = conn.prepareStatement(sql);
				preStatement.executeQuery();

				String content= "Email Updated";
		
				response.setContentType("text/html");
				request.setAttribute("content", content);

				getServletContext().getRequestDispatcher("/successful.jsp")
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
		doGet(request, response);
	}

}
