package com.znczLfylJhb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczLfylJhb.entity.*;

public interface CheLiangMapper {

	int add(CheLiang cl);

	int queryForInt(@Param("lx") Integer lx, @Param("cph") String cph);

	List<CheLiang> queryList(@Param("lx") Integer lx, @Param("cph") String cph, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int updateWcddcsByCph(@Param("cph") String cph);

	Integer getWcddcsByCphLx(@Param("cph") String cph, @Param("lx") Integer lx);

	int updateLxByCph(@Param("lx") Integer lx, @Param("cph") String cph);

	int getCountByCph(@Param("cph") String cph);

	int getIdByCph(@Param("cph") String cph);

}
