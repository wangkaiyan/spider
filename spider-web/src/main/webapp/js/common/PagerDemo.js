$(document).ready(function() {
	  init(1);
});

//Ĭ�ϼ���
function init(pagenumber){
	//��������������󣬲�ѯ���������ļ�¼
	//$.getJSON('',{},function(data){
        //data Ϊ����json ���� ������(pagecount��totalcount)��key-valueֵ;
		var data = {'pagecount':15,'totalcount':150};
		$("#pager").pager({ pagenumber: pagenumber, pagecount:data.pagecount,totalcount:data.totalcount, buttonClickCallback: PageClick});
	//});
}
//�ص�����
PageClick = function(pageclickednumber) {
	init(pageclickednumber);
	$("#result").html("Clicked Page " + pageclickednumber);
}