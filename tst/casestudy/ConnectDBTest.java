package casestudy;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import n4.dao.ConnectionFactory;

public class ConnectDBTest {

	@Test
	public void DBとの接続クローズを行うテスト() {

		Connection con = null;

		try{
			con = ConnectionFactory.getConnection();
			System.out.println("DB接続成功");

		}catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		try {
			if(con != null){
				con.close();
				System.out.println("DBクローズ成功");
			}
		} catch(SQLException e) {
			System.out.println("クローズに失敗しました");
			e.printStackTrace();
		}

	}

}
