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
    <script type="text/javascript"   src="<%=path%>/js/jquery.easyui.min.js" charset="utf-8"></script>
    <!-- 引入easyUi国际化--中文 -->
    <script type="text/javascript"   src="<%=path%>/js/easyui-lang-zh_CN.js" charset="utf-8"></script>
    <!-- 引入easyUi默认的CSS格式--蓝色 -->
    <link rel="stylesheet" type="text/css"   href="<%=path%>/js/css/easyui.css" />
    <!-- 引入easyUi小图标 -->
    <link rel="stylesheet" type="text/css"   href="<%=path%>/js/css/icon.css" />

    <script type="text/javascript">
        $(function() {
            $('#mydatagrid').datagrid({
                title : 'datagrid实例',
                iconCls : 'icon-ok',
                width : 600,
                pageSize : 5,//默认选择的分页是每页5行数据
                pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
                nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
                striped : true,//设置为true将交替显示行背景。
                collapsible : true,//显示可折叠按钮
                toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个
                url:'/spider/msg/list',//url调用Action方法
                loadMsg : '数据装载中......',
                singleSelect:true,//为true时只能选择单行
                fitColumns:true,//允许表格自动缩放，以适应父容器
                //sortName : 'xh',//当数据表格初始化时以哪一列来排序
                //sortOrder : 'desc',//定义排序顺序，可以是'asc'或者'desc'（正序或者倒序）。
                remoteSort : false,
                frozenColumns : [ [ {
                    field : 'ck',
                    checkbox : true
                } ] ],
                pagination : true,//分页
                rownumbers : true//行数
            });

        });


        $(document).ready(function(){
            $.ajax({
                type : "GET",  //提交方式
                url : "/spider/msg/list",//路径
                data : {
                    "org.id" : "${org.id}"
                },//数据，这里使用的是Json格式进行传输
                success : function(result) {//返回数据根据结果进行相应的处理
                    list = result.data.list;
                }
            });
        });

    </script>

</head>
<body>
<h2>
    <b>easyui的DataGrid实例</b>
</h2>

<table id="mydatagrid">
    <thead>
    <tr>
        <th data-options="field:'id',width:100,align:'center'">学生学号</th>
        <th data-options="field:'name',width:100,align:'center'">姓名</th>
        <th data-options="field:'gender',width:100,align:'center'">性别</th>
        <th data-options="field:'age',width:100,align:'center'">年龄</th>
    </tr>
    </thead>
</table>

</body>
</html>