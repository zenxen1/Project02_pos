package com.sds.pos.koreain14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.iot.pos.PosMain;

public class StaffTableModel extends AbstractTableModel {
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	String[] columnTitle ={"이름","연락처","계정"};
	
	public StaffTableModel() {
		getUsers();
	}
	
	@Override
	public int getColumnCount() {
		return columnTitle.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnTitle[col];
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		String[] record=list.get(row);
		return record[col];
	}
	
	public void getUsers(){
		Connection con=PosMain.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from pos_user order by user_id desc";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				String[] record=new String[3];
				record[0] = rs.getString("user_name");
				record[1] = rs.getString("user_phone");
				record[2] = rs.getString("user_acount");
				
				list.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
}
