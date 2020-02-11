package com.bsk.dican.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsk.dican.dao.BskGoodDao;
import com.bsk.dican.entity.BskGood;
import com.bsk.dican.service.BskGoodService;

@Service
public class BskGoodServiceImpl implements BskGoodService{

	@Autowired
	private BskGoodDao bskGoodDao;
	
	@Override
	public List<BskGood> findObjects(Integer menuId) {
		List<BskGood> list = bskGoodDao.findObjects(menuId);
		return list;
	}

}
