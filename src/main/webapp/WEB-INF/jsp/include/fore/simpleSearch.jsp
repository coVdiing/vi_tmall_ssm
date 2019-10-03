<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function(){
        $.ajax({
            type:"post",
            url:"randomShowCategory",
            success:function(result){
                $("div input").val(result);
                $("div input").css("color","#CCCCCC");
            }
        });
    });
</script>

<div>
    <a href="${contextPath}">
        <img id="simpleLogo" class="simpleLogo" src="img/site/simpleLogo.png">
    </a>

    <form action="foresearch" method="post">
        <div class="simpleSearchDiv pull-right">
            <input type="text" name="keyword" >
            <button type="submit" class="searchButton">搜天猫</button>
        </div>

    <div class="searchBelow">
        <c:forEach items="${categories}" var="category" varStatus="st">
            <c:if test="${st.count >= 8 and st.count <= 11}">
                    <span>
                        <a href="forecategory?cid=${category.id}">
                                ${category.name}
                        </a>
                        <c:if test="${st.count != 11}">
                            <span>|</span>
                        </c:if>
                    </span>
            </c:if>
        </c:forEach>
    </div>
    </form>
    <div style="clear:both"></div>
</div>