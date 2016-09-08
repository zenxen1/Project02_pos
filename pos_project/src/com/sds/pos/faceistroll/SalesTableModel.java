package com.sds.pos.faceistroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.DocFlavor.CHAR_ARRAY;
import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class SalesTableModel extends AbstractTableModel {
	String[] column = { "거래번호", "판매내역", "상품정보", "수량" };
	ArrayList<String[]> data = new ArrayList<String[]>();
	SalesManagement salesManagement;
	PolylineBarChart barChart;

	public SalesTableModel(SalesManagement salesManagement, PolylineBarChart barChart) {
		this.salesManagement =salesManagement;
		this.barChart=barChart;
		showSale();
	}

	public void showSale(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select saledetail_id, s.sale_id as id, p.product_name as name, sale_count";
		sql = sql + " from saledetail d, sale s, product p";
		sql = sql + " where d.sale_id=s.sale_id and d.product_id=p.product_id";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			data.removeAll(data);
			while(rs.next()){
				String[] record = new String[column.length];
				record[0]=Integer.toString(rs.getInt("saledetail_id"));
				record[1]=Integer.toString(rs.getInt("id"));
				record[2]=rs.getString("name");
				record[3]=Integer.toString(rs.getInt("sale_count"));
				data.add(record);
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
	
	public void searchSale(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select saledetail_id, s.sale_id as id, p.product_name as name, sale_count";
		sql = sql + " from saledetail d, sale s, product p, subcategory sb";
		sql = sql + " where d.sale_id=s.sale_id and  d.product_id=p.product_id";
		sql = sql + " and sb.subcategory_id=p.subcategory_id and subgroup=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, salesManagement.ch_subcategory.getSelectedItem());	
			rs = pstmt.executeQuery();
			data.removeAll(data);
			while(rs.next()){
				String[] record = new String[column.length];
				record[0]=Integer.toString(rs.getInt("saledetail_id"));
				record[1]=Integer.toString(rs.getInt("id"));
				record[2]=rs.getString("name");
				record[3]=Integer.toString(rs.getInt("sale_count"));
				data.add(record);
			}
			barChart.setData(data);
			barChart.dataChange();
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
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	public String getColumnName(int col) {
		return column[col];
	}

	public int getColumnCount() {
		return column.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0)[arg1];
	}

}
