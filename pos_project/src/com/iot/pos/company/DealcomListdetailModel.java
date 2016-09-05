package com.iot.pos.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class DealcomListdetailModel extends AbstractTableModel{
	String[] columTilte = {"dealdetail_id","거래내역","상품정보","수량"}; 
	
	ArrayList<String[]> list = new ArrayList<String[]>();
	int maxdealid;
	
	public DealcomListdetailModel(int maxdealid) {
		this.maxdealid = maxdealid;
		//selectAll(maxdealid);
	}

	public void selectAll(int maxdealid){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		//String sql = "select * from dealdetail where deal_id = "+maxdealid+" order by deal_id desc";
		String sql = "select dealdetail_id, deal_id, s.subgroup as uuuu, dealdetail_count from dealdetail d, subcategory s where deal_id = "+maxdealid+" and d.product_id = s.subCATEGORY_ID order by deal_id desc";
		//System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list.removeAll(list);
			
			while(rs.next()){
				String[] record = new String[columTilte.length];
				record[0] = Integer.toString(rs.getInt("dealdetail_id"));
				record[1] = Integer.toString(rs.getInt("deal_id"));
				record[2] = rs.getString("uuuu");
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
