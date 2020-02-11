package com.bsk.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsk.common.vo.JsonResult;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult handleException(RuntimeException e) {
		e.printStackTrace();
		return JsonResult.error(e.getMessage());
	}

}
