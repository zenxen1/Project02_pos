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

	
<<<<<<< HEAD
	JPanel p_north, p_south, p_west, p_east;
=======
	JPanel p_north, p_south;
>>>>>>> efe53f73f301d329226ad95994378e6d5ce38a28
	JLabel la_north;
	Choice ch_menu;
	SalesTable model;
	JTable table;
	JScrollPane scroll;
	
	
	public SalesManagement() {
		p_north = new JPanel();
		p_south = new JPanel();
<<<<<<< HEAD
		p_west = new JPanel();
		p_east = new JPanel();
=======
>>>>>>> efe53f73f301d329226ad95994378e6d5ce38a28
		la_north = new JLabel("        ");
		ch_menu = new Choice();
		model = new SalesTable();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		ch_menu.add("�ؽ�Ʈ");
		ch_menu.add("��¥");
		ch_menu.add("����");
		ch_menu.add("�ݾ�");		
		
		la_north.setPreferredSize(new Dimension(800, 100));
		ch_menu.setPreferredSize(new Dimension(80, 60));
		p_south.setPreferredSize(new Dimension(1024, 250));
		
		p_north.add(la_north);
		p_north.add(ch_menu);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
<<<<<<< HEAD
		add(p_east, BorderLayout.EAST);
		add(p_west, BorderLayout.WEST);
=======
>>>>>>> efe53f73f301d329226ad95994378e6d5ce38a28
		
		setSize(1024, 768);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new SalesManagement();

	}

}
