<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
	$(function() {
		$("#editForm").submit(function(){
			if(!checkEmpty("name","产品名称"))
				return false;
			if(!checkEmpty("subTitle","产品小标题"))
				return false;
			if(!checkEmpty("originalPrice","原价格"))
				return false;
			if(!checkEmpty("promotePrice","优惠价格"))
				return false;
			if(!checkEmpty("stock","库存数量"))
				return false;
			return true;
		});
	});
</script>
<title>产品编辑</title>
<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">所有分类</a></li>
		<li><a href="admin_product_list?cid=${category.id}">${category.name}</a></li>
		<li>${product.name}</li>
		<li class="active">产品编辑</li>
	</ol>
	
	<!-- 产品编辑  -->
	<div style="width: 400px; margin: 0 auto;">
		<div class="panel panel-primary">
			<div class="panel-heading" style="text-align: center;">产品编辑</div>
			<div class="panel-body">
				<form action="admin_product_update" method="post" id="editForm">
					<table class="editTable">
						<tr>
							<td>产品名称</td>
							<td><input type="text" id="name" name="name" value="${product.name}"  class="form-control"></td>
						</tr>
						<tr>
							<td>产品小标题</td>
							<td><input type="text" id="subTitle" name="subTitle" value="${product.subTitle}" class="form-control"></td>
						</tr>
						<tr>
							<td>原价格</td>
							<td><input type="text" id="originalPrice" name="originalPrice" value="${product.originalPrice}" class="form-control"></td>
						</tr>
						<tr>
							<td>优惠价格</td>
							<td><input type="text" id="promotePrice" name="promotePrice" value="${product.promotePrice}" class="form-control"></td>
						</tr>
						<tr>
							<td>库存数量</td>
							<td><input type="text" id="stock" name="stock" value="${product.stock}" class="form-control"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<!-- 隐式传递Category id给下个页面 -->
								<input type="hidden" name="cid" value="${category.id}" >
								<input type="hidden" name="id" value="${product.id}" >
								<button type="submit" class="btn btn-primary">提交</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>