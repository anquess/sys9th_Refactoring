package n4.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import casestudy.DbItem;
import casestudy.Position;

public class GetPositionDao extends N1PramDao {

	private  final String SQL = "select * from position where timestamp in (select max(timestamp) from position group by modes)"
				+ "and  timestamp > systimestamp-0.0008";

	public Position[] findposi() throws SQLException {
		Position position[] = new Position[100];
		try{
			open();
			super.setSql(SQL);
			PreparedStatement stmt = super.getStmt();
			ResultSet result = stmt.executeQuery();

			executeQuery(position, result);

		}finally{
			close();

		}
		return position;
	}

	public void executeQuery(DbItem[] position, ResultSet result) throws SQLException {
		int i=0;
		while(result.next()){

			position[i] = new Position(
					result.getString("modes"),
					result.getFloat("latitude"),
					result.getFloat("longitude"),
					result.getFloat("altitude"),
					result.getTimestamp("timestamp")
					);
			i++;

		}
	}

}
