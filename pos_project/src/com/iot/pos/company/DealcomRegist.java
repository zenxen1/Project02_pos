package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DealcomRegist extends JPanel implements ActionListener {
	JPanel p_west, p_north;
	JLabel la_title, la_company_name, la_company_phone;
	JTextField tf_company_name, tf_company_phone;
	JTable table;
	JScrollPane scroll;
	JButton bt_regist, bt_delete;
	DealcomRegistModel model;

	/*
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "posman";
	String password = "posman";
	*/

	Connection con = PosMain.getConnection();
	PreparedStatement pstmt;
	ResultSet rs;

	int company_id;

	public DealcomRegist() {
		la_title = new JLabel("거래업체등록");
		la_company_name = new JLabel("거래업체");
		la_company_phone = new JLabel("연락처");

		tf_company_name = new JTextField(20);
		tf_company_phone = new JTextField(20);

		p_north = new JPanel();
		p_west = new JPanel();

		bt_regist = new JButton("등록");
		bt_delete = new JButton("삭제");

		//connect();
		table = new JTable(model = new DealcomRegistModel(con));
		scroll = new JScrollPane(table);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String value = (String) table.getValueAt(row, 0);
				company_id = Integer.parseInt(value);
				System.out.println(row + "," + col + "클릭했나?" + value);
				
				tf_company_name.setText((String)table.getValueAt(row, 1));
				tf_company_phone.setText((String)table.getValueAt(row, 2));
			}
		});
		
		p_north.setPreferredSize(new Dimension(1000, 50));
		p_west.setPreferredSize(new Dimension(300, 960));
		scroll.setPreferredSize(new Dimension(700, 960));
		
		p_west.setBackground(Color.YELLOW);
		p_north.setBackground(Color.BLUE);
		

		p_north.add(la_title);
		p_west.add(la_company_name);
		p_west.add(tf_company_name);
		p_west.add(la_company_phone);
		p_west.add(tf_company_phone);
		p_west.add(bt_regist);
		p_west.add(bt_delete);
		
		add(p_north, BorderLayout.NORTH);
		add(p_west, BorderLayout.WEST);
		add(scroll);

		/*
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// db닫기!!
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				// 프로세스 죽이기!
				System.exit(0);
			}
		});
*/
		bt_regist.addActionListener(this);
		bt_delete.addActionListener(this);

		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setSize(1024, 960);
		//setResizable(false);
		//setVisible(true);

	}
	/*
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		//	this.setTitle("드라이버 로드 성공");
			con = DriverManager.getConnection(url, user, password);
			if (con == null) {
				JOptionPane.showMessageDialog(this, "접속실패");
			}
			//this.setTitle("접속성공");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
*/
	public void registCompany() {
		
		String company_name = tf_company_name.getText();
		String company_phone = tf_company_phone.getText();

		StringBuffer sb = new StringBuffer();
		sb.append("insert into company(company_id, company_name, company_phone)");
		sb.append(" values(seq_company.nextval, ?,?)");

		System.out.println(sb.toString());

		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, company_name);
			pstmt.setString(2, company_phone);
			int result = pstmt.executeUpdate();

			if (result != 0) {
				System.out.println("등록성공");

				model.selectAll();
				table.updateUI();

			} else {
				System.out.println("등록실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bt_regist) {
			registCompany();
		} else if (obj == bt_delete) {
			int result = JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?");
			if (result == JOptionPane.OK_OPTION) {
				if (company_id == 0) {
					JOptionPane.showMessageDialog(this, "회사를 선택하여 주십시오");
					return;
				}
				if (model.deleteCompany(company_id) != 0) {
					JOptionPane.showMessageDialog(this, "삭제성공");
					model.selectAll();
					model.fireTableDataChanged();
					table.updateUI();
				} else {
					JOptionPane.showMessageDialog(this, "삭제실패");
				}
			}
		}
	}
	
	/*
	public static void main(String[] args) {
		new DealcomRegist();
	}
	*/

}
