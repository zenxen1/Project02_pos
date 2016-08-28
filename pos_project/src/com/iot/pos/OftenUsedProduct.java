package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OftenUsedProduct extends JFrame implements ActionListener{
	
	Choice ch;
	JTextField txt_search;
	JButton bt_search, bt_regist;
	JPanel p_north, p_center;
	TableInfo info;
	JTable t_info;
	JScrollPane scroll;

	
	public OftenUsedProduct() {
		ch = new Choice();
		txt_search = new JTextField(25);
		bt_search = new JButton("검색");
		bt_regist = new JButton("등록");
		info = new TableInfo();
		t_info = new JTable(info);
		scroll = new JScrollPane(t_info);
		p_north = new JPanel();
		p_center = new JPanel();
		
		ch.add("분류");
		ch.add("간편식사");
		ch.add("즉석요리");
		ch.add("스낵");
		ch.add("식품");
		ch.add("음료");
		ch.add("생활용품");
		ch.add("아이스크림");
		
		
		txt_search.setPreferredSize(new Dimension(400, 29));
		
		p_north.add(ch);
		p_north.add(txt_search);
		p_north.add(bt_search);
		p_north.add(bt_regist);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		bt_search.addActionListener(this);
		bt_regist.addActionListener(this);	
		
		
		setSize(1028, 768);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_search){
			System.out.println("검색");
		}else if(obj == bt_regist){
			System.out.println("등록");
		}
	}
	
	public static void main(String[] args) {
		new OftenUsedProduct();
	}


}
