package com.hcworkshifts.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee_photo.model.EmpPhotosService;
import com.employee_photo.model.EmpPhotosVO;
import com.hcworkshifts.model.HcWorkShiftsService;
import com.hcworkshifts.model.HcWorkShiftsVO;




public class HcWorkshiftsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HcWorkshiftsServlet() {
        super();
    }


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {



		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html ; charset=UTF-8");
		
			System.out.println(action);
		if ("getAllByDateTime".equals(action)) { // 來自Hc_order.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String servDate = req.getParameter("servDate");
				if (servDate == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請輸入服務日期");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				try {
					new SimpleDateFormat("yyyy-MM-dd").parse(servDate);
				} catch (Exception e) {
					errorMsgs.add("服務日期格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				HcWorkShiftsService  hcwshiftSvc = new HcWorkShiftsService(); 
				String servTime = req.getParameter("servTime");
				System.out.println("contr"+servDate);
				System.out.println("contr"+servTime);

				List<HcWorkShiftsVO> hcWorkShiftsVOList = hcwshiftSvc.getAllByDateTime(servDate, servTime);
				
				if (hcWorkShiftsVOList.size()==0) {
					errorMsgs.add("抱歉!您選擇的日期目前尚無人力喔!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				for(HcWorkShiftsVO hcWorkShiftsVO:hcWorkShiftsVOList){
				System.out.println(hcWorkShiftsVO.getEmpNo());
				}	
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			//考慮放哪個scope
				req.getSession().setAttribute("hcWorkShiftsVOList", hcWorkShiftsVOList); 
				String url = "/front/homeCare/Hc_show_emps.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
				failureView.forward(req, res);
			}
		}
		
		


	}

}
