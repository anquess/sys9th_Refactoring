package casestudy;

import java.sql.Connection;
import java.sql.SQLException;

import n4.dao.ConnectionFactory;

/**
 * AirCraftSerchのrunメソッドにおいて、
 * ConnectionManagerのげｔConnectionでエラーとなるため、
 * スレッドが原因か特定するためのモックメソッド
 */
public class RunMethod extends Thread {

	/**
	 * スレッド内の無限ループのon,off
	 * trueなら無限ループ、falseなら無限ループからでる。
	 */
	private boolean loop = true;

	/**
	 * AirCraftSerchのrunメソッドにおいて、
	 * ConnectionManagerのげｔConnectionでエラーとなるため、
	 * スレッドが原因か特定するためのモックメソッド
	 */
	@Override
	public void run(){
		Connection con = null;
		while(loop){
			try {
				con = ConnectionFactory.getConnection();
			}catch(Exception e){
				this.loop = false;
				e.printStackTrace();
			}
			System.out.println("接続完了");
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("接続終了");
	}

	public void setLoop(boolean loop){
		this.loop = loop;
	}


}
