$(function(){
	
	 var index=0; //goodsDetail[Title]
	 
	 var hirtIndex=0; //index Wish
	 
	 var mobileHeaderMenuIndex =0; // mobile header menu
	 
	 rentalItemText();
	 saleItemListText();
	 
	/* header + index */
/*	$(document).on("scroll",function(){
		
		if($(document).scrollTop() > 65){	
			$(".headerNavaction").addClass("headerNavactionAdvice");
		}else{		
			$(".headerNavaction").removeClass("headerNavactionAdvice");
		}
		
		
	});*/
	 	 
	 $(document).on("scroll",function(){		
		if($(document).scrollTop() > 170){	
			$("footer .footerScrollTopUp").fadeIn();	
		}else{		
			$("footer .footerScrollTopUp").fadeOut();
		}
	});
	
	$(".headerNavaction ul li .myPageList").hover(function(){
		
		$(".headerNavaction ul li .myPageList ul").stop(true,false).animate({"opacity":"1" , "top" : "100%"},500).show();
	},function(){
	
		$(".headerNavaction ul li .myPageList ul").stop(true,false).animate({"opacity":"0" , "top" : "130%"},500).hide(10);
	});
	 
	 
	$(document).on("click","footer .footerScrollTopUp",function(){
		  $("html, body").stop().animate({ scrollTop: 0 }, 600);
		
	});
	 
	
	$(document).on("click",".headerIconNavaction .headerSearchIcon",function(){
		if($(".HeaderSearchForm").is(":animated"))return;
		$(".HeaderSearchForm").animate({"opacity":"1"},500).show();
	});//HeaaderSearch Event End
	
	$(document).on("click",".HeaderSearchForm .HeaderSearchClose",function(){
		if($(".HeaderSearchForm").is(":animated"))return;
		$(".HeaderSearchForm").animate({"opacity":"0"},500).hide(10);
	});//HeaaderSearch Event Close End
	
	
	
	
	/* 목록에서 위시리스트 */
	$(document).on("click",".heartWish",function(){
		var boardNo = $(this).attr("id");
		if($(this).css('color') == 'rgb(0, 0, 0)' == true){		
			insertWishList(boardNo, $(this)); 
		} else {
			deleteWishList(boardNo, $(this));
		}
	});//wish Event End
	
	/* 위시리스트 추가 */
	function insertWishList(boardNo, wishObj) {
		$.ajax({
			url: "/controller/mypage/insertWishList" , //서버요청이름
			type : "post" , //method방식 (get , post) 
			dataType :  "text" , //요청결과타입 (text ,xml , html , json)
			data : "boardNo="+boardNo,
			success : function(result){
				wishObj.css({"color":"#ff0000"});
			}, //성공
			error : function(err){
				alert(err);
			} 
		})
	}
	
	/* 위시리스트 삭제 */
	function deleteWishList(boardNo, wishObj) {
		$.ajax({
			url: "/controller/mypage/deleteWishList" , //서버요청이름
			type : "post" , //method방식 (get , post) 
			dataType :  "text" , //요청결과타입 (text ,xml , html , json)
			data : "boardNo="+boardNo,
			success : function(result){
				wishObj.css({"color":"#000"});
			}, //성공
			error : function(err){
				alert(err);
			} 
		})
	}
	
	$(document).on("click",".mobileHeaderNavaction .mobileHeaderNavactionBtn",function(){
		if($(".mobileHeaderNavactionMenu").is(":animated"))return;
		$(this).toggleClass('active-9');
		if(mobileHeaderMenuIndex <=0){
			mobileHeaderMenuIndex =1;
			$(".mobileHeaderNavactionMenu").animate({"left":"0"},500);
		}else{
			mobileHeaderMenuIndex =0;
			$(".mobileHeaderNavactionMenu").animate({"left":"-100%"},500);
		}
	});
	
	
	
	 //Sharing List Event 
	 var owl = $(".indexSharingList");	
	 owl.owlCarousel({
	      items : 4, //10 items above 1000px browser width
	      loop:true,
	      margin:10,
	      responsive:{
	          0:{
	              items:1
	          },
	          600:{
	              items:3
	          },
	          1000:{
	              items:4
	          }
	      },
	      autoplay:true,
	      autoplayTimeout:3000,
	      autoplayHoverPause:false,      
	  });
	 
	  // Custom Navigation Events
	  $(".SharingNext").click(function(){
	    owl.trigger('next.owl');
	  })
	  $(".SharingPrev").click(function(){
	    owl.trigger('prev.owl');
	  })//Sharing List Event End
	  
  	 //Rental List Event 
	 var owl2 = $(".indexRentalList");	
	 owl2.owlCarousel({
	      items : 4, //10 items above 1000px browser width
	      loop:true,
	      margin:10,
	      responsive:{
	          0:{
	              items:1
	          },
	          600:{
	              items:3
	          },
	          1000:{
	              items:4
	          }
	      },
	      autoplay:true,
	      autoplayTimeout:3000,
	      autoplayHoverPause:false,      
	  });
	 
	  // Custom Navigation Events
	  $(".RentalNext").click(function(){
	    owl2.trigger('next.owl');
	  })
	  $(".RentalPrev").click(function(){
	    owl2.trigger('prev.owl');
	  })//Rental List Event End
	  
	  
	  
	  var owl3 = $(".mobileGoodsListCategory>ul");	
		 owl3.owlCarousel({
		      items : 4, //10 items above 1000px browser width
		      autoplay:false,
		      autoplayHoverPause:false,     
		      responsive:{
		          0:{
		              items:1
		          },
		          600:{
		              items:3
		          },
		          1000:{
		              items:4
		          }
		      }
		  }); 
		 
		  // Custom Navigation Events
		  $(".goodsListNext").click(function(){
		    owl3.trigger('next.owl');
		  })
		  
		  $(".goodsListPrev").click(function(){
		    owl3.trigger('prev.owl');
		  })//mobileGoodsListCategory  Event End
		  
		  $(document).on("click",".mobilegoodsListDetailSearch",function(){
			  if($("#mobileGoodsListDetailSearchDialog").is(":animated"))return;
			  	$("#mobileGoodsListDetailSearchDialog").css({"left":"60%"});
				 $(".dialogBlack").animate({"opacity":"0.7"},500).show();
				 $("#mobileGoodsListDetailSearchDialog").animate({"opacity":"1","left":"50%"},500).show();
		  });//mobilegoodsListDetailSearch Dialog Event End 
		  
		  $(document).on("click","#mobileGoodsListDetailSearchDialog .mobileGoodsCategory .mobileGoodsListDialogClose",function(){
			  if($("goodsListDetailSearchDialog").is(":animated"))return;
				 $(".dialogBlack").animate({"opacity":"0"},500).hide(10);
				 $("#mobileGoodsListDetailSearchDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
		  });//mobilegoodsListDetailSearch Close Dialog Event End   
		  
		  //goodsDetail 
		  $(document).on("click",".goodsReviewTitle",function(){	
			  if($(".goodsReviewContent").is(":animated"))return;
			  if(  $(this).next().is(':hidden'))index = 0;
			  if(  $(this).next().is(':visible'))index = 1;
			  
			 if(index<=0){
				  $(this).next().slideDown();
			  }else{
				  $(this).next().slideUp();		  			 
			  }
		  });//review slide  
		  
		  //별점 
		  if($(".starGrade input[name=starGrade]").val() == ""){
			  $(".starGrade input[name=starGrade]").val(0)
		  }
		  
		  $(".sellerStar").rateYo({
			  rating : $(".starGrade input[name=starGrade]").val(),
		   	  readOnly : true
		  });//goodsDetail rateYo Evnet end
		  
		  
		
		  $(document).on("click",".goodsQuestionTitle",function(){	
			  if($(".goodsQuestionContent").is(":animated"))return;
			  if(  $(this).next().is(':hidden'))index = 0;
			  if(  $(this).next().is(':visible'))index = 1;
			  
			 if(index<=0){
				  $(this).next().slideDown();
			  }else{
				  $(this).next().slideUp();		  			 
			  }
		  });// q & a slide  
		  
		  $(document).on("click",".sellerInformation .sellerSub span:nth-child(1)",function(){
			  if($(".saleProfile").is(":animated"))return;
			  $(".saleProfile").css({"left":"60%"});
			  $(".dialogBlack").animate({"opacity":"0.7"},500).show();
			  $(".saleProfile").animate({"opacity":"1","left":"50%"},500).show(); 
			  goodsDetailSaleReview();
			  goodsDetailSaleList();
		  });// goodsDetail Profile open Event End
		  
		  $(document).on("click",".saleProfile .saleProfileTitle .saleProfileClose",function(){
			  if($(".saleProfile").is(":animated"))return;
			  $(".dialogBlack").animate({"opacity":"0"},500).hide(10);
			  $(".saleProfile").animate({"opacity":"0","left":"40%"},500).hide(10); 
		  });// goodsDetail Profile Close Event End
		  
		  //goodsDetail profile saleReview dataTable + ajax
		  function goodsDetailSaleReview(){
			  var boardNo = $(".sellerInformation ul input[name=goodsDetailBoardNo]").val();
			  $("#saleReviewTable").DataTable({
				  destroy: true,
				  "language": {
					    "zeroRecords": "후기가 존재하지않습니다."
					  },	
		 	    	'ajax': {
		 	    	    "type"   : "POST",
		 	    	    "url"    : '/controller/board/boardSaleReview',
		 	    	    "data"   :{
		 	    	    	"boardNo" : boardNo 
		 	    	    },
		 	    	    "dataSrc": ""
		 	    	  },
		 	    	
		 	    	  'columns': [
		 	    	    {"targets":"0" , "width" : "10%" , "data" : "gradeNo"},
		 	    	    {"targets":"1" , "width" : "40%" , "data" : "memberReview"},
		 	    	   	{"targets":"2" , "width" : "30%" , "data" : "regDate"},
		 	    	    {"targets":"3" , "width" : "10%" , "data" : "memberId"}    	
		 	    	  ]
			  });
		  }//goodsDetail profile saleReview dataTable + ajax End
		  
		
		//goodsDetail profile saleList dataTable + ajax
		  function goodsDetailSaleList(){
			  var memberId = $(".saleProfileImformation span").text();
			  $("#saleProductTable").DataTable({
				  destroy: true,
				  "language": {
					    "zeroRecords": "상품이 존재하지않습니다."
					  },	
		 	    	'ajax': {
		 	    	    "type"   : "POST",
		 	    	    "url"    : '/controller/board/boardSaleList',
		 	    	    "data"   :{
		 	    	    	"memberId" : memberId
		 	    	    },
		 	    	    "dataSrc": ""
		 	    	  },
		 	    	
		 	    	  'columns': [
		 	    	    {"targets":"0" , "width" : "30%" , "data" : "boardNo"}, 
		 	    	    {"targets" : "1", "width" : "30%" ,"data" : "filePath",render: function ( data, type, row ) {
		                      return '<a href=/controller/board/goodsDetail?boardNo='+row.boardNo+'><img src=/controller'+data+' alt='+data+'></a>';
		    	            }},
		 	    	   	{"targets":"2" , "width" : "30%" , "data" : "productName"},	
		 	    	  ]
			  });
		  }//goodsDetail profile saleList dataTable + ajax End
		  
		  
		  $(document).on("click",".sellerInformation .sellerSub span:nth-child(2)",function(){
			 if($(".saleMessage").is(":animated"))return;
			 $(".saleMessageRecipient input[type=text]").val("")
			 $(".saleMessageContent textarea").val("")
			 $(".saleMessage").css({"left":"60%"});
			 $(".dialogBlack").animate({"opacity":"0.7"},500).show();
			 $(".saleMessage").animate({"opacity":"1","left":"50%"},500).show();
		  });// goodsDetail saleMessage Open Event End
		  
		  $(document).on("click",".saleMessage .saleMessageTitle .saleMessageClose",function(){
				 if($(".saleMessage").is(":animated"))return;
				 $(".dialogBlack").animate({"opacity":"0"},500).hide(10);
				 $(".saleMessage").animate({"opacity":"0","left":"40%"},500).hide(10);
			  });// goodsDetail saleMessage Close Event End
		  
		  
		  //상세보기 쪽지 보내기
		  $(document).on("click",".saleMessageBtn input[type=button]",function(){
				if($(".saleMessageRecipient input[type=text]").val() == ""){
					alert("받는이를 입력해주세요");
					$(".recipient input[type=text]").focus();
					
				}else if($(".saleMessageContent textarea").val() == ""){
					alert("쪽지내욤을 입력해주세요");
					$(".noteContent textarea").focus();		
				}else if(!receiverBoolean){
					alert("수신자가 존재하지 않습니다.");
				}else{
			  $.ajax({
					url: "/controller/mypage/DetailSendMessageInsert" , //서버요청이름
					type : "post" , //method방식 (get , post) 
					dataType :  "text" , //요청결과타입 (text ,xml , html , json)
					data : $(".saleMessage form").serialize(),
					success : function(result){
						if(result <= 0){
							alert("error")
						}else{
							alert("메세지 전송");
							 if($(".saleMessage").is(":animated"))return;
							 $(".dialogBlack").animate({"opacity":"0"},500).hide(10);
							 $(".saleMessage").animate({"opacity":"0","left":"40%"},500).hide(10);
						}
					}, //성공
					error : function(err){
						alert("err :"+err);
					} , //실패
				}); // ajax End
				}	
		  });//상세보기 message Event End
		  
		  //상세보기 수신자 유효성 검사
		  
		  var receiverBoolean = true;
		  $(document).on("keyup",".saleMessageRecipient input[name=receiver]",function(){			  
			  $.ajax({
					url: "/controller/member/receiverSelect" , //서버요청이름
					type : "post" , //method방식 (get , post) 
					dataType : "text" , //요청결과타입 (text ,xml , html , json)
					data : "receiver="+$(this).val(),
					success : function(result){
						if(result <=0){
							receiverBoolean = false;
						}else{
							receiverBoolean = true;
						}
					} , //성공
					error : function(err){
						alert("err :"+err)
						receiverBoolean = false;
					} , //실패
				});
			});
		  		  
		  
//상품후기
		  
		  //질문하기
		  $(document).on("click","#goodsReview .goodsReviewPlus",function(){
			  $(".goodsReviewPlusForm").remove();
			  
			  $(".goodsReviewFrame").append("<div class='goodsReviewPlusForm'>" +
					  "<form action='' method=''><div class='goodsReviewPlusFormTitle'>" +
					  "<span>제목 : </span><input type='text' name='sub3'></div>" +
					  "<div class='goodsReviewPlusFormContent'><span>내용 : </span>" +
					  "<textarea id='sub4'></textarea></div><input type='button' id='sub5' value='전송'>" +
			  "</form></div>");
			  
		  });
		  
		  $(document).on("click","#sub5",function(){
			  $.ajax({
				  type:'post',
				  dataType:'json',
				  data:'boardNo='+$(".sellerInformation input[name=goodsDetailBoardNo]").val()+'&contents='+$("#sub4").val()+'&subject='+$("input[name=sub3]").val(),
				  url:'/controller/board/rinsert',
				  success:function(re){
					  if(re > 0) ReviewSelect();
				  },
				  
				  error:function(err){
					  alert("err : "+err);
				  }
			});
			  
		  });
		  
		  //후기 리스트
			function ReviewSelect(){
				$(".goodsReviewFrame").html("");
				$.ajax({
	             	type:'post',
	             	dataType:'json',
	             	data:'boardNo='+$(".sellerInformation input[name=goodsDetailBoardNo]").val(),
	             	url:'/controller/board/rselect',
	             	success:function(re){
	             		var str="";
	             		$.each(re, function(index,item) {
		             		if(item.parentNo==0){
		             			str ='<div class="goodsReviewForm"><div class="goodsReviewTitle">'
		             			+"<input type='hidden' values="+item.reviewNo+">"
		    					+'<span><ul><li>'+item.subject+'</li><li>'+item.regDate+'</li><li>'+item.memberId+'</li></ul></span></div>'
		    					+'<div class="goodsReviewContent">'
		    					+'<span id="Review'+item.reviewNo+'"><div class="goodsProductReviewsForm"><div class="goodsProductReviews">'+item.contents+'</div>'
		    					+'<div class="goodsReviewAdd" name="insertReview" id="' + item.reviewNo + '">댓글달기 | <input type="button" id= "insertReviewDelete" name="'+item.reviewNo+'" value="삭제" ></div></div></span></div></div>'
		    					
		    					$(".goodsReviewFrame").append(str);
		             		}
		             	})
		             	
	             		$.each(re, function(index,item) {
	             			if(item.parentNo!=0){
	             				str =''
		    					+'<span id="Review'+item.reviewNo+'"><div class="goodsProductReviewsForm"><div class="goodsProductReviews"><span><ul><li>'+item.memberId+'</li><li>'+item.regDate+'</li></ul></span>'+item.contents+'</div>'
		    					+'<div class="goodsReviewAdd" name="insertReview" id="' + item.reviewNo + '"><input type="button" id= "insertReviewDelete" name="'+item.reviewNo+'" value="삭제" ></div></div></span></div></div>'
 
		    					$("span[id=Review"+item.parentNo+"]").append(str);
	             			}
	             		})
	             	},
	             	error:function(err){
	             		alert("err : "+err);
	             		
	             	}
				})
			  }
			
			
			//상푸후기 댓글삭제
			$(document).on("click", "#insertReviewDelete", function(){
				$(this).attr('name');
				$.ajax({
					  type:'post',
					  dataType:'json',
					  data:'reviewNo='+$(this).attr('name')+'&memberId='+$(".sellerInformation input[name=user]").val(),
					  url:'/controller/board/rdelete',
					  success:function(re){
						  ReviewSelect();						  	 
					  },
					  error:function(err){
						  ReviewSelect();
					  }
					  
				  })	
				  $(".goodsReviewForm").remove();
				  $(".goodsReviewContent").remove();
				
			})
			
		  
			//상품후기 클릭
			$(document).on("click", "#ww", function() {
				  ReviewSelect();
				  $(".goodsReviewForm").remove();
				  $(".goodsReviewContent").remove();
			  })

			  
		  // 댓글달기 클릭
			  $(document).on("click",".goodsReviewContent .goodsReviewAdd",function(){
				  $(".goodsReviewChattingForm").remove();
				  
				  $(this).parent().append("<div class='goodsReviewChattingForm'>" +
						  "<form action=''id='f' method='post'>" +
						  "<textarea  name='goodsReviewChatting' id='goodsReviewChatting'></textarea>" +
						  "<input type='button' id='"+ $(this).attr('id') +"' name='rev' value='입력'>" +
				  "</form><p>200자내외</p></div>");
			  });
			  
			
			  
			//내용 입력버튼 클릭
			  $(document).on("click","input[name=rev]",function(){
				  
				  var str = $(this).attr('id'); // 첫번째 댓글 번호 가져오기
				  $.ajax({
					  type:'post',
					  dataType:'json',
					  data:'boardNo='+$(".sellerInformation input[name=goodsDetailBoardNo]").val()+'&contents='+$("#goodsReviewChatting").val()+'&parentNo='+str,
					  url:'/controller/board/rinsert',
					  success:function(re){
						  if(re > 0) ReviewSelect();
						  
					  },
					  error:function(err){
						  alert(err);
					  }
					  
				  })	
				  $(".goodsReviewForm").remove();
				  $(".goodsReviewContent").remove();
			  })
		  
		  
		   //goodsDetailChatting 
		  
		  $(document).on("click","#goodsQuestion .goodsQuestionPlus",function(){
			  $(".goodsQuestionPlusForm").remove();
			  
			  $(".goodsQuestionFrame").append("<div class='goodsQuestionPlusForm'>" +
					  "<form action='' method=''><div class='goodsQuestionPlusFormTitle'>" +
					  "<span>제목 : </span><input type='text' name='sub1'></div>" +
					  "<div class='goodsQuestionPlusFormContent'><span>내용 : </span>" +
					  "<textarea id='sub2'></textarea></div><input type='button' id='sub' value='전송'>" +
			  "</form></div>");
			  
		  });
		  
		  $(document).on("click","#sub",function(){
		  
			  $.ajax({
				  type:'post',
				  dataType:'json',
				  data:'boardNo='+$(".sellerInformation input[name=goodsDetailBoardNo]").val()+'&contents='+$("#sub2").val()+'&subject='+$("input[name=sub1]").val(),
				  url:'/controller/board/insert',
				  success:function(re){
					  if(re > 0) select();
				  },
				  
				  error:function(err){
					  alert(err);
				  }
			});
			  
		  });
		  
		  //댓글 리스트
			function select(){
				$(".goodsQuestionFrame").html("");
				$.ajax({
	             	type:'post',
	             	dataType:'json',
	             	data:'boardNo='+$(".sellerInformation input[name=goodsDetailBoardNo]").val(),
	             	url:'/controller/board/select',
	             	success:function(re){
	             		var str="";
	             		$.each(re, function(index,item) {
		             		if(item.parentCommentNo==0){
		             			str ='<div class="goodsQuestionForm"><div class="goodsQuestionTitle">'
		             			+"<input type='hidden' values="+item.commentNo+">"
		    					+'<span><ul><li>'+item.subject+'</li><li>'+item.regDate+'</li><li>'+item.memberId+'</li></ul></span></div>'
		    					+'<div class="goodsQuestionContent">'
		    					+'<span id="comment'+item.commentNo+'"><div class="goodsQuestionCommentsForm"><div class="goodsQuestionComments">'+item.contents+'</div>'
		    					+'<div class="goodsQuestionAdd" name="insertComm" id="' + item.commentNo + '">댓글달기 | <input type="button" id= "insertQuestionCommentsDelete" name="'+item.commentNo+'" value="삭제" ></div></div></span></div></div>'
		    					
		    					$(".goodsQuestionFrame").append(str);
		             			
		             		}
		             	})
		             	
	             		$.each(re, function(index,item) {
	             			if(item.parentCommentNo!=0){
	             				str ='<div class="goodsQuestionReContent">'
		    					+'<span id="comment'+item.commentNo+'"><div class="goodsQuestionCommentsForm"><div class="goodsQuestionComments"><span><ul><li>'+item.memberId+'</li><li>'+item.regDate+'</li></ul></span>'+item.memberPId+'님에게<p>'+item.contents+'</div>'
		    					+'<div class="goodsQuestionAdd" name="insertComm" id="' + item.commentNo + '">댓글달기 | <input type="button" id= "insertQuestionCommentsDelete" name="'+item.commentNo+'" value="삭제" ></div></div></span></div></div>'

		    					$("span[id=comment"+item.parentCommentNo+"]").append(str);

	             			}
	             			
	             		})
	             		
	             	},
	             	error:function(err){
	             		alert(err);
	             	}
			    })
			 }
			
			
			//질문 댓글삭제
			$(document).on("click", "#insertQuestionCommentsDelete", function(){
				$(this).attr('name');
				$.ajax({
					  type:'post',
					  dataType:'json',
					  data:'commentNo='+$(this).attr('name') +'&memberId='+$(".sellerInformation input[name=user]").val(),
					  url:'/controller/board/delete',
					  success:function(re){
						  select();
						  
					  },
					  error:function(err){
						  alert(33);
						  select();
					  }
					  
				  })	
				  $(".goodsQuestionForm").remove();
				  $(".goodsQuestionContent").remove();
				
			})
			
			
			// QnA 클릭
			  $(document).on("click", "#qq", function() {
				  select();
				  $(".goodsQuestionForm").remove();
				  $(".goodsQuestionContent").remove();
			  })
			 
			  
			  // 댓글달기 클릭
			  $(document).on("click",".goodsQuestionContent .goodsQuestionAdd",function(){
				  $(".goodsChattingForm").remove();
	
				  $(this).parent().append("<div class='goodsChattingForm'>" +
						  "<form action=''id='f' method='post'><span>" +
						  "'"+$(this).parent().children(".goodsQuestionComments").children("span").children("ul").children("li:nth-child(1)").text()+"'에게 댓글 쓰기</span>"+
						  "<textarea  name='goodsChatting' id='goodsChatting'></textarea>" +
						  "<input type='button' id='"+ $(this).attr('id') +"' name='comm' value='입력'>" +
				  "</form><p>200자내외</p></div>");
			  });

			
			  
			//내용 입력버튼 클릭
			  $(document).on("click","input[name=comm]",function(){
				  
				  var str = $(this).attr('id'); // 첫번째 댓글 번호 가져오기
				  $.ajax({
					  type:'post',
					  dataType:'json',
					  data:'boardNo='+$(".sellerInformation input[name=goodsDetailBoardNo]").val()+'&contents='+$("#goodsChatting").val()+'&parentCommentNo='+str, 
					  url:'/controller/board/insert',
					  success:function(re){
						  if(re > 0) select();
						  
					  },
					  error:function(err){
						  alert(err);
					  }
					  
				  })	
				  $(".goodsQuestionForm").remove();
				  $(".goodsQuestionContent").remove();
			  })
			  
			  //장바구니 추가 버튼
			  $(document).on("click",".goodsDetailIcon #cartIcon", function(){
			        	var eventArray = $('#calendar').fullCalendar('clientEvents');
			        	for (var i = 0; eventArray.length !== 0 && i < eventArray.length; i++) {
			        		if(eventArray[i].id=="myReservation") { // 내가 선택한 예약이라면
			        			var start = eventArray[i].start.format('YYYYMMDD');
			    	        	var end = moment(eventArray[i].end-(1000*60*60*24)).format('YYYYMMDD');
			    	        	var boardNum = $("input[name=goodsDetailBoardNo]").val(); // 나중에 값 변경필요
			    	        	var price = $(".goodsPrice").text();
			    	        	var form = $('<form></form>');
			    	        	    form.attr('action', "/controller/board/cartinsert");
			    	        	    form.attr('method', 'post');
			    	        	    form.appendTo('body');
			    	        	    var st = $("<input type='hidden' value="+start+" name='cartStart'>");
			    	        	    var e = $("<input type='hidden' value="+end+" name='cartEnd'>");
			    	        	    var mId = $("<input type='hidden' value="+boardNum+" name='boardNo'>");
			    	        	    var price = $("<input type='hidden' value="+price+" name='cartPrice'>");
			    	        	    form.append(st);
			    	        	    form.append(e);
			    	        	    form.append(mId);
			    	        	    form.append(price);
			    	        	    form.submit();
			    	        	// location.href="<c:url value='/reservation/apply?productid="+productid+"&start="+start+"&end="+end+"'/>";
			    	    		return;
			        		}	
			        	}
			        	alert("예약 날을 선택해 주세요");
				})
			  
			  //장바구니 결제버튼
			   $(document).on("click","input[name=paymen]", function(){
				//   if($("input:checkbox[name='check']").is(":checked") == true)
					   
			   })

		  
			  /* 결제 창에서 수량 변경 될 때 */
			    $(document).on("change", "input[name=productCount]", function() {
					/*alert($("#totalPrice").text()*$(this).val());*/
					$("#totPrice").text($("#price").text()*$(this).val()*$("#sharingDays").text());
					$("#paymentPrice").text($("#price").text()*$(this).val());
					$("#paymentTotal").text($("#paymentPrice").text()*$("#sharingDays").text());
					$("#totalPrice").val(($("#paymentPrice").text()*$("#sharingDays").text()));
				});
			    
			    /* 결제 버튼 누르기 */
			    $(document).on("click", "input[value=결제]", function() {
			    	payment();
				})
				
				/* 결제 API */
				function payment() {
			    	var IMP = window.IMP; // 생략가능
				 	IMP.init('imp18029594'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
				 	/*	
				 	alert($("#productName").text());
				 	alert($("#totalPrice").val());
				 	alert($("#buyerName").text());
				 	alert($("#buyerPhone").text());*/
				 	
				 	IMP.request_pay({
					    pg : 'inicis', // version 1.1.0부터 지원.
					    pay_method : 'card',
					    merchant_uid : 'merchant_' + new Date().getTime(),
					    name : '주문명:' + $("#productName").text(),
					    amount : $("#totalPrice").val(),
					    buyer_name : $("#buyerName").text(),
					    buyer_tel : $("#buyerPhone").text(),
					    buyer_addr : $("#buyerAddr").text()
					}, function(rsp) {
					    if ( rsp.success ) {
					    	$('#paymentForm').submit();
					        var msg = '결제가 완료되었습니다.';
					        msg += '고유ID : ' + rsp.imp_uid;
					        msg += '결제 금액 : ' + rsp.paid_amount;
					        msg += '카드 승인번호 : ' + rsp.apply_num;
					    } else {
					        var msg = '결제에 실패하였습니다.';
					        msg += '에러내용 : ' + rsp.error_msg;
					    }
					    alert(msg);
					});
				}
		  
			    //saleItemList 
				var sharingNum = 0;
				  $(document).on("click",".saleItemListForm input[value=비용청구]",function(){
					  if($(".saleItemListDialog").is(":animated"))return;
					  $(".saleItemListDialog").css({"left":"60%"});
					  $(".dialogBlack").animate({"opacity":"0.7"},500).show();
					  $(".saleItemListDialog").animate({"opacity":"1","left":"50%"},500).show();
					  
					  sharingNum = $(this).parent().children().eq(1).val();
					  
					  
					  $.ajax({
						  url:"/controller/reservation/selectDemandPayment",
						  type : "post",
						  dataType : "json",
						  data : "sharingNum=" + sharingNum, 
						  success : function(result) {
							  $("#price").text(result.board.price); // 하루 대여료
							  $("#sharingDays").text(result.sharingStart+"~"+result.sharingEnd+" ("+result.sharingDays+"일)"); // 대여 기간
							  $("#totalPrice").text(result.totalPrice); // 기본 총 대여료(대여료*기간)
							 
							  if(result.overDue>0) { // 연체 했다면
								  $("#overDue").text(result.overDue); // 연체기간
								  $("#overDueFee").text(result.overDueFee); // 하루연체료
								  $("#totalOverDueFee").text(result.totalOverDueFee); // 총 연체료 
								  $("#totalMoney").text(result.totalPrice + result.totalOverDueFee); // 기본비용+연체
							  } else { // 연체하지 않았다면
								  $("#totalMoney").text(result.totalPrice);
							  }
							  $("#paidMoney").text(result.totalPrice); // 이미 지불한 금액
							  
						  },
						  error : function(err) {
							  alert(err);
						  }
					  })		
				  });// saleItemListDialog Event End
		  
		 var rate=0;
		 //rentalItemEvaluation 
		$(".rentalItemEvaluation #rateYo").rateYo().on("rateyo.set", function (e, data) {
			  alert("The rating is set to " + data.rating + "!");
			  rate = data.rating;
		});//rentalItemEvaluation Event End
			
		// 청구 비용 이벤트
		  $(document).on("keyup", ".saleItemAddMoney input[name=demandFee]", function() {
			 // alert($(this).val());
			  if($(this).val()=="") {
				  $("#totalMoney").text(parseInt($("#totalOverDueFee").text())+parseInt($("#totalPrice").text()));
			  } else {
				  $("#totalMoney").text(parseInt($(this).val())+parseInt($("#totalPrice").text()) + parseInt($("#totalOverDueFee").text()));
			  }
			  $("#charge").text(parseInt($("#totalMoney").text())-parseInt($("#paidMoney").text()));
		})
		
		$(document).on("click", ".saleItemBtn input[value=신청]", function() {
			if(saleItemAddMoneyValidityCheck()) {
				var inputChargeNo = $("<input type='hidden' value="+sharingNum+" name='chargeNo'>");
				var intputCharge = $("<input type='hidden' value="+parseInt($("#charge").text())+" name='charge'>");
				  $('#extraChargeForm').append(inputChargeNo);
				  $('#extraChargeForm').append(intputCharge);
				  $('#extraChargeForm').submit();
			}
		})
		
		  
		  $(document).on("click",".saleItemListDialog .saleItemListClose",function(){
			  		if($(".saleItemListDialog").is(":animated"))return;
			  		$(".dialogBlack").animate({"opacity":"0"},500).hide(10);
			  		$(".saleItemListDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
		  });//saleItemListDialog Close Event End
		  
		  $(document).on("click",".saleItemListDialog .saleItemBtn input[value=취소]",function(){
		  		if($(".saleItemListDialog").is(":animated"))return;
		  		$(".dialogBlack").animate({"opacity":"0"},500).hide(10);
		  		$(".saleItemListDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
	  	  });//saleItemListDialog Close Event End
		  
		  
		  //rentalItem Text 
		  function rentalItemText(){
			  var rentalItemSpan = $(".rentalItemListTransactionStatus span");
			  var rentalItemBtn = $(".rentalItemListTransactionStatus input[type=button]");
			  
			  for(var retalItemNumber=0; retalItemNumber< rentalItemSpan.length ; retalItemNumber++ ){
				  if(rentalItemSpan.eq(retalItemNumber).text()=="대여대기"){
					  //예약취소
					  rentalItemBtn.eq(retalItemNumber).val("예약취소");
				  }else if(rentalItemSpan.eq(retalItemNumber).text() == "대여중"){
					  //반납신청
					  rentalItemBtn.eq(retalItemNumber).val("반납신청");
				  }else if(rentalItemSpan.eq(retalItemNumber).text()== "반납"){
					  //판매자한테 청구하기 버튼 생성
					  //판매자가 청구하기 할때 대여자도 청구하기 버튼이 생김
					  //대여자 버튼 사라짐
					  rentalItemSpan.eq(retalItemNumber).text("반납대기");
					  rentalItemSpan.eq(retalItemNumber).css({"line-height":"60px"});
					  rentalItemBtn.eq(retalItemNumber).hide();
				  }else if(rentalItemSpan.eq(retalItemNumber).text()== "파손" || 
						  rentalItemSpan.eq(retalItemNumber).text()== "분실") {
					  var str = "반납대기<br>(" + rentalItemSpan.eq(retalItemNumber).text() +")";
					  rentalItemSpan.eq(retalItemNumber).html(str);
					  rentalItemSpan.eq(retalItemNumber).css({"line-height":"30px"});
					  rentalItemBtn.eq(retalItemNumber).hide();
				  } else if(rentalItemSpan.eq(retalItemNumber).text() == "비용청구"){
					  rentalItemSpan.eq(retalItemNumber).text("추가금 결제");          
					  rentalItemSpan.eq(retalItemNumber).css({"line-height":"30px"});
					  rentalItemBtn.eq(retalItemNumber).show().val("결제하기");
				  }else if(rentalItemSpan.eq(retalItemNumber).text() == "거래완료"){
					  rentalItemBtn.eq(retalItemNumber).remove();
					  //거래완료
				  }
			  }//rentalItemListFor End
		  }
		  
		  //saleItemList Text
		  function saleItemListText(){
			  var saleItemSpan = $(".saleItemListTransactionStatus span");
			  var saleItemBtn = $(".saleItemListTransactionStatus input[type=button]");
			  
			  for(var saleItemNumber = 0; saleItemNumber < saleItemSpan.length ; saleItemNumber++){
			  if(saleItemSpan.eq(saleItemNumber).text() == "대여대기"){
				  //예약취소
				  saleItemSpan.eq(saleItemNumber).css({"line-height":"74px"});
				  saleItemBtn.eq(saleItemNumber).hide();
			  } else if(saleItemSpan.eq(saleItemNumber).text() == "거래완료") {
				  saleItemSpan.eq(saleItemNumber).css({"line-height":"74px"});
				  saleItemBtn.eq(saleItemNumber).hide();
			  } else if(saleItemSpan.eq(saleItemNumber).text() == "대여중"){
				  //반납신청
				  saleItemSpan.eq(saleItemNumber).css({"line-height":"74px"});
				  saleItemBtn.eq(saleItemNumber).hide();
			  }else if(saleItemSpan.eq(saleItemNumber).text() == "반납"){
				  // 기본 반납
				  saleItemSpan.eq(saleItemNumber).text("반납신청");
				  saleItemSpan.eq(saleItemNumber).css({"line-height":"38px"});
				  saleItemBtn.eq(saleItemNumber).show().val("확인");
				  /*
				  
				  //판매자한테 청구하기 버튼 생성
				  saleItemSpan.eq(saleItemNumber).css({"line-height":"38px"});
				  saleItemBtn.eq(saleItemNumber).show().val("청구하기");		  
				  //판매자가 청구하기 할때 대여자도 청구하기 버튼이 생김
				  //대여자 버튼 사라짐
				  saleItemSpan.eq(saleItemNumber).css({"line-height":"74px"});
				  saleItemBtn.eq(saleItemNumber).remove();*/
			  } else if(saleItemSpan.eq(saleItemNumber).text() == "비용청구"){
				  // 비용청구 상태
				  saleItemSpan.eq(saleItemNumber).css({"line-height":"74px"});
				  saleItemBtn.eq(saleItemNumber).hide();
			  }
			  
			  else if(saleItemSpan.eq(saleItemNumber).text() == "거래완료"){
				  //거래완료
			  }
     	    }//saleItemListFor end
		  }
	 
	  /*
	   * 구매자의 반납 신청 
	   * */
	  var sharingNo = 0;
	  var sellerId = "";
	  var boardNo = 0;
	  $(document).on("click",".rentalItemListForm input[value=반납신청]",function(){
	 		if($(".rentalItemListDialog").is(":animated"))return;
	  		$(".rentalItemListDialog").css({"left":"60%"});
	  		$(".dialogBlack").animate({"opacity":"0.7"},500).show();
	  		$(".rentalItemListDialog").animate({"opacity":"1","left":"50%"},500).show();
	  		sharingNo = $(this).attr("id");
	  		sellerId = $(this).attr("name");
	  		boardNo = $(this).parent().children().eq(3).val();
	  });//rentalItemListDialog Event End
	  
	  /*
	   * 버튼명 예약 취소=>클릭시 삭제
	   * */
	  $(document).on("click",".rentalItemListForm input[value=예약취소]",function(){
		  var result = confirm('예약 취소하시겠습니까');
		  var reservationNo = $(this).prev().val();
		  if(result) {
			  var form = $('<form></form>');
	    	  form.attr('action', "/controller/reservation/deleteReservation");
	    	  form.attr('method', 'post');
	    	  form.appendTo('body');
	    	  var inputSharing = $("<input type='hidden' value="+reservationNo+" name='reservationNo'>");
	    	  form.append(inputSharing);
	    	  form.submit();		    	  
		  } else {
		} 
	  })
	  
	  /*
	   * 반납 신청
	   * */
	  $(document).on("click",".rentalItemListDialog .rentalItemListBtn input[value=신청]",function(){
		  if(rentalItemValidityCheck()) {/*
			  var rentalItemList = $(".rentalItemList select[name=rentalItemList]").val();*/
			  var inputSharingNo = $("<input type='hidden' value="+sharingNo+" name='sharingNo'>");
			  var inputMemberPoint = $("<input type='hidden' value="+rate+" name='memberPoint'>");
			  var intputMemberReview = $("<input type='hidden' value="+$("#memberReview").val()+" name='memberReview'>");
			  var inputSellerId = $("<input type='hidden' value="+sellerId+" name='sellerId'>");
			  var inputBoardNo = $("<input type='hidden' value="+boardNo+" name='boardNo'>");
			  
			  $('#returnForm').append(inputSharingNo);
			  $('#returnForm').append(inputMemberPoint);
			  $('#returnForm').append(intputMemberReview);
			  $('#returnForm').append(inputSellerId);
			  $('#returnForm').append(inputBoardNo);
			
			  $('#returnForm').submit();
		  } 
	  });
	  
	  $(document).on("click",".rentalItemListDialog .rentalItemListClose",function(){
		  		if($(".rentalItemListDialog").is(":animated"))return;
		  		$(".dialogBlack").animate({"opacity":"0"},500).hide(10);
		  		$(".rentalItemListDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
	  });//rentalItemListDialog Close Event End
	  
	  $(document).on("click",".rentalItemListDialog .rentalItemListBtn input[value=취소]",function(){
		  if($(".rentalItemListDialog").is(":animated"))return;
	  		$(".dialogBlack").animate({"opacity":"0"},500).hide(10);
	  		$(".rentalItemListDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
	  });
	  /*
	   * 구매자의 반납 확인(일반 반납시)
	   * */
	  $(document).on("click", ".saleItemListForm input[value='확인']", function() {
		  var sharingNum = $(this).parent().children().eq(1).val(); // sharingNo;
		  var responseState = $(this).val();
		  alert(responseState);
		  var form = $('<form></form>');
    	  form.attr('action', "/controller/reservation/responseReturn");
    	  form.attr('method', 'post');
    	  form.appendTo('body');
    	  var inputSharing = $("<input type='hidden' value="+sharingNum+" name='sharingNum'>");
    	  var inputResponseState = $("<input type='hidden' value="+responseState+" name='responseState'>");
    	  form.append(inputSharing);
    	  form.append(inputResponseState);
    	  form.submit();		   
	}) 
	
	/*
	 * 추가금 결제
	 * */
	$(document).on("click", ".rentalItemListTransactionStatus input[value='결제하기']", function() {
		if($(".saleItemListDialog").is(":animated"))return;
		  $(".saleItemListDialog").css({"left":"60%"});
		  $(".dialogBlack").animate({"opacity":"0.7"},500).show();
		  $(".saleItemListDialog").animate({"opacity":"1","left":"50%"},500).show();
		  
		  $.ajax({
			  type:'post',
			  dataType:'json',
			  data:'sharingNo='+$(this).attr("id"), 
			  url:'/controller/payment/selectExtraChargeInfo',
			  success:function(result){
				  $("#price").text(result.board.price); // 하루 대여료
				  $("#sharingDays").text(result.sharingStart+"~"+result.sharingEnd+" ("+result.sharingDays+"일)"); // 대여 기간
				  $("#totalPrice").text(result.totalPrice); // 기본 총 대여료(대여료*기간)
				 
				  if(result.overDue>0) { // 연체 했다면
					  $("#overDue").text(result.overDue); // 연체기간
					  $("#overDueFee").text(result.overDueFee); // 하루연체료
					  $("#totalOverDueFee").text(result.totalOverDueFee); // 총 연체료 
					  $("#totalMoney").text(result.totalPrice + result.totalOverDueFee); // 기본비용+연체
				  } else { // 연체하지 않았다면
					  $("#totalMoney").text(result.totalPrice);
				  }
				  $("#paidMoney").text(result.totalPrice); // 이미 지불한 금액
				  $("#chargeReason").text(result.extraCharge.chargeReason); // 사유
				  $("#demandFee").text(parseInt(result.extraCharge.charge)-parseInt($("#totalOverDueFee").text())); // 청구 금액 (연체 제외)
				  $("#totalMoney").text(parseInt(result.totalPrice) + parseInt($("#totalOverDueFee").text()) + parseInt(result.extraCharge.charge)); 
				  $("#charge").text(result.extraCharge.charge); // 총 청구금액
				  
				  var sharingNumInput = $("<input type='hidden' value="+result.sharingNo+" name='sharingNum'>");
				  var stateInput = $("<input type='hidden' value="+'거래완료'+" name='state'>");
				  $('#extraChargeFormRe').append(sharingNumInput);
				  $('#extraChargeFormRe').append(stateInput);
			  },
			  error:function(err){
				  alert(err);
			  }
			  
		  })
	})
	
	  
		  //declaration
		  $(document).on("click",".declaration img",function(){
			  if($(".declarationDialog").is(":animated"))return;
			  $(".declarationDialog").css({"left":"60%"});
			  $(".dialogBlack").animate({"opacity":"0.7"},500).show();
			  $(".declarationDialog").animate({"opacity":"1","left":"50%"},500).show()
		  });//declarationDialog Open Event End
		  
		  $(document).on("click",".declarationDialog .declarationTitle .declarationClose",function(){
			  if($(".declarationDialog").is(":animated"))return;
			  $(".dialogBlack").animate({"opacity":"0"},500).hide(10);
			  $(".declarationDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
		  });//declarationDialog Close Event End
		  
		  $(document).on("click",".declarationDialog .declarationBtn input[value=취소] ",function(){
			  if($(".declarationDialog").is(":animated"))return;
			  $(".dialogBlack").animate({"opacity":"0"},500).hide(10);
			  $(".declarationDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
		  });//declarationDialog Close Event End
		  		
		 //wishList 
	    $(document).on("click",".wishListClose",function(){
	    	$(this).parent().parent().remove();
	    	var boardNo = $(this).parent().parent().children("td:nth-child(1)").children("input[type=text]").val();
	    	
	    	$.ajax({
				url: "/controller/mypage/deleteWishList" , //서버요청이름
				type : "post" , //method방식 (get , post) 
				dataType :  "text" , //요청결과타입 (text ,xml , html , json)
				data : "boardNo="+boardNo,
				success : function(result){
					$(".heartWish").attr("id",boardNo).css({"color":"#000"});
				}, //성공
				error : function(err){
					alert(err);
				} 
			})
	    });
	    
/*	     //userManagement
	    $(document).on("click","#userManagement ul li a",function(){
	    	if($(this).text() == "전체회원")$(".userManagementBtn input:nth-child(2)").val("정지");
    		if($(this).text() == "신고당한회원")$(".userManagementBtn input:nth-child(2)").val("정지")$(".userManagementBtn input:nth-child(1)").val("신고해제");
			if($(this).text() == "정지회원")$(".userManagementBtn input:nth-child(2)").val("정지해제");
	    });
	   */
	    //sendMessage
	    //받은 쪽지함
	    function sendInMessage(){
	    	var user = $(".sendMessageTabForm input[name=sender]").val();
	       	 $("#inMessageTable").DataTable({
	    		  destroy: true,
	    		  "language": {
	    			    "zeroRecords": "받은쪽지가 존재하지않습니다."
	    			  },	
		 	    	'ajax': {
		 	    	    "type"   : "POST",
		 	    	    "url"    : '/controller/mypage/sendMessageSelect',
		 	    	    "data"   :{
		 	    	    	"posts" : "receiver",
		 	    	    	"division" : user
		 	    	    },
		 	    	    "dataSrc": ""
		 	    	  },
		 	    	  
		 	    	  'columns': [
	    	            {"data" : "messageNo",render: function ( data, type, row ) {
	                      return '<input type="checkbox" name="inMessageCheckbox" value='+data+'>';
	    	            }},
		 	    	    {"data" : "content"},
		 	    	    {"data" : "sender"},
		 	    	    {"data" : "sendingDate"}
		 	    	  ]
		 	    }); 	
	    	
	    	
	    }
	   
	    
		
	    $(document).on("click",".sendMessageForm .receiverMessage",function(){
	    	sendInMessage();
	    }); //받은 쪽지함 Event end
	    
	    //받은 쪽지함 check all
	    $(document).on("click",".inMessage .allCheck",function(){
	    	if($(".inMessage .allCheck").prop("checked")){
	    		$(".inMessage table tr td input[type=checkbox]").prop("checked",true);
	    	}else{
	    		$(".inMessage table tr td input[type=checkbox]").prop("checked",false);
	    	}
	    });
	    
	    //받은 쪽지함 삭제
	    $(document).on("click",".inSendMessageBtn input[type=button]",function(){
	    	var inSendMessageCheckBoxVal = [];
	    	$(".inMessage table tr td input[name=inMessageCheckbox]:checked").each(function(index){
	    		inSendMessageCheckBoxVal.push($(this).val());
	    	});
	    	$.ajax({
				url: "/controller/mypage/sendMessageDelete" , //서버요청이름
				type : "post" , //method방식 (get , post) 
				dataType : "text" , //요청결과타입 (text ,xml , html , json)
				data : "messageNo="+inSendMessageCheckBoxVal,
				success : function(result){
					if(result <=0){
						alert("삭제할 항목을 선택해 주십시오")
					}
					sendInMessage();
				} , //성공
				error : function(err){
					alert("err :"+err)
				} , //실패
			});
	    	
	    });
	        
	  //보낸 쪽지함
	    function sendOutMessage(){
	    	var user = $(".sendMessageTabForm input[name=sender]").val();
	    	 $("#outMessageTable").DataTable({
	    		  destroy: true,
	    		  "oLanguage": {
	    			  "sZeroRecords": "보낸쪽지가 존재하지않습니다"
	    			  },
		 	    	'ajax': {
		 	    	    "type"   : "POST",
		 	    	    "url"    : '/controller/mypage/sendMessageSelect',
		 	    	    "data"   :{
		 	    	    	"posts" : "sender",
		 	    	    	"division" : user
		 	    	    },
		 	    	    "dataSrc": ""
		 	    	  },
		 	    	  
		 	    	  'columns': [
	    	            {"data" : "messageNo",render: function ( data, type, row ) {
	                      return '<input type="checkbox" name="outMessageCheckbox" value='+data+'>';
	    	            }},
		 	    	    {"data" : "content"},
		 	    	    {"data" : "receiver"},
		 	    	    {"data" : "sendingDate"}
		 	    	  ]
		 	    }); 
	    }
	    
	    $(document).on("click",".sendMessageForm .senderMessage",function(){
	    	sendOutMessage();
	    }); //보낸 쪽지함 Event end
	    
	    //보낸 쪽지함 check all
	    $(document).on("click",".outMessage .allCheck",function(){
	    	if($(".outMessage .allCheck").prop("checked")){
	    		$(".outMessage table tr td input[type=checkbox]").prop("checked",true);
	    	}else{
	    		$(".outMessage table tr td input[type=checkbox]").prop("checked",false);
	    	}
	    });
	    
	    //보낸 쪽지함 삭제
	    $(document).on("click",".outSendMessageBtn input[type=button]",function(){
	    	var outSendMessageCheckBoxVal = [];
	    	$(".outMessage table tr td input[name=outMessageCheckbox]:checked").each(function(index){
	    		outSendMessageCheckBoxVal.push($(this).val());
	    	});
	    	
	    	$.ajax({
				url: "/controller/mypage/sendMessageDelete" , //서버요청이름
				type : "post" , //method방식 (get , post) 
				dataType : "text" , //요청결과타입 (text ,xml , html , json)
				data : "messageNo="+outSendMessageCheckBoxVal,
				success : function(result){
					if(result <=0){
						alert("삭제할 항목을 선택해 주십시오")
					}
					sendOutMessage();
				} , //성공
				error : function(err){
					alert("err :"+err)
				} , //실패
			});
	    	
	    });
	    
	  //신고하기 serialize
	    $(document).on("click",".declarationBtn input[value=전송]",function(){

	    	if($(".reasonForReport textarea").val() == ""){
	    		alert("신고이유를 입력해 주십시오");
	    		$(".reasonForReport textarea").focus();
	    	}else{
	    		$.ajax({
					url: "/controller/mypage/declarationInsert" , //서버요청이름
					type : "post" , //method방식 (get , post) 
					dataType : "text" , //요청결과타입 (text ,xml , html , json)
					data : $(".declarationDialog form").serialize(),
					success : function(result){
						if(result <=0){
							
							alert("신고 실패 다시 입력해주십시오");
							$(".reasonForReport textarea").text("");
						}else{
							
							alert("신고가 완료되었습니다");
							 if($(".declarationDialog").is(":animated"))return;
							  $(".dialogBlack").animate({"opacity":"0"},500).hide(10);
							  $(".declarationDialog").animate({"opacity":"0","left":"40%"},500).hide(10);
						}
					} , //성공
					error : function(err){
						alert("err :"+err)
					} , //실패
				});
	    	}
	    });
	   
	});

