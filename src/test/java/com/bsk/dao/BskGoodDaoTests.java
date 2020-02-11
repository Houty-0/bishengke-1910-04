package com.bsk.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bsk.dican.dao.BskGoodDao;
import com.bsk.dican.entity.BskGood;

@SpringBootTest
public class BskGoodDaoTests {

	@Autowired
	private BskGoodDao bskGoodDao;
	
	@Test
	public void testFindObjects() {
		List<BskGood> list = bskGoodDao.findObjects(2);
		for (BskGood bskGood : list) {
			System.out.println(bskGood);
		}
	}
}
