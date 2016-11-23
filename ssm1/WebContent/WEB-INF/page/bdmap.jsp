<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

request.setAttribute("basePath", basePath);
%>
<%-- <%@ taglib prefix="spring" uri="WEB-INF/spring.tld"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="//api.map.baidu.com/api?v=2.0&ak=TwiRjCcLImniKzUx8RWoLrv6OHccn1d8&s=1"></script>
	
<script type="text/javascript" src="${basePath }jquery-easyui-1.5/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="allmap"></div>

	<script>
		$(function() {
			//百度地图API功能
			var map = new BMap.Map("allmap"); // 创建Map实例
			map.centerAndZoom(new BMap.Point(116.404, 39.915), 11); // 初始化地图,设置中心点坐标和地图级别
			map.addControl(new BMap.MapTypeControl()); //添加地图类型控件
			map.setCurrentCity("北京"); // 设置地图显示的城市 此项是必须设置的
			map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放

		})
	</script>
</body>
</html>