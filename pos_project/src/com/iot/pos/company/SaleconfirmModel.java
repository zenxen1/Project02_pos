package com.iot.pos.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SaleconfirmModel extends AbstractTableModel{
	String[] columTitle = {"ID","토탈거래금액","판매일","판매시간","직원"};
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	ArrayList<String[]> data = new ArrayList<String[]>(); 
	
	public SaleconfirmModel(Connection con) {
		this.con = con;
		selectAll();
	}
	
	public void selectAll(){
		String sql = "select * from sale";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String[] record = new String[columTitle.length];
				record[0] = Integer.toString(rs.getInt("sale_id"));
				record[1] = Integer.toString(rs.getInt("total_money"));
				record[2] = rs.getString("sale_date");
				record[3] = rs.getString("sale_time");
				record[4] = Integer.toString(rs.getInt("user_id"));
				
				data.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getColumnName(int col) {
		return columTitle[col];
	}
	
	public int getColumnCount() {
		return columTitle.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		String[] record = data.get(row);
		return record[col];
	}

}
