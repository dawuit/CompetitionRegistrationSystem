package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.MatchEntity;
import run.Main;
import utils.DaoUtils;

public class WelcomeFrame extends JFrame
{
	private JLabel titleContentLabel;
	private JLabel titleLabel;
	private JLabel enroll_timeLabel;
	private JLabel upload_timeLabel;
	private JTextArea contentTextArea;
	private JLabel warnContentLabel;
	private JButton loginButton;
	private JPanel panel;
	
	public WelcomeFrame() 
	{
		this.setSize(450, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//字体对象
		Font font = new Font("微软雅黑", 0, 12);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		this.add(panel);
		
		//标题描述控件
		titleContentLabel = new JLabel("报名项目:");
		titleContentLabel.setBounds(50,20,300,30);
		titleContentLabel.setForeground(new Color(255,255,255));
		titleContentLabel.setFont(font); 
		panel.add(titleContentLabel);
		
		//标题控件
		titleLabel = new JLabel("无比赛");
		titleLabel.setBounds(130,20,300,30);
		titleLabel.setForeground(new Color(255,255,255));
		titleLabel.setFont(new Font("微软雅黑", 0, 15)); 
		panel.add(titleLabel);
		
		//报名截止时间
		enroll_timeLabel = new JLabel("上传截止时间: 0000-00-00 00:00:00");
		enroll_timeLabel.setBounds(50,50,300,30);
		enroll_timeLabel.setForeground(new Color(255,255,255));
		enroll_timeLabel.setFont(font); 
		panel.add(enroll_timeLabel);
		
		//上传截止时间
		upload_timeLabel = new JLabel("报名截止时间: 0000-00-00 00:00:00");
		upload_timeLabel.setBounds(50,80,300,30);
		upload_timeLabel.setForeground(new Color(255,255,255));
		upload_timeLabel.setFont(font); 
		panel.add(upload_timeLabel);
		
		//注意事项描述控件
		warnContentLabel = new JLabel("注意事项:");
		warnContentLabel.setBounds(50,105,300,30);
		warnContentLabel.setForeground(new Color(255,255,255));
		warnContentLabel.setFont(font); 
		panel.add(warnContentLabel);
		
		//注意事项控件
		
		contentTextArea = new JTextArea();
		contentTextArea.setText("无数据");
		contentTextArea.setBounds(50,135,330,200);
		contentTextArea.setForeground(new Color(255, 255, 255));
		contentTextArea.setBackground(new Color(80, 80, 80));
		contentTextArea.setEditable(false);
		contentTextArea.setLineWrap(true);
		JScrollPane jp =new JScrollPane(contentTextArea);
		jp.setBounds(50,135,330,200);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(jp);
		
		//创建报名按钮
        loginButton = new JButton("开始报名");
        loginButton.setBounds(50, 350, 330, 40);
        loginButton.setBackground(new Color(52,152,219));
        loginButton.setBorderPainted(false);
        loginButton.setFont(new Font("微软雅黑", 0, 12)); 
        loginButton.setFocusable(false);
        loginButton.setForeground(new Color(255,255,255));
        loginButton.addActionListener(new loginBtnActionListener());
        panel.add(loginButton); 
        
	}
	@Override
	public void setVisible(boolean b)
	{
		setMatchData();
		super.setVisible(b);
	}
	
	/**
	 * 点击报名按钮
	 * @author W.J.H
	 * @date 2018年6月1日
	 */
	public class loginBtnActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Main process = Main.getInstance();
			process.getWelcomeFrame().setEnabled(false);
			process.getLoginFrame().setVisible(true);
			process.getLoginFrame().setLocationRelativeTo(process.getWelcomeFrame());
		}
	}
	
	/**
	 * 读取比赛信息
	 * @return
	 */
	public boolean setMatchData()
	{
		List<MatchEntity> matchEntities = DaoUtils.selectMatchType();
		Main.getInstance().setMatchEntities(matchEntities);
		//没有数据
		if(matchEntities.size() == 0)
		{
			return false;
		}
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		MatchEntity firstMatch = matchEntities.get(0);
		this.titleLabel.setText(firstMatch.getMatchname());
		this.enroll_timeLabel.setText("报名截止时间:  " + fmt.format(firstMatch.getEnrolltime()));
		this.upload_timeLabel.setText("上传截止时间:  " + fmt.format(firstMatch.getUploadtime()));
		this.contentTextArea.setText(firstMatch.getInfo());
		
		return true;
	}
}
