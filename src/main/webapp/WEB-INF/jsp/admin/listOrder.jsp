<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<script>
	$(function() {
		$(".showDetailBtn").click(function() {
			var oid = $(this).attr("oid");
			$(".detailTR[oid=" + oid + "]").toggle();
		});
	});
</script>

<title>订单管理</title>
<h4 class="label label-info">订单管理</h4>
<br>
<br>

<div>
	<table
		class="table table-hover table-striped table-bordered table-condensed">
		<thead>
			<tr class="info">
				<th>ID</th>
				<th>订单状态</th>
				<th>金额</th>
				<th width="100px">商品数量</th>
				<th width="100px">买家名称</th>
				<th>创建时间</th>
				<th>支付时间</th>
				<th>发货时间</th>
				<th>确认收货时间</th>
				<th width="120px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orders}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.statusDesc}</td>
					<td>￥<fmt:formatNumber minFractionDigits="2"
							value="${order.total}" type="number" /></td>
					<td align="center">${order.totalNumber}</td>
					<td align="center">${order.user.name}</td>
					<td><fmt:formatDate value="${order.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${order.payDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${order.deliveryDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${order.confirmDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<button oid="${order.id}"
							class="showDetailBtn btn btn-xs btn-primary">查看详情</button>
						 <a href="admin_order_delivery?id=${order.id}">
							<button class="btn btn-primary btn-xs">发货</button>
						</a>
					</td>
				</tr>
				<tr class="detailTR" style="display: none;" oid="${order.id}">
					<td colspan="10" align="center">
						<div class="orderPageOrderItem">
							<table width="800px" align="center"
								class="orderPageOrderItemTable">
								<c:forEach items="${order.orderItems}" var="orderItem">
									<tr>
										<td style="width: 200px;"><img width="40px" height="40px"
											src="img/productSingle/${orderItem.product.firstProductImage.id}.jpg">
										</td>
										<td style="width: 200px;"><a href="#"><span>${orderItem.product.name}</span></a></td>
										<td style="width: 200px;"><span class="muted-text">
												x ${orderItem.number}</span></td>
										<td style="width: 200px;"><span class="muted-text">
												￥ ${orderItem.product.promotePrice}</span></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>

<!-- 分页  -->
<div align="center">
	<%@ include file="../include/admin/adminPageHelper.jsp"%>
</div>