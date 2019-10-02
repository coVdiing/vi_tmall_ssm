<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
    <!-- 防止空提交 -->
    $(function () {
        $("#addSinglePic").submit(function () {
            return checkEmpty("filePathSingle", "单个图片");

        });
        $("#addDetailPic").submit(function () {
            return checkEmpty("filePathDetail", "单个图片");

        });
    });
</script>
<style>
    div span.title {
        display: block;
        width: 429px;
        height: 45px;
        background: #337ab7;
        font-size: 14px;
        padding-top: 11px;
        padding-left: 21px;
        color: white;
    }

    div span.tip {
        display: block;
        width: 428px;
        height: 45px;
        font-size: 14px;
        padding-top: 11px;
        padding-left: 21px;
    }

    div.form1 {
        display: inline-block;
        width: 430px;
        height: 225px;
        border: 1px solid #337ab7;
        margin-left: 180px;
        margin-top: 50px;
    }

    div.form2 {
        display: inline-block;
        width: 430px;
        height: 225px;
        border: 1px solid #337ab7;
        margin-right: 180px;
        margin-top: 50px;
        float: right;
    }

    div.table1 {
        display: inline-block;
        width: 430px;
        margin-left: 180px;
        margin-top: 50px;
    }

    div.table2 {
        display: inline-block;
        width: 430px;
        margin-right: 180px;
        margin-top: 50px;
        float: right;
    }

    div input {
        display: block;
        margin-left: 21px;
    }

    div button {
        width: 68px;
    }
</style>
<title>产品图片管理</title>
<div style="margin: 0 40px;">
    <!-- 面包屑导航 -->
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${product.cid}">${product.category.name}</a></li>
        <li class="active">${product.name}</li>
        <li class="active">产品图片管理</li>
    </ol>

    <div class="content">
        <div class="form1">
            <form action="admin_productImage_add" id="addSinglePic" enctype="multipart/form-data" method="post">
                <span class="title">新增产品<span style="color:#C40000;font-weight:bold;">单个</span>图片</span><br>
                <span class="tip">请选择本地图片 尺寸400X400 为佳</span>
				<input id="filePathSingle" type="file" name="image" accept="image/*">
                <input type="hidden" name="pid" value="${product.id}">
                <input type="hidden" name="type" value="type_single">
                <div style="margin:20px auto;width:69px;">
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>
        </div>

        <div class="form2">
            <form id="addDetailPic" action="admin_productImage_add" method="post" enctype="multipart/form-data">
                <span class="title">新增产品<span style="color:#C40000;font-weight:bold;">详情</span>图片</span><br>
                <span class="tip">请选择本地图片 宽度790 为佳</span>
                <input id="filePathDetail" type="file" name="image"  accept="image/*">
                <input type="hidden" name="pid" value="${product.id}">
                <input type="hidden" name="type" value="type_detail">
                <div style="margin:20px auto;width:69px;">
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>
        </div>
        <div style="clear:both;"></div>

        <div class="table1">
            <table class="table table-striped table-bordered">
                <thead>
                <tr style="background:#337ab7;color:white;">
                    <th>ID</th>
                    <th>产品单个图片缩略图</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${type_single}" var="productImage">
                    <tr>
                        <td>${productImage.id}</td>
                        <td><a href="img/productSingle/${productImage.id}.jpg"
                               title="点击查看原图"> <img height="50px" alt="productImage"
                                                    src="img/productSingle/${productImage.id}.jpg">
                        </a></td>
                        <td><a
                                href="admin_productImage_delete?id=${productImage.id}"> <span
                                class="glyphicon glyphicon-trash"></span>
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="table2">
            <table class="table table-striped table-bordered">
                <thead>
                <tr style="background:#337ab7;color:white;">
                    <th>ID</th>
                    <th>产品单个详情缩略图</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${type_detail}" var="productImage">
                    <tr>
                        <td>${productImage.id}</td>
                        <td><a href="img/productDetail/${productImage.id}.jpg"
                               title="点击查看原图"> <img height="50px" alt="productImage"
                                                    src="img/productDetail/${productImage.id}.jpg">
                        </a></td>
                        <td><a
                                href="admin_productImage_delete?id=${productImage.id}"> <span
                                class="glyphicon glyphicon-trash"></span>
                        </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
