package dao;
import java.util.Date;

public class MatchEntity 
{
	private Date enrolltime;
	private Date uploadtime;
	private Date issuetime;
	private String matchname;
	private String info;
	
	public MatchEntity(Date enrolltime, Date uploadtime, Date issuetime, String matchname, String info) 
	{
		this.enrolltime = enrolltime;
		this.uploadtime = uploadtime;
		this.issuetime = issuetime;
		this.matchname = matchname;
		this.info = info;
	}


	public Date getEnrolltime() 
	{
		return enrolltime;
	}
	
	public void setEnrolltime(Date enrolltime) 
	{
		this.enrolltime = enrolltime;
	}
	
	public Date getUploadtime() 
	{
		return uploadtime;
	}
	
	public void setUploadtime(Date uploadtime) 
	{
		this.uploadtime = uploadtime;
	}
	
	public Date getIssuetime() 
	{
		return issuetime;
	}
	
	public void setIssuetime(Date issuetime) 
	{
		this.issuetime = issuetime;
	}
	
	public String getMatchname() 
	{
		return matchname;
	}
	
	public void setMatchname(String matchname) 
	{
		this.matchname = matchname;
	}


	public String getInfo() 
	{
		return info;
	}


	public void setInfo(String info) 
	{
		this.info = info;
	}
	
	
	
}
