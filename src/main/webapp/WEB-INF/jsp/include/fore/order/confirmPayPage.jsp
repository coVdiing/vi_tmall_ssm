<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java"  pageEncoding="UTF-8" isELIgnored="false" %>
<div class="confirmPayPageDiv">
    <div class="confirmPayImageDiv">
        <img src="img/site/comformPayFlow.png"/>
        <div class="confirmPayTime1">
            <fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </div>
        <div class="confirmPayTime2">
            <fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </div>
        <div class="confirmPayTime3">
            <fmt:formatDate value="${order.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </div>
    </div>

    <div class="confirmPayOrderInfoDiv">
        <div class="confirmPayOrderInfoText">我已收到货，同意支付宝付款</div>
    </div>
    <div class="confirmPayOrderItemDiv">
        <div class="confirmPayOrderItemText">订单信息</div>
        <table class="confirmPayOrderItemTable">
            <thead>
                <th colspan="2">宝贝</th>
                <th width="120px">单价</th>
                <th width="120px">数量</th>
                <th width="120px">运费</th>
            </thead>
            <tbody>
            <c:forEach items="${order.orderItems}" var="orderItem">
                <tr>
                    <td><img width="50px" src="img/productSingle_middle/${orderItem.product.firstProductImage.id}.jpg"></td>
                    <td class="confirmPayOrderItemProductLink">
                        <a href="#nowhere">${orderItem.product.name}</a>
                    </td>
                    <td>
                        ￥<fmt:formatNumber type="number" value="${orderItem.product.promotePrice}" minFractionDigits="2"/>
                    </td>
                    <td>
                        ${orderItem.number}
                    </td>

                    <td>
                        <span>快递:0.00</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="confirmPayOrderItemText pull-right">
            实付款：
            <span class="confirmPayOrderItemSumPrice">
                ￥<fmt:formatNumber type="number" value="${order.total}" minFractionDigits="2"/>
            </span>
        </div>
    </div>


    <div class="confirmPayOrderDetailDiv">
        <table class="confirmPayOrderDetailTable">
            <tr>
                <td>订单编号:</td>
                <td>${order.orderCode}<img width="23px" src="img/site/confirmOrderTmall.png"></td>
            </tr>
            <tr>
                <td>卖家昵称:</td>
                <td>天猫商铺<span class="wangwangGif"></span></td>
            </tr>
            <tr>
                <td>收货信息:</td>
                <td>${order.address},${order.receiver},${order.mobile},${order.post}</td>
            </tr>
            <tr>
                <td>成交时间：</td>
                <td><fmt:formatDate value="${order.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </table>
    </div>

    <div class="confirmPayButtonDiv">
        <div class="confirmPayWarning">请收到货后，再确认收货！否则您可能钱货两空！</div>
        <a href="foreorderConfirmed?oid=${order.id}"><button class="confirmPayButton">确认支付</button></a>
    </div>
</div>
