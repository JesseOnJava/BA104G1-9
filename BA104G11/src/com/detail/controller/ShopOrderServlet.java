package com.detail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.detail.cart.CartVO;
import com.shoporder.model.ShopOrderService;
import com.shoporder.model.ShopOrderVO;

public class ShopOrderServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("addshoporder".equals(action)) {// �Ӧ�ListAllPropro.jsp���ШD
			System.out.println("�i�JShopOrderSevlet");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String str = req.getParameter("ADDRESS");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�a�}");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/MasterOrder/addshoporder.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				ShopOrderVO shopOrderVO=new ShopOrderVO();
				shopOrderVO.setCustomer_address(req.getParameter("ADDRESS"));
				shopOrderVO.setCustomer_name(req.getParameter("NAME"));
				shopOrderVO.setCustomer_phone(req.getParameter("PHONE"));
				shopOrderVO.setMemberno(req.getParameter("MEMNO"));
				shopOrderVO.setItemno(new Integer(req.getParameter("ITEMNO")));
				shopOrderVO.setOrdercount(new Integer(req.getParameter("ORDERCOUNT")));

				/*************************** 2.�}�l�s�W��� *****************************************/
				ShopOrderService shopOrderSvc = new ShopOrderService();
				ShopOrderVO shopordervo =shopOrderSvc.addShopOrder(shopOrderVO);
