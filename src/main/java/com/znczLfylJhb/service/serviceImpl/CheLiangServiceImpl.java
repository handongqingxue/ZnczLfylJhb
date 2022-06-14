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
}