package casestudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import analyzer.casestudy.DB_Item_CallSign;
import debug.DebugPrint;

/**
 *
 * CallsignテーブルのDAO
 *
 */


public class CallsignDAO {
	private  Connection con;

	/**
	 *
	 * @param con DB接続のための設定情報
	 */
	public CallsignDAO(Connection con) {
		this.con = con;
	}

	/**
	 * DBから取得したPosition情報に対応するcallsignを取得するメソッド
	 * @param posi 画面に表示する全航空機の位置情報（配列）
	 * @return posiに対応したCallsignの配列
	 * @throws SQLException DBに接続できなかった場合のエラー
	 */
	public Callsign[] findcall(Position[] posi) throws SQLException {
		String sql = "select * from callsign where timestamp in (select max(timestamp) from callsign group by modes) and modes = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = null;
		Callsign call[] = new Callsign[100];


		int i=0;
		try{

			while(posi[i]!=null){
				//stmt = ;
				stmt.setString(1,posi[i].getModes());
				res = stmt.executeQuery();//sqlをとってくる
				DebugPrint.debugPrint(posi[i].getModes());
//				System.out.print(posi[i].getModes());
				if(res.next()){
					call[i] = new Callsign(//列の名前を書く.その列のデータをとってきてくれる
							res.getString("modes"),
							res.getString("callsign")
							);
				}
				i++;

			}
		}finally{//絶対行う
			if(res != null){
				res.close();
			}
			if(stmt != null){
				stmt.close();

			}

		}
		return call;
	}


	/**
	 * DBにCallsignを格納するメソッド
	 * @param callsign 解析されたCallsign情報
	 * @throws SQLException DBに接続できなかった場合のエラー
	 */
	public void insertcallsign(DB_Item_CallSign callsign) throws SQLException{
		String sql = "INSERT INTO callsign(modes, callsign, timestamp)"+
					"VALUES(?,?,systimestamp)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1,callsign.getModeSAddress());
			stmt.setString(2,callsign.getCallSign());


			stmt.executeUpdate();//追加するinsert

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}


	}

	/**
	 * Callsignテーブルの全レコード消去
	 * @throws SQLException DBに接続できなかった場合のエラー
	 */
	public void resetCallSignDB() throws SQLException {
		Statement stmt = null;

		try{

			stmt = con.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE callsign");


		}catch (SQLException e){

			  System.err.println("SQLException:" + e.getMessage());

		}finally{

			if(stmt != null){
				stmt.close();
			}
			DebugPrint.debugPrint("Callsignテーブルリセット完了");
//			System.out.println("Callsignテーブルリセット完了");

		}


	}

	/**
	 * Callsignテーブルの全レコードをcsv形式で保存
	 * @throws SQLException  DBに接続できなかった場合のエラー
	 */
	public void exportCallSignCSV() throws SQLException {
		try {



			DBWriter dbWriter = new DBWriter(con);
			dbWriter.createCSV("callsign");
			DebugPrint.debugPrint("エクスポート完了");
//			System.out.println("エクスポート完了");

		}catch (Exception e) {
			// 何らかのエラーがあっても表示するのみ
			System.err.println("エラーです");
			e.printStackTrace();
		}



	}


}
