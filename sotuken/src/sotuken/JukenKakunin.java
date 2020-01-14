package sotuken;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JukenKakunin
 */
@WebServlet("/JukenKakunin")
public class JukenKakunin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JukenKakunin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("/KenteiMosikomi/Login"); 
		}else if(session.getAttribute("gno") == null) {
			response.sendRedirect("/KenteiMosikomi/Login"); 
		}else {
			
		String nnoStr = request.getParameter("nno");
		int nno;
		
		if(nnoStr.length() == 0) {
			response.sendRedirect("/KenteiMosikomi/GHome");
		}else {
			nno = Integer.parseInt(nnoStr);
		
			try {
				final String driverName = "oracle.jdbc.driver.OracleDriver";
				final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
				final String id = "TESTUSER";
				final String pass = "testuser";
	
				Class.forName(driverName);
				Connection connection=DriverManager.getConnection(url,id,pass);
				PreparedStatement st = 
						connection.prepareStatement(
							"select s_name, juken_no, kigen, s_date, n_no from nyukingo join mosikomi on nyukingo.m_no = mosikomi.m_no left outer join siken on mosikomi.s_no = siken.s_no where n_no = ?"
							);
				st.setInt(1, nno);
				ResultSet rs = st.executeQuery();
				
				rs.next();
				
				String[] list = new String[5];
					
				list[0] = String.valueOf(rs.getInt("N_NO"));
				list[1] = rs.getString("S_NAME");
				list[2] = rs.getString("JUKEN_NO");
				list[3] = rs.getString("KIGEN");
				list[4] = rs.getString("S_DATE");
				
				
				// çÏÇ¡ÇΩArrayListÇjspÇ÷ëóÇËÅAforwardÇµÇƒÇ¢ÇÈÅB
				request.setAttribute("list", list);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/JukenKakunin.jsp");
				rd.forward(request, response);
				
				}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
		}
		
		}
		
	}

}
