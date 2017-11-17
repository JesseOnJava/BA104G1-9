package com.hcworkshifts.model;

import java.util.List;

public class HcWorkShiftsService {
	private HcWorkShiftsDAO_interface dao;
	public HcWorkShiftsService(){
		dao = new HcWorkShiftsJDBCDAO();
	}
	
	public HcWorkShiftsVO addHcWorkShifts (String monthOfYear,String empNo,
			String workShiftStatus,Integer totalWorkShifts){
		HcWorkShiftsVO hcWorkShifts = new HcWorkShiftsVO();
		hcWorkShifts.setMonthOfYear(monthOfYear);
		hcWorkShifts.setEmpNo(empNo);
		hcWorkShifts.setWorkShiftStatus(workShiftStatus);
		hcWorkShifts.setTotalWorkShifts(totalWorkShifts);
		dao.insert(hcWorkShifts);
		return hcWorkShifts;		
	}
	
	public HcWorkShiftsVO updateHcWorkShifts (String monthOfYear,String empNo,
			String workShiftStatus){
		HcWorkShiftsVO hcWorkShifts = new HcWorkShiftsVO();
		hcWorkShifts.setMonthOfYear(monthOfYear);
		hcWorkShifts.setEmpNo(empNo);
		hcWorkShifts.setWorkShiftStatus(workShiftStatus);
		hcWorkShifts.setTotalWorkShifts(countWorkShifts(workShiftStatus));
		dao.update(hcWorkShifts);
		
		return hcWorkShifts;
	}
	
	public HcWorkShiftsVO getOne(String monthOfYear,String empNo){
		return dao.findByPrimarykey(monthOfYear, empNo);

	}
	
	public List<HcWorkShiftsVO> getAll(){
		return dao.getAll();
	}
	
	public List<HcWorkShiftsVO> getAllByDateTime(String servdate, String servTime){
		String[] servdates = convertDateToNumber(servdate,servTime);
		return dao.getAllByDateTime(servdates[0], servdates[1]);
	}
	
	static int countWorkShifts(String workShiftStr){
		int count = 0;
		for(int i=0;i<workShiftStr.length();i++){
			//day mid night
			if(workShiftStr.charAt(i) == '\u65e9'|| workShiftStr.charAt(i) =='\u4e2d'|| workShiftStr.charAt(i) =='\u665a'){
				count++;
			}
		}		
		return count;		
	}
	
	static String[] convertDateToNumber(String servdate,String servTime){
		String[] listS = new String[2];
		//轉換成10611格式
		String monthOfYear = (Integer.valueOf(servdate.substring(0,4))-1911)+servdate.substring(5,7);
		listS[0] = monthOfYear;
		//轉換成當月班次
		int shiftNumber = (Integer.valueOf(servdate.substring(8,10))*3);
		if(servTime.equals("\u65e9")){
			shiftNumber = shiftNumber-2;
		}else if(servTime.equals("\u4e2d")){
			shiftNumber = shiftNumber-1;
		}
//		else if(time.equals("\u665a")){
//			shiftNumber = shiftNumber;
//		}
		listS[1] = String.valueOf(shiftNumber);
		return listS;
	}

}
