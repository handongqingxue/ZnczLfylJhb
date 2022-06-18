package com.znczLfylJhb.service;

import java.util.List;

import com.znczLfylJhb.entity.*;

public interface CheLiangService {

	int queryForInt(String cph, Integer lx, String yssMc, String fhdwMc, String shbmMc, String wzlxMc, String wzMc);

	List<CheLiang> queryList(String cph, Integer lx, String yssMc, String fhdwMc,
			String shbmMc, String wzlxMc, String wzMc, int page, int rows, String sort, String order);

	int updateWcddcsByCph(String cph);

	boolean checkIfExistByCph(String cph);

	int add(CheLiang cl);

	int edit(CheLiang cl);

	int getIdByCph(String cph);

	CheLiang selectById(String id);

	boolean checkCphIfExist(String cph);

}
