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
}
