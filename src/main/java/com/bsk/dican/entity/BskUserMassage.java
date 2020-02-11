package com.bsk.dican.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BskUserMassage implements Serializable{

	private static final long serialVersionUID = -515229085981792733L;
	
	//private Integer userId;
	
    private String username;
    
    private Integer gender;
    
    private Integer coupon;
    
    private Integer vj;
    
    private Integer balance;
}
