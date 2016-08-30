package com.iot.pos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel {
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	String[] columnTitle ={"이름","연락처","계정"};
	
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
		return list.get(row)[col];
	}
	

}
