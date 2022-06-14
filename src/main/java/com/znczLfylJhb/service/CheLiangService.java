package com.znczLfylJhb.service;

import java.util.List;

import com.znczLfylJhb.entity.*;

public interface CheLiangService {

	int queryForInt(String cph);

	List<CheLiang> queryList(String cph, int page, int rows, String sort, String order);

	int updateWcddcsByCph(String cph);

}
