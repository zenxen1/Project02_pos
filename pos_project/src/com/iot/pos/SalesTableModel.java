package com.iot.pos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SalesTableModel extends AbstractTableModel{
	String[] column = {"번호","날짜 ","매출","금액","담당자"};
	ArrayList<String[]> data = new ArrayList<String[]>();
	
	
	public SalesTableModel() {
		selectSale();
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

	public void selectSale(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
