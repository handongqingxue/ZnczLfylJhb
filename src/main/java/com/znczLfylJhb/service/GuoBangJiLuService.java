package com.znczLfylJhb.service;

import java.util.List;

import com.znczLfylJhb.entity.*;

public interface GuoBangJiLuService {

	int add(GuoBangJiLu gbjl);

	int edit(GuoBangJiLu gbjl);

	int queryForInt(String ddh, String cph, String gbsjks, String gbsjjs);

	List<GuoBangJiLu> queryList(String ddh, String cph, String gbsjks, String gbsjjs, int page, int rows, String sort, String order);

	GuoBangJiLu selectById(String id);

	GuoBangJiLu selectPrintInfo(Integer ddId, Integer gblx);

	boolean checkIfExistByDdId(Integer gblx, Integer ddId);

	int deleteByDdId(Integer gblx,Integer ddId);

}
