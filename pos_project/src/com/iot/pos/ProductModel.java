package com.iot.pos;

import javax.swing.table.AbstractTableModel;

public class ProductModel extends AbstractTableModel {
	String[] columnTitle={"��ǰ�ڵ�","ǰ��","�ܰ�","�ǸŰ�","������","�������"};
	
	public ProductModel(){
		
	}
	
	public String getColumnName(int col) {
		col = 6;
		return columnTitle[col];
	}

	public int getColumnCount() {
		return 6;
	}

	public int getRowCount() {
		return 8;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		return null;
	}
}
