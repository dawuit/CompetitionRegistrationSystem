package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import dao.UserEntity;
import run.Main;
import utils.BNUZLogin;
import utils.DaoUtils;

/**
 * 
 * @author W.J.H
 * @date 2018��5��31��
 */
public class LoginFrame extends JFrame
{
	private JTextField userText;
	private JTextField passwordText;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton backButton;
	private JPanel panel;
	
	public LoginFrame() 
	{
		this.setSize(400, 180);
		this.setTitle("��½");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//�������
		Font font = new Font("΢���ź�", 0, 12);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		
		this.add(panel);
		
		//�û����ؼ�
		userLabel = new JLabel("ѧ��");
        userLabel.setBounds(60,20,40,25);
        userLabel.setForeground(new Color(255,255,255));
        userLabel.setFont(font); 
        panel.add(userLabel);
		
		//����ؼ�
        passwordLabel = new JLabel("����:");
        passwordLabel.setBounds(60,50,40,25);
        passwordLabel.setForeground(new Color(255,255,255));
        passwordLabel.setFont(font); 
        panel.add(passwordLabel);
		
        //�����
        passwordText = new JPasswordField(20);
        passwordText.setBounds(110,50,185,25);
        panel.add(passwordText);
        //�˺ſ�
        userText = new JTextField(20);
        userText.setBounds(110,20,185,25);
        panel.add(userText);
        
        //������¼��ť
        loginButton = new JButton("��½");
        loginButton.setBounds(110, 90, 85, 30);
        loginButton.setBackground(new Color(52,152,219));
        loginButton.setBorderPainted(false);
        loginButton.setFont(font); 
        loginButton.setFocusable(false);
        loginButton.setForeground(new Color(255,255,255));
        panel.add(loginButton);
        
        //���ذ�ť
        backButton = new JButton("����");
        backButton.setBounds(210, 90, 85, 30);
        backButton.setBackground(new Color(219, 40, 40));
        backButton.setBorderPainted(false);
        backButton.setFont(font); 
        backButton.setFocusable(false);
        backButton.setForeground(new Color(255,255,255));
        backButton.addActionListener(new BackBtnActionListener());
        panel.add(backButton);
        
        //���ð�ť������
        loginButton.addActionListener(new LoginActionListener());
	}

	/**
	 * ��½���ӽ����½��½�������½ʧ���ٴ����ݿ���ҵ�½
	 */
	public class LoginActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(userText.getText().equals("") || passwordText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(LoginFrame.this, "�������˺���Ϣ");
				return;
			}
			UserEntity userEntity = null;
			String userName = userText.getText();
			String password = passwordText.getText();
			userEntity = new BNUZLogin().doLogin(userName, password);
			//�ӽ����½��½�������½ʧ���ٴ����ݿ���ҵ�½
			if(userEntity != null)
			{
				//�����û���Ϣ
				userEntity.setUauth("0");
				DaoUtils.insertUser(userEntity);
			}
			else
			{
				userEntity = DaoUtils.selectUser(new UserEntity(userName, password, null, null));
				if (userEntity == null) 
				{
					JOptionPane.showMessageDialog(LoginFrame.this, "�˺Ŵ���");
					return;
				}
			}
			
			JOptionPane.showMessageDialog(LoginFrame.this, "��½�ɹ�");
			Main.getInstance().setUserEntity(userEntity);
			Main.getInstance().getWelcomeFrame().setVisible(false);
			Main.getInstance().getLoginFrame().setVisible(false);
			if(!userEntity.getUauth().equals("0"))
			{
				Main.getInstance().getIssueMacthFrame().setVisible(true);
				Main.getInstance().getIssueMacthFrame().setLocationRelativeTo(null);
			}
			else
			{
				//����������Ϣ
				Main.getInstance().getPersonalFrame().setData();
				Main.getInstance().getPersonalFrame().setLocationRelativeTo(null);
				Main.getInstance().getPersonalFrame().setVisible(true);
			}
		}
	}
	
	public class BackBtnActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Main.getInstance().getLoginFrame().setVisible(false);
			Main.getInstance().getWelcomeFrame().setEnabled(true);
			Main.getInstance().getWelcomeFrame().setVisible(true);
		}
		
	}
	
}
