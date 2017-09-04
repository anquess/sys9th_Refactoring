package n4.TG.AircraftXml;



import n4.TG.framework.AircraftFactory;
import n4.TG.framework.AircraftObjList;

public class AircraftXmlFactory extends AircraftFactory{


	@Override
	public AircraftObjList createAircaraft() {
		return new XmlObjList();
	}

}
