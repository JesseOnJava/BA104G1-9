package com.balance.model;

import java.util.List;


public interface BalanceDAO_interface {

    public void insert(BalanceVO balanceVO);
    public void update(BalanceVO balanceVO);
    public void delete(String topupNo);
    public BalanceVO findByPrimaryKey(String etopupNo);
    public List<BalanceVO> getAll();

}
