package com.bsk.dican.dao;

import com.bsk.dican.entity.BskOrderAddr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BskAddrDao {


    @Insert("insert into bsk_addr values(null,#{orderName},#{orderAddr},#{orderPhone},#{orderGender},#{userId})")
    void saveOrderAddr(BskOrderAddr bskOrderAddr);
}
