package com.bsk.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bsk.dican.entity.BskGood;
import com.bsk.dican.service.BskGoodService;

@SpringBootTest
public class BskGoodServiceTests {

	@Autowired
	private BskGoodService bskGoodService;
	
	@Test
	public void testFindObjects() {
		List<BskGood> list = bskGoodService.findObjects(1);
		
		for (BskGood bskGood : list) {
			System.err.println(bskGood);
		}
	}
	
	
}
