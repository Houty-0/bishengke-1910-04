package com.bsk.dican.service.impl;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsk.dican.dao.BskUserDao;
import com.bsk.dican.entity.BskUser;
import com.bsk.dican.entity.BskUserMessage;
import com.bsk.dican.service.BskUserService;

@Service
public class BskUserServiceImpl implements BskUserService {

	@Autowired
	private BskUserDao bskUserDao;

	@Override
	public void saveObject(BskUser bskUser) {
		String phone = bskUser.getPhone();
		String password = bskUser.getPassword();
		if (phone == null || phone == "")
			throw new IllegalArgumentException("用户名不能为空");
		
		// 校验手机号的正则表达式
		Pattern compile = Pattern.compile("^[1][3-8]+\\d{9}");
		Matcher matcher = compile.matcher(phone);
		if (!matcher.matches())
			throw new IllegalArgumentException("手机号码格式不正确");
		if (password == null || phone == "")
			throw new IllegalArgumentException("密码不能为空");
		if (password.length() > 16 || password.length() < 6)
			throw new IllegalArgumentException("密码长度为6-16个字符");
		
		// 获取盐值
		String salt = UUID.randomUUID().toString();
		SimpleHash sh = new SimpleHash("MD5", password, salt, 1);
		// 加密后
		bskUser.setSalt(salt);
		bskUser.setPassword(sh.toHex());
		// 保存对象
		bskUserDao.insertObject(bskUser);
	}

	@Override
	public BskUser findObjectByPhone(String phone) {
		BskUser user = bskUserDao.findObjectByPhone(phone);
		return user;
	}

	@Override
	public BskUserMessage findUserMessageByUserId(Integer userId) {
		BskUserMessage userMsg = bskUserDao.findUserMessageByUserId(userId);
		
		return userMsg;
	}

}
