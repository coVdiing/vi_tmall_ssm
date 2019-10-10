<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="searchResult">

    <div class="searchResultDiv">
        <div class="searchProducts">

            <div class="CategorySortBar">
                <table class="categorySortBarTable categorySortTable">
                    <tr>
                        <td  <c:if test="${'all'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                            <a href="?keyword=${param.keyword}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a>
                        </td>
                        <td  <c:if test="${'review'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                            <a href="?keyword=${param.keyword}&sort=review">人气<span class="glyphicon glyphicon-arrow-down"></span></a>
                        </td>
                        <td  <c:if test="${'date'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                            <a href="?keyword=${param.keyword}&sort=date">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
                        </td>
                        <td  <c:if test="${'saleCount'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                            <a href="?keyword=${param.keyword}&sort=saleCount">销量<span class="glyphicon glyphicon-arrow-down"></span></a>
                        </td>
                        <td  <c:if test="${'priceAsc'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                            <a href="?keyword=${param.keyword}&sort=priceAsc">价格<span class="glyphicon glyphicon-arrow-up"></span></a>
                        </td>
                        <td  <c:if test="${'priceDesc'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                            <a href="?keyword=${param.keyword}&sort=priceDesc">价格<span class="glyphicon glyphicon-arrow-down"></span></a>
                        </td>
                    </tr>
                </table>
            </div>

            <c:forEach items="${products}" var="product">
                <div class="productUnit">
                    <a href="foreproduct?pid=${product.id}">
                        <img class="productImage" src="img/productSingle/${product.firstProductImage.id}.jpg"/>
                    </a>
                    <span class="productPrice">￥<fmt:formatNumber type="number" value="${product.promotePrice}" minFractionDigits="2"></fmt:formatNumber></span>
                    <a class="productLink" href="foreproduct?pid=${product.id}">
                            ${fn:substring(product.name,0,10)}
                    </a>
                    <a href="tmallLink" href="foreproduct?pid=${product.id}">天猫专卖</a>

                    <div class="productInfo">
                        <span class="monthDeal">月成交<span class="productDealNumber">${product.saleCount}</span></span>
                        <span class="productReview">评价<span class="productReviewNumber">${product.reviewCount}</span></span>
                        <span class="wangwang" ><img src="img/site/wangwang.png"/></span>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty products}">
                <div class="noMatch">没有满足条件的产品</div>
            </c:if>
            <div style="clear:both"></div>
        </div>

    </div>

</div>