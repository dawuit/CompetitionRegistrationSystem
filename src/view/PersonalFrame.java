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
	//�ı��ؼ�
	private JLabel labelCompetitionEvents;//������Ŀ
	private JLabel labelApplicationForm;//������
	private JLabel labelSubmitWork;//�ύ��Ʒ
	
	//ѡ��ؼ�
	//private JButton ChoiceButton;//ѡ��ť
	private JButton failSubmitProduction;//Ϊ�ύ��Ʒ
	private JButton failSubmitApplication;//Ϊ�ύ������
	private JButton attentionButton;//ע������
	private JButton quitLoginButton;//�˳���¼
	private JButton backButton;//����
	
	private JLabel enroll_timeLabel;
	private JLabel upload_timeLabel;
	
	private JPanel panel;
	
	private JComboBox<String> comboBox;//������ť
	//�������±�
	private int index = 0;

	public PersonalFrame() {
		this.setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// �������
		Font font = new Font("΢���ź�", 0, 12);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));

		this.add(panel);

		// ����ؼ�
		labelCompetitionEvents = new JLabel("������Ŀ:");
		labelCompetitionEvents.setBounds(70, 30, 300, 30);
		labelCompetitionEvents.setForeground(new Color(255, 255, 255));
		labelCompetitionEvents.setFont(font);
		panel.add(labelCompetitionEvents);

		labelApplicationForm = new JLabel("������:");
		labelApplicationForm.setBounds(80, 90, 300, 30);
		labelApplicationForm.setForeground(new Color(255, 255, 255));
		labelApplicationForm.setFont(font);
		panel.add(labelApplicationForm);

		labelSubmitWork = new JLabel("�ύ��Ʒ:");
		labelSubmitWork.setBounds(70, 155, 300, 30);
		labelSubmitWork.setForeground(new Color(255, 255, 255));
		labelSubmitWork.setFont(font);
		panel.add(labelSubmitWork);

		
		// �����˵�
		comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 25, 250, 35);
		comboBox.addItemListener(new ComBoxItemListener());
		panel.add(comboBox);

		// ����������ť
		failSubmitApplication = new JButton("δ�ύ");
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

		attentionButton = new JButton("ע������");
		attentionButton.setBounds(250, 90, 100, 35);
		attentionButton.setBackground(new Color(251, 189, 8));
		attentionButton.setBorderPainted(false);
		attentionButton.setFont(font);
		attentionButton.setFocusable(false);
		attentionButton.setForeground(new Color(255, 255, 255));
		attentionButton.addActionListener(new warnBtnActionListener());
		panel.add(attentionButton);

		// �����ύ��Ʒ��ť
		failSubmitProduction = new JButton("δ�ύ");
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

		// ������¼��ť
		quitLoginButton = new JButton("�˳���¼");
		quitLoginButton.setBounds(80, 200, 130, 35);
		quitLoginButton.setBackground(new Color(52, 152, 219));
		quitLoginButton.setBorderPainted(false);
		quitLoginButton.setFont(font);
		quitLoginButton.setFocusable(false);
		quitLoginButton.setForeground(new Color(255, 255, 255));
		quitLoginButton.addActionListener(e->{System.exit(0);});
		panel.add(quitLoginButton);

		// �������ذ�ť
		backButton = new JButton("����");
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
		
		//������ֹʱ��
		enroll_timeLabel = new JLabel("������ֹʱ��:  0000-00-00 00:00:00");
		enroll_timeLabel.setBounds(45,60,300,30);
		enroll_timeLabel.setForeground(new Color(255,255,255));
		enroll_timeLabel.setFont(font); 
		panel.add(enroll_timeLabel);
		
		//�ϴ���ֹʱ��
		upload_timeLabel = new JLabel("�ϴ���ֹʱ��:  0000-00-00 00:00:00");
		upload_timeLabel.setBounds(45, 125, 300, 30);
		upload_timeLabel.setForeground(new Color(255,255,255));
		upload_timeLabel.setFont(font); 
		panel.add(upload_timeLabel);
	}
	
	/**
	 * ��������
	 * @param index
	 */
	public void setData()
	{
		List<MatchEntity> matchEntities = Main.getInstance().getMatchEntities();
		//�±�Խ��
		if(index >= matchEntities.size())
			return;
		
		//��ѯ������¼
		EnrollEntity enrollEntity = DaoUtils.selectEnrollTable(Main.getInstance().getUserEntity(), matchEntities.get(index));
		if(enrollEntity != null)
		{
			failSubmitApplication.setBackground(new Color(33,186,69));
			failSubmitApplication.setText("���ύ");
		}
		else
		{
			failSubmitApplication.setBackground(new Color(219,40,40));
			failSubmitApplication.setText("δ�ύ");
		}
		//��ѯ�ύ��Ʒ��¼
		UploadFileEntity uploadFileEntity = DaoUtils.selectUploadWork(Main.getInstance().getUserEntity(), matchEntities.get(index));
		if(uploadFileEntity != null)
		{
			failSubmitProduction.setBackground(new Color(33,186,69));
			failSubmitProduction.setText("���ύ");
		}
		else
		{
			failSubmitProduction.setBackground(new Color(219,40,40));
			failSubmitProduction.setText("δ�ύ");
		}
		
		this.setTitle(Main.getInstance().getUserEntity().getName() + "��"+ Main.getInstance().getUserEntity().getUid() + "��");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		enroll_timeLabel.setText("������ֹʱ��:   " + fmt.format(matchEntities.get(index).getEnrolltime()));
		upload_timeLabel.setText("�ϴ���ֹʱ��:   " + fmt.format(matchEntities.get(index).getUploadtime()));
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
		//��������
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
