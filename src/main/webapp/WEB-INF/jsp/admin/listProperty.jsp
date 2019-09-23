<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
	$(function(){
		$("#addForm").submit(function(){
			if(!checkEmpty("name","属性名称"))
				return false;
			return true;
		});
	});
</script>
<title>属性管理</title>
<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">全部分类</a></li>
		<li><a href="admin_property_list?id=${category.id}">${category.name}</a></li>
		<li class="active">属性管理</li>
	</ol>
</div>

<div class="container">
	<div class="table">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr>
					<th>id</th>
					<th>属性名称</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${props}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td><a href="admin_property_edit?id=${item.id}"><span
								class="glyphicon glyphicon-th-list"></span></a></td>
						<td><a deleteLink="true"
							href="admin_property_delete?id=${item.id}"><span
								class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!-- 分页  -->
<div style="text-align:center">
<!-- 使用PageHelper插件分页 -->
	<nav>
		<ul class="pagination">
			<li><a href="?page=1&id=${category.id}">首页</a></li>
			<c:if test="${pageInfo.hasPreviousPage}&id=${category.id}">
				<li><a href="?page=${pageInfo.pageNum-1}&id=${category.id}">上一页</a></li>
			</c:if>
			<c:forEach items="${pageInfo.navigatepageNums}" var="cur">
				<li <c:if test="${pageInfo.pageNum == cur }">class="active"</c:if>>
					<a href="?page=${cur}&id=${category.id}">${cur}</a>
				</li>
			</c:forEach>
			<c:if test="${pageInfo.hasNextPage}">
				<li><a href="?page=${pageInfo.pageNum+1}&id=${category.id}">下一页</a></li>
			</c:if>
			<li><a href="?page=${pageInfo.pages}&id=${category.id}">末页</a></li>
		</ul>
	</nav>
</div>

<!-- 增加属性  -->
<div style="width:400px;margin:0px auto;text-align:center">
	<div class="panel panel-primary">
		<div class="panel-heading">增加属性</div>
		<div class="panel-body">
			<form id="addForm" action="admin_property_add" method="post">
				<table style="width:100%;">
					<tr>
						<td style="padding:5px;">属性名称</td>
						<td style="padding:5px;"><input type="text" name="name" id="name" class="form-control"></td>
					</tr>
					<tr>
						<td  colspan="2" align="center" style="padding:5px;">
							<!-- 用隐藏的input传递cid给add方法 -->
							<input type="hidden" name="cid" value="${category.id}">
							<button type="submit" id="addPropertyBtn" class="btn btn-primary">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>