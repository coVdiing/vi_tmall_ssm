<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<title>模仿X猫官网</title>
<div class="categoryPictureInProductPageDiv">
    <img class="categoryPictureInProductPage" src="img/category/${product.category.id}.jpg">
</div>
<div class="productPageDiv">
    <%@ include file="imgAndInfo.jsp" %>
    <%@ include file="productReview.jsp" %>
    <%@ include file="productDetail.jsp" %>
</div>
