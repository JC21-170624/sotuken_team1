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
 * Servlet implementation class Message3
 */
@WebServlet("/Message3")
public class Message3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message3() {
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
			
		int nno = Integer.parseInt(request.getParameter("nno"));
		String jukengo = request.getParameter("jukengo");
		
		String message = "";
		
		if(jukengo.equals("back")) {
			response.sendRedirect("/KenteiMosikomi/GHome");
		}else {
			
			try {
				final String driverName = "oracle.jdbc.driver.OracleDriver";
				final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
				final String id = "TESTUSER";
				final String pass = "testuser";
				
				Class.forName(driverName);
				Connection connection=DriverManager.getConnection(url,id,pass);
				
				PreparedStatement st = 
						connection.prepareStatement(
							"update nyukingo set juken_flg = '受験後' where n_no = ?"
							);
				st.setInt(1, nno);
				int result = st.executeUpdate();
				
				if(result > 0) {
					message = "受験後処理が完了しました。";
				}else {
					message = "受験後処理に失敗しました。時間を置いてからまたお試しください。<br />お手数をおかけして申し訳ございません。";
				}
				
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Message3.jsp");
				rd.forward(request, response);
				
				}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
		}
			
		}
		
	}

}
