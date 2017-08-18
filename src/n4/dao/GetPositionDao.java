package n4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import casestudy.DbItem;
import casestudy.Position;

public class GetPositionDao extends GetDao {

	private  final String SQL = "select * from position where timestamp in (select max(timestamp) from position group by modes)"
				+ "and  timestamp > systimestamp-0.0008";

	public Position[] findposi() throws SQLException {
		Position position[] = new Position[100];
		executeQuery(position);
		return position;
	}



	@Override
	public void executeQuery(DbItem[] position, ResultSet result,int i) throws SQLException {
		position[i] = new Position(	result.getString("modes"),result.getFloat("latitude"),
				result.getFloat("longitude"),result.getFloat("altitude"),result.getTimestamp("timestamp"));
	}

	@Override
	public String getSql(){
		return SQL;
	}


}
