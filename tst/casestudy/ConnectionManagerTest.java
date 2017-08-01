package casestudy;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionManagerTest {

	@Test
	public void test() throws SQLException {
		Connection con = null;
			try {
				con = ConnectionManager.getConnection();
				con.close();

			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
				fail();
			}
	}


}
