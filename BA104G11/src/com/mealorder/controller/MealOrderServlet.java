package com.mealorder.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mealorder.model.MealOrderService;
import com.mealorder.model.MealOrderVO;
import com.mealorderdetail.model.MealOrderDetailVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.setmeal.model.SetMealService;
import com.setmeal.model.SetMealVO;

public class MealOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String action2=new String(action.getBytes("ISO-8859-1"),"UTF-8");
		
		
		
		
		if("listOrders_ByStatus".equals(action2)){
			String moStatus=req.getParameter("moStatus");
			String moStatus2=new String(moStatus.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(moStatus2);
			MealOrderService mealOrderSvc=new MealOrderService();
			List<MealOrderVO> list=mealOrderSvc.getByStatus(moStatus2);
			
			
			req.setAttribute("list", list);
			
			String url = "/back/MealOrder/listAllMealOrder2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		
		if("update_status".equals(action)){
			String moNo=req.getParameter("moNo");
			String moStatus=req.getParameter("moStatus");
			
			
			MealOrderService mealOrderSvc=new MealOrderService();
            mealOrderSvc.updateStatus(moNo, moStatus);
			
            
            
        	String url = "/back/MealOrder/listAllMealOrder2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		
		
		
		
		
		
		
		if ("fill_in_a_form".equals(action)) {
			//
			Integer smNo = new Integer(req.getParameter("smNo"));
			//
			SetMealService setMealSvc = new SetMealService();
			SetMealVO setMealVO = setMealSvc.getOneSetMeal(smNo);
			Integer smPrice = setMealVO.getSmPrice();
			req.setAttribute("smPrice", smPrice);
			//
			String deliveryDate = req.getParameter("deliveryDate");
			String[] deliveryDates = deliveryDate.split(", ");

			Integer days = deliveryDates.length;
			req.setAttribute("days", days);

			Integer orderQty = new Integer(req.getParameter("orderQty"));

			String mealTime = req.getParameter("mealTime");

			Integer breakfast = 0;
			if (mealTime.contains("早")) {
				breakfast = days * orderQty;
			} else {
				breakfast = 0;
			}

			req.setAttribute("breakfast", breakfast);

			Integer lunch = 0;
			if (mealTime.contains("中")) {
				lunch = days * orderQty;
			} else {
				lunch = 0;
			}

			req.setAttribute("lunch", lunch);

			Integer dinner = 0;
			if (mealTime.contains("晚")) {
				dinner = days * orderQty;
			} else {
				dinner = 0;
			}

			req.setAttribute("dinner", dinner);

			Integer totalMeals = breakfast + lunch + dinner;
			req.setAttribute("totalMeals", totalMeals);

			Integer totalPrice = smPrice * totalMeals;
			req.setAttribute("totalPrice", totalPrice);

			String url = "/front/MealOrder/FileInAForm2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("insert".equals(action)) {

			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

			Integer smNo = new Integer(req.getParameter("smNo"));

			String deliveryDate = req.getParameter("deliveryDate");
			String[] deliveryDates = deliveryDate.split(", ");

			String mealTime = req.getParameter("mealTime").trim();
			Integer orderQty = new Integer(req.getParameter("orderQty").trim());
			String memNo = memberVO.getMemNo();
			String rcptName = req.getParameter("rcptName").trim();
			String rcptAdd = req.getParameter("rcptAdd").trim();
			String rcptPhone = req.getParameter("rcptPhone").trim();

			Integer totalPrice = new Integer(req.getParameter("totalPrice"));
			System.out.println(totalPrice);
			Integer memberPoint = memberVO.getPoint();
			System.out.println(memberPoint);

			if (memberPoint > totalPrice) {
				MealOrderVO mealOrderVO = new MealOrderVO();
				mealOrderVO.setMemNo(memNo);
				mealOrderVO.setRcptName(rcptName);
				mealOrderVO.setRcptAdd(rcptAdd);
				mealOrderVO.setRcptPhone(rcptPhone);

				List<MealOrderDetailVO> list = new ArrayList();
				for (int i = 0; i < deliveryDates.length; i++) {
					MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();
					mealOrderDetailVO.setDeliveryDate(java.sql.Date.valueOf(deliveryDates[i]));
					mealOrderDetailVO.setMealTime(mealTime);
					mealOrderDetailVO.setSmNo(smNo);
					mealOrderDetailVO.setOrderQty(orderQty);
					list.add(mealOrderDetailVO);
				}

				MealOrderService mealOrderSvc = new MealOrderService();
				mealOrderSvc.addMealOrder(mealOrderVO, list);

				MemberService memberSvc = new MemberService();
				memberSvc.updatePoint((memberPoint - totalPrice), memberVO.getMemNo());

			}

			String url = "/front/MealOrder/OrderSucess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("get_All_mealOrder_ByMember".equals(action)) {
			String memNo = req.getParameter("memNo");

			MealOrderService mealOrderSvc = new MealOrderService();
			List<MealOrderVO> list = mealOrderSvc.getByMember(memNo);
			req.setAttribute("list", list);

			String url = "/back/MealOrder/listAllMealOrderByMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
	}

}
