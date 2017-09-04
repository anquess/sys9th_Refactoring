package n4.TG.framework;

import java.sql.Connection;
import java.sql.SQLException;

import casestudy.Callsign;
import casestudy.Position;
import casestudy.Velocity2;
import n4.dao.ConnectionFactory;
import n4.dao.GetCallSignDao;
import n4.dao.GetPositionDao;
import n4.dao.GetVelocityDao;

public abstract class AircraftFactory extends Thread {

	public void makeAircraftField(AircraftObjList aircraftObjList) {

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
					AircraftObj aircraftObj = new AircraftObj();
					aircraftObj.setPosition(
							position[i].getModes(),
							position[i].getTimestamp(),
							position[i].getLat(),
							position[i].getLng(),
							position[i].getAlt()
						);
					aircraftObjList.appendAircraft(aircraftObj);
				}


			} catch (NullPointerException e) {
				System.err.println("AirCraftSerch 80");
				e.printStackTrace();
			}

			velocity = velocityDao.findvelo(position);
			for (int i = 0; i < velocity.length; i++) {
				if (aircraftObjList.aircraftList.get(velocity[i].getModes()) != null) {
					aircraftObjList.aircraftList.get(velocity[i].getModes()).setVelocity(
							velocity[i].getH_velo(),
							velocity[i].getV_velo(),
							velocity[i].getH_dir(),
							velocity[i].getV_dir()
						);
				}
			}
			callSign = callSignDao.findcall(position);
			for (int i = 0; i < callSign.length; i++) {
				if (aircraftObjList.aircraftList.get(callSign[i].getModes()) != null) {
					aircraftObjList.aircraftList.get(callSign[i].getModes()).setCallsign(
							callSign[i].getCallsign()
						);
				}
			}

			if(connection1 != null){
				connection1.close();
				System.out.println("1クローズ完了だよー");
			}
			if(connection2 != null){
				connection2.close();
				System.out.println("2クローズ完了だよー");
			}
			if(connection3 != null){
				connection3.close();
				System.out.println("3クローズ完了だよー");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public abstract AircraftObjList createAircaraft();

	public AircraftObjList create(){
		AircraftObjList aircraftObjList= createAircaraft();
		makeAircraftField(aircraftObjList);
		return aircraftObjList;
	}


}
