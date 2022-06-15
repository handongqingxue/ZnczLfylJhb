package com.znczLfylJhb.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znczLfylJhb.dao.*;
import com.znczLfylJhb.entity.*;
import com.znczLfylJhb.service.*;

@Service
public class CheLiangServiceImpl implements CheLiangService {

	@Autowired
	private CheLiangMapper cheLiangDao;

	@Override
	public int queryForInt(String cph) {
		// TODO Auto-generated method stub
		return cheLiangDao.queryForInt(cph);
	}

	@Override
	public List<CheLiang> queryList(String cph, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return cheLiangDao.queryList(cph, (page-1)*rows, rows, sort, order);
	}

	@Override
	public int updateWcddcsByCph(String cph) {
		// TODO Auto-generated method stub
		int count=cheLiangDao.updateWcddcsByCph(cph);
		Integer wcddcs=cheLiangDao.getWcddcsByCphLx(cph,CheLiang.MO_SHENG_CHE_LIANG);
		if(wcddcs!=null) {
			if(wcddcs>=CheLiang.DSBCLBZZ) {
				cheLiangDao.updateLxByCph(CheLiang.DAI_SHI_BIE_CHE_LIANG,cph);
			}
		}
		return count;
	}

	@Override
	public boolean checkIfExistByCph(String cph) {
		// TODO Auto-generated method stub
		int count=cheLiangDao.getCountByCph(cph);
		return count>0?true:false;
	}

	@Override
	public int add(CheLiang cl) {
		// TODO Auto-generated method stub
		return cheLiangDao.add(cl);
	}

	@Override
	public int getIdByCph(String cph) {
		// TODO Auto-generated method stub
		return cheLiangDao.getIdByCph(cph);
	}
}
