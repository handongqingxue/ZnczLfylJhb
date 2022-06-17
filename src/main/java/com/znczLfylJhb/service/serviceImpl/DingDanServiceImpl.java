package com.znczLfylJhb.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczLfylJhb.dao.*;
import com.znczLfylJhb.entity.*;
import com.znczLfylJhb.service.*;

@Service
public class DingDanServiceImpl implements DingDanService {

	@Autowired
	private DingDanMapper dingDanDao;
	@Autowired
	private DingDanZhuangTaiMapper dingDanZhuangTaiDao;
	private SimpleDateFormat ddhSdf=new SimpleDateFormat("yyyyMMdd");

	@Override
	public int queryZHCXForInt(String ddh, Integer ddztId, String ddztMc, String cph, String yssMc, String wzMc, 
			String fhdwMc, String shbmMc) {
		// TODO Auto-generated method stub
		return dingDanDao.queryZHCXForInt(ddh,ddztId,ddztMc,cph,yssMc,wzMc,fhdwMc,shbmMc);
	}

	@Override
	public List<DingDan> queryZHCXList(String ddh, Integer ddztId, String ddztMc, String cph, String yssMc, String wzMc, 
			String fhdwMc, String shbmMc, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return dingDanDao.queryZHCXList(ddh, ddztId, ddztMc, cph, yssMc, wzMc, fhdwMc, shbmMc, (page-1)*rows, rows, sort, order);
	}

	@Override
	public DingDan selectById(String id) {
		// TODO Auto-generated method stub
		return dingDanDao.selectById(id);
	}

	@Override
	public int add(DingDan dd) {
		// TODO Auto-generated method stub
		int ddztId=dingDanZhuangTaiDao.getIdByMc(DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT);
		dd.setDdztId(ddztId);
		return dingDanDao.add(dd);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=dingDanDao.deleteByIds(idList);
		return count;
	}

	@Override
	public int edit(DingDan dd) {
		// TODO Auto-generated method stub
		String ddztMc = dd.getDdztMc();
		if(!StringUtils.isEmpty(ddztMc)) {
			int ddztId=dingDanZhuangTaiDao.getIdByMc(ddztMc);
			dd.setDdztId(ddztId);
		}
		return dingDanDao.edit(dd);
	}

	@Override
	public int editByZt(DingDan dd) {
		// TODO Auto-generated method stub
		int ddztId=dingDanZhuangTaiDao.getIdByMc(dd.getDdztMc());
		dd.setDdztId(ddztId);
		String xddztMc = dd.getXddztMc();
		if(!StringUtils.isEmpty(xddztMc)&&!"null".equals(xddztMc)) {
			int xddztId=dingDanZhuangTaiDao.getIdByMc(dd.getXddztMc());
			dd.setXddztId(xddztId);
		}
		return dingDanDao.editByZt(dd);
	}

	@Override
	public DingDan getDingDan(String cph, String ddztMc) {
		// TODO Auto-generated method stub
		DingDan dd = dingDanDao.getByZtMcCph(ddztMc,cph);
		return dd;
	}

	@Override
	public DingDan getByZt(Integer yjbfh,Integer ejbfh,String ddztMc, Integer yjzt, Integer ejzt) {
		// TODO Auto-generated method stub
		return dingDanDao.getByZt(yjbfh,ejbfh,ddztMc, yjzt, ejzt);
	}

	@Override
	public DingDan getPDZByCph(String cph) {
		// TODO Auto-generated method stub
		return dingDanDao.getPDZByCph(cph,DingDanZhuangTai.YI_JIAN_PAI_DUI_ZHONG_TEXT,DingDanZhuangTai.ER_JIAN_PAI_DUI_ZHONG_TEXT);
	}

	@Override
	public boolean checkDdhIfExist(String ddh) {
		// TODO Auto-generated method stub
		int count=dingDanDao.getCountByDdh(ddh);
		return count==0?false:true;
	}

	@Override
	public boolean checkIfExistByZt(Integer yjbfh,Integer ejbfh,String ddztMc, Integer yjzt, Integer ejzt) {
		// TODO Auto-generated method stub
		int count=dingDanDao.getCountByZt(yjbfh,ejbfh,ddztMc, yjzt, ejzt);
		return count==0?false:true;
	}

	@Override
	public DingDan getByCphJL(String cph) {
		// TODO Auto-generated method stub
		return dingDanDao.getByCphJL(cph);
	}

	@Override
	public int getIdByDdh(String ddh) {
		// TODO Auto-generated method stub
		return dingDanDao.getIdByDdh(ddh);
	}

	@Override
	public boolean checkIfExistByIdCph(Integer id, String cph) {
		// TODO Auto-generated method stub
		return dingDanDao.getCountByIdCph(id, cph)==0?false:true;
	}

	@Override
	public String createDdhByDateYMD() {
		// TODO Auto-generated method stub
		String ddhDate = ddhSdf.format(new Date());
		int count=dingDanDao.getCountByDdhDate(ddhDate);
		String ddhxhStr=null;
		int ddhxh=count+1;
		if(ddhxh<10)
			ddhxhStr="00"+ddhxh;
		else if(ddhxh<100)
			ddhxhStr="0"+ddhxh;
		return ddhDate+ddhxhStr;
	}

	@Override
	public String getCphByBfhDdzt(Integer bfh, String ddztMc) {
		// TODO Auto-generated method stub
		return dingDanDao.getCphByBfhDdzt(bfh, ddztMc);
	}
}