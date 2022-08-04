package com.znczLfylJhb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;

import com.znczLfylJhb.entity.*;

public interface GuoBangJiLuMapper {

	int add(GuoBangJiLu gbjl);

	int edit(GuoBangJiLu gbjl);

	int queryForInt(@Param("ddh") String ddh, @Param("cph") String cph, @Param("gbsjks") String gbsjks, @Param("gbsjjs") String gbsjjs);

	List<GuoBangJiLu> queryList(@Param("ddh") String ddh, @Param("cph") String cph, @Param("gbsjks") String gbsjks, @Param("gbsjjs") String gbsjjs, 
			@Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);
	
	GuoBangJiLu selectById(@Param("id") String id);

	GuoBangJiLu selectPrintInfo(@Param("ddId") Integer ddId, @Param("gblx") Integer gblx);

	int deleteByDdId(@Param("gblx") Integer gblx, @Param("ddId") Integer ddId);

	int getCountByDdId(@Param("gblx") Integer gblx, @Param("ddId") Integer ddId);

	GuoBangJiLu getLastByCph(@Param("cph") String cph);
}
