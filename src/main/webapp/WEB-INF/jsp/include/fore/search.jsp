<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vi
  Date: 2019/9/24
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<script>
$(function(){
   $.ajax({
       type:"get",
       url:"randomShowCategory",
       success:function(result){
           $("#searchInput").attr("placeholder",result);
       }
   });
});

</script>

<a href="${contextPath}">
    <img id="logo" src="img/site/logo.gif" class="logo">
</a>

<form action="foresearch" method="post">
    <div class="searchDiv">
        <input name="keyword" type="text" id="searchInput" >
        <button type="submit" class="searchButton">搜索</button>
        <div class="searchBelow">
            <c:forEach items="${cs}" var="category" varStatus="st">
                <c:if test="${st.count>=5 and st.count <= 8}">
                    <span>
                        <a href="forecategory?cid=${category.id}">
                            ${category.name}
                        </a>
                        <c:if test="${st.count != 8}">
                            <span>|</span>
                        </c:if>
                    </span>
                </c:if>
            </c:forEach>
        </div>
    </div>
</form>
