package com.znczLfylJhb.service;

import java.util.List;

import com.znczLfylJhb.entity.*;

public interface CheLiangService {

	int queryForInt(Integer lx, String cph);

	List<CheLiang> queryList(Integer lx, String cph, int page, int rows, String sort, String order);

	int updateWcddcsByCph(String cph);

	boolean checkIfExistByCph(String cph);

	int add(CheLiang cl);

	int edit(CheLiang cl);

	int getIdByCph(String cph);

	CheLiang selectById(String id);

}
