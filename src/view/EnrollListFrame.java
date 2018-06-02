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

	private JLabel matchLabel;//比赛信息
	

	private JPanel panel;
	private JButton backAgainButton;// 返回
	private JComboBox<String> comboBox;//下拉列表
	private JTable table;//菜单
	private JScrollPane scrollPane;//滚动面板
	public EnrollListFrame() {
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// 字体对象
		Font font = new Font("微软雅黑", 0, 15);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(60, 60, 60));
		this.add(panel);

		// 用户信息
		this.setTitle("比赛信息");
		this.setLocationRelativeTo(null);
		// 标题控件
		matchLabel = new JLabel("比赛项目");
		matchLabel.setBounds(50, 25, 300, 30);
		matchLabel.setForeground(new Color(255, 255, 255));
		matchLabel.setFont(font);
		panel.add(matchLabel);

		// 下拉菜单
		comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 25, 500, 30);
		comboBox.addItemListener(new ComBoxItemListener());
		panel.add(comboBox);

		String[] columnNames = { "学号", "姓名", "报名时间", "上传时间", "查重报名", "查看作品" };
		
		// 定义表格数据的二维数组
		String[][] data = { { "", "", "", "", "", "" }};
		
		// 创建指定列名和数据的表格
		table = new JTable(data, columnNames) {
             public boolean isCellEditable(int row, int column){return false;}
	      }; 
	      
		// 创建显示表格的滚动面板
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 70, 600, 300);
		
		panel.add(scrollPane);

		
		// 创建返回按钮
		backAgainButton = new JButton("返回");
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
			JOptionPane.showMessageDialog(this, "暂无比赛");
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
		columnNames.addAll(Arrays.asList(new String[]{ "学号", "姓名", "报名时间", "上传时间"}));
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
		//更新下拉
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
