<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
    <%@include file="include.jsp" %>
</head>
<body>
<div id="qpanel" class="easyui-panel" width="94%"
     data-options="height:100,maximized:false,collapsible:true,collapsed:false"
     style="background:#eee"
     draggable="false"
     top="100"
     inline="true"
     title="查询条件">
    <div class="dialog-toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"
           onclick="obj.query()">查询</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true"
           onclick="obj.reset()">重置</a>
        <div style="clear:both"></div>
    </div>
    <form id="queryForm">
        <table>
            <tr>
                <td align="right">title:</td>
                <td><input name="q_title" id="q_title"/></td>
            </tr>
        </table>
    </form>
</div>
<table id="dg" class="easyui-datagrid" title="菜单列表" style="width:94%;height:380px"
       data-options="rownumbers:true, singleSelect:true, autoRowHeight:false,
				toolbar: '#toobar', url: 'dzinfo2/page2',
				pagination:true, pageSize:10">
    <thead>
    <tr>
        <th data-options="field:'id',width:80">id</th>
        <th data-options="field:'pid',width:10">pid</th>
        <th data-options="field:'title',width:320,formatter:titleFormatter">title</th>
        <th data-options="field:'type1',width:40">level</th>
        <th data-options="field:'type2',width:40">type</th>
        <th data-options="field:'catid',width:40">orderStr</th>
        <th data-options="field:'url',width:450">url</th>
        <th data-options="field:'createName',width:10">createName</th>
    </tr>
    </thead>
</table>
<div id="toobar" style="height:auto">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"
       onclick="obj.detail()">查看详情</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
       onclick="obj.new()">新增</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
       onclick="obj.edit()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
       onclick="obj.delete()">删除</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
       onclick="obj.open()">删除</a>
</div>
<div id="dlg1" class="easyui-dialog" modal="true" style="width:800px;height:400px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons1">
    <iframe></iframe>
</div>
<div id="dlg-buttons1">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')">关闭</a>
</div>
<div id="dlg" class="easyui-dialog" modal="true" style="width:800px;height:400px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post" align=center width="100%" class="connTable" height="100%" border="2"
          style="border-color: red;text-align: center">
        <input type="hidden" name="id" id="id"/>
        <table align="center">
            <tr>
                <td align=center>pid：</td>
                <td cospan="2"><input tip="pid" class="easyui-validatebox" name="pid" id="pid"
                                      data-options="required:true" style="width: 180px"/></td>
                <td align=center></td>
            </tr>
            <tr>
                <td align=center>title：</td>
                <td cospan="3"><input tip="title" class="easyui-validatebox" name="title" id="title"
                                      data-options="required:true" style="width: 180px"/></td>
            </tr>
            <tr>
                <td align=center>level：</td>
                <td><input tip="level" class="easyui-validatebox" name="level" id="level" data-options="required:true"
                           style="width: 180px"/></td>
                <td align=center>type：</td>
                <td><input tip="type" class="easyui-validatebox" name="type" id="type" data-options="required:true"
                           style="width: 180px"/></td>
            </tr>
            <tr>
                <td align=center>orderStr：</td>
                <td colspan="3"><input tip="orderStr" class="easyui-validatebox" name="orderStr" id="orderStr"
                                       data-options="required:true" style="width: 180px"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-ok" id="saveButton" onclick="obj.save()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton"
       iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
</div>
<script>
    function titleFormatter(value, row, index) {
            return "<a target='_blank' href='/spider-web/page/template.html?"+row.id+"'>"+value+"</a>";
    }

    function ztFormatter(value, row, index) {
        if (value == 0) {
            return "未提交";
        } else if (value == 1) {
            return "退回";
        } else if (value == 2) {
            return "已提交";
        } else if (value == 4) {
            return "审核通过";
        }
    }
    var obj = {
        new: function () {
            $("#saveButton").css("display", "");
            $('#dlg').dialog('open').dialog("center").dialog('setTitle', '添加');
            $('#fm').form('clear');
        },
        edit: function () {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#fm').form('load', row);
                $("#saveButton").css("display", "");
                $('#dlg').dialog('open').dialog("center").dialog('setTitle', '修改');
            } else {
                alert("请您所要操作的记录！");
            }
        },
        save: function () {
            $("#fm").jsonsubmit({
                url: 'save',
                successAfter: function (data) {
                    $('#dlg').dialog('close');
                    $('#dg').datagrid('reload');
                }
            });
        },
        detail:function(){
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#fm').form('load', row);
                $("#saveButton").css("display", "none");
                $('#dlg').dialog('open').dialog("center").dialog('setTitle', '查看详情');
            } else {
                alert("请您所要操作的记录！");
            }
        },
        open:function(){
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#fm').form('load', row);
                $('#dlg1').dialog('open').dialog("center").dialog('setTitle', '查看详情1');
            } else {
                alert("请您所要操作的记录！");
            }
        },
        delete:function(){
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $('#fm').form('load', row);
                $.messager.confirm('提示信息', '确定要删除吗?', function (r) {
                    if (r) {
                        $("#fm").jsonsubmit({
                            url: 'delete?id=' + row.id,
                            successAfter: function (data) {
                                $('#dg').datagrid('reload');
                            }
                        });
                    }
                });
            } else {
                alert("请您所要操作的记录！");
            }
        },
        query:function(){
            var queryParams = $('#dg').datagrid('options').queryParams;
            queryParams.page = 1;
            queryParams.title = $('#q_title').val();
            $('#dg').datagrid('load');
        },
        reset: function () {
            $('#queryForm').form('clear');
        }
    };
</script>
</body>
</html>