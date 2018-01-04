<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>数字框</title>
    <!-- 引入Jquery -->
    <script type="text/javascript"   src="<%=path%>/js/jquery.min.js" charset="utf-8"></script>
    <!-- 引入Jquery_easyui -->
   <%-- <script type="text/javascript"   src="<%=path%>/js/jquery.easyui.min.js" charset="utf-8"></script>
    <!-- 引入easyUi国际化--中文 -->
    <script type="text/javascript"   src="<%=path%>/js/easyui-lang-zh_CN.js" charset="utf-8"></script>
    <!-- 引入easyUi默认的CSS格式--蓝色 -->
    <link rel="stylesheet" type="text/css"   href="<%=path%>/js/css/easyui.css" />
    <!-- 引入easyUi小图标 -->
    <link rel="stylesheet" type="text/css"   href="<%=path%>/js/css/icon.css" />--%>

    <style type="text/css">
        #divTb
        {
            width:800px;
            border:1px solid #aaa;
            margin:0 auto;
        }
        .even{background:#CCCCCC;}
        .odd{background:#FFFFFF;}

    </style>

    <script type="text/javascript">

        //获取发布模块类型
        function getModuleInfo() {
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "/spider-web/msg/list",
                //data: { id: id, name: name },
                success: function(json) {
                    debugger
                    var typeData = json.data.list;
                    $.each(typeData, function(i, n) {
                        var tbBody = ""
                        var trColor;
                        if (i % 2 == 0) {
                            trColor = "even";
                        }
                        else {
                            trColor = "odd";
                        }
                        tbBody += "<tr class='" + trColor + "'><td>" + n.id + "</td>" + "<td><a href= \"/spider-web/page/template.html?"+ n.id+"\" target=\"_blank\">" + n.title + "</a></td>" + "<td>" + n.category + "</td><td>" + n.channel + "</td><td>" + n.summary + "</td></tr>";
                        $("#myTb").append(tbBody);
                    });
                },
                error: function(json) {
                    alert("加载失败");
                }
            });
        }
        $(function() {
            getModuleInfo();
        });


    </script>

</head>
<body>


<body>
<form id="form1" runat="server">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    抓取数据部分展示
    <div id="divTb">
        <table id="myTb" style=" width:100%">
            <tr>
                <td>id</td>
                <td>title</td>
                <td>咨询类型</td>
                <td>金属类别</td>
                <td>时间</td>
            </tr>
        </table>
    </div>
    <%--<div class="easyui-panel">
        <div class="easyui-pagination" data-options="total:114"></div>
    </div>--%>
</form>
</body>

</body>
</html>