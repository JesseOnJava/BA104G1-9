package com.android.hcOrderDetail.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HcOrderDetailDAO implements HcOrderDetailDAO_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "BA104G1DB";
	private static final String PWD ="123456";
	
	private static final String INSERT_STMT="INSERT INTO HC_ORDER_DETAIL(ORDER_DETAIL_NO,ORDER_NO,SERVICE_DATE,SERVICE_TIME,EMP_NO,ORDER_DEDIAL_STATUS)"
			+ "VALUES(TO_CHAR(SYSDATE,'yyyymmdd')||'-'||LPAD(HC_ORDER_DETAIL_NO_SEQ.NEXTVAL,6,'0'),?,?,?,?,?)";
	
	private static final String GET_ALL_STMT = "SELECT * FROM HC_ORDER_DETAIL";
	private static final String GET_ONE_STMT = "SELECT * FROM HC_ORDER_DETAIL WHERE ORDER_DETAIL_NO=?";
	private static final String DELETE = "DELETE FROM HC_ORDER_DETAIL WHERE ORDER_DETAIL_NO=?";
	private static final String UPDATE = "UPDATE HC_ORDER_DETAIL SET ORDER_NO = ?,SERVICE_DATE=?,SERVICE_TIME = ?,EMP_NO=?,ORDER_DEDIAL_STATUS=? WHERE ORDER_DETAIL_NO = ?";
	
	
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		HcOrderDetailDAO hcOrderDetailDAO = new HcOrderDetailDAO();
		HcOrderDetailVO hcOrderDetail =  new HcOrderDetailVO();
		
//		hcOrderDetail.setOrderNo("20171028-500005");
//		hcOrderDetail.setServiceDate(new Date(System.currentTimeMillis()));
//		hcOrderDetail.setServiceTime("����");
//		hcOrderDetail.setEmpNo("EMP0005");
//		hcOrderDetail.setOrderDetailStatus("������");
//		hcOrderDetail.setOrderDetailNo("20171028-600007");
//		/*���շs�W�@�����ӭq�����*/
//		hcOrderDetailDAO.insert(hcOrderDetail);
//		/*���խק�@�����ӭq�����*/
//		hcOrderDetailDAO.update(hcOrderDetail);
//		/*���լd�ߤ@�����ӭq�����*/
//		hcOrderDetail = hcOrderDetailDAO.findByPrimaryKey("20171028-600007");
//		System.out.println(hcOrderDetail);
//		/*���լd�ߥ������ӭq�����*/
//		for(HcOrderDetail hcOrderDetail1 : hcOrderDetailDAO.getAll()){
//			System.out.println(hcOrderDetail1);
//		}
		
	}
	

	@Override
	public void insert(HcOrderDetailVO hcOrderDetail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, hcOrderDetail.getOrderNo());
			pstmt.setDate(2,hcOrderDetail.getServiceDate());
			pstmt.setString(3,hcOrderDetail.getServiceTime());
			pstmt.setString(4,hcOrderDetail.getEmpNo());
			pstmt.setString(5,hcOrderDetail.getOrderDetailStatus());
			
			int i = pstmt.executeUpdate();
			System.out.println("");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void update(HcOrderDetailVO hcOrderDetail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, hcOrderDetail.getOrderNo());
			pstmt.setDate(2, hcOrderDetail.getServiceDate());
			pstmt.setString(3,hcOrderDetail.getServiceTime());
			pstmt.setString(4,hcOrderDetail.getEmpNo());
			pstmt.setString(5, hcOrderDetail.getOrderDetailStatus());
			pstmt.setString(6, hcOrderDetail.getOrderDetailNo());
			
			int i = pstmt.executeUpdate();
			System.out.println("");
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				};
			}
		}
	}

	@Override
	public void delete(String orderDetailNo) {
		Connection con =  null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, orderDetailNo);
			int i  = pstmt.executeUpdate();
			System.out.println(""+i+"�����ӭq�����");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public HcOrderDetailVO findByPrimaryKey(String orderDetailNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HcOrderDetailVO HcOrderDetailVO = new HcOrderDetailVO();
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, orderDetailNo);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				HcOrderDetailVO.setOrderDetailNo(rs.getString("ORDER_DETAIL_NO"));
				HcOrderDetailVO.setOrderNo(rs.getString("ORDER_NO"));
				HcOrderDetailVO.setServiceDate(rs.getDate("SERVICE_DATE"));
				HcOrderDetailVO.setServiceTime(rs.getString("SERVICE_TIME"));
				HcOrderDetailVO.setEmpNo(rs.getString("EMP_NO"));
				HcOrderDetailVO.setOrderDetailStatus(rs.getString("ORDER_DEDIAL_STATUS"));
			}
			return HcOrderDetailVO;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return HcOrderDetailVO;
	}

	@Override
	public List<HcOrderDetailVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HcOrderDetailVO> hcOrderDetailList = new ArrayList();
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HcOrderDetailVO HcOrderDetailVO = new HcOrderDetailVO();
				HcOrderDetailVO.setOrderDetailNo(rs.getString("ORDER_DETAIL_NO"));
				HcOrderDetailVO.setOrderNo(rs.getString("ORDER_NO"));
				HcOrderDetailVO.setServiceDate(rs.getDate("SERVICE_DATE"));
				HcOrderDetailVO.setServiceTime(rs.getString("SERVICE_TIME"));
				HcOrderDetailVO.setEmpNo(rs.getString("EMP_NO"));
				HcOrderDetailVO.setOrderDetailStatus(rs.getString("ORDER_DEDIAL_STATUS"));
				hcOrderDetailList.add(HcOrderDetailVO);
			}
			return hcOrderDetailList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return hcOrderDetailList;
	}

}
