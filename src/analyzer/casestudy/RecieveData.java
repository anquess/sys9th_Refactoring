package analyzer.casestudy;

public class RecieveData {
	private String hexRawData;

	public RecieveData(String hex){
		this.hexRawData = hex;
	}
	/**
	 * 16進数かつスペースで区切られて表記されたデータを2進数かつスペース区切りなしのデータに変換する
	 * @param hex 生データ
	 * @return data 変換済みのデータ
	 */
	public String hexToBinary(){

		StringBuilder sb = new StringBuilder();

		for(int j = 0; j < this.hexRawData.length(); j++){
			if(isHexString(this.hexRawData.charAt(j))){
				sb.append(asciiToBinaryString4d(this.hexRawData.charAt(j)));
			}
		}

		return sb.toString();
	}

	private boolean isHexString(int ch) {
		return ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'f');
	}

	private String asciiToBinaryString4d(int ch) {
		String binary = String.format("%04d", Integer.parseInt(asciiToBinaryString(ch)));
		return binary;
	}

	private String asciiToBinaryString(int ch) {
		String binary = Integer.toBinaryString(Integer.parseInt(asciiIntToString(ch), 16));
		return binary;
	}

	private String asciiIntToString(int ch){
		return String.valueOf((char)ch);
	}



}
