package analyzer.casestudy;

/**
 * タイプコードがDF17のデータを解析するクラス
 */
public class ADS_B_Analyzer {

	private static final int TYPE_CODE_START_POSTION 	= 88;
	private static final int TYPE_CODE_END_POSTION 	= 93;
	private static final int MODES_ADDR_START_POSTION 	= 64;
	private static final int MODES_ADDR_END_POSTION 	= 88;
	private static final int CRC_START_POSTION			= 144;
	private static final int CRC_END_POSTION 			= 168;



	/**
	 * dataからTypeCcodeを抽出
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return タイプコード番号
	 */
	public static int tc_Analyze(String data) {
		return Integer.parseInt(data.substring(TYPE_CODE_START_POSTION,TYPE_CODE_END_POSTION), 2);
	}

	/**
	 * dataからモードSアドレスを抽出
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return モードSアドレス
	 */
	public static String modeS_Analyze(String data) {
		 return Integer.toHexString(Integer.parseInt(data.substring(MODES_ADDR_START_POSTION,MODES_ADDR_END_POSTION), 2));
	}

	/**
	 * dataにパリティチェックのエラーがあるか確認
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return パリティチェックの結果
	 */
	public static boolean parityCheck(String data){
		return Integer.parseInt(data.substring(CRC_START_POSTION, CRC_END_POSTION),2) == 0;
	}


}
