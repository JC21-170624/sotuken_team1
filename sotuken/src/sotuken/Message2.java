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
 * Servlet implementation class Message2
 */
@WebServlet("/Message2")
public class Message2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mno = Integer.parseInt(request.getParameter("mno"));
		String jukenno = request.getParameter("juken_no");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String kigen = year + "/" + month + "/" + day;
		
		String kakunin = request.getParameter("kakunin");
		
		if(kakunin.equals("back")) {
			try {
				final String driverName = "oracle.jdbc.driver.OracleDriver";
				final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
				final String id = "TESTUSER";
				final String pass = "testuser";

				Class.forName(driverName);
				Connection connection=DriverManager.getConnection(url,id,pass);
				PreparedStatement st = 
						connection.prepareStatement(
							"SELECT M_NO,G_NO,S_NAME,M_DATE,MENJO_NO,HENKO_YN,MENJO_JUKEN_NO,Y_DATE,NYUKIN_YN FROM MOSIKOMI M LEFT OUTER JOIN SIKEN S ON M.S_NO = S.S_NO"
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
		}else {
		
			if(jukenno.length() == 0) {
				jukenno = "-";
			}
			
			int messageID = 0;
			String flg = "";
			
			try {
					final String driverName = "oracle.jdbc.driver.OracleDriver";
					final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
					final String id = "TESTUSER";
					final String pass = "testuser";
		
					Class.forName(driverName);
					Connection connection=DriverManager.getConnection(url,id,pass);
					PreparedStatement st = 
							connection.prepareStatement(
								"SELECT M.M_NO, N_NO FROM MOSIKOMI M JOIN NYUKINGO N ON M.M_NO = N.M_NO WHERE M.M_NO = ?"
								);
					st.setInt(1, mno);
					ResultSet rs = st.executeQuery();
					
					if(rs.next() == false) {
						flg = "toroku";
					}else {
						flg = "henko";
					}
					
					if(flg.equals("toroku")) {
						
						PreparedStatement st1 = 
								connection.prepareStatement(
									"insert into nyukingo values(seq_n.nextval,?, ?, ?, '受験前')"
									);
						st1.setString(1, jukenno);
						st1.setString(2, kigen);
						st1.setInt(3, mno);
						int inresult = st1.executeUpdate();
						
						PreparedStatement st0 = 
								connection.prepareStatement(
									"update mosikomi set nyukin_yn = ? where m_no = ?"
									);
						st0.setString(1, "入金済");
						st0.setInt(2, mno);
						int nyresult = st0.executeUpdate();
						
						if(inresult > 0 && nyresult > 0) {
							messageID = 1;
						}else {
							messageID = 0;
						}
						
					}else {
						
						PreparedStatement st2 = 
								connection.prepareStatement(
									"update nyukingo set juken_no = ?, kigen = ? where m_no = ?"
									);
						st2.setString(1, jukenno);
						st2.setString(2, kigen);
						st2.setInt(3, mno);
						int upresult = st2.executeUpdate();
						
						if(upresult > 0) {
							messageID = 1;
						}else {
							messageID = 0;
						}
					}
				
				}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
			
			String message = "";
			if(messageID > 0) {
				message = "受験情報の登録と入金処理が完了しました。";
			}else if(messageID == 0) {
				message = "受験情報の登録と入金処理に失敗しました。時間を置いてからまたお試しください。";
			}
			
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Message2.jsp");
			rd.forward(request, response);
		}
	}

}
