package utils;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UserEntity;

import java.io.*;
/**
 * 2018-2-3 21:15:16
 * @Description: 模拟登陆正方教务获
 * @version: 1.0
 * @author: dawuit
 */

public class BNUZLogin
{
	/**登陆域名*/
	public static final String urlStr= "http://es.bnuz.edu.cn";
	/**默认登陆页面*/
	public static final String loginPath = "/default2.aspx";
	
	public static final String personal = "/xs_main.aspx";

	private String userName;
	private String password;
	/**cookie*/
	private String cookie;
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return String 
	 * 
	 * @throws IOException
	 */
	public UserEntity doLogin(String userName, String password)
	{
		UserEntity user = null;
		try 
		{		
			this.userName = userName;
			this.password = password;
			//创建登陆链接
			HttpURLConnection bnuzCon = (HttpURLConnection)new URL(urlStr + loginPath).openConnection();
			//设置参数
			bnuzCon.setDoOutput(true);
			bnuzCon.setRequestMethod("POST");	
			bnuzCon.setInstanceFollowRedirects(false);
			//禁止304状态码自动跳转
			//打开输入流，写入POST信息
			OutputStreamWriter ow =  new OutputStreamWriter(bnuzCon.getOutputStream(), "UTF-8");
			ow.write("__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUJLTQwNjEzNDEyDxYCHgh1c2VybmFtZWhkZD5HOYLp%2BFwNuDEclUhijthCeycr&__VIEWSTATEGENERATOR=09394A33&__PREVIOUSPAGE=P41Qx-bOUYMcrSUDsalSZQ66PXL-H_8xeQ4t7bJ3gWnYCDI-j8Z8SOoK8eM1&__EVENTVALIDATION=%2FwEWCwKW88jlCgLs0bLrBgLs0fbZDAK%2FwuqQDgKAqenNDQLN7c0VAveMotMNAu6ImbYPArursYYIApXa%2FeQDAoixx8kB7650%2Bpw9oL97V7rb42e1dOnEL9g%3D&TextBox1=" + userName + "&TextBox2="+ password + "&RadioButtonList1=%E5%AD%A6%E7%94%9F&Button4_test=");
			ow.flush();
			//向服务器发起连接请求
			bnuzCon.connect();
			//获取响应头cookie字段（保存sessionId），保持登录状态。
			cookie = (cookie = bnuzCon.getHeaderField("Set-Cookie")).substring(0, cookie.indexOf(';'));
			//打开输入流；读取返回的HTML页面
			HttpURLConnection MainCon = (HttpURLConnection)new URL(urlStr + personal + "?xh=" + userName).openConnection();
			MainCon.setRequestProperty("Cookie", cookie);
			BufferedReader br = new BufferedReader(new InputStreamReader(MainCon.getInputStream(), "UTF-8"));
			StringBuffer strTemp = new StringBuffer();
			String strTempLine;
			while((strTempLine = br.readLine()) != null)
			{
				strTemp.append(strTempLine).append("\n");
			}

			Pattern pattern = Pattern.compile("<span id=\"xhxm\">(.*?)同学</span>");
			
			Matcher matcher = pattern.matcher(strTemp.toString());
			System.out.println(strTemp);
			matcher.find();
			String res = matcher.group(1);
			if(!res.equals(""))
				user = new UserEntity(userName, password, res.split("\\s+")[1], "0");
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return user;
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


		
}

