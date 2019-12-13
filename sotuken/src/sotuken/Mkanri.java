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
 * Servlet implementation class Mkanri
 */
@WebServlet("/Mkanri")
public class Mkanri extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mkanri() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
								"SELECT M_NO,G_NO,S_NAME,M_DATE,MENJO_NO,HENKO_YN,MENJO_JUKEN_NO,Y_DATE,NYUKIN_YN FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO ORDER BY S.S_NO, M_DATE DESC"
								);
					ResultSet rs = st.executeQuery();
					
					// 変数「rs」の中にあるSELECT文の結果を、作ったArrayListの中へ移している。
					ArrayList<String[]> list = new ArrayList<String[]>();
					
					while(rs.next() != false) {
						
						String[] s = new String[9];
						
						s[0] = String.valueOf(rs.getInt("M_NO"));
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
					
					// 作ったArrayListをjspへ送り、forwardしている。
					request.setAttribute("list", list);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Mkanri.jsp");
					rd.forward(request, response);
					
					}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
	}

}
