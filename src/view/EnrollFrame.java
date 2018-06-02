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
	
	private JLabel titleLabel;//�����
	private JLabel productionName;//��Ʒ���Ʊ���
	private JTextField productionNameText; //��Ʒ�����ı���
	
	
	private JLabel teamName;//�Ŷ����Ʊ���
	private JTextField teamNameText; //�Ŷ������ı���
	
	private JLabel teacherName;//ָ����ʦ����
	private JTextField teacherNameText; //ָ����ʦ�ı���
	
	private JLabel groupLeaderName;//�鳤����
	private JTextField groupLeaderText; //�鳤�ı���
	
	private JLabel numberName;//ѧ�ű���
	private JTextField numberText; //ѧ���ı���
	
	private JLabel professionalName;//רҵ�꼶����
	private JTextField professionalText; //רҵ�꼶�ı���
	
	private JLabel telephoneName;//�绰����
	private JTextField telephoneText; //�绰�ı���
	
	private JButton submitProductionButton;//�ύ��ť
	private JButton goBackButton;//���ذ�ť
	
	

	public EnrollFrame() 
	{
		this.setSize(800, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//�������
		Font font = new Font("΢���ź�", 0, 15);
	
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		
		this.add(panel);
		
		
		this.setTitle("����");
		
		
		//�����ؼ�����һ�У�
		titleLabel = new JLabel();
		titleLabel.setBounds(0,25,800,30);
		titleLabel.setForeground(new Color(255,255,255));
		titleLabel.setHorizontalAlignment(JTextField.CENTER);
		titleLabel.setFont(font); 
		panel.add(titleLabel);

		//��Ʒ���Ʊ��⣨�ڶ��У�
		productionName = new JLabel("��Ʒ���ƣ�50�����ڣ�");
		productionName.setBounds(50, 60, 200, 30);
		productionName.setForeground(new Color(255,255,255));
		productionName.setFont(font);
		panel.add(productionName);
		

        //��Ʒ�����ı��򣨵ڶ��У�
		productionNameText = new JTextField();
		productionNameText.setBounds(50, 100, 200, 30);
		productionNameText.setForeground(new Color(0,0,0));
		productionNameText.setFont(font);
		panel.add(productionNameText);
		
		// �Ŷ����Ʊ��⣨�ڶ��У�
		teamName = new JLabel("�Ŷ����ƣ�50�����ڣ�");
		teamName.setBounds(280, 60, 200, 30);
		teamName.setForeground(new Color(255,255,255));
		teamName.setFont(font);
		panel.add(teamName);

		// �Ŷ������ı��򣨵ڶ��У�
		teamNameText = new JTextField();
		teamNameText.setBounds(280, 100, 200, 30);
		teamNameText.setForeground(new Color(0,0,0));
		teamNameText.setFont(font);
		panel.add(teamNameText);
		
		
		// ָ����ʦ���Ʊ��⣨�ڶ��У�
		teacherName = new JLabel("ָ����ʦ");
		teacherName.setBounds(510, 60, 200, 30);
		teacherName.setForeground(new Color(255,255,255));
		teacherName.setFont(font);
		panel.add(teacherName);

		// ָ����ʦ�����ı��򣨵ڶ��У�
		teacherNameText = new JTextField();
		teacherNameText.setBounds(510, 100, 200, 30);
		teacherNameText.setForeground(new Color(0,0,0));
		teacherNameText.setFont(font);
		panel.add(teacherNameText);

		
		
		// �鳤���Ʊ��⣨�����У�
		groupLeaderName = new JLabel("�鳤����");
		groupLeaderName.setBounds(50, 140, 100, 30);
		groupLeaderName.setForeground(new Color(255,255,255));
		groupLeaderName.setFont(font);
		panel.add(groupLeaderName);

		// �鳤�����ı��򣨵����У�
		groupLeaderText = new JTextField();
		groupLeaderText.setBounds(50, 180, 150, 30);
		groupLeaderText.setForeground(new Color(0,0,0));
		groupLeaderText.setFont(font);
		groupLeaderText.setEditable(false);
		panel.add(groupLeaderText);

		// ѧ�����Ʊ��⣨�����У�
		numberName = new JLabel("ѧ��");
		numberName.setBounds(220, 140, 100, 30);
		numberName.setForeground(new Color(255,255,255));
		numberName.setFont(font);
		panel.add(numberName);

		// ѧ�������ı��򣨵����У�
		numberText = new JTextField();
		numberText.setBounds(220, 180, 150, 30);
		numberText.setForeground(new Color(0,0,0));
		numberText.setFont(font);
		numberText.setEditable(false);
		panel.add(numberText);

		// רҵ�꼶���Ʊ��⣨�����У�
		professionalName = new JLabel("רҵ�꼶");
		professionalName.setBounds(390, 140, 100, 30);
		professionalName.setForeground(new Color(255,255,255));
		professionalName.setFont(font);
		panel.add(professionalName);

		// רҵ�꼶�����ı��򣨵����У�
		professionalText = new JTextField();
		professionalText.setBounds(390, 180, 150, 30);
		professionalText.setForeground(new Color(0,0,0));
		professionalText.setFont(font);
		panel.add(professionalText);
		
		
		// �绰���Ʊ��⣨�����У�
		telephoneName = new JLabel("�绰");
		telephoneName.setBounds(560, 140, 100, 30);
		telephoneName.setForeground(new Color(255,255,255));
		telephoneName.setFont(font);
		panel.add(telephoneName);

		// �绰�����ı��򣨵����У�
		telephoneText = new JTextField();
		telephoneText.setBounds(560, 180, 150, 30);
		telephoneText.setForeground(new Color(0,0,0));
		telephoneText.setFont(font);
		panel.add(telephoneText);
		
		
		// �ύ
		submitProductionButton = new JButton("�ύ");
		submitProductionButton.setBounds(470, 250, 110, 40);
		submitProductionButton.setBackground(new Color(90, 190, 30));
		submitProductionButton.setBorderPainted(false);
		submitProductionButton.setFont(font);
		submitProductionButton.setFocusable(false);
		submitProductionButton.setForeground(new Color(255, 255, 255));
		submitProductionButton.addActionListener(new SubmitBtnActionListener());
		
		panel.add(submitProductionButton);

		// ����
		goBackButton = new JButton("����");
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
	 * ��������
	 */
	public void setData()
	{
		groupLeaderText.setText(Main.getInstance().getUserEntity().getName());
		numberText.setText(Main.getInstance().getUserEntity().getUid());
		titleLabel.setText(Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()).getMatchname());
		EnrollEntity enrollEntity = DaoUtils.selectEnrollTable(Main.getInstance().getUserEntity(), Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()));
		//������ڼ�¼
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
				JOptionPane.showMessageDialog(EnrollFrame.this, "����д��Ϣ");
				return;
			}
			if(null != DaoUtils.selectEnrollTable(Main.getInstance().getUserEntity(), Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex())))
			{
				JOptionPane.showMessageDialog(EnrollFrame.this, "�Ѿ�����");
				return;
			}
			if(Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()).getEnrolltime().compareTo(new Date()) > 0 && 0 != DaoUtils.insertEnrollTable(new EnrollEntity(uid, matchname, new Date(), projectName, teamname, teachername, name, phone, grade, null, null, phone, null, null)))
			{
				JOptionPane.showMessageDialog(EnrollFrame.this, "�����ɹ�");
			}
			else
			{
				JOptionPane.showMessageDialog(EnrollFrame.this, "����ʧ��");
			}
			EnrollFrame.this.setVisible(false);
			Main.getInstance().getPersonalFrame().setData();
			Main.getInstance().getPersonalFrame().setVisible(true);
			Main.getInstance().getPersonalFrame().setEnabled(true);
			
		}
	}
	
}
