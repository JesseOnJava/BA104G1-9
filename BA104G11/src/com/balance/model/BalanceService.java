package com.balance.model;

import java.sql.Date;
import java.util.List;
import com.balance.model.*;

public class BalanceService {

	private BalanceDAO_interface dao;

	public BalanceService() {
		dao = new BalanceDAO();
	}
	private String topupNo;
	private String memNo;
	private Integer topupValue;
	private Date topupTime;
	private String status;
	
	public BalanceVO addTopup(
			String topupNo,
			String memNo,
			java.sql.Date topupTime,
			String status ) {

		BalanceVO balanceVO = new BalanceVO();

		balanceVO.setTopupNo(topupNo);
		balanceVO.setMemNo(memNo);
		balanceVO.setTopupValue(topupValue);
		balanceVO.setTopupTime(topupTime);
		balanceVO.setStatus(status);
		dao.insert(balanceVO);

		return balanceVO;
	}

	public BalanceVO updateEmp(Integer topupValue, String status) {

		BalanceVO balanceVO = new BalanceVO();
		balanceVO.setTopupValue(topupValue);
		balanceVO.setStatus(status);
		dao.update(balanceVO);

		return balanceVO;
	}

	public void deleteEmp(String topupNo) {
		dao.delete(topupNo);
	}

	public BalanceVO getOneEmp(String topupNo) {
		return dao.findByPrimaryKey(topupNo);
	}

	public List<BalanceVO> getAll() {
		return dao.getAll();
	}
}
