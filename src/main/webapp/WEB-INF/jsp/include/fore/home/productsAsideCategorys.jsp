<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
    $(function(){
       $("div.productsAsideCategorys div .row a").each(function(){
           var v = Math.round(Math.random()*6);
           if ( v== 1) {
               $(this).css("color","#87CEFA");
           }
       });
    });
</script>

<c:forEach items="${categories}" var="category">
    <div class="productsAsideCategorys" cid="${category.id}" >
    <c:forEach items="${category.productsByRow}" var="ps" >
        <div class="row">
            <c:forEach items="${ps}" var="product">
                <c:if test="${!empty product.subTitle}">
                    <a href="foreproduct?pid=${product.id}">
                        <c:forEach items="${fn:split(product.subTitle,' ')}" var="title" varStatus="st">
                            <c:if test="${st.index == 0}">
                                ${title}
                            </c:if>
                        </c:forEach>
                    </a>
                </c:if>
            </c:forEach>
            <div class="seperator"></div>
        </div>
    </c:forEach>
    </div>
</c:forEach>
