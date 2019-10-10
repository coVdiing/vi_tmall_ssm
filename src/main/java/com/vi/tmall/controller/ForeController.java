package com.vi.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.tmall.comparator.*;
import com.vi.tmall.pojo.*;
import com.vi.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 对应前台路径的控制器
 */
@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    /**
     * 访问主页
     *
     * @param model 传递参数
     * @return 跳转地址
     */
    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> categories = categoryService.list();
        //填充分类对应的产品
        productService.fill(categories);
        //填充推荐产品集合
        productService.fillByRow(categories);
        model.addAttribute("categories", categories);
        return "fore/home";
    }

    /**
     * 注册
     *
     * @param model 传递参数
     * @param user 接收前端传入的参数
     * @return 跳转到注册页
     */
    @RequestMapping("foreregister")
    public String register(Model model, User user) {
        String name = user.getName();
        //把HTML编码转义
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean flag = userService.isExist(name);
        if (flag) {
            String msg = "用户名已被占用！";
            System.out.println(msg);
            model.addAttribute("msg", msg);
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    /**
     * 退出登录
     *
     * @param session 通过session获取登录时传入的user属性
     * @return 跳转到主页
     */
    @RequestMapping("forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    /**
     * 跳转到产品详情页
     *
     * @param pid 产品id
     * @param model 传递参数
     * @return 跳转到产品详情页
     */
    @RequestMapping("foreproduct")
    public String product(int pid, Model model) {
        Product product = productService.get(pid);
        //设置图片
        List<ProductImage> singleProducts = productImageService.list(pid, productImageService.type_single);
        List<ProductImage> detailProducts = productImageService.list(pid, productImageService.type_detail);
        product.setProductSingleImages(singleProducts);
        product.setProductDetailImages(detailProducts);
        //设置评论数量和销量
        productService.setSaleAndReviewNumber(product);
        //将对应产品的评论和属性值存入域中
        List<PropertyValue> propertyValues = propertyValueService.list(pid);
        List<Review> reviews = reviewService.list(pid);
        model.addAttribute("propertyValues", propertyValues);
        model.addAttribute("reviews", reviews);
        model.addAttribute("product", product);
        return "fore/product";
    }

    /**
     * 校验登录状态
     *
     * @param session 通过session获取user对象
     * @return 返回校验信息字符串(会被封装json对象)
     */
    @ResponseBody
    @RequestMapping(value = "forecheckLogin")
    public String checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null)
            return "success";
        return "fail";
    }

    /**
     * 登录方法
     * @param name 前端传入的用户名
     * @param password 前端传入的密码
     * @param model 用于传递验证信息
     * @param session 如果登陆成功，需要将用户信息存到session中
     * @return
     */
    @RequestMapping("forelogin")
    public String login(String name, String password, Model model,HttpSession session) {
        User user = userService.get(name, password);
        if(user==null) {
            String msg = "登录失败，用户名密码错误!";
            model.addAttribute("msg",msg);
            //回到登录页面
            return "fore/login";
        } else {
            //登录成功，跳转到首页
            session.setAttribute("user",user);
            return "redirect:forehome";
        }
    }

    /**
     * 校验用户名密码是否正确
     *
     * @param name 接收前端传入的name参数
     * @param password 接收前端传入的密码
     * @param session 通过参数保存user
     * @return 返回校验登录的信息(被封装成json)
     */
    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(String name, String password, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);
        if (user == null)
            return "fail";
        session.setAttribute("user", user);
        return "success";
    }

    /**
     * 根据类别浏览商品
     * @param cid category id
     * @param sort 商品排序规则，从前端接收数据
     * @param model 用于传递参数
     * @return 跳转到category.jsp
     */
    @RequestMapping("forecategory")
    public String category(int cid, String sort, Model model) {
        Category category = categoryService.get(cid);
        productService.fill(category);
        List<Product> products = category.getProducts();
        productService.setSaleAndReviewNumber(products);
        PageInfo<Product> pageInfo = new PageInfo<>(products,5);
        //根据传入的关键字排序
        sort(products,sort);
        //如果没有收到排序参数，默认根据id排序
        model.addAttribute("category", category);
        model.addAttribute("pageInfo",pageInfo);
        return "fore/category";
    }

    /**
     * 搜索商品
     * @param keyword 搜索关键字
     * @param model 用于传递参数
     * @param sort 排序规则
     * @return 跳转到searchResult.jsp
     */
    @RequestMapping("foresearch")
    public String search(String keyword, Model model,String sort) {
        //根据keyword进行模糊查询，获取满足条件的前20个产品
        PageHelper.offsetPage(0,20);
        List<Product> products = productService.search(keyword);
        productService.setSaleAndReviewNumber(products);
        //根据传入的关键字排序
        sort(products,sort);
        model.addAttribute("products",products);
        return "fore/searchResult";
    }

    /**
     *
     * @param pid 商品id
     * @param num 用户购买商品的数量
     * @param session 用于取出用户信息
     * @return 跳转到结算页面
     */
    @RequestMapping("forebuyone")
    public String buyone(int pid,int num,HttpSession session){
        User user = (User)session.getAttribute("user");
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(productService.get(pid));
        orderItem.setNumber(num);
        orderItem.setPid(pid);
        orderItem.setUid(user.getId());
        orderItemService.add(orderItem);
        int oiid = orderItem.getId();
        return "redirect:forebuy?oiid="+oiid;
    }

    /**
     * 订单结算
     * @param oiid 订单项id
     * @return
     */
    @RequestMapping("forebuy")
    public String buy(String[] oiid,HttpSession session,Model model){
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        float total = 0;
        for (String temp : oiid) {
            int id = Integer.parseInt(temp);
            OrderItem orderItem = orderItemService.get(id);
            total += orderItem.getProduct().getPromotePrice()*orderItem.getNumber();
            orderItems.add(orderItem);
        }
        session.setAttribute("orderItems",orderItems);
        model.addAttribute("total",total);
        return "fore/buy";
    }

    /**
     * 点击加入购物车按钮，将商品和数量数据加入到购物车订单项中
     * @param pid 商品id
     * @param num 商品数量
     * @param session 会话，用于取出当前登录的user信息
     * @return 发送json数据到前端，前端接收到进行判断，做出相应处理
     */
    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int pid,int num,HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
        boolean flag = false;
        for (OrderItem orderItem : orderItems) {
            if(orderItem.getPid().intValue()==pid) {
                //如果订单项中已存在该商品,修改数量
                flag = true;
                orderItem.setNumber(orderItem.getNumber()+num);
                orderItemService.update(orderItem);
                break;
            }
        }
        //如果订单项中没有该商品，新增orderItem
        if (!flag) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPid(pid);
            orderItem.setNumber(num);
            orderItem.setUid(user.getId());
            orderItem.setProduct(productService.get(pid));
            orderItemService.add(orderItem);
        }
        return "success";
    }

    /**
     * 跳转到购物车页面
     * @param model 用于传递订单项
     * @param session 用于取出当前登录用户数据
     * @return fore/cart
     */
    @RequestMapping("forecart")
    public String cart(Model model,HttpSession session){
        User user =(User) session.getAttribute("user");
        List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
        model.addAttribute("orderItems", orderItems);
        return "fore/cart";
    }

    /**
     * 修改在订单结算页面的商品价格和数量
     *
     * @param pid 前端传来的商品ID
     * @param number 前端传来的商品数量
     * @return 返回json字符串格式的处理信息
     */
    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(int pid, int number, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "fail";
        }
        List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
        for (OrderItem orderItem : orderItems) {
            if (pid == orderItem.getPid().intValue()) {
                orderItem.setNumber(number);
                orderItemService.update(orderItem);
                break;
            }
        }
        return "success";
    }

    /**
     * 删除订单中的订单项
     * @param oiid 订单项id
     * @param session 用于验证用户是否登录
     * @return 返回json字符串格式的校验信息
     */
    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(int oiid,HttpSession session) {
        User user = (User) session.getAttribute("user");
        //判断用户是否登录
        if (user == null) {
            return "fail";
        } else {
            orderItemService.delete(oiid);
            return "success";
        }
    }

    /**
     * 生成订单信息，跳转到结算页面
     * @param order 接收前端注入的数据，收货地址，收货人，邮编地址等
     * @param session 用于获取当前的用户
     * @return 跳转到支付页
     */
    @RequestMapping("forecreateOrder")
    public String createOrder(Order order,HttpSession session) {
        User user = (User) session.getAttribute("user");
        //orderCode使用时间加4位随机数字组成
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        Date date = new Date();
        String randomCode = "";
        for (int i = 0; i < 4; i++) {
           randomCode += (int)(Math.random()*10);
        }
        String orderCode = sdf.format(date)+randomCode;
        order.setUid(user.getId());
        order.setStatus(OrderService.waitPay);
        order.setCreateDate(date);
        order.setOrderCode(orderCode);
        List<OrderItem> orderItems =(List) session.getAttribute("orderItems");
        float total = orderService.add(order, orderItems);
        return "redirect:forealipay?oid="+order.getId()+"&total="+total;
    }

    /**
     * 修改已支付订单状态
     * @param oid 前端传入的order id
     * @param total 前端传入的order 总价格
     * @param model 传递order对象到下个页面
     * @return 跳转到支付成功页面
     */
    @RequestMapping("forepayed")
    public String payed(int oid, float total, Model model) {
        Order order = orderService.get(oid);
        order.setPayDate(new Date());
        order.setStatus(OrderService.waitDelivery);
        orderService.update(order);
        //预计支付后三天可以收货
        Date date = new Date();
        long time = System.currentTimeMillis()+3*24*60*60*1000;
        date.setTime(time);
        model.addAttribute("order", order);
        model.addAttribute("receiveTime",date);
        return "fore/payed";
    }

    /**
     * 展示订单数据
     * @param session 取出用户信息
     * @param model 传递订单项集合
     * @return 跳转到订单页
     */
    @RequestMapping("forebought")
    public String bought(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Order> orders = (List) orderService.list(user.getId(),OrderService.delete);
        model.addAttribute("orders", orders);
        return "fore/bought";
    }

    /**
     *  在我的订单页点击确认收货，跳转到confirmPay.jsp
     * @param oid order id，从前端传入
     * @param model 传递参数到jsp页面
     * @return
     */
    @RequestMapping("foreconfirmPay")
    public String confirmPay(int oid, Model model) {
        Order order = orderService.get(oid);
        model.addAttribute("order", order);
        return "fore/confirmPay";
    }

    /**
     * 确认收货成功
     * @param oid 接收前台传入的order id
     * @return 跳转到orderConfirm.jsp页面
     */
    @RequestMapping("foreorderConfirmed")
    public String orderConfirm(int oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitReview);
        orderService.update(order);
        return "redirect:orderConfirmPage";
    }

    /**
     * 修改订单状态为删除
     * @param order
     * @return 处理结果json字符串
     */
    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder(Order order) {
        order.setStatus(OrderService.delete);
        orderService.update(order);
        return "success";
    }

    /**
     * 跳转到评价产品页
     * @param oid 前端传入的order id
     * @param model 传递参数到下个页面
     * @return 跳转到review.jsp
     */
    @RequestMapping("forereview")
    public String review( Model model,int oid) {
        Order o = orderService.get(oid);
        Product p = o.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.list(p.getId());
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("p", p);
        model.addAttribute("o", o);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    /**
     * 提交评论
     * @param oid 从上个页面传递的order id
     * @param pid 上个页面传递的product id
     * @param model 用于传递参数
     * @param session 获取user
     * @return 提交评论以后查看评论页
     */
    @RequestMapping("foredoreview")
    public String doreview(Model model, HttpSession session, @RequestParam("oid") int oid, @RequestParam("pid") int pid, String content) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        Product p = productService.get(pid);
        content = HtmlUtils.htmlEscape(content);
        User user =(User)  session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setPid(pid);
        review.setCreateDate(new Date());
        review.setUid(user.getId());
        reviewService.add(review,o);
        return "redirect:forereview?oid="+oid+"&showonly=true";
    }

    /**
     * 通过比较器进行排序
     * @param products
     * @param sort 排序规则
     */
    public void sort(List<Product> products,String sort) {
        if (null != sort) {
            switch (sort) {
                case "review":
                    Collections.sort(products, new ProductReviewComparator());
                    break;
                case "saleCount":
                    Collections.sort(products, new ProductSaleCountComparator());
                    break;
                case "date" :
                    Collections.sort(products, new ProductDateComparator());
                case "priceAsc" :
                    Collections.sort(products, new ProductPriceAscComparator());
                    break;
                case "priceDesc" :
                    Collections.sort(products, new ProductPriceDescComparator());
                    break;
                case "all" :
                    Collections.sort(products, new ProductAllComparator());
                    break;
            }
        }
    }

    /**
     * 在搜索框中随机显示分类名称
     */
    @ResponseBody
    @RequestMapping(value="randomShowCategory",produces = "text/html;charset=UTF-8")
    public String randomShowCategory(){
        ArrayList<Category> categories = (ArrayList<Category>) categoryService.list();
        int index =(int) (Math.random()*categories.size());
        Category category = categories.get(index);
        return category.getName();
    }

}
