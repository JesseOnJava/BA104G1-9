package com.hcworkshifts.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HcWorkShiftsDAO implements HcWorkShiftsDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Project");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO Hc_WorkShifts (MONTH_OF_YEAR,EMP_NO,WORK_SHIFT_STATUS,TOTAL_WORK_SHIFTS) "
			+ "VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = "select * from HC_WORKSHIFTS ORDER BY MONTH_OF_YEAR,EMP_NO";
		private static final String GET_ONE_STMT = 
			"SELECT MONTH_OF_YEAR,EMP_NO,WORK_SHIFT_STATUS,TOTAL_WORK_SHIFTS "
			+ "FROM Hc_WorkShifts where MONTH_OF_YEAR=? and EMP_NO=?";
		private static final String UPDATE = 
			"UPDATE Hc_WorkShifts set WORK_SHIFT_STATUS=?, TOTAL_WORK_SHIFTS=? "
			+ "where MONTH_OF_YEAR=? and EMP_NO=?";
		
		private static final String GET_EMPNO_BY_DATE = "select EMP_NO from HC_WORKSHIFTS where MONTH_OF_YEAR =? and Substr(WORK_SHIFT_STATUS,?,1)='空' ";

	
	@Override
	public void insert(HcWorkShiftsVO hcWorkShifts) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, hcWorkShifts.getMonthOfYear());
			pstmt.setString(2, hcWorkShifts.getEmpNo());
			pstmt.setString(3, hcWorkShifts.getWorkShiftStatus());
			pstmt.setInt(4, hcWorkShifts.getTotalWorkShifts());
			
			int i =pstmt.executeUpdate();
			System.out.println("�s�W"+i+"��");
			
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
	public void update(HcWorkShiftsVO hcWorkShifts) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

	
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
						
			pstmt.setString(1, hcWorkShifts.getWorkShiftStatus());
			pstmt.setInt(2, hcWorkShifts.getTotalWorkShifts());
			pstmt.setString(3, hcWorkShifts.getMonthOfYear());
			pstmt.setString(4, hcWorkShifts.getEmpNo());
			int i = pstmt.executeUpdate();
			System.out.println("�ק�"+i+" �����");

			// Handle any driver errors
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
	public HcWorkShiftsVO findByPrimarykey(String monthOfYear ,String empNo) {
		HcWorkShiftsVO hcWorkShifts = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, monthOfYear);
			pstmt.setString(2, empNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				hcWorkShifts = new HcWorkShiftsVO();
				hcWorkShifts.setMonthOfYear(rs.getString("month_Of_Year"));
				hcWorkShifts.setEmpNo(rs.getString("Emp_No"));
				hcWorkShifts.setTotalWorkShifts(rs.getInt("TOTAL_WORK_SHIFTS"));
				hcWorkShifts.setWorkShiftStatus(rs.getString("WORK_SHIFT_STATUS"));

			}

			// Handle any driver errors
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
		return hcWorkShifts;
		
	}

	@Override
	public List<HcWorkShiftsVO> getAll() {
		List<HcWorkShiftsVO> list = new ArrayList<HcWorkShiftsVO>();
		HcWorkShiftsVO workShifts = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				workShifts = new HcWorkShiftsVO();
				workShifts.setMonthOfYear(rs.getString("MONTH_OF_YEAR"));
				workShifts.setEmpNo(rs.getString("EMP_NO"));
				workShifts.setWorkShiftStatus(rs.getString("WORK_SHIFT_STATUS"));
				workShifts.setTotalWorkShifts(rs.getInt("TOTAL_WORK_SHIFTS"));
				list.add(workShifts); // Store the row in the list
			}

			// Handle any driver errors
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
	
	

	
	
	@Override
	public List<HcWorkShiftsVO> getAllByDateTime (String yearOfMonth, String shiftNumber) {
		List<HcWorkShiftsVO> list = new ArrayList<HcWorkShiftsVO>();
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println(yearOfMonth);
			System.out.println(shiftNumber);

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMPNO_BY_DATE);
			pstmt.setString(1, yearOfMonth);
			pstmt.setString(2, shiftNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Store the row in the list
				HcWorkShiftsVO hcWorkShiftsVO = new HcWorkShiftsVO();
				hcWorkShiftsVO.setEmpNo(rs.getString("EMP_NO")); 
				hcWorkShiftsVO.setMonthOfYear(rs.getString("MONTH_OF_YEAR"));
				hcWorkShiftsVO.setWorkShiftStatus(rs.getString("WORK_SHIFT_STATUS"));
				hcWorkShiftsVO.setTotalWorkShifts(rs.getInt("TOTAL_WORK_SHIFTS"));
				list.add(hcWorkShiftsVO); 
			}

			// Handle any driver errors
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
}
