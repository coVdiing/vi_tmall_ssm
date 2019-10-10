<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	$(function() {
		$("ul.pagination li.disabled a").click(function() {
			return false;
		});

		$("#addCategoryForm").submit(function() {
			if (!checkEmpty("name", "分类名称"))
				return false;
			return checkEmpty("categoryPic", "分类图片");

		});
		
		
	});
</script>
<title>分类详情</title>
 <br>
 <br>

<div class="container">
	<!-- 分类数据展示 -->
	<div class="table">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="info">
					<th>分类id</th>
					<th>分类名称</th>
					<th>图片</th>
					<th>属性管理</th>
					<th>产品管理</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>
							<img src="img/category/${item.id}.jpg" height=40px alt="" />
						</td>
						<td align="center"><a href="admin_property_list?id=${item.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
						<td align="center"><a href="admin_product_list?cid=${item.id}"><span
								class="glyphicon glyphicon-shopping-cart"></span></a></td>
						<td align="center"><a href="admin_category_edit?id=${item.id}"><span
								class="glyphicon glyphicon-th-list"></span></a></td>
						<td align="center"><a deleteLink="true"
							href="admin_category_delete?id=${item.id}"><span
								class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="pageDiv">
<%@ include file="../include/admin/adminPageHelper.jsp" %>
</div>

<!-- 增加分类 -->
<div class="addDiv" style="text-align:center">
	<div class="panel panel-primary">
		<div class="panel-heading">增加分类</div>
		<div class="panel-body">
			<form action="admin_category_add" id="addCategoryForm"
				enctype="multipart/form-data" method="post">
				<table class="addTable">
					<tr>
						<td>分类名称</td>
						<td><input type="text" id="name" name="name" class="form-control"></td>
					</tr>
					<tr>
			  			<td>分类图片</td>
			  			<td><input type="file" accept="image/*" class="form-control" id="categoryPic" name="image"></td>
			  		</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="submit" class="btn btn-primary">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
