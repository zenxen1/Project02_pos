package com.sds.pos.faceistroll;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class OftenUsedTableModel extends AbstractTableModel{
	String[] title = {"상품코드","품명","단가","판매가","제조사","유통기한"};
	ArrayList<String[]> data = new ArrayList<String[]>(); 
	
	public String getColumnName(int col) {
		return title[col];
	}
	
	public int getColumnCount() {
		return title.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}

}
