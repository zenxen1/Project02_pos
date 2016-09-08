package com.iot.pos.pcs88pc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaleDeskTop_North extends JPanel {

	JPanel p_center;
	JLabel la_sale;
	//JTextField tf_login, tf_watch;
	//JButton bt_login, bt_watch;
	JButton bt_main;
	JLabel time;
	Thread thread;
	
	public SaleDeskTop_North(){
		setLayout(new BorderLayout());
		
		p_center = new JPanel();
		la_sale = new JLabel("판매화면");
		time = new JLabel("시계");
		//tf_login = new JTextField(15);
		//tf_watch = new JTextField(15);
		//bt_login = new JButton("로그인 한 사람");
		//bt_watch = new JButton("시계");
		bt_main = new JButton("메인화면");
		
		add(la_sale,BorderLayout.WEST);
		add(bt_main,BorderLayout.EAST);
		
		p_center.setLayout(new FlowLayout(FlowLayout.LEFT,40,10));
		p_center.add(time);
		//p_center.add(bt_login);
		//p_center.add(tf_login);
		//p_center.add(bt_watch);
		//p_center.add(tf_watch);
		p_center.setPreferredSize(new Dimension(300, 100));
		add(p_center);
		
		thread = new Thread(){ //스레드하나 만들자
			public void run(){ //런을 재정의 하고
				while(true){
					
					try {
						thread.sleep(1000); //너무빠르니깐 논런어블부분으로 옮겼다오자
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				
					time();
				}
			}
			
		};
		
		thread.start();
	}
	
	public void time(){
		String yy   = new java.text.SimpleDateFormat("yyyy").format(new java.util.Date());
		String mm   = new java.text.SimpleDateFormat("MM").format(new java.util.Date());
		String dd   = new java.text.SimpleDateFormat("dd").format(new java.util.Date());
		
		String h   = new java.text.SimpleDateFormat("HH").format(new java.util.Date());
		String m = new java.text.SimpleDateFormat("mm").format(new java.util.Date());
		String d   = new java.text.SimpleDateFormat("ss").format(new java.util.Date());
		
		time.setText(yy +"년"+mm+"월"+dd+"일"+h+"시"+m+"분"+d+"초");
	}
	
}