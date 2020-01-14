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
 * Servlet implementation class GHome
 */
@WebServlet("/GHome")
public class GHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("/KenteiMosikomi/Login"); 
		}else if(session.getAttribute("gno") == null) {
			response.sendRedirect("/KenteiMosikomi/Login"); 
		}else {
			
		String gno = (String)session.getAttribute("gno");
		
				// 該当するオラクルデータベースに対して、SELECT文を実行する。結果は変数「rs」に入れている。
				try {
					final String driverName = "oracle.jdbc.driver.OracleDriver";
					final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
					final String id = "TESTUSER";
					final String pass = "testuser";
					
					Class.forName(driverName);
					Connection connection=DriverManager.getConnection(url,id,pass);
					
					//上の表
					PreparedStatement st = 
							connection.prepareStatement(
								"select s_name, juken_no, kigen, s_date, n_no from nyukingo join mosikomi on nyukingo.m_no = mosikomi.m_no left outer join siken on mosikomi.s_no = siken.s_no where g_no = ? and juken_flg = '受験前' order by mosikomi.s_no"
								);
					st.setString(1, gno);
					// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO
					ResultSet rs = st.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					ArrayList<String[]> list = new ArrayList<String[]>();
					
					while(rs.next() != false) {
						
						String[] s = new String[5];
						
						s[0] = rs.getString("s_name");
						s[1] = rs.getString("juken_no");
						s[2] = rs.getString("kigen");
						s[3] = rs.getString("s_date");
						s[4] = rs.getString("n_no");
						
						list.add(s);
					}
					
					//下の表
					PreparedStatement st2 = 
							connection.prepareStatement(
								"select s_name, uketuke, price from mosikomi left outer join siken on mosikomi.s_no = siken.s_no where g_no = ? and nyukin_yn = '未入金' order by mosikomi.s_no"
								);
					st2.setString(1, gno);
					// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO
					ResultSet rs2 = st2.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					ArrayList<String[]> list2 = new ArrayList<String[]>();
					
					while(rs2.next() != false) {
						
						String[] s = new String[3];
						
						s[0] = rs2.getString("s_name");
						s[1] = rs2.getString("uketuke");
						s[2] = rs2.getString("price");
						
						list2.add(s);
					}
					
					// 作ったArrayListをjspへ送り、forwardしている。
					request.setAttribute("list", list);
					request.setAttribute("list2", list2);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/GHome.jsp");
					rd.forward(request, response);
					
					}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
		}
		
	}

}
