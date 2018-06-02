package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.fabric.xmlrpc.base.Data;

import dao.EnrollEntity;
import dao.UploadFileEntity;
import run.Main;
import utils.DaoUtils;




public class UploadFileFrame extends JFrame
{
	private JPanel panel;
	private JLabel titleLabel;//�����
	
	private JLabel panName;//�������Ʊ���
	private JTextField panNameText;//���������ı���
	private JLabel passwordName;//�������Ʊ���
	private JTextField passwordText;//���������ı���
	private JButton backAgainButton;//����
	private JButton submitButton;//�ύ
	

	public UploadFileFrame() 
	{
		this.setSize(500, 330);
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
		this.setTitle("�ϴ���Ʒ");
		
		//�����ؼ�����һ�У�
		titleLabel = new JLabel("��ʮ���IT��֮�������Ʒѡ����");
		titleLabel.setBounds(0,25,500,40);
		titleLabel.setForeground(new Color(255,255,255));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(fontTitle); 
		panel.add(titleLabel);

		//�������Ʊ��⣨�ڶ��У�
		panName = new JLabel("��������");
		panName.setBounds(80, 60, 200, 30);
		panName.setForeground(new Color(255, 255, 255));
		panName.setFont(font);
		panel.add(panName);
		

       //��Ʒ�����ı��򣨵ڶ��У�
		panNameText = new JTextField();
		panNameText.setBounds(80, 90, 330, 30);
		panNameText.setForeground(new Color(255, 255, 255));
		panNameText.setFont(font);
		panNameText.setForeground(new Color(50, 50, 50));
		panel.add(panNameText);
		
		// �������Ʊ��⣨�����У�
		passwordName = new JLabel("����");
		passwordName.setBounds(80, 130, 200, 30);
		passwordName.setForeground(new Color(255, 255, 255));
		passwordName.setFont(font);
		panel.add(passwordName);

		// ��Ʒ�����ı��򣨵����У�
		passwordText = new JTextField();
		passwordText.setBounds(80, 160, 330, 30);
		passwordText.setForeground(new Color(255, 255, 255));
		passwordText.setFont(font);
		passwordText.setForeground(new Color(50, 50, 50));
		panel.add(passwordText);
	
		// �����ύ��ť
		submitButton = new JButton("�ύ");
		submitButton.setBounds(80, 220, 140, 40);
		submitButton.setBackground(new Color(33, 186, 69));
		submitButton.setBorderPainted(false);
		submitButton.setFont(font);
		submitButton.setFocusable(false);
		submitButton.setForeground(new Color(255, 255, 255));
		submitButton.addActionListener(new SubmitBtnActionListener());
		panel.add(submitButton);
		
		// �������ذ�ť
		backAgainButton = new JButton("����");
		backAgainButton.setBounds(270, 220, 140, 40);
		backAgainButton.setBackground(new Color(52, 152, 219));
		backAgainButton.setBorderPainted(false);
		backAgainButton.setFont(font);
		backAgainButton.setFocusable(false);
		backAgainButton.setForeground(new Color(255, 255, 255));
		backAgainButton.addActionListener(e -> {
			UploadFileFrame.this.setVisible(false);
			Main.getInstance().getPersonalFrame().setVisible(true);
			Main.getInstance().getPersonalFrame().setEnabled(true);
		});
		panel.add(backAgainButton);
		
	}
	
	/**
	 * ������Ϣ
	 */
	public void setData()
	{
		titleLabel.setText(Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()).getMatchname());
		UploadFileEntity uploadFileEntity = DaoUtils.selectUploadWork(Main.getInstance().getUserEntity(), Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()));
		//������ڼ�¼
		if(uploadFileEntity != null)
		{
			submitButton.setVisible(false);
			panNameText.setText(uploadFileEntity.getDropboxurl());
			passwordText.setText(uploadFileEntity.getBoxpassword());
			panNameText.setEditable(false);
			passwordText.setEditable(false);
		}
		else
		{
			submitButton.setVisible(true);
			panNameText.setText("");
			passwordText.setText("");
			panNameText.setEditable(true);
			passwordText.setEditable(true);
		}
	}
	
	public class SubmitBtnActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Main process = Main.getInstance() ;
			UploadFileEntity res = DaoUtils.selectUploadWork(process.getUserEntity(), process.getMatchEntities().get(process.getPersonalFrame().getIndex()));
			if(process.getMatchEntities().get(process.getPersonalFrame().getIndex()).getUploadtime().compareTo(new Date()) < 0)
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "ʱ���Ѿ���ֹ");
				return;
			}
			if(res != null)
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "�Ѿ�����");
				return;
			}
			String url = UploadFileFrame.this.panNameText.getText();
			String password = UploadFileFrame.this.passwordText.getText();
			if(url.equals("") || password.equals(""))
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "����д��Ϣ");
				return;
			}
			UploadFileEntity uploadFileEntity = new UploadFileEntity(process.getUserEntity().getUid(), process.getMatchEntities().get(process.getPersonalFrame().getIndex()).getMatchname(), new Date(), url, password);
			int resCode = DaoUtils.insertUploadWork(uploadFileEntity);
			if(resCode != 0)
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "�ύ�ɹ�");
			}
			else
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "�ύʧ��");
			}
			
			UploadFileFrame.this.setVisible(false);
			Main.getInstance().getPersonalFrame().setData();
			Main.getInstance().getPersonalFrame().setVisible(true);
			Main.getInstance().getPersonalFrame().setEnabled(true);
		}
		
	}
}
