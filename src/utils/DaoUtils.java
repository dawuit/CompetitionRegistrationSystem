package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import dao.EnrollEntity;
import dao.MatchEntity;
import dao.PersonallistEntity;
import dao.UploadFileEntity;
import dao.UserEntity;

/**
 * 数据操纵层
 * @author W.J.H
 * @date 2018年5月31日
 */
public class DaoUtils
{
	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	public static UserEntity selectUser(UserEntity user)
	{
		UserEntity resUser = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement("select * from user where uid = ? and upassword = md5(?)");
			ps.setString(1, user.getUid());
			ps.setString(2, user.getUpassword());
			res = ps.executeQuery();
			if(res.next())
			{
				String id =res.getString("uid");
				String name =res.getString("uname");
				String auth =res.getString("uauth");
				resUser = new UserEntity(id, null, name, auth);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, ps, conn);
		}
			
		return resUser;
	}
	
	/**
	 * 插入用户
	 * @param user
	 * @return
	 */
	public static int insertUser(UserEntity user)
	{
		int resCode = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			//删除原来的记录
			ps = conn.prepareStatement("delete from user where uid=?");
			ps.setString(1, user.getUid());
			ps.executeUpdate();
			ps.close();
			
			ps = conn.prepareStatement("insert into user values(?, md5(?), ?, ?)");
			ps.setString(1, user.getUid());
			ps.setString(2, user.getUpassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getUauth());
			resCode = ps.executeUpdate();
		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(null, ps, conn);
		}
			
		return resCode;
	}
	
	/**
	 * 新建比赛信息
	 * @param match
	 * @return
	 */
	public static int insertMatchType(MatchEntity match)
	{
		int resCode = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement("insert into matchtype values(?,?,?,?,?)");
			ps.setString(1, match.getMatchname());
			ps.setTimestamp(2, new Timestamp(match.getEnrolltime().getTime()));
			ps.setTimestamp(3, new Timestamp(match.getUploadtime().getTime()));
			ps.setTimestamp(4, new Timestamp(match.getIssuetime().getTime()));
			ps.setString(5, match.getInfo());
			resCode = ps.executeUpdate();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, ps, conn);
		}
		return resCode;
	}
	
	/**
	 * 查询报名情况
	 * @param match
	 * @return
	 */
	public static ArrayList<EnrollEntity> selectAllEnrollTable(MatchEntity match)
	{
		ArrayList<EnrollEntity> resEnroll = new ArrayList<EnrollEntity>();
		Connection conn = null;
		PreparedStatement sp = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			sp = conn.prepareStatement("SELECT enroll.uid, enroll.matchname, enroll.uploadtime, enroll.projectname"
					+ " enroll.teamname, enroll.teachername, enroll.name1, number1,grade1, qq1, wechat1, phone1,"
					+ " dropboxurl, boxpassword FROM enroll, enrollwork WHERE enroll.uid=enrollwork.uid AND "
					+ " enroll.matchname=?");
			sp.setString(1, match.getMatchname());
			res = sp.executeQuery();
			while(res.next())
			{
				String uid = res.getString(1);
				String matchname = res.getString(2);
				Date uploadtime = new Date(res.getTimestamp(3).getTime());
				String projectname = res.getString(4);
				String teamname = res.getString(5);
				String teachername = res.getString(6);
				String name = res.getString(7);
				String number = res.getString(8);
				String grade = res.getString(9);
				String qq = res.getString(10);
				String wechat =res.getString(11);
				String phone = res.getString(12);
				String dropboxurl = res.getString(13);
				String boxpassword = res.getString(14);
				resEnroll.add(new EnrollEntity(uid, matchname, uploadtime, projectname, teamname, teachername, name, number, grade, qq, wechat, phone, dropboxurl, boxpassword));
			}
				
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, sp, conn);
		}
		return resEnroll;
	}
	
	/**
	 * 查询比赛项目列表
	 * @return
	 */
	public static ArrayList<MatchEntity> selectMatchType()
	{
		ArrayList<MatchEntity> resMatchTypes = new ArrayList<MatchEntity>();
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			res = st.executeQuery("select * from matchtype order by issuetime desc");
			while(res.next())
			{
				Date enrolltime = new Date(res.getTimestamp("enrolltime").getTime());
				Date uploadtime = new Date(res.getTimestamp("uploadtime").getTime());
				Date issuetime = new Date(res.getTimestamp("issuetime").getTime());
				String matchname = res.getString("matchname");
				String info = (res.getString("info") == null ? "" : res.getString("info"));
				resMatchTypes.add(new MatchEntity(enrolltime, uploadtime, issuetime, matchname, info));
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, st, conn);
		}
		return resMatchTypes;
	}
	
	/**
	 * 修改比赛项目信息
	 * @param match
	 * @return
	 */
	public static int alterMatchType(MatchEntity match)
	{
		int resCode = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement("update matchtype set enrolltime=?, uploadtime=?, info=? where matchname=?");
			ps.setTimestamp(1, new Timestamp(match.getEnrolltime().getTime()));
			ps.setTimestamp(2, new Timestamp(match.getUploadtime().getTime()));
			ps.setString(3, match.getInfo());
			ps.setString(4, match.getMatchname());
			resCode = ps.executeUpdate();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, ps, conn);
		}
		return resCode;
	}
	
	/**
	 * 鎻掑叆琛ㄦ槑琛�
	 * @param enroll
	 * @return
	 */
	public static int insertEnrollTable(EnrollEntity enroll)
	{
		Connection conn = null;
		PreparedStatement sp = null;
		ResultSet res = null;
		int resCode = 0;
		try 
		{
			conn = JdbcUtils.getConnection();
			sp = conn.prepareStatement("insert into enroll (uid,matchname,uploadtime,projectname,"
					+ "teamname,teachername,name1,number1,grade1, phone1) values(?,?,?,?,?,?,?,?,?,?)");
			sp.setString(1, enroll.getUid());
			sp.setString(2, enroll.getMatchname());
			sp.setTimestamp(3, new Timestamp(enroll.getUploadtime().getTime()));
			sp.setString(4, enroll.getProjectname());
			sp.setString(5, enroll.getTeamname());
			sp.setString(6, enroll.getTeachername());
			sp.setString(7, enroll.getName());
			sp.setString(8, enroll.getNumber());
			sp.setString(9, enroll.getGrade());
			sp.setString(10, enroll.getPhone());
			resCode = sp.executeUpdate();		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, sp, conn);
		}
		return resCode;
	}
	

	/**
	 * 查询报名信息
	 * @param user
	 * @param match
	 * @return
	 */
	public static EnrollEntity selectEnrollTable(UserEntity user, MatchEntity match)
	{
		EnrollEntity resEnroll = null;
		Connection conn = null;
		PreparedStatement sp = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			sp = conn.prepareStatement("select * from enroll where uid=? and matchname=?");
			sp.setString(1, user.getUid());
			sp.setString(2, match.getMatchname());
			res = sp.executeQuery();
			if(res.next())
			{
				String uid = res.getString(1);
				String matchname = res.getString(2);
				Date uploadtime = new Date(res.getTimestamp(3).getTime());
				String projectname = res.getString(4);
				String teamname = res.getString(5);
				String teachername = res.getString(6);
				String name = res.getString(7);
				String number = res.getString(8);
				String grade = res.getString(9);
				String qq = res.getString(10);
				String wechat =res.getString(11);
				String phone = res.getString(12);
				resEnroll = new EnrollEntity(uid, matchname, uploadtime, projectname, teamname, teachername, name, number, grade, qq, wechat, phone, null,null);
			}
				
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, sp, conn);
		}
		return resEnroll;
	}
	
	/**
	 * 
	 * @param match
	 * @return
	 */
	public static ArrayList<PersonallistEntity> selectPersonal(MatchEntity match)
	{
		ArrayList<PersonallistEntity> reslist = new ArrayList<PersonallistEntity>();
		Connection conn = null;
		PreparedStatement sp = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			sp = conn.prepareStatement("select A.uid, name1, A.uploadtime as enrolltime,B.uploadtime as worktime from (select uid, name1,uploadtime from enroll where matchname=?) as A left join (select uid, uploadtime from uploadwork where matchname=?) as B  on A.uid = B.uid");
			sp.setString(1, match.getMatchname());
			sp.setString(2, match.getMatchname());
			res = sp.executeQuery();
			while(res.next())
			{
				String uid = res.getString(1);
				String name = res.getString(2);
				Date enrolltime = res.getTimestamp(3) != null ? (new Date(res.getTimestamp(3).getTime())) : null;
				Date uploadtime = res.getTimestamp(4) != null ? (new Date(res.getTimestamp(4).getTime())) : null;
				reslist.add(new PersonallistEntity(uid, name, enrolltime, uploadtime));
			}
				
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, sp, conn);
		}
		return reslist;
	}
	
	/**
	 * 删除报名记录
	 * @param user
	 * @param match
	 * @return
	 */
	public static int deleteEnrollTable(UserEntity user, MatchEntity match)
	{
		int resCode = 0;  
		Connection conn = null;
		PreparedStatement sp = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			sp = conn.prepareStatement("delete from enroll where uid=? and matchname=?");
			sp.setString(1, user.getUid());
			sp.setString(2, match.getMatchname());
			resCode = sp.executeUpdate();
				
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, sp, conn);
		}
		return resCode;
	}
	
	/**
	 * 插入文件上传记录
	 * @param work
	 * @return
	 */
	public static int insertUploadWork(UploadFileEntity work)
	{
		int resCode = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement("insert into uploadwork values(?,?,?,?,?)");
			ps.setString(1, work.getUid());
			ps.setString(2, work.getMatchname());
			ps.setTimestamp(3, new Timestamp(work.getUploadtime().getTime()));
			ps.setString(4, work.getDropboxurl());
			ps.setString(5, work.getBoxpassword());
			resCode = ps.executeUpdate();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, ps, conn);
		}
		return resCode;
	}
	
	/**
	 * 更新上传记录
	 * @param user
	 * @param match
	 * @return
	 */
	public static UploadFileEntity selectUploadWork(UserEntity user, MatchEntity match)
	{
		UploadFileEntity reswork = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement("select * from uploadwork where uid=? and matchname=?");
			ps.setString(1, user.getUid());
			ps.setString(2, match.getMatchname());
			res = ps.executeQuery();
			if(res.next())
			{
				String uid = res.getString("uid");
				String matchname = res.getString("matchname");   
				Date uploadtime = new Date(res.getTimestamp("uploadtime").getTime());
				String dropboxurl = res.getString("dropboxurl");
				String boxpassword = res.getString("boxpassword");
				reswork = new UploadFileEntity(uid, matchname, uploadtime, dropboxurl, boxpassword); 
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, ps, conn);
		}
		return reswork;
	}
	

	/**
	 * 删除上传记录
	 * @param user
	 * @param match
	 * @return
	 */
	public static int deleteUploadWork(UserEntity user, MatchEntity match)
	{
		int resCode = 0;  
		Connection conn = null;
		PreparedStatement sp = null;
		ResultSet res = null;
		try 
		{
			conn = JdbcUtils.getConnection();
			sp = conn.prepareStatement("delete from uploadwork where uid=? and matchname=?");
			sp.setString(1, user.getUid());
			sp.setString(2, match.getMatchname());
			resCode = sp.executeUpdate();
				
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			JdbcUtils.free(res, sp, conn);
		}
		return resCode;
	}
	
}


