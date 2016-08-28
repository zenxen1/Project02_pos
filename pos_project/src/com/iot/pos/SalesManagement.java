package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SalesManagement extends JFrame{

	
	JPanel p_north, p_south;
	JLabel la_north;
	Choice ch_menu;
	SalesTable model;
	JTable table;
	JScrollPane scroll;
	
	
	public SalesManagement() {
		p_north = new JPanel();
		p_south = new JPanel();
		la_north = new JLabel("        ");
		ch_menu = new Choice();
		model = new SalesTable();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		ch_menu.add("텍스트");
		ch_menu.add("날짜");
		ch_menu.add("매출");
		ch_menu.add("금액");		
		
		la_north.setPreferredSize(new Dimension(800, 100));
		ch_menu.setPreferredSize(new Dimension(80, 60));
		p_south.setPreferredSize(new Dimension(1024, 250));
		
		p_north.add(la_north);
		p_north.add(ch_menu);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		
		setSize(1024, 768);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new SalesManagement();

	}

}
