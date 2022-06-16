<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tab1_div{
	margin-top:80px;
	margin-left: 220px;
}
.tab1_div .toolbar{
	height:64px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .cph_span,
.tab1_div .toolbar .row_div .yssMc_span,
.tab1_div .toolbar .row_div .fhdwMc_span,
.tab1_div .toolbar .row_div .shbmMc_span,
.tab1_div .toolbar .row_div .wzlxMc_span,
.tab1_div .toolbar .row_div .wzMc_span{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .cph_inp,
.tab1_div .toolbar .row_div .yssMc_inp,
.tab1_div .toolbar .row_div .fhdwMc_inp,
.tab1_div .toolbar .row_div .shbmMc_inp,
.tab1_div .toolbar .row_div .wzlxMc_inp,
.tab1_div .toolbar .row_div .wzMc_inp{
	width: 120px;
	height: 25px;
}
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var clglPath=path+'clgl/';
var lx='${requestScope.lx}';
$(function(){
	initSearchLB();
	initTab1();
});

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var cph=$("#toolbar #cph").val();
			var yssMc=$("#toolbar #yssMc").val();
			var fhdwMc=$("#toolbar #fhdwMc").val();
			var shbmMc=$("#toolbar #shbmMc").val();
			var wzlxMc=$("#toolbar #wzlxMc").val();
			var wzMc=$("#toolbar #wzMc").val();
			tab1.datagrid("load",{cph:cph,lx:lx,yssMc:yssMc,fhdwMc:fhdwMc,shbmMc:shbmMc,wzlxMc:wzlxMc,wzMc:wzMc});
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"已识别车辆查询",
		url:clglPath+"queryZHCXList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		queryParams:{lx:lx},
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"cph",title:"车牌号",width:100},
			{field:"wcddcs",title:"完成订单次数",width:110},
			{field:"yssMc",title:"运输商",width:150},
			{field:"fhdwMc",title:"发货单位",width:150},
			{field:"shbmMc",title:"收货部门",width:150},
			{field:"wzlxMc",title:"物资类型",width:150},
			{field:"wzMc",title:"物资名称",width:150},
			{field:"cjsj",title:"创建时间",width:150},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"\">编辑</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{cph:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"cph",colspan:9});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function setFitWidthInParent(o){
	var width=$(o).css("width");
	return width.substring(0,width.length-2)-250;
}
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<%@include file="../../inc/side.jsp"%>
	<div class="tab1_div" id="tab1_div">
		<div class="toolbar" id="toolbar">
			<div class="row_div">
				<span class="cph_span">车牌号：</span>
				<input type="text" class="cph_inp" id="cph" placeholder="请输入车牌号"/>
				<span class="yssMc_span">运输商：</span>
				<input type="text" class="yssMc_inp" id="yssMc" placeholder="请输入运输商"/>
				<span class="fhdwMc_span">发货单位：</span>
				<input type="text" class="fhdwMc_inp" id="fhdwMc" placeholder="请输入发货单位"/>
			</div>
			<div class="row_div">
				<span class="shbmMc_span">收货部门：</span>
				<input type="text" class="shbmMc_inp" id="shbmMc" placeholder="请输入收货部门"/>
				<span class="wzlxMc_span">物资类型：</span>
				<input type="text" class="wzlxMc_inp" id="wzlxMc" placeholder="请输入物资类型"/>
				<span class="wzMc_span">物资名称：</span>
				<input type="text" class="wzMc_inp" id="wzMc" placeholder="请输入物资名称"/>
				<a class="search_but" id="search_but">查询</a>
			</div>
		</div>
		<table id="tab1">
		</table>
	</div>
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>