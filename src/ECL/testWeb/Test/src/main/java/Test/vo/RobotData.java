package Test.vo;

public class RobotData {
    private double speed;
    private double distance;
    private String direction;
    private String time;

    public RobotData(double speed, double distance, String direction, String time) {
        this.speed = speed;
        this.distance = distance;
        this.direction = direction;
        this.time = time;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}