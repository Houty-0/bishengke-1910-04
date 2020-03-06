package com.bsk.dican.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bsk.common.util.ShiroUtil;
import com.bsk.dican.dao.BskOrderDao;
import com.bsk.dican.dao.BskOrderGoodDao;
import com.bsk.dican.entity.BskOrder;
import com.bsk.dican.entity.BskOrderGood;
import com.bsk.dican.service.BskOrderService;

@Service
public class BskOrderServiceImpl implements BskOrderService {

	@Autowired
	private BskOrderDao bskOrderDao;
	
	@Autowired
	private BskOrderGoodDao bskOrderGoodDao;

	@Transactional
	@Override
	public String saveOrder(BskOrder bskOrder) {
		
		String orderId = "" + System.currentTimeMillis() + ShiroUtil.getUserId();
		
		List<BskOrderGood> bskOrderGoods = bskOrder.getBskOrderGoods();
		
		bskOrder.setOrderId(orderId).setCreated(new Date()).setUpdated(bskOrder.getCreated());
		bskOrderDao.insert(bskOrder);
		
		for (BskOrderGood good : bskOrderGoods) {
			good.setOrderId(orderId);
			bskOrderGoodDao.insert(good);
		}
		
		return orderId;
	}

	@Override
	public BskOrder findOrderByOrderId(String orderId) {
		QueryWrapper<BskOrder> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_id", orderId).select("order_id","total_price","pay_type","created");
		
		BskOrder bskOrder = bskOrderDao.selectOne(queryWrapper);
		bskOrder.setCreated(new Date());
		//System.out.println(bskOrder);
		return bskOrder;
	}
}
