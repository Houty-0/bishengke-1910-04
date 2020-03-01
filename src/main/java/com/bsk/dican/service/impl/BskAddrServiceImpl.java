package com.bsk.dican.service.impl;

import com.bsk.dican.dao.BskAddrDao;
import com.bsk.dican.entity.BskOrderAddr;
import com.bsk.dican.service.BskAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BskAddrServiceImpl implements BskAddrService {

    @Autowired
    private BskAddrDao bskAddrDao;


    @Override
    public void saveOrderAddr(BskOrderAddr bskOrderAddr) {
        bskAddrDao.saveOrderAddr(bskOrderAddr);
    }
}
