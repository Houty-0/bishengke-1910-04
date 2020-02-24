package com.bsk.dican.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bsk.dican.entity.BskGood;

@Mapper
public interface BskGoodDao {

	/**
	 * 1.查询所有商品信息
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
