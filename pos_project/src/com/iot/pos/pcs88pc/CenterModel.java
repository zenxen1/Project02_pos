package com.iot.pos.pcs88pc;

import javax.swing.table.AbstractTableModel;

public class CenterModel extends AbstractTableModel {
	String[] columnTitle={
			"No",
			"��ǰ�ڵ�",
			"��ǰ��",
			"����",
			"�ܰ�",
			"����"
			
			
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
