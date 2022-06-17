package com.znczLfylJhb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.znczLfylJhb.entity.*;

public interface DingDanMapper {

	int queryZHCXForInt(@Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("ddztMc") String ddztMc, @Param("cph") String cph, @Param("yssMc") String yssMc, @Param("wzMc") String wzMc, 
			@Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc);

	List<DingDan> queryZHCXList(@Param("ddh") String ddh, @Param("ddztId") Integer ddztId, @Param("ddztMc") String ddztMc, @Param("cph") String cph, @Param("yssMc") String yssMc, @Param("wzMc") String wzMc, 
			@Param("fhdwMc") String fhdwMc, @Param("shbmMc") String shbmMc, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	DingDan selectById(@Param("id") String id);

	int add(DingDan dd);

	int deleteByIds(List<String> idList);

	int edit(DingDan dd);

	int editByZt(DingDan dd);

	DingDan getByZtMcCph(@Param("ddztMc") String ddztMc, @Param("cph") String cph);

	DingDan getByZt(@Param("yjbfh")Integer yjbfh,@Param("ejbfh") Integer ejbfh, @Param("ddztMc") String ddztMc, @Param("yjzt") Integer yjzt, @Param("ejzt") Integer ejzt);

	DingDan getPDZByCph(@Param("cph") String cph,@Param("ztMc1") String ztMc1,@Param("ztMc2") String ztMc2);

	int getCountByDdh(@Param("ddh") String ddh);

	int getCountByZt(@Param("yjbfh") Integer yjbfh,@Param("ejbfh") Integer ejbfh,@Param("ddztMc") String ddztMc, @Param("yjzt") Integer yjzt, @Param("ejzt") Integer ejzt);

	DingDan getByCphJL(@Param("cph") String cph);

	int getIdByDdh(@Param("ddh") String ddh);

	int getCountByIdCph(@Param("id") Integer id, @Param("cph") String cph);

	int getCountByDdhDate(@Param("ddhDate") String ddhDate);

	String getCphByBfhDdzt(@Param("bfh") Integer bfh, @Param("ddztMc") String ddztMc);

}