<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
	$(function(){
		$("#editProperty").submit(function() {
			if(!checkEmpty("editName","属性名称"))
				return false;
			return true;
		});
	});
</script>
<title>编辑属性</title>
<div style="margin:0 40px">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">全部分类</a></li>
		<li><a href="admin_property_list?id=${property.cid}">${category.name}</a></li>
		<li class="active">编辑属性</li>
	</ol>
</div>

<div style="width:400px;margin:0 auto;text-align:center">
	<div class="panel panel-primary">
		<div class="panel-heading">编辑属性</div>
		<div class="panel-body">
			<form action="admin_property_update" method="post" id="editProperty">
				<table style="width:100%">
					<tr>
						<td style="padding:5px">属性名称</td>
						<td style="padding:5px"><input type="text" name="name" id="editName" value="${property.name}" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2" align="center" style="padding:5px">
							<input type="hidden" name="cid" value="${category.id}" >
			  				<input type="hidden" name="id" value="${property.id}">
							<button type="submit" class="btn btn-primary">提交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>