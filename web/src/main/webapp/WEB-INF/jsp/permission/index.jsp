<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/WEB-INF/jsp/common/navigation.jsp"%>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@include file="/WEB-INF/jsp/common/menu.jsp"%>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
                  <ul id="permissionTree" class="ztree"></ul>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    
			    var setting = {
		    		async: {
		    			enable: true,
		    			url:"${APP_PATH}/permission/loadData",
		    			autoParam:["id", "name=n", "level=lv"]
		    		},
					view: {
						selectedMulti: false,
						addDiyDom: function(treeId, treeNode){
							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
							if ( treeNode.icon ) {
								icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
							}
                            
						},
						addHoverDom: function(treeId, treeNode){  
                        //   <a><span></span></a>
							var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
							aObj.attr("href", "javascript:;");
							if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
							var s = '<span id="btnGroup'+treeNode.tId+'">';
							if ( treeNode.level == 0 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addNode('+treeNode.id+')" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 1 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="editNode('+treeNode.id+')" href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								if (treeNode.children.length == 0) {
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deleteNode('+treeNode.id+')" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								}
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addNode('+treeNode.id+')" href="#" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 2 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="editNode('+treeNode.id+')" href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deleteNode('+treeNode.id+')" href="#">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}
			
							s += '</span>';
							aObj.after(s);
						},
						removeHoverDom: function(treeId, treeNode){
							$("#btnGroup"+treeNode.tId).remove();
						}
					}
			    };
			    /*
				var zNodes =[
					{ name:"父节点1 - 展开11111", open:true,
						children: [
							{ name:"父节点11 - 折叠",
								children: [
									{ name:"叶子节点111"},
									{ name:"叶子节点112"},
									{ name:"叶子节点113"},
									{ name:"叶子节点114"}
								]},
							{ name:"父节点12 - 折叠",
								children: [
									{ name:"叶子节点121"},
									{ name:"叶子节点122"},
									{ name:"叶子节点123"},
									{ name:"叶子节点124"}
								]},
							{ name:"父节点13 - 没有子节点", isParent:true}
						]},
					{ name:"父节点2 - 折叠",
						children: [
							{ name:"父节点21 - 展开", open:true,
								children: [
									{ name:"叶子节点211"},
									{ name:"叶子节点212"},
									{ name:"叶子节点213"},
									{ name:"叶子节点214"}
								]},
							{ name:"父节点22 - 折叠",
								children: [
									{ name:"叶子节点221"},
									{ name:"叶子节点222"},
									{ name:"叶子节点223"},
									{ name:"叶子节点224"}
								]},
							{ name:"父节点23 - 折叠",
								children: [
									{ name:"叶子节点231"},
									{ name:"叶子节点232"},
									{ name:"叶子节点233"},
									{ name:"叶子节点234"}
								]}
						]},
					{ name:"父节点3 - 没有子节点", isParent:true}

				];
			    */
			    // 异步获取树形结构数据
			    $.fn.zTree.init($("#permissionTree"), setting);
            });
            
            function addNode(id) {
            	window.location.href = "${APP_PATH}/permission/add?id="+id;
            }
            
            function editNode(id) {
            	window.location.href = "${APP_PATH}/permission/edit?id="+id;
            }
            
            function deleteNode(id) {
    			layer.confirm("删除许可信息, 是否继续",  {icon: 3, title:'提示'}, function(cindex){
    				// 删除选择的用户信息
    				$.ajax({
    					type : "POST",
    					url  : "${APP_PATH}/permission/delete",
    					data : {
    						id : id
    					},
    					success : function(result) {
    						if ( result.success ) {
    							// 刷新数据
    							var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
    							treeObj.reAsyncChildNodes(null, "refresh");
    						} else {
                                layer.msg("许可信息删除失败", {time:2000, icon:5, shift:6}, function(){
                                	
                                });
    						}
    					}
    				});
    				
    				layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
        </script>
  </body>
</html>
