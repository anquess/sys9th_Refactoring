package analyzer.casestudy;

import static analyzer.casestudy.ADS_B_Analyzer.*;
import static analyzer.casestudy.CallSignFactory.*;
import static analyzer.casestudy.PlanePositionFactory.*;
import static analyzer.casestudy.TypeCode.*;

import java.util.ArrayList;

/**
 * ADSBからのデータを解析するクラス
 */
public class EvenAndOddMatcher {

	/** DELETE_TIME=削除のしきい値(ms) */
	static final long DELETE_TIME = 1000 * 10;

	/** evenデータを格納 */
	static ArrayList<Data> evenDataList = new ArrayList<Data>();

	/** oddデータを格納 */
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	/**
	 * 受信した受信した生データ(16進数表記かつスペースで区切られている)を解析し、各DB-Itemを作る
	 * @param hexRawData
	 * @return 各DB_Item
	 */
	public static String analyzeData(String hexRawData){

		String binaryRawData = HexToBinary.hexToBinary(hexRawData);
		PlanePosition planePosition = null;

		//受信データのパリティチェック
		if(parityCheck(binaryRawData)){

			//ADS-Bデータか判別
			if(judgedADS_B_Data(binaryRawData)){

				if(			createTypeCode(binaryRawData) == CALL_SIGN){
					DB_Item_Generator.dB_Item_Generate(modeS_Analyze(binaryRawData), calcCallSign(binaryRawData));
				}else if(	createTypeCode(binaryRawData) == VELOCITY){
					DB_Item_Generator.dB_Item_Generate(modeS_Analyze(binaryRawData), VelocityFactory.calc_velocity(binaryRawData));
				}else if(	createTypeCode(binaryRawData) == PLANE_POSITION){
					planePosition = rawDataToPlanePosition(binaryRawData);
					if(!(planePosition == null)){
						DB_Item_Generator.dB_Item_Generate(modeS_Analyze(binaryRawData), planePosition);
					}
				}
			}
		}
		return null;
	}


	/**
	 * ダウンリンクフォーマットが17(ADS-Bデータ)か判別する
	 * @param rawData
	 * @return DF(ダウンリンクフォーマット)が17のときtrueを返す
	 */
	private static boolean judgedADS_B_Data(String rawData) {
		return createDownLinkFormatNo(rawData) == 17;
	}

	/**
	 * 受信データ(バイナリ形式)のDF(ダウンリンクフォーマット)を返す
	 * @param data
	 * @return DF(ダウンリンクフォーマット)
	 */
	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}

}