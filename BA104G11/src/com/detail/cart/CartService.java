package com.detail.cart;

public class CartService {

	
	private CartDAO_Interface dao;
	
	public CartService() {
		dao=new CartDAO();
	}
	//�@��ӫ~�d��
	public CartVO findshop(Integer number,Integer quantity) {
		return dao.findshop(number,quantity);
	}
	//�P�P���ʰӫ~�d��
	public CartVO findshopbypro(Integer number,Integer quantity) {
		System.out.println("�i�JCartService.findshopbypro");
		System.out.println("�ǤJ�Ѽ�number quantity"+number+","+quantity);
		return dao.findshopbypro(number,quantity);
	}
}
