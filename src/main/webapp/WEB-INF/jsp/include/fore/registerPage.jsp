<%--
  Created by IntelliJ IDEA.
  User: vi
  Date: 2019/9/29
  Time: 8:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function(){
        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        </c:if>

    $(".registerForm").submit(function(){
    if(0==$("#name").val().length){
    $("span.errorMessage").html("请输入用户名");
    $("div.registerErrorMessageDiv").css("visibility","visible");
    return false;
    }
    if(0==$("#password").val().length){
    $("span.errorMessage").html("请输入密码");
    $("div.registerErrorMessageDiv").css("visibility","visible");
    return false;
    }
    if(0==$("#repeatPassword").val().length){
        $("span.errorMessage").html("请输入重复密码");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        return false;
    }
    if($("#password").val() != $("#repeatPassword").val()){
        $("span.errorMessage").html("两次输入的密码不一致");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        return false;
    }
    return true;
    });

    /*异步验证用户名是否可用*/
        // $("#name").blur(function(){
        //     console.log("调试代码",$(this).val());
        //     $.ajax({
        //         type:"post",
        //         url:"checkUsername",
        //         data:{
        //             username:$(this).val()
        //         },
        //         success:function(result){
        //             if("success"==result){
        //                 console.log("用户名可用");
        //                 $("#successmsg").html("用户名可用！");
        //                alert("用户名可用");
        //                 $("#registerBtn").attr("disabled",false);
        //             }
        //             if("fail"==result){
        //                 console.log("用户名不可用");
        //                alert("用户名被占用");
        //                $("#registerBtn").attr("disabled",true);
        //             }
        //         }
        //     });
        // })

    });
</script>
<style>
    div.formDiv{
       margin:60px 10px 60px 10px;
    }

    div.formDiv div{
        width:400px;
        margin:40px auto;
    }

    div.formDiv input{
        width:200px;
        height:30px;
        color:#999;
    }

    div.formDiv span{
        font-size: 12px;
        color:#999;
        width:50px;
        display:inline-block;
        text-align:center;
        margin:0px 10px;
    }

    .registerBtn {
        width:200px;
        height: 30px;
        background-color: #C40000;
        border:1px solid transparent;
        color:white;
        cursor: pointer;
        margin:0px auto;
        padding:0px;
        font-size: 16px;
        font-weight: bold;
    }

</style>

<form method="post" action="foreregister" class="registerForm">
    <div class="registerDiv">
        <div class="registerErrorMessageDiv">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" arial-label="Close"></button>
                <span class="errorMessage"></span>
            </div>
        </div>
        <div class="formDiv" >
            <div>
                <span class="registerTableLeftTD">登录名</span>
                <input type="text" id="name" name="name" placeholder="会员名一旦设置成功，无法修改">
            </div>
            <div>
                <span class="registerTableLeftTD">登录密码</span>
                <input type="password" id="password" name="password" placeholder="设置你的登录密码"></input>
            </div>
           <div>
               <span class="registerTableLeftTD">密码确认</span>
               <input type="password" id="repeatPassword" name="repeatpassword" placeholder="请再次输入你的密码" ></input>
           </div>
            <button id="registerBtn" type="submit" class="registerBtn" >提交</button>
        </div>

    </div>
</form>
