package com.iot.pos.pcs88pc;

import javax.swing.table.AbstractTableModel;

public class CenterModel extends AbstractTableModel {
	String[] columnTitle={
			"No",
			"상품코드",
			"상품명",
			"수량",
			"단가",
			"할인"
			
			
	};

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
		return 50;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
		return null;
	}
	

}
