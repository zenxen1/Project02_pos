package com.iot.pos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;

import com.iot.pos.company.DealcomList;
import com.iot.pos.company.DealcomRegist;
import com.iot.pos.company.Saleconfirm;
import com.iot.pos.pcs88pc.FirstLogin;
import com.iot.pos.pcs88pc.FirstMain;
import com.iot.pos.pcs88pc.SaleDeskTop;
import com.iot.pos.pcs88pc.SaleDeskTop_North;
import com.sds.pos.faceistroll.ManagementWindow;
import com.sds.pos.faceistroll.OftenUsedProduct;
import com.sds.pos.faceistroll.SalesManagement;
import com.sds.pos.koreain14.ProductInformation;
import com.sds.pos.koreain14.StaffManagement;

public class PosMain extends JFrame implements ActionListener{
	String[] menuTitle ={"로그인","메인화면","판매화면","관리화면","상품선택","매출관리1","매출관리2","거래내역","업체등록","상품정보","직원관리"};
	JButton[] menu;
	JPanel p_north; 
	JPanel[] content;
	JPanel center;
	
	//데이터베이스 접속 관련...
	String driver = "oracle.jdbc.driver.OracleDriver";
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "posman";
	String password= "posman";
	
	private static Connection con;
	
	FirstLogin firstLogin;
	FirstMain firstMain;
	SaleDeskTop saledeskTop;
	ManagementWindow managementWindow;
	OftenUsedProduct oftenUsedProduct;
	SalesManagement salesManagement;
	DealcomList dealcomList;
	Saleconfirm saleconfirm;
	DealcomRegist dealcomRegist;
	ProductInformation productInformation;
	StaffManagement staffManagement;
	
