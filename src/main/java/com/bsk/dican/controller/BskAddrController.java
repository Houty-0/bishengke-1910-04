package com.bsk.dican.controller;

import com.bsk.common.vo.JsonResult;
import com.bsk.dican.entity.BskOrderAddr;
import com.bsk.dican.service.BskAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/addr")
public class BskAddrController {

	@Autowired
	private BskAddrService bskAddrService;

	@RequestMapping("/saveOrderAddr")
	public JsonResult saveOrderAddr(BskOrderAddr bskOrderAddr, HttpSession session){
		System.out.println(bskOrderAddr);
		Integer id=Integer.parseInt((String) session.getAttribute("id"));
		bskOrderAddr.setUserId(id);
		bskAddrService.saveOrderAddr(bskOrderAddr);
		return JsonResult.ok("保存成功");
	}
}
