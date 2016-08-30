package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class InventoryInformation extends JFrame {
	JPanel p_north, p_center,p_west, p_east, p_south;
	Choice ch;
	JTextField txt;
	JButton bt_search, bt_regist;
	JTable table;
	JScrollPane scroll;
	InventoryTableModel model;
	
	public InventoryInformation() {
		// 여백
		p_west = new JPanel();
		p_east = new JPanel();
		p_south = new JPanel();
		
		// 북쪽 디자인
		p_north = new JPanel();
		ch = new Choice();
		txt = new JTextField(15);
		bt_search = new JButton("검색");
		bt_regist = new JButton("등록");
		
		p_center = new JPanel();
		model=new InventoryTableModel();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		ch.add("분류");
		
		p_north.setPreferredSize(new Dimension(1024, 50));		
		p_north.add(ch);
		p_north.add(txt);
		p_north.add(bt_search);
		p_north.add(bt_regist);
		
		scroll.add(p_center);
		
		add(p_west, BorderLayout.WEST);
		add(p_east, BorderLayout.EAST);
		add(p_east, BorderLayout.SOUTH);

		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 960);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new InventoryInformation();
	}
	
}
