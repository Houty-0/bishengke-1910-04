package com.bsk.common.vo;

import java.io.Serializable;

/**
 * JSON 响应结果
 */
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private int state;
	
	/**
	 * 结果消息
	 */
	private String message;

	/**
	 * 成功时响应数据
	 */
	private T data;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/*
	 * 自定义方法，方便使用
	 * ==================================================
	 */

	public JsonResult state(int state) {
		this.state = state;
		return this;
	}

	public JsonResult message(String message) {
		this.message = message;
		return this;
	}

	public JsonResult data(T data) {
		this.data = data;
		return this;
	}

	public static <T> JsonResult<T> error(String message) {
		return error(0, message);
	}

	public static <T> JsonResult<T> error(int state, String message) {
		JsonResult<T> jr = new JsonResult<>();
		jr.setState(state);
		jr.setMessage(message);
		return jr;
	}

	public static <T> JsonResult<T> ok() {
		return ok(null);
	}

	public static <T> JsonResult<T> ok(T data) {
		JsonResult<T> jr = new JsonResult<>();
		jr.setState(1);
		jr.setData(data);
		return jr;
	}

}
