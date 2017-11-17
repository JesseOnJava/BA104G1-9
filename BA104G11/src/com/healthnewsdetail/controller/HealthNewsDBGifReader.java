package com.healthnewsdetail.controller;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HealthNewsDBGifReader extends HttpServlet{

	Connection con;

	  public void doGet(HttpServletRequest req, HttpServletResponse res)
	                               throws ServletException, IOException {
	     
	    req.setCharacterEncoding("UTF-8");
	    res.setContentType("image/png");
	    ServletOutputStream out = res.getOutputStream();
	    
	    try {
	      
	      Statement stmt = con.createStatement();
	      String healthNo  = req.getParameter("healthNo");
	      String healthNo2 = new String(healthNo.getBytes("ISO-8859-1"), "UTF-8");  
	      ResultSet rs = stmt.executeQuery(
	    		  "select coverpic from healthnewsdetail where healthNo = '"+healthNo2+"'");

	      if (rs.next()) {
	        BufferedInputStream in =
	          new BufferedInputStream(rs.getBinaryStream("coverPic"));
	        byte[] buf = new byte[4 * 1024];  // 4K buffer
	        int len;
	        while ((len = in.read(buf)) != -1) {
	          out.write(buf, 0, len);
	        }
	        in.close();
	      } else {
	          res.sendError(HttpServletResponse.SC_NOT_FOUND);
	      }
	      rs.close();
	      stmt.close();
	    } catch(Exception e) {
//	    	System.out.println(e);
	    	InputStream in = getServletContext().getResourceAsStream("/img/notimg.jpg");
	    	byte[] buf = new byte[in.available()];
	    	in.read(buf);
	    	out.write(buf);
	    	in.close();
	    	}
	    }
	  
	  public void init() throws ServletException {
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		      con = DriverManager.getConnection("jdbc:oracle:thin:@13.229.86.22:1521:XE", "ba104g1", "ba104g1");
		    }
		    catch (ClassNotFoundException e) {
		      throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		    }
		    catch (SQLException e) {
		      throw new UnavailableException("Couldn't get db connection");
		    }
		  }
		  
	  public void destroy() {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				  System.out.println(e);
			}
		}
		
	}

