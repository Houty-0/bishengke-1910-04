package com.bsk.dican.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BskGood implements Serializable{

	private static final long serialVersionUID = 7304876237166243282L;
	
	private Integer id;
	private Integer menuId;
	private String name;
	private String note;
	private Double price;
	private String imgUrl;
	private String baoZhuang;
	private String type;
	private Integer size;
	private Integer isNew;
}
