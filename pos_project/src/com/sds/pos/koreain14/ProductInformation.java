package com.sds.pos.koreain14;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.iot.pos.PosMain;


public class ProductInformation extends JPanel implements ActionListener, ItemListener{
	PosMain posMain;
	
	JPanel p_all;
	Choice ch_top, ch_sub;
	JTextField txt_search;
	JButton bt_search, bt_regist; 
	public JButton bt_main;
	JPanel p_north, p_center, p_west, p_east, p_south; // p_all 안의 패널
	HashMap<String, Integer> topMap = new HashMap<String, Integer>();
	HashMap<String, Integer> subMap = new HashMap<String, Integer>();

	ProductTableModel info;
	JTable t_info;
	JScrollPane scroll;
	
	JLabel la_img;
	JLabel la_empty;
	ImageIcon icon;
	
	JFileChooser chooser;
	
	public ProductInformation(PosMain posMain) {
		this.posMain = posMain;
		
		p_all = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		
		txt_search = new JTextField("상품명을 입력하세요",25);
		txt_search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setTextBox();
			}
		});
		
		bt_search = new JButton("검색");
		bt_search.setBackground(new Color(183, 197, 200));
		bt_search.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		bt_search.setForeground(Color.white);

		bt_regist = new JButton("엑셀파일불러오기");
		bt_regist.setBackground(new Color(183, 197, 200));
		bt_regist.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		bt_regist.setForeground(Color.white);
		
		bt_main = new JButton("메인으로");
		bt_main.setBackground(new Color(183, 197, 200));
		bt_main.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		bt_main.setForeground(Color.white);
		
		//#남쪽에 붙일 라벨#
		la_empty = new JLabel("");
		la_empty.setPreferredSize(new Dimension(50, 680));
		
		// #이미지버튼 넣기#
		icon=new ImageIcon("C:/project_rev/pos_project/res/home2.png");
		Image homeImage=icon.getImage();
		Image newHomeImg=homeImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newHomeImg);
		
		la_img = new JLabel(icon);
		
		//#홈버튼 연결#
		la_img.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				posMain.showContent(1);
			}
		});
		
		// 버튼에 리스너 연결
		bt_search.addActionListener(this);
		bt_regist.addActionListener(this);	
	
		info = new ProductTableModel(this);
		t_info = new JTable(info);
		scroll = new JScrollPane(t_info);
		p_north = new JPanel();
		p_north.setBackground(new Color(123,182,212));
		
		p_east = new JPanel();
		p_east.setPreferredSize(new Dimension(100, 460));
		p_east.setBackground(new Color(123,182,212) );
		
		
		//#남쪽에 홈 버튼 붙이기#
		p_south = new JPanel();
		p_south.setBackground(new Color(123,182,212));
		p_south.add(la_empty);
		p_south.add(la_img);
		
		p_west = new JPanel();
		p_west.setPreferredSize(new Dimension(100, 460));
		p_center = new JPanel();

		ch_top.add("상위분류");	
		ch_sub.add("하위분류");
		
		p_all.setLayout(new BorderLayout());
		
		txt_search.setPreferredSize(new Dimension(400, 29));
		scroll.setPreferredSize(new Dimension(800, 400));
		
		p_north.add(ch_top);
		p_north.add(ch_sub);
		p_north.add(txt_search);
		p_north.add(bt_search);
		p_north.add(bt_regist);
		p_north.add(bt_main);
		//p_north.add(la_empty);
		//p_north.add(la_img);
		
		p_all.add(p_north, BorderLayout.NORTH);
		p_all.add(scroll, BorderLayout.CENTER);
		
		add(p_all);
		add(p_east, BorderLayout.EAST);
		add(p_south, BorderLayout.SOUTH);
		
		ch_top.addItemListener(this);
		ch_sub.addItemListener(this);
		
		getTopCategory();
	}
	
	//탑 카테고리 가져오기
	public void getTopCategory(){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from topcategory";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ch_top.add(rs.getString("topgroup"));
				topMap.put(rs.getString("topgroup"), rs.getInt("topcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if(rs!=null){
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(pstmt!=null){
						try {
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	//서브카테고리 가져오기
	public void getSubCategory(int subcategory_id){
		Connection con = PosMain.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select * from subcategory where topcategory_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			rs = pstmt.executeQuery();
			ch_sub.removeAll();
			subMap.clear();
			ch_sub.add("하위 카테고리");
			
			while(rs.next()){
				ch_sub.add(rs.getString("subgroup"));
				subMap.put(rs.getString("subgroup"), rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bt_search){
			info.searchProduct();
			t_info.updateUI();
		}else if(obj == bt_regist){
			openFile();
			t_info.removeAll();
			info.showProduct();
			t_info.updateUI();
		}
	}
	
	public void setTextBox(){
		txt_search.setText("");
	}
	
	// 파일선택하기
	
	public void openFile(){
		chooser = new JFileChooser("c:/");
		int result=chooser.showOpenDialog(this);
		
		
		if(result==JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet sheet=workbook.getSheet("product");
				
				Connection con=PosMain.getConnection();
				PreparedStatement pstmt=null;
				
				int total = sheet.getPhysicalNumberOfRows();
				
				System.out.println(total);
				
				for(int i=1;i<total;i++){
					XSSFRow row = sheet.getRow(i);
					
					/*
					int subcategory_id=(int)row.getCell(0).getNumericCellValue();
					String name=row.getCell(1).getStringCellValue();
					String barcode=row.getCell(2).getRawValue();
					int prime_cost=(int)row.getCell(3).getNumericCellValue();
					int selling_price=(int)row.getCell(4).getNumericCellValue();
					String manufacturer=row.getCell(5).getStringCellValue();
					String expiration_date=row.getCell(6).getStringCellValue();
						
					
					String sql = "insert into product(product_id, subcategory_id, name, barcode, prime_cost, selling_price, manufacturer, expiration_date)";
					sql = sql +" values(seq_product.nextval, "+subcategory_id+", '"+name+"', '"+barcode+"', "+prime_cost+", "+selling_price+", '"+manufacturer+"', '"+expiration_date+"')";
					System.out.println(sql);
					*/
					
					String sql = "insert into product(product_id, subcategory_id, name, barcode, prime_cost, selling_price, manufacturer, expiration_date)";
					sql = sql +" values(seq_product.nextval, ?, ?, ?, ?, ?, ?, ?)";
					
					try {
						pstmt=con.prepareStatement(sql);
						
						for(int j=0; j<row.getPhysicalNumberOfCells();j++){
							
							XSSFCell cell=row.getCell(j);
							
							if(cell.getCellType()==XSSFCell.CELL_TYPE_STRING){
								pstmt.setString(j+1, cell.getStringCellValue());
							} else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
								pstmt.setInt(j+1, (int)cell.getNumericCellValue());
							}
						}
						pstmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally{
						if(pstmt!=null){
							try {
								pstmt.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	public void itemStateChanged(ItemEvent e) {
		Object obj=e.getSource();
		if(obj==ch_top){
			getSubCategory(topMap.get(ch_top.getSelectedItem()));
		} else if(obj==ch_sub){
			t_info.removeAll();
			info.sortProduct();
			updateUI();
			info.fireTableDataChanged();
		}
	}
}
