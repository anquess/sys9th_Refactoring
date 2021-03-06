package casestudy;

import java.sql.Connection;
import java.sql.SQLException;

import debug.DebugPrint;
import n4.dao.ConnectionFactory;


/**
 * DBの全テーブルをリセットするクラス
 */
public class ResetDB {

	public void resetDB() throws SQLException {

		Connection   con = null;
		try {
			con = ConnectionFactory.getConnection();
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		DebugPrint.debugPrint("DB接続完了");
//		System.out.println("DB接続完了");

		//Statement stmt = null;

		try{

			//stmt = con.createStatement();
			//stmt.executeUpdate("TRUNCATE TABLE callsign");
			//stmt.executeUpdate("TRUNCATE TABLE velocity");
			//stmt.executeUpdate("TRUNCATE TABLE position");

			VelocityDAO veDAO = new VelocityDAO(con);
			PositionDAO poDAO = new PositionDAO(con);
			CallsignDAO caDAO = new CallsignDAO(con);

			poDAO.resetPositionDB();
			caDAO.resetCallSignDB();
			veDAO.resetVelocityDB();



		}catch (SQLException e){

			  System.err.println("SQLException:" + e.getMessage());

		}

	}

}
