package com.znczLfylJhb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczLfylJhb.entity.*;

public interface CheLiangMapper {

	int add(CheLiang cl);

	int edit(CheLiang cl);

	int queryForInt(@Param("cph") String cph, @Param("lx") Integer lx, @Param("yssMc") String yssMc, @Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc, @Param("wzlxMc") String wzlxMc, @Param("wzMc") String wzMc);

	List<CheLiang> queryList(@Param("cph") String cph, @Param("lx") Integer lx, @Param("yssMc") String yssMc, @Param("fhdwMc") String fhdwMc,
			@Param("shbmMc") String shbmMc, @Param("wzlxMc") String wzlxMc, @Param("wzMc") String wzMc, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int updateWcddcsByCph(@Param("cph") String cph);

	Integer getWcddcsByCphLx(@Param("cph") String cph, @Param("lx") Integer lx);

	int updateLxByCph(@Param("lx") Integer lx, @Param("cph") String cph);

	int getCountByCph(@Param("cph") String cph);

	int getIdByCph(@Param("cph") String cph);

	CheLiang selectById(@Param("id") String id);

}
