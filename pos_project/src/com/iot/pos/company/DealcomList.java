package com.iot.pos.company;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DealcomList extends JPanel implements ActionListener {
	JPanel p_north, p_center, p_input, p_detailinput, p_south;
	Choice ch;
	JTextField tf_serch;
	JButton bt_serch, bt_regist;
	JTable table, table_detailinput;
	JScrollPane scroll,scroll_detailinput;
	DealcomListModel model;
	DealcomListdetailModel model1;

	// 입력구성
	Choice ch_product, ch_company;
	JTextField tf_dealid,tf_dealdetailcount, tf_dealmoney, tf_paidmoney, tf_dealdate;
	JLabel la_dealdetailcount, la_dealmoney, la_paidmoney, la_dealdate, la_dealid;
	JButton bt_productregist, bt_companyregist;

	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "posman";
	String password = "posman";

	Connection con;

	public DealcomList() {
		p_north = new JPanel();
		p_center = new JPanel();
		p_south = new JPanel();
		p_input = new JPanel();
		p_detailinput = new JPanel();
		ch = new Choice();
		tf_serch = new JTextField(20);
		bt_serch = new JButton("검색");
		bt_regist = new JButton("거래업체등록");

		// 입력구성
		ch_product = new Choice();
		ch_product.add("▼거래업체선택");
		ch_company = new Choice();
		ch_company.add("▼상품선택");
		tf_dealid = new JTextField(5);
		tf_dealdetailcount = new JTextField("0", 10);
		tf_dealmoney = new JTextField("0", 10);
		tf_paidmoney = new JTextField("0", 10);
		tf_dealdate = new JTextField("0", 10);
		la_dealdetailcount = new JLabel("수량");
		la_dealmoney = new JLabel("매입금");
		la_paidmoney = new JLabel("지불금");
		la_dealdate = new JLabel("거래일자");
		la_dealid = new JLabel("dealid");
		bt_companyregist = new JButton("거래등록");
		bt_productregist = new JButton("상품등록");

		// connect();
		table = new JTable(model = new DealcomListModel());
		scroll = new JScrollPane(table);
		table_detailinput = new JTable(model1 = new DealcomListdetailModel());
		scroll_detailinput = new JScrollPane(table_detailinput);

		// p_center.setLayout(new FlowLayout());
		p_detailinput.setPreferredSize(new Dimension(850, 250));
		scroll_detailinput.setPreferredSize(new Dimension(830, 200));
		p_input.setPreferredSize(new Dimension(150, 250));
		p_south.setPreferredSize(new Dimension(1200, 350));
		scroll.setPreferredSize(new Dimension(1000, 550));
		p_north.setPreferredSize(new Dimension(1000, 50));
		p_center.setPreferredSize(new Dimension(1000, 900));
		
		p_detailinput.setBackground(Color.BLUE);
		p_input.setBackground(Color.YELLOW);

		ch.add("분류");

		// 입력연결
		p_detailinput.add(scroll_detailinput);
		p_detailinput.add(la_dealid);
		p_detailinput.add(tf_dealid);
		p_detailinput.add(ch_company);
		p_detailinput.add(la_dealdetailcount);
		p_detailinput.add(tf_dealdetailcount);
		p_detailinput.add(bt_productregist);
		
		p_input.add(ch_product);
		p_input.add(la_dealmoney);
		p_input.add(tf_dealmoney);
		p_input.add(la_paidmoney);
		p_input.add(tf_paidmoney);
		p_input.add(la_dealdate);
		p_input.add(tf_dealdate);
		p_input.add(bt_companyregist);

		p_north.add(ch);
		p_north.add(tf_serch);
		p_north.add(bt_serch);
		p_north.add(bt_regist);
		
		p_south.add(p_detailinput);
		p_south.add(p_input);

		p_center.add(scroll);
		p_center.add(p_south);

		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		bt_companyregist.addActionListener(this);

		/*
		 * addWindowListener(new WindowAdapter() { public void
		 * windowClosing(WindowEvent e) { // db닫기!! if (con != null) { try {
		 * con.close(); } catch (SQLException e1) { e1.printStackTrace(); } } //
		 * 프로세스 죽이기! System.exit(0); } });
		 */

		// setSize(1024, 960);
		// setResizable(false);
		// setVisible(true);
		getCompany();
	}
	
	public void getCompany(){
		System.out.println("실행되냐?");
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from company";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ch_product.add(rs.getString("company_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public void comProductRegist(){
		
	}
	
	public void companyRegist(){
		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(bt_companyregist)){
			companyRegist();
		}
		if(obj.equals(bt_productregist)){
			comProductRegist();
		}
	}

	/*
	 * public void connect(){ try {
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); //setTitle("드라이버로드성공");
	 * con = DriverManager.getConnection(url, user, password); if(con==null){
	 * JOptionPane.showMessageDialog(this, "접속실패!!"); return; }
	 * //setTitle("접속성공"); } catch (SQLException e) { e.printStackTrace(); }
	 * catch (ClassNotFoundException e) { e.printStackTrace(); } }
	 */
	/*
	 * public static void main(String[] args) { new DealcomList(); }
	 */

}
