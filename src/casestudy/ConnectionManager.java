package casestudy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * DBに接続するための設定管理クラス
 *
 */
public class ConnectionManager {
	/** データベース接続URL */
	private static final String URL = "jdbc:oracle:thin:@192.168.1.17:1521/ASCPDB01";
	/** ユーザー名 */
	private static final String USER = "ASC45th";
	/** パスワード */
	private static final String PASSWORD = "system";


	/**
	 * データベースの接続を取得する。
	 * @return データベースの接続
	 * @throws Exception
	 */
	public static synchronized Connection getConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println("接続失敗");
			e.printStackTrace();
			throw e;
		}

		return con;
	}
}
