package com.bsk.dican.dao;

import com.bsk.dican.entity.BskUserMessage;
import org.apache.ibatis.annotations.Mapper;

import com.bsk.dican.entity.BskUser;

@Mapper
public interface BskUserDao {

	/**
	 * 1.用户注册
	 * @param bskUser
	 * @return
	 */
	int insertObject(BskUser bskUser);
	
	/**
	 * 2.根据手机号查询用户
	 * @param
	 * @return
	 */
	BskUser findObjectByPhone(String phone);
	
	/**
	 * 3.根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	BskUserMessage findUserMessageByUserId(Integer userId);
}
