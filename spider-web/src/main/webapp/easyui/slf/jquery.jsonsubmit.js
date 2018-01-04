(function($) {
    var defaults = {
        errorDispaly: function(title, msg) {
            $.messager.show({
                title: title,
                msg: msg,
                timeout: 20000,
                showType: 'fade'
            });

        },
        successAfter: null,
        /**
         * 客户端跳转时候使用的
         */

        /**
         *默认post，还可以设置其他的
         */
        type: 'POST',
        /***
         *默认肯定是json
         */
        contentType: 'application/json;charset=UTF-8',
        /**
         *默认结果返回的也是json
         */
        dataType: "json",
        data: null,
        url: "",
        /*
         * form校验方法 这个考虑默认委托jqueryeasy ui的校验
         */
        validate: function(form, settings) {

            return form.form("validate");
        },
        /**
         * ajax 提交返回成功时候的方法
         * 默认不处理数据仅仅提示操纵成功信息
         * 然后如果有跳转则跳转
         */
        success: function(data, textStatus) {
            var rstr = "";
           
            if (data.redirectUrl != undefined && data.redirectUrl != null && data.redirectUrl != "") {
                rstr = "(30秒后跳转)<br/><a href='"+data.redirectUrl+"'>点击立即跳转</a>";
                window.setTimeout(function() {
                    location.href = data.redirectUrl;
                }, 30000);
               
            }
            var messageStr = "";
            if (data.messages != undefined &&
                    data.messages != null) {
                for (var i = 0; i < data.messages.length; i++) {  
                
                    messageStr += "," + data.messages[i];                   
                }
            }
          
            if (messageStr.length > 0) {
               
                messageStr = messageStr.substring(1);
            }
            
            messageStr=messageStr+rstr;
           
            if (data.flag != undefined && data.flag != null && data.flag != "") {
                if (data.flag == 400) {

                    // $.messager.alert('服务器端验证错误', messageStr + rstr + '！', null, redirect);
                    settings.errorDispaly("服务器端验证错误", messageStr);

                }
                else if (data.flag == 401 || data.flag == 403) {

                    //$.messager.alert('本次操作未授权', messageStr + rstr + '！', null, redirect);
                    settings.errorDispaly("本次操作未授权", messageStr);

                }
                else if (data.flag == 500) {

                    //$.messager.alert('本次操作执行失败', messageStr + rstr + '！', null, redirect);

                    settings.errorDispaly("本次操作执行失败", messageStr);

                } else {
                    if (messageStr != "") {
                       
                        settings.errorDispaly("操作成功", messageStr);
                        //$.messager.alert('操作成功', messageStr + rstr + '！', null, redirect);

                    } else {
                        settings.errorDispaly("操作成功", "本次操作成功执行!"+rstr);
                    }

                }


            } else {
                //-------
                if (messageStr != "") {
                    //$.messager.alert('操作成功', messageStr + rstr + '！', null, redirect);
                    settings.errorDispaly("操作成功", "数据格式不正确," + messageStr);

                } else {

                    // $.messager.alert('操作成功', '本次操作成功执行)' + rstr + '！', null, redirect);
                    settings.errorDispaly("操作成功", "数据格式不正确!"+rstr);


                }
                //-------

            }
            if (settings.successAfter != undefined && settings.successAfter != null) {
                settings.successAfter(data);
            }




        }
        ,
        /**
         * ajax提交返回失败时候的方法
         */
        error: function(jqXHR, textStatus, errorThrown) {

            //暂时系统不用这个方法了

        },
        /**
         * form字段转换的json的方法
         */
        form2json: function(form, settings) {
            var json = form.form2json(settings);
            return json;
        }
    }, settings = {};

    $.fn.jsonsubmit = function(options) {

        var form = $(this);
        //   不是form函数返回
        //if (!form.is('form')) {
        //   return;
        //}

        settings = $.extend(true, {}, defaults, options);
        //客户端校验未通过返回
        if (!settings.validate(this, settings)) {
            //alert(11);
            return;
        }
        var jsonData = settings.data;
        if (jsonData == null) {
            var jsonData = settings.form2json(this, settings);


        }
        jsonData = JSON.stringify(jsonData);

        jQuery.ajax({
            type: settings.type,
            contentType: settings.contentType,
            url: settings.url,
            data: jsonData,
            dataType: settings.dataType,
            success: function(data, textStatus) {
                settings.success(data, textStatus);

            },
            error: settings.error
        });
    };

})(jQuery);