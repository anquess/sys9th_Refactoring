package casestudy;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminEnd
 * 管理者画面のログインブロックを解除するサーブレット
 */
@WebServlet("/AdminEnd")
public class AdminEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 管理者画面を終了した際にPostされるメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ServletContext application = this.getServletContext();
		Beans t = (Beans) application.getAttribute("beans");

		t.setLogin(false);

		response.sendRedirect("N1/N1.jsp");

	}

}
