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
 * Servlet implementation class KSiken
 */
@WebServlet("/KSiken")
public class KSiken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KSiken() {
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
				// 該当するオラクルデータベースに対して、SELECT文を実行する。結果は変数「rs」に入れている。
				try {
					final String driverName = "oracle.jdbc.driver.OracleDriver";
					final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
					final String id = "TESTUSER";
					final String pass = "testuser";

					Class.forName(driverName);
					Connection connection=DriverManager.getConnection(url,id,pass);
					PreparedStatement st = 
							connection.prepareStatement(
								"SELECT * FROM SIKEN"
								);
					ResultSet rs = st.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					ArrayList<String[]> list = new ArrayList<String[]>();
					
					while(rs.next() != false) {
						
						String[] s = new String[4];
						
						s[0] = rs.getString("S_NAME");
						s[1] = rs.getString("UKETUKE");
						s[2] = rs.getString("S_DATE");
						s[3] = rs.getString("PRICE");
						
						list.add(s);
					}
					
					// 作ったArrayListをjspへ送り、forwardしている。
					request.setAttribute("list", list);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/kSiken.jsp");
					rd.forward(request, response);
					
					}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
		}
	}

}
