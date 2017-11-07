	function addMember(){
		//右侧选中的项添加到左侧
		var add=$("#newAddMembersId").val();
		var del=$("#deleteMembersId").val();
		var ext=$("#existedMemberId").val();
		var newAddId="";
		//获取新加的userId
		var addedUsers=$("#enterpriseMember input:checked");
//		var addedUsers = $(".memberCheckbox:checked");
		for(var i=0;i<addedUsers.length;i++){
			var checkbox=$(addedUsers[i]);
			var uid=checkbox.next().val();
			if(add.indexOf(uid)>=0 || (ext.indexOf(uid)>=0 && del.indexOf(uid)<0)){
				continue;
			}
			if(!(ext.indexOf(uid)>=0 && del.indexOf(uid)>=0)){
				if(add.indexOf(uid)<0){
					if(add.length==0)
						add+=uid;
					else
						add+=","+uid;
				}
			}
			//添加数据到右侧列表
			checkbox.removeAttr("checked");
			var userDiv=checkbox.parent().parent().parent();
			$("#groupMember").append(userDiv.clone());
			del=del.replace(uid+",","");
			del=del.replace(uid,"");
			$("#deleteMembersId").val(del);
			
			//左侧列表中移除数据
			checkbox.removeAttr("checked");
			var userRemovDiv=checkbox.parent().parent();
			userRemovDiv.remove();
			
		}
		
		$("#newAddMembersId").val(add);
	}
	
	function removeMember(){
		//左侧移除成员
		var add=$("#newAddMembersId").val();
		var del=$("#deleteMembersId").val();
		var newRemoveId="";
		
//		var removedUsers=$("#groupMember input:checkbox[checked]");
		var removedUsers=$("#groupMember input:checked");
		for(var i=0;i<removedUsers.length;i++){
			var checkbox=$(removedUsers[i]);
			var uid=checkbox.next().val();
			if(del.indexOf(uid)>=0){
				continue;
			}
			if(del.indexOf(uid)<0){
				if(del.length==0)
					del+=uid;
				else
					del+=","+uid;
			}
			//左侧列表中移除数据
			checkbox.removeAttr("checked");
			var userDiv=checkbox.parent().parent().parent();
			userDiv.remove();
			//$("#enterpriseMember").append(userDiv);
			add=add.replace(uid + ",", "");
			add=add.replace(uid, "");
			$("#newAddMembersId").val(add);
			
			//添加数据到右侧列表
			checkbox.removeAttr("checked");
			var userDiv=checkbox.parent().parent().parent();
			$("#enterpriseMember").append(userDiv.clone());
			del=del.replace(uid+",","");
			del=del.replace(uid,"");
			$("#deleteMembersId").val(del);
			
		}
		$("#deleteMembersId").val(del);
	}
	//搜索成员，按名字,按部门
	function searchMember(){
	
		var searchTxt=$.trim($("#searchTxt").val());
		var memberLIsts=$(".dmlist");
		//移除上次的结果
		var lastReusts=$(".tempResult");
		for(var i=0;i<lastReusts.length;i++){
			$(lastReusts[i]).remove();
		}
		//为空搜索显示全部列表
		if(searchTxt=="" || searchTxt=="搜索企业成员"){
			//show all hidded ml
			for(var i=0;i<memberLIsts.length;i++){
				$(memberLIsts[i]).css("display","");
			}
			return ;
		}
		//hide all memberlist,隐藏所有的列表
		for(var i=0;i<memberLIsts.length;i++){
			$(memberLIsts[i]).css("display","none");
		}
		//搜索-------------
		var enterpriseMemberDiv=$("#enterpriseMember");
		//search by name,add class:tempResult
		var names=$(".sname");
		for(var i=0;i<names.length;i++){
			var name=$(names[i]).text();
			if(name.indexOf(searchTxt)>=0){
				var result=$(names[i]).parent().parent().parent().clone();
				result.addClass("tempResult");
				result.css("display","");
				enterpriseMemberDiv.append(result);
			}
		}
		
		//search by departmentName
		var departmentNames=$(".sdepartment");
		for(var i=0;i<departmentNames.length;i++){
			var name=$(departmentNames[i]).text();
			if(name.indexOf(searchTxt)>=0){
				var result=$(departmentNames[i]).parent().parent().parent().clone();
				result.addClass("tempResult");
				result.css("display","");
				enterpriseMemberDiv.append(result);
			}
		}
	}
	
	function clearTxt(){
		var txt=$.trim($("#searchTxt").val());
		if(txt=="搜索企业成员")
		$("#searchTxt").val("");
	}
	
	function setsearchTxt(){
		var txt=$.trim($("#searchTxt").val());
		if(txt=="")
		$("#searchTxt").val("搜索企业成员");
	}
	//展开折叠器
	function folderToggle(event,basePath){
		var evnet=event||window.event;
		var arrowDiv=event.target||event.srcElement;
		var closeimg=basePath+"/images/folder_close.png";
		var openimg=basePath+"/images/folder_open.png";
		var srcval=$(arrowDiv).attr("src");
		if(srcval==closeimg)
		$(arrowDiv).attr("src",openimg);
		if(srcval==openimg)
		$(arrowDiv).attr("src",closeimg);
		
		var listDiv=$(arrowDiv).parent().next().next().next();
		listDiv.toggleClass("memberlist");
	
	}
	//点击名字折叠
	function folderNameToggle(event,basePath){
		var evnet=event||window.event;
		var arrowDiv=event.target||event.srcElement;
		var closeimg=basePath+"/images/folder_close.png";
		var openimg=basePath+"/images/folder_open.png";
		var srcval=$(arrowDiv).attr("src");
		if(srcval==closeimg)
		$(arrowDiv).attr("src",openimg);
		if(srcval==openimg)
		$(arrowDiv).attr("src",closeimg);
		
		var listDiv=$(arrowDiv).parent().next().next();
		listDiv.toggleClass("memberlist");
	
	}
	
	function folderNumToggle(event,basePath){
		var evnet=event||window.event;
		var arrowDiv=event.target||event.srcElement;
		var closeimg=basePath+"/images/folder_close.png";
		var openimg=basePath+"/images/folder_open.png";
		var srcval=$(arrowDiv).attr("src");
		if(srcval==closeimg)
		$(arrowDiv).attr("src",openimg);
		if(srcval==openimg)
		$(arrowDiv).attr("src",closeimg);
		
		var listDiv=$(arrowDiv).parent().parent().next().next();
		listDiv.toggleClass("memberlist");
	}
	
	
	function setCss(event,onoff){
		var theclass=$.trim(onoff);
		var offclass="addmemberBtnoff";
		var onclass="addmemberBtnon";
		var event=event||window.event;
		var thisbtn=event.target||event.srcElement;
		$(thisbtn).removeClass(onclass);
		$(thisbtn).removeClass(offclass);
		if(theclass=="on"){
			$(thisbtn).addClass(onclass);
		}else{
			$(thisbtn).addClass(offclass);
		}
	}
