package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DealcomList extends JPanel {
	JPanel p_north, p_center;
	Choice ch;
	JTextField tf_serch;
	JButton bt_serch, bt_regist;
	JTable table;
	JScrollPane scroll;
	DealcomListModel model;
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "posman";
	String password = "posman";
	
	Connection con;
		
	
	public DealcomList() {
		p_north = new JPanel();
		p_center = new JPanel();
		ch = new Choice();
		tf_serch = new JTextField(20);
		bt_serch = new JButton("검색");
		bt_regist = new JButton("거래업체등록");
		
		//connect();
		table = new JTable(model = new DealcomListModel());
		scroll = new JScrollPane(table);
		
		//p_center.setLayout(new FlowLayout());
		scroll.setPreferredSize(new Dimension(1000, 900));
		p_north.setPreferredSize(new Dimension(1000, 50));
		p_center.setPreferredSize(new Dimension(1000, 900));
		
		ch.add("분류");
		
		p_north.add(ch);
		p_north.add(tf_serch);
		p_north.add(bt_serch);
		p_north.add(bt_regist);
		
		p_center.add(scroll);
		
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		/*
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// db닫기!!
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				// 프로세스 죽이기!
				System.exit(0);
			}
		});*/
		
	
		//setSize(1024, 960);
		//setResizable(false);
		//setVisible(true);
		
		
	}
	/*
	public void connect(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//setTitle("드라이버로드성공");
			con = DriverManager.getConnection(url, user, password);
			if(con==null){
				JOptionPane.showMessageDialog(this, "접속실패!!");
				return;
			}
			//setTitle("접속성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/
	/*
	public static void main(String[] args) {
		new DealcomList();
	}
	*/
}
