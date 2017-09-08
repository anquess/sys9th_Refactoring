package n4.TG.AircraftXml;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import casestudy.Beans;
import n4.TG.framework.AircraftObj;
import n4.TG.framework.AircraftObjList;


public class XmlObjList extends AircraftObjList {
	private Document document;



	public void makeXml(){

			try {
				document = CreateXmlApi.createXMLDocument("aircrafts");
				Element aircrafts = document.getDocumentElement();
				document.appendChild(aircrafts);

				for (Map.Entry<String, AircraftObj> entry: aircraftList.entrySet()) {
					System.out.println("XML1機分作成");
					Element aircraft = document.createElement("aircraft");
					aircrafts.appendChild(aircraft);
					aircraft.setAttribute("modeSaddress",entry.getValue().getModes());
					aircraft.setAttribute("latitude",String.valueOf(entry.getValue().getLat()));
					aircraft.setAttribute("longitude",String.valueOf(entry.getValue().getLng()));
					aircraft.setAttribute("altitude",String.valueOf(entry.getValue().getAlt()));
					aircraft.setAttribute("timestamp",String.valueOf(entry.getValue().getTimestamp()));

					if(entry.getValue().getCallsign()!=null){
						aircraft.setAttribute("callsign",entry.getValue().getCallsign() );
					}else{
						aircraft.setAttribute("callsign","????????");
					}


					aircraft.setAttribute("h_velo",String.valueOf(entry.getValue().getH_velo()));
					aircraft.setAttribute("v_velo",String.valueOf(entry.getValue().getV_velo()));
					aircraft.setAttribute("h_dir",String.valueOf(entry.getValue().getH_dir()));
					aircraft.setAttribute("v_dir",String.valueOf(entry.getValue().getV_dir()));

				}

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			// XMLファイルの作成
			File file = new File(Beans.getPwd()+"/N1/radar/Aircraft.xml");
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			write(file, document);




	}






	private boolean write(File file, Document document) {

        // Transformerインスタンスの生成
        Transformer transformer = null;
        try {
             TransformerFactory transformerFactory = TransformerFactory
                       .newInstance();
             transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
             e.printStackTrace();
             return false;
        }

        // Transformerの設定
        transformer.setOutputProperty("indent", "yes"); //改行指定
        transformer.setOutputProperty("encoding", "UTF-8"); // エンコーディング

        // XMLファイルの作成
        try {
             transformer.transform(new DOMSource(document), new StreamResult(
                       file));
        } catch (TransformerException e) {
             e.printStackTrace();
             return false;
        }

        return true;
   }

}
