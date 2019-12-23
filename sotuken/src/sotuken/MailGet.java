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
		
//		���O�A�E�g����MailGet��null�����ł���d�g�݂ɂȂ��Ă���
//		String mail="170000@jc-21.jp"; ���X�f�o�b�O�̂��߂Ɏg���Ă�������
		
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
				
				// �ߋ��Ƀ��O�C���������Ƃ����郆�[�U�[���ǂ����𒲂ׂ邽�߂�SQL
				PreparedStatement st = 
						connection.prepareStatement(
							"select * from gakusei where g_no = ?"
							);
				st.setString(1, gno);
				ResultSet rs = st.executeQuery();
				
				// rs�ɉ��������Ă��Ȃ���ΐV�K�̃��[�U�[
				int result = 1;
				if(rs.next() == false) {
					
					// �V�K���[�U�[�̏����w���\�ɐV���ɓo�^
					PreparedStatement st2 = 
							connection.prepareStatement(
								"insert into gakusei values(?, ?)"
								);
					st2.setString(1, gno);
					st2.setString(2, mail);
					result = st2.executeUpdate();
					
				}
				
				// �V�K���[�U�[�o�^�����܂������Ȃ��������݂̂����m���āA���O�C����ʂɂ�����x��΂�
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
