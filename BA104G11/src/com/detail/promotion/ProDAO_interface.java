package com.detail.promotion;

import java.util.List;

import com.shop.model.ShopVO;

public interface ProDAO_interface {
	
	public void insert(ProVO proVO);
	public void update(ProVO proVO);	
	public void delete(Integer proVO);
	//�A�n�A�ڬO�d�ߩҦ����b�i�檺�P�P�ӫ~
	public List<ProVO> getAllProNow();	
	//�A�n�A�ڬOmainforpro�M�Ϊ�
	public List<ProVO> activity();
	//�A�n�A�ڬOupdate�M�Ϊ�
	public ProVO findByPrimaryKey(Integer proVO);
	//�A�n�A�ڬO�ΰӫ~�W�٥h�d�ߪ�
	public ProVO getOneEmpForStringName(String proVO);
	//�A�n�A�ڬO�αM�צW�٥h�d�ߪ�(�ҥH�|�Ǧ^�ܦh��)
	public List<ProVO> findByPrimaryKeyByString(String pk);
	//�A�n�A�ڬO�αM�׽s���h�d�ߪ�
	public ProVO getOneOnItemNO(int itemno);
	//�A�n�A�ڬO�d�ߩҦ����P�P�M��
	public List<ProVO> getAllPro();
	//�A�n�A�ڬO�d�ߩҦ��P�P�ӫ~
	public List<ProVO> getAllProShop();
	//�A�n�A�ڬO�d�߳�@�P�P�ӫ~
	public ProVO getOneProShop(Integer itemno);
	
	
	
}
