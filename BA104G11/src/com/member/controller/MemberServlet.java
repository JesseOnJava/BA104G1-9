package com.member.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.member.model.*;
import com.tool.MailService;

//  /member/member.do

public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// =================================================登入================================================================================================
		if ("login".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			String url = "/front/Login.jsp";
			req.setAttribute("errorMsgs", errorMsgs);

			String memId = req.getParameter("memId");
			String memPwd = req.getParameter("memPwd");

			if (memId == null || memId.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}

			if (memPwd == null || memPwd.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}

			// ===========================================================================

			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMemById(memId);

			if (memberVO == null) {
				errorMsgs.add("帳號錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			if (!memberVO.getMemPwd().equals(memPwd)) {
				errorMsgs.add("密碼錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setMemLoginTime(new Timestamp(System.currentTimeMillis()));
			memberSvc.updateMember(memberVO);

			HttpSession session = req.getSession();
			session.setAttribute("memberVO", memberVO);
			session.setAttribute("isLogin", "correct");

			String location = (String) session.getAttribute("location");
			System.out.println("location" + location);
			if (location != null) {
				res.sendRedirect(location);
				return;
			} else {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
				return;
			}
		}

		// ==========================================登出===========================================================================
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.setAttribute("memberVO", null);
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
		// ======================================================================================================================================================

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			String url = "/back/member/listAllMember1.jsp";
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String memId = req.getParameter("memId");
				if (memId == null || (memId.trim()).length() == 0) {
					errorMsgs.add("請勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMemById(memId);
				if (memberVO == null) {
					errorMsgs.add("查無帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				/***************************************/
				List<MemberVO> list = new ArrayList<MemberVO>();
				list.add(memberVO);
				req.setAttribute("list", list); //

				RequestDispatcher successView = req.getRequestDispatcher(url);
				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		// ======================================================================================================================

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String memNo = req.getParameter("memNo");

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMemByNo(memNo);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/member/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		// ====================================================================================================================

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MemberService memSvc = new MemberService();
			String url = "/front/member/MemberInfo.jsp";
//			try {

				String memNo = req.getParameter("memNo");
				String memName = req.getParameter("memName");
				String memPwd = req.getParameter("memPwd");
				String memPhone = req.getParameter("memPhone");
				String memGender = req.getParameter("memGender");
				String memEmail = req.getParameter("memEmail");
				String address = req.getParameter("address");

				MemberVO memberVO = memSvc.getOneMemByNo(memNo);

				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				memberVO.setMemName(memName);
				
				if (memPwd != null && memPwd.trim().length() != 0) {
					memberVO.setMemPwd(memPwd);
				}

				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				memberVO.setMemPhone(memPhone);

				if (memGender != null) {
					memberVO.setMemGender(memGender);
				}

				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				memberVO.setMemEmail(memEmail);

				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				memberVO.setAddress(address);

				/*************************** 2.�}�l�ק��� *****************************************/

				memberVO = memSvc.updateMember(memberVO);
				/****************************************************************************/
				System.out.println("Update Member Success");
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("錯誤訊息:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(url);
//				failureView.forward(req, res);
//			}
		}

		// ======================================================================================================================

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String url = "/front/member/MemberRegister.jsp";
			// =======================================================================
			String memName = req.getParameter("memName");
			String memId = req.getParameter("memId");
			String memPwd = req.getParameter("memPwd");
			String memPhone = req.getParameter("memPhone");
			String memGender = req.getParameter("memGender");
			String memEmail = req.getParameter("memEmail");
			String address = req.getParameter("address");
			Integer point = new Integer(0);
			// =======================================================================
			String memSratus = "未驗證";
			String chkIp = req.getLocalAddr();
			Timestamp memLoginTime = new Timestamp(System.currentTimeMillis());

			MemberVO memberVO = new MemberVO();

			if (memName == null || memName.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setMemName(memName);

			if (memId == null || memId.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setMemId(memId);

			if (memPwd == null || memPwd.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setMemPwd(memPwd);

			if (memPhone == null || memPhone.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setMemPhone(memPhone);

			if (memGender == null || memGender.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setMemGender(memGender);

			if (memEmail == null || memEmail.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setMemEmail(memEmail);

			if (address == null || address.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			memberVO.setAddress(address);

			memberVO.setPoint(point);
			memberVO.setMemSratus(memSratus);
			memberVO.setChkIp(chkIp);
			memberVO.setMemLoginTime(memLoginTime);

			/*************************** 2.�}�l�s�W��� ***************************************/
			MemberService memSvc = new MemberService();
			memberVO = memSvc.addMember(memName, memPhone, memGender, memEmail, address, point, memId, memPwd,
					memSratus, chkIp, memLoginTime);
			/***************************
			 * 3.�s�W����,�ǳ����(Send the Success view)
			 ***********/
			memberVO = memSvc.getOneMemById(memberVO.getMemId());
			req.getSession().setAttribute("memberVO", memberVO);
			String successUrl = "/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(successUrl);
			successView.forward(req, res);
			System.out.println("成功");
			/*************************** ��L�i�઺���~�B�z **********************************/

		}
		// ======================================================================================================================

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String memNo = req.getParameter("memNo");

				/*************************** 2.�}�l�R����� ***************************************/
				MemberService empSvc = new MemberService();
				empSvc.deleteEmp(memNo);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/front/member/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/member/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		// ======================================================================================================================

		if ("queryStatus".equals(action)) {
			String status = req.getParameter("status");
			System.out.println(status);
			String param = null;
			String url = "/back/member/listAllMember1.jsp";
			MemberService memberSvc = new MemberService();
			List<MemberVO> list = new ArrayList<>();

			if ("verification".equals(status)) {
				list = memberSvc.getStatus("已驗證");
				param = "verification";
			} else if ("unverified".equals(status)) {
				list = memberSvc.getStatus("未驗證");
				param = "unverified";
			} else if ("suspension".equals(status)) {
				list = memberSvc.getStatus("已停權");
				param = "suspension";
			} else {
				list = memberSvc.getAll();
				param = "getAll";
			}
			if (!list.isEmpty()) {
				req.setAttribute("list", list);
				req.setAttribute("status", param);
				RequestDispatcher dis = req.getRequestDispatcher(url);
				dis.forward(req, res);
			}
		}

		// ======================================================================================================================
		if ("updateFromBack".equals(action)) {
			System.out.println("123");
			String memNo = req.getParameter("memNo");
			String memSratus = req.getParameter("memSratus");
			List<MemberVO> list = new ArrayList<>();

			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMemByNo(memNo);
			memberVO.setMemSratus(memSratus);
			memSvc.updateMember(memberVO);
			System.out.println("後台成功修改會員狀態");
			list.add(memberVO);
			req.setAttribute("list", list);
			RequestDispatcher successView = req.getRequestDispatcher("/back/member/listAllMember1.jsp");
			successView.forward(req, res);
		}
		// ==================================================忘記密碼=============================================================
		if ("forgetPwd".equals(action)) {
			String memNo = req.getParameter("memNo");
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.getOneMemByNo(memNo);
			sendRegisterMail(memVO.getMemEmail(),memVO.getMemName(),memVO.getMemPwd());
			RequestDispatcher successView = req.getRequestDispatcher("/back/employee/listOneEmployee.jsp");
			successView.forward(req, res);
		}
		// ======================================================================================================================
	}
	
	
	private void sendRegisterMail(String Email, String Name, String Pwd) {
		String to = Email;
		String subject = "密碼通知";
		String messageText = "Hello! " + Name + " 請謹記此密碼: " + Pwd + "\n" + " (已經啟用)";
		MailService mailService = new MailService();
		mailService.sendMail(to, subject, messageText);
	}
}

