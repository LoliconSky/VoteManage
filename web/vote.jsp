<%--
  Created by IntelliJ IDEA.
  User: 冰封承諾Andy
  Date: 2018/5/26
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>投票页面</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg top-navigation">
    <div id="wrapper">
        <div id="page-wrapper" class="gray-bg">
            <%-- 表头 --%>
            <div class="row border-bottom white-bg">
                <nav class="navbar navbar-static-top" role="navigation">
                    <div class="navbar-header">
                        <button aria-controls="navbar" aria-expanded="false" data-target="#navbar"
                                data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                            <i class="fa fa-reorder"></i>
                        </button>
                        <a href="index.html" class="navbar-brand">投票管理系统</a>
                    </div>
                    <div class="navbar-collapse collapse" id="navbar">
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <a href="register.html">
                                    注册页面
                                </a>
                            </li>
                        </ul>
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <a href="login.html">
                                    登陆页面
                                </a>
                            </li>
                        </ul>
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <a href="VoteServlet">
                                    投票界面
                                </a>
                            </li>
                        </ul>
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <a href="show.html">
                                    数据统计
                                </a>
                            </li>
                        </ul>
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <a href="VoteServlet">
                                    查看我的选择
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>

            <div class="wrapper wrapper-content">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h5>请进行选择/查看选择</h5>
                                    <div class="ibox-tools">
                                        <a class="collapse-link">
                                            <i class="fa fa-chevron-up"></i>
                                        </a>
                                        <a class="close-link">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>

                                <div class="ibox-content">
                                    <form method="post" action="VoteServlet" class="form-horizontal">
                                        <input type="hidden" name="method" value="addChoose">
                                        <input type="hidden" name="uid" value="${requestScope.uid}">

                                        <c:forEach items="${requestScope.list}" var="tp" varStatus="status">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">
                                                        ${tp.topic.conte}
                                                    <!--<small class="text-navy">自定义样式</small>-->
                                                </label>

                                                <div class="col-sm-9">
                                                        <%-- 单选 --%>
                                                    <c:if test="${tp.topic.single == 0}">
                                                        <c:forEach items="${tp.chooseAndSelect}" var="cs">
                                                            <div class="radio i-checks">
                                                                <label>
                                                                    <input type="radio" ${cs.select?"checked":""}
                                                                           value="${cs.choose.id}"
                                                                           name="${status.count}">
                                                                    <i></i> ${cs.choose.conte}</label>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>

                                                        <%-- 多选 --%>
                                                    <c:if test="${tp.topic.single == 1}">
                                                        <c:forEach items="${tp.chooseAndSelect}" var="cs">
                                                            <div class="checkbox i-checks">
                                                                <label>
                                                                    <input type="checkbox" ${cs.select?"checked":""}
                                                                           value="${cs.choose.id}"
                                                                           name="${status.count}">
                                                                    <i></i> ${cs.choose.conte}</label>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                            </div>

                                            <div class="hr-line-dashed"></div>
                                        </c:forEach>


                                        <div class="form-group" id="sub">
                                            <div style="text-align: center;">
                                                <button class="btn btn-primary" type="submit">提交选择</button>
                                                <button class="btn btn-white" type="submit">取消</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>

    <!-- iCheck -->
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            isSelect();
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });

        function isSelect() {
            var s = $(":checked");
            if (s.length !== 0) {
                $("#sub").css('display', 'none');
            }
        }
    </script>
</body>
</html>
