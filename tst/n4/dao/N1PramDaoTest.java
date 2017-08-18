package n4.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class N1PramDaoTest {

	@Test
	public void 接続テスト() {
		N1PramDao sut = new N1PramDao() {
		};


		try {
			sut.open();
			sut.close();
		} catch (SQLException e) {
			fail();
		}
	}

}
