<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<div class="productReviewDiv">
    <div class="productReviewTopPart">
        <a href="#nowhere" class="productReviewTopPartSelectedLink">商品详情</a>
        <a href="#nowhere" class="selected">累计评价<span class="productReviewTopReviewLinkNumber">${product.reviewCount}条</span></a>
    </div>

    <div class="productReviewContentPart">
        <c:forEach items="${reviews}" var="review">
            <div class="productReviewItem">
                <div class="productReviewItemDesc">
                    <div class="productReviewItemContent">
                        ${review.content}
                    </div>
                    <div class="productReviewItemDate">
                        <fmt:formatDate value="${review.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </div>
                </div>
                <div class="productReviewItemUserInfo">
                    ${user.anonymousName}<span class="userInfoGrayPart">（匿名）</span>
                </div>
            </div>
            <div style="clear:both"></div>
        </c:forEach>
    </div>
</div>
