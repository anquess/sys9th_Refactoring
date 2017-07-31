package analyzer.casestudy;

/**
 * SBS-3から受信した生データを解析処理しやすい形に変換するクラス
 */
public class HexToBinary {

	/**
	 * 16進数かつスペースで区切られて表記されたデータを2進数かつスペース区切りなしのデータに変換する
	 * @param hex 生データ
	 * @return data 変換済みのデータ
	 */
	public static String hexToBinary(String hex){

		String data = new String();
		StringBuilder sb = new StringBuilder();

		for(int j = 0; j < hex.length(); j++){

			if(isHexString(hex.charAt(j))){
				sb.append(asciiToBinaryString4d(hex.charAt(j)));
			}

		}

		data = sb.toString();
		sb.delete(0, sb.length());
		return data;

	}

	private static boolean isHexString(int ch) {
		return ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'f');
	}

	private static String asciiToBinaryString4d(int ch) {
		String binary = String.format("%04d", Integer.parseInt(asciiToBinaryString(ch)));
		return binary;
	}

	private static String asciiToBinaryString(int ch) {
		String binary = Integer.toBinaryString(Integer.parseInt(asciiIntToString(ch), 16));
		return binary;
	}

	private static String asciiIntToString(int ch){
		return String.valueOf((char)ch);
	}



}
