package n4.TG.framework;

import java.util.List;
import java.util.Map;

import casestudy.DbItem;

public abstract class AircraftObj {

	private Map<String, List<DbItem>> aircraftList;

	public void setData(Map<String, List<DbItem>> aircraftList) {
		this.aircraftList = aircraftList;

	}



}
