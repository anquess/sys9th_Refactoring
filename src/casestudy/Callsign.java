package casestudy;


/**
 *
 *DBから取り出したCallsign情報を管理するbeans
 *
 */
public class Callsign extends DbItem {



	private String callsign;

	private String modes;

	/**
	 *
	 * @param modes modesaddress
	 * @param callsign callsign
	 */
	public Callsign(String modes, String callsign) {
		this.modes = modes;
		this.callsign = callsign;
	}




	public String getModes() {
		return modes;
	}


	public void setModes(String modes) {
		this.modes = modes;
	}


	public String getCallsign() {
		return callsign;
	}


	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}


}
