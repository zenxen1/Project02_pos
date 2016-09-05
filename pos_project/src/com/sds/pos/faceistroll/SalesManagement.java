package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import com.iot.pos.PosMain;

public class SalesManagement extends JPanel implements ItemListener{
	
	public JButton bt_main;
	JPanel p_all;
	JPanel p_north, p_south, p_west, p_east, p_center;
	JLabel la_north;
	Choice ch_topcategory, ch_subcategory;
	SaleGraph saleGraph;
	SalesTableModel model;
	JTable table;
	JScrollPane scroll;
	HashMap<String, Integer> topMap = new HashMap<String, Integer>();
	HashMap<String, Integer> subMap = new HashMap<String, Integer>();
	
	public SalesManagement() {
		bt_main = new JButton("메인화면");
		p_north = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		p_center = new JPanel();
		la_north = new JLabel("        ");
		ch_topcategory = new Choice();
		ch_subcategory = new Choice();
		model = new SalesTableModel();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		p_all = new JPanel();
		p_all.setLayout(new BorderLayout());
		
		
		ch_topcategory.add("상위분류");
		ch_subcategory.add("하위분류");
		
		la_north.setPreferredSize(new Dimension(700, 100));
		p_south.setPreferredSize(new Dimension(1024, 325));
		
		p_north.add(bt_main);
		p_north.add(la_north);
		p_north.add(ch_topcategory);
		p_north.add(ch_subcategory);
		
		ch_topcategory.addItemListener(this);
		getTopCategory();
		
		p_all.add(p_north, BorderLayout.NORTH);
		p_all.add(scroll);
		p_all.add(p_south, BorderLayout.SOUTH);
		p_all.add(p_west, BorderLayout.WEST);
		p_all.add(p_east, BorderLayout.EAST);

		add(p_all);	
	}
	
	//상위 카테고리 가져오기
	public void getTopCategory(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from topcategory";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ch_topcategory.add(rs.getString("topgroup"));
				topMap.put(rs.getString("topgroup"), rs.getInt("topcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//하위 카테고리 가져오기
	public void getSubCategory(int subcategory_id){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select * from subcategory where topcategory_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			rs = pstmt.executeQuery();
			ch_subcategory.removeAll();
			subMap.clear();
			ch_subcategory.add("하위 카테고리");
			while(rs.next()){
				ch_subcategory.add(rs.getString("subgroup"));
				subMap.put(rs.getString("subgroup"), rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public void itemStateChanged(ItemEvent e) {
		getSubCategory(topMap.get(ch_topcategory.getSelectedItem()));
	}
	

}
