package n4.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.junit.Test;

public class ConnectionFactoryTest {

	@Test
	public void test() {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			con.close();

		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}

	}
	@Test
	public void CallSignTableに接続して数を数えるtest() {
		int actual = 0;
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT modes FROM CALLSIGN");
			LinkedList<String> result = new LinkedList<>();
			while(rs.next()){
				result.add(rs.getString(1));
			}
			actual = result.size();

		} catch (Throwable e) {
			e.printStackTrace();
			fail("a");
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
