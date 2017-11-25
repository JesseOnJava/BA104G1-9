package com.expertlist.model;

import java.sql.Date;
import java.util.List;
import com.expertlist.model.*;

public class ExpertlistService {

	private ExpertlistDAO_interface dao;

	public ExpertlistService() {
		dao = new ExpertlistJDBCDAO();
	}
	public ExpertlistVO addEXPERTLIST(ExpertlistVO expertlistVO) {

		dao.insert(expertlistVO);

		return expertlistVO;
	}

	public ExpertlistVO addEXPERTLIST(
			String expNo, 
			String expName, 
			String expSpec,
			Integer expPrice) {

		ExpertlistVO expertlistVO = new ExpertlistVO();
		
		expertlistVO.setExpNo(expNo);
		expertlistVO.setExpName(expName);
		expertlistVO.setExpSpec(expSpec);
		dao.insert(expertlistVO);

		return expertlistVO;
	}

	public ExpertlistVO updateEXPLIST(ExpertlistVO expertlistVO ) {


		dao.update(expertlistVO);

		return expertlistVO;
	}
//	public  ExpertlistVO updateEXPLIST(ExpertlistVO expertlistVO ) {

//		ExpertlistVO expertlistVO = new ExpertlistVO();
//		expertlistVO.setExpName(expName);
//		expertlistVO.setExpSpec(expSpec);
//		expertlistVO.setExpPrice(expPrice);
//		expertlistVO.setExpNo(expNo);
//		dao.update(expName, expSpec,  expPrice,  expNo);

//	return expertlistVO;
//	}

	public void deleteTHECARED(String expNo) {
		dao.delete(expNo);
	}

	public ExpertlistVO getOneTHECARED(String expNo) {
		return dao.findByPrimaryKey(expNo);
	}

	public List<ExpertlistVO> getAll() {
		return dao.getAll();
	}
}
