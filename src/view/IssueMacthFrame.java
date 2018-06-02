package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.MatchEntity;
import run.Main;
import utils.DaoUtils;




public class IssueMacthFrame extends JFrame
{
	private JPanel panel;
	
	
	private JLabel matchName;//比赛名称标题
	private JTextField matchText;//比赛名称文本框
	
	private JLabel signUpName;//报名截止时间标题
	private JTextField signUpText;//报名截止时间本框
	
	private JLabel uploadTimeName;//文件上传截止时间文标题
	private JTextField uploadTimeText;//文件上传截止时间文文本框
	
	private JLabel attentionName ;//注意事项标题
	private JTextArea attentionText ;//注意事项文本框
	private JScrollPane attentionTool ;//注意事项滑动条
	
	private JButton releaseButton;//发布按钮
	private JButton backAgainButton;//返回按钮
	private JButton enrollListButton;
	
	
	public IssueMacthFrame() 
	{
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//字体对象
		Font font = new Font("微软雅黑", 0, 15);
		Font fontTitle = new Font("微软雅黑", 0, 20);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		
		this.add(panel);
		
		//用户信息
		this.setTitle("发布");
		

		//比赛名称标题（第一行）
		matchName = new JLabel("比赛名称");
		matchName.setBounds(60, 20, 200, 30);
		matchName.setForeground(new Color(255, 255, 255));
		matchName.setFont(font);
		panel.add(matchName);
		
		//比赛名称文本框（第一行）
		matchText = new JTextField("");
		matchText.setBounds(60, 50, 450, 30);
		matchText.setForeground(new Color(255, 255, 255));
		matchText.setFont(font);
		matchText.setForeground(new Color(50, 50, 50));
		panel.add(matchText);
		

		//报名截止时间标题（第二行）
		signUpName = new JLabel("报名截止时间");
		signUpName.setBounds(60, 90, 200, 30);
		signUpName.setForeground(new Color(255, 255, 255));
		signUpName.setFont(font);
		panel.add(signUpName);
		
		//报名截止时间文本框（第二行）
		signUpText = new JTextField("格式:yyyy-MM-dd hh:mm:ss");
		signUpText.setBounds(60, 120, 210, 30);
		signUpText.setForeground(new Color(255, 255, 255));
		signUpText.setFont(font);
		signUpText.setForeground(new Color(50, 50, 50));
		panel.add(signUpText);
		
		//文件上传截止时间标题（第二行）
		uploadTimeName = new JLabel("文件上传截止时间");
		uploadTimeName.setBounds(300, 90, 200, 30);
		uploadTimeName.setForeground(new Color(255, 255, 255));
		uploadTimeName.setFont(font);
		panel.add(uploadTimeName);
		
		//文件上传截止时间文本框
		uploadTimeText = new JTextField("格式:yyyy-MM-dd hh:mm:ss");
		uploadTimeText.setBounds(300, 120, 210, 30);
		uploadTimeText.setForeground(new Color(255, 255, 255));
		uploadTimeText.setFont(font);
		uploadTimeText.setForeground(new Color(50, 50, 50));
		panel.add(uploadTimeText);
		
		//注意事项标题（第三行）
		attentionName = new JLabel("注意事项");
		attentionName.setBounds(60, 160, 200, 30);
		attentionName.setForeground(new Color(255, 255, 255));
		attentionName.setFont(font);
		panel.add(attentionName);
		
		//注意事项文本框（第三行）
		attentionText = new JTextArea();
		attentionTool= new JScrollPane(attentionText);
		attentionTool.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		attentionTool.setBounds(60, 190,450, 200);
		attentionTool.setForeground(new Color(255, 255, 255));
		attentionTool.setFont(font);
		panel.add(attentionTool);
		
		
		//创建发布按钮
		releaseButton = new JButton("发布");
		releaseButton.setBounds(60, 410,140, 40);
		releaseButton.setBackground(new Color(33,186,69));
		releaseButton.setBorderPainted(false);
		releaseButton.setFont(font);
		releaseButton.setFocusable(false);
		releaseButton.setForeground(new Color(255, 255, 255));
		releaseButton.addActionListener(new IssueActionListener());
		panel.add(releaseButton);
		
		// 创建返回按钮
		backAgainButton = new JButton("返回");
		backAgainButton.setBounds(370, 410,140, 40);
		backAgainButton.setBackground(new Color(219,40,40));
		backAgainButton.setBorderPainted(false);
		backAgainButton.setFont(font);
		backAgainButton.setFocusable(false);
		backAgainButton.setForeground(new Color(255, 255, 255));
		backAgainButton.addActionListener(e -> {
			this.setVisible(false);
			Main.getInstance().getWelcomeFrame().setEnabled(true);
			Main.getInstance().getWelcomeFrame().setVisible(true);
		});
		panel.add(backAgainButton);
		
		// 报名列表按钮
		enrollListButton = new JButton("报名信息");
		enrollListButton.setBounds(215, 410,140, 40);
		enrollListButton.setBackground(new Color(33,186,69));
		enrollListButton.setBorderPainted(false);
		enrollListButton.setFont(font);
		enrollListButton.setFocusable(false);
		enrollListButton.setForeground(new Color(255, 255, 255));
		enrollListButton.addActionListener(e -> {
			this.setVisible(false);
			Main.getInstance().getEnrollListFrame().setVisible(true);
			Main.getInstance().getEnrollListFrame().setData();
		});
		panel.add(enrollListButton);
		
	}
	
	public class IssueActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Date enrolltime = null;
			Date uploadtime = null;
			String matchname = IssueMacthFrame.this.matchText.getText();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				enrolltime = fmt.parse(IssueMacthFrame.this.signUpText.getText());
				uploadtime = fmt.parse(IssueMacthFrame.this.uploadTimeText.getText());
			} catch (ParseException e1)
			{
				e1.printStackTrace();
				JOptionPane.showMessageDialog(IssueMacthFrame.this, "时间格式错误");
				return;
			}
			Date issuetime = new Date();
			String info = IssueMacthFrame.this.attentionText.getText();
			MatchEntity matchEntity = new MatchEntity(enrolltime, uploadtime, issuetime, matchname, info);
			if(DaoUtils.insertMatchType(matchEntity) == 0)
				JOptionPane.showMessageDialog(IssueMacthFrame.this, "发布失败");
			else
			{
				JOptionPane.showMessageDialog(IssueMacthFrame.this, "发布成功");
				IssueMacthFrame.this.setVisible(false);
				Main.getInstance().getEnrollListFrame().setVisible(true);
				Main.getInstance().getEnrollListFrame().setData();
			}
		}
		
	}
}
