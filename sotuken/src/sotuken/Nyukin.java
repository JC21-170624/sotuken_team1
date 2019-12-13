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
 * Servlet implementation class Nyukin
 */
@WebServlet("/Nyukin")
public class Nyukin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Nyukin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mnoStr = request.getParameter("mno");
		int mno;
		
		String mkanri = request.getParameter("mkanri");
		
		if(mnoStr.length() == 0) {
			response.sendRedirect("/sotuken/Mkanri");
		}else {
				mno = Integer.parseInt(mnoStr);
				
				if(mkanri.equals("money")) {
		
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
									"SELECT * FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO WHERE M_NO = ?"
									);
						st.setInt(1, mno);
						ResultSet rs = st.executeQuery();
						
						rs.next();
						String[] s = new String[10];
							
						s[0] = String.valueOf(rs.getInt("M_NO"));
						s[1] = rs.getString("G_NO");
						s[2] = rs.getString("S_NO");
						s[3] = rs.getString("M_DATE");
						s[4] = rs.getString("MENJO_NO");
						s[5] = rs.getString("HENKO_YN");
						s[6] = rs.getString("MENJO_JUKEN_NO");
						s[7] = rs.getString("Y_DATE");
						s[8] = rs.getString("NYUKIN_YN");
						s[9] = rs.getString("S_NAME");
						
						int bchk = 0;
						if(s[2].equals("S003") || s[2].equals("S007") || s[2].equals("S008") || s[2].equals("S009") || s[2].equals("S010")) {
							bchk = 1;
						}
						
						// 作ったArrayをjspへ送り、forwardしている。
						request.setAttribute("mno", mno);
						request.setAttribute("bchk", bchk);
						request.setAttribute("list", s);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Nyukin.jsp");
						rd.forward(request, response);
						
						}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
				
				}else { // 確認するサーブレットへ飛ばす
					
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
									"SELECT * FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO WHERE M_NO = ?"
									);
						st.setInt(1, mno);
						ResultSet rs = st.executeQuery();
						
						rs.next();
						String[] s = new String[10];
							
						s[0] = String.valueOf(rs.getInt("M_NO"));
						s[1] = rs.getString("G_NO");
						s[2] = rs.getString("S_NO");
						s[3] = rs.getString("M_DATE");
						s[4] = rs.getString("MENJO_NO");
						s[5] = rs.getString("HENKO_YN");
						s[6] = rs.getString("MENJO_JUKEN_NO");
						s[7] = rs.getString("Y_DATE");
						s[8] = rs.getString("NYUKIN_YN");
						s[9] = rs.getString("S_NAME");
						
						
						request.setAttribute("list", s);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/DKakunin.jsp");
						rd.forward(request, response);
						
						}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
					
				}
		}
	}

}
