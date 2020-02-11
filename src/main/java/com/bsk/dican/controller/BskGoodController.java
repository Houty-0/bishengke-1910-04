package com.bsk.dican.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsk.common.vo.JsonResult;
import com.bsk.dican.entity.BskGood;
import com.bsk.dican.service.BskGoodService;

@RestController
@RequestMapping("/goods")
public class BskGoodController {

	@Autowired
	private BskGoodService bskGoodService;
	
	@RequestMapping("/doFindObjects")
	public JsonResult doFindObjects(Integer menuId) {
		System.err.println("menuId::::" + menuId);
		List<BskGood> goods = bskGoodService.findObjects(menuId);
		
		return JsonResult.ok(goods);
	}
	
}
