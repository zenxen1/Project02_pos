package com.iot.pos.company;

import javax.swing.table.AbstractTableModel;

public class DealcomListModel extends AbstractTableModel{
	
	String[] columTitle ={"NO","�ŷ���ü","�ŷ�����","�����ұݾ�","���ұݾ�","������"};
	
	public String getColumnName(int col) {
		return columTitle[col];
	}
	
	public int getColumnCount() {
		return columTitle.length;
	}

	public int getRowCount() {
		return 4;
	}

	public Object getValueAt(int row, int col) {
		return null;
	}

}
