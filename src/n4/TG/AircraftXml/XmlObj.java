package n4.TG.AircraftXml;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import n4.TG.framework.AircraftObj;


public class XmlObj extends AircraftObj {

	public void makeXml(){
		Document document;
		try {
			document = CreateXmlApi.createXMLDocument("aircrafts");
			Element aircrafts = document.getDocumentElement();
			document.appendChild(aircrafts);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		while([i]!=null){
			System.out.println("XML1機分作成");
			Element aircraft = document.createElement("aircraft");
			aircrafts.appendChild(aircraft);
			aircraft.setAttribute("modeSaddress",position[i].getModes());

			if(callSign[i]!=null){
				aircraft.setAttribute("callsign",callSign[i].getCallsign() );
			}else{
				aircraft.setAttribute("callsign","????????");
			}

			aircraft.setAttribute("latitude",String.valueOf(position[i].getLat()));
			aircraft.setAttribute("longitude",String.valueOf(position[i].getLng()));
			aircraft.setAttribute("altitude",String.valueOf(position[i].getAlt()));

			veloSetAttribute(velocity[i], aircraft);

			aircraft.setAttribute("timestamp",String.valueOf(position[i].getTimestamp()));
			i++;
		}

	}

}
