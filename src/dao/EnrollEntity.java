package dao;

import java.util.Date;

public class EnrollEntity 
{
	private String uid;
	private String matchname;   
	private Date uploadtime;
	private String projectname;
	private String teamname;   
	private String teachername;   
	private String name;   
	private String number;   
	private String grade;   
	private String qq;   
	private String wechat;   
	private String phone;   
	private String dropboxurl;
	private String boxpassword;
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
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public EnrollEntity(String uid, String matchname, Date uploadtime, String projectname, String teamname,
			String teachername, String name, String number, String grade, String qq, String wechat, String phone,
			String dropboxurl, String boxpassword) {
		super();
		this.uid = uid;
		this.matchname = matchname;
		this.uploadtime = uploadtime;
		this.projectname = projectname;
		this.teamname = teamname;
		this.teachername = teachername;
		this.name = name;
		this.number = number;
		this.grade = grade;
		this.qq = qq;
		this.wechat = wechat;
		this.phone = phone;
		this.dropboxurl = dropboxurl;
		this.boxpassword = boxpassword;
	}
	
	
	
}
