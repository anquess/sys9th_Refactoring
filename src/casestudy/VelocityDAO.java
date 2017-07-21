package casestudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import analyzer.casestudy.DB_Item_Velocity;

/**
 *
 * VelocityテーブルのDAO
 *
 */
public class VelocityDAO {

	private  Connection con;

	/**
	 *
	 * @param con DB接続のための設定情報
	 */
	public VelocityDAO(Connection con) {
		this.con = con;
	}

	/**
	 * DBから取得したPosition情報に対応するvelosity情報を取得するメソッド
	 * @param posi 画面に表示する全航空機の位置情報（配列）
	 * @return posiに対応したVelocityの配列
	 * @throws SQLException DBに接続できなかった場合のエラー
	 */
	public Velocity2[] findvelo(Position[] posi) throws SQLException {


		String sql = "select * from velocity where timestamp in (select max(timestamp) from velocity group by modes) and modes = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = null;
		Velocity2[] velo = new Velocity2[100];

		int i=0;
		try{

			while(posi[i]!=null){

				//stmt =
				stmt.setString(1,posi[i].getModes());
				res = stmt.executeQuery();//sqlをとってくる
				if(res.next()){
					velo[i] = new Velocity2(//列の名前を書く.その列のデータをとってきてくれる
							res.getString("modes"),
							res.getFloat("h_velocity"),
							res.getFloat("v_velocity"),
							res.getFloat("h_direction"),
							res.getFloat("v_direction")
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
		return velo;
	}

	/**
	 * DBにVelocity情報を格納するメソッド
	 * @param velocity 解析されたVelocity情報
	 * @throws SQLException DBに接続できなかった場合のエラー
	 */
	public void insertvelocity(DB_Item_Velocity velocity) throws SQLException{
		String sql = "INSERT INTO velocity(modes, H_velocity, V_velocity, H_direction, V_direction, timestamp)"+
					"VALUES(?,?,?,?,?,systimestamp)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1,velocity.getModeSAddress());
			stmt.setDouble(2,velocity.getVelocity().getVel());
			stmt.setDouble(3,velocity.getVelocity().getVr());
			stmt.setInt(4,velocity.getVelocity().getDeg());
			stmt.setInt(5,velocity.getVelocity().getS_Vr());
//			stmt.setLong(6,velocity.getTimeStamp());

			stmt.executeUpdate();//追加するinsert

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}


	}

	/**
	 * Velocityテーブルの全レコード消去
	 * @throws SQLException DBに接続できなかった場合のエラー
	 */
	public void resetVelocityDB()  throws SQLException {
		Statement stmt = null;

		try{

			stmt = con.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE velocity");


		}catch (SQLException e){

			  System.out.println("SQLException:" + e.getMessage());

		}finally{

			if(stmt != null){
				stmt.close();
			}
			System.out.println("Velocityテーブルリセット完了");

		}

	}

	/**
	 * Velocityテーブルの全レコードをcsv形式で保存
	 * @throws SQLException  DBに接続できなかった場合のエラー
	 */
	public void exportVelocityCSV() throws SQLException {
		try {



			DBWriter dbWriter = new DBWriter(con);

			dbWriter.createCSV("velocity");

			System.out.println("エクスポート完了");

		}catch (Exception e) {
			// 何らかのエラーがあっても表示するのみ
			System.out.println("エラーです");
			e.printStackTrace();
		}



	}
}
