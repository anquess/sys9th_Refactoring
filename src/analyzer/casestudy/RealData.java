package analyzer.casestudy;

/**
 * ADS-Bデータを受信し解析処理を呼び出すクラス
 */
public class RealData extends Thread{

	/**	SBS-3 IPアドレス */
	final static String ipAddress = "192.168.3.171";

	/** SBS-3 ポート番号 */
	final static int portNum = 10001;

	public boolean doin = true;

	public void run(){
		SensorAccessObject sao = new SensorAccessObject(ipAddress, portNum);

		/* SBS-3と接続 */
		sao.connect();
		System.out.println("*** SBS-3 接続 ***");

		try{

			/* データを受信し解析処理を呼び出す */
			while(doin){
				String hex = sao.readSensor();
				EvenAndOddMatcher.analyzeData(hex);
			}

		}finally{

			/* SBS-3との接続を切断 */
			sao.close();
			System.out.println("*** SBS-3 切断 ***");
		}

	}

	public boolean isDoin() {
		return doin;
	}

	public void setDoin(boolean doin) {
		this.doin = doin;
	}

}
