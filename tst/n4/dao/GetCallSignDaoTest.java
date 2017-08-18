package n4.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import casestudy.Callsign;
import casestudy.DbItem;
import casestudy.Position;

public class GetCallSignDaoTest {

	@Test
	public void DB取り出しCallsign() {
		GetPositionDao posdao = new GetPositionDao(){
			@Override
			public String getSql(){
				return "select * from position where timestamp in (select max(timestamp) from position group by modes)";
			}
		};
		Position[] position = new Position[100];
		try {
			posdao.executeQuery(position);
		} catch (SQLException e) {
			fail();
		}

		GetCallSignDao sut = new GetCallSignDao();
		DbItem[] dbItem = new Callsign[100];
		try {
			sut.executeQuery(dbItem,position);
		} catch (SQLException e) {
			fail();
		}
		assertThat(dbItem, notNullValue());

	}

}
