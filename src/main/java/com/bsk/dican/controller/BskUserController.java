package com.bsk.dican.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsk.common.vo.JsonResult;
import com.bsk.dican.entity.BskUser;
import com.bsk.dican.entity.BskUserMessage;
import com.bsk.dican.service.BskUserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class BskUserController {

	@Autowired
	private BskUserService bskUserService;

	//用户注册
	@RequestMapping("/doSaveObject")
	public JsonResult doSaveObject(BskUser bskUser,HttpSession session,String code) {
		System.out.println("bskUser:" + bskUser);
		System.out.println("用户输入的验证码:" + code);
		String securitycode = (String)session.getAttribute("SecurityCode");
		System.out.println("从session获取的验证码:" + securitycode);
		if(!securitycode.equals(code)){
			return JsonResult.error("验证码错误");
		}
			bskUserService.saveObject(bskUser);
		return JsonResult.ok("注册成功");
	}

	//用户登录
	@RequestMapping("/doLogin")
	public JsonResult doLogin(String phone, String password, HttpServletResponse response, HttpSession session) {
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
		
		subject.login(token);
		
		BskUser user = bskUserService.findObjectByPhone(phone);

		Cookie cookie = new Cookie("id", user.getId().toString());
		cookie.setPath("/");
		response.addCookie(cookie);
		session.setAttribute("id", user.getId().toString());

		return JsonResult.ok("登录成功");
	}

	//可以通过访问这个路径拿到用户的信息
	@RequestMapping("/getMessage")
	public JsonResult getMessage(HttpServletRequest httpServletRequest,HttpSession session){
		Cookie[] cookies = httpServletRequest.getCookies();
		String id=session.getAttribute("id").toString();
		for (Cookie cookie: cookies) {
			String userId=cookie.getValue();
			if(id.equals(userId)){
				BskUserMessage message=bskUserService.findUserMessageByUserId(Integer.valueOf(userId));
				//返回查到的用户信息
				return JsonResult.ok(message);
			}
		}
		return JsonResult.error("用户不存在");
	}


}
