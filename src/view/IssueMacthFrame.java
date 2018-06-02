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
	
	
	private JLabel matchName;//�������Ʊ���
	private JTextField matchText;//���������ı���
	
	private JLabel signUpName;//������ֹʱ�����
	private JTextField signUpText;//������ֹʱ�䱾��
	
	private JLabel uploadTimeName;//�ļ��ϴ���ֹʱ���ı���
	private JTextField uploadTimeText;//�ļ��ϴ���ֹʱ�����ı���
	
	private JLabel attentionName ;//ע���������
	private JTextArea attentionText ;//ע�������ı���
	private JScrollPane attentionTool ;//ע���������
	
	private JButton releaseButton;//������ť
	private JButton backAgainButton;//���ذ�ť
	private JButton enrollListButton;
	
	
	public IssueMacthFrame() 
	{
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//�������
		Font font = new Font("΢���ź�", 0, 15);
		Font fontTitle = new Font("΢���ź�", 0, 20);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		
		this.add(panel);
		
		//�û���Ϣ
		this.setTitle("����");
		

		//�������Ʊ��⣨��һ�У�
		matchName = new JLabel("��������");
		matchName.setBounds(60, 20, 200, 30);
		matchName.setForeground(new Color(255, 255, 255));
		matchName.setFont(font);
		panel.add(matchName);
		
		//���������ı��򣨵�һ�У�
		matchText = new JTextField("");
		matchText.setBounds(60, 50, 450, 30);
		matchText.setForeground(new Color(255, 255, 255));
		matchText.setFont(font);
		matchText.setForeground(new Color(50, 50, 50));
		panel.add(matchText);
		

		//������ֹʱ����⣨�ڶ��У�
		signUpName = new JLabel("������ֹʱ��");
		signUpName.setBounds(60, 90, 200, 30);
		signUpName.setForeground(new Color(255, 255, 255));
		signUpName.setFont(font);
		panel.add(signUpName);
		
		//������ֹʱ���ı��򣨵ڶ��У�
		signUpText = new JTextField("��ʽ:yyyy-MM-dd hh:mm:ss");
		signUpText.setBounds(60, 120, 210, 30);
		signUpText.setForeground(new Color(255, 255, 255));
		signUpText.setFont(font);
		signUpText.setForeground(new Color(50, 50, 50));
		panel.add(signUpText);
		
		//�ļ��ϴ���ֹʱ����⣨�ڶ��У�
		uploadTimeName = new JLabel("�ļ��ϴ���ֹʱ��");
		uploadTimeName.setBounds(300, 90, 200, 30);
		uploadTimeName.setForeground(new Color(255, 255, 255));
		uploadTimeName.setFont(font);
		panel.add(uploadTimeName);
		
		//�ļ��ϴ���ֹʱ���ı���
		uploadTimeText = new JTextField("��ʽ:yyyy-MM-dd hh:mm:ss");
		uploadTimeText.setBounds(300, 120, 210, 30);
		uploadTimeText.setForeground(new Color(255, 255, 255));
		uploadTimeText.setFont(font);
		uploadTimeText.setForeground(new Color(50, 50, 50));
		panel.add(uploadTimeText);
		
		//ע��������⣨�����У�
		attentionName = new JLabel("ע������");
		attentionName.setBounds(60, 160, 200, 30);
		attentionName.setForeground(new Color(255, 255, 255));
		attentionName.setFont(font);
		panel.add(attentionName);
		
		//ע�������ı��򣨵����У�
		attentionText = new JTextArea();
		attentionTool= new JScrollPane(attentionText);
		attentionTool.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		attentionTool.setBounds(60, 190,450, 200);
		attentionTool.setForeground(new Color(255, 255, 255));
		attentionTool.setFont(font);
		panel.add(attentionTool);
		
		
		//����������ť
		releaseButton = new JButton("����");
		releaseButton.setBounds(60, 410,140, 40);
		releaseButton.setBackground(new Color(33,186,69));
		releaseButton.setBorderPainted(false);
		releaseButton.setFont(font);
		releaseButton.setFocusable(false);
		releaseButton.setForeground(new Color(255, 255, 255));
		releaseButton.addActionListener(new IssueActionListener());
		panel.add(releaseButton);
		
		// �������ذ�ť
		backAgainButton = new JButton("����");
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
		
		// �����б�ť
		enrollListButton = new JButton("������Ϣ");
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
				JOptionPane.showMessageDialog(IssueMacthFrame.this, "ʱ���ʽ����");
				return;
			}
			Date issuetime = new Date();
			String info = IssueMacthFrame.this.attentionText.getText();
			MatchEntity matchEntity = new MatchEntity(enrolltime, uploadtime, issuetime, matchname, info);
			if(DaoUtils.insertMatchType(matchEntity) == 0)
				JOptionPane.showMessageDialog(IssueMacthFrame.this, "����ʧ��");
			else
			{
				JOptionPane.showMessageDialog(IssueMacthFrame.this, "�����ɹ�");
				IssueMacthFrame.this.setVisible(false);
				Main.getInstance().getEnrollListFrame().setVisible(true);
				Main.getInstance().getEnrollListFrame().setData();
			}
		}
		
	}
}
