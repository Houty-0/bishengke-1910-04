package com.bsk.dican.service;

import com.bsk.dican.entity.BskUser;
import com.bsk.dican.entity.BskUserMassage;

public interface BskUserService {

	/**
	 * 1.用户注册
	 * @param bskUser
	 * @return
	 */
	void saveObject(BskUser bskUser);
	
	/**
	 * 2.根据手机号查询用户
	 * @param id
	 * @return
	 */
	BskUser findObjectByPhone(String phone); 
	
	/**
	 * 3.根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	BskUserMassage findUserMessageByUserId(Integer userId);
}
