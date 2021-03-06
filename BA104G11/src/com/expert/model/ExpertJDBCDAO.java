package com.expert.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class ExpertJDBCDAO implements ExpertDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String userid = "BA104G1DB";
	String passwd = "BA104G1DB";
	
//  ************** AWS Ollie**************
//	String url = "jdbc:oracle:thin:@13.229.86.22:1521:XE";
//  ************** AWS Aerin**************
//	String url = "jdbc:oracle:thin:@13.124.90.221:1521:XE";	
//  ************** BA104 **************
//	String url = "jdbc:oracle:thin:@10.120.25.6:1521:XE";
//	************* Localhost **********
	String url = "jdbc:oracle:thin:@localhost:1521:XE";


	//---------------前端------------------
	//設定：可增
	private static final String INSERT_STMT =
		"INSERT INTO EXPERT ( EXP_OWN, EMP_NO, EXP_NO) "
		+ "VALUES (to_char('EXP'||LPAD(to_char(SEQ_EXPERT.NEXTVAL),4,'0')),?, ?)";
	
	//設定：可刪
	private static final String DELETE =
		"DELETE FROM EXPERT WHERE EXP_OWN = ?";
	
	//設定：可改
	private static final String UPDATE = 
		"UPDATE EXPERT SET EMP_NO=?, EXP_NO=? WHERE EXP_OWN =?";
	 
	//設定：可查
	private static final String GET_ONE_STMT = 			
		" SELECT EXP_OWN, EMP_NO,EXP_NO FROM EXPERT WHERE EXP_OWN =? ";
	
	private static final String GET_ALL_BY_EMP_NO = 			
			" SELECT * FROM EXPERT WHERE EMP_NO =? ";
	
	//---------------後端-------------------
	//設定：不增
	//設定：不刪 
	//設定：不改 
	//設定：可查
	private static final String GET_ALL_STMT =  
		" SELECT EXP_OWN, EMP_NO, EXP_NO FROM EXPERT ";

	@Override
	public void insert(ExpertVO expertVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
					
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
		
			pstmt.setString(1, expertVO.getEmpNo());
			pstmt.setString(2, expertVO.getExpNo());
		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(ExpertVO expertVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
				
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, expertVO.getEmpNo());
			pstmt.setString(2, expertVO.getExpNo());
			pstmt.setString(3, expertVO.getExpOwn());
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(String expNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, expNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public ExpertVO findByPrimaryKey(String expOwn) {

		ExpertVO expertVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, expOwn);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expertVO = new ExpertVO();
				expertVO.setExpOwn(rs.getString("exp_own"));
				expertVO.setEmpNo(rs.getString("emp_no"));
				expertVO.setExpNo(rs.getString("exp_no"));
							
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return expertVO;
	}

	@Override
	public List<ExpertVO> getAll() {
		List<ExpertVO> list = new ArrayList<ExpertVO>();
		ExpertVO expertVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				expertVO = new ExpertVO();
				expertVO.setExpOwn(rs.getString("exp_own"));
				expertVO.setEmpNo(rs.getString("emp_no"));
				expertVO.setExpNo(rs.getString("exp_no"));
				
				list.add(expertVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

		ExpertJDBCDAO dao = new ExpertJDBCDAO();

//		// 新增
//		ExpertVO expertVO1 = new ExpertVO();
//		expertVO1.setExpOwn("EXP1001");
//		expertVO1.setEmpNo("EMP0001");
//	    expertVO1.setExpNo("101");
//		dao.insert(expertVO1);
//System.out.println("新增ＯＫ");
//
//		// 修改
//
//		ExpertVO expertVO2 = new ExpertVO();
//		expertVO2.setEmpNo("EMP0001");
//		expertVO2.setExpNo("102");
//		expertVO2.setExpOwn("EXP1001");
//		dao.update(expertVO2);
//		
//System.out.println("修改ＯＫ");


//		// 刪除
//
//		dao.delete("EMP0001");
//System.out.println("刪除ＯＫ");


		// 查詢
		ExpertVO expertVO3 = dao.findByPrimaryKey("EXP0002");
		System.out.print(expertVO3.getExpOwn() + ",");
		System.out.print(expertVO3.getEmpNo() + ",");	
		System.out.print(expertVO3.getExpNo() );	
		
		System.out.println("---------------------");

		
		// 查詢
		List<ExpertVO> list = dao.getAll();
		for (ExpertVO aEmp : list) {
			System.out.print(aEmp.getExpOwn() + ",");
			System.out.print(aEmp.getEmpNo() + ",");
			System.out.print(aEmp.getExpNo() );
			System.out.println();
		}
	}

	@Override
	public List<ExpertVO> getAllByEMPNO(String empNo) {
		List<ExpertVO> list = new ArrayList<ExpertVO>();
		ExpertVO expertVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_EMP_NO);
			pstmt.setString(1, empNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				expertVO = new ExpertVO();
				expertVO.setExpOwn(rs.getString("exp_own"));
				expertVO.setEmpNo(rs.getString("emp_no"));
				expertVO.setExpNo(rs.getString("exp_no"));
				
				list.add(expertVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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
}
	
	
	
	
