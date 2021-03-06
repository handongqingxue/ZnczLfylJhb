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
	position: fixed;
}
.tab1_div .toolbar{
	height:64px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .ddh_span,
.tab1_div .toolbar .row_div .ddzt_span,
.tab1_div .toolbar .row_div .cph_span,
.tab1_div .toolbar .row_div .yss_span,
.tab1_div .toolbar .row_div .wzMc_span,
.tab1_div .toolbar .row_div .fhdw_span,
.tab1_div .toolbar .row_div .shbm_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .ddh_inp,
.tab1_div .toolbar .row_div .cph_inp,
.tab1_div .toolbar .row_div .yssMc_inp,
.tab1_div .toolbar .row_div .wzMc_inp,
.tab1_div .toolbar .row_div .fhdwMc_inp,
.tab1_div .toolbar .row_div .shbmMc_inp{
	width: 120px;
	height: 25px;
}
.tab1_div .check_a{
	visibility: hidden;
}

.input_cph_bg_div{
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,.45);
	position: fixed;
	z-index: 9016;
	display:none;
}

.input_cph_div{
	width: 500px;
	height: 300px;
	margin: 200px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var ddglPath=path+'ddgl/';
var gkjPath=path+'gkj/';
var dialogTop=10;
var dialogLeft=20;
var icphdNum=0;
var dshDdztMc='${requestScope.dshDdztMc}';
$(function(){
	initDDZTCBB();
	initSearchLB();
	initManualLB();
	initDdfwLB();
	initRemoveLB();
	initTab1();
	initInputCphDialog();//0
	
	initDialogPosition();//将不同窗体移动到主要内容区域
	showCompontByQx();
});

function showCompontByQx(){
	manualLB.hide();
	removeLB.hide();
	if(yhm=="admin"){
		manualLB.show();
		removeLB.show();
	}
	else{
		var qxIdsArr=qxIds.split(",");
		for(var i=0;i<qxIdsArr.length;i++){
			if(qxIdsArr[i]==1){//仓储员
				setTimeout(function(){
					$(".tab1_div .check_a").css("visibility","visible");
				},2000)
			}
			if(qxIdsArr[i]==2){//磅房人员
				manualLB.show();
			}
		}
	}
}

function initDialogPosition(){
	var icphdpw=$("body").find(".panel.window").eq(icphdNum);
	var icphdws=$("body").find(".window-shadow").eq(icphdNum);

	var icphdDiv=$("#input_cph_div");
	icphdDiv.append(icphdpw);
	icphdDiv.append(icphdws);
}

function initInputCphDialog(){
	$("#input_cph_dialog_div").dialog({
		title:"录入车牌号",
		width:setFitWidthInParent("#input_cph_div","input_cph_dialog_div"),
		height:250,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkCphToClient();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openInputCphDialog(false);
           }}
        ]
	});

	$("#input_cph_dialog_div table").css("width",(setFitWidthInParent("#input_cph_div","input_cph_dialog_table"))+"px");
	$("#input_cph_dialog_div table").css("magin","-100px");
	$("#input_cph_dialog_div table td").css("padding-left","40px");
	$("#input_cph_dialog_div table td").css("padding-right","20px");
	$("#input_cph_dialog_div table td").css("font-size","15px");
	$("#input_cph_dialog_div table .td1").css("width","30%");
	$("#input_cph_dialog_div table .td2").css("width","60%");
	$("#input_cph_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(icphdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(icphdNum).css("color","#000");
	$(".panel.window .panel-title").eq(icphdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(icphdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(icphdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(icphdNum).css("border-color","#ddd");

	$("#input_cph_dialog_div #ok_but").css("left","30%");
	$("#input_cph_dialog_div #ok_but").css("position","absolute");

	$("#input_cph_dialog_div #cancel_but").css("left","50%");
	$("#input_cph_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");

	initBFHCBB();
	initXzCphCBB();
	initLrSjcCBB();
	initLrWscphCBB();
}

function initBFHCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	data.push({"value":1,"text":"北磅房"});
	data.push({"value":2,"text":"南磅房"});
	
	bfhCBB=$("#bfh_cbb").combobox({
		width:120,
		valueField:"value",
		textField:"text",
		//multiple:true,
		data:data
	});
}

function initXzCphCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	$.post(ddglPath+"queryXzCphCBBList",
		{page:1,rows:20,sort:"lrsj",order:"desc"},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i],"text":rows[i]});
			}
			xzcphCBB=$("#xzcph_cbb").combobox({
				width:120,
				valueField:"value",
				textField:"text",
				data:data,
				onChange:function(){
					var cph=xzcphCBB.combobox("getValue");
					lrSjcCBB.combobox("setValue",cph.substring(0,1));
					lrWscphCBB.combobox("setValue",cph.substring(1));
				}
			});
		}
	,"json");
}

