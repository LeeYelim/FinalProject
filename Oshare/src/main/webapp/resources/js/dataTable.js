$(function(){
	$("#wishListTable").DataTable({
		"autoWidth": false,
		"language": {
		    "zeroRecords": "위시리스트가 존재하지 않습니다."
		  },
		"columns" : [
     {"targets":"0","width":"50%"},
     {"targets":"1","width":"50%"}
     ]
	});
	
	$("#saleItemTable").DataTable({
		"autoWidth": false,
		"order": [[ 4, "desc" ]],
		"language": {
		    "zeroRecords": "판매물품이 존재하지 않습니다."
		  },
		"columns" : [
         {"targets":"0","width":"17%"},
         {"targets":"1","width":"10%"},
         {"targets":"2","width":"10%"},
         {"targets":"3","width":"15%"},
         {"targets":"4","width":"10%"},
         {"targets":"5","width":"10%"}
         ]
	});
	
	$("#rentalItemTable").DataTable({
		"autoWidth": false,
		"order": [[ 4, "desc" ]],
		"language": {
		    "zeroRecords": "대여물품이 존재 하지 않습니다."
		  },
		"columns" : [
		             {"targets":"0","width":"15%"},
		             {"targets":"1","width":"10%"},
		             {"targets":"2","width":"10%"},
		             {"targets":"3","width":"15%"},
		             {"targets":"4","width":"10%"},
		             {"targets":"5","width":"10%"}  
		             ]
	});
	
	$("#userManagementTable").DataTable({
		"autoWidth": false,
		"language": {
		    "zeroRecords": "회원이 존재하지 않습니다."
		  },	
		"columns" : [
		             {"targets":"0","width":"1%"},
		             {"targets":"1","width":"10%"},
		             {"targets":"2","width":"10%"},
		             {"targets":"3","width":"10%"},
		             {"targets":"4","width":"10%"},
		             {"targets":"5","width":"10%"},
		             {"targets":"6","width":"10%"},
		             {"targets":"7","width":"10%"},
		             {"targets":"8","width":"10%"},
		             {"targets":"9","width":"10%"},
		             {"targets":"10","width":"10%"},
		             {"targets":"11","width":"10%"},
		             ]
	});
	
	
	$(document).on("click","#userManagement ul li:nth-child(1)",function(){
		$("#userManagementTable .allCheck").prop("checked",false);
		$("#userManagementTable tr td input[type=checkbox]").prop("checked",false);
	});
	
	
	
	//전체유저 Checkbox ALL Event
	$(document).on("click","#userManagementTable .allCheck",function(){
		if($("#userManagementTable .allCheck").prop("checked")){
    		$("#userManagementTable tr td input[type=checkbox]").prop("checked",true);
    	}else{
    		$("#userManagementTable tr td input[type=checkbox]").prop("checked",false);
    	}
	});
	
	//전체회원 탭
	$(document).on("click","#userManagement ul li:nth-child(1)",function(){
		document.location.href="/controller/mypage/userManagement";
	});
	
	//전체회원 신고버튼
	$(document).on("click","#userManagementAllUser .userManagementBtn input[value=신고]",function(){
		var userManagementCheckBoxVal = [];
		$("#userManagementTable tr td input[type=checkbox]:checked").each(function(index){
			userManagementCheckBoxVal.push($(this).val())
		});
		  $.ajax({
			  type:'post',
			  dataType:'text',
			  data:'memberGrade=1&memberNo='+userManagementCheckBoxVal, 
			  url:'/controller/mypage/userBtn',
			  success:function(result){
				  if(result<=0){
					  alert("항목을 선택해 주십시오");
					  
				  }else{
					
					  document.location.href="/controller/mypage/userManagement"
				  }
			  },
			  error:function(err){
				 
				  alert("err : "+err);
			  }  
		  })	   
	}); //전체회원 신고버튼 Event End
	
	//전체회원 정지버튼
	$(document).on("click","#userManagementAllUser .userManagementBtn input[value=정지]",function(){
		var userManagementCheckBoxVal = [];
		$("#userManagementTable tr td input[type=checkbox]:checked").each(function(index){
			userManagementCheckBoxVal.push($(this).val())
		});
		  $.ajax({
			  type:'post',
			  dataType:'text',
			  data:'memberGrade=2&memberNo='+userManagementCheckBoxVal, 
			  url:'/controller/mypage/userBtn',
			  success:function(result){
				  if(result<=0){
					  alert("항목을 선택해 주십시오");		  
				  }else{	
					  document.location.href="/controller/mypage/userManagement"
				  }
			  },
			  error:function(err){
				 
				  alert("err : "+err);
			  }	  
		  });
	});//전체회원 정지버튼 Event End
	
	
	//신고회원 신고취소버튼
	$(document).on("click","#userManagementDeclaration .userManagementBtn input[value=신고취소]",function(){
		var userManagementCheckBoxVal = [];
		$("#userManagementDeclarationTable tr td input[type=checkbox]:checked").each(function(index){
			userManagementCheckBoxVal.push($(this).val())
		});
		  $.ajax({
			  type:'post',
			  dataType:'text',
			  data:'memberGrade=0&memberNo='+userManagementCheckBoxVal, 
			  url:'/controller/mypage/userBtn',
			  success:function(result){
				  if(result<=0){
					  alert("항목을 선택해 주십시오");		  
				  }else{	
					  userManagementDeclaration();
				  }
			  },
			  error:function(err){
				 
				  alert("err : "+err);
			  }	
		  });
	});//신고회원 신고취소버튼 Event End
	
	//신고회원 정지버튼
	$(document).on("click","#userManagementDeclaration .userManagementBtn input[value=정지]",function(){
		var userManagementCheckBoxVal = [];
		$("#userManagementDeclarationTable tr td input[type=checkbox]:checked").each(function(index){
			userManagementCheckBoxVal.push($(this).val())
		});
		  $.ajax({
			  type:'post',
			  dataType:'text',
			  data:'memberGrade=2&memberNo='+userManagementCheckBoxVal, 
			  url:'/controller/mypage/userBtn',
			  success:function(result){
				  if(result<=0){
					  alert("항목을 선택해 주십시오");		  
				  }else{	
					  userManagementDeclaration();
				  }
			  },
			  error:function(err){
				 
				  alert("err : "+err);
			  }	
		  });
	});//신고회원 정지버튼 Event End
	
	
	//정지회원 정지취소버튼
	$(document).on("click","#userManagementStop .userManagementBtn input[value=정지취소]",function(){
		var userManagementCheckBoxVal = [];
		$("#userManagementStopTable tr td input[type=checkbox]:checked").each(function(index){
			userManagementCheckBoxVal.push($(this).val())
		});
		  $.ajax({
			  type:'post',
			  dataType:'text',
			  data:'memberGrade=0&memberNo='+userManagementCheckBoxVal, 
			  url:'/controller/mypage/userBtn',
			  success:function(result){
				  if(result<=0){
					  alert("항목을 선택해 주십시오");		  
				  }else{	
					  userManagementStop();
				  }
			  },
			  error:function(err){
				 
				  alert("err : "+err);
			  }	
		  });
	});//정지회원 정지취소버튼 Event End
	
	//정지회원 신고변경버튼
	$(document).on("click","#userManagementStop .userManagementBtn input[value=신고]",function(){
		var userManagementCheckBoxVal = [];
		$("#userManagementStopTable tr td input[type=checkbox]:checked").each(function(index){
			userManagementCheckBoxVal.push($(this).val())
		});
		  $.ajax({
			  type:'post',
			  dataType:'text',
			  data:'memberGrade=1&memberNo='+userManagementCheckBoxVal, 
			  url:'/controller/mypage/userBtn',
			  success:function(result){
				  if(result<=0){
					  alert("항목을 선택해 주십시오");		  
				  }else{	
					  userManagementStop();
				  }
			  },
			  error:function(err){
				 
				  alert("err : "+err);
			  }	
		  });
	});
	
	
	
	//신고 Tab Event Click End
	$(document).on("click","#userManagement ul li:nth-child(2)",function(){
		$("#userManagementDeclarationTable .allCheck").prop("checked",false)
		userManagementDeclaration();
	});
	
	//신고 Checkbox ALL Event
	$(document).on("click","#userManagementDeclarationTable .allCheck",function(){
		if($("#userManagementDeclarationTable .allCheck").prop("checked")){
    		$("#userManagementDeclarationTable tr td input[type=checkbox]").prop("checked",true);
    	}else{
    		$("#userManagementDeclarationTable tr td input[type=checkbox]").prop("checked",false);
    	}
	});
	
	function userManagementDeclaration(){
	$("#userManagementDeclarationTable").DataTable({
		 destroy: true,
		"autoWidth": false,
		"language": {
		    "zeroRecords": "신고회원이 존재하지 않습니다."
		  },
	  'ajax': {
	    	    "type"   : "POST",
	    	    "url"    : '/controller/mypage/userBlacklist',
	    	    "data"   :{
	    	    	"memberGrade" : 1
	    	    },
	    	    "dataSrc": ""
	   },  
		"columns" : [
		             {"targets":"0","width":"1%","data" : "memberNo",render : function(data ,type , row){
		            	 return '<input type="checkbox" name="userBlacklistCheckbox" value='+data+'>';
		             }},
		             {"targets":"1","width":"10%","data":"memberId"},
		             {"targets":"2","width":"10%","data":"memberPwd"},
		             {"targets":"3","width":"10%","data":"memberName"},
		             {"targets":"4","width":"10%","data":"memberBirth"},
		             {"targets":"5","width":"10%","data":"memberAddr"},
		             {"targets":"6","width":"10%","data":"memberAccount"},
		             {"targets":"7","width":"10%","data":"memberGrade",render : function(data,type,row){
		            	 if(data == 0){
		            		 return '일반'
		            	 }else if(data == 1){
		            		 return '신고유저'
		            	 }else if(data ==2){
		            		 return '정지유저'
		            	 }else{
		            		 return '어드민'
		            	 }
		             }},
		             {"targets":"8","width":"10%","data":"memberValidMonth"},
		             {"targets":"9","width":"10%","data":"memberValidYear"},
		             {"targets":"10","width":"10%","data":"memberSharingCount"},
		             {"targets":"11","width":"10%","data":"memberPhone"},
		             {"targets":"12","width":"10%","data":"memberId",render : function(data , type , row){
		            	 return "<input type='text' value="+data+" hidden><input type='button' value='신고사유'>"
		             }},
		             ]
 
	   
		});//신고회원 table
	}
	
	//정지 Tab Event Click End
	$(document).on("click","#userManagement ul li:nth-child(3)",function(){
	$("#userManagementStopTable .allCheck").prop("checked",false)
		userManagementStop();
	});
	
	//정지 Checkbox ALL Event
	$(document).on("click","#userManagementStopTable .allCheck",function(){
		if($("#userManagementStopTable .allCheck").prop("checked")){
    		$("#userManagementStopTable tr td input[type=checkbox]").prop("checked",true);
    	}else{
    		$("#userManagementStopTable tr td input[type=checkbox]").prop("checked",false);
    	}
	});
	
	function userManagementStop(){
	$("#userManagementStopTable").DataTable({
		 destroy: true,
		"autoWidth": false,
		"language": {
		    "zeroRecords": "정지회원 존재하지 않습니다."
		  },	
		  'ajax': {
	    	    "type"   : "POST",
	    	    "url"    : '/controller/mypage/userBlacklist',
	    	    "data"   :{
	    	    	"memberGrade" : 2
	    	    },
	    	    "dataSrc": ""
	   },  
		"columns" : [
		             {"targets":"0","width":"1%","data" : "memberNo",render : function(data ,type , row){
		            	 return '<input type="checkbox" name="userManagementStopCheckbox" value='+data+'>';
		             }},
		             {"targets":"1","width":"10%","data":"memberId"},
		             {"targets":"2","width":"10%","data":"memberPwd"},
		             {"targets":"3","width":"10%","data":"memberName"},
		             {"targets":"4","width":"10%","data":"memberBirth"},
		             {"targets":"5","width":"10%","data":"memberAddr"},
		             {"targets":"6","width":"10%","data":"memberAccount"},
		             {"targets":"7","width":"10%","data":"memberGrade",render : function(data,type,row){
		            	 if(data == 0){
		            		 return '일반'
		            	 }else if(data == 1){
		            		 return '신고유저'
		            	 }else if(data ==2){
		            		 return '정지유저'
		            	 }else{
		            		 return '어드민'
		            	 }
		             }},
		             {"targets":"8","width":"10%","data":"memberValidMonth"},
		             {"targets":"9","width":"10%","data":"memberValidYear"},
		             {"targets":"10","width":"10%","data":"memberSharingCount"},
		             {"targets":"11","width":"10%","data":"memberPhone"},
		             {"targets":"12","width":"10%","data":"memberId",render : function(data , type , row){
		            	 return "<input type='text' value="+data+" hidden><input type='button' value='정지사유'>"
		             }},
		             ]
		});
	}
	
	
	$("#saleProductTable").DataTable({
		"autoWidth": false,
		"columns" : [
             {"width":"25%"},
             {"width":"50%"},
             {"width":"25%"}
		]
		     		
	});
	
	//신고사유 버튼 Event
	$(document).on("click","#userManagementDeclaration table tr td input[value=신고사유]",function(){	
		var userID =  $(this).parent().children("input[type=text]").val();
		if($(".reasonForFiling").is(":animated"))return;
		$(".reasonForFiling").css({"left":"60%"});
		$(".dialogBlack").animate({"opacity":"0.7"},500).show();
		$(".reasonForFiling").animate({"opacity":"1","left":"50%"},500).show();
	//신고 이유 	
	$("#reasonForFilingTable").DataTable({
		 destroy: true,
			"autoWidth": false,
			"language": {
			    "zeroRecords": "신고사유가 존재하지 않습니다."
			  },
			  'ajax': {
		    	    "type"   : "POST",
		    	    "url"    : '/controller/mypage/reasonForFiling',
		    	    "data"   :{
		    	    	"declarationReporter" : userID
		    	    },
		    	    "dataSrc": ""
		   },  			  
			    "columns" : [
               {"targets": "0" ,"width":"10%" , "data" : "declarationNo"},
               {"targets": "1" ,"width":"10%" , "data" : "declarationSubject"},
               {"targets": "2" ,"width":"10%" , "data" : "declarationReporter"},
               {"targets": "3" ,"width":"10%" , "data" : "declarationType"},
               {"targets": "4" ,"width":"50%" , "data" : "declarationReason"}
           ]
		})
	});
	
	//정지버튼 Event END
	$(document).on("click","#userManagementStop table tr td input[value=정지사유]",function(){	
		var userID =  $(this).parent().children("input[type=text]").val();
		if($(".reasonForFiling").is(":animated"))return;
		$(".reasonForFiling").css({"left":"60%"});
		$(".dialogBlack").animate({"opacity":"0.7"},500).show();
		$(".reasonForFiling").animate({"opacity":"1","left":"50%"},500).show();
		//신고 이유 	
		$("#reasonForFilingTable").DataTable({
			 destroy: true,
				"autoWidth": false,
				"language": {
				    "zeroRecords": "정지사유가 존재하지 않습니다."
				  },	
				  'ajax': {
			    	    "type"   : "POST",
			    	    "url"    : '/controller/mypage/reasonForFiling',
			    	    "data"   :{
			    	    	"declarationReporter" : userID
			    	    },
			    	    "dataSrc": ""
			   },  			  
				    "columns" : [
	               {"targets": "0" ,"width":"10%" , "data" : "declarationNo"},
	               {"targets": "1" ,"width":"10%" , "data" : "declarationSubject"},
	               {"targets": "2" ,"width":"10%" , "data" : "declarationReporter"},
	               {"targets": "3" ,"width":"10%" , "data" : "declarationType"},
	               {"targets": "4" ,"width":"50%" , "data" : "declarationReason"}
	           ]
			})
		});
	
	// 신고 / 정지 사유 Close 버튼 Event End
	$(document).on("click",".reasonForFiling .reasonForFilingClose",function(){
		if($(".reasonForFiling").is(":animated"))return;
		$(".dialogBlack").animate({"opacity":"0"},500).hide(10);
		$(".reasonForFiling").animate({"opacity":"0","left":"40%"},500).hide(10);
	})
});