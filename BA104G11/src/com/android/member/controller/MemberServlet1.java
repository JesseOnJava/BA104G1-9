package com.android.member.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.member.model.MemberService;
import com.android.member.model.MemberVO;


@WebServlet("/member/MemberServlet")
public class MemberServlet1 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String memNo = req.getParameter("memNo").toUpperCase();
				if (memNo == null || (memNo.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				
				/***************************2.�}�l�d�߸��*****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMemByNo(memNo);
				if (memberVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/member/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String memNo = req.getParameter("memNo");
				
				/***************************2.�}�l�d�߸��****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMemByNo(memNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front/member/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				String memNo = req.getParameter("memNo");
				String memName = req.getParameter("memName").trim();
				String memId = req.getParameter("memId").trim();	
				String memPwd = req.getParameter("memPwd").trim();	
				String memEmail = req.getParameter("memEmail").trim();	
				
				

				MemberVO memberVO = new MemberVO();
				memberVO.setMemName(memName);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemId(memId);
				memberVO.setMemPwd(memPwd);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemberService memSvc = new MemberService();
				memberVO=memSvc.getOneMemByNo(memNo);
				
				memberVO.setMemName(memName);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemId(memId);
				memberVO.setMemPwd(memPwd);
				memberVO = memSvc.updateMember(memberVO);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front/member/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String memName = req.getParameter("memName").trim();
				String memPhone = req.getParameter("memPhone").trim();
				String memGender = req.getParameter("memGender").trim();
				String memEmail = req.getParameter("memEmail").trim();
				String address = req.getParameter("address").trim();
				Integer point = new Integer(0);
				String memId = req.getParameter("memId").trim();	
				String memPwd = req.getParameter("memPwd").trim();
				
				String memSratus = "未驗證";
				String chkIp = req.getLocalAddr();
				Timestamp memLoginTime = new Timestamp(System.currentTimeMillis()) ;
			

				MemberVO memberVO = new MemberVO();
				memberVO.setMemName(memName);
				memberVO.setMemPhone(memPhone);
				memberVO.setMemGender(memGender);
				memberVO.setMemEmail(memEmail);
				memberVO.setAddress(address);
				memberVO.setPoint(point);
				memberVO.setMemId(memId);
				memberVO.setMemPwd(memPwd);
				memberVO.setMemSratus(memSratus);
				memberVO.setChkIp(chkIp);
				memberVO.setMemLoginTime(memLoginTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.addMember(memName,memPhone,memGender,memEmail,address,point,memId,memPwd,memSratus,chkIp,memLoginTime);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front/member/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String memNo = req.getParameter("memNo");
				
				/***************************2.�}�l�R�����***************************************/
				MemberService empSvc = new MemberService();
				empSvc.deleteEmp(memNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front/member/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