function initLrSjcCBB(){
	var data=[];
	data.push({"value":"","text":"请录入"});
	lrSjcCBB=$("#lrSjc_cbb").combobox({
		width:50,
		valueField:"sjc",
		textField:"sjc",
		editable:true,
        mode:'remote',
        url:ddglPath+"queryLrSjcCBBList",
        onBeforeLoad: function(param){
    		param.page = 1;
    		param.rows = 100;
    		param.sort = "lrsj";
    		param.order = "desc";
    	}
	});
}

function initLrWscphCBB(){
	var data=[];
	data.push({"value":"","text":"请录入"});
	lrWscphCBB=$("#lrWscph_cbb").combobox({
		width:70,
		valueField:"wscph",
		textField:"wscph",
		editable:true,
        mode:'remote',
        url:ddglPath+"queryLrWscphCBBList",
        onBeforeLoad: function(param){
        	var sjc=lrSjcCBB.combobox("getValue");
        	if(sjc==null||sjc==""){
        	  	return false;
        	}
    		param.sjc = sjc;
    		param.page = 1;
    		param.rows = 100;
    		param.sort = "lrsj";
    		param.order = "desc";
    	}
	});
}

function checkCphToClient(){
	if(checkBfh()){
		if(checkRglrCph()){
			sendCphToClient();
		}
	}
}

function sendCphToClient(){
	var bfNoFlag=bfhCBB.combobox("getValue");
	var sjc=lrSjcCBB.combobox("getValue");
	var wscph=lrWscphCBB.combobox("getValue");
	var cph=sjc+wscph;
	$.post(gkjPath+"sendCphToClient",
		{cph:cph,bfNoFlag:bfNoFlag},
		function(data){
			if(data.status=="ok"){
				openInputCphDialog(false);
			}
		}
	,"json");
}

//验证磅房号
function checkBfh(){
	var bfh=bfhCBB.combobox("getValue");
	if(bfh==null||bfh==""){
	  	alert("请选择磅房");
	  	return false;
	}
	else
		return true;
}

//验证人工录入车牌号
function checkRglrCph(){
	var sjc=lrSjcCBB.combobox("getValue");
	var wscph=lrWscphCBB.combobox("getValue");
	if(sjc==null||sjc==""||wscph==null||wscph==""){
	  	alert("请录入车牌号");
	  	return false;
	}
	else
		return true;
}

function initDDZTCBB(){
	var data=[];
	data.push({"value":"","text":"请选择"});
	$.post(ddglPath+"queryDingDanZhuangTaiCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			ddztCBB=$("#ddzt_cbb").combobox({
				valueField:"value",
				textField:"text",
				//multiple:true,
				data:data
			});
		}
	,"json");
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var ddh=$("#toolbar #ddh").val();
			var ddztId=ddztCBB.combobox("getValue");
			var cph=$("#toolbar #cph").val();
			var yssMc=$("#toolbar #yssMc").val();
			var wzMc=$("#toolbar #wzMc").val();
			var fhdwMc=$("#toolbar #fhdwMc").val();
			var shbmMc=$("#toolbar #shbmMc").val();
			tab1.datagrid("load",{ddh:ddh,ddztId:ddztId,cph:cph,yssMc:yssMc,
				wzMc:wzMc,fhdwMc:fhdwMc,shbmMc:shbmMc});
		}
	});
}

