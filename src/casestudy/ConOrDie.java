package casestudy;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import analyzer.casestudy.RealData;



/**
 * Servlet implementation class ConnectionOrDie2
 * DB格納機能とXML作成機能をスレッドで動かすサーブレット
 */
@WebServlet("/ConOrDie")
public class ConOrDie extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConOrDie() {
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
	 * DB格納機能とXML作成機能をスレッドで動かすメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		ServletContext application = this.getServletContext();
		Beans t = (Beans) application.getAttribute("beans");

		//リクエスト取得

		String in = request.getParameter("in");
		String xml = request.getParameter("xml");


		int numIn;
		int numXml;

	    if (in == null || in.length() == 0){
	      numIn = 0;
	    }else{
	      try{
	        numIn = Integer.parseInt(in);
	      }catch (NumberFormatException e){
	        numIn = 0;
	      }
	    }

	    if (xml == null || xml.length() == 0){
		      numXml = 0;
		    }else{
		      try{
		        numXml = Integer.parseInt(in);
		      }catch (NumberFormatException e){
		        numXml = 0;
		      }
		    }




		response.setContentType("text/html;charset=UTF-8");




		if(numIn==1 && t.isIn() != true){


			t.getRd().doin = true;
			t.getRd().start();
			t.setIn(true);

		}else if(numIn==0){


			t.getRd().doin=false;
			RealData rd = new RealData();
			t.setRd(rd);
			t.setIn(false);

		}



		if(numXml==1 && t.isXml() != true){


			t.getAcs().dox = true;
			t.getAcs().start();
			t.setXml(true);
		}else if(numXml==0){

			t.getAcs().dox=false;
			AircraftSerch acs = new AircraftSerch();
			t.setAcs(acs);
			t.setXml(false);
		}






	

		response.sendRedirect("N1/administrator.jsp");
	}

}
