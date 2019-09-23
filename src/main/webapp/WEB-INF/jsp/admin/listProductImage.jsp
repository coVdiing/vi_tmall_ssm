<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>
<script>
<!-- 防止空提交 -->
	$(function() {
		$("#addSinglePic").submit(function() {
			if(!checkEmpty("filePathSingle","单个图片"))
				return false;
			return true;
		});
		$("#addDetailPic").submit(function() {
			if(!checkEmpty("filePathDetail","单个图片"))
				return false;
			return true;
		});
	});
</script>
<style>

</style>
<title>产品图片管理</title>
<div style="margin: 0 40px;">
	<!-- 面包屑导航 -->
	<ol class="breadcrumb">
		<li><a href="admin_category_list">所有分类</a></li>
		<li><a href="admin_product_list?cid=${product.cid}">${product.category.name}</a></li>
		<li class="active">${product.name}</li>
		<li class="active">产品图片管理</li>
	</ol>



	<table align="center" >
		<tr>
			<td style="padding-right: 50px;">
				<div class="panel panel-primary">
					<div class="panel-heading">
						新增产品<b class="text text-danger">单个</b>图片
					</div>
					<div class="panel-body" style="width: 400px;">
					<form id="addSinglePic" action="admin_productImage_add" method="post" enctype="multipart/form-data">
						<!-- 添加图片  -->
						<table class="addTable">
								<tr>
									<td>请选择本地图片 尺寸400X400 为佳</td>
								</tr>
								<tr>
									<td><input id="filePathSingle" type="file" name="image"></td>
								</tr>
								<tr>
									<td align="center">
										<input type="hidden" name="pid" value="${product.id}">
										<input type="hidden" name="type" value="type_single">
										<button type="submit"class="btn btn-primary">提交</button>
										</td>
								</tr>					
						</table>
						</form>
					</div>
				</div>
			</td>

			<td>
				<div class="panel panel-primary">
					<div class="panel-heading">
						新增产品<b class="text text-danger">详情</b>图片
					</div>
					<div class="panel-body" style="width: 400px;">
						<!-- 添加图片  -->
						<form id="addDetailPic" action="admin_productImage_add"
								method="post" enctype="multipart/form-data">
						<table class="addTable">
					
							<tr>
								<td>请选择本地图片 790宽度为佳</td>
							</tr>
							<tr>
								<td><input type="file" id="filePathDetail" accept="image/*"
									name="image"></td>
							</tr>
							<tr>
								<td align="center"><input type="hidden" name="pid"
									value="${product.id}"> <input type="hidden" name="type"
									value="type_detail">
									<button type="submit" class="btn btn-primary">提交</button></td>
							</tr>
						</table>
						</form>
					</div>
				</div>
			</td>
		</tr>
		<!-- 数据展示 -->
		<tr>
			<td>
				<table class="table table-striped table-bordered"
					style="width: 100%; width: 400px;">
					<thead>
						<tr style="background-color: #337ab7;">
							<th>ID</th>
							<th>产品单个图片缩略图</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${type_single}" var="productImage">
							<tr>
								<td>${productImage.id}</td>
								<td><a href="img/productSingle/${productImage.id}.jpg"
									title="点击查看原图"> <img height="50px"
										src="img/productSingle/${productImage.id}.jpg">
								</a></td>
								<td><a
									href="admin_productImage_delete?id=${productImage.id}"> <span
										class="glyphicon glyphicon-trash"></span>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</td>
			<td>
				<table class="table table-striped table-bordered"
					style="width: 100%; width: 400px;">
					<thead>
						<tr style="background-color: #337ab7;">
							<th>ID</th>
							<th>产品详情图片缩略图</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${type_detail}" var="productImage">
							<tr>
								<td>${productImage.id}</td>
								<td><a href="img/productDetail/${productImage.id}.jpg"
									title="点击查看原图"> <img height="50px"
										src="img/productDetail/${productImage.id}.jpg">
								</a></td>
								<td><a
									href="admin_productImage_delete?id=${productImage.id}"> <span
										class="glyphicon glyphicon-trash"></span>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</div>