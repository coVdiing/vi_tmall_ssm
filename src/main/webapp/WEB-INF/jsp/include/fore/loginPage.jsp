<%--
  Created by IntelliJ IDEA.
  User: vi
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<script>
    $(function(){
        <c:if test="${!empty msg}">
            $("span.errorMessage").html("${msg}");
            $("div.loginErrorMessageDiv").show();
        </c:if>

        $("form.loginForm").submit(function(){
            if(0==$("#name").val().length || 0==$("#password").val().length) {
                $("span.errorMessage").html("请输入账号密码");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            return true;
        });

        $("form.loginForm input").keyup(function(){
            $("div.loginErrorMessageDiv").hide();
        });


    });
</script>

<div class="loginDiv" style="position:relative">
    <div class="simpleLogo">
        <a href="${contextPath}" ><img src="img/site/simpleLogo.png"/></a>
    </div>

    <img id="loginBackgroundImg" class="loginBackgroundImg" src="img/site/loginBackground.png">

    <form class="loginForm" action="forelogin" method="post">
            <div id="loginSmallDiv" class="loginSmallDiv">
                <div class="loginErrorMessageDiv">
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert"></button>
                        <span class="errorMessage"></span>
                    </div>
                </div>

                <div class="login_account_text" style="font-weight: bold;">账户登录</div>
                <div class="loginInput">
                    <span class="loginInputIcon">
                        <span class="glyphicon glyphicon-user"></span>
                    </span>
                    <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">
                </div>

                <div class="loginInput">
                    <span class="loginInputIcon">
                        <span class="glyphicon glyphicon-lock"></span>
                    </span>
                    <input type="password" name="password"  id="password" placeholder="密码">
                </div>
                <span class="text-danger">不要输入真实的天猫账号密码</span><br><br>

                <div>
                    <a href="#nowhere" class="notImplementLink" href="#nowhere">忘记登录密码</a>
                    <a href="registerPage" class="pull-right">免费注册</a>
                </div>
                <div style="margin-top:20px">
                    <button class="btn btn-block redButton" type="submit">登录</button>
                </div>
            </div>
    </form>

</div>