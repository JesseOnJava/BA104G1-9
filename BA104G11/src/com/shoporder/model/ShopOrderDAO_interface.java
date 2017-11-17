package com.shoporder.model;

import java.util.List;

public interface ShopOrderDAO_interface {

	//���o�q��s���̪��Ҧ��ӫ~
	public List<ShopOrderVO> getAllByOrderNo(String orderno);
	//���o�q�ʸӰӫ~�s�����Ҧ��H����
	public List<ShopOrderVO> getOneByItemNo(Integer pk);
	//���o�ӭq��s�����Ҧ��ӫ~
	public List<ShopOrderVO> getPriceByOrderNo(String orderno);
	//���o�ӭq��s�����P�P���ӫ~
	public List<ShopOrderVO> getPriceByOrderNoIfHave(String orderno);
	//���o�ӷ|�����ʶR���ӫ~
	public List<ShopOrderVO> getAllByMenNO(String menno);
	//���o�Ҧ����q��&�������|��
	public List<ShopOrderVO> getAllOrder();
	//�s�W�ӫ~�Ω���
	public void addShopOrder(ShopOrderVO shoporderVO);
	//�ʪ����ӫ~�妸�s�W
	public void addShopCartOrder(List<ShopOrderVO> shoporderVO);
	//��s�q��
	public List<ShopOrderVO> updateShopOrder(List<ShopOrderVO> shoporderVO,Integer finalTotal);
	//��ITEMNO&ORDERID�R��
	public void delete(String orderno,String memno);
	//�^���|�����x���I��
	public Integer returnPoint(String memno);
	//�����|���I��
	public Integer returnAfterShoppingPoint(Integer total,String memno);
	//�R���q�����ٷ|���I��  //�^�ǳѾl�I��
	public Integer returnPointback(String Orderno);
	
	public ShopOrderVO findByPrimaryKey(String orderno);
	
}
