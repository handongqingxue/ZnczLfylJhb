package com.znczLfylJhb.service;

import java.util.List;

import com.znczLfylJhb.entity.*;

public interface DingDanService {

	int queryZHCXForInt(String ddh, Integer ddztId, String ddztMc, String cph, String yssMc, String wzMc, 
			String fhdwMc, String shbmMc);

	List<DingDan> queryZHCXList(String ddh, Integer ddztId, String ddztMc, String cph, String yssMc, String wzMc, 
			String fhdwMc, String shbmMc, int page, int rows, String sort, String order);

	DingDan selectById(String id);

	int add(DingDan dd);

	int deleteByIds(String ids);

	int edit(DingDan dd);

	int editByZt(DingDan dd);

	DingDan getDingDan(String cph, String ddztMc);

	DingDan getByZt(Integer yjbfh, Integer ejbfh, String ddztMc, Integer yjzt, Integer ejzt);

	DingDan getPDZByCph(String cph);

	boolean checkDdhIfExist(String ddh);

	boolean checkIfExistByZt(Integer yjbfh,Integer ejbfh,String ddztMc, Integer yjzt, Integer ejzt);

	DingDan getByCphJL(String cph);

	int getIdByDdh(String ddh);

	boolean checkIfExistByIdCph(Integer id, String cph);

	String createDdhByDateYMD();

	String getCphByBfhDdzt(Integer bfh, String ddztMc);

	boolean checkIfCPSB(String cph);

}