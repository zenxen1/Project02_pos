package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.iot.pos.pcs88pc.CenterGrid;

public class RegistProduct extends JFrame implements ActionListener{
	JPanel p_container, p_west, p_east, p_south, p_center, p_1, p_2, p_3;
	String[] bt_title = {"1번","2번","3번","4번","5번","6번","7번","8번","9번"};
	JButton[] bt;
	//CenterGrid cen;
	
	public RegistProduct() {
		p_container = new JPanel();
		p_south = new JPanel();
		p_east = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		p_1 = new JPanel();
		p_2 = new JPanel();
		p_3 = new JPanel();
		bt = new JButton[9];
		
		for(int i=0;i<bt_title.length;i++){
			bt[i] = new JButton(bt_title[i]);
			bt[i].setPreferredSize(new Dimension(80, 80));
			p_center.add(bt[i]);
			bt[i].addActionListener(this);
		}
		
		
		
		p_center.setLayout(new FlowLayout());
		p_center.setPreferredSize(new Dimension(310, 310));
		
		p_2.add(bt[3]);
		p_2.add(bt[4]);
		p_2.add(bt[5]);
		p_3.add(bt[6]);
		p_3.add(bt[7]);
		p_3.add(bt[8]);
		
		p_center.add(p_2);
		p_center.add(p_3);
		
		p_container.add(p_west, BorderLayout.WEST);
		p_container.add(p_east, BorderLayout.EAST);
		p_container.add(p_south, BorderLayout.SOUTH);
		p_container.add(p_center);
		
		add(p_container);
		
		setSize(350, 350);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

	}
	
	public void changeName(){
		
	}

}


