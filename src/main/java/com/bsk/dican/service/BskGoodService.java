package com.bsk.dican.service;

import java.util.List;

import com.bsk.dican.entity.BskGood;

public interface BskGoodService {

	/**
	 * 1.根据菜单id查询对应的所有商品信息
	 * @return
	 */
	List<BskGood> findObjects(Integer menuId);
	
	/**
	 * 2.根据id查询商品信息
	 * @param id
	 * @return
	 */
	BskGood findObjectById(Integer id);
}