function initManualLB(){
	manualLB=$("#manual_but").linkbutton({
		iconCls:"icon-save",
		onClick:function(){
			openInputCphDialog(true);
		}
	});
}

function initDdfwLB(){
	ddfwLB=$("#ddfw_but").linkbutton({
		iconCls:"icon-back",
		onClick:function(){
			var rows=tab1.datagrid("getSelections");
			if (rows.length == 0) {
				$.messager.alert("提示","请选择要复位的订单信息！","warning");
				return false;
			}
			if(checkFwddZt()){
				fwddById();
			}
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			deleteByIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"综合查询",
		url:ddglPath+"queryZHCXList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
            {field:"id",title:"操作",width:80,formatter:function(value,row){
            	var str;
           		if(row.id!="<div style=\"text-align:center;\">暂无信息<div>"){
	            	str="<a href=\"detail?id="+value+"\">详情</a>";
           		}
           		else
           			str=value;
            	return str;
            }},
			{field:"ddh",title:"订单号",width:150},
			{field:"ddztMc",title:"订单状态",width:150},
			{field:"yjzt",title:"一检状态",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="待上磅";
					break;
				case 2:
					str="上磅中";
					break;
				case 3:
					str="待称重";
					break;
				case 4:
					str="称重中";
					break;
				case 5:
					str="待下磅";
					break;
				case 6:
					str="下磅中";
					break;
				case 7:
					str="已完成";
					break;
				}
            	return str;
            }},
			{field:"ejzt",title:"二检状态",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="待上磅";
					break;
				case 2:
					str="上磅中";
					break;
				case 3:
					str="待称重";
					break;
				case 4:
					str="称重中";
					break;
				case 5:
					str="待下磅";
					break;
				case 6:
					str="下磅中";
					break;
				case 7:
					str="已完成";
					break;
				}
            	return str;
            }},
			{field:"cph",title:"车牌号",width:150},
			{field:"wzlxMc",title:"物资类型",width:150},
			{field:"wzMc",title:"物资名称",width:150},
			{field:"yssMc",title:"运输商",width:150},
			{field:"fhdwMc",title:"发货单位",width:150},
			{field:"shbmMc",title:"收货部门",width:150},
            {field:"lxlx",title:"流向类型",width:100,formatter:function(value,row){
            	var str;
            	switch (value) {
				case 1:
					str="送运";
					break;
				case 2:
					str="取运";
					break;
				}
            	return str;
            }},
            {field:"yjbfh",title:"一检地磅",width:100,formatter:function(value,row){
            	var dbm;
            	switch (value) {
				case 1:
					dbm="托利多";
					break;
				case 2:
					dbm="耀华";
					break;
				}
            	return dbm;
            }},
            {field:"ejbfh",title:"二检地磅",width:100,formatter:function(value,row){
            	var dbm;
            	switch (value) {
				case 1:
					dbm="托利多";
					break;
				case 2:
					dbm="耀华";
					break;
				}
            	return dbm;
            }},
            {field:"bjsj",title:"编辑时间",width:150}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{id:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"id",colspan:15});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function openInputCphDialog(flag){
	if(flag){
		$("#input_cph_bg_div").css("display","block");
	}
	else{
		$("#input_cph_bg_div").css("display","none");
	}
}

function deleteByIds() {
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的信息！","warning");
		return false;
	}
	
	$.messager.confirm("提示","确定要删除吗？",function(r){
		if(r){
			var ids = "";
			for (var i = 0; i < rows.length; i++) {
				ids += "," + rows[i].id;
			}
			ids=ids.substring(1);
			
			$.post(ddglPath + "deleteDingDan",
				{ids:ids},
				function(result){
					if(result.status==1){
						alert(result.msg);
						tab1.datagrid("load");
					}
					else{
						alert(result.msg);
					}
				}
			,"json");
			
		}
	});
}

