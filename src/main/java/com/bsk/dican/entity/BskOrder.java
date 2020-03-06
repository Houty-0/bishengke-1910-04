package com.bsk.dican.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("bsk_orders")
public class BskOrder extends BasePojo implements Serializable {

	private static final long serialVersionUID = 1794431742607440523L;

	@TableId
	private String orderId; // 订单号

	private Integer userId; // 用户id

	private String username; // 订单用户

	private String phone; // 用户联系电话

	private Double totalPrice; // 订单金额

	private Integer totalNum; // 订单商品总数

	private Integer payType; // 支付方式

	private String orderType; // 订单类型:自取/外卖

	private String city; // 城市

	private String addr; // 送餐地址

	@TableField(exist = false)
	private List<BskOrderGood> bskOrderGoods; // 封装订单商品信息 一对多
}
