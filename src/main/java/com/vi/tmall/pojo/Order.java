package com.vi.tmall.pojo;

import java.util.Date;
import java.util.List;

import com.vi.tmall.service.OrderService;

public class Order {
    private Integer id;

    private String orderCode;

    private String address;

    private String post;

    private String receiver;

    private String mobile;

    private String userMessage;

    private Date createDate;

    private Date payDate;

    private Date deliveryDate;

    private Date confirmDate;

    private Integer uid;

    private String status;

    private User user;	//非数据库字段，订单对应的用户对象
    
    private float total; //非数据库字段，订单总金额，之后浏览订单用于页面展示
    
    private int totalNumber; //订单中产品数量
    
    private List<OrderItem> orderItems;	//订单中包含的订单项集合
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	//�ж϶���״̬
	public String getStatusDesc() {
		String desc = "";
		switch(status) {
			case OrderService.waitPay :
				desc = "待付款";
				break;
			case OrderService.waitDelivery :
				desc = "待发货";
				break;
			case OrderService.waitConfirm :
				desc = "待确认";
				break;
			case OrderService.waitReview :
				desc = "待评论";
				break;
			case OrderService.finish :
				desc = "已结束";
				break;
			case OrderService.delete :
				desc = "已删除";
				break;
			default:
				desc = "未知";
		}
		return desc;
	}
}