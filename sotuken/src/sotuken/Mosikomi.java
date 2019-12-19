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
 * Servlet implementation class Mosikomi
 */
@WebServlet("/Mosikomi")
public class Mosikomi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mosikomi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("/sotuken/Login"); 
		}else if(session.getAttribute("gno") == null) {
			response.sendRedirect("/sotuken/Login"); 
		}else {
			
		String sno = request.getParameter("sno");
		
		int backflg = 0;
		
		if(sno.contentEquals("S001")) {
			request.setAttribute("backflg", backflg);
			request.setAttribute("sno", sno);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Zyoho.jsp");
			rd.forward(request, response);
			
		}else if(sno.contentEquals("S002")) {
			request.setAttribute("backflg", backflg);
			request.setAttribute("errflg", "0"); // 未入力項目のチェックで使う。0の場合は初めてその画面に遷移するとき。
			request.setAttribute("sno", sno);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Menjo.jsp");
			rd.forward(request, response);
			
		}else if(sno.contentEquals("S003")) {
			request.setAttribute("backflg", backflg);
			request.setAttribute("sno", sno);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ITpass.jsp");
			rd.forward(request, response);
			
		}else if(sno.contentEquals("S004") || sno.contentEquals("S005") || sno.contentEquals("S006")) {
			request.setAttribute("sno", sno);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KJavan.jsp");
			rd.forward(request, response);
				/*
				 * request.setAttribute("backflg", backflg); request.setAttribute("sno", sno);
				 * RequestDispatcher rd =
				 * request.getRequestDispatcher("/WEB-INF/jsp/Javan.jsp"); rd.forward(request,
				 * response);
				 */
			
		}else if(sno.contentEquals("S007") || sno.contentEquals("S008") || sno.contentEquals("S009") || sno.contentEquals("S010")) {
			request.setAttribute("sno", sno);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KOracle.jsp");
			rd.forward(request, response);
				/*
				 * request.setAttribute("backflg", backflg); request.setAttribute("sno", sno);
				 * RequestDispatcher rd =
				 * request.getRequestDispatcher("/WEB-INF/jsp/Oracle.jsp"); rd.forward(request,
				 * response);
				 */
			
		}
		}
	}

}
