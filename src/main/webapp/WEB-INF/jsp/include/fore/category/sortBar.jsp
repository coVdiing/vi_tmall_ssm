<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(function () {

    })
</script>
<div class="CategorySortBar">
    <table class="categorySortBarTable categorySortTable">
        <tr>
            <td  <c:if test="${'all'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
               <a href="?cid=${category.id}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a>
            </td>
            <td  <c:if test="${'review'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                <a href="?cid=${category.id}&sort=review">人气<span class="glyphicon glyphicon-arrow-down"></span></a>
            </td>
            <td  <c:if test="${'date'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                <a href="?cid=${category.id}&sort=date">新品<span class="glyphicon glyphicon-arrow-down"></span></a>
            </td>
            <td  <c:if test="${'saleCount'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                <a href="?cid=${category.id}&sort=saleCount">销量<span class="glyphicon glyphicon-arrow-down"></span></a>
            </td>
            <td  <c:if test="${'priceAsc'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                <a href="?cid=${category.id}&sort=priceAsc">价格<span class="glyphicon glyphicon-arrow-up"></span></a>
            </td>
            <td  <c:if test="${'priceDesc'==param.sort || empty param.sort}">class="grayColumn"</c:if>>
                <a href="?cid=${category.id}&sort=priceDesc">价格<span class="glyphicon glyphicon-arrow-down"></span></a>
            </td>
        </tr>
    </table>

    <table class="categorySortBarTable">
        <tr>
            <td><input class="sortBarPrice beginPrice" type="text" placeholder="请输入"></td>
            <td class="grayColumn priceMiddleColumn">-</td>
            <td><input class="sortBarPrice endPrice" type="text" placeholder="请输入"></td>
        </tr>
    </table>
</div>