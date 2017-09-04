package n4.TG.framework;

import java.util.HashMap;
import java.util.Map;

public abstract class AircraftObjList {
	protected Map<String, AircraftObj> aircraftList = new HashMap<String, AircraftObj>();

	public void appendAircraft(AircraftObj aircraftObj){
		aircraftList.put(aircraftObj.getModes(), aircraftObj);
	}

}
