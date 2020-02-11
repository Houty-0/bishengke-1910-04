package com.bsk.dican.service;

import java.util.List;

import com.bsk.dican.entity.BskMenu;

public interface BskMenuService {

	/**
	 * 1.查询所有菜单
	 * @return
	 */
	List<BskMenu> findObjets();
}
