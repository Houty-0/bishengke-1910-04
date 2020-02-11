package com.bsk.dican.service;

import java.util.List;

import com.bsk.dican.entity.BskGood;

public interface BskGoodService {

	/**
	 * 1.根据菜单id查询对应的所有商品信息
	 * @return
	 */
	List<BskGood> findObjects(Integer menuId);
}
