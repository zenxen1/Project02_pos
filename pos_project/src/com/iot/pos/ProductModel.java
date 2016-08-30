package com.iot.pos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ProductModel extends AbstractTableModel {
	ArrayList<String[]> list = new ArrayList<String[]>();
	
	String[] columnTitle={"상품코드","품명","단가","판매가","제조사","유통기한"};
		
		
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
