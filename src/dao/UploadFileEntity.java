package dao;

import java.util.Date;

public class UploadFileEntity
{
	private String uid;
	private String matchname;   
	private Date uploadtime;
	private String dropboxurl;
	private String boxpassword;
	
	public UploadFileEntity(String uid, String matchname, Date uploadtime, String dropboxurl,
			String boxpassword) 
	{
		this.uid = uid;
		this.matchname = matchname;
		this.uploadtime = uploadtime;
		this.dropboxurl = dropboxurl;
		this.boxpassword = boxpassword;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMatchname() {
		return matchname;
	}

	public void setMatchname(String matchname) {
		this.matchname = matchname;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getDropboxurl() {
		return dropboxurl;
	}

	public void setDropboxurl(String dropboxurl) {
		this.dropboxurl = dropboxurl;
	}

	public String getBoxpassword() {
		return boxpassword;
	}

	public void setBoxpassword(String boxpassword) {
		this.boxpassword = boxpassword;
	}


	
	
}
