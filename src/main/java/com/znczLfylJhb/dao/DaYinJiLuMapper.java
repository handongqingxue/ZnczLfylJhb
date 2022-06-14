package com.znczLfylJhb.dao;

import org.apache.ibatis.annotations.Param;

import com.znczLfylJhb.entity.*;

public interface DaYinJiLuMapper {

	int add(DaYinJiLu dyjl);

	DaYinJiLu selectByTime(@Param("time") String time);

}
