<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

request.setAttribute("basePath", basePath);

response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
%>
<%-- <%@ taglib prefix="spring" uri="WEB-INF/spring.tld"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>list</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: auto;margin:0;font-family:"微软雅黑";}
		/* table td{height:30px;width:5cm;} */
		/* .l{height:100px;} */
	
	</style>
	
	<link rel="stylesheet" type="text/css" href="${basePath }jquery-easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${basePath }jquery-easyui-1.5/themes/icon.css">
	<link rel="stylesheet" href="${basePath }zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
	<script type="text/javascript" src="${basePath }jquery-easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath }jquery-easyui-1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath }jquery-easyui-1.5/jquery.easyui.mobile.js"></script>
	<script type="text/javascript" src="${basePath }public/zjj/list.js"></script>
  	<script type="text/javascript" src="${basePath }zTree_v3-master/js/jquery.ztree.core.js"></script>
  	<script type="text/javascript" src="${basePath }zTree_v3-master/js/jquery.ztree.excheck.js"></script>
	
	<script type="text/javascript"	src="${basePath }public/zjj/Lodop/LodopFuncs.js"></script>
	
	<script type="text/javascript"
	src="//api.map.baidu.com/api?v=2.0&ak=TwiRjCcLImniKzUx8RWoLrv6OHccn1d8&s=1"></script><!-- 百度地图 -->
	
  </head>
  
  <body>
    <%-- <%=basePath%><br> --%>
    
    <div>
    	<div>
    		<h3><fmt:message key="main.title" /></h3><!-- 使用了国际化 -->
    		<!-- <h3><s:message code="main.title" /></h3> -->
    	</div>
    	<div>
    		<form action="" id="searchForm">
    			姓名：<input name="name" id="searchName" >
    			部门：<input name="depName" id="searchDepName" class="dep"><input name="depId" id="searchDepId" hidden="true">
    		</form>
			<div style="z_index:999;background-color: white;" id="treeArea" class="dep">
				<ul id="tree" class="ztree"></ul>
			</div>
		</div>
    	<div>
    		<input type="button" value="add" id="addbtn" >
    		<input type="button" value="delete" id="deletebtn" >
    		<input type="button" value="modify" id="modifybtn" >
    		&nbsp;&nbsp;&nbsp;&nbsp;
    		<input type="button" value="search" id="searchbtn" >
    		<input type="button" value="testRole" id="opennew">
    		<input type="button" value="testPer" id="test">
    		<a type="button" value="logout" id="logout" href="logout">logout</a> <br>
    		
    		<input type="button" value="testAop" id="testAop"> <br>
    		
    		
    		<iframe src="auth/fileForm.mvc">
	    		
    		</iframe>
    		
    		<iframe src="auth/importFileForm.mvc">
	    		
    		</iframe><br>
    		
    		<a type="button" id="download" href="auth/download.mvc?filename=j.png">download</a>
    		<a type="button" id="export" href="auth/export.mvc?filename=users.xls">export</a> <br>
    		
    		<input  class="button"  type="button" id="b0" value="打印"   onclick=" print() "/>
    		<input  class="button"  type="button" id="b1" value="打印预览"   onclick=" preview()"/> 
    		
    		<a type="button" id="export" href="role/toRoleList.mvc">权限管理</a>
    		<a type="button" id="export" href="role/toPrint.mvc">打印页面跳转</a>
    	</div>
    	
    	
    	<!-- 用户数据表格 -->
    	<div id="maingrid"></div>
    	
    	
    	
    </div>
    
    <div id="add-dialog" class="easyui-dialog" closed="true"  title="新增" width="350px;"  height="300px;">
    	<form action="" id="addform">
	 		<table>
	 			<tr><td align="right">id</td><td align="left"><input class="addvalue" readonly="readonly" name="id" id="addId" ></td></tr>	
	          	<tr><td align="right">姓名</td><td align="left"><input class="addvalue" name="name" id="addName" ></td></tr>	
	 		</table>
 		</form>
 		<div style="width:100%;clear:both;height:30px;">
 			<input type="button" value="保存" id="add">
 			<input type="button" value="取消" id="addCancel">
 		</div>
 	</div>
 	
 	<div id="modify-dialog" class="easyui-dialog" closed="true"  title="修改" width="350px;"  height="300px;">
    	<form action="" id="modifyform">
	 		<table>
	 			<tr><td align="right">id</td><td align="left"><input class="modifyvalue" readonly="readonly" name="id" id="modifyId" ></td></tr>	
	          	<tr><td align="right">姓名</td><td align="left"><input class="modifyvalue" name="name" id="modifyName" ></td></tr>	
	 		</table>
 		</form>
 		<div style="width:100%;clear:both;height:30px;">
 			<input type="button" value="保存" id="modify">
 			<input type="button" value="取消" id="modifyCancel">
 		</div>
 	</div>
    
    <div id="print">
    	<h4>测试打印。。。。。。。。。。。。。内容。。。。。。。。。。</h4>
    	<table border="1">
    		<tr style="height:50px;">
    			<td style="width:150px;">kk</td><td style="width:50px;">jj</td>
    		</tr>
    		<tr>
    			<td class="l" style="width:110px;height:40px;"></td><td class="l" style=""></td>
    		</tr>
    	</table>
    	<br>
    	<div style="border:3px solid #000;width:300px;height:30px;"></div>
    	
    </div>
    <div id="print1">测试打印-----------------内容1。。。。。。。。。。</div>
    
    <!-- <iframe src="auth/bdmap.mvc">
	    		
    </iframe><br> -->
    
    <div id="allmap"></div>
    
  </body>
  
  <script type="text/javascript">
  var map;
  var step = 0;
  $(function() {
		//百度地图API功能
	  map = new BMap.Map("allmap");
	  map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	  
	  // 定次数移动地图（汽车在路上行驶模拟）
	  var timesRun = 0;
	  var interval = setInterval(function(){
	  timesRun += 1;
	  if(timesRun === 10){
	  clearInterval(interval);
	  };
	  move();
	  }, 1000);
  });
  
  	function move(){
  		map.centerAndZoom(new BMap.Point(116.404+step/10, 39.915+step/10), 11);
  		step++;
  	};
  
  	var LODOP = getLodop();
	//打印预览
	function preview() {
		LODOP.PRINT_INIT("用户数据"); //首先一个初始化语句 
		/* var p_left = getCookie("p_left");
		var p_top = getCookie("p_top"); */
		//                  顶部距离      左侧距离
		LODOP.ADD_PRINT_HTM('5cm', '1cm', '21cm', '29.7cm', document
				.getElementById("print").innerHTML);
		/* LODOP.ADD_PRINT_HTM('7cm', '1cm', '21cm', '29.7cm', document
						.getElementById("print1").innerHTML); *///然后多个ADD语句及SET语句 
		LODOP.PREVIEW();
	}

	function print() {
		LODOP.PRINT_INIT("用户数据"); //首先一个初始化语句 
		/* var p_left = getCookie("p_left");
		var p_top = getCookie("p_top"); */
		LODOP.ADD_PRINT_HTM('5cm', '1cm', '21cm', '29.7cm', document
				.getElementById("print").innerHTML);//然后多个ADD语句及SET语句 
		LODOP.PRINT_DESIGN();
	}
  
  </script>
</html>

