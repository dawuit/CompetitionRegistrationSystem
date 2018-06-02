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
	private JLabel titleLabel;//大标题
	
	private JLabel panName;//网盘名称标题
	private JTextField panNameText;//网盘名称文本框
	private JLabel passwordName;//网盘名称标题
	private JTextField passwordText;//网盘名称文本框
	private JButton backAgainButton;//返回
	private JButton submitButton;//提交
	

	public UploadFileFrame() 
	{
		this.setSize(500, 330);
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
		this.setTitle("上传作品");
		
		//大标题控件（第一行）
		titleLabel = new JLabel("第十五届IT节之计算机作品选拔赛");
		titleLabel.setBounds(0,25,500,40);
		titleLabel.setForeground(new Color(255,255,255));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(fontTitle); 
		panel.add(titleLabel);

		//网盘名称标题（第二行）
		panName = new JLabel("网盘链接");
		panName.setBounds(80, 60, 200, 30);
		panName.setForeground(new Color(255, 255, 255));
		panName.setFont(font);
		panel.add(panName);
		

       //作品名称文本框（第二行）
		panNameText = new JTextField();
		panNameText.setBounds(80, 90, 330, 30);
		panNameText.setForeground(new Color(255, 255, 255));
		panNameText.setFont(font);
		panNameText.setForeground(new Color(50, 50, 50));
		panel.add(panNameText);
		
		// 网盘名称标题（第三行）
		passwordName = new JLabel("密码");
		passwordName.setBounds(80, 130, 200, 30);
		passwordName.setForeground(new Color(255, 255, 255));
		passwordName.setFont(font);
		panel.add(passwordName);

		// 作品名称文本框（第三行）
		passwordText = new JTextField();
		passwordText.setBounds(80, 160, 330, 30);
		passwordText.setForeground(new Color(255, 255, 255));
		passwordText.setFont(font);
		passwordText.setForeground(new Color(50, 50, 50));
		panel.add(passwordText);
	
		// 创建提交按钮
		submitButton = new JButton("提交");
		submitButton.setBounds(80, 220, 140, 40);
		submitButton.setBackground(new Color(33, 186, 69));
		submitButton.setBorderPainted(false);
		submitButton.setFont(font);
		submitButton.setFocusable(false);
		submitButton.setForeground(new Color(255, 255, 255));
		submitButton.addActionListener(new SubmitBtnActionListener());
		panel.add(submitButton);
		
		// 创建返回按钮
		backAgainButton = new JButton("返回");
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
	 * 设置信息
	 */
	public void setData()
	{
		titleLabel.setText(Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()).getMatchname());
		UploadFileEntity uploadFileEntity = DaoUtils.selectUploadWork(Main.getInstance().getUserEntity(), Main.getInstance().getMatchEntities().get(Main.getInstance().getPersonalFrame().getIndex()));
		//如果存在记录
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
				JOptionPane.showMessageDialog(UploadFileFrame.this, "时间已经截止");
				return;
			}
			if(res != null)
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "已经报名");
				return;
			}
			String url = UploadFileFrame.this.panNameText.getText();
			String password = UploadFileFrame.this.passwordText.getText();
			if(url.equals("") || password.equals(""))
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "请填写信息");
				return;
			}
			UploadFileEntity uploadFileEntity = new UploadFileEntity(process.getUserEntity().getUid(), process.getMatchEntities().get(process.getPersonalFrame().getIndex()).getMatchname(), new Date(), url, password);
			int resCode = DaoUtils.insertUploadWork(uploadFileEntity);
			if(resCode != 0)
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "提交成功");
			}
			else
			{
				JOptionPane.showMessageDialog(UploadFileFrame.this, "提交失败");
			}
			
			UploadFileFrame.this.setVisible(false);
			Main.getInstance().getPersonalFrame().setData();
			Main.getInstance().getPersonalFrame().setVisible(true);
			Main.getInstance().getPersonalFrame().setEnabled(true);
		}
		
	}
}
