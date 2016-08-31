package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SalesManagement extends JFrame{

	
	JPanel p_north, p_south, p_west, p_east;
	JLabel la_north;
	Choice ch_menu, ch_topcategory, ch_subcategory;
	SalesTableModel model;
	JTable table;
	JScrollPane scroll;
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "posman";
	String password = "posman";
	
	public static Connection con;
	
	
	public SalesManagement() {
		connectDB();
		p_north = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		la_north = new JLabel("        ");
		ch_menu = new Choice();
		ch_topcategory = new Choice();
		ch_subcategory = new Choice();
		model = new SalesTableModel();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		ch_menu.add("매출확인");
		ch_menu.add("일");
		ch_menu.add("월");
		ch_menu.add("연");		
		
		ch_topcategory.add("상위분류");
		ch_subcategory.add("하위분류");
		
		la_north.setPreferredSize(new Dimension(700, 100));
		ch_menu.setPreferredSize(new Dimension(80, 60));
		p_south.setPreferredSize(new Dimension(1024, 325));
		
		p_north.add(la_north);
		p_north.add(ch_topcategory);
		p_north.add(ch_subcategory);
		p_north.add(ch_menu);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);

		add(p_east, BorderLayout.EAST);
		add(p_west, BorderLayout.WEST);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 768);
		setVisible(true);
		
	}
	
	//연결
	public void connectDB(){
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if(con != null){
				setTitle("online");
			}else{
				setTitle("접속실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new SalesManagement();

	}

}
