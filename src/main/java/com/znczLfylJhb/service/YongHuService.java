package com.znczLfylJhb.service;

import java.util.List;

import com.znczLfylJhb.entity.*;

public interface YongHuService {
	
	int add(YongHu yh);

	int edit(YongHu yh);

	int queryForInt(String yhm,Boolean check);

	List<YongHu> queryList(String yhm, Boolean check, int page, int rows, String sort, String order);

	YongHu selectById(String id);

	int checkByIds(Boolean check, String ids);
}
