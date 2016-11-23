<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>carManage</title>

<style type="text/css">
/* table td{height:35px;text-align:center;}
#tb3 td{width:2.257cm;}
#tb1 td{width:2.633cm;} */
/* .w{width:2.933cm;}
.l{width:2.333cm;} */

</style>
	
	<script type="text/javascript"	src="${basePath }public/zjj/Lodop/LodopFuncs.js"></script>

</head>
<body>
	<div id="print" align="center">
		<div style="width: 15.8cm; height: 23.1cm; position: relative; margin-left: 2.0cm; margin-top: 1.0cm;">
			<div style="text-align:center;"><h4>教学车辆管理档案</h4></div>
			<div>
				<table border="1" cellspacing="0" cellpadding="0" border-collapse="collapse" style="text-align:center;width:15.8cm;border-collapse:collapse;" id="tb1">
					<tr style="height:35px;">
						<td colspan="6">车辆基本情况</td>
					</tr>
					<tr style="height:35px;">
						<td>车牌号码</td><td class="w">111</td><td class="l">颜色</td><td>222</td><td rowspan="6" colspan="2">11</td>
					</tr>
					<tr style="height:35px;">
						<td>车辆类型</td><td class="w" style="width:2.933cm;">111</td><td class="l" style="width:2.333cm;">厂牌型号</td><td>222</td>
					</tr>
					<tr>
						<td>制造厂名</td><td colspan="3">111</td>
					</tr>
					<tr>
						<td>出厂日期</td><td class="w">19966365</td><td class="l">注册日期</td><td>19988989</td>
					</tr>
					<tr>
						<td>报废日期</td><td class="w">12225545</td><td class="l">发动机号</td><td>5464879</td>
					</tr>
					<tr>
						<td>车辆外廓尺寸</td><td>558877</td><td>总质量</td><td>11t</td>
					</tr>
					
					<tr>
						<td>发动机排量</td><td>4</td><td>排放标准</td><td>222</td><td>燃料类型</td><td>汽油</td>
					</tr>
					<tr>
						<td>核定载质量</td><td>50t</td><td>核定载客</td><td>30人</td><td>空调系统</td><td>jj</td>
					</tr>
					<tr>
						<td>计时系统装置</td><td>11</td><td>卫星定位装置</td><td>22</td><td>副后视镜</td><td>33</td>
					</tr>
					<tr>
						<td>副制动踏板</td><td>22</td><td colspan="3">GB/T21055、GB7258&nbsp;辅助装置</td><td>jjj</td>
					</tr>
				</table>
				
				<table border="1" cellspacing="0" cellpadding="0" style="width:15.8cm;border-collapse:collapse;text-align:center;" id="tb2">
					<tr>
						<td colspan="6">车辆变更情况</td>
					</tr>
					<tr>
						<td>原车牌号</td><td>现车牌号</td><td>变更日期</td><td>变更前单位</td><td>变更后单位</td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td>
					</tr>
				</table>
				
				<table border="1" cellspacing="0" cellpadding="0" style="width:15.8cm;border-collapse:collapse;text-align:center;" id="tb3" >
					<tr>
						<td>维护日期</td><td>累计行驶里程</td><td>合格证编号</td><td>车辆检测评定日期</td><td>评定类别</td><td>检测报告编号</td><td>检测评定有效期</td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
					<tr>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td>
					</tr>
				</table>
			
			</div>
		</div>
	</div>
	
	<div>
		<input  class="button"  type="button" id="b0" value="打印"   onclick=" print() "/> &#160;
		<input  class="button"  type="button" id="b1" value="打印预览"   onclick=" preview() "/> &#160;
	</div>
</body>

<script type="text/javascript">
	var LODOP = getLodop();
	//打印预览
	function preview() {
		LODOP.PRINT_INIT("机动车驾驶员培训学员登记表"); //首先一个初始化语句 
		/* var p_left = getCookie("p_left");
		var p_top = getCookie("p_top"); */
		LODOP.ADD_PRINT_HTML('0cm', '0cm', '21cm', '29.7cm', document
				.getElementById("print").innerHTML);//然后多个ADD语句及SET语句 
		LODOP.PREVIEW();
	}
	
	function print() {
		LODOP.PRINT_INIT("机动车驾驶员培训学员登记表"); //首先一个初始化语句 
		/* var p_left = getCookie("p_left");
		var p_top = getCookie("p_top"); */
		LODOP.ADD_PRINT_HTML('0cm', '0cm',  '21cm', '29.7cm', document
				.getElementById("print").innerHTML);//然后多个ADD语句及SET语句 
		LODOP.PRINT_DESIGN();
	}
	
</script>
</html>