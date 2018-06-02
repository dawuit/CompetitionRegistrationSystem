package dao;

public class UserEntity
{
	private String uid;
	private String upassword;
	private String uname;
	private String uauth;


	public UserEntity(String id, String password, String name, String auth) 
	{
		uid = id;
		upassword = password;
		uname = name;
		uauth = auth;
	}
	
	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return uname;
	}

	public void setName(String name) {
		this.uname = name;
	}

	public String getUauth() {
		return uauth;
	}

	public void setUauth(String uauth) {
		this.uauth = uauth;
	}
	
}