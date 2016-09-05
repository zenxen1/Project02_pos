package com.sds.pos.faceistroll;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iot.pos.PosMain;


public class OftenUsedProduct extends JPanel implements ActionListener, ItemListener{
	JPanel p_all;
	Choice ch_top, ch_sub;
	JTextField txt_search;
	JButton bt_search, bt_regist;
	public JButton bt_main;
	JPanel p_north, p_center, p_west, p_east, p_south;
	HashMap<String, Integer> topMap = new HashMap<String, Integer>();
	HashMap<String, Integer> subMap = new HashMap<String, Integer>();

	OftenUsedTableModel info;
	JTable t_info;
	JScrollPane scroll;
	
	RegistProduct registProduct;

	public OftenUsedProduct() {
		p_all = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		txt_search = new JTextField("상품명을 입력하세요",25);
		bt_search = new JButton("검색");
		bt_regist = new JButton("등록");
		bt_main = new JButton("메인화면");
		info = new OftenUsedTableModel();
		t_info = new JTable(info);
		scroll = new JScrollPane(t_info);
		p_north = new JPanel();
		p_east = new JPanel();
		p_south = new JPanel();
		p_west = new JPanel();
		p_center = new JPanel();
		
		ch_top.add("상위분류");	
		
		ch_sub.add("하위분류");
		
		p_all.setLayout(new BorderLayout());
		
		txt_search.setPreferredSize(new Dimension(400, 29));
		
		p_north.add(bt_main);
		p_north.add(ch_top);
		p_north.add(ch_sub);
		p_north.add(txt_search);
		p_north.add(bt_search);
		p_north.add(bt_regist);
		
		p_all.add(p_north, BorderLayout.NORTH);
		p_all.add(scroll, BorderLayout.CENTER);
		
		add(p_all);
		ch_top.addItemListener(this);
		getTopCategory();

		bt_search.addActionListener(this);
		bt_regist.addActionListener(this);	
	}
	
	//탑 카테고리 가져오기
	public void getTopCategory(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from topcategory";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ch_top.add(rs.getString("topgroup"));
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
	
	//서브카테고리 가져오기
	public void getSubCategory(int subcategory_id){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select * from subcategory where topcategory_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			rs = pstmt.executeQuery();
			ch_sub.removeAll();
			subMap.clear();
			ch_sub.add("하위 카테고리");
			while(rs.next()){
				ch_sub.add(rs.getString("subgroup"));
				subMap.put(rs.getString("subgroup"), rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_search){
			search_Product();
		}else if(obj == bt_regist){
			registProduct = new RegistProduct();
		}
	}
	
	public void search_Product(){
		info.searchProduct();
	}
	
	public void itemStateChanged(ItemEvent e) {
		getSubCategory(topMap.get(ch_top.getSelectedItem()));
		
	}


}
