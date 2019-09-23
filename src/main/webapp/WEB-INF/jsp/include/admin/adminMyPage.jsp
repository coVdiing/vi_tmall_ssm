
<script>
	$(function() {
		$(".pagination li a .disabled").click(function(){
			return false;
		});
	});
</script>
<!-- 导航分页部分 -->
	<nav>
		<ul class="pagination">
			<li><a href="?start=0">&laquo;</a></li>
			<li <c:if test="${!page.hasPrevious}">class="disabled"</c:if>><a
				href="?start=${page.start-page.count}"><span><</span></a></li>

			<c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
				<c:if
					test="${status.count >= page.current-2 && status.count <= page.current+2}">
					<li
						<c:if test="${status.count==page.current}">class="active"</c:if>>
						<a href="?start=${status.index*page.count}">${status.count}</a>
					</li>
				</c:if>

			</c:forEach>
			<li <c:if test="${!page.hasNext}">class="disabled"</c:if>><a
				href="?start=${page.start+page.count}"><span>></span></a></li>
			<li><a href="?start=${page.last}">&raquo;</a></li>
		</ul>
	</nav>