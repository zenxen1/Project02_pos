package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegistProduct extends JFrame{
	JPanel p_container, p_north, p_center;
	JLabel la_north;
	JButton bt_regist;
	RegistProductTable model;
	JTable table;
	JScrollPane scroll;
	
	public RegistProduct() {
		p_container = new JPanel();
		p_north = new JPanel();
		p_center = new JPanel();
		la_north = new JLabel("  ");
		bt_regist = new JButton("µî·Ï");
		model = new RegistProductTable();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		setLayout(new BorderLayout());
		la_north.setPreferredSize(new Dimension(600, 100));
		p_north.add(la_north);
		p_north.add(bt_regist);
		p_center.add(scroll);
		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_center, BorderLayout.CENTER);
		
		add(p_container);
		
		setSize(800, 700);
		setVisible(true);
	}

}
