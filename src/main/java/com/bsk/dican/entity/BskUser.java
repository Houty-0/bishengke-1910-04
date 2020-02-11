package com.bsk.dican.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BskUser implements Serializable{

	private static final long serialVersionUID = -5068789675196750286L;
	
	private Integer id;
	
	private String phone;
	
	private String password;
	
	private String salt;

}
