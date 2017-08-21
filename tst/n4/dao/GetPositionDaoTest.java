package n4.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import casestudy.DbItem;
import casestudy.Position;

public class GetPositionDaoTest {

	public Connection con;

	@Before
	public void setUp(){
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Test
	public void DB取り出しPosition() {
		GetPositionDao sut = new GetPositionDao(con){
			@Override
			public String getSql(){
				return "select * from position where timestamp in (select max(timestamp) from position group by modes)";
			}
		};
		DbItem[] dbItem = new Position[100];
		try {
			sut.executeQuery(dbItem);
		} catch (SQLException e) {
			fail();
		}
		assertThat(dbItem, notNullValue());

	}

}
