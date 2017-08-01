package casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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
	@Test
	public void CallSignTableに接続して数を数えるtest() {
		int actual = 0;
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT modes FROM CALLSIGN");
			LinkedList<String> result = new LinkedList<>();
			while(rs.next()){
				result.add(rs.getString(1));
			}
			actual = result.size();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		assertThat(actual, is(42));

	}



}
