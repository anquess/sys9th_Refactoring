package n4.TG.AircraftAdvisory;

import n4.TG.framework.AircraftFactory;
import n4.TG.framework.AircraftObjList;

public class AircraftAdvisoryObjFactory extends AircraftFactory {

	@Override
	public AircraftObjList createAircaraft() {
		// TODO 自動生成されたメソッド・スタブ
		return new AdvisoryObjList();
	}

}
