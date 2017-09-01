package n4.TG.AircraftXml;



import n4.TG.framework.AircraftFactory;
import n4.TG.framework.AircraftObj;

public class AircraftXmlFactory extends AircraftFactory{

	@Override
	public AircraftObj createAircaraft() {
		XmlObj xmlObj = new XmlObj();
		return xmlObj;
	}



}
