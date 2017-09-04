package n4.TG;

import n4.TG.AircraftXml.AircraftXmlFactory;
import n4.TG.AircraftXml.XmlObjList;

public class TgController extends Thread {
	/**
	 * スレッド内の無限ループのon,off
	 * trueなら無限ループ、falseなら無限ループからでる。
	 */
	public boolean dox=true;

	AircraftXmlFactory aircraftXmlFactory = new AircraftXmlFactory();


	public void run(){
		while(dox){
			XmlObjList xmlObjList = (XmlObjList) aircraftXmlFactory.create();

			xmlObjList.makeXml();
		}
	}

}
