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
import com.bsk.common.util.ShiroUtil;
import com.bsk.dican.entity.BskMenu;
import com.bsk.dican.entity.BskOrder;
import com.bsk.dican.entity.BskUser;
import com.bsk.dican.service.BskMenuService;
import com.bsk.dican.service.BskOrderService;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @Autowired
    private BskMenuService bskMenuService;

    @RequestMapping("doLoginUI")
    public String doLoginUI() {
        return "login";
    }
    
    
    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page) {
        System.out.println("page---------" + page);

        return page;
    }

   
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
    
    @RequestMapping("/Pizza.html")
	public String toPizza(Model model) {
		List<BskMenu> menus = bskMenuService.findObjets();

		model.addAttribute("menus", menus);
		
		return "Pizza";
	}
    
    @RequestMapping("/order.html")
	public String toOrder(HttpSession session,Model model) {
		System.out.println("~~~~~~~order.html~~~~~~~~");
		Integer userId = ShiroUtil.getUserId();
		BskOrder bskOrder = (BskOrder)session.getAttribute("order" + userId);
		model.addAttribute("order",bskOrder);
		System.out.println();
		return "order";
	}
	
	@RequestMapping("/pay.html")
	public String toPay(HttpSession session,Model model) {
		System.out.println("~~~~~~~pay.html~~~~~~~~");
		Integer userId = ShiroUtil.getUserId();
		BskOrder bskOrder = (BskOrder)session.getAttribute("order" + userId);
		model.addAttribute("order",bskOrder);
		System.out.println();
		return "pay";
	}
	
	@Autowired
	private BskOrderService bskOrderService;
	
	@RequestMapping("/payment.html")
	public String payment(String id,Model model) {
		System.out.println("~~~~~~~payment.html~~~~~~~~");
		BskOrder bskOrder = bskOrderService.findOrderByOrderId(id);
		model.addAttribute("order", bskOrder);
		
		return "payment";
	}

}
