package com.thecared.model;


import java.util.List;


public class ThecaredService {

	private ThecaredDAO_interface dao;

	public ThecaredService() {
		dao = new ThecaredDAO();
	}

	public ThecaredVO addTHECARED(
			String mem_no, 
			String cared_no, 
			String cared_gender,
			String kinship,
			Integer cared_height,
			Integer cared_weight,
			String cared_address,
			String cared_phone,
			String con_status,
			String bio_status,
			java.sql.Date modify_time) {

		ThecaredVO thecaredVO = new ThecaredVO();
		
		thecaredVO.setMemNo(mem_no);
		thecaredVO.setCaredNo(cared_no);
		thecaredVO.setCaredGender(cared_gender);
		thecaredVO.setKinship(kinship);
		thecaredVO.setCaredHeight(cared_height);
		thecaredVO.setCaredWeight(cared_weight);
		thecaredVO.setCaredAddress(cared_address);
		thecaredVO.setCaredPhone(cared_phone);
		thecaredVO.setConStatus(con_status);
		thecaredVO.setBioStatus(bio_status);
		thecaredVO.setModifyTime(modify_time);
		
		dao.insert(thecaredVO);

		return thecaredVO;
	}

	
	public ThecaredVO updateTHECARED(
			String cared_name,
			Integer cared_weight,
			String cared_address,
			String cared_phone,
			String con_status,
			String bio_status
			) {

		ThecaredVO thecaredVO = new ThecaredVO();
		thecaredVO.setCaredName(cared_name);
		thecaredVO.setCaredWeight(cared_weight);
		thecaredVO.setCaredAddress(cared_address);
		thecaredVO.setCaredPhone(cared_phone);
		thecaredVO.setConStatus(con_status);
		thecaredVO.setBioStatus(bio_status);
		dao.update(thecaredVO);

		return thecaredVO;
	}

	public void deleteTHECARED(String cared_no) {
		dao.delete(cared_no);
	}

	public ThecaredVO getOneTHECARED(String cared_no) {
		return dao.findByPrimaryKey(cared_no);
	}
	
	public List<ThecaredVO> getAllByMemNo(String MemNo) {
		return dao.getAllByMemNo(MemNo);
	}

	public List<ThecaredVO> getAll() {
		return dao.getAll();
	}
}
