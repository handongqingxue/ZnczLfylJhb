package com.znczLfylJhb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczLfylJhb.entity.*;

public interface CheLiangMapper {

	int queryForInt(String cph);

	List<CheLiang> queryList(@Param("cph") String cph, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

}
