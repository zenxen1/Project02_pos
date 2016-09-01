package com.iot.pos.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DealcomListdetailModel extends AbstractTableModel{
	String[] columTilte = {"dealdetail_id","거래내역","상품정보","수량"}; 
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	public DealcomListdetailModel() {
		selectAll();
	}

	public void selectAll(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = "select * from dealdetail";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list.removeAll(list);
			
			while(rs.next()){
				String[] record = new String[columTilte.length];
				record[0] = Integer.toString(rs.getInt("dealdetail_id"));
				record[1] = Integer.toString(rs.getInt("deal_id"));
				record[2] = Integer.toString(rs.getInt("product_id"));
				record[3] = Integer.toString(rs.getInt("dealdetail_count"));
				
				list.add(record);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String getColumnName(int col) {
		return columTilte[col];
	}
	public int getColumnCount() {
		return columTilte.length;
	}

	public int getRowCount() {
		return list.size();
	}

	public Object getValueAt(int row, int col) {
		String[] record = list.get(row);
		return record[col];
	}

}
