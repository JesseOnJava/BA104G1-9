package com.employee_photo.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/DBGifReader4")
public class DBGifReader4 extends HttpServlet {

	private static Connection conn;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("getPhotoByEmpNO".equals(action)) {
			String empNo = req.getParameter("empNo");
			Statement stmt = null;
			ResultSet rs = null;
			byte[] b = new byte[1024 * 4];
			int len = 0;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT EMP_PHOTO FROM EMP_PHOTOS WHERE EMP_NO='" + empNo + "' AND ROWNUM<=1");
				rs.next();

				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("EMP_PHOTO"));
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				in.close();

			} catch (SQLException e) {
				InputStream in = getServletContext().getResourceAsStream("/noData/noPic.jpg");
				byte[] bb = new byte[in.available()];
				in.read(bb);
				out.write(bb);
				in.close();

			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return;
		}

		try

		{
			String emp_photo_no = req.getParameter("emp_photo_no");
			emp_photo_no = new String(emp_photo_no.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("pic!!!!");

			Statement stmt = conn.createStatement();
			// ResultSet rs = stmt.executeQuery(
			// "SELECT PICTURE FROM emp_photo WHERE EMPNO ='"+ "漂亮"+"'");
			ResultSet rs = stmt
					.executeQuery("SELECT EMP_PHOTO FROM emp_photos WHERE EMP_PHOTO_NO ='" + emp_photo_no + "'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("EMP_PHOTO"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				// res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/noData/noPic.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/noData/noPic.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Project");
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
