package sotuken;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Password
 */
@WebServlet("/Password")
public class Password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Password() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = request.getParameter("pass");
		
		if(pass.equals("admin")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_token", "login_token");
			response.sendRedirect("/KenteiMosikomi/KHome");
		}else {
			request.setAttribute("flg", "1");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KLogin.jsp");
			rd.forward(request, response);
		}
	}

}
