package casestudy;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
		Connection   connection = null;


		try{
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			connection = ds.getConnection();
			System.out.println("接続完了call");

			CallsignDAO InsCallsign =new CallsignDAO(connection);
			InsCallsign.insertcallsign(callsign);

		}catch (SQLException e) {
			System.out.println("1登録に失敗しました");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			if(connection != null){
				connection.close();
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
		Connection   connection = null;

		try{
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			connection = ds.getConnection();
			System.out.println("接続完了posi");

			PositionDAO InsPosition =new PositionDAO(connection);
			InsPosition.insertposition(position);

		}catch (SQLException e) {
			System.out.println("2登録に失敗しました");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			if(connection != null){
				connection.close();
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
		Connection   connection = null;

		try{
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			connection = ds.getConnection();
			System.out.println("接続完了velo");

			VelocityDAO InsVelocity =new VelocityDAO(connection);
			InsVelocity.insertvelocity(velocity);

		}catch (SQLException e) {
			System.out.println("3登録に失敗しました");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			if(connection != null){
				connection.close();
				System.out.println("クローズ完了");
			}
		} catch(SQLException e) {
				e.printStackTrace();
		}

		System.out.println("以下の内容を登録しました");
		System.out.println("modeS=" + velocity.getModeSAddress());
		System.out.println("S_Vr=" + velocity.getVelocity().getS_Vr());
		System.out.println("Vr=" + velocity.getVelocity().getVr());
		System.out.println("Deg=" + velocity.getVelocity().getDeg());
		System.out.println("Vel=" + velocity.getVelocity().getVel());

	}
}
