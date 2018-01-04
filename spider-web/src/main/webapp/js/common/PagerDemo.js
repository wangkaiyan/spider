$(document).ready(function() {
	  init(1);
});

//默认加载
function init(pagenumber){
	//向服务器发送请求，查询满足条件的记录
	//$.getJSON('',{},function(data){
        //data 为返回json 对象 并包括(pagecount、totalcount)的key-value值;
		var data = {'pagecount':15,'totalcount':150};
		$("#pager").pager({ pagenumber: pagenumber, pagecount:data.pagecount,totalcount:data.totalcount, buttonClickCallback: PageClick});
	//});
}
//回调函数
PageClick = function(pageclickednumber) {
	init(pageclickednumber);
	$("#result").html("Clicked Page " + pageclickednumber);
}