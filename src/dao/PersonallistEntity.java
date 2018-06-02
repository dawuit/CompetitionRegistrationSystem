package dao;

import java.util.Date;

public class PersonallistEntity 
{
	private String uid;
	private String name;
	private Date enrolltime;
	private Date uploadtime;
	
	public PersonallistEntity(String uid, String name, Date enrolltime, Date uploadtime)
	{
		super();
		this.uid = uid;
		this.name = name;
		this.enrolltime = enrolltime;
		this.uploadtime = uploadtime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEnrolltime() {
		return enrolltime;
	}

	public void setEnrolltime(Date enrolltime) {
		this.enrolltime = enrolltime;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	
}
