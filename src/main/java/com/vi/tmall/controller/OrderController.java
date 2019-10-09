package com.vi.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.tmall.pojo.Order;
import com.vi.tmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("admin_order_list")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        PageHelper.startPage(page, 5);
        List<Order> orders = orderService.list();
        PageInfo<Order> pageInfo = new PageInfo<>(orders, 5);
        model.addAttribute("orders", orders);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/listOrder";
    }

    @RequestMapping("admin_order_delivery")
    public String delivery(Order order) throws IOException {
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:admin_order_list";

    }
}
