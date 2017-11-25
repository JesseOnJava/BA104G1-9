package com.balance.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BalanceDAO implements BalanceDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G1DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT =
		"INSERT INTO BALANCE (TOPUP_NO, MEM_NO,TOPUP_VALUE,TOPUP_WAY) VALUES (to_char('TPP'||to_char(sysdate,'yymm')||LPAD(to_char(SEQ_EMPLOYEE.NEXTVAL),3,'0')),?,?,?)";
	
	private static final String GET_ALL_STMT =  
		"SELECT TOPUP_NO, MEM_NO,TOPUP_VALUE,STATUS,TOPUP_WAY,to_char(TOPUP_TIME,'yyyy-mm-dd-hh:mm:ss') TOPUP_TIME FROM BALANCE ORDER BY TOPUP_NO";
	
	private static final String GET_ONE_STMT = 
		"SELECT TOPUP_NO, MEM_NO,TOPUP_VALUE,STATUS,TOPUP_WAY,to_char(TOPUP_TIME,'yyyy-mm-dd-hh:mm:ss') TOPUP_TIME FROM BALANCE WHERE TOPUP_NO=?";
	
	private static final String DELETE =
		"DELETE FROM BALANCE WHERE TOPUP_NO = ?";		
	
	private static final String UPDATE = 
		"UPDATE BALANCE set TOPUP_VALUE=?, STATUS=? WHERE TOPUP_NO =?";

	@Override
	public void insert(BalanceVO balanceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, balanceVO.getMemNo());
			pstmt.setInt(2, balanceVO.getTopuValue());
			pstmt.setString(3, balanceVO.getTopupWay());
		
			pstmt.executeUpdate();
			
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(BalanceVO balanceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, balanceVO.getTopuValue());
			pstmt.setString(2, balanceVO.getStatus());
			pstmt.setString(3, balanceVO.getTopupNo());
			
			pstmt.executeUpdate();


			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String topupNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, topupNo);

			pstmt.executeUpdate();


		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public BalanceVO findByPrimaryKey(String topupNo) {

		BalanceVO balanceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, topupNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// balanceVO 也稱為 Domain objects
				balanceVO = new BalanceVO();
				balanceVO.setTopupNo(rs.getString("topup_no"));
				balanceVO.setMemNo(rs.getString("mem_no"));
				balanceVO.setTopupValue(rs.getInt("topup_value"));
				balanceVO.setStatus(rs.getString("status"));
				balanceVO.setTopupWay(rs.getString("topup_way"));
				balanceVO.setTopupTime(rs.getDate("topup_time"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return balanceVO;
	}

	@Override
	public List<BalanceVO> getAll() {
		List<BalanceVO> list = new ArrayList<BalanceVO>();
		BalanceVO balanceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// balanceVO 也稱為 Domain objects
				balanceVO = new BalanceVO();
				balanceVO.setTopupNo(rs.getString("topup_no"));
				balanceVO.setMemNo(rs.getString("mem_no"));
				balanceVO.setTopupValue(rs.getInt("topup_value"));
				balanceVO.setStatus(rs.getString("status"));
				balanceVO.setTopupWay(rs.getString("topup_way"));
				balanceVO.setTopupTime(rs.getDate("topup_time"));
				list.add(balanceVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		BalanceDAO dao = new BalanceDAO();

		// 新增
		BalanceVO balanceVO1 = new BalanceVO();
		
		balanceVO1.setMemNo("MEM0001");
		balanceVO1.setTopupValue(2000);
		balanceVO1.setTopupWay("credit");
		dao.insert(balanceVO1);
System.out.println("新增ＯＫ");

		// 修改
// "UPDATE BALANCE set TOPUP_VALUE=?, STATUS=? WHERE TOPUP_NO =?";

		BalanceVO balanceVO2 = new BalanceVO();
		balanceVO2.setTopupNo("TPP1710022");
		balanceVO2.setTopupValue(7002);
		balanceVO2.setStatus("ok");
		dao.update(balanceVO2);
System.out.println("修改ＯＫ");

		// 刪除

		dao.delete("TPP1710028");
System.out.println("刪除ＯＫ");


		// 查詢

//"SELECT TOPUP_NO, MEM_NO,TOPUP_VALUE,STATUS,TOPUP_WAY,to_char(TOPUP_TIME,'yyyy-mm-dd-hh:mm:ss') TOPUP_TIME FROM BALANCE WHERE TOPUP_NO=?";
		BalanceVO balanceVO3 = dao.findByPrimaryKey("TPP1710029");
		System.out.print(balanceVO3.getTopupNo() + ",");
		System.out.print(balanceVO3.getMemNo() + ",");
		System.out.print(balanceVO3.getTopuValue() + ",");
		System.out.print(balanceVO3.getStatus() + ",");
		System.out.print(balanceVO3.getTopupWay() + ",");
		System.out.print(balanceVO3.getTopupTime()+ ",");
		System.out.print(balanceVO3.getTopupNo() );
		
		System.out.println("---------------------");

//				"SELECT TOPUP_NO, MEM_NO,TOPUP_VALUE,STATUS,TOPUP_WAY,to_char(TOPUP_TIME,'yyyy-mm-dd-hh:mm:ss') TOPUP_TIME FROM BALANCE ORDER BY TOPUP_NO";
		// 查詢
		List<BalanceVO> list = dao.getAll();
		for (BalanceVO aEmp : list) {
			System.out.print(aEmp.getTopupNo() + ",");
			System.out.print(aEmp.getMemNo() + ",");
			System.out.print(aEmp.getTopuValue() + ",");
			System.out.print(aEmp.getStatus() + ",");
			System.out.print(aEmp.getStatus() +",");
			System.out.print(aEmp.getTopupTime());
			System.out.print(balanceVO3.getTopupNo() + ",");
			
			System.out.println();
		}
	}
}
