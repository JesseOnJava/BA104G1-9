package com.hcorder.modal;

import java.util.List;

public class HcOrderDetailService {
	private HcOrderDetailDAO_interface dao;
	
	public HcOrderDetailService(){
		dao = new HcOrderDetailDAO();
	}
	
	public  List<HcOrderDetailVO> getAllByOrderNo(String orderNo){
		List<HcOrderDetailVO> HcOrderDetailVOList = dao.getAllByOrderNo(orderNo);
		return HcOrderDetailVOList;
	}

}
