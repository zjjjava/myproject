<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>roleList</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	
	<link rel="stylesheet" type="text/css" href="${basePath }jquery-easyui-1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${basePath }jquery-easyui-1.5/themes/icon.css">
	<link rel="stylesheet" href="${basePath }zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
	<script type="text/javascript" src="${basePath }jquery-easyui-1.5/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath }jquery-easyui-1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath }jquery-easyui-1.5/jquery.easyui.mobile.js"></script>
	<script type="text/javascript" src="${basePath }public/zjj/role/list.js"></script>
  	<script type="text/javascript" src="${basePath }zTree_v3-master/js/jquery.ztree.core.js"></script>
  	<script type="text/javascript" src="${basePath }zTree_v3-master/js/jquery.ztree.excheck.js"></script>

  </head>
  
  <body>
    <div>
	    <div id="roleGrid" style="width:50%;float:left;"></div>
	    <div id="funcTree" class="ztree" style="width:40%;float:left;marging-left:10px;"></div>
	    <input type="button" value="saveRoleFuncs" id="saveRoleFuncsBtn">
    </div>
  </body>
</html>
