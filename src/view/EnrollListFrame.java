package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.EnrollEntity;
import dao.MatchEntity;
import dao.PersonallistEntity;
import run.Main;
import utils.DaoUtils;


public class EnrollListFrame extends JFrame {

	private JLabel matchLabel;//������Ϣ
	

	private JPanel panel;
	private JButton backAgainButton;// ����
	private JComboBox<String> comboBox;//�����б�
	private JTable table;//�˵�
	private JScrollPane scrollPane;//�������
	public EnrollListFrame() {
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// �������
		Font font = new Font("΢���ź�", 0, 15);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		this.add(panel);

		// �û���Ϣ
		this.setTitle("������Ϣ");
		this.setLocationRelativeTo(null);
		// ����ؼ�
		matchLabel = new JLabel("������Ŀ");
		matchLabel.setBounds(50, 25, 300, 30);
		matchLabel.setForeground(new Color(255, 255, 255));
		matchLabel.setFont(font);
		panel.add(matchLabel);

		// �����˵�
		comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 25, 500, 30);
		comboBox.addItemListener(new ComBoxItemListener());
		panel.add(comboBox);

		String[] columnNames = { "ѧ��", "����", "����ʱ��", "�ϴ�ʱ��", "���ر���", "�鿴��Ʒ" };
		
		// ���������ݵĶ�ά����
		String[][] data = { { "", "", "", "", "", "" }};
		
		// ����ָ�����������ݵı��
		table = new JTable(data, columnNames) {
             public boolean isCellEditable(int row, int column){return false;}
	      }; 
	      
		// ������ʾ���Ĺ������
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 70, 600, 300);
		
		panel.add(scrollPane);

		
		// �������ذ�ť
		backAgainButton = new JButton("����");
		backAgainButton.setBounds(500, 400, 150, 40);
		backAgainButton.setBackground(new Color(52, 152, 219));
		backAgainButton.setBorderPainted(false);
		backAgainButton.setFont(font);
		backAgainButton.setFocusable(false);
		backAgainButton.setForeground(new Color(255, 255, 255));
		backAgainButton.addActionListener(e ->{
			this.setVisible(false);
			Main.getInstance().getIssueMacthFrame().setVisible(true);
		});
		panel.add(backAgainButton);
	
	}
	
	public void setData()
	{
		List<MatchEntity> matchEntities = Main.getInstance().getMatchEntities();
		if(matchEntities.size() == 0)
		{
			JOptionPane.showMessageDialog(this, "���ޱ���");
			return;
		}
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		int index = comboBox.getSelectedIndex();
		List<PersonallistEntity> personallistEntities = DaoUtils.selectPersonal(matchEntities.get(index));
		 Vector<Vector> data = new Vector<Vector>();
		for(PersonallistEntity i : personallistEntities)
		{
			Vector<String> row = new Vector<String>();
			row.add(i.getUid());
			row.add(i.getName());
			row.add(fmt.format(i.getEnrolltime()));
			row.add(i.getUploadtime() == null ? "X" : fmt.format(i.getUploadtime()));
			data.add(row);
		}
		Vector<String> columnNames = new Vector<String>();
		columnNames.addAll(Arrays.asList(new String[]{ "ѧ��", "����", "����ʱ��", "�ϴ�ʱ��"}));
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table.setModel(model);
	}
	
	public class ComBoxItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				setData();
			}
		}
		
	}
	
	@Override
	public void setVisible(boolean b)
	{
		List<MatchEntity> matchEntities = DaoUtils.selectMatchType();
		Main.getInstance().setMatchEntities(matchEntities);
		panel.remove(comboBox);
		//��������
		comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 25, 500, 30);
		comboBox.addItemListener(new ComBoxItemListener());
		for(MatchEntity i : matchEntities)
		{
			comboBox.addItem(i.getMatchname());
		}
		panel.add(comboBox);
		super.setVisible(b);
	}
	
}
