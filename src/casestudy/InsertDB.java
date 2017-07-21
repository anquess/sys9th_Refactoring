package casestudy;

import java.sql.Connection;
import java.sql.SQLException;

import analyzer.casestudy.DB_Item_CallSign;
import analyzer.casestudy.DB_Item_PlanePosition;
import analyzer.casestudy.DB_Item_Velocity;

/**
 *DAOに接続し、DBにDBーITEMを登録するクラス
 *
 */
public class InsertDB {

	/**
	 * CallsignDAOに接続しCallsignを登録するメソッド
	 * @param callsign 解析されたcallsign情報
	 */
	public static void callSignInsert(DB_Item_CallSign callsign) {
		Connection   con = null;


		try{
			con = ConnectionManager.getConnection();
			System.out.println("接続完了call");

			CallsignDAO InsCallsign =new CallsignDAO(con);
			InsCallsign.insertcallsign(callsign);

		}catch (SQLException e) {
			System.out.println("1登録に失敗しました");
			e.printStackTrace();
		}
		try {
			if(con != null){
				con.close();
				System.out.println("クローズ完了");
			}
		} catch(SQLException e) {
				e.printStackTrace();
		}

		System.out.println("以下の内容を登録しました");
		System.out.println(callsign.getModeSAddress());
		System.out.println(callsign.getCallSign());

	}

	/**
	 * PositionDAOに接続しPositionを登録するメソッド
	 * @param position 解析されたPosition情報
	 */
	public static void positionInsert(DB_Item_PlanePosition position) {
		Connection   con = null;

		try{
			con = ConnectionManager.getConnection();
			System.out.println("接続完了posi");

			PositionDAO InsPosition =new PositionDAO(con);
			InsPosition.insertposition(position);

		}catch (SQLException e) {
			System.out.println("2登録に失敗しました");
			e.printStackTrace();
		}
		try {
			if(con != null){
				con.close();
				System.out.println("クローズ完了");
			}
		} catch(SQLException e) {
				e.printStackTrace();
		}

		System.out.println("以下の内容を登録しました");
		System.out.println(position.getModeSAddress());
		System.out.println(position.getPlanePosition().getLon());
		System.out.println(position.getPlanePosition().getLat());
		System.out.println(position.getPlanePosition().getAlt());
	}

	/**
	 * VelocityDAOに接続しVelocityを登録するメソッド
	 * @param velocity 解析されたVelocity情報
	 */
	public static void velocityInsert(DB_Item_Velocity velocity) {
		Connection   con = null;

		try{
			con = ConnectionManager.getConnection();
			System.out.println("接続完了velo");

			VelocityDAO InsVelocity =new VelocityDAO(con);
			InsVelocity.insertvelocity(velocity);

		}catch (SQLException e) {
			System.out.println("3登録に失敗しました");
			e.printStackTrace();
		}
		try {
			if(con != null){
				con.close();
				System.out.println("クローズ完了");
			}
		} catch(SQLException e) {
				e.printStackTrace();
		}

		System.out.println("以下の内容を登録しました");
		System.out.println(velocity.getModeSAddress());
		System.out.println(velocity.getVelocity().getS_Vr());
		System.out.println(velocity.getVelocity().getVr());
		System.out.println(velocity.getVelocity().getDeg());
		System.out.println(velocity.getVelocity().getVel());

	}
}
