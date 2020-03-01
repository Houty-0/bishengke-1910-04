package com.bsk.dican.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsk.common.vo.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsk.common.session.MySessionContext;
import com.bsk.dican.entity.BskMenu;
import com.bsk.dican.entity.BskUser;
import com.bsk.dican.service.BskMenuService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @Autowired
    private BskMenuService bskMenuService;

    @RequestMapping("doLoginUI")
    public String doLoginUI() {
        return "login";
    }

    /**
     * 转发到指定的页面
     *
     * @param page 页面的视图名
     * @return 视图名
     */
    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page, Model model, HttpServletRequest request,
                         HttpServletResponse response, HttpSession session) {
        System.out.println("page---------" + page);

        if ("Pizza.html".equals(page)) {
            List<BskMenu> menus = bskMenuService.findObjets();

            model.addAttribute("menus", menus);
        }

//		Cookie[] cookies = request.getCookies();
//		System.err.println("cookies.length====" + cookies.length);
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("JSESSIONID")) {
//					System.err.println("cookieName:::" + cookie.getName());
//					System.err.println("cookieValue:::" + cookie.getValue());
//					HttpSession userSession = MySessionContext.getInstance().getSession(cookie.getValue());
//					if (userSession != null) {
//						BskUser user = (BskUser) userSession.getAttribute("user");
//						System.out.println("登录用户loginUser:::" + user);
//					} else {
//						System.out.println("用户未登录!!!!!!");
//					}
//
//				}
//			}
//		}

        return page;
    }

    /**
     * rest风格(软件架构编码风格)的url {}为rest表达式(内容为自己定义的变量)
     *
     * @param moduleUI
     * @return
     */
    @RequestMapping("{module}/{moduleUI}.html")
    public String doLogUI(@PathVariable String moduleUI) {
        System.out.println(moduleUI + "----------");
        return "other/" + moduleUI;
    }

    //退出登录
    @RequestMapping("/doLogout")
    @ResponseBody
    public JsonResult doLogout() {
        System.out.println("正在退出");
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return JsonResult.error("退出失败");
        }
        subject.logout();
        return JsonResult.ok("成功退出");
    }

}
