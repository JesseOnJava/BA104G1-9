package com.android.carOrder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.carOrder.model.CarOrderVO;
import com.android.carSchedul.model.CarSchedulService;
import com.android.carSchedul.model.CarSchedulVO;
import com.android.carType.model.CarTypeService;
import com.android.vehicle.model.VehicleVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/CarOrderController")
public class CarOrderController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		
		BufferedReader br = req.getReader();
		StringBuilder s = new StringBuilder();
		String str;
		while((str = br.readLine())!=null){
			s.append(str);
		}
		
		Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'").create();
		String action = req.getParameter("action");		
		
		if(action.equals("addCarOrder")){
			CarOrderVO carOrder = (CarOrderVO) gson.fromJson(s.toString(), CarOrderVO.class);
			CarTypeService carTypeSvc = new CarTypeService();
			CarSchedulService carSchedulSvc = new CarSchedulService();
			
			Map<String,List<CarSchedulVO>> empCarSchedulMap = new HashMap();
			List<VehicleVO> vehicleList = carTypeSvc.getVehicleVObyCarTypeNo(carOrder.getCarTypeNo());
			for(VehicleVO vechicleVO:vehicleList){
				carSchedulSvc.getByEmpNo(vechicleVO.getEmpNo(),"");
			}
			
		}
	}

}
