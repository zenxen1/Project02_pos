package com.sds.pos.koreain14;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class InventoryTableModel extends AbstractTableModel {
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	String[] columnTitle={"상품코드","품명","단가","판매가","제조사","수량"};
		
		
	public int getColumnCount() {
		return columnTitle.length;
	}
	
	public String getColumnName(int col) {
		return columnTitle[col];
	}

	public int getRowCount() {
		return list.size();
	}

	public Object getValueAt(int row, int col) {
		return list.get(row)[col];
	}
}
