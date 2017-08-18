package n4.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import casestudy.Callsign;
import casestudy.DbItem;
import casestudy.Position;

public class GetCallSignDao extends GetDao {
	public GetCallSignDao(Connection connection){
		super(connection);
	}

	private  final String SQL = "select * from callsign where timestamp in (select max(timestamp) from callsign group by modes) and modes = ?";

	public Callsign[] findcall(Position[] position) throws SQLException {
		Callsign callsign[] = new Callsign[100];
		executeQuery(callsign,position);
		return callsign;
	}

	@Override
	public void executeQuery(DbItem[] callsign, ResultSet result, int i) throws SQLException {

		callsign[i] = new Callsign(result.getString("modes"),result.getString("callsign"));

	}

	@Override
	public String getSql() {
		// TODO 自動生成されたメソッド・スタブ
		return SQL;
	}

}
