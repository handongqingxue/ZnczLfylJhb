<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<style type="text/css">
.center_con_div{
	height: 90vh;
	margin-left:205px;
	position: absolute;
}
.page_location_div{
	height: 50px;
	line-height: 50px;
	margin-top: 60px;
	margin-left: 20px;
	font-size: 18px;
}
.cph_inp{
	width: 150px;
	height:30px;
}
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var clglPath=path+'clgl/';
var wzglPath=path+'wzgl/';
var dwglPath=path+'dwgl/';
var dialogTop=70;
var dialogLeft=20;
var ndNum=0;
$(function(){
	initNewDialog();//0

	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	//基本属性组
	var ndpw=$("body").find(".panel.window").eq(ndNum);
	var ndws=$("body").find(".window-shadow").eq(ndNum);

	var ccDiv=$("#center_con_div");
	ccDiv.append(ndpw);
	ccDiv.append(ndws);
	ccDiv.css("width",setFitWidthInParent("body","center_con_div")+"px");
}

function initNewDialog(){
	dialogTop+=20;
	$("#new_div").dialog({
		title:"已识别车辆信息",
		width:setFitWidthInParent("body","new_div"),
		height:235,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"保存",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkNew();
           }}
        ]
	});

	$("#new_div table").css("width",(setFitWidthInParent("body","new_div_table"))+"px");
	$("#new_div table").css("magin","-100px");
	$("#new_div table td").css("padding-left","50px");
	$("#new_div table td").css("padding-right","20px");
	$("#new_div table td").css("font-size","15px");
	$("#new_div table .td1").css("width","15%");
	$("#new_div table .td2").css("width","30%");
	$("#new_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#new_div table tr").each(function(i){
		$(this).css("height","45px");
	});

	$(".panel.window").eq(ndNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(ndNum).css("color","#000");
	$(".panel.window .panel-title").eq(ndNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(ndNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(ndNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(ndNum).css("border-color","#ddd");

	$("#new_div #ok_but").css("left","45%");
	$("#new_div #ok_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
	
	initWZLXCBB();
	initWZCBB();
	initYSSCBB();
	initFHDWCBB();
	initSHBMCBB();
}

function initWZLXCBB(){
	var data=[];
	data.push({"value":"","text":"请选择物资类型"});
	$.post(wzglPath+"queryWuZiLeiXingCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			wzlxCBB=$("#new_div #wzlx_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onSelect:function(){
					loadWZCBBData();
				}
			});
		}
	,"json");
}

function initWZCBB(){
	var data=[];
	data.push({"value":"","text":"请选择物资名称"});
	wzCBB=$("#new_div #wz_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
}

function loadWZCBBData(){
	var data=[];
	data.push({"value":"","text":"请选择物资名称"});
	var wzlxId=wzlxCBB.combobox("getValue");
	$.post(wzglPath+"queryWuZiCBBList",
		{wzlxId:wzlxId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			wzCBB.combobox("loadData",data);
		}
	,"json");
}

function initYSSCBB(){
	var data=[];
	data.push({"value":"","text":"请选择运输商"});
	$.post(dwglPath+"queryYunShuShangCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			yssCBB=$("#new_div #yss_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
		}
	,"json");
}

function initFHDWCBB(){
	var data=[];
	data.push({"value":"","text":"请选择发货单位"});
	$.post(dwglPath+"queryFaHuoDanWeiCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			fhdwCBB=$("#new_div #fhdw_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
		}
	,"json");
}

function initSHBMCBB(){
	var data=[];
	data.push({"value":"","text":"请选择收货部门"});
	$.post(dwglPath+"queryShouHuoBuMenCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].mc});
			}
			shbmCBB=$("#new_div #shbm_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
		}
	,"json");
}

function checkNew(){
	if(checkCph()){
		if(checkWZLXId()){
			if(checkWZId()){
				if(checkYSSId()){
					if(checkFHDWId()){
						if(checkSHBMId()){
							newCheLiang();
						}
					}
				}
			}
		}
	}
}

