package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DealcomList extends JFrame {
	JPanel p_north, p_center;
	Choice ch;
	JTextField tf_serch;
	JButton bt_serch, bt_regist;
	JTable table;
	JScrollPane scroll;
	DealcomListModel model;
		
	
	public DealcomList() {
		p_north = new JPanel();
		p_center = new JPanel();
		ch = new Choice();
		tf_serch = new JTextField(20);
		bt_serch = new JButton("검색");
		bt_regist = new JButton("거래업체등록");
		table = new JTable(model = new DealcomListModel());
		scroll = new JScrollPane(table);
		
		ch.add("분류");
		
		p_north.setPreferredSize(new Dimension(1024, 50));
		p_north.add(ch);
		p_north.add(tf_serch);
		p_north.add(bt_serch);
		p_north.add(bt_regist);
		
		p_center.add(scroll);
		
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 960);
		setResizable(false);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new DealcomList();
	}
}
