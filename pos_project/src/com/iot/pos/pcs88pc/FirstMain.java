package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//public class FirstMain extends JFrame implements ActionListener {
	public class FirstMain extends JPanel implements ActionListener {

	JPanel p_east, p_west;
	public JButton bt, bt1;
	JLabel la_bt, la_bt1;

	public FirstMain(){
		System.out.println("FirstMain 클릭");

		p_east = new JPanel();
		p_west = new JPanel();
		bt = new JButton("판매");
		bt1 = new JButton("관리");
		la_bt = new JLabel();
		la_bt1 = new JLabel();
		
		setLayout(new FlowLayout());
		
		p_east.add(bt);
		p_east.add(la_bt);
		p_west.add(bt1);
		p_west.add(la_bt1);
		
		p_east.setBackground(Color.cyan);
		p_west.setBackground(Color.cyan);
		p_east.setPreferredSize(new Dimension(400, 400));
		p_west.setPreferredSize(new Dimension(400, 400));
		
		p_east.setBounds(10, 20, 150, 50);
		p_west.setBounds(10, 20, 200, 50);
		
		bt.addActionListener(this);
		bt1.addActionListener(this);
		
		add(p_east);
		add(p_west);
		
		setSize(1024, 960);
		setVisible(true);
		System.out.println("FirstMain 로드 완료");

	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("판매/관리 화면이 나옵니다!");
	}
	/*
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new FirstMain());

		frame.setSize(1024, 960);
		frame.setVisible(true);
	}
	*/
}
