package com.iot.pos;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

public class RegistModel extends AbstractTableModel {
	String[] columTitle = {"no","�ŷ���ü","����ó"};
	int col =3;
	
	public RegistModel() {
	}
	
	public String getColumnName(int col) {
		
		return columTitle[col];
	}
	
	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return 3;
	}

	public Object getValueAt(int arg0, int arg1) {
		return null;
	}

	
}
