package com.iot.pos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

public class RegistModel extends AbstractTableModel {
	String[] columTitle = {"no","거래업체","연락처"};
	
	ArrayList<String[]>data = new ArrayList<String[]>();
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public RegistModel(Connection con) {
		this.con = con;
		
		selectAll();
	}
	
	public void selectAll(){
		String sql = "select * from company";
		try {
			pstmt = con.prepareStatement(sql);
			rs  = pstmt.executeQuery();
			
			data.removeAll(data);
						
			while(rs.next()){
				String[] record = new String[columTitle.length];
				record[0] = Integer.toString(rs.getInt("COMPANY_ID"));
				record[1] = rs.getString("COMPANY_NAME");
				record[2] = rs.getString("COMPANY_PHONE");
				
				data.add(record);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs !=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null){
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
		String[] record =data.get(row);
		return record[col];
	}

	
}
