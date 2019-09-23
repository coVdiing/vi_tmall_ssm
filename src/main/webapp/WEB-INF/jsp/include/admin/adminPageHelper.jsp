<%@ page language="java" contentType="text/html; charset=UTF-8" %>
	<!-- 使用PageHelper插件分页 -->
	<nav>
		<ul class="pagination">
			<li><a href="?page=1">首页</a></li>
			<c:if test="${pageInfo.hasPreviousPage}">
				<li><a href="?page=${pageInfo.pageNum-1}">上一页</a></li>
			</c:if>
			<c:forEach items="${pageInfo.navigatepageNums}" var="cur">
				<li <c:if test="${pageInfo.pageNum == cur }">class="active"</c:if>>
					<a href="?page=${cur}">${cur}</a>
				</li>
			</c:forEach>
			<c:if test="${pageInfo.hasNextPage}">
				<li><a href="?page=${pageInfo.pageNum+1}">下一页</a></li>
			</c:if>
			<li><a href="?page=${pageInfo.pages}">末页</a></li>
		</ul>
	</nav>