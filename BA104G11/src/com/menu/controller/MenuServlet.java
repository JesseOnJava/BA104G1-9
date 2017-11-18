package com.menu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.menu.model.MenuService;
import com.menu.model.MenuVO;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insertMenu".equals(action)) {
			String jsonStr = req.getParameter("test");
			Integer smNo = new Integer(req.getParameter("smNo"));
			System.out.println(jsonStr);
			List<MenuVO> list = new ArrayList<>();
			
			
			MenuService menuSvc = new MenuService();
			menuSvc.getAll(smNo);
			System.out.println(new JSONArray(menuSvc.getAll(smNo)).toString());
			
			
			
			
			
			
			
			
			try {
				JSONArray jsonArray = new JSONArray(jsonStr);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String date = jsonObject.getString("date");
					String time = jsonObject.getString("time");
					String dish = jsonObject.getString("dish");
					MenuVO menuVO = new MenuVO();
					menuVO.setSmNo(smNo);
					menuVO.setMenuDate(java.sql.Date.valueOf(date));
					menuVO.setMealTime(time);
					menuVO.setDishName(dish);
					list.add(menuVO);
				}

				
				//menuSvc.delete(smNo);
//				for (int i = 0; i < list.size(); i++) {
//					menuSvc.addMenu(list.get(i));
//				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
