package com.bsk.dican.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bsk.dican.entity.BskMenu;

@Mapper
public interface BskMenuDao {

	/**
	 * 1.查询所有菜单
	 * @return
	 */
	List<BskMenu> findObjects();
}
