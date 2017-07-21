package casestudy;

import java.sql.Connection;
/**
 *
 * DAOに接続し、csvファイルで出力するクラス
 *
 */

public class ExportCSV {

	/**
	 *
	 * DAOに接続し、csvファイルで出力するメソッド
	 *
	 */
	public void exportCSV(){

		Connection   con = null;

		try {

			con = ConnectionManager.getConnection();
			System.out.println("接続完了");

			CallsignDAO caDAO = new CallsignDAO(con);
			VelocityDAO veDAO = new VelocityDAO(con);
			PositionDAO poDAO = new PositionDAO(con);

			caDAO.exportCallSignCSV();
			veDAO.exportVelocityCSV();
			poDAO.exportPositionCSV();




		}catch (Exception e) {
			// 何らかのエラーがあっても表示するのみ
			System.out.println("エラーです");
			e.printStackTrace();
		}

	}

}
