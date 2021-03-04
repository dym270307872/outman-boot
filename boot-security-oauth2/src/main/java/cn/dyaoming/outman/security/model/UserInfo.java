package cn.dyaoming.outman.security.model;

public class UserInfo {

	private String userId;
	private String userName;
	private String password;
	private String salt;

	public UserInfo() {

	}

	public UserInfo(String userId, String userName, String password, String salt) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.salt = salt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
