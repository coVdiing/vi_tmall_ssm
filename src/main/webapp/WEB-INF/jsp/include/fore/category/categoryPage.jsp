<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<title>模仿X猫官网-${category.name}</title>
<div id="category">
    <div class="categoryPageDiv">
        <div class="sortBarDiv" style="margin-left:30px;">
            <%@ include file="sortBar.jsp"%>
        </div>
        <%@ include file="productByCategory.jsp"%>
    </div>
</div>