package n4.TG.framework;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import casestudy.Callsign;
import casestudy.DbItem;
import casestudy.Position;
import casestudy.Velocity2;
import n4.dao.ConnectionFactory;
import n4.dao.GetCallSignDao;
import n4.dao.GetPositionDao;
import n4.dao.GetVelocityDao;

public abstract class AircraftFactory {

	private Map<String, List<DbItem>> aircraftList = new HashMap<String, List<DbItem>>();

	public void makeAircraftList() {

		try {

			Connection connection1 = null;
			Connection connection2 = null;
			Connection connection3 = null;

			Velocity2[] velocity = null;
			Position[] position = null;
			Callsign[] callSign = null;

			connection1 = ConnectionFactory.getConnection();
			connection2 = ConnectionFactory.getConnection();
			connection3 = ConnectionFactory.getConnection();

			GetPositionDao positionDao = new GetPositionDao(connection1);
			GetVelocityDao velocityDao = new GetVelocityDao(connection2);
			GetCallSignDao callSignDao = new GetCallSignDao(connection3);
			try {
				position = positionDao.findposi();
				for (int i = 0; i < position.length; i++) {
					aircraftList.put(position[i].getModes(), new ArrayList<DbItem>());
					aircraftList.get(position[i].getModes()).add(position[i]);
				}

			} catch (NullPointerException e) {
				System.err.println("AirCraftSerch 80");
				e.printStackTrace();
			}
			velocity = velocityDao.findvelo(position);
			for (int i = 0; i < velocity.length; i++) {
				if (aircraftList.get(velocity[i].getModes()) != null) {
					aircraftList.get(velocity[i].getModes()).add(velocity[i]);
				}
			}
			callSign = callSignDao.findcall(position);
			for (int i = 0; i < callSign.length; i++) {
				if (aircraftList.get(callSign[i].getModes()) != null) {
					aircraftList.get(callSign[i].getModes()).add(callSign[i]);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public abstract AircraftObj createAircaraft();

	public AircraftObj create(){
		makeAircraftList();
		AircraftObj aircraftObj= createAircaraft();

		return aircraftObj;
	}


}
