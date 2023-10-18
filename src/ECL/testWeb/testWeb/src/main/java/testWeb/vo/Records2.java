package testWeb.vo;


public class Records2 {
	
	private int recordid;
	private String time;
	private String speed;
    private String distance;
    private String direction;
    
	public Records2() {}
	
	public Records2(int recordid, String time, String speed, String distance, String direction) {
		// TODO Auto-generated constructor stub
		setRecordid2(recordid);
		setTime2(time);
		setSpeed2(speed);
		setDistance2(distance);
		setDirection2(direction);
	}
	
	public int getRecordid2() {
		return recordid;
	}
	public void setRecordid2(int recordid) {
		this.recordid = recordid;
	}
	public String getTime2() {
		return time;
	}
	public void setTime2(String time) {
		this.time = time;
	}
	public String getSpeed2() {
		return speed;
	}
	public void setSpeed2(String speed) {
		this.speed = speed;
	}

	public String getDistance2() {
		return distance;
	}

	public void setDistance2(String distance) {
		this.distance = distance;
	}
	
	public String getDirection2() {
		return direction;
	}

	public void setDirection2(String direction) {
		this.direction = direction;
	}
}