function newCheLiang(){
	var wzlxId=wzlxCBB.combobox("getValue");
	$("#new_div #wzlxId").val(wzlxId);
	var wzId=wzCBB.combobox("getValue");
	$("#new_div #wzId").val(wzId);
	var yssId=yssCBB.combobox("getValue");
	$("#new_div #yssId").val(yssId);
	var fhdwId=fhdwCBB.combobox("getValue");
	$("#new_div #fhdwId").val(fhdwId);
	var shbmId=shbmCBB.combobox("getValue");
	$("#new_div #shbmId").val(shbmId);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:clglPath+"newCheLiang",
		dataType: "json",
		data:formData,
		cache: false,
		processData: false,
		contentType: false,
		success: function (data){
			if(data.message=="ok"){
				alert(data.info);
				history.go(-1);
			}
			else{
				alert(data.info);
			}
		}
	});
}

function focusCph(){
	var cph = $("#cph").val();
	if(cph=="车牌号不能为空"||cph=="车牌号已存在"){
		$("#cph").val("");
		$("#cph").css("color", "#555555");
	}
}

//验证车牌号
function checkCph(){
	var flag=false;
	var cph = $("#cph").val();
	if(cph==null||cph==""||cph=="车牌号不能为空"){
		$("#cph").css("color","#E15748");
    	$("#cph").val("车牌号不能为空");
    	flag=false;
	}
	else if(cph=="车牌号已存在"){
		$("#cph").css("color","#E15748");
    	$("#cph").val("车牌号已存在");
    	flag=false;
	}
	else{
		$.ajaxSetup({async:false});
		$.post(clglPath+"checkCphIfExist",
			{cph:cph},
			function(data){
				if(data.status==1)
			    	flag=true;
				else{
					$("#cph").css("color","#E15748");
			    	$("#cph").val(data.msg);
			    	flag=false;
				}
			}
		,"json");
	}
	return flag;
}

//验证物资类型
function checkWZLXId(){
	var wzlxId=wzlxCBB.combobox("getValue");
	if(wzlxId==null||wzlxId==""){
	  	alert("请选择物资类型");
	  	return false;
	}
	else
		return true;
}

//验证物资
function checkWZId(){
	var wzId=wzCBB.combobox("getValue");
	if(wzId==null||wzId==""){
	  	alert("请选择物资");
	  	return false;
	}
	else
		return true;
}

//验证运输商
function checkYSSId(){
	var yssId=yssCBB.combobox("getValue");
	if(yssId==null||yssId==""){
	  	alert("请选择运输商");
	  	return false;
	}
	else
		return true;
}

//验证发货单位
function checkFHDWId(){
	var fhdwId=fhdwCBB.combobox("getValue");
	if(fhdwId==null||fhdwId==""){
	  	alert("请选择发货单位");
	  	return false;
	}
	else
		return true;
}

//验证收货部门
function checkSHBMId(){
	var shbmId=shbmCBB.combobox("getValue");
	if(shbmId==null||shbmId==""){
	  	alert("请选择收货部门");
	  	return false;
	}
	else
		return true;
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "center_con_div":
		space=205;
		break;
	case "new_div":
		space=340;
		break;
	case "new_div_table":
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
	<div class="center_con_div" id="center_con_div">
		<div class="page_location_div">车辆管理-已识别车辆-添加</div>
		
		<div id="new_div">
			<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
			<input type="hidden" id="lx" name="lx" value="${requestScope.lx}"/>
			<table>
			  <tr>
				<td class="td1" align="right">
					车牌号
				</td>
				<td class="td2">
					<input type="text" class="cph_inp" id="cph" name="cph" placeholder="请输入车牌号" onfocus="focusCph()" onblur="checkCph()"/>
				</td>
				<td class="td1" align="right">
					物资类型
				</td>
				<td class="td2">
					<input id="wzlx_cbb"/>
					<input type="hidden" id="wzlxId" name="wzlxId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					物资名称
				</td>
				<td class="td2">
					<input id="wz_cbb"/>
					<input type="hidden" id="wzId" name="wzId"/>
				</td>
				<td class="td1" align="right">
					运输商
				</td>
				<td class="td2">
					<input id="yss_cbb"/>
					<input type="hidden" id="yssId" name="yssId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					发货单位
				</td>
				<td class="td2">
					<input id="fhdw_cbb"/>
					<input type="hidden" id="fhdwId" name="fhdwId"/>
				</td>
				<td class="td1" align="right">
					收货部门
				</td>
				<td class="td2">
					<input id="shbm_cbb"/>
					<input type="hidden" id="shbmId" name="shbmId"/>
				</td>
			  </tr>
			</table>
			</form>
		</div>

		<%@include file="../../inc/foot.jsp"%>
	</div>
</div>
</body>
</html>