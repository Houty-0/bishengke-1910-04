package com.bsk.dican.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsk.dican.entity.BskMenu;
import com.bsk.dican.service.BskMenuService;

@Controller
@RequestMapping("/menu")
public class BskMenuController {

	@Autowired
	private BskMenuService bskMenuService;
	
	@RequestMapping("/doFindObjects")
	public void doFindObjects(Model model) {
		List<BskMenu> menus = bskMenuService.findObjets();
		model.addAttribute("menus", menus);
		
	}
}
