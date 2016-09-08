package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//public class FirstMain extends JFrame implements ActionListener {
	public class FirstMain extends JPanel implements ActionListener {

	public JButton bt, bt1;
	JPanel p_east, p_west, p_center, p_north, p_south;
	JLabel la_empty;
	Font font;
	

	public FirstMain(){
		
		p_east = new JPanel();
		p_west = new JPanel();
		p_north = new JPanel();
		p_south = new JPanel();
		p_center = new JPanel();
		
		p_north.setPreferredSize(new Dimension(1024, 300));
		p_south.setPreferredSize(new Dimension(1024, 200));
		
		//#패널에 색깔주기#
		p_east.setBackground(new Color(123,182,212));
		p_west.setBackground(new Color(123,182,212));
		p_south.setBackground(new Color(123,182,212));
		p_north.setBackground(new Color(123,182,212));
		p_center.setBackground(new Color(123,182,212));
		
		
		bt = new JButton("판매");
		bt.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		bt.setForeground(Color.white);
		bt.setBackground(new Color(160,206,222));
		bt1 = new JButton("관리");
		bt1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		bt1.setForeground(Color.white);
		bt1.setBackground(new Color(160,206,222));
		
		la_empty = new JLabel();
		
		setLayout(new BorderLayout());
		
		bt.setPreferredSize(new Dimension(200, 200));
		bt1.setPreferredSize(new Dimension(200, 200));
		
		p_center.add(bt);
		p_center.add(bt1);
		
		//bt.setBounds(10, 20, 150, 400);
		//bt1.setBounds(10, 20, 200, 400);
		
		add(p_north, BorderLayout.NORTH);
		add(p_south, BorderLayout.SOUTH);
		add(p_east, BorderLayout.EAST);
		add(p_west, BorderLayout.WEST);
		
		add(p_center);
		
		
		// #버튼에 이벤트 연결#
		bt.addActionListener(this);
		bt1.addActionListener(this);
		
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
