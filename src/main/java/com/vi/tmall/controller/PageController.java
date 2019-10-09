package com.vi.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务器端跳转，访问web-inf下的页面
 */
@Controller
public class PageController {
    @RequestMapping("registerPage")
    public String registerPage() {
        return "fore/register";
    }

    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage() {
        return "fore/registerSuccess";
    }

    @RequestMapping("loginPage")
    public String loginPage() {
        return "fore/login";
    }

    @RequestMapping("forealipay")
    public String alipay() {
        return "fore/alipay";
    }

    @RequestMapping("orderConfirmPage")
    public String orderConfirm(){
        return "fore/orderConfirm";
    }

}
