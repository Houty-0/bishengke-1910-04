package com.bsk.dican.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BskMenu implements Serializable{

	private static final long serialVersionUID = -8818861472473423323L;
	
	private Integer id;
	private String title;
}
