package casestudy;

/**
*
*DBから取り出したVelocity(水平面の速度・向き　垂直面の速度・上昇下降)情報を管理するbeans
*
*/
public class Velocity2 {


	private String modes;



	private float h_velo;

	private float v_velo;

	private float h_dir;

	private float v_dir;

	/**
	 *
	 * @param modes modesaddress
	 * @param h_velo 水平速度 Knot
	 * @param v_velo 垂直速度 Knot
	 * @param h_dir  水平面進行方向 °
	 * @param v_dir  上昇下降 0の場合は上昇、1の場合は下降
	 */
	public Velocity2(String modes, float h_velo, float v_velo,float h_dir, float v_dir ){
		this.modes = modes;
		this.h_velo = h_velo;
		this.v_velo = v_velo;
		this.h_dir = h_dir;
		this.v_dir = v_dir;
	}

	public String getModes() {
		return modes;
	}

	public void setModes(String modes) {
		this.modes = modes;
	}

	public float getH_velo() {
		return h_velo;
	}

	public void setH_velo(float h_velo) {
		this.h_velo = h_velo;
	}

	public float getV_velo() {
		return v_velo;
	}

	public void setV_velo(float v_velo) {
		this.v_velo = v_velo;
	}

	public float getH_dir() {
		return h_dir;
	}

	public void setH_dir(float h_dir) {
		this.h_dir = h_dir;
	}

	public float getV_dir() {
		return v_dir;
	}

	public void setV_dir(float v_dir) {
		this.v_dir = v_dir;
	}
}
