package com.shopcomplain.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.shopcomplain.model.*;
import com.shoporder.model.*;

@WebServlet("/ShopComplainServlet")
public class ShopComplainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
/************************************** 會員前端查詢 getOne_For_Update **********************************************************************************/		
//		這裡要改小石model的資訊
		
//		if ("getOne_For_Update_MealOrder".equals(action)) { // 來自listAllMealOrder.jsp
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/complain/listAllMealCom.jsp】 或 
//			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑
//			
//			String whichPage = req.getParameter("whichPage");
//			req.setAttribute("whichPage", whichPage);   // 送出修改的來源網頁的第幾頁, 存入req(只用於:listAllMealCom.jsp)
//			
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				String mo_no = new String(req.getParameter("mo_no"));
//				
//				/***************************2.開始查詢資料****************************************/
//				HcOrderMasterService hcOrderMasterorderSvc = new HcOrderMasterService();
//				HcOrderMasterVO hcOrderMasterVO = hcOrderMasterorderSvc.getOne(mo_no);
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("hcOrderMasterVO", hcOrderMasterVO); // 資料庫取出的VO物件,存入req
//				
//				String url = "/front/complain/addHcCom.jsp";      // 成功轉交 addHcCom.jsp
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交addHcCom.jsp
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理************************************/
//			} catch (Exception e) {
//				throw new ServletException(e);
//			}
//		}		

		
				
		
		
		
/************************************** 會員前端新增申訴 insert **********************************************************************************/		
		
		if("insert_front".equals(action)){  //addShopCom.jsp請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/complain/listAllShopCom.jsp】 或 
			String whichPage = req.getParameter("whichPage");
			
			try{
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				//  申訴單號
				String orderno = req.getParameter("orderno");
				System.out.println("-------"+orderno);
			
				// 申訴內容
				String complainDetail = req.getParameter("complainDetail").trim();
				if(complainDetail == null || complainDetail.trim().length() ==0){
					errorMsgs.add("申訴內容請勿空白");
				}
										
				// 改取小石的
//				MealOrderMasterService mealOrderMasterSvc = new MealOrderMasterService();
//				MealOrderMasterVO mealOrderMasterVO = hcOrderMasterSvc.getOne(order_no);
				
				ShopComplainVO shopComplainVO = new ShopComplainVO();
				shopComplainVO.setOrderno(orderno);
				shopComplainVO.setComplainDetail(complainDetail);
				shopComplainVO.setComplainStatus("未處理");
				
				
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()){
					req.setAttribute("shopComplainVO", shopComplainVO);  //含有輸入格式錯誤的shopComplainVO,也存入req
//					req.setAttribute("shoporderVO", shopOrderSvc.getAllByOrderNo(orderno));
					System.out.println(orderno);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/complain/addShopCom.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				

				/***************************2.開始修改資料*****************************************/
				ShopComplainService shopComplainSvc = new ShopComplainService();
				shopComplainVO = shopComplainSvc.addShopCom(orderno, complainDetail, "未處理");
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopComplainVO", shopComplainVO); // 資料庫update成功後,正確的的VO物件,存入req
				// 這裡要改回去
				String url = "/front/complain/listAllCarOrder.jsp";
//				String url = requestURL+"?whichPage="+whichPage+"&mo_no="+mo_no; // 送出修改的來源網頁的第幾頁(只用於:listAllCarCom.jsp)和修改的是哪一筆
//				System.out.println("requestURL:"+requestURL+"------------"+"mo_no:"+mo_no);
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);
				System.out.println("--------------轉交出去");
				
				/***************************其他可能的錯誤處理*************************************/
			
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/complain/addShopCom.jsp");
				failureView.forward(req, res);
			
			}
		}
		
		  
