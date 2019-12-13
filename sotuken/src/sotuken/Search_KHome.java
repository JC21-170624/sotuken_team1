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

/**
 * Servlet implementation class Search_KHome
 */
@WebServlet("/Search_KHome")
public class Search_KHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search_KHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gno = request.getParameter("gno");
		String sno = request.getParameter("sno");
		String flg;
		
		if(gno.length() == 0 && sno.length() == 0) {
			flg = "0";
		}else if(gno.length() > 0 && sno.length() == 0) {
			flg = "gno";
		}else if(gno.length() == 0 && sno.length() > 0) {
			flg = "sno";
		}else {
			flg = "gno sno";
		}
		
		// 該当するオラクルデータベースに対して、SELECT文を実行する。結果は変数「rs」に入れている。
		try {
			final String driverName = "oracle.jdbc.driver.OracleDriver";
			final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
			final String id = "TESTUSER";
			final String pass = "testuser";

			Class.forName(driverName);
			Connection connection=DriverManager.getConnection(url,id,pass);
			
			ArrayList<String[]> list = new ArrayList<String[]>();
			
			if(flg.equals("0")) {
				PreparedStatement st = 
						connection.prepareStatement(
							"select mosikomi.m_no, s_name, g_no, juken_no, kigen, s_date from nyukingo left outer join mosikomi on nyukingo.m_no = mosikomi.m_no left outer join siken on mosikomi.s_no = siken.s_no"
							);
				// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO
				ResultSet rs = st.executeQuery();
				
				// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
				while(rs.next() != false) {
					
					String[] s = new String[6];
					
					s[0] = rs.getString("m_no");
					s[1] = rs.getString("s_name");
					s[2] = rs.getString("g_no");
					s[3] = rs.getString("juken_no");
					s[4] = rs.getString("kigen");
					s[5] = rs.getString("s_date");
					
					list.add(s);
				}
			}else if(flg.equals("gno")) {
				PreparedStatement st = 
						connection.prepareStatement(
							"select mosikomi.m_no, s_name, g_no, juken_no, kigen, s_date from nyukingo left outer join mosikomi on nyukingo.m_no = mosikomi.m_no left outer join siken on mosikomi.s_no = siken.s_no where g_no = ?"
							);
				st.setString(1, gno);
				// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO 大文字版
				ResultSet rs = st.executeQuery();
				
				// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
				while(rs.next() != false) {
					
					String[] s = new String[6];
					
					s[0] = rs.getString("m_no");
					s[1] = rs.getString("s_name");
					s[2] = rs.getString("g_no");
					s[3] = rs.getString("juken_no");
					s[4] = rs.getString("kigen");
					s[5] = rs.getString("s_date");
					
					list.add(s);
				}
			}else if(flg.equals("sno")) {
				PreparedStatement st = 
						connection.prepareStatement(
							"select mosikomi.m_no, s_name, g_no, juken_no, kigen, s_date from nyukingo left outer join mosikomi on nyukingo.m_no = mosikomi.m_no left outer join siken on mosikomi.s_no = siken.s_no where siken.s_no = ?"
							);
				st.setString(1, sno);
				// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO 大文字版
				ResultSet rs = st.executeQuery();
				
				// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
				while(rs.next() != false) {
					
					String[] s = new String[6];
					
					s[0] = rs.getString("m_no");
					s[1] = rs.getString("s_name");
					s[2] = rs.getString("g_no");
					s[3] = rs.getString("juken_no");
					s[4] = rs.getString("kigen");
					s[5] = rs.getString("s_date");
					
					list.add(s);
				}
			}else if(flg.equals("gno sno")) {
				PreparedStatement st = 
						connection.prepareStatement(
							"select mosikomi.m_no, s_name, g_no, juken_no, kigen, s_date from nyukingo left outer join mosikomi on nyukingo.m_no = mosikomi.m_no left outer join siken on mosikomi.s_no = siken.s_no where g_no = ? and siken.s_no = ?"
							);
				st.setString(1, gno);
				st.setString(2, sno);
				// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO 大文字版
				ResultSet rs = st.executeQuery();
				
				// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
				while(rs.next() != false) {
					
					String[] s = new String[6];
					
					s[0] = rs.getString("m_no");
					s[1] = rs.getString("s_name");
					s[2] = rs.getString("g_no");
					s[3] = rs.getString("juken_no");
					s[4] = rs.getString("kigen");
					s[5] = rs.getString("s_date");
					
					list.add(s);
				}
			}
			
			// 作ったArrayListをjspへ送り、forwardしている。
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/KHome.jsp");
			rd.forward(request, response);
			
			}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
	}

}
