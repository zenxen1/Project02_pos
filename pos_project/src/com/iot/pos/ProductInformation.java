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

public class ProductInformation extends JFrame {
	JPanel p_north, p_center;
	Choice ch;
	JTextField txt;
	JButton bt_search, bt_regist;
	JTable table;
	JScrollPane scroll;
	ProductModel model;
	
	public ProductInformation() {
		p_north = new JPanel();
		ch = new Choice();
		txt = new JTextField(15);
		bt_search = new JButton("검색");
		bt_regist = new JButton("등록");
		table = new JTable(model= new ProductModel());
		
		
		ch.add("분류");
		
		p_north.setPreferredSize(new Dimension(1024, 50));		
		p_north.add(ch);
		p_north.add(txt);
		p_north.add(bt_search);
		p_north.add(bt_regist);
		
		p_center = new JPanel();
		
		p_center.add(table);
		
		scroll = new JScrollPane(p_center);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 960);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ProductInformation();
	}
	
}
