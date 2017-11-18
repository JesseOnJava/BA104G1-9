package com.detail.cart;

public class CartService {

	
	private CartDAO_Interface dao;
	
	public CartService() {
		dao=new CartDAO();
	}
	//坝珇琩高
	public CartVO findshop(Integer number,Integer quantity) {
		return dao.findshop(number,quantity);
	}
	//玃綪笆坝珇琩高
	public CartVO findshopbypro(Integer number,Integer quantity) {
		System.out.println("秈CartService.findshopbypro");
		System.out.println("肚把计number quantity"+number+","+quantity);
		return dao.findshopbypro(number,quantity);
	}
}
