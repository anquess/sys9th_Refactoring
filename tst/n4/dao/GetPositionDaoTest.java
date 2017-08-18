package n4.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import casestudy.DbItem;
import casestudy.Position;

public class GetPositionDaoTest {

	@Test
	public void DB取り出しPosition() {
		GetPositionDao sut = new GetPositionDao(){
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
