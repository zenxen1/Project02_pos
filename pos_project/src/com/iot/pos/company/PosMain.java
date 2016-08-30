package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PosMain extends JFrame implements ActionListener{
	String[] menuTitle ={"매출관리","업체등록","업체조회"};
	JButton[] menu;
	JPanel p_north;
	JPanel[] content;
	JPanel center;
	
	//데이터베이스 접속 관련...
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "posman";
	String password= "posman";
	
	private static Connection con;
		
	
	public PosMain() {
		connectDB();
		menu = new JButton[3];
		content = new JPanel[menu.length];
		
		p_north = new JPanel();
		p_north.setLayout(new GridLayout(1, menu.length));
		
		for(int i=0;i<menu.length;i++){
			menu[i] = new JButton(menuTitle[i]);
			p_north.add(menu[i]);
			menu[i].addActionListener(this);
		}
		
		content[0] = new Saleconfirm();
		content[1] = new DealcomRegist();
		content[2] = new DealcomList();
		
		center = new JPanel();
		
		add(p_north,BorderLayout.NORTH);
		
		for(int i=0;i<menu.length;i++){
			center.add(content[i]);
			content[i].setPreferredSize(new Dimension(1024, 960));
			content[i].setVisible(false);
		}
		/*
		center.add(content[0]);
		content[0].setPreferredSize(new Dimension(1024, 960));
		content[0].setVisible(false);
		
		center.add(content[1]);
		content[1].setPreferredSize(new Dimension(1024, 960));
		content[1].setVisible(false);
		
		center.add(content[2]);
		content[2].setPreferredSize(new Dimension(1024, 960));
		content[2].setVisible(true);
		*/
		
		add(center);
		
		showContent(0);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				if(con!=null){
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
		
		setSize(1024,960);
		setVisible(true);
		
	}
	
	public void connectDB(){
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
			if(con != null){
				setTitle("접속됨");
			}else{
				setTitle("접속실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void showContent(int current){
		for(int i=0;i<menu.length;i++){
			if(i==current){
				content[i].setVisible(true);
			}else{
				content[i].setVisible(false);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		
		for(int i=0;i<menu.length;i++){
			if(obj==menu[i]){
				showContent(i);
			}
		}
	}
	
	public static Connection getConnection(){
		return con;
	}
	
	
	public static void main(String[] args) {
		new PosMain();
	}
	

}
