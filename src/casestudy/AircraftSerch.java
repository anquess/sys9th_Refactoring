package casestudy;

import java.io.File;
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

import n4.dao.ConnectionFactory;
import n4.dao.GetCallSignDao;
import n4.dao.GetPositionDao;
import n4.dao.GetVelocityDao;

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
		Connection connection1 = null;
		Connection connection2 = null;
		Connection connection3 = null;

		Velocity2[] velocity = null;
		Position[] position = null;
		Callsign[] callSign = null;

		while(dox){
			try{
	//					Context context = new InitialContext();
	//					DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
				connection1 = ConnectionFactory.getConnection();
				connection2 = ConnectionFactory.getConnection();
				connection3 = ConnectionFactory.getConnection();




				System.out.println("DB読み込み用接続完了");

				GetPositionDao positionDao = new GetPositionDao(connection1);
				GetVelocityDao velocityDao = new GetVelocityDao(connection2);
				GetCallSignDao callSighnDao = new GetCallSignDao(connection3);
				try{
					position = positionDao.findposi();
				}catch(NullPointerException e){
					System.err.println("AirCraftSerch 80");
					e.printStackTrace();
				}
				velocity = velocityDao.findvelo(position);
				callSign = callSighnDao.findcall(position);


//				Thread.sleep(3000);

				if(position == null || callSign == null || velocity == null ){
					System.out.println("そのような航空機はいません");
				}else{
					int i=0;
					int k=0;
						if(position[0]==null){
							System.out.println("Position is null");
						}
						if(velocity[0]==null){
							System.out.println("Velocity is null");
						}
						if(callSign[0]==null){
							System.out.println("CallSign is null");
						}

/*						while(position[i]!=null){
							if(callSign[i]==null || position[i]==null || velocity[i]==null){
								System.out.println("Null\n\n");
							}else{
								System.out.println("DBから読み込み開始");
								System.out.println(position[i].getModes());
								System.out.println(callSign[i].getCallsign());
								System.out.println(position[i].getLat());
								System.out.println(position[i].getLng());
								System.out.println(position[i].getAlt());
								System.out.println(velocity[i].getH_velo());
								System.out.println(velocity[i].getV_velo());
								System.out.println(velocity[i].getH_dir());
								System.out.println(velocity[i].getV_dir());
								System.out.println();
							}
							i++;
						}
*/
						// Documentインスタンスの生成
						DocumentBuilder documentBuilder = null;
						try {
							documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
						} catch (ParserConfigurationException e){
							e.printStackTrace();
						}

						Document document = documentBuilder.newDocument();
						i=0;
						Element aircrafts = document.createElement("aircrafts");
						document.appendChild(aircrafts);

						while(position[i]!=null){
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

						// XMLファイルの作成
						File file = new File(Beans.getPwd()+"/N1/radar/Aircraft.xml");
						file.delete();
						file.createNewFile();
						write(file, document);

						for(k=0;k<i;k++){
							position[k]=null;
							velocity[k]=null;
							callSign[k]=null;
						}

						i=0;
						k=0;
				}
			}catch (Throwable e) {
				System.err.println("AirCraftSerch 178");
				e.printStackTrace();
			}
			try {
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
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}


		}	// While文終了

		try{
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

		}catch(SQLException e){
			System.err.println("AirCraftSerch 197");
			e.printStackTrace();
		}



	}

	private void veloSetAttribute(Velocity2 velo, Element aircraft) {
		String subjects[] = {"h_velo","v_velo","h_dir","v_dir"};
		for(String subject:subjects){
			aircraft.setAttribute(subject,veloToString(velo,subject));
		}
	}
	private String veloToString(Velocity2 velo, String subject){
		if(velo == null){
			return "????????";
		}else{
			return velo.veloToString(subject);
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