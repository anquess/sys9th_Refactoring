package casestudy;

import java.io.Serializable;

import analyzer.casestudy.RealData;

/**
 *
 *アプリケーションスコープで使用。
 * DB格納スレッド、XML作成スレッド、administratorのステータス管理するためのbeans。
 *
 */
public class Beans implements Serializable{
	// private static final long serialVersionUID = 1L;
	 private AircraftSerch acs;
	 private RealData rd;
	 private boolean isIn = false;
	 private boolean isXml = false;
	 private boolean isLogin = false;
//	 public String dir = System.getProperty("user.dir");
	 //public String pwd = this.getServletContext().getRealPath("./");
	 private static String pwd = null;


	public static String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		Beans.pwd = pwd;
	}
	public AircraftSerch getAcs() {
		return acs;
	}
	public void setAcs(AircraftSerch acs) {
		this.acs = acs;
	}
	public RealData getRd() {
		return rd;
	}
	public void setRd(RealData rd) {
		this.rd = rd;
	}

	public boolean isIn() {
		return isIn;
	}
	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}
	public boolean isXml() {
		return isXml;
	}
	public void setXml(boolean isXml) {
		this.isXml = isXml;
	}

	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
}
