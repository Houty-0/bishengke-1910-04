package com.bsk.dican.service;

import com.bsk.dican.entity.BskOrder;

public interface BskOrderService {

	String saveOrder(BskOrder bskOrder);

	BskOrder findOrderByOrderId(String orderId);

	
}
