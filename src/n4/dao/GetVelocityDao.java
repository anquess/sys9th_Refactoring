package n4.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import casestudy.DbItem;
import casestudy.Position;
import casestudy.Velocity2;

public class GetVelocityDao extends GetDao {

	private  final String SQL = "select * from velocity where timestamp in (select max(timestamp) from velocity group by modes) and modes = ?";

	public Velocity2[] findvelo(Position[] position) throws SQLException {
		Velocity2 velocity[] = new Velocity2[100];
		executeQuery(velocity, position);
		return velocity;
	}

	@Override
	public void executeQuery(DbItem[] velocity, ResultSet result, int i) throws SQLException {

		velocity[i] = new Velocity2(result.getString("modes"),result.getFloat("h_velocity"),
		result.getFloat("v_velocity"),result.getFloat("h_direction"),result.getFloat("v_direction"));

	}

	@Override
	public String getSql() {
		return SQL;
	}

}
