package com.android.hcOrderMaster.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.android.hcOrderDetail.model.HcOrderDetailVO;

public class HcOrderMasterDAO_JDBC implements HcOrderMasterDAO_interface{
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL ="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "BA104G1DB";
	private static final String PWD = "123456";
	
	private static final String INSERT_STMT="INSERT INTO HC_ORDER_MASTER (ORDER_NO,MEM_NO,CARED_NO,ORDER_DATE,ORDER_STATUS) "
			+ "VALUES(TO_CHAR(SYSDATE,'yyyymmdd')||'-'||LPAD(HC_ORDER_NO_SEQ.NEXTVAL,6,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT="SELECT * FROM HC_ORDER_MASTER ";
	private static final String GET_ONE_STMT="SELECT * FROM HC_ORDER_MASTER WHERE ORDER_NO = ?";
	private static final String DELETE = "DELETE FROM HC_ORDER_MASTER WHERE ORDER_NO=?";
	private static final String UPDATE = "UPDATE HC_ORDER_MASTER SET MEM_NO=?,CARED_NO=?,ORDER_DATE=?,ORDER_STATUS=? WHERE ORDER_NO=?";

	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		HcOrderMasterDAO_JDBC HcOrderMasterDAO_JDBC = new HcOrderMasterDAO_JDBC();
		HcOrderMasterVO hcOrderMasterVO = new HcOrderMasterVO();
		
//		hcOrderMasterVO.setMemNo("MEM0002");
//		hcOrderMasterVO.setCaredNo("CRD0001");
//		hcOrderMasterVO.setOrderDate(new Timestamp(System.currentTimeMillis()));
//		hcOrderMasterVO.setOrderStatus("������");
//		hcOrderMasterVO.setOrderNo("20171028-500006");
//		/*���շs�W���ӭq��*/
//		HcOrderMasterDAO_JDBC.insert(hcOrderMasterVO);
		/*���խק���ӭq��*/
//		HcOrderMasterDAO_JDBC.update(hcOrderMasterVO);
		/*���էR�����ӭq��*/
//		HcOrderMasterDAO_JDBC.delete("20171028-500006");
		/*���լd�߳浧���ӭq��*/
//		HcOrderMasterDAO_JDBC = hcOrderMasterDAO.findByPrimaryKey("20171028-500007");
//		System.out.println(hcOrderMasterVO);
		/*���լd�ߥ������ӭq��*/
//		for(HcOrderMasterVO hcOrderMasterVO1 : HcOrderMasterDAO_JDBC.getAll()){
//			System.out.println(hcOrderMasterVO1);
//			System.out.println();
//		}
		
		
	}
	
	
	@Override
	public void insert(HcOrderMasterVO hcOrderMasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, hcOrderMasterVO.getMemNo());
			pstmt.setString(2,hcOrderMasterVO.getCaredNo());
			pstmt.setTimestamp(3, hcOrderMasterVO.getOrderDate());
			pstmt.setString(4, hcOrderMasterVO.getOrderStatus());
			
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
	public void update(HcOrderMasterVO hcOrderMasterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, hcOrderMasterVO.getMemNo());
			pstmt.setString(2,hcOrderMasterVO.getCaredNo());
			pstmt.setTimestamp(3, hcOrderMasterVO.getOrderDate());
			pstmt.setString(4, hcOrderMasterVO.getOrderStatus());
			pstmt.setString(5, hcOrderMasterVO.getOrderNo());
			
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
	public void delete(String orderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,orderNo);
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
	public HcOrderMasterVO findByPrimaryKey(String orderNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HcOrderMasterVO hcOrderMasterVO = new HcOrderMasterVO();
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, orderNo);
			rs  = pstmt.executeQuery();
			
			if(rs.next()){
				hcOrderMasterVO.setOrderNo(rs.getString("ORDER_NO"));
				hcOrderMasterVO.setMemNo(rs.getString("MEM_NO"));
				hcOrderMasterVO.setCaredNo(rs.getString("CARED_NO"));
				hcOrderMasterVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				hcOrderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
			}
			
			return hcOrderMasterVO;
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
		return hcOrderMasterVO;
	}

	@Override
	public List<HcOrderMasterVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HcOrderMasterVO> hcOrderList = new ArrayList();
		
		try {
			con = DriverManager.getConnection(URL,USER,PWD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				HcOrderMasterVO hcOrderMasterVO = new HcOrderMasterVO();
				hcOrderMasterVO.setOrderNo(rs.getString("ORDER_NO"));
				hcOrderMasterVO.setMemNo(rs.getString("MEM_NO"));
				hcOrderMasterVO.setCaredNo(rs.getString("CARED_NO"));
				hcOrderMasterVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				hcOrderMasterVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				hcOrderList.add(hcOrderMasterVO);
			}
			return hcOrderList;
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
		return hcOrderList;
	}


	@Override
	public void transaction(HcOrderMasterVO hcOrderMasterVO, List<HcOrderDetailVO> detailList) {
		Connection con  = null;
		PreparedStatement pstmt = null;
		
	}

}
