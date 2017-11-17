package com.hcorder.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcorder.modal.HcOrderMasterService;
import com.hcorder.modal.HcOrderMasterVO;



public class HcOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HcOrderController() {
        super();
    }


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		



		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		Enumeration en = req.getParameterNames();
		System.out.println("----------------------------");
		
		
		while(en.hasMoreElements()){
			String name = (String)en.nextElement();
			System.out.println(name+":"+req.getParameter(name)); 
		}
		
		
		
		
		if ("add_hc_order".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String servDate = req.getParameter("servDate");
				String serviceTime = req.getParameter("servTime");
				String caredNo = req.getParameter("caredNo");
				String empNo = req.getParameter("empNo");
				String memNo = req.getParameter("memNo");
				if(req.getSession().getAttribute("memVO") == null){
					errorMsgs.add("請登入");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if (servDate == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務日期");
				}
				if (serviceTime == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務日期");
				}
				if (caredNo == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務對象");
				}
				if (empNo == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務人員");
				}				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Date serviceDate = null;				
				try {
					serviceDate = java.sql.Date.valueOf(req.getParameter("servDate").trim());
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
				
			//檢查該天是否已經下定

				
				/***************************2.開始新增資料*****************************************/
				
				HcOrderMasterService hcOrderSvc = new HcOrderMasterService();
				HcOrderMasterVO hcOrderMasterVO = hcOrderSvc.addHcOrderMaster(memNo, caredNo, "未確認",serviceDate,serviceTime,empNo);
				System.out.println("取回來的VO"+hcOrderMasterVO.getOrderNo());
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("hcOrderMasterVO", hcOrderMasterVO);
				String url = "/front/homeCare/Hc_show_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
				failureView.forward(req, res);
			}
		}
	
			
		
		

		if ("add_hc_order_many".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("YY");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String servDate = req.getParameter("servDate");
				String serviceTime = req.getParameter("servTime");
				String caredNo = req.getParameter("caredNo");
				String empNo = req.getParameter("empNo");
				String memNo = req.getParameter("memNo");
				
				if(req.getSession().getAttribute("memVO") == null){
					errorMsgs.add("請登入");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				if (servDate == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務日期");
				}
				if (serviceTime == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務日期");
				}
				if (caredNo == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務對象");
				}
				if (empNo == null || (servDate.trim()).length() == 0) {
					errorMsgs.add("請選擇服務人員");
				}				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Date serviceDate = null;				
				try {
					serviceDate = java.sql.Date.valueOf(req.getParameter("servDate").trim());
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
				
			//檢查該天是否已經下定

				
				/***************************2.開始新增資料*****************************************/
				
				HcOrderMasterService hcOrderSvc = new HcOrderMasterService();
				HcOrderMasterVO hcOrderMasterVO = hcOrderSvc.addHcOrderMaster(memNo, caredNo, "未確認",serviceDate,serviceTime,empNo);
				System.out.println("取回來的VO"+hcOrderMasterVO.getOrderNo());
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("hcOrderMasterVO", hcOrderMasterVO);
				String url = "/front/homeCare/Hc_show_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/homeCare/Hc_order.jsp");
				failureView.forward(req, res);
			}
		}
				
				
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始查詢資料****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empno);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/emp/update_emp_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	
				
				
				
				
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer empno = new Integer(req.getParameter("empno").trim());
//				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}	
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpno(empno);
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String ename = req.getParameter("ename");
//				//String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//				
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//				
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	

	}

}
