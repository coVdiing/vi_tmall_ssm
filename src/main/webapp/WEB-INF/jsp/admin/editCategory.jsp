<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	$(function() {
		$("#editCategoryForm").submit(function() {
			if (!checkEmpty("name", "分类名称"))
				return false;
			return true;
		});
	});
</script>
<title>编辑分类属性</title>
<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">所有分类</a></li>
		<li class="active">编辑分类</li>
	</ol>
</div>

<!-- 编辑分类 -->
<div class="editDiv" style="text-align:center">
	<div class="panel panel-primary">
		<div class="panel-heading">编辑分类</div>
		<div class="panel-body">
			<form action="admin_category_update?id=${category.id}"
				id="editCategoryForm" enctype="multipart/form-data" method="post">
					<table class="editTable">
						<tr>
							<td>分类名称</td>
							<td><input type="text" class="form-control" id="name"name="name" value="${category.name}"></td>
						</tr>
			  			<tr>
			  				<td>分类图片</td>
			  				<td><input type="file" accept="image/*" class="form-control" id="categoryPic" name="image"></td>
			  			</tr>
			  			<tr>
			  				<td colspan="2" align="center"><button type="submit" class="btn btn-primary" id="editCategoryBtn">提交</button></td>
			  			</tr>
					</table>
			</form>
		</div>	
	</div>
</div>