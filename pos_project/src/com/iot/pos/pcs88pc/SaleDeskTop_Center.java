package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SaleDeskTop_Center extends JPanel{
	CenterModel model;
	JPanel center;
	JTable table;
	JScrollPane scroll;
	CenterGrid grid;
	
	
	public SaleDeskTop_Center(){
		center = new JPanel(new BorderLayout());
		table = new JTable(model=new CenterModel());
		scroll = new JScrollPane(table);
		
		scroll.setPreferredSize(new Dimension(540, 450));
		table.setRowHeight(50);
		
		grid = new CenterGrid();
		
		center.setPreferredSize(new Dimension(550, 450));
		grid.setPreferredSize(new Dimension(400, 450));
		
		center.add(scroll);
		
		add(center);
		add(grid);
		
		setBackground(Color.RED);
		
		setVisible(true);
		setSize(1000,600);
		
	}
	

}
