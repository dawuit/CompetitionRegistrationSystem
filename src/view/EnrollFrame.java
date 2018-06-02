package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dao.EnrollEntity;

import javax.swing.JTextField;

import run.Main;
import utils.DaoUtils;

public class EnrollFrame extends JFrame
{
	private JPanel panel;
	
	private JLabel titleLabel;//大标题
	private JLabel productionName;//作品名称标题
	private JTextField productionNameText; //作品名称文本框
	
	
	private JLabel teamName;//团队名称标题
	private JTextField teamNameText; //团队名称文本框
	
	private JLabel teacherName;//指导老师标题
	private JTextField teacherNameText; //指导老师文本框
	
	private JLabel groupLeaderName;//组长标题
	private JTextField groupLeaderText; //组长文本框
	
	private JLabel numberName;//学号标题
	private JTextField numberText; //学号文本框
	
	private JLabel professionalName;//专业年级标题
	private JTextField professionalText; //专业年级文本框
	
	private JLabel telephoneName;//电话标题
	private JTextField telephoneText; //电话文本框
	
	private JButton submitProductionButton;//提交按钮
	private JButton goBackButton;//返回按钮
	
	

	public EnrollFrame() 
	{
		this.setSize(800, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//字体对象
		Font font = new Font("微软雅黑", 0, 15);
	
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		
		this.add(panel);
		
		
		this.setTitle("报名");
		
		
		//大标题控件（第一行）
		titleLabel = new JLabel();
		titleLabel.setBounds(0,25,800,30);
		titleLabel.setForeground(new Color(255,255,255));
		titleLabel.setHorizontalAlignment(JTextField.CENTER);
		titleLabel.setFont(font); 
		panel.add(titleLabel);

		//作品名称标题（第二行）
		productionName = new JLabel("作品名称（50字以内）");
		productionName.setBounds(50, 60, 200, 30);
		productionName.setForeground(new Color(255,255,255));
		productionName.setFont(font);
		panel.add(productionName);
		

        //作品名称文本框（第二行）
		productionNameText = new JTextField();
		productionNameText.setBounds(50, 100, 200, 30);
		productionNameText.setForeground(new Color(0,0,0));
		productionNameText.setFont(font);
		panel.add(productionNameText);
		
		// 团队名称标题（第二行）
		teamName = new JLabel("团队名称（50字以内）");
		teamName.setBounds(280, 60, 200, 30);
		teamName.setForeground(new Color(255,255,255));
		teamName.setFont(font);
		panel.add(teamName);

		// 团队名称文本框（第二行）
		teamNameText = new JTextField();
		teamNameText.setBounds(280, 100, 200, 30);
		teamNameText.setForeground(new Color(0,0,0));
		teamNameText.setFont(font);
		panel.add(teamNameText);
		
		
		// 指导老师名称标题（第二行）
		teacherName = new JLabel("指导老师");
		teacherName.setBounds(510, 60, 200, 30);
		teacherName.setForeground(new Color(255,255,255));
		teacherName.setFont(font);
		panel.add(teacherName);

		// 指导老师名称文本框（第二行）
		teacherNameText = new JTextField();
		teacherNameText.setBounds(510, 100, 200, 30);
		teacherNameText.setForeground(new Color(0,0,0));
		teacherNameText.setFont(font);
		panel.add(teacherNameText);

		
		
		// 组长名称标题（第三行）
		groupLeaderName = new JLabel("组长姓名");
		groupLeaderName.setBounds(50, 140, 100, 30);
		groupLeaderName.setForeground(new Color(255,255,255));
		groupLeaderName.setFont(font);
		panel.add(groupLeaderName);

		// 组长名称文本框（第三行）
		groupLeaderText = new JTextField();
		groupLeaderText.setBounds(50, 180, 150, 30);
		groupLeaderText.setForeground(new Color(0,0,0));
		groupLeaderText.setFont(font);
		groupLeaderText.setEditable(false);
		panel.add(groupLeaderText);

		// 学号名称标题（第三行）
		numberName = new JLabel("学号");
		numberName.setBounds(220, 140, 100, 30);
		numberName.setForeground(new Color(255,255,255));
		numberName.setFont(font);
		panel.add(numberName);

		// 学号名称文本框（第三行）
		numberText = new JTextField();
		numberText.setBounds(220, 180, 150, 30);
		numberText.setForeground(new Color(0,0,0));
		numberText.setFont(font);
		numberText.setEditable(false);
		panel.add(numberText);

		// 专业年级名称标题（第三行）
		professionalName = new JLabel("专业年级");
		professionalName.setBounds(390, 140, 100, 30);
		professionalName.setForeground(new Color(255,255,255));
		professionalName.setFont(font);
		panel.add(professionalName);

		// 专业年级名称文本框（第三行）
		professionalText = new JTextField();
		professionalText.setBounds(390, 180, 150, 30);
		professionalText.setForeground(new Color(0,0,0));
		professionalText.setFont(font);
		panel.add(professionalText);
		
		
		// 电话名称标题（第三行）
		telephoneName = new JLabel("电话");
		telephoneName.setBounds(560, 140, 100, 30);
		telephoneName.setForeground(new Color(255,255,255));
		telephoneName.setFont(font);
		panel.add(telephoneName);

		// 电话名称文本框（第三行）
		telephoneText = new JTextField();
		telephoneText.setBounds(560, 180, 150, 30);
		telephoneText.setForeground(new Color(0,0,0));
		telephoneText.setFont(font);
		panel.add(telephoneText);
		
		
		// 提交
		submitProductionButton = new JButton("提交");
		submitProductionButton.setBounds(470, 250, 110, 40);
		submitProductionButton.setBackground(new Color(90, 190, 30));
		submitProductionButton.setBorderPainted(false);
		submitProductionButton.setFont(font);
		submitProductionButton.setFocusable(false);
		submitProductionButton.setForeground(new Color(255, 255, 255));
		submitProductionButton.addActionListener(new SubmitBtnActionListener());
		
		panel.add(submitProductionButton);

		// 返回
		goBackButton = new JButton("返回");
		goBackButton.setBounds(600, 250, 110, 40);
		goBackButton.setBackground(new Color(213, 73, 70));
		goBackButton.setBorderPainted(false);
		goBackButton.setFont(font);
		goBackButton.setFocusable(false);
		goBackButton.setForeground(new Color(255, 255, 255));
		goBackButton.addActionListener(e->{
			EnrollFrame.this.setVisible(false);
			Main.getInstance().getPersonalFrame().setVisible(true);
			Main.getInstance().getPersonalFrame().setEnabled(true);
		});
		panel.add(goBackButton);
	}
	
	/**
	 * 设置数据
	 */
	public void setData()
	{
		groupLeaderText.setText(Main.getInstance().getUserEntity().getName());
		numberText.setText(Main.getInstance().getUserEntity().getUid());
		titleLabel.setText(Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()).getMatchname());
		EnrollEntity enrollEntity = DaoUtils.selectEnrollTable(Main.getInstance().getUserEntity(), Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()));
		//如果存在记录
		if(enrollEntity != null)
		{
			submitProductionButton.setVisible(false);
			teacherNameText.setText(enrollEntity.getTeachername());
			teacherNameText.setEditable(false);
			teamNameText.setText(enrollEntity.getTeamname());
			teamNameText.setEditable(false);
			telephoneText.setText(enrollEntity.getPhone());
			telephoneText.setEditable(false);
			professionalText.setText(enrollEntity.getProjectname());
			professionalText.setEditable(false);
			productionNameText.setText(enrollEntity.getProjectname());
			productionNameText.setEditable(false);
		}
		else
		{
			submitProductionButton.setVisible(true);
			submitProductionButton.setVisible(true);
			teacherNameText.setEditable(true);
			teamNameText.setEditable(true);
			telephoneText.setEditable(true);
			professionalText.setEditable(true);
			productionNameText.setEditable(true);
			submitProductionButton.setVisible(true);
			teacherNameText.setText("");
			teacherNameText.setEditable(true);
			teamNameText.setText("");
			teamNameText.setEditable(true);
			telephoneText.setText("");
			telephoneText.setEditable(true);
			professionalText.setText("");
			professionalText.setEditable(true);
			productionNameText.setText("");
			productionNameText.setEditable(true);
		}
			
	}
	
	public class SubmitBtnActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String uid = EnrollFrame.this.numberText.getText();
			String name = EnrollFrame.this.groupLeaderText.getText();
			String matchname = EnrollFrame.this.titleLabel.getText();
			String projectName = EnrollFrame.this.productionNameText.getText();
			String teamname = EnrollFrame.this.teamNameText.getText();
			String teachername = EnrollFrame.this.teacherNameText.getText();
			String grade = EnrollFrame.this.productionNameText.getText();
			String phone = EnrollFrame.this.telephoneText.getText();
			if(uid.equals("") || name.equals("") || matchname.equals("") || projectName.equals("") || teamname.equals("")
					|| teachername.equals("") || grade.equals("") || phone.equals(""))
			{
				JOptionPane.showMessageDialog(EnrollFrame.this, "请填写信息");
				return;
			}
			if(null != DaoUtils.selectEnrollTable(Main.getInstance().getUserEntity(), Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex())))
			{
				JOptionPane.showMessageDialog(EnrollFrame.this, "已经报名");
				return;
			}
			if(Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()).getEnrolltime().compareTo(new Date()) > 0 && 0 != DaoUtils.insertEnrollTable(new EnrollEntity(uid, matchname, new Date(), projectName, teamname, teachername, name, phone, grade, null, null, phone, null, null)))
			{
				JOptionPane.showMessageDialog(EnrollFrame.this, "报名成功");
			}
			else
			{
				JOptionPane.showMessageDialog(EnrollFrame.this, "报名失败");
			}
			EnrollFrame.this.setVisible(false);
			Main.getInstance().getPersonalFrame().setData();
			Main.getInstance().getPersonalFrame().setVisible(true);
			Main.getInstance().getPersonalFrame().setEnabled(true);
			
		}
	}
	
}
