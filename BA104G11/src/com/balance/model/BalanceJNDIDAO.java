package com.balance.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/BalanceJNDIDAO")

public class BalanceJNDIDAO extends HttpServlet {
	

	private static final String INSERT_STMT =
		"INSERT INTO BALANCE (TOPUP_NO, MEM_NO,TOPUP_VALUE,TOPUP_WAY) VALUES "
		+ "(to_char('TPP'||to_char(sysdate,'yymm')||LPAD(to_char(SEQ_EMPLOYEE.NEXTVAL),3,'0')),?,?,?)";
	private static final String GET_ONE_STMT = 
		"SELECT TOPUP_NO, MEM_NO,TOPUP_VALUE,STATUS,TOPUP_WAY,"
		+ "to_char(TOPUP_TIME,'yyyy-mm-dd-hh:mm:ss') TOPUP_TIME FROM BALANCE WHERE TOPUP_NO=?";
	private static final String DELETE =
		"DELETE FROM BALANCE WHERE TOPUP_NO = ?";	
	private static final String UPDATE = 
		"UPDATE BALANCE set TOPUP_VALUE=?, STATUS=? WHERE TOPUP_NO =?";
	private static final String GET_ALL_STMT =  
			"SELECT TOPUP_NO, MEM_NO,TOPUP_VALUE,STATUS,TOPUP_WAY,to_char (TOPUP_TIME,'yyyy-mm-dd hh:mm:ss') TOPUP_TIME FROM BALANCE ";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		

		res.setContentType("text/plain; charset=Big5");
		PrintWriter out = res.getWriter();

		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba104a1");
			if (ds != null) {
				Connection conn = ds.getConnection();

				if (conn != null) {
					out.println("Got Connection: " + conn.toString());
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from balance");
					while (rs.next()) {
						out.println("memNo = " + rs.getString(1));

					}
					conn.close();
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
