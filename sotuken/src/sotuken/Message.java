package sotuken;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect("/sotuken/Login"); 
		}else if(session.getAttribute("gno") == null) {
			response.sendRedirect("/sotuken/Login"); 
		}else {
			
		String sno = request.getParameter("sno");
		String gno = request.getParameter("gno");
		String menjo = request.getParameter("menjo");
		String henko = request.getParameter("henko");
		String menjuken = request.getParameter("menjuken");
		String ydate = request.getParameter("ydate");
		
		String kakunin = request.getParameter("kakunin");
		
		if(kakunin.equals("back")) {
			int backflg = 1;
			
			if(sno.contentEquals("S001")) {
				request.setAttribute("backflg", backflg);
				request.setAttribute("sno", sno);
				request.setAttribute("gno", gno);
				request.setAttribute("menjo", menjo);
				request.setAttribute("henko", henko);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Zyoho.jsp");
				rd.forward(request, response);
				
			}else if(sno.contentEquals("S002")) {
				request.setAttribute("errflg", "0");
				request.setAttribute("backflg", backflg);
				request.setAttribute("sno", sno);
				request.setAttribute("gno", gno);
				request.setAttribute("menjuken", menjuken);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Menjo.jsp");
				rd.forward(request, response);
				
			}else if(sno.contentEquals("S003")) {
				request.setAttribute("backflg", backflg);
				request.setAttribute("sno", sno);
				request.setAttribute("gno", gno);
				request.setAttribute("ydate", ydate);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ITpass.jsp");
				rd.forward(request, response);
				
			}else if(sno.contentEquals("S004") || sno.contentEquals("S005") || sno.contentEquals("S006")) {
				request.setAttribute("backflg", backflg);
				request.setAttribute("sno", sno);
				request.setAttribute("gno", gno);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Javan.jsp");
				rd.forward(request, response);
				
			}else if(sno.contentEquals("S007") || sno.contentEquals("S008") || sno.contentEquals("S009") || sno.contentEquals("S010")) {
				request.setAttribute("backflg", backflg);
				request.setAttribute("sno", sno);
				request.setAttribute("gno", gno);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Oracle.jsp");
				rd.forward(request, response);
				
			}
		}else {
		
			Date date = new Date();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        String mdate = sdf1.format(date);
			
	        int messageID = 0;
	        
	        if(gno.length() == 0) {
	        	request.setAttribute("message", "申込みに失敗しました。入力内容に誤りがないかご確認ください。");
	    		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Message.jsp");
	    		rd.forward(request, response);
	        }
	        
	        if(menjo.length() == 0) {
	        	menjo = "-";
	        }
	        
			try {
				final String driverName = "oracle.jdbc.driver.OracleDriver";
				final String url = "jdbc:oracle:thin:@192.168.54.191:1521/orcl";
				final String id = "TESTUSER";
				final String pass = "testuser";
	
				Class.forName(driverName);
				Connection connection=DriverManager.getConnection(url,id,pass);
			
				PreparedStatement st1 = 
							connection.prepareStatement(
								"insert into mosikomi values(seq_m.nextval,?, ?, ?, ?, ?, ?, ?, '未入金')"
								);
				st1.setString(1, gno);
				st1.setString(2, sno);
				st1.setString(3, mdate);
				st1.setString(4, menjo);
				st1.setString(5, henko);
				st1.setString(6, menjuken);
				st1.setString(7, ydate);
				int result = st1.executeUpdate();
				
				if(result > 0) {
					messageID = 1;
				}else {
					messageID = 0;
				}
				
			}catch(SQLException | ClassNotFoundException e) {e.printStackTrace();}
			
			String message = "";
			if(messageID > 0) {
				message = "申込みが完了しました。";
			}else if(messageID == 0) {
				message = "申込みに失敗しました。申し訳ありませんが時間を置いてからまたお試しください。";
			}
			
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Message.jsp");
			rd.forward(request, response);
		}
		
		}
		
	}
}

