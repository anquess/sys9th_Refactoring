package n4.TG.framework;

import java.sql.Timestamp;

public class AircraftObj {

	protected String modes;


	protected Timestamp timestamp;
	protected float lat;
	protected float lng;
	protected float alt;





	protected float h_velo;
	protected float v_velo;


	protected float h_dir;
	protected float v_dir;

	protected String callsign;



	public void setPosition(String modes, Timestamp timestamp, float lat, float lng, float alt) {
		this.modes = modes;
		this.timestamp = timestamp;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
	}



	public void setVelocity(float h_velo, float v_velo, float h_dir, float v_dir) {
		this.h_velo = h_velo;
		this.v_velo = v_velo;
		this.h_dir = h_dir;
		this.v_dir = v_dir;
	}



	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}



	public String getModes() {
		return modes;
	}

	public String getCallsign() {
		return callsign;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}



	public float getLat() {
		return lat;
	}



	public float getLng() {
		return lng;
	}



	public float getAlt() {
		return alt;
	}



	public float getH_velo() {
		return h_velo;
	}



	public float getV_velo() {
		return v_velo;
	}



	public float getH_dir() {
		return h_dir;
	}



	public float getV_dir() {
		return v_dir;
	}



}
