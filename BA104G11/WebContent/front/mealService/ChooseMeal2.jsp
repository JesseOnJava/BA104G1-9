<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.setmeal.model.*"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>訂購送餐</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">



<!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/usebootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/newstyle_footer.css">
<!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/mealService/jquery-ui.multidatespicker.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/mealService/mealOrder.css">
<link href="<%=request.getContextPath()%>/front/css/fullcalendar-scheduler/fullcalendar.min.css"
	    rel="stylesheet">
<link href="<%=request.getContextPath()%>/front/css/fullcalendar-scheduler/scheduler.min.css"
	    rel="stylesheet" >	
<link href="https://code.jquery.com/ui/1.12.1/themes/pepper-grinder/jquery-ui.css" rel="stylesheet">
	
<style type="text/css">
.btn3d {
    transition:all .08s linear;
    position:relative;
    outline:medium none;
    -moz-outline-style:none;
    border:0px;
    margin-right:10px;
    margin-top:15px;
}
.btn3d:focus {
    outline:medium none;
    -moz-outline-style:none;
}
.btn3d:active {
    top:9px;
}
.btn-default {
    box-shadow:0 0 0 1px #ebebeb inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #adadad, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
    background-color:#fff;
}
.btn-primary {
    box-shadow:0 0 0 1px #428bca inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #357ebd, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
    background-color:#428bca;
}
 .btn-success {
    box-shadow:0 0 0 1px #5cb85c inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #4cae4c, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
    background-color:#5cb85c;
}
 .btn-info {
    box-shadow:0 0 0 1px #5bc0de inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #46b8da, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
    background-color:#5bc0de;
}
.btn-warning {
    box-shadow:0 0 0 1px #f0ad4e inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #eea236, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
    background-color:#f0ad4e;
}
.btn-danger {
    box-shadow:0 0 0 1px #c63702 inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #C24032, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
    background-color:#c63702;
}


.btn3d {
    transition: all .08s linear;
    position: relative;
    outline: medium none;
    -moz-outline-style: none;
    border: 0px;
    margin-top: 0px;
    top: 0;
}
.btn3d:focus {
    outline: medium none;
    -moz-outline-style: none;
}
.btn3d:active {
    top: 6px;
}
.btn3d.btn-default {
    box-shadow: 0 0 0 1px #ebebeb inset, 0 0 0 2px rgba(255, 255, 255, 0.15) inset, 0 5px 0 0 #adadad, 0 5px 0 1px rgba(0, 0, 0, 0.4), 0 5px 5px 1px rgba(0, 0, 0, 0.5);
}
.btn3d.btn-primary {
    box-shadow: 0 0 0 1px #428bca inset, 0 0 0 2px rgba(255, 255, 255, 0.15) inset, 0 8px 0 0 #357ebd, 0 8px 0 1px rgba(0, 0, 0, 0.4), 0 8px 8px 1px rgba(0, 0, 0, 0.5);
}
.btn3d.btn-success {
    box-shadow: 0 0 0 1px #5cb85c inset, 0 0 0 2px rgba(255, 255, 255, 0.15) inset, 0 8px 0 0 #4cae4c, 0 8px 0 1px rgba(0, 0, 0, 0.4), 0 8px 8px 1px rgba(0, 0, 0, 0.5);
}
.btn3d.btn-info {
    box-shadow: 0 0 0 1px #5bc0de inset, 0 0 0 2px rgba(255, 255, 255, 0.15) inset, 0 8px 0 0 #46b8da, 0 8px 0 1px rgba(0, 0, 0, 0.4), 0 8px 8px 1px rgba(0, 0, 0, 0.5);
}
.btn3d.btn-warning {
    box-shadow: 0 0 0 1px #f0ad4e inset, 0 0 0 2px rgba(255, 255, 255, 0.15) inset, 0 8px 0 0 #eea236, 0 8px 0 1px rgba(0, 0, 0, 0.4), 0 8px 8px 1px rgba(0, 0, 0, 0.5);
}
.btn3d.btn-danger {
    box-shadow: 0 0 0 1px #c63702 inset, 0 0 0 2px rgba(255, 255, 255, 0.15) inset, 0 8px 0 0 #c24032, 0 8px 0 1px rgba(0, 0, 0, 0.4), 0 8px 8px 1px rgba(0, 0, 0, 0.5);
}
.btn3d:active {
    box-shadow: none;
}

