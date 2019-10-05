package com.vi.tmall.interceptor;


import com.vi.tmall.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 对部份页面进行未登录拦截
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        //不需要登录的页面
        String[] noNeedAuthPage = {
                "home",
                "login",
                "register",
                "checkLogin",
                "loginAjax",
                "product",
                "category",
                "search"};
        uri = uri.substring(contextPath.length(),uri.length());
            String method = uri.substring(5,uri.length());
            if(!Arrays.asList(noNeedAuthPage).contains(method)) {
                //如果uri不在不需要登录的页面范围内,判断登录状态
                User user =  (User) session.getAttribute("user");
                if (null == user) {
                    response.sendRedirect("loginPage");
                    return false;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
