package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DealcomRegist extends JFrame{
	JPanel p;
	JLabel la1,la2,la3;
	JTextField tf1,tf2;
	JTable table;
	JScrollPane scroll;
	JButton bt1,bt2,bt3;
	RegistModel model;
	
	
	public DealcomRegist() {
		la1 = new JLabel("거래업체등록");
		la2 = new JLabel("거래업체");
		la3= new JLabel("연락처");
		
		tf1 = new JTextField(20);
		tf2 = new JTextField(20);
		
		table = new JTable(model=new RegistModel());
		scroll = new JScrollPane(table);
		p = new JPanel();
		bt1 = new JButton("등록");
		bt2 = new JButton("취소");
		bt3 = new JButton("삭제");
		
		add(la1,BorderLayout.NORTH);
		add(scroll);
		
		p.setPreferredSize(new Dimension(300, 960));
		
		p.add(la2);
		p.add(tf1);
		p.add(la3);
		p.add(tf2);
		p.add(bt1);
		p.add(bt2);
		p.add(bt3);
		add(p,BorderLayout.WEST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 960);
		setVisible(true);
		
	}
	
	public static void main(String[] args){
		new DealcomRegist();
	}

}
