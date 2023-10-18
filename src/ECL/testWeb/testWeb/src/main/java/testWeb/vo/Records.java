package testWeb.vo;

public class Records {
	private int recordid;
	private int userid;
	private int robotid;
	private String time;
	private int speed;
	private String imageUrl;
    private int distance;
    private int direction;

    
    public Records() {}
	
	public Records(int recordid,int userid, int robotid, String time, int speed, String imageUrl, int distance, int direction) {
		// TODO Auto-generated constructor stub
		setRecordid(recordid);
		setUserid(userid);
		setRobotid(robotid);
		setTime(time);
		setSpeed(speed);
		setImageUrl(imageUrl);
		setDistance(distance);
		setDirection(direction);
	}
	
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed2) {
		this.speed = speed2;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance2) {
		this.distance = distance2;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction2) {
		this.direction = direction2;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRobotid() {
		return robotid;
	}

	public void setRobotid(int robotid) {
		this.robotid = robotid;
	}

}



