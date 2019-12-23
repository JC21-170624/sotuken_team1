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
 * Servlet implementation class MailGet
 */
@WebServlet("/MailGet")
public class MailGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailGet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		ログアウト時にMailGetにnullが飛んでくる仕組みになっている
//		String mail="170000@jc-21.jp"; 元々デバッグのために使っていたもの
		
		if(request.getParameter("mail") == null) {
			HttpSession nokori = request.getSession(true);
			nokori.invalidate();
			response.sendRedirect("/sotuken/Login");
		}else {
			String mail=request.getParameter("mail");
//			response.getWriter().println("(Get)mail="+mail);
			String gno = mail.substring(0,6); 
			
			HttpSession nokori = request.getSession(true);
			nokori.invalidate();
			HttpSession session = request.getSession(true);
			session.setAttribute("gno", gno);
			
			try {
				final String driverName = "oracle.jdbc.driver.OracleDriver";
				final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
				final String id = "TESTUSER";
				final String pass = "testuser";
				
				Class.forName(driverName);
				Connection connection=DriverManager.getConnection(url,id,pass);
				
				// 過去にログインしたことがあるユーザーかどうかを調べるためのSQL
				PreparedStatement st = 
						connection.prepareStatement(
							"select * from gakusei where g_no = ?"
							);
				st.setString(1, gno);
				ResultSet rs = st.executeQuery();
				
				// rsに何も入っていなければ新規のユーザー
				int result = 1;
				if(rs.next() == false) {
					
					// 新規ユーザーの情報を学生表に新たに登録
					PreparedStatement st2 = 
							connection.prepareStatement(
								"insert into gakusei values(?, ?)"
								);
					st2.setString(1, gno);
					st2.setString(2, mail);
					result = st2.executeUpdate();
					
				}
				
				// 新規ユーザー登録がうまくいかなかった時のみを検知して、ログイン画面にもう一度飛ばす
				if(result > 0) {
					response.sendRedirect("/sotuken/GHome");
				}else {
					request.setAttribute("inschk", "1");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
					rd.forward(request, response);
				}
				
				}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
		}
		
	}

}
