package com.detail.promotion;

import java.sql.Date;
import java.util.List;

import com.shop.model.ShopVO;



public class ProService {

	private ProDAO_interface dao;

	public ProService() {
		dao = new ProDAO();
	}

	
	public ProVO addPro(String NAME,Date BEGINDATE,Date ENDDATE) {
		
		ProVO proVO = new ProVO();
		proVO.setNAME(NAME);
		proVO.setBEGINDATE(BEGINDATE);
		proVO.setENDDATE(ENDDATE);
		dao.insert(proVO);
		return proVO;
	}

	public ProVO updateEmp(String NAME,int prono,int price, int itemno,Date BEGINDATE,Date ENDDATE) {
		ProVO proVO = new ProVO();
		proVO.setPROMOTIOMNO(prono);
		proVO.setPRICE(price);
		proVO.setITEMNO(itemno);
		proVO.setBEGINDATE(BEGINDATE);
		proVO.setENDDATE(ENDDATE);
		proVO.setNAME(NAME);
		dao.update(proVO);

		return proVO;
	}
	
	
	public void deleteEmp(Integer proVO) {
		dao.delete(proVO);
	}

	//�A�n�A�ڬOupdate�M�Ϊ�
	public ProVO getOneEmp(Integer proVO) {
		return dao.findByPrimaryKey(proVO);
	}
	
	//�A�n�A�ڬO�αM�׽s���h�d�ߪ�
	public ProVO getOneOnItemNO(int itemno){
		return dao.getOneOnItemNO(itemno);
	}
	//�A�n�A�ڬO�ΰӫ~�W�٥h�d�ߪ�
	public ProVO getOneEmpForStringName(String actName){
		return dao.getOneEmpForStringName(actName);
	}
	//�A�n�A�ڬO�αM�צW�٥h�d�ߪ�(�ҥH�|�Ǧ^�ܦh��)
	public List<ProVO> getOneEmpbyString(String pk) {
		return  dao.findByPrimaryKeyByString(pk);
	}
	//�A�n�A�ڬO�@�����������P�P�ӫ~
	public List<ProVO> getAllProNow() {
		return dao.getAllProNow();
	}
	
	//�A�n�A�ڬO�@�����������P�P�M��
	public List<ProVO> getAllPro() {
		return dao.getAllPro();
	}
	//�A�n�A�ڬO�@�����������P�P�ӫ~
	public List<ProVO> getAllProShop() {
		return dao.getAllProShop();
	}
	
}


