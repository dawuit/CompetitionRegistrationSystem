package run;

import java.util.List;

import javax.swing.JOptionPane;

import dao.EnrollEntity;
import dao.MatchEntity;
import dao.UploadFileEntity;
import dao.UserEntity;
import view.EnrollFrame;
import view.EnrollListFrame;
import view.IssueMacthFrame;
import view.LoginFrame;
import view.PersonalFrame;
import view.UploadFileFrame;
import view.WelcomeFrame;

/**
 * 程序入口 单例
 * @author W.J.H
 * @date 2018年5月31日
 */
public class Main
{
	//窗体
	private LoginFrame loginFrame;
	private WelcomeFrame welcomeFrame;
	private PersonalFrame personalFrame;
	private EnrollFrame enrollFrame;
	private UploadFileFrame uploadFileFrame;
	private IssueMacthFrame issueMacthFrame;
	private EnrollListFrame enrollListFrame;
	private static Main mainObject = null;
	
	//数据
	private List<MatchEntity> matchEntities;
	private UserEntity userEntity;
	private EnrollEntity enrollEntity;
	private UploadFileEntity uploadFileEntity;
	
	public static Main getInstance()
	{
		if(mainObject == null)
			mainObject = new Main();
		return mainObject;
	}
	
	private Main()
	{
		loginFrame = new LoginFrame();
		welcomeFrame = new WelcomeFrame();
		personalFrame = new PersonalFrame();
		enrollFrame = new EnrollFrame();
		uploadFileFrame = new UploadFileFrame();
		issueMacthFrame = new IssueMacthFrame();
		enrollListFrame = new EnrollListFrame();
	}
	
	
	
	public EnrollListFrame getEnrollListFrame() {
		return enrollListFrame;
	}

	public void setEnrollListFrame(EnrollListFrame enrollListFrame) {
		this.enrollListFrame = enrollListFrame;
	}

	public LoginFrame getLoginFrame() {
		return loginFrame;
	}

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public WelcomeFrame getWelcomeFrame() {
		return welcomeFrame;
	}

	public void setWelcomeFrame(WelcomeFrame welcomeFrame) {
		this.welcomeFrame = welcomeFrame;
	}

	
	
	public UploadFileFrame getUploadFileFrame() {
		return uploadFileFrame;
	}

	public void setUploadFileFrame(UploadFileFrame uploadFileFrame) {
		this.uploadFileFrame = uploadFileFrame;
	}

	public PersonalFrame getPersonalFrame() {
		return personalFrame;
	}

	public void setPersonalFrame(PersonalFrame personalFrame) {
		this.personalFrame = personalFrame;
	}

	public List<MatchEntity> getMatchEntities() {
		return matchEntities;
	}
	

	public void setMatchEntities(List<MatchEntity> matchEntities) {
		this.matchEntities = matchEntities;
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	
	
	public IssueMacthFrame getIssueMacthFrame() {
		return issueMacthFrame;
	}

	public void setIssueMacthFrame(IssueMacthFrame issueMacthFrame) {
		this.issueMacthFrame = issueMacthFrame;
	}

	public EnrollFrame getEnrollFrame() {
		return enrollFrame;
	}

	public void setEnrollFrame(EnrollFrame enrollFrame) {
		this.enrollFrame = enrollFrame;
	}

	public static Main getMainObject() {
		return mainObject;
	}

	public static void setMainObject(Main mainObject) {
		Main.mainObject = mainObject;
	}

	public EnrollEntity getEnrollEntity() {
		return enrollEntity;
	}

	public void setEnrollEntity(EnrollEntity enrollEntity) {
		this.enrollEntity = enrollEntity;
	}

	public UploadFileEntity getUploadFileEntity() {
		return uploadFileEntity;
	}

	public void setUploadFileEntity(UploadFileEntity uploadFileEntity) {
		this.uploadFileEntity = uploadFileEntity;
	}

	public static void main(String[] args)
	{
		Main process = Main.getInstance();
		process.getWelcomeFrame().setVisible(true);
		if(process.getWelcomeFrame().setMatchData() == false)
		{
	        JOptionPane.showMessageDialog(process.getWelcomeFrame(), "暂无比赛数据, 程序退出。");
		}
	}
	
	
}
