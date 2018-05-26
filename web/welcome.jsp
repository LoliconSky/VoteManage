<%--
  Created by IntelliJ IDEA.
  User: 冰封承諾Andy
  Date: 2018/5/19
  Time: 9:39
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>首页</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

    <div id="cont"></div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            queryAllTopic();
        });

        function queryAllTopic() {
           $.ajax({
               type: "get",
               url: "VoteServlet?method=queryAllTopic",
               dataType: "json",
               success : function (data) {
                   console.log(data);
                   var main = $("#cont");

                   // 遍历题目
                   $.each(data, function (index, t) {
                       main.append($("<h3>" + t.conte + "</h3>"));
                       var ul = $("<ul></ul>");

                       // 遍历选项
                       $.each(t.choosesById, function (index, c) {
                           var li = $("<li></li>");
                           var str = c.conte + " (" + c.userChoosesById.length + "人)";
                           str += "<a href='${pageContext.request.contextPath}/VoteServlet?method=queryUsersByCid&cid="
                               + c.id + "'>查看详情</a>";
                           li.html(str);
                           ul.append(li);
                       });

                       main.append(ul);
                   })
               }
           })
        }
    </script>
</body>
</html>
