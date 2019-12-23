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
 * Servlet implementation class Search_Mkanri
 */
@WebServlet("/Search_Mkanri")
public class Search_Mkanri extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search_Mkanri() {
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
								"SELECT M_NO,G_NO,S_NAME,M_DATE,MENJO_NO,HENKO_YN,MENJO_JUKEN_NO,Y_DATE,NYUKIN_YN FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO ORDER BY S.S_NO, M_DATE DESC"
								);
					// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO
					ResultSet rs = st.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					while(rs.next() != false) {
						
						String[] s = new String[9];
						
						s[0] = rs.getString("M_NO");
						s[1] = rs.getString("G_NO");
						s[2] = rs.getString("S_NAME");
						s[3] = rs.getString("M_DATE");
						s[4] = rs.getString("MENJO_NO");
						s[5] = rs.getString("HENKO_YN");
						s[6] = rs.getString("MENJO_JUKEN_NO");
						s[7] = rs.getString("Y_DATE");
						s[8] = rs.getString("NYUKIN_YN");
						
						list.add(s);
					}
				}else if(flg.equals("gno")) {
					PreparedStatement st = 
							connection.prepareStatement(
								"SELECT M_NO,G_NO,S_NAME,M_DATE,MENJO_NO,HENKO_YN,MENJO_JUKEN_NO,Y_DATE,NYUKIN_YN FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO WHERE G_NO = ?"
								);
					st.setString(1, gno);
					// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO 大文字版
					ResultSet rs = st.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					while(rs.next() != false) {
						
						String[] s = new String[9];
						
						s[0] = rs.getString("M_NO");
						s[1] = rs.getString("G_NO");
						s[2] = rs.getString("S_NAME");
						s[3] = rs.getString("M_DATE");
						s[4] = rs.getString("MENJO_NO");
						s[5] = rs.getString("HENKO_YN");
						s[6] = rs.getString("MENJO_JUKEN_NO");
						s[7] = rs.getString("Y_DATE");
						s[8] = rs.getString("NYUKIN_YN");
						
						list.add(s);
					}
				}else if(flg.equals("sno")) {
					PreparedStatement st = 
							connection.prepareStatement(
								"SELECT M_NO,G_NO,S_NAME,M_DATE,MENJO_NO,HENKO_YN,MENJO_JUKEN_NO,Y_DATE,NYUKIN_YN FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO WHERE S.S_NO = ?"
								);
					st.setString(1, sno);
					// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO 大文字版
					ResultSet rs = st.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					while(rs.next() != false) {
						
						String[] s = new String[9];
						
						s[0] = rs.getString("M_NO");
						s[1] = rs.getString("G_NO");
						s[2] = rs.getString("S_NAME");
						s[3] = rs.getString("M_DATE");
						s[4] = rs.getString("MENJO_NO");
						s[5] = rs.getString("HENKO_YN");
						s[6] = rs.getString("MENJO_JUKEN_NO");
						s[7] = rs.getString("Y_DATE");
						s[8] = rs.getString("NYUKIN_YN");
						
						list.add(s);
					}
				}else if(flg.equals("gno sno")) {
					PreparedStatement st = 
							connection.prepareStatement(
								"SELECT M_NO,G_NO,S.S_NO,S_NAME,M_DATE,MENJO_NO,HENKO_YN,MENJO_JUKEN_NO,Y_DATE,NYUKIN_YN FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO WHERE G_NO = ? AND S.S_NO = ?"
								);
					st.setString(1, gno);
					st.setString(2, sno);
					// SELECT S_NAME, G_NO, JUKEN_NO, KIGEN, S_DATE FROM NYUKINGO N LEFT OUTER JOIN MOSIKOMI M ON N.M_NO = M.M_NO LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO 大文字版
					ResultSet rs = st.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					while(rs.next() != false) {
						
						String[] s = new String[9];
						
						s[0] = rs.getString("M_NO");
						s[1] = rs.getString("G_NO");
						s[2] = rs.getString("S_NAME");
						s[3] = rs.getString("M_DATE");
						s[4] = rs.getString("MENJO_NO");
						s[5] = rs.getString("HENKO_YN");
						s[6] = rs.getString("MENJO_JUKEN_NO");
						s[7] = rs.getString("Y_DATE");
						s[8] = rs.getString("NYUKIN_YN");
						
						list.add(s);
					}
				}
				
				// 作ったArrayListをjspへ送り、forwardしている。
				request.setAttribute("list", list);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Mkanri.jsp");
				rd.forward(request, response);
				
				}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
		}
	}

}
