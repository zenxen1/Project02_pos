package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iot.pos.PosMain;
//import com.iot.pos.pcs88pc.CenterGrid;


public class OftenUsedProduct extends JPanel implements ActionListener{
	JPanel p_all;
	Choice ch_top, ch_sub;
	JTextField txt_search;
	JButton bt_search, bt_regist;
	public JButton bt_main;
	JPanel p_north, p_center, p_west, p_east, p_south;

	OftenUsedTableModel info;
	JTable t_info;
	JScrollPane scroll;
	
	//CenterGrid centerGrid;
	String name;

	public OftenUsedProduct() {
		p_all = new JPanel();
		txt_search = new JTextField("상품명을 입력하세요",25);
		bt_search = new JButton("검색");
		bt_regist = new JButton("등록");
		bt_main = new JButton("메인화면");
		info = new OftenUsedTableModel(this);
		t_info = new JTable(info);
		scroll = new JScrollPane(t_info);
		p_north = new JPanel();
		p_east = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();

		p_all.setLayout(new BorderLayout());
		txt_search.setPreferredSize(new Dimension(400, 29));
		
		p_north.add(bt_main);
		p_north.add(txt_search);
		p_north.add(bt_search);
		p_north.add(bt_regist);
		scroll.setPreferredSize(new Dimension(924, 600));
		
		p_all.add(p_north, BorderLayout.NORTH);
		p_all.add(scroll, BorderLayout.CENTER);
		
		add(p_all);
		
		bt_search.addActionListener(this);
		bt_regist.addActionListener(this);	
		txt_search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txt_search.setText("");
			}
		});
		t_info.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int row = t_info.getSelectedRow();
				name = (String)t_info.getValueAt(row, 2);
				System.out.println("눌렀어요? "+name);
			}
		});
	}
		
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_search){
			info.searchProduct();
			t_info.updateUI();
		}else if(obj == bt_regist){
			selectRs();
		}
	}
	
	public void selectRs(){
		System.out.println("시험용");
	}
	
	
	
	
	public void search_Product(){
		info.searchProduct();
	}
	

}