//				if (proVO == null) {
//					errorMsgs.add("�d�L���");
//				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/MasterOrder/addshoporder.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("shopordervo", shopordervo); 
				String url = "/MasterOrder/ListAllProOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/MasterOrder/addshoporder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("CONFIRM".equals(action)) {// �Ӧ�CHECK.JSP���ШD
			
			
			HttpSession session = req.getSession();
			Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
			Integer Point=null;

			for(int i=0;i<buylist.size();i++){
				CartVO cartVO = buylist.get(i);
				cartVO.setQUANTITY(new Integer(req.getParameter("quantity"+i)));
				System.out.println("�ҧ��ܪ��ƶq :"+req.getParameter("quantity"+i));
			}
			
			List<ShopOrderVO> list = new ArrayList<ShopOrderVO>();
			ShopOrderService shopOrSvc = new ShopOrderService();
			
			Integer amount=(int) (Float.parseFloat(req.getParameter("amount")));
			String MEMNO=req.getParameter("MEMNO");
			Point=shopOrSvc.returnPoint(MEMNO);
			ShopOrderVO shopOrderVO = null;
			//�o��n���d�ߤ@���o��|����POINT���S���C���`���B�b����U���ʰ�
			if(Point>=amount) {
				System.out.println("�l�B����");
			for (int i = 0; i < buylist.size(); i++) {
				CartVO cartVO = buylist.get(i);
				shopOrderVO = new ShopOrderVO();
//				System.out.println("�� " + i + "�����XBEAN���ȧ@����");
//				System.out.println("cartVO.getNAME()" + cartVO.getNAME());
				shopOrderVO.setItemno(cartVO.getITEMNO());
				shopOrderVO.setMemberno(MEMNO);
//				System.out.println("shopOrderVO.setItemno:" + shopOrderVO.getItemno());
//				System.out.println("cartVO.getITEMNO()" + cartVO.getITEMNO());
//				System.out.println("cartVO.getQUANTITY()" + cartVO.getQUANTITY());
				shopOrderVO.setOrdercount(cartVO.getQUANTITY());
				// �Ĥ@�����ɭ��ٻݭn�]�m�Τ�q��a�}�A�����i�H�q�|�쨺�伴�o������갵
//				System.out.println("shopOrderVO.getOrdercount():" + shopOrderVO.getOrdercount());
//				System.out.println("cartVO.getDES()" + cartVO.getDES().substring(0, 5));
//				System.out.println("cartVO.getPRICE()" + cartVO.getPRICE());
				//��ت����ثe��LIST�����갵�A�ǹL�h��LIST<ShopOrderVO>
				list.add(shopOrderVO);
				// shopOrSvc.addShopOrder(shopOrderVO);
				}
			//���B�����q�榨��
			shopOrSvc.addShopCartOrder(list);
			}else{
				System.out.println("�l�B�����q�楼����,FORWARD�X�h�F�C");
				//String url = "/MasterOrder/listallOrder.jsp";
				String url = "/MasterOrder/listallOrderbeautiful.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// ListAllProOrder.jsp
				successView.forward(req, res);
				//�����n�_�F�{���~���|�~��]�A�q�z�ʡC
				return;
			}

			Integer NowPoint=shopOrSvc.returnAfterShoppingPoint(amount, MEMNO);
			
			System.out.println("���O�e�I�� :"+Point+",���O���I��"+NowPoint);
			//String url = "/MasterOrder/listallOrder.jsp";
			String url = "/MasterOrder/listallOrderbeautiful.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																			// ListAllProOrder.jsp
			successView.forward(req, res);

		} 
		if ("DELETE".equals(action)) {// �Ӧ�CHECK.JSP���ШD

			try {
				String ORDERID = (String) req.getParameter("ORDERID");
				String MEMBERNO = (String) req.getParameter("MEMBERNO");

				ShopOrderService shopOrderSvc = new ShopOrderService();
				shopOrderSvc.delete(ORDERID, MEMBERNO);

				String url = "/MasterOrder/listallOrderbeautiful.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
																				// ListAllProOrder.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				System.out.println("ShopOrderServlet.java DELETE���� :" + e);
			}
		}
		
		if ("UPDATE_ORDERID".equals(action) || "LOOKORDERID".equals(action)) {// �Ӧ�CHECK.JSP���ШD

			System.out.println("�i�JShopOrderServlet.java.UPdate_orderid�޿�B��");
			String orderid = req.getParameter("ORDERID");
			String memberno = req.getParameter("MEMBERNO");
			Integer total=0;
			List<ShopOrderVO> FinishShopOrderVO = null;
			ShopOrderService shopOrderSvc = new ShopOrderService();
			// liatinpromotion�q�椤���S�����ӫ~������(�h�@��VO�ݩ�newprice)
			List<ShopOrderVO> ListAllOrderShop = shopOrderSvc.getPriceByOrderNoIfHave(orderid);
			System.out.println("�j�M�����ӫ~�ɪ�ListAllOrderShop.size() :"+ListAllOrderShop.size());
			System.out.println("-----------------------------------------------");
			// ListAllPro�O�����q�ʰӫ~������
			List<ShopOrderVO> ListAllPro = shopOrderSvc.getPriceByOrderNo(orderid);
			System.out.println("�j�M�����ӫ~�ɪ�ListAllPro.size() :"+ListAllPro.size());
			// ���j����O�_���P�P����A�������л\��ListAllPro
			for (int i = 0; i < ListAllOrderShop.size(); i++) {
				ShopOrderVO ListShopVO = ListAllOrderShop.get(i);
				for (int j = 0; j < ListAllPro.size(); j++) {
					ShopOrderVO ListShopProVO = ListAllPro.get(j);
					if (ListShopVO.getItemno() == ListShopProVO.getItemno()) {
						System.out.println("�쥻ListShopProVO.getPrice() :" + ListShopProVO.getPrice());
						System.out.println("�쥻ListShopVO.getPrice() :" + ListShopVO.getPrice());
						ListShopVO.setPrice(ListShopProVO.getPrice());
						System.out.println( "ListShopVO.setPrice(ListShopProVO.getPrice()):" + ListShopProVO.getPrice());
						System.out.println("錯誤處理123");
					}
				}
			}
			//��X�����q�檺�`���B
			for (int i = 0; i < ListAllOrderShop.size(); i++) {
				total+=ListAllOrderShop.get(i).getPrice()*ListAllOrderShop.get(i).getOrdercount();
				System.out.println("ListAllPro.size() :" + ListAllOrderShop.size());
				System.out.println("ListAllPro.get(i).getPrice() :" + ListAllOrderShop.get(i).getPrice());
				System.out.println("ListAllPro.get(i).getItemno() :"+ListAllOrderShop.get(i).getItemno());
				System.out.println("ListAllPro.get(i).getItemno() :"+ListAllOrderShop.get(i).getCustomer_name());
				System.out.println("ListAllPro.get(i).getItemno() :"+ListAllOrderShop.get(i).getPrice());
			}
			System.out.println("�`���B :"+total);
			try {
				req.setAttribute("total", total);
				req.setAttribute("OrderList", ListAllOrderShop);
//				HttpSession session =req.getSession();
//				session.setAttribute("OrderList", ListAllOrderShop);
				String url = null;
				if("UPDATE_ORDERID".equals(action)) {
					 url = "/MasterOrder/Update_one_order.jsp";
				}else if("LOOKORDERID".equals(action)){
					url = "/MasterOrder/ShowOneOrderDetail.jsp";
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
				successView.forward(req, res);

			} catch (Exception e) {
				System.out.println("ShopOrderServlet.java DELETE���� :" + e);
			}
		}
		if ("CONFIRM_UPDATE_ORDERID".equals(action)) {// �Ӧ�CHECK.JSP���ШD
			Integer amount=new Integer(req.getParameter("amount"));
			Integer newTotal=0;
			String Orderno=null;
			
			HttpSession session = req.getSession();
			List<ShopOrderVO> OrderList = (List<ShopOrderVO>) session.getAttribute("OrderList");
			for(int i=0;i<OrderList.size();i++){
				ShopOrderVO ShopOrderVO = OrderList.get(i);
				ShopOrderVO.setOrdercount(new Integer(req.getParameter("quantity"+i)));
				newTotal+=ShopOrderVO.getOrdercount()*ShopOrderVO.getPrice();
				System.out.println("�s������ :"+newTotal);
				Orderno=ShopOrderVO.getOrderno();
			}
			Integer finalTotal=amount-newTotal;
			ShopOrderService shopOrSvc = new ShopOrderService();
			Integer Point=shopOrSvc.returnPoint(OrderList.get(0).getMemberno());
			if(Point>finalTotal) {
			shopOrSvc.updateShopOrder(OrderList,finalTotal);
			}else {
				System.out.println("�l�B�����ʧ@����");
			}
			
//			System.out.println("���O�e�I�� :"+Point+",���O���I��"+NowPoint);
//			String url = "/MasterOrder/listallOrder.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
//																			// ListAllProOrder.jsp
//			successView.forward(req, res);

		} 
	}
	
}

