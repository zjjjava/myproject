var mouseLeave = true;

$(function() {
	$("#treeArea").hide();

	var zTreeNodes;
	setting = {
		check: {  
            enable: true,  
            chkStyle: "checkbox",               //多选  
            chkboxType: { "Y" : "", "N" : "" }  //不级联父节点选择  
        },
		
	};

	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "zjj/findDeps.mvc?_=" + Math.random(),
		data : $('#addform').serialize(),
		success : function(data) {
			zTreeNodes = data;
			zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
		}
	});

	$("#searchDepName").focus(function() {
		var left, top;
		var objPosition = $("#searchDepName").position();
		left = objPosition.left;
		top = objPosition.top + $("#searchDepName").height();
		// alert(left + " " + top);
		$("#treeArea").css({
			position : 'absolute',
			top : top,
			left : left
		}).show();
		mouseLeave = false;
	});

	$(".dep").mouseout(function() {
		mouseLeave = true;
		// alert(mouseLeave);
	});
	$(".dep").mouseover(function() {
		mouseLeave = false;
		// alert(mouseLeave);
	});

	$("body").click(function() {
		if (mouseLeave) {
			$("#treeArea").hide();
		}
		;
	});

	$("#maingrid").datagrid({
		url : 'zjj/list.mvc?_=' + Math.random(),
		fitColumns : false,
		height : 'auto',
		queryParams : {},
		pageSize : 2,
		pagination : true,
		pageList : [ 2, 10, 20 ],
		rownumbers : true,
		singleSelect : true,
		remoteSort : true,
		columns : [ [ {
			field : 'id',
			title : 'id',
			width : '10%',
			align : 'center',
			sortable : true
		}, {
			field : 'name',
			title : '姓名',
			width : '10%',
			align : 'center',
			sortable : true
		} ] ],
		onLoadSuccess : function(data) {

		},

	});

	$("#addbtn").click(function() {
		$("#add-dialog").dialog("open");
	});
	$("#addCancel").click(function() {
		$("#add-dialog").dialog("close");
		$("#add-dialog").find(".addvalue").val("");
	});
	$("#add").click(function() {
		add();
	});

	$("#modifybtn").click(function() {
		$("#modify-dialog").dialog("open");
		var row = $("#maingrid").datagrid("getSelected"); // ��ȡѡ�е���
		// alert(row.id);
		// alert(row.name);
		$("#modify-dialog").find("#modifyId").val(row.id);
		$("#modify-dialog").find("#modifyName").val(row.name);

	});
	$("#modifyCancel").click(function() {
		$("#modify-dialog").dialog("close");
		$("#modify-dialog").find(".modifyvalue").val("");
	});
	$("#modify").click(function() {
		modify();
	});

	$("#deletebtn").click(function() {
		deleteUser();
	});

	$("#searchbtn").click(function() {
		searchList();
	});

	$("#opennew").click(function() {
		opennew();

	});

	$("#testAop").click(function() {
		testAop();

	});

});

function testAop() {
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "auth/aop.mvc?_=" + Math.random(),
		data : {},
		success : function(data) {
			alert(data.msg);
		}
	});

};

// 用于测试角色权限
function opennew() {
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "auth/opennew.mvc?_=" + Math.random(),
		data : $('#addform').serialize(),
		success : function(data) {
			alert(data.msg);
		}
	});
};

function add() {
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "zjj/add.mvc?_=" + Math.random(),
		data : $('#addform').serialize(),
		success : function(data) {
			if (data.msg == "success") {
				$.messager.alert("��ʾ", "��ӳɹ���");
				$("#add-dialog").dialog("close");
				$("#add-dialog").find(".addvalue").val("");
				// ����table���
				$("#maingrid").datagrid({
				// queryParams: unitData
				});

			} else {
				var orrMsg = data.checkMsg;
				if (orrMsg != "" && orrMsg != null) {
					alert(orrMsg);
				} else {
					alert("���ʧ�ܣ������ԣ�");
				}

			}
		}
	});
};

function modify() {
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "zjj/modify.mvc?_=" + Math.random(),
		data : $('#modifyform').serialize(),
		success : function(data) {
			if (data.msg == "success") {
				$.messager.alert("��ʾ", "�޸ĳɹ���");
				$("#modify-dialog").dialog("close");
				// $("#modify-dialog").find(".modifyvalue").val("");
				// ����table���
				$("#maingrid").datagrid({
				// queryParams: unitData
				});

			} else {
				var orrMsg = data.checkMsg;
				if (orrMsg != "" && orrMsg != null) {
					alert(orrMsg);
				} else {
					alert("�޸�ʧ�ܣ������ԣ�");
				}

			}
		}
	});
};

function deleteUser() {
	var row = $("#maingrid").datagrid("getSelected"); // ��ȡѡ�е���
	var id = row.id;
	$.ajax({
		cache : false,
		dataType : 'json',
		type : "POST",
		url : "zjj/delete.mvc?_=" + Math.random(),
		data : {
			id : id
		},
		success : function(data) {
			if (data.msg == "success") {
				$.messager.alert("��ʾ", "ɾ��ɹ���");
				$("#modify-dialog").dialog("close");
				// ����table���
				$("#maingrid").datagrid({
				// queryParams: unitData
				});

			} else {
				var orrMsg = data.checkMsg;
				if (orrMsg != "" && orrMsg != null) {
					alert(orrMsg);
				} else {
					alert("ɾ��ʧ�ܣ������ԣ�");
				}

			}
		}
	});
};

function searchList() {
	var name = $("#searchForm").find("#searchName").val();
	var queryParams = {
		"name" : name,
	};
	// ����table���
	$("#maingrid").datagrid({
		queryParams : queryParams
	});

};

function zTreeOnClick(event, treeId, treeNode) {
	// alert(treeNode.id + ", " + treeNode.name);
	$("#searchDepName").val(treeNode.name);
	$("#serachDepId").val(treeNode.id);
};

function bottomLeftPosition(left, top, obj) {
	var objPosition = obj.position();
	left = objPosition.left;
	top = objPosition.top + obj.height();
	alert(left + " " + top);
};