function checkFwddZt(){
	var rows=tab1.datagrid("getSelections");
	var ddztMc=rows[0].ddztMc;
	var yjsbDdztMc='${requestScope.yjsbDdztMc}';
	var ejsbDdztMc='${requestScope.ejsbDdztMc}';
	if(ddztMc==yjsbDdztMc)
		return true;
	else if(ddztMc==ejsbDdztMc)
		return true;
	else{
		alert("该车辆非过磅状态");
		return false;
	}
}

function fwddById(){
	var jyFlag=0;
	var rows=tab1.datagrid("getSelections");
	var id=rows[0].id;
	var ddztMc=rows[0].ddztMc;
	var yjsbDdztMc='${requestScope.yjsbDdztMc}';
	var ejsbDdztMc='${requestScope.ejsbDdztMc}';
	var xddztMc;
	var yjzt;
	var ejzt;
	if(ddztMc==yjsbDdztMc){
		jyFlag=1
		yjzt=1;
		xddztMc='${requestScope.yjpdzDdztMc}';
	}
	else if(ddztMc==ejsbDdztMc){
		jyFlag=2
		ejzt=1;
		xddztMc='${requestScope.ejpdzDdztMc}';
	}
	$.post(ddglPath+"fwddById",
		{id:id,jyFlag:jyFlag,ddztMc:xddztMc,yjzt:yjzt,ejzt:ejzt},
		function(result){
			if(result.status==1){
				alert(result.msg);
				tab1.datagrid("load");
			}
			else{
				alert(result.msg);
			}
		}
	,"json");
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "center_con_div":
		space=205;
		break;
	case "tab1_div":
		space=250;
		break;
	case "input_cph_dialog_div":
		space=50;
		break;
	case "input_cph_dialog_table":
		space=68;
		break;
	case "panel_window":
		space=355;
		break;
	}
	var width=$(parent).css("width");
	return width.substring(0,width.length-2)-space;
}
</script>
</head>
<body>
<div class="layui-layout layui-layout-admin">
	<%@include file="../../inc/side.jsp"%>
	<div class="tab1_div" id="tab1_div">
		<div class="toolbar" id="toolbar">
			<div class="row_div">
				<span class="ddh_span">订单号：</span>
				<input type="text" class="ddh_inp" id="ddh" placeholder="请输入订单号"/>
				<span class="ddzt_span">订单状态：</span>
				<input id="ddzt_cbb"/>
				<span class="cph_span">车牌号：</span>
				<input type="text" class="cph_inp" id="cph" placeholder="请输入车牌号"/>
				<span class="yss_span">运输商：</span>
				<input type="text" class="yssMc_inp" id="yssMc" placeholder="请输入运输商"/>
			</div>
			<div class="row_div">
				<span class="wzMc_span">物资名称：</span>
				<input type="text" class="wzMc_inp" id="wzMc" placeholder="请输入物资名称"/>
				<span class="fhdw_span">发货单位：</span>
				<input type="text" class="fhdwMc_inp" id="fhdwMc" placeholder="请输入发货单位"/>
				<span class="shbm_span">收货部门：</span>
				<input type="text" class="shbmMc_inp" id="shbmMc" placeholder="请输入收货部门"/>
				<a class="search_but" id="search_but">查询</a>
				<a id="manual_but">人工</a>
				<a id="ddfw_but">订单复位</a>
				<a id="remove_but">删除</a>
			</div>
		</div>
		<table id="tab1">
		</table>
	</div>
	
	<div class="input_cph_bg_div" id="input_cph_bg_div">
		<div class="input_cph_div" id="input_cph_div">
			<div class="input_cph_dialog_div" id="input_cph_dialog_div">
				<table>
				  <tr>
					<td class="td1" align="right">
						磅房
					</td>
					<td class="td2">
						<input id="bfh_cbb"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						选择
					</td>
					<td class="td2">
						<input id="xzcph_cbb"/>
					</td>
				  </tr>
				  <tr>
					<td class="td1" align="right">
						车牌号
					</td>
					<td class="td2">
						<input id="lrSjc_cbb"/>
						<input id="lrWscph_cbb"/>
					</td>
				  </tr>
				</table>
			</div>
		</div>
	</div>
	
	<%@include file="../../inc/foot.jsp"%>
</div>
</body>
</html>