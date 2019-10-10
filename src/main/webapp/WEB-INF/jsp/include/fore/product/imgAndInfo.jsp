<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script>
    $(function () {
        var stock = ${product.stock};
        $(".productNumberSetting").keyup(function () {
            var num = $(this).val();
            num = parseInt(num);
            if (isNaN(num))
                num = 1;
            if (num <= 0)
                num = 1;
            if (num > stock)
                num = stock;
            $(this).val(num);
        });

        $(".increaseNumber").click(function () {
            var num = $(".productNumberSetting").val();
            num++;
            if (num > stock) {
                num = stock;
            }
            $(".productNumberSetting").val(num);
        })

        $(".decreaseNumber").click(function () {
            var num = $(".productNumberSetting").val();
            num--;
            if (num <= 0)
                num = 1;
            $(".productNumberSetting").val(num);
        })

        $(".addCartLink").click(function () {
            var page = "forecheckLogin";
            $.get(
                page,
                function (result) {
                    //如果检测到已经登录,请求访问forecart
                    if ("success" == result) {
                        var id = ${product.id};
                        var num = $(".productNumberSetting").val();
                        var addCartPage = "foreaddCart";
                        $.ajax({
                            type:"get",
                            url:addCartPage,
                            data:{"pid": id, "num": num},
                            success:function (result) {
                                if ("success" == result) {
                                    $(".addCartButton").html("已加入购物车");
                                    $(".addCartButton").attr("disabled", "disabled");
                                    $(".addCartButton").css("background", "lightgray");
                                    $(".addCartButton").css("color", "black");
                                    var cartNumber = parseInt(${cartTotalItemNumber})+parseInt(num);
                                    $("span strong").html(cartNumber);
                                } else {

                                }

                            }
                        });

                    } else {
                        $("#loginModal").modal("show");
                    }
                }
            );
            return false;
        });
        $(".buyLink").click(function () {
            var page = "forecheckLogin";
            $.get(
                page,
                function (result) {
                    if ("success" == result) {
                        var num = $(".productNumberSetting").val();
                        //如果检测到已经登录，访问forebuyone?pid=${product.id}&num=num
                        location.href = $(".buyLink").attr("href") + "&num=" + num;
                    } else {
                        $("#loginModal").modal("show");
                    }
                }
            );
            return false;
        });
        /**
         * 点击模态窗口的登录按钮，触发事件
         * */
        $("button.loginSubmitButton").click(function () {
            var name = $("#name").val();
            var password = $("#password").val();
            if (0 == name.length || password.length == 0) {
                $("span.errorMessage").html("请输入账号密码");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            var page = "foreloginAjax";
            $.get(
                page,
                {
                    "name": name,
                    "password": password
                },
                function (result) {
                    if ("success" == result)
                        location.reload();
                    else {
                        $("span.errorMessage").html("账号密码错误");
                        $("div.loginErrorMessageDiv").show();
                    }
                }
            );
            return true;
        });
        $("img.smallImage").mouseenter(function () {
            var bigImageURL = $(this).attr("bigImageURL");
            $("img.bigImg").attr("src", bigImageURL);
        });
        /*预加载技术:
        对图片进行预加载，提高用户体验，当img.bigImg加载完成后会调用load函数，进而遍历小图片集合，
        * 取出它们对应的bigImageURL属性，添加到不可见的div.img4load,等需要用到这些图片的时候，
        html会先扫描DOM中是否存在，如果有就不用去服务器再次加载，从而提高图片显示的效率，优化用户体验*/
        $("img.bigImg").load(function () {
            $("img.smallImage").each(function () {
                var bigImageURL = $(this).attr("bigImageURL");
                img = new Image();
                img.src = bigImageURL;
                img.onload = function () {
                    $("div.img4load").append($(img));
                }
            });
        });
    });
</script>

<div class="imgAndInfo">
    <div class="imgInimgAndInfo">
        <img src="img/productSingle/${product.firstProductImage.id}.jpg" class="bigImg">
        <div class="smallImageDiv">
            <c:forEach items="${product.productSingleImages}" var="pi">
                <img src="img/productSingle_small/${pi.id}.jpg" bigImageURL="img/productSingle/${pi.id}.jpg"
                     class="smallImage">
            </c:forEach>
        </div>
        <div class="img4load hidden"></div>
    </div>
    <div class="infoInimgAndInfo">
        <div class="productTitle">
            ${product.name}
        </div>
        <div class="productSubTitle">
            ${product.subTitle}
        </div>
        <div class="productPrice">
            <div class="juhuasuan">
                <span class="juhuasuanBig">聚划算</span>
                <span>此商品即将参加聚划算,<span class="juhuasuanTime">1天19小时后开始</span></span>
            </div>
            <div class="originalDiv">
                <span class="originalPriceDesc">价格</span>
                <span class="originalPriceYuan">￥</span>
                <span class="originalPrice">
                    <fmt:formatNumber type="number" value="${product.originalPrice}" minFractionDigits="2"/>
                </span>
            </div>
            <div class="promoteDiv">
                <span class="promotionPriceDesc">促销价</span>
                <span class="promotionPriceYuan">￥</span>
                <span class="promotionPrice">
                    <fmt:formatNumber type="number" value="${product.promotePrice}"
                                      minFractionDigits="2"></fmt:formatNumber>
                </span>
            </div>
        </div>

        <div class="productSaleAndReviewNumber">
            <div>销量<span class="redColor boldWord">${product.saleCount}</span></div>
            <div>累计评价<span class="redColor boldWord">${product.reviewCount}</span></div>
        </div>
        <div class="productNumber">
            <span>数量</span>
            <span>
            <span class="productNumberSettingSpan">
                <input type="text" value="1" class="productNumberSetting">
            </span>
            <span class="arrow">
                <a href="#nowhere" class="increaseNumber">
                    <span class="updown">
                        <img src="img/site/increase.png">
                    </span>
                </a>
                <span class="updownMiddle"></span>
                <a class="decreaseNumber" href="#nowhere">
                    <span class="updown">
                        <img src="img/site/decrease.png"/>
                    </span>
                </a>
           </span>
                件
            </span>
                <span>库存${product.stock}件</span>
        </div>
        <div class="serviceCommitment">
            <span class="serviceCommitmentDesc">服务承诺</span>
            <span class="serviceCommitmentLink">
            <a href="#nowhere">正品保证</a>
            <a href="#nowhere">极速退款</a>
            <a href="#nowhere">赠运费险</a>
            <a href="#nowhere">七天无理由退换</a>
        </span>
        </div>

        <div class="buyDiv">
            <a class="buyLink" href="forebuyone?pid=${product.id}">
                <button class="buyButton">立即购买</button>
            </a>
            <a href="#nowhere" class="addCartLink">
                <button class="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button>
            </a>
        </div>
    </div>
    <div style="clear:both;"></div>
</div>
