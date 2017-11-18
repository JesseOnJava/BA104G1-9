package com.detail.controller;

import java.io.IOException;
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

import com.detail.cart.CartDAO;
import com.detail.cart.CartService;
import com.detail.cart.CartVO;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
System.out.println("�i�Jcartservlet");
		req.setCharacterEncoding("UTF-8");
		// res.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = res.getWriter();
		System.out.println(req.getParameter("STOCK"));
		System.out.println(req.getParameter("action"));
		System.out.println(req.getParameter("ITEMNO"));
		HttpSession session = req.getSession();
		Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if (action.equals("CLEARCART")) {
			buylist.clear();
		}
		if (!action.equals("CHECKOUT")) {

			// �R���ʪ����������y
			if (action.equals("DELETE")) {
				System.out.println("�i�Jcartservlet.DELETE");
				String del = req.getParameter("del");
//				String price = req.getParameter("price");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
				
			}
			// �s�W���y���ʪ�����
			else if (action.equals("ADD")) {
				boolean match = false;
				// ���o��ӷs�W�����y
				CartService CartSvc = new CartService();
				// �o����ե�stock�t�X�e�����Ѽ�,��ڰѼƬOquantity
				Integer ITEMNO = Integer.parseInt(req.getParameter("ITEMNO"));
				Integer STOCK = Integer.parseInt(req.getParameter("STOCK"));
				

				if (STOCK == 0) {
					errorMsgs.add("�ӫ~�ƶq���i��0");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listAll.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				
				CartVO CartVO = CartSvc.findshop(ITEMNO, STOCK);
				// CartVO CartVO = getCartVO(req);
				// �s�W�Ĥ@�����y���ʪ�����
				if (buylist == null) {
					buylist = new Vector<CartVO>();
					buylist.add(CartVO);
				}  else {
					for (int i = 0; i < buylist.size(); i++) {
						CartVO cartVO = buylist.get(i);

						// ���Y�s�W�����y�M�ʪ��������y�@�ˮ�
						if (cartVO.getNAME().equals(CartVO.getNAME())) {
							cartVO.setQUANTITY(cartVO.getQUANTITY() + CartVO.getQUANTITY());
							buylist.setElementAt(cartVO, i);
							System.out.println("�s�W�쭫�ƪ��ӫ~�W�� :" + cartVO.getNAME());
							match = true;
							System.out.println("���Фw�Q���");
						} // end of if name matches
						// System.out.println("��ܪ��ӫ~�W��"+cartVO.getNAME());
						// System.out.println("��ܪ��ӫ~�ƶq"+cartVO.getQuantity());
						// System.out.println("��ܪ��ӫ~�y�z"+cartVO.getDES());
						// System.out.println("��ܪ��ӫ~�s��"+cartVO.getITEMNO());
						// System.out.println("��ܪ��ӫ~����"+cartVO.getPRICE());
					} // end of for

					// ���Y�s�W�����y�M�ʪ��������y���@�ˮ�
					if (!match)
						buylist.add(CartVO);
				}
			}else if (action.equals("ADD2")) {
				boolean match = false;
				// ���o��ӷs�W�����y
				CartService CartSvc = new CartService();
				// �o����ե�stock�t�X�e�����Ѽ�,��ڰѼƬOquantity
				Integer ITEMNO = Integer.parseInt(req.getParameter("ITEMNO"));
				Integer STOCK = Integer.parseInt(req.getParameter("STOCK"));

				if (STOCK == 0) {
					errorMsgs.add("�ӫ~�ƶq���i��0");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listAll.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				System.out.println("findshopbypro�}�l");
				CartVO CartVO = CartSvc.findshopbypro(ITEMNO, STOCK);
				// CartVO CartVO = getCartVO(req);
				// �s�W�Ĥ@�����y���ʪ�����
				if (buylist == null) {
					buylist = new Vector<CartVO>();
					buylist.add(CartVO);
				}  else {
					for (int i = 0; i < buylist.size(); i++) {
						CartVO cartVO = buylist.get(i);

						// ���Y�s�W�����y�M�ʪ��������y�@�ˮ�
						if (cartVO.getNAME().equals(CartVO.getNAME())) {
							cartVO.setQUANTITY(cartVO.getQUANTITY() + CartVO.getQUANTITY());
							buylist.setElementAt(cartVO, i);
							System.out.println("�s�W�쭫�ƪ��ӫ~�W�� :" + cartVO.getNAME());
							match = true;
							System.out.println("���Фw�Q���");
						} // end of if name matches
						// System.out.println("��ܪ��ӫ~�W��"+cartVO.getNAME());
						// System.out.println("��ܪ��ӫ~�ƶq"+cartVO.getQuantity());
						// System.out.println("��ܪ��ӫ~�y�z"+cartVO.getDES());
						// System.out.println("��ܪ��ӫ~�s��"+cartVO.getITEMNO());
						// System.out.println("��ܪ��ӫ~����"+cartVO.getPRICE());
					} // end of for

					// ���Y�s�W�����y�M�ʪ��������y���@�ˮ�
					if (!match)
						buylist.add(CartVO);
				}
				float total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					CartVO order = buylist.get(i);
					float price = order.getPRICE();
					int quantity = order.getQUANTITY();
					total += (price * quantity);
				}
			
				String amount = String.valueOf(total);
				session.setAttribute("amount", amount);
				req.setAttribute("amount", amount);
			}
			
			float total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				CartVO order = buylist.get(i);
				float price = order.getPRICE();
				int quantity = order.getQUANTITY();
				total += (price * quantity);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			req.setAttribute("amount", amount);
			
			//�C��CARTVO�a�L�h���ѼƦ�NAME,PRICE,DES,QUANTITY,ITEMNO
			session.setAttribute("shoppingcart", buylist);
			System.out.println("forward��/Shop/listAll.jsp");
			String url = "/MasterOrder/Cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// ���b�A�p���ʪ������y�����`��
		else if (action.equals("CHECKOUT")) {
			
			float total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				CartVO order = buylist.get(i);
				float price = order.getPRICE();
				int quantity = order.getQUANTITY();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			session.setAttribute("amount", amount);
			String url = "/MasterOrder/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}
		
	}

	private CartVO getCartVO(HttpServletRequest req) {

		Integer STOCK = Integer.parseInt(req.getParameter("STOCK"));
		Integer ITEMNO = Integer.parseInt(req.getParameter("ITEMNO"));
		Integer CLASSNO = Integer.parseInt(req.getParameter("CLASSNO"));
		Integer PRICE = Integer.parseInt(req.getParameter("PRICE"));
		String STATE = req.getParameter("STATE");
		String DES = req.getParameter("DES");
		String NAME = req.getParameter("NAME");
		// �ȤJ�۪v�ʪ����M��bean
		CartVO CartVO = new CartVO();
		CartVO.setNAME(NAME);
		CartVO.setITEMNO(ITEMNO);
		CartVO.setQUANTITY(STOCK);
		CartVO.setDES(DES);
		CartVO.setPRICE(PRICE);

		return CartVO;
	}
}