/************************************** getOne_For_Updat_shop **********************************************************************************/		

		if ("getOne_For_Updat_shop".equals(action)) { // 來自listAllShopCom.jsp 或  的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/complain/listAllShopCom.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req (是為了給update_shop_input.jsp)
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);   // 送出修改的來源網頁的第幾頁, 存入req(只用於:listAllShopcom.jsp)
			
			try {
				/***************************1.接收請求參數****************************************/
				
				String complainNo = req.getParameter("complainNo");
				String orderno = req.getParameter("orderno");
				System.out.println("=========查詢一個"+orderno);
				/***************************2.開始查詢資料****************************************/
				ShopComplainService shopComplainSvc = new ShopComplainService();
				ShopComplainVO shopComplainVO = shopComplainSvc.getOneShopCom(complainNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shopComplainVO", shopComplainVO); // 資料庫取出的shopComplainV物件,存入req
				String url = "/back/complain/update_shop_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
/************************************** getOne_For_Display **********************************************************************************/		

		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String complainNo = req.getParameter("complainNo");
				System.out.println("==============:"+complainNo);
				
				/***************************2.開始查詢資料*****************************************/
				ShopComplainService shopComplainSvc = new ShopComplainService();
				ShopComplainVO shopComplainVO = shopComplainSvc.getOneShopCom(complainNo);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopComplainVO", shopComplainVO); // 資料庫取出的shopComplainV物件,存入req
				String url = "/back/complain/listOneShopCom.jsp";
				System.out.println("==============商城準備轉交");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);

		}
		
		
/************************************** delete **********************************************************************************/		

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/complain/listAllShopCom.jsp】 或 
			String whichPage = req.getParameter("whichPage");
			
			try {
				/***************************1.接收請求參數***************************************/
				String complainNo = req.getParameter("complainNo");
						
				/***************************2.開始刪除資料***************************************/
				ShopComplainService shopComplainSvc = new ShopComplainService();
				shopComplainSvc.deleteShopCom(complainNo);
						
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				
				String url = requestURL+"?whichPage="+whichPage+"&complianNo="+complainNo; // 送出修改的來源網頁的第幾頁(只用於:listAllShopCom.jsp)和修改的是哪一筆
				System.out.println("requestURL:"+requestURL+"------------"+"complainNo:"+complainNo);
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);
					
				/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
								.getRequestDispatcher("/back/newsdetail/listAllShopCom.jsp");
				failureView.forward(req, res);
			}
		}
				
	
/************************************** update **********************************************************************************/		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String complainNo = req.getParameter("complainNo");
				System.out.println("=========" + complainNo);
				
				String orderno = req.getParameter("orderno");
				System.out.println("================"+orderno);
				String complainDetail = req.getParameter("complainDetail");
				
				String complainReply = req.getParameter("complainReply");
				if( complainReply == null || complainReply.trim().length() ==0){
					errorMsgs.add("回覆內容請勿留空");
				}
				
				Timestamp replyDate = new Timestamp(System.currentTimeMillis());
				
				String emp_no = req.getParameter("emp_no");
				System.out.println("================"+emp_no);
				
				String complainStatus = req.getParameter("complainStatus");
				
//				ShopOrderService shoporderSvc = new ShopOrderService();
				ShopComplainVO shopComplainVO = new ShopComplainVO();
				shopComplainVO.setComplainNo(complainNo);
				shopComplainVO.setOrderno(orderno);
				shopComplainVO.setComplainDetail(complainDetail);
				shopComplainVO.setComplainReply(complainReply);
				shopComplainVO.setReplyDate(replyDate);
				shopComplainVO.setEmp_no(emp_no);
				shopComplainVO.setComplainStatus(complainStatus);
						
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopComplainVO", shopComplainVO); // 含有輸入格式錯誤的shopComplainVO物件,也存入req
//					req.setAttribute("shopOrderVO", shoporderSvc.getOneShopCom(orderno));
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/complain/update_shop_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ShopComplainService shopComplainSvc = new ShopComplainService();
				shopComplainVO = shopComplainSvc.updateShopCom(complainNo, complainReply,replyDate, emp_no ,"已完成");
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopComplainVO", shopComplainVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = requestURL+"?whichPage="+whichPage+"&complianNo="+complainNo; // 送出修改的來源網頁的第幾頁(只用於:listAllShopCom.jsp)和修改的是哪一筆
				System.out.println("requestURL:"+requestURL+"------------"+"complainNo:"+complainNo);
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/complain/update_shop_input.jsp");
				failureView.forward(req, res);
			}
		}
	
	}

}
