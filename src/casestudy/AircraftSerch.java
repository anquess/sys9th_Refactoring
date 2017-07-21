package casestudy;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * DB-ITEMからXMLを作成するスレッド
 *
 *
 */

public class AircraftSerch  extends Thread {
	/**
	 * スレッド内の無限ループのon,off
	 * trueなら無限ループ、falseなら無限ループからでる。
	 */
	public boolean dox=true;

	/**
	 * DB-ITEMからXMLを作成するメソッド
	 */
	public void run(){
		Connection con = null;
		Velocity2[] velo = null;
		Position[] posi = null;
		Callsign[] call = null;

		while(dox){
			try{
				con = ConnectionManager.getConnection();
				System.out.println("接続完了");

				VelocityDAO veDAO = new VelocityDAO(con);
				PositionDAO poDAO = new PositionDAO(con);
				CallsignDAO caDAO = new CallsignDAO(con);

				posi = poDAO.findposi();
				velo = veDAO.findvelo(posi);
				call = caDAO.findcall(posi);

				Thread.sleep(3000);

				if(posi == null || call == null || velo == null ){
					System.out.println("そのような航空機はいません");

				}else{

					int i=0;
					int k=0;

						if(posi[0]==null){System.out.println("oppaiposi");}
						if(velo[0]==null){System.out.println("oppaivelo");}
						if(call[0]==null){System.out.println("oppaicall");}
						while(posi[i]!=null){
							if(call[i]==null || posi[i]==null || velo[i]==null){System.out.println("Null\n\n");}
							else{
							System.out.println(posi[i].getModes());
							System.out.println(call[i].getCallsign());
							System.out.println(posi[i].getLat());
							System.out.println(posi[i].getLng());
							System.out.println(posi[i].getAlt());
							System.out.println(velo[i].getH_velo());
							System.out.println(velo[i].getV_velo());
							System.out.println(velo[i].getH_dir());
							System.out.println(velo[i].getV_dir());
							System.out.println();
							}
							i++;
						  }

						 // Documentインスタンスの生成
				          DocumentBuilder documentBuilder = null;
				          try {
				               documentBuilder = DocumentBuilderFactory.newInstance()
				                         .newDocumentBuilder();

				          } catch (ParserConfigurationException e) {
				               e.printStackTrace();
				          }
				          Document document = documentBuilder.newDocument();

				          i=0;

				          Element aircrafts = document.createElement("aircrafts");
				          document.appendChild(aircrafts);

				          while(posi[i]!=null){

						  		System.out.println("XML1機分作成");

								Element aircraft = document.createElement("aircraft");
								aircrafts.appendChild(aircraft);
						          aircraft.setAttribute("modeSaddress",posi[i].getModes());
						          if(call[i]!=null){
						          aircraft.setAttribute("callsign",call[i].getCallsign() );
						          }else{aircraft.setAttribute("callsign","????????");}
						          aircraft.setAttribute("latitude",String.valueOf(posi[i].getLat()));
						          aircraft.setAttribute("longitude",String.valueOf(posi[i].getLng()));
						          aircraft.setAttribute("altitude",String.valueOf(posi[i].getAlt()));
						          if(velo[i]!=null){
						          aircraft.setAttribute("h_velo",String.valueOf(velo[i].getH_velo()));
						          aircraft.setAttribute("v_velo",String.valueOf(velo[i].getV_velo()));
						          aircraft.setAttribute("h_dir",String.valueOf(velo[i].getH_dir()));
						          aircraft.setAttribute("v_dir",String.valueOf(velo[i].getV_dir()));
						          }else{
						          aircraft.setAttribute("h_velo","????????");
						          aircraft.setAttribute("v_velo","????????");
						          aircraft.setAttribute("h_dir","????????");
						          aircraft.setAttribute("v_dir","????????");}

						          aircraft.setAttribute("timestamp",String.valueOf(posi[i].getTimestamp()));

						          i++;
				          }



				          // XMLファイルの作成

				          File file = new File(Beans.getPwd()+"/N1/radar/Aircraft.xml");
				          file.delete();
				          file.createNewFile();
				          write(file, document);

				          for(k=0;k<i;k++){
				        	  posi[k]=null;
				        	  velo[k]=null;
				        	  call[k]=null;
				          }


				          i=0;
				          k=0;



				}
			}catch(SQLException | InterruptedException e){
							e.printStackTrace();

			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}

		try{
			if(con != null){
				con.close();
				System.out.println("クローズ完了だよー");
			}
		}catch(SQLException e){
			e.printStackTrace();

		}



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

