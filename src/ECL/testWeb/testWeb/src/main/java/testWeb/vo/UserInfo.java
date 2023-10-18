package testWeb.vo;

public class UserInfo {
	
private String userinfoid;	
private String username;
private String password;
private String gender;
private String email;
private int robotid;
public String getUserinfoid() {
	return userinfoid;
}
public void setUserinfoid(String userinfoid) {
	this.userinfoid = userinfoid;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getRobotid() {
	return robotid;
}
public void setRobotid(int robotid) {
	this.robotid = robotid;
}
}
