package com.android.emp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.emp.model.HcEmpService;
import com.android.tool.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/HcEmpController")
public class HcEmpController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		BufferedReader br = req.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine())!=null){
			sb.append(line);
		}
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(sb.toString(),JsonObject.class);
		
		HcEmpService hcEmpSvc = new HcEmpService();
		String action = json.get("action").getAsString();
		
		if("getAll".equals(action)){
			System.out.println("查詢全部長照人員");
			System.out.println(gson.toJson(hcEmpSvc.getAll()));
			writeToClient(res,gson.toJson(hcEmpSvc.getAll()));
		}
		if("getImage".equals(action)){
			ServletOutputStream out;
			byte[] img = hcEmpSvc.getImgByEmpNo(json.get("id").getAsString());
			if(img!=null){
				img = ImageUtil.shrink(img,json.get("imageSize").getAsInt());
				res.setContentType("image/jpeg");
				res.setContentLength(img.length);
				out = res.getOutputStream();
				out.write(img);
				out.flush();
			}
		}
		if("getShifts".equals(action)){
			
		}
		
		
		
	}
	
	private void writeToClient(HttpServletResponse res,String jsonStr) throws IOException{
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.println(jsonStr);
		out.flush();
	}
}
