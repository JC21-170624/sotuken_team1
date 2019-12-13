package sotuken;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Kakunin
 */
@WebServlet("/Kakunin")
public class Kakunin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kakunin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno = request.getParameter("gno");
		String sno = request.getParameter("sno");
		String menjo = request.getParameter("menjo");
		String henko = request.getParameter("henko");
		String menjuken = request.getParameter("menjuken");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String ydate = year + "/" + month;
		
		if(sno.contentEquals("S001")) {
			request.setAttribute("gno", gno);
			request.setAttribute("sno", sno);
			request.setAttribute("menjo", menjo);
			request.setAttribute("henko", henko);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KZyoho.jsp");
			rd.forward(request, response);
			
		}else if(sno.contentEquals("S002")) {
			request.setAttribute("gno", gno);
			request.setAttribute("sno", sno);
			request.setAttribute("menjuken", menjuken);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KMenjo.jsp");
			rd.forward(request, response);
			
		}else if(sno.contentEquals("S003")) {
			request.setAttribute("gno", gno);
			request.setAttribute("sno", sno);
			request.setAttribute("ydate", ydate);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KITpass.jsp");
			rd.forward(request, response);
			
		}else if(sno.contentEquals("S004") || sno.contentEquals("S005") || sno.contentEquals("S006")) {
			request.setAttribute("gno", gno);
			request.setAttribute("sno", sno);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KJavan.jsp");
			rd.forward(request, response);
			
		}else if(sno.contentEquals("S007") || sno.contentEquals("S008") || sno.contentEquals("S009") || sno.contentEquals("S010")) {
			request.setAttribute("gno", gno);
			request.setAttribute("sno", sno);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KOracle.jsp");
			rd.forward(request, response);
			
		}
	}

}
