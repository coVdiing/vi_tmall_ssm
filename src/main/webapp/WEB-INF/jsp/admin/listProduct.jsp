<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<script>
	$(function() {
		$("#addForm").submit(function(){
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
<title>产品列表</title>
<div style="margin: 0 40px;">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">所有分类</a></li>
		<li>${category.name}</li>
		<li>产品列表</li>
	</ol>

	<!-- 数据展示 -->
	<table class="table table-striped table-bordered table-hover  table-condensed">
		<thead>
			<tr class="info">
				<th>ID</th>
				<th>图片</th>
				<th>产品名称</th>
				<th>产品小标题</th>
				<th>原价格</th>
				<th>优惠价格</th>
				<th>库存数量</th>
				<th>图片管理</th>
				<th>设置属性</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productList}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>
						<c:if test="${!empty product.firstProductImage}">
							<img height="40px" src="img/productSingle/${product.firstProductImage.id}.jpg">
						</c:if>
					</td>
					<td>${product.name}</td>	
					<td>${product.subTitle}</td>
					<td>${product.originalPrice}</td>
					<td>${product.promotePrice}</td>
					<td>${product.stock}</td>
					<td><a href="admin_productImage_list?pid=${product.id}"><span class="glyphicon glyphicon-picture"></span></a></td>
					<td><a href="admin_propertyValue_edit?pid=${product.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
					<td><a href="admin_product_edit?id=${product.id}"><span
							class="glyphicon glyphicon-edit"></span></a></td>
					<td><a deleteLink="true" href="admin_product_delete?id=${product.id}&cid=${category.id}"><span
							class="glyphicon glyphicon-trash"></span></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div style="text-align: center;">
		<!-- 使用PageHelper插件分页 -->
		<nav>
			<ul class="pagination">
				<li><a href="?page=1&cid=${category.id}">首页</a></li>
				<c:if test="${pageInfo.hasPreviousPage}&cid=${category.id}">
					<li><a href="?page=${pageInfo.pageNum-1}&cid=${category.id}"><</a></li>
				</c:if>
				<c:forEach items="${pageInfo.navigatepageNums}" var="cur">
					<li <c:if test="${pageInfo.pageNum == cur }">class="active"</c:if>>
						<a href="?page=${cur}&cid=${category.id}">${cur}</a>
					</li>
				</c:forEach>
				<c:if test="${pageInfo.hasNextPage}">
					<li><a href="?page=${pageInfo.pageNum+1}&cid=${category.id}">></a></li>
				</c:if>
				<li><a href="?page=${pageInfo.pages}&cid=${category.id}">末页</a></li>
			</ul>
		</nav>
	</div>

	<!-- 增加产品  -->
	<div style="width: 400px; margin: 0 auto;">
		<div class="panel panel-primary">
			<div class="panel-heading" style="text-align: center;">产品添加</div>
			<div class="panel-body">
				<form action="admin_product_add" method="post" id="addForm">
					<table class="addTable">
						<tr>
							<td>产品名称</td>
							<td><input type="text" id="name" name="name" class="form-control"></td>
						</tr>
						<tr>
							<td>产品小标题</td>
							<td><input type="text" id="subTitle" name="subTitle" class="form-control"></td>
						</tr>
						<tr>
							<td>原价格</td>
							<td><input type="text" id="originalPrice" name="originalPrice" value="99.9" class="form-control"></td>
						</tr>
						<tr>
							<td>优惠价格</td>
							<td><input type="text" id="promotePrice" name="promotePrice" value="29.9" class="form-control"></td>
						</tr>
						<tr>
							<td>库存数量</td>
							<td><input type="text" id="stock" name="stock" value="99" class="form-control"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<!-- 隐式传递Category id给下个页面 -->
								<input type="hidden" name="cid" value="${category.id}" >
								<button type="submit" class="btn btn-primary">提交</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>