	public PosMain() {
		connectDB();
		menu = new JButton[menuTitle.length];
		content = new JPanel[menu.length];
		
		p_north = new JPanel();
		p_north.setLayout(new GridLayout(1, menu.length));
		
		firstLogin = new FirstLogin(this);
		firstMain = new FirstMain();
		saledeskTop = new SaleDeskTop(firstLogin);
		managementWindow = new ManagementWindow(this);
		oftenUsedProduct = new OftenUsedProduct();
		salesManagement = new SalesManagement();
		dealcomList = new DealcomList();
		saleconfirm = new Saleconfirm();
		dealcomRegist = new DealcomRegist();
		productInformation = new ProductInformation(this);
		staffManagement = new StaffManagement(this);
		
		for(int i=0;i<menu.length;i++){
			menu[i] = new JButton(menuTitle[i]);
			p_north.add(menu[i]);
			menu[i].addActionListener(this);
		}
		
		//업체등록으로 가기
		dealcomList.bt_regist.addActionListener(this);
		
		//first main 화면 버튼 판매 / 관리
		firstMain.bt.addActionListener(this);
		firstMain.bt1.addActionListener(this);
		
		//메인으로 가기 버튼 부착
		saleconfirm.bt_main.addActionListener(this);
		dealcomList.bt_main.addActionListener(this);
		dealcomRegist.bt_main.addActionListener(this);
		saledeskTop.bt_main.addActionListener(this);
		managementWindow.bt_main.addActionListener(this);
		oftenUsedProduct.bt_main.addActionListener(this);
		salesManagement.bt_main.addActionListener(this);
		productInformation.bt_main.addActionListener(this);
		staffManagement.bt_main.addActionListener(this);
		
		managementWindow.bt_menu[0].addActionListener(this);
		managementWindow.bt_menu[1].addActionListener(this);
		managementWindow.bt_menu[2].addActionListener(this);
		managementWindow.bt_menu[3].addActionListener(this);
		managementWindow.bt_menu[4].addActionListener(this);
		managementWindow.bt_menu[5].addActionListener(this);
		
		//메인 화면 부착
		content[0] = firstLogin; //로그인
		content[1] = firstMain; //메인화면
		content[2] = saledeskTop; // 판매화면
		content[3] = managementWindow; // 관리화면
		content[4] = oftenUsedProduct; // 상품선택
		content[5] = salesManagement; // 매출관리1
		content[6] = saleconfirm; //매출관리2
		content[7] = dealcomList; //거래내역
		content[8] = dealcomRegist;//업체등록
		content[9] = productInformation; //상품등록
		content[10] = staffManagement; //직원등록
		
		
		
		
		center = new JPanel();
		
		add(p_north,BorderLayout.NORTH);
		
		for(int i=0;i<menu.length;i++){
			center.add(content[i]);
			content[i].setPreferredSize(new Dimension(1024, 960));
			content[i].setVisible(false);
		}
		/*
		center.add(content[0]);
		content[0].setPreferredSize(new Dimension(1024, 960));
		content[0].setVisible(false);
		
		center.add(content[1]);
		content[1].setPreferredSize(new Dimension(1024, 960));
		content[1].setVisible(false);
		
		center.add(content[2]);
		content[2].setPreferredSize(new Dimension(1024, 960));
		content[2].setVisible(true);
		*/
		
		add(center);
		
		showContent(0);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				if(con!=null){
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
		
		setSize(1024,960);
		setVisible(true);
		
		
		
	}
	
	public void connectDB(){
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
			if(con != null){
				setTitle("오라클 접속 성공");
			}else{
				setTitle("접속실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void showContent(int current){
		if(firstLogin.loginname != null){
			setTitle(firstLogin.loginname + "접속");
		}
		for(int i=0;i<menu.length;i++){
			if(i==current){
				content[i].setVisible(true);
			}else{
				content[i].setVisible(false);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		//System.out.println(obj);
		for(int i=0;i<menu.length;i++){
			if(obj==menu[i]){
				showContent(i);
			}
		}
		
		//판매화면 가기
		if(obj.equals(firstMain.bt)){
			showContent(2);
		}
		//관리 화면 가기
		if(obj.equals(firstMain.bt1)){
			showContent(3);
		}
		
		//메인화면 보여주기
		if(obj.equals(saleconfirm.bt_main)||obj.equals(dealcomList.bt_main)||obj.equals(dealcomRegist.bt_main)||obj.equals(saledeskTop.bt_main)
				||obj.equals(managementWindow.bt_main)||obj.equals(oftenUsedProduct.bt_main)||obj.equals(salesManagement.bt_main)
				||obj.equals(productInformation.bt_main)||obj.equals(staffManagement.bt_main)){
			showContent(1);//메인화면 보여주기
		}
		
		//"로그인(0)","메인화면(1)","판매화면(2)","관리화면(3)","상품선택(4)","매출관리1(5)",
		//"매출관리2(6)","거래내역(7)","업체등록(8)","상품정보(9)","직원관리(10)"
		
		//거래업체가기
		if(obj.equals(dealcomList.bt_regist)){
			//System.out.println("눌리냐");
			showContent(7);
		}
		//관리화면 버튼 구성
		if(obj == managementWindow.bt_menu[0]){
			System.out.println("매출관리1");
			showContent(5);
		}else if(obj == managementWindow.bt_menu[1]){
			System.out.println("매출관리2");
			showContent(6);
		}else if(obj == managementWindow.bt_menu[2]){
			System.out.println("거래내역");
			showContent(7);
		}else if(obj == managementWindow.bt_menu[3]){
			System.out.println("업체등록");
			showContent(8);
		}else if(obj == managementWindow.bt_menu[4]){
			System.out.println("상품정보");
			showContent(9);
		}else if(obj == managementWindow.bt_menu[5]){
			System.out.println("직원관리");
			showContent(10);
		}
	}
	
	public static Connection getConnection(){
		return con;
	}
	
	
	public static void main(String[] args) {
		new PosMain();
	}
	

}
