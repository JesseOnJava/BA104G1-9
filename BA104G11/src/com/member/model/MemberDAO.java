package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class MemberDAO implements MemberDAO_interface{
	
	private static DataSource ds = null;
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER(MEM_NO,MEM_NAME,MEM_PHONE,MEM_GENDER,MEM_EMAIL,ADDRESS,POINT,MEM_ID,MEM_PWD,MEM_SRATUS,CHK_IP,MEM_LOGINTIME)"
			+ "VALUES(to_char('MEM'||LPAD(to_char(SEQ_MEMBER.NEXTVAL),4,'0')),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEMBER";
	private static final String GET_ONE_STMT = "SELECT * FROM MEMBER WHERE MEM_NO=?";
	
	private static final String GET_ONE_BYMEMID = "SELECT * FROM MEMBER WHERE MEM_ID=?";
	private static final String DELETE = "DELETE FROM MEMBER WHERE MEM_NO = ?";
	private static final String UPDATE = "UPDATE MEMBER SET MEM_NAME=? ,MEM_PHONE=?,MEM_GENDER=?,MEM_EMAIL=?,ADDRESS=?,POINT=?,MEM_ID=?,MEM_PWD=?,MEM_SRATUS=?,CHK_IP=?,MEM_LOGINTIME=? WHERE MEM_NO=?";
	private static final String GET="SELECT * FROM MEMBER WHERE MEM_SRATUS = ? ";
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G1DB");
		} catch ( NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		MemberDAO memberDAO = new MemberDAO();
//		MemberVO memberVO = new MemberVO();
		
//		memberVO.setMemNo("MEM0005");
//		memberVO.setMemName("������");
//		memberVO.setMemPhone("99999999");
//		memberVO.setMemGender("�k");
//		memberVO.setMemEmail("zodiac@gmail.com");
//		memberVO.setAddress("��饫���c�Ϥ�����300��");
//		memberVO.setPoint(15000);
//		memberVO.setMemId("zodiac3012");
//		memberVO.setMemPwd("aaaaaa");
//		memberVO.setMemSratus("������");
//		memberVO.setChkIp("140.112.130.9.2");
//		memberVO.setMemLoginTime(new Timestamp(System.currentTimeMillis()));
		
		/*�s�W�|�����*/
//		memberDAO.insert(memberVO);
		
		/*��s�|�����*/
//		memberDAO.update(memberVO);
		
		/*�d�߳浧�|�����*/
//		memberVO = memDAO.findByPrimaryKey("MEM0005");
//		System.out.println(memberVO);
		
		/*�d�ߥ����|�����*/
//		List<MemberVO> memberList  = memberDAO.getAll();
//		for(MemberVO memVO:memberList){
//			System.out.println(memVO.getMemId());
//			System.out.println();
//		}
		
		/*�R���浧�|�����*/
//		memberDAO.delete("MEM0005");
		
	}

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemPhone());
			pstmt.setString(3, memberVO.getMemGender());
			pstmt.setString(4, memberVO.getMemEmail());
			pstmt.setString(5, memberVO.getAddress());
			pstmt.setInt(6, memberVO.getPoint());
			pstmt.setString(7, memberVO.getMemId());
			pstmt.setString(8, memberVO.getMemPwd());
			pstmt.setString(9, memberVO.getMemSratus());
			pstmt.setString(10, memberVO.getChkIp());
			pstmt.setTimestamp(11, memberVO.getMemLoginTime());

			int i = pstmt.executeUpdate();
			System.out.println("memVO insert success");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemPhone());
			pstmt.setString(3, memberVO.getMemGender());
			pstmt.setString(4, memberVO.getMemEmail());
			pstmt.setString(5, memberVO.getAddress());
			pstmt.setInt(6, memberVO.getPoint());
			pstmt.setString(7, memberVO.getMemId());
			pstmt.setString(8, memberVO.getMemPwd());
			pstmt.setString(9, memberVO.getMemSratus());
			pstmt.setString(10, memberVO.getChkIp());
			pstmt.setTimestamp(11, memberVO.getMemLoginTime());
			pstmt.setString(12, memberVO.getMemNo());
			
			int i = pstmt.executeUpdate();
			System.out.println("memVO update success");
			
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
	public MemberVO findByPrimaryKey(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1,memNo);
			rs = pstmt.executeQuery();
			if(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMemNo(rs.getString("MEM_NO"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemPhone(rs.getString("MEM_PHONE"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memberVO.setAddress(rs.getString("ADDRESS"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setMemId(rs.getString("MEM_ID"));
				memberVO.setMemPwd(rs.getString("MEM_PWD"));
				memberVO.setMemSratus(rs.getString("MEM_SRATUS"));
				memberVO.setChkIp(rs.getString("CHK_IP"));
				memberVO.setMemLoginTime(rs.getTimestamp("MEM_LOGINTIME"));
			}
			System.out.println("memVO findByPrimaryKey success");
			return memberVO;
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memberList = new ArrayList();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNo(rs.getString("MEM_NO"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemPhone(rs.getString("MEM_PHONE"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memberVO.setAddress(rs.getString("ADDRESS"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setMemId(rs.getString("MEM_ID"));
				memberVO.setMemPwd(rs.getString("MEM_PWD"));
				memberVO.setMemSratus(rs.getString("MEM_SRATUS"));
				memberVO.setChkIp(rs.getString("CHK_IP"));
				memberVO.setMemLoginTime(rs.getTimestamp("MEM_LOGINTIME"));
				memberList.add(memberVO);
			}
			return memberList;
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
	
		return memberList;
	}

	@Override
	public void delete(String memNo) {
		Connection con  = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, memNo);
			int i = pstmt.executeUpdate();
			
			System.out.println("delete"+i+" shot");
			
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
	public MemberVO findByMemId(String memId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BYMEMID);
			pstmt.setString(1,memId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				memberVO = new MemberVO();
				memberVO.setMemNo(rs.getString("MEM_NO"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemPhone(rs.getString("MEM_PHONE"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memberVO.setAddress(rs.getString("ADDRESS"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setMemId(rs.getString("MEM_ID"));
				memberVO.setMemPwd(rs.getString("MEM_PWD"));
				memberVO.setMemSratus(rs.getString("MEM_SRATUS"));
				memberVO.setChkIp(rs.getString("CHK_IP"));
				memberVO.setMemLoginTime(rs.getTimestamp("MEM_LOGINTIME"));
			}
			System.out.println("memVO findByMemId success");
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getStatus(String status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 List<MemberVO> memList = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			memList = new ArrayList<>();
			while(rs.next()){
				MemberVO memberVO = new MemberVO();
				memberVO.setMemNo(rs.getString("MEM_NO"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemPhone(rs.getString("MEM_PHONE"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));
				memberVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memberVO.setAddress(rs.getString("ADDRESS"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setMemId(rs.getString("MEM_ID"));
				memberVO.setMemPwd(rs.getString("MEM_PWD"));
				memberVO.setMemSratus(rs.getString("MEM_SRATUS"));
				memberVO.setChkIp(rs.getString("CHK_IP"));
				memberVO.setMemLoginTime(rs.getTimestamp("MEM_LOGINTIME"));

				memList.add(memberVO);
			}
			return memList;
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
		return memList;
	}

}
