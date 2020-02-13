package com.bsk.dican.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 生成验证码类
 */
@Controller
public class SecurityCodeController {

    @RequestMapping("/getSecurityCode")
    public void createdSecurityCode(HttpServletResponse response, HttpSession Session) throws IOException {
        //验证码格式
        LineCaptcha securityCode = CaptchaUtil.createLineCaptcha(120, 36, 4, 50);
        //将验证码信息保存到session中方便在控制层去出验证
        Session.setAttribute("SecurityCode",securityCode.getCode());
        //设置响应类型
        response.setContentType("image/jpg");
        //获取流输出
        ServletOutputStream outputStream = response.getOutputStream();
        //通过write输出
        securityCode.write(outputStream);
        //关闭资源
        outputStream.close();
    }
}
