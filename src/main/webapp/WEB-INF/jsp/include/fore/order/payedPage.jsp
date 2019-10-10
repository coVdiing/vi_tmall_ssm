<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<div class="payedDiv">
    <div class="payedAddressInfo">
        <img src="img/site/paySuccess.png"/>
        <span>您已成功付款</span>
    </div>

    <div class="payedAddressInfo">
        <ul>
            <li>收货地址:${order.address} ${order.receiver} ${order.mobile}</li>
            <li>实付款:<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/> </li>
            <li>预计<fmt:formatDate value="${receiveTime}" pattern="yyyy-MM-dd"></fmt:formatDate>送达 </li>
        </ul>

        <div>
            您可以<a href="forebought">查看交易详情</a>
        </div>
    </div>

    <div class="payedSeperateLine">

    </div>

    <div class="warningDiv">
        <img src="img/site/warning.png"/>
        <b>安全提醒:</b>下单后，<span class="redColor boldWord">用QQ给您发送链接办理退款的都是骗子！</span>X猫不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！
    </div>

</div>