.btn span.glyphicon {    			
	opacity: 0;				
    }
.btn.active span.glyphicon {				
	opacity: 1;				
    }
</style>
<!--[if lt IE 9]>
						<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
						<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
						<![endif]-->
</head>
<body>
	<!-- 是Navbar 不要亂刪 感謝～～ ==================================================================== -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">

				<!-- logo 區 稍後會補上 ================================================== -->
				<a href="#" class="navbar-brand"> <img src="">有我罩你
				</a>
				<!-- logo 區 稍後會補上 ================================================== -->
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">


				<!-- 各項button 連結請自行找 <a href= "填入自己的頁面連結 (溫腥提醒:記得用動態) "> ================== -->
				<ul class="nav navbar-nav">

					<!-- 關於我們 ================================================== -->
					<li><a href="#">關於我們</a></li>
					<!-- 關於我們 ================================================== -->
					<!-- 最新消息 ================================================== -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" id="themes">最新消息 <span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#">最新消息</a></li>
							<li><a href="#">保健資訊</a></li>
						</ul></li>
					<!-- 最新消息 ================================================== -->
					<!-- 長照服務 ================================================== -->
					<li><a href="#">長照服務</a></li>
					<!-- 長照服務 ================================================== -->
					<!-- 派車服務 ================================================== -->
					<li><a href="#">派車服務</a></li>
					<!-- 派車服務 ================================================== -->
					<!-- 送餐服務 ================================================== -->
					<li><a
						href="<%=request.getContextPath()%>/front/mealService/MealIndex.jsp">送餐服務</a>
					</li>
					<!-- 送餐服務 ================================================== -->
					<!-- 商城服務 ================================================== -->
					<li><a href="#">商&nbsp&nbsp&nbsp&nbsp城</a></li>
					<!-- 商城服務 ================================================== -->
					<!-- 聯絡我們 ================================================== -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" id="themes">聯絡我們 <span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#">線上客服</a></li>
							<li><a href="#">意見回饋</a></li>
						</ul></li>
					<!-- 聯絡我們 ================================================== -->
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<!-- 會員專區 ================================================== -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" id="themes">會員專區 <span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="themes">
							<li><a href="#">會員資料管理</a></li>
							<li><a href="#">我的錢包</a></li>
							<li><a href="#">訂單管理</a></li>
							<li><a href="#">我的最愛</a></li>
						</ul></li>
					<!-- 會員專區 ================================================== -->
					<c:if test="${memberVO!=null}">
						<li>${memberVO.memName}你好</li>
					</c:if>
					<!-- 登入 / 註冊 ================================================== -->
					<li><a href="#">登入 / 註冊 </a></li>
					<!-- 登入 / 註冊 ================================================== -->
					<c:choose>
						<c:when test="${isLogin!=true}">
							<li><a href="<%=request.getContextPath() %>/front/Login.jsp">登入 / 註冊</a></li>
  						</c:when>
						<c:when test="${isLogin==true}">
							<li><a href="<%=request.getContextPath() %>/member/member.do?action=logout">登出</a></li>
  						</c:when>
					</c:choose>
				</ul>

			</div>
		</div>
	</div>
	<!-- 大圖bannan -->
	<div class="container">
		<div class="page-header" id="banner">
			<div class="row">
				<div class="col-lg-6">
					<h1>Welcome</h1>
					<p class="lead">To a new bootstrap theme by UseBootstrap.</p>
				</div>
			</div>
		</div>
		<hr>
		<!-- 大圖bannan -->

		<div class="container">
			<div class="row">
				<!-- 我是sidebar -->
				<div class="col-xs-12 col-sm-2">
					<div class="panel-group">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<a
									href="<%=request.getContextPath()%>/front/mealService/MealIndex.jsp"
									style="color: white">送餐服務</a>
							</div>
							<div class="panel-body">
								<a href="#collapseOne" data-toggle="collapse"
									data-parent="#accordion">餐點介紹</a>
								<div id="collapseOne" class="panel panel-collapse collapse in">
									<div class=" panel-body" style="padding: 0px">
										<div style="margin: 10px">
											<a href="<%= request.getContextPath()%>/front/mealService/mealIntroA.jsp">活力養身餐</a>
										</div>
										<div style="margin: 10px">
											<a href="<%= request.getContextPath()%>/front/mealService/mealIntroB.jsp">疾病健康餐</a>
										</div>
										<div style="margin: 10px">
											<a href="<%= request.getContextPath()%>/front/mealService/mealIntroC.jsp">術後調理餐</a>
										</div>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<a
									href="<%=request.getContextPath()%>/front/mealService/ChooseMeal.jsp">訂購餐點</a>
							</div>
							<div class="panel-body">
								<a
									href="<%=request.getContextPath()%>/front/mealService/OrderQ&A.jsp">訂購須知</a>
							</div>
						</div>
					</div>
				</div>
				<!-- 我是sidebar結束 -->
				<!-- 我是右邊內容 -->
				<div class="col-xs-12 col-sm-10">
					<div class="wizard">
						<div class="wizard-inner">
							<div class="connecting-line"></div>
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a
									aria-controls="step1" role="tab" title="Step 1"> <span
										class="round-tab"> <i
											class="glyphicon glyphicon-shopping-cart"></i>
									</span>
								</a></li>
								<li role="presentation" class="disabled"><a
									aria-controls="step2" role="tab" title="Step 2"> <span
										class="round-tab"> <i
											class="glyphicon glyphicon-pencil"></i>
									</span>
								</a></li>
								<li role="presentation" class="disabled"><a
									aria-controls="complete" role="tab" title="Complete"> <span
										class="round-tab"> <i class="glyphicon glyphicon-ok"></i>
									</span>
								</a></li>
							</ul>
						</div>
						<h1 class="text-center">訂購餐點</h1>
						<form action="<%=request.getContextPath()%>/MealOrder.do"
							method="post">

							<jsp:useBean id="setMealSvc" scope="page"
								class="com.setmeal.model.SetMealService" />
							<div class="form-group">
								<label for="smNo">選擇套餐</label> <select class="form-control"
									id="smNo" name="smNo">
									<c:forEach var="setMealVO" items="${setMealSvc.getAll()}">
										<option value="${setMealVO.smNo}">${setMealVO.smName}</option>
									</c:forEach>
								</select>
							</div>
							
							<div id='calendar'></div>
							<div class="form-group">
								<label for="orderQty">每餐數量</label> <input type="number" min="1"
									max="99" id="orderQty" class="form-control" name="orderQty">
							</div>

							<input type="hidden" name="action" value="fill_in_a_form">
							<input type="hidden" name="cc" value="cc"> <input
								type="submit" class="btn btn-info" value="填寫資料">

						</form>
					</div>
				</div>
				<!-- 	我是右邊內容結束	 -->
			</div>
		</div>

		
		<!-- 是footer bar 不要亂刪 感謝～～ ==================================================================== -->
		<div class="navbar navbar-default navbar-fixed">
			<div class="container">
				<p class="navbar-text text-center">BA104G1&nbsp ©&nbsp 2017
					&nbsp&nbsp 有我罩你全家股份有限公司</p>
			</div>
		</div>
		<!-- 是footer bar 不要亂刪 感謝～～ ==================================================================== -->
		<!-- 這裡是JS專區 =========================================================== -->
		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/front/js/navbar/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/front/js/navbar/usebootstrap.js"></script>
		<!-- 這裡是JS專區 =========================================================== -->
		<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	   <script
			src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/moment.min.js"></script>
	    <script
			src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/fullcalendar.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/scheduler.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/zh-tw.js"></script>		
		<script
			src="<%=request.getContextPath()%>/front/js/mealService/jquery-ui.multidatespicker.js"></script>
		<script>
			$(function() {
				$("#datepicker").multiDatesPicker({
					dateFormat : "yy-m-d",
					maxPicks : 30,
					minDate : 0,
					maxDate : 30
				});
			});
		</script>
		<script>
		 var selectedDays=[];

			$(document).ready(function() {
				
				
				$('#calendar').fullCalendar({
					themeSystem:'jquery-ui',
					schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
					header: {
						left: 'prev,next today',
						center: 'title',
						right: ''
					},
					defaultView:'month',
					validRange: function(nowDate) {
		        return {
		            start: nowDate,
		            end: nowDate.clone().add(3, 'months')
		        };
		    },
		          
		            
		      
					editable: true,
					eventLimit: true, // allow "more" link when too many events
				    dayRender:function(date,cell){
				    	
				    	console.log(selectedDays);
				    	var id = moment(date).format('YYYY-MM-DD');
				    	var exists = selectedDays.indexOf((moment(date).format('YYYY-MM-DD'))+"-早");
				    		if (exists > -1) {
				    	cell.append('<br><div><label class="btn btn-warning btn-xs btn3d" style="font-size: 1em"><input id="'+id+'-早" type="checkbox" name="Checkday[]" autocomplete="off" value="'+moment(date).format('YYYY-MM-DD')+'-早餐"checked><span class="glyphicon glyphicon-ok"></span>早餐</label></div>');
				    }else{

				    	cell.append('<br><div><label class="btn btn-warning btn-xs btn3d" style="font-size: 1em"><input id="'+id+'-早" type="checkbox" name="Checkday[]" autocomplete="off" value="'+moment(date).format('YYYY-MM-DD')+'-早餐"><span class="glyphicon glyphicon-ok"></span>早餐</label></div>');
				    };

		             var exists2 = selectedDays.indexOf((moment(date).format('YYYY-MM-DD'))+"-中");
				    		if (exists2 > -1) {
				    	cell.append('<div><label class="btn btn-success btn-xs btn3d" style="font-size: 1em"><input id="'+id+'-中" type="checkbox" name="Checkday[]" autocomplete="off" value="'+moment(date).format('YYYY-MM-DD')+'-中餐"checked><span class="glyphicon glyphicon-ok"></span>中餐</label></div>');
				    }else{

				    	cell.append('<div><label class="btn btn-success btn-xs btn3d" style="font-size: 1em"><input id="'+id+'-中" type="checkbox" name="Checkday[]" autocomplete="off" value="'+moment(date).format('YYYY-MM-DD')+'-中餐"><span class="glyphicon glyphicon-ok"></span>中餐</label></div>');
				    };
				
		            var exists3 = selectedDays.indexOf((moment(date).format('YYYY-MM-DD'))+"-晚");
				    		if (exists3 > -1) {
				    	cell.append('<div><label class="btn btn-danger btn-xs btn3d" style="font-size: 1em"><input id="'+id+'-晚" type="checkbox" name="Checkday[]" autocomplete="off" value="'+moment(date).format('YYYY-MM-DD')+'-晚餐"checked><span class="glyphicon glyphicon-ok"></span>晚餐</label></div>');
				    }else{

				    	cell.append('<div><label class="btn btn-danger btn-xs btn3d" style="font-size: 1em"><input id="'+id+'-晚" type="checkbox" name="Checkday[]" autocomplete="off" value="'+moment(date).format('YYYY-MM-DD')+'-晚餐"><span class="glyphicon glyphicon-ok"></span>晚餐</label></div>');
				    };

				$("#"+id+"-早").change(function() {
					
		        if($(this).is(":checked")) {              
		            selectedDays.push($(this).attr('id'));        
		        }
		     	else{         
		        	var index = selectedDays.indexOf($(this).attr('id'));            
		            if (index > -1) {
		                selectedDays.splice(index, 1);
		            }
		        };
		        console.log(selectedDays);
		        });

		        $("#"+id+"-中").change(function() {
					
		        if($(this).is(":checked")) {              
		            selectedDays.push($(this).attr('id'));        
		        }
		     	else{         
		        	var index = selectedDays.indexOf($(this).attr('id'));            
		            if (index > -1) {
		                selectedDays.splice(index, 1);
		            }
		        };
		        console.log(selectedDays);
		        });

		        $("#"+id+"-晚").change(function() {
					
		        if($(this).is(":checked")) {              
		            selectedDays.push($(this).attr('id'));        
		        }
		     	else{         
		        	var index = selectedDays.indexOf($(this).attr('id'));            
		            if (index > -1) {
		                selectedDays.splice(index, 1);
		            }
		        };
		        console.log(selectedDays);
		        });

				    },
		            
				    viewRender:function( view, element ){
					$('.fc-day.ui-widget-content.fc-other-month.fc-future').empty();
		            $('.fc-day.ui-widget-content.fc-disabled-day').empty();
				    }
					
				});
				
			});

		
		
		
		
		</script>
		
		
		
		
</body>

</html>