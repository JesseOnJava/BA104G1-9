package com.shopcomplain.model;

import java.sql.Timestamp;
import java.util.List;

public class ShopComplainService {
	
	public ShopComplainDAO_interface dao;
	
	public ShopComplainService(){
		dao = new ShopComplainJDBCDAO();
	}
	
	public ShopComplainVO addShopCom(String orderno, String complainDetail, String complainStatus){
		ShopComplainVO shopComplainVO = new ShopComplainVO();
		shopComplainVO.setOrderno(orderno);
		shopComplainVO.setComplainDetail(complainDetail);
		shopComplainVO.setComplainStatus(complainStatus);
		dao.insert(shopComplainVO);
		return shopComplainVO;
	}
	
	
	public ShopComplainVO updateShopCom(String complainNo, String complainReply, Timestamp replyDate ,String emp_no, String complainStatus){
		ShopComplainVO shopComplainVO = new ShopComplainVO();
		shopComplainVO.setComplainNo(complainNo);
		shopComplainVO.setComplainReply(complainReply);
		shopComplainVO.setReplyDate(replyDate);
		shopComplainVO.setEmp_no(emp_no);
		shopComplainVO.setComplainStatus(complainStatus);
		dao.update(shopComplainVO);
		return shopComplainVO;
	}
	
	public void deleteShopCom(String complainNo){
		dao.delete(complainNo);
	}
	
	public ShopComplainVO getOneShopCom(String complainNo){
		return dao.findByPrimaryKey(complainNo);
	}
	
	public List<ShopComplainVO> getAll(){
		return dao.getAll();
	}
	
	
}
