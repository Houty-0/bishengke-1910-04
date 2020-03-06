package com.bsk.dican.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsk.common.util.ShiroUtil;
import com.bsk.common.vo.JsonResult;
import com.bsk.dican.entity.BskOrder;
import com.bsk.dican.service.BskOrderService;

@RestController
@RequestMapping("/order")
public class BskOrderController {
	
	@Autowired
	private BskOrderService bskOrderService;

	@RequestMapping("/saveOrderGoods")
	public JsonResult<String> saveOrderGoods(@RequestBody BskOrder bskOrder, HttpSession session) {
		Integer userId = ShiroUtil.getUserId();
		session.setAttribute("order" + userId, bskOrder);
		System.out.println(bskOrder);

		return JsonResult.ok("ok!!!");
	}
	
	@RequestMapping("/getOrderGoods")
	public JsonResult<BskOrder> getOrderGoods(HttpSession session) {
		Integer userId = ShiroUtil.getUserId();
		BskOrder bskOrder = (BskOrder)session.getAttribute("order" + userId);
		return JsonResult.ok(bskOrder);
	}
	
	@RequestMapping("/submit")
	public JsonResult<String> submit(Integer payType,HttpSession session) {
		System.out.println("payType:"+payType);
		Integer userId = ShiroUtil.getUserId();
		BskOrder bskOrder = (BskOrder)session.getAttribute("order" + userId);
		bskOrder.setPayType(payType);
		String orderId = bskOrderService.saveOrder(bskOrder);
		if (StringUtils.isEmpty(orderId)) {
			return JsonResult.error(null);
		}
		
		return JsonResult.ok(orderId);
	}
	
}
