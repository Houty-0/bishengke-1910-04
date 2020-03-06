package com.bsk.dican.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("bsk_order_good")
public class BskOrderGood implements Serializable{

	private static final long serialVersionUID = 5795644567781758486L;

	@TableId
	private String orderId;		//订单号
	
	@TableId
	private Integer goodId;		//商品id
	
	private String title;		//商品名称
	
	private Integer num;		//商品数量
	
	private Double price;		//商品单价
	
	private Double totalPrice;	//商品小计总金额 
	
	private String picPath;		//商品图片地址
}
