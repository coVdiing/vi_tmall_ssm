<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>



<!-- 展示数据  -->

<div style="width:50%;margin:0 auto;">
		<h3>用户管理</h3>
		<table class="table table-striped table-hover table-bordered table-condensed" >
			<thead>
				<tr class="info">
					<td>ID</td>
					<td>用户名</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${list}">
					<tr>
						<td>${user.id}</td>
						<td>${user.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</div>

<div align="center">
	<%@ include file="../include/admin/adminPageHelper.jsp" %>
</div>
