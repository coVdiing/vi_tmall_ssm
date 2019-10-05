package com.vi.tmall.interceptor;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.OrderItem;
import com.vi.tmall.pojo.User;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OtherInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<Category> categories = categoryService.list();
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        session.setAttribute("cs",categories);
        session.setAttribute("contextPath", contextPath);
        //获取购物车中一共有多少数量
        User user = (User) session.getAttribute("user");
        int cartTotalItemNumber = 0;
        if (user != null) {
            List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
            for (OrderItem oi : orderItems) {
                cartTotalItemNumber += oi.getNumber();
            }
        }
        session.setAttribute("cartTotalItemNumber",cartTotalItemNumber);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
