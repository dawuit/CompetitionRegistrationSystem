package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import dao.EnrollEntity;
import dao.MatchEntity;
import dao.UploadFileEntity;
import run.Main;
import utils.DaoUtils;


public class PersonalFrame extends JFrame {
	//private JLabel MessageLabel;
	//文本控件
	private JLabel labelCompetitionEvents;//比赛项目
	private JLabel labelApplicationForm;//报名表
	private JLabel labelSubmitWork;//提交作品
	
	//选择控件
	//private JButton ChoiceButton;//选择按钮
	private JButton failSubmitProduction;//为提交作品
	private JButton failSubmitApplication;//为提交报名表
	private JButton attentionButton;//注意事项
	private JButton quitLoginButton;//退出登录
	private JButton backButton;//返回
	
	private JLabel enroll_timeLabel;
	private JLabel upload_timeLabel;
	
	private JPanel panel;
	
	private JComboBox<String> comboBox;//下拉按钮
	//下拉框下标
	private int index = 0;

	public PersonalFrame() {
		this.setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// 字体对象
		Font font = new Font("微软雅黑", 0, 12);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));

		this.add(panel);

		// 标题控件
		labelCompetitionEvents = new JLabel("比赛项目:");
		labelCompetitionEvents.setBounds(70, 30, 300, 30);
		labelCompetitionEvents.setForeground(new Color(255, 255, 255));
		labelCompetitionEvents.setFont(font);
		panel.add(labelCompetitionEvents);

		labelApplicationForm = new JLabel("报名表:");
		labelApplicationForm.setBounds(80, 90, 300, 30);
		labelApplicationForm.setForeground(new Color(255, 255, 255));
		labelApplicationForm.setFont(font);
		panel.add(labelApplicationForm);

		labelSubmitWork = new JLabel("提交作品:");
		labelSubmitWork.setBounds(70, 155, 300, 30);
		labelSubmitWork.setForeground(new Color(255, 255, 255));
		labelSubmitWork.setFont(font);
		panel.add(labelSubmitWork);

		
		// 下拉菜单
		comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 25, 250, 35);
		comboBox.addItemListener(new ComBoxItemListener());
		panel.add(comboBox);

		// 创建报名表按钮
		failSubmitApplication = new JButton("未提交");
		failSubmitApplication.setBounds(130, 90, 100, 35);
		failSubmitApplication.setBackground(new Color(219, 40, 40));
		failSubmitApplication.setBorderPainted(false);
		failSubmitApplication.setFont(font);
		failSubmitApplication.setFocusable(false);
		failSubmitApplication.setForeground(new Color(255, 255, 255));
		failSubmitApplication.addActionListener(e->{
			Main.getInstance().getEnrollFrame().setLocationRelativeTo(null);
			Main.getInstance().getEnrollFrame().setVisible(true);
			this.setEnabled(false);
			Main.getInstance().getEnrollFrame().setData();});
		panel.add(failSubmitApplication);

		attentionButton = new JButton("注意事项");
		attentionButton.setBounds(250, 90, 100, 35);
		attentionButton.setBackground(new Color(251, 189, 8));
		attentionButton.setBorderPainted(false);
		attentionButton.setFont(font);
		attentionButton.setFocusable(false);
		attentionButton.setForeground(new Color(255, 255, 255));
		attentionButton.addActionListener(new warnBtnActionListener());
		panel.add(attentionButton);

		// 创建提交作品按钮
		failSubmitProduction = new JButton("未提交");
		failSubmitProduction.setBounds(130, 155, 100, 35);
		failSubmitProduction.setBackground(new Color(219, 40, 40));
		failSubmitProduction.setBorderPainted(false);
		failSubmitProduction.setFont(font);
		failSubmitProduction.setFocusable(false);
		failSubmitProduction.setForeground(new Color(255, 255, 255));
		failSubmitProduction.addActionListener(e -> {
			Main.getInstance().getUploadFileFrame().setLocationRelativeTo(null);
			Main.getInstance().getUploadFileFrame().setVisible(true);
			this.setEnabled(false);
			Main.getInstance().getUploadFileFrame().setData();
		});
		panel.add(failSubmitProduction);

		// 创建登录按钮
		quitLoginButton = new JButton("退出登录");
		quitLoginButton.setBounds(80, 200, 130, 35);
		quitLoginButton.setBackground(new Color(52, 152, 219));
		quitLoginButton.setBorderPainted(false);
		quitLoginButton.setFont(font);
		quitLoginButton.setFocusable(false);
		quitLoginButton.setForeground(new Color(255, 255, 255));
		quitLoginButton.addActionListener(e->{System.exit(0);});
		panel.add(quitLoginButton);

		// 创建返回按钮
		backButton = new JButton("返回");
		backButton.setBounds(230, 200, 130, 35);
		backButton.setBackground(new Color(219, 40, 40));
		backButton.setBorderPainted(false);
		backButton.setFont(font);
		backButton.setFocusable(false);
		backButton.setForeground(new Color(255, 255, 255));
		backButton.addActionListener(e->{
			PersonalFrame.this.setVisible(false); 
			Main.getInstance().getWelcomeFrame().setEnabled(true);
			Main.getInstance().getWelcomeFrame().setVisible(true);
			});
		panel.add(backButton);
		
		//报名截止时间
		enroll_timeLabel = new JLabel("报名截止时间:  0000-00-00 00:00:00");
		enroll_timeLabel.setBounds(45,60,300,30);
		enroll_timeLabel.setForeground(new Color(255,255,255));
		enroll_timeLabel.setFont(font); 
		panel.add(enroll_timeLabel);
		
		//上传截止时间
		upload_timeLabel = new JLabel("上传截止时间:  0000-00-00 00:00:00");
		upload_timeLabel.setBounds(45, 125, 300, 30);
		upload_timeLabel.setForeground(new Color(255,255,255));
		upload_timeLabel.setFont(font); 
		panel.add(upload_timeLabel);
	}
	
	/**
	 * 设置数据
	 * @param index
	 */
	public void setData()
	{
		List<MatchEntity> matchEntities = Main.getInstance().getMatchEntities();
		//下标越界
		if(index >= matchEntities.size())
			return;
		
		//查询报名记录
		EnrollEntity enrollEntity = DaoUtils.selectEnrollTable(Main.getInstance().getUserEntity(), matchEntities.get(index));
		if(enrollEntity != null)
		{
			failSubmitApplication.setBackground(new Color(33,186,69));
			failSubmitApplication.setText("已提交");
		}
		else
		{
			failSubmitApplication.setBackground(new Color(219,40,40));
			failSubmitApplication.setText("未提交");
		}
		//查询提交作品记录
		UploadFileEntity uploadFileEntity = DaoUtils.selectUploadWork(Main.getInstance().getUserEntity(), matchEntities.get(index));
		if(uploadFileEntity != null)
		{
			failSubmitProduction.setBackground(new Color(33,186,69));
			failSubmitProduction.setText("已提交");
		}
		else
		{
			failSubmitProduction.setBackground(new Color(219,40,40));
			failSubmitProduction.setText("未提交");
		}
		
		this.setTitle(Main.getInstance().getUserEntity().getName() + "【"+ Main.getInstance().getUserEntity().getUid() + "】");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		enroll_timeLabel.setText("报名截止时间:   " + fmt.format(matchEntities.get(index).getEnrolltime()));
		upload_timeLabel.setText("上传截止时间:   " + fmt.format(matchEntities.get(index).getUploadtime()));
	}
	
	public class ComBoxItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				index = comboBox.getSelectedIndex();
				setData();
			}
		}
		
	}
	
	@Override
	public void setVisible(boolean b)
	{
		List<MatchEntity> matchEntities = DaoUtils.selectMatchType();
		Main.getInstance().setMatchEntities(matchEntities);	
		//更新下拉
		panel.remove(comboBox);
		comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 25, 250, 35);
		panel.add(comboBox);
		for(MatchEntity i : matchEntities)
		{
			comboBox.addItem(i.getMatchname());
		}
		comboBox.addItemListener(new ComBoxItemListener());
		super.setVisible(b);
	}
	
	public class warnBtnActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(PersonalFrame.this, Main.getInstance().getMatchEntities().get(index).getInfo());
			
		}
		
	}

	public JLabel getLabelCompetitionEvents() {
		return labelCompetitionEvents;
	}

	public void setLabelCompetitionEvents(JLabel labelCompetitionEvents) {
		this.labelCompetitionEvents = labelCompetitionEvents;
	}

	public JLabel getLabelApplicationForm() {
		return labelApplicationForm;
	}

	public void setLabelApplicationForm(JLabel labelApplicationForm) {
		this.labelApplicationForm = labelApplicationForm;
	}

	public JLabel getLabelSubmitWork() {
		return labelSubmitWork;
	}

	public void setLabelSubmitWork(JLabel labelSubmitWork) {
		this.labelSubmitWork = labelSubmitWork;
	}

	public JButton getFailSubmitProduction() {
		return failSubmitProduction;
	}

	public void setFailSubmitProduction(JButton failSubmitProduction) {
		this.failSubmitProduction = failSubmitProduction;
	}

	public JButton getFailSubmitApplication() {
		return failSubmitApplication;
	}

	public void setFailSubmitApplication(JButton failSubmitApplication) {
		this.failSubmitApplication = failSubmitApplication;
	}

	public JButton getAttentionButton() {
		return attentionButton;
	}

	public void setAttentionButton(JButton attentionButton) {
		this.attentionButton = attentionButton;
	}

	public JButton getQuitLoginButton() {
		return quitLoginButton;
	}

	public void setQuitLoginButton(JButton quitLoginButton) {
		this.quitLoginButton = quitLoginButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
