package com.shop.model;

import java.util.List;

public interface ShopDAO_interface {

	public void insert(ShopVO ShopVO);
	public void update(ShopVO ShopVO);
	public void update2(ShopVO ShopVO);
	public void delete(Integer ShopVO);
	public void changeState(Integer pk,Integer state);
	public ShopVO findByPrimaryKey(Integer pk);
	public ShopVO findByPrimaryKeyByString(String pkName);
	
	//�ӫ������M�ΡA�u��state=1�~�|�q�X��
	public List<ShopVO> getAll();
	//��ݺ޲z�ӫ~�M��
	public List<ShopVO> getAllFromBack();
	
	
}
