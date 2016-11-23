var curRoleId;

$(function() {
	$("#roleGrid").datagrid({
		url : 'role/list.mvc',
		fitColumns : false,
		height : 'auto',
		queryParams : {},
		pageSize : 2,
		pagination : true,
		pageList : [ 2, 10, 20 ],
		rownumbers : true,
		singleSelect : true,
		remoteSort:true,
		columns : [ [ {
			field : 'id',
			title : 'id',
			width : '10%',
			align : 'center',
			sortable:true
		}, {
			field : 'name',
			title : '姓名',
			width : '10%',
			align : 'center',
			sortable:true
		}] ],
		onLoadSuccess : function(data) {

		},
		onClickRow: function(rowIndex, rowData){
			curRoleId = rowData.id;
			//alert(rowData.id);
			var zTreeNodes;
			setting = {
				check: {  
		            enable: true,  
		            chkStyle: "checkbox",               //多选  
		            chkboxType: { "Y" : "", "N" : "" }  //不级联父节点选择  
		        },
				view : {
					selectedMulti : false
				},
				data : {
					
					 key: { title:"t" },
					 
					simpleData : {
						enable : true
					}
				},
				callback : {
					//onClick : zTreeOnClick,
				}
			};

			$.ajax({
				cache : false,
				dataType : 'json',
				type : "POST",
				url : "role/findFuncs.mvc?_=" + Math.random(),
				data : {id:rowData.id},
				success : function(data) {
					zTreeNodes = data;
					zTreeObj = $.fn.zTree.init($("#funcTree"), setting, zTreeNodes);
				}
			});
			
		},

	});
	
	$("#saveRoleFuncsBtn").click(function(){
		var zTreeObj = $.fn.zTree.getZTreeObj("funcTree");
		var allNodes = zTreeObj.getNodes();
		var checkedNodes = zTreeObj.getCheckedNodes(true);
		
		var checkedNodeIds = "";
		for(var i = 0;i < checkedNodes.length;i ++){
			checkedNodeIds += checkedNodes[i].id;
			if(i < checkedNodes.length - 1){
				checkedNodeIds += ",";
			}
		}
		
		console.log(curRoleId);
		console.log(checkedNodeIds);
		
		$.ajax({
			cache : false,
			dataType : 'json',
			type : "POST",
			url : "role/modRoleFuncs.mvc?_=" + Math.random(),
			data : {
				roleId:curRoleId,
				roleFuncIds:checkedNodeIds	
			},
			success : function(data) {
				if(data.msg == "success"){
					alert("保存成功");
				}else{
					alert("保存失败");
				}
			}
		});
		
	});
	
});