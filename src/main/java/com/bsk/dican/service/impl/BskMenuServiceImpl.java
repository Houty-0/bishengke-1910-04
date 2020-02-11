package com.bsk.dican.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsk.dican.dao.BskMenuDao;
import com.bsk.dican.entity.BskMenu;
import com.bsk.dican.service.BskMenuService;

@Service
public class BskMenuServiceImpl implements BskMenuService{

	@Autowired
	private BskMenuDao bskMenuDao;
	
	@Override
	public List<BskMenu> findObjets() {
		List<BskMenu> list = bskMenuDao.findObjects();
		return list;
	}

	
}
