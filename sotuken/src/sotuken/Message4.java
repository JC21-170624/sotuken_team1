package sotuken;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Message4
 */
@WebServlet("/Message4")
public class Message4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("/sotuken/KLogin"); 
		}else if(session.getAttribute("login_token") == null) {
			response.sendRedirect("/sotuken/KLogin"); 
		}else {
		
			String mnoStr = request.getParameter("mno");
			int mno;
			
			String backflg = request.getParameter("kakunin");
			
			String dflg = "0";
			String dchk = "0";
			String m_only = "0";
			
			String message = "";
			
			if(mnoStr.length() == 0) {
				response.sendRedirect("/sotuken/Mkanri");
			}else {
				mno = Integer.parseInt(mnoStr);
				
				if(backflg.equals("delete")) {
		
					try {
						final String driverName = "oracle.jdbc.driver.OracleDriver";
						final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
						final String id = "TESTUSER";
						final String pass = "testuser";
	
						Class.forName(driverName);
						Connection connection=DriverManager.getConnection(url,id,pass);
						PreparedStatement st = 
								connection.prepareStatement(
									"SELECT JUKEN_FLG FROM NYUKINGO WHERE M_NO = ?"
									);
						st.setInt(1, mno);
						ResultSet rs = st.executeQuery();
						
						if(rs.next() != false){
							String s = rs.getString("juken_flg");
							if(s.equals("受験前")) {
								dflg = "0";
							}else {
								dflg = "1";
							}
						}else {
							dflg = "1";
							m_only = "1";
						}
						
						if(dflg.equals("1")) {
							
							PreparedStatement std = 
									connection.prepareStatement(
										"DELETE FROM NYUKINGO WHERE M_NO = ?"
										);
							std.setInt(1, mno);
							int result = std.executeUpdate();
							
							PreparedStatement std2 = 
									connection.prepareStatement(
										"DELETE FROM MOSIKOMI WHERE M_NO = ?"
										);
							std2.setInt(1, mno);
							int result2 = std2.executeUpdate();
							
							if(m_only.equals("1")) {
								if(result2 > 0) {
									dchk = "1";
								}
							}else {
								if(result > 0 && result2 > 0) {
									dchk = "1";
								}
							}
						}
							
							if(dflg.equals("0")) {
								message = "大変申し訳ございません。まだ学生が受験し終えていないため削除は行えません。";
							}else if(dflg.equals("1") && dchk.equals("0")) {
								message = "削除処理に失敗しました。申し訳ございませんが、時間を置いてからまたお試しください。";
							}else if(dflg.equals("1") && dchk.equals("1")) {
								message = "削除処理が完了しました。";
							}
							
							request.setAttribute("message", message);
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Message4.jsp");
							rd.forward(request, response);
							
						
						}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
				
				}else {
					
					response.sendRedirect("/sotuken/Mkanri");
					
				}
			}
		}
		
	}

}
