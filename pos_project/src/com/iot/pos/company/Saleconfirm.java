package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Saleconfirm extends JFrame{
	JPanel p_north,p_south,p_west,p_east,p_center,p_graph;
	JLabel la_north;
	Choice ch;
	JTable table;
	JScrollPane scroll;
	SaleconfirmModel model;
	
	public Saleconfirm() {
		p_north = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		p_center = new JPanel();
		p_graph =new JPanel();
		
		la_north = new JLabel("매출확인(일일매출)");
		
		ch = new Choice();
		ch.add("일매출");
		ch.add("월매출");
		ch.add("년매출");
		
		table = new JTable(model = new SaleconfirmModel());
		scroll = new JScrollPane(table);
		
		
		la_north.setPreferredSize(new Dimension(800, 100));
		p_south.setPreferredSize(new Dimension(1024, 50));
		scroll.setPreferredSize(new Dimension(900, 400));
		p_graph.setPreferredSize(new Dimension(900, 400));
		ch.setPreferredSize(new Dimension(80, 60));
		
		p_north.add(la_north);
		p_north.add(ch);
		
		
		p_graph.setBackground(Color.YELLOW);
		
		p_center.add(scroll);
		p_center.add(p_graph);
		
		
		add(p_north,BorderLayout.NORTH);
		add(p_center,BorderLayout.CENTER);
		add(p_south,BorderLayout.SOUTH);
		add(p_east,BorderLayout.EAST);
		add(p_west,BorderLayout.WEST);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024,960);
		setResizable(false);
		setVisible(true);
			
	}
	

	public static void main(String[] args) {
		new Saleconfirm();
	}

}
