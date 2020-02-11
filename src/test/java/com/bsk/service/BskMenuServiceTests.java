package com.bsk.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bsk.dican.entity.BskMenu;
import com.bsk.dican.service.BskMenuService;

@SpringBootTest
public class BskMenuServiceTests {

	@Autowired
	private BskMenuService bskMenuService;
	
	@Test
	public void testFindObjects() {
		List<BskMenu> list = bskMenuService.findObjets();
		
		for (BskMenu bskMenu : list) {
			System.out.println(bskMenu);
		}
	}
}
