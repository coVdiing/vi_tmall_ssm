<%--
  Created by IntelliJ IDEA.
  User: vi
  Date: 2019/9/28
  Time: 6:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script>
    function showProductsAsideCategorys(cid) {
        console.log("调试代码:3");
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "white");
        $("div.eachCategory[cid=" + cid + "] a").css("color", "#87CEFA");
        console.log("调试代码:4");
        console.log("cid=",cid);
        $("div.productsAsideCategorys[cid=" + cid + "]").show();
        console.log("调试代码:5");
    }

    function hideProductsAsideCategorys(cid) {
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "#e2e2e3");
        $("div.eachCategory[cid=" + cid + "] a").css("color", "#000");
        $("div.productsAsideCategorys[cid=" + cid + "]").hide();
    }

    $(function () {
        /*分类菜单页面推荐商品的显示和退出*/
        $("div.eachCategory").mouseenter(function(){
            var cid = $(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.eachCategory").mouseleave(function(){
            var cid = $(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseenter(function(){
            var cid = $(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseleave(function(){
            var cid = $(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });

        /*导航栏上的猫耳朵出现与消失*/
        $("div.rightMenu span").mouseenter(function(){
            var left = $(this).position().left;
            var top = $(this).position().top;
            var width = $(this).css("width");
            var destLeft = parseInt(left) + parseInt(width) / 2;
            $("img#catear").css("left", destLeft);
            $("img#catear").css("top", top - 20);
            $("img#catear").fadeIn(500);
        });

        $("div.rightMenu span").mouseleave(function(){
            $("img#catear").hide();
        });

        var left = $("div#carousel-of-product").offset().left;
        $("div.categoryMenu").css("left", left - 20);
        $("div.categoryWithCarousel div.head").css("margin-left", left);
        $("div.productsAsideCategorys").css("left", left - 20);
    });
</script>
<img src="img/site/catear.png" id="catear" class="catear"/>
<div class="categoryWithCarousel">
    <div class="headbar">
        <div class="head">
            <span style="margin-left:10px;" class="glyphicon glyphicon-th-list"></span>
            <span style="margin-left:10px;">商品分类</span>
        </div>
        <div class="rightMenu">
            <span><a href="#"><img src="img/site/chaoshi.png"/></a></span>
            <span><a href="#"><img src="img/site/guoji.png"/></a></span>
            <c:forEach items="${categories}" var="category" varStatus="st">
                <c:if test="${st.count <= 4}">
                <span>
                    <a href="forecategory?cid=${category.id}">${category.name}</a>
                </span>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <div style="position:relative;">
        <%@ include file="categoryMenu.jsp" %>
    </div>

    <div style="position: relative;left:0;top:0;">
        <%@ include file="productsAsideCategorys.jsp" %>
    </div>
    <%@ include file="carousel.jsp" %>
    <div class="carouselBackgroundDiv"></div>
</div>