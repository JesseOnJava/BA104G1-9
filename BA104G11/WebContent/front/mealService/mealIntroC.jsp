<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.setmeal.model.*"%>
<%@ page import="com.menu.model.*"%>
<%
SetMealService setMealSvc = new SetMealService();
SetMealVO setMealVO = setMealSvc.getOneSetMeal(30);
pageContext.setAttribute("setMealVO", setMealVO);
%>
<%
	MenuService menuSvc = new MenuService();
	List<MenuVO> menuList = menuSvc.getAll(30);
	pageContext.setAttribute("menuList", menuList);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<!-- 頁面標題 ================================================== -->
<title>有我罩你</title>
<!-- 頁面標題 ================================================== -->

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/usebootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/newstyle_footer.css">
<!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
<link
	href="<%=request.getContextPath()%>/front/css/fullcalendar-scheduler/fullcalendar.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/front/css/fullcalendar-scheduler/scheduler.min.css"
	rel="stylesheet" />


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="bootstrap/html5shiv.js"></script>
      <script src="bootstrap/respond.min.js"></script>
    <![endif]-->

</head>
<body>


	<!-- image資料夾 - share裡有 一些小圖示可使用 (都是.png檔//是透明底圖//) -->

	<!-- 大家的各自頁面拜託記得要加上註解 這樣之後整合 會比較好找到 -->
	<!-- 各自 CSS & JS 資料夾記得取名歸類  -->

	<!-- 專題加油～～～～～  -->



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
					<li><a href="<%= request.getContextPath()%>/front/mealService/MealIndex.jsp">送餐服務</a></li>
					<!-- 送餐服務 ================================================== -->


					<!-- 商城服務 ================================================== -->
					<li><a
						href="<%=request.getContextPath()%>/front/footerbar.jsp">商&nbsp&nbsp&nbsp&nbsp城</a>
					</li>
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
					<!--  --------側邊導覽---------- -->
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
									href="<%=request.getContextPath()%>/front/mealService/ChooseMeal2.jsp">訂購餐點</a>
								</div>
								<div class="panel-body">
									<a
									href="<%=request.getContextPath()%>/front/mealService/OrderQ&A.jsp">訂購須知</a>
								</div>
							</div>
						</div>
					</div>
					<!-- --------右邊顯示 -->
					<div class="col-xs-12 col-sm-10">
						<h2>術後調理餐</h2>
						<div class="row">
						<div class="col-xs-12 col-sm-6">
						<p>${setMealVO.smIntro}</p>
						</div>
						<div class="col-xs-12 col-sm-6">
						<div id="carousel-id" class="carousel slide" data-ride="carousel">
							<!-- 幻燈片小圓點區 -->
							<ol class="carousel-indicators">
								<li data-target="#carousel-id" data-slide-to="0" class=""></li>
								<li data-target="#carousel-id" data-slide-to="1" class=""></li>
								<li data-target="#carousel-id" data-slide-to="2" class="active"></li>
							</ol>
							<!-- 幻燈片主圖區 -->
							<div class="carousel-inner">
								<div class="item">
									<img
									src="<%=request.getContextPath()%>/DBGifReaderForSetMeal?picNo=1&smNo=${setMealVO.smNo}"
									alt="">
								</div>
								<div class="item">
									<img
									src="<%=request.getContextPath()%>/DBGifReaderForSetMeal?picNo=2&smNo=${setMealVO.smNo}"
									alt="">
								</div>
								<div class="item active">
									<img
									src="<%=request.getContextPath()%>/DBGifReaderForSetMeal?picNo=3&smNo=${setMealVO.smNo}"
									alt="">
								</div>
							</div>
							<!-- 上下頁控制區 -->
							<a class="left carousel-control" href="#carousel-id"
								data-slide="prev"><span
								class="glyphicon glyphicon-chevron-left"></span></a> <a
								class="right carousel-control" href="#carousel-id"
								data-slide="next"><span
								class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
						</div>
						
						
						</div>
					
						
							<br>
							<h3>菜色內容</h3>
							<table class="table table-hover">
								<tr>
									<th>元氣主食</th>
									<td>南瓜飯、牛蒡香菇飯、桂圓飯、麥片糙米飯、糙米飯、麻油飯、香椿飯、栗子飯..等等</td>
								</tr>
								<tr>
									<th>季節食蔬</th>
									<td>南瓜飯、牛蒡香菇飯、桂圓飯、麥片糙米飯、糙米飯、麻油飯、香椿飯、栗子飯..等等</td>
								</tr>
								<tr>
									<th>健康主食</th>
									<td>南瓜飯、牛蒡香菇飯、桂圓飯、麥片糙米飯、糙米飯、麻油飯、香椿飯、栗子飯..等等</td>
								</tr>
								<tr>
									<th>滋養湯品</th>
									<td>南瓜飯、牛蒡香菇飯、桂圓飯、麥片糙米飯、糙米飯、麻油飯、香椿飯、栗子飯..等等</td>
								</tr>
								<tr>
									<th>養身飲品</th>
									<td>南瓜飯、牛蒡香菇飯、桂圓飯、麥片糙米飯、糙米飯、麻油飯、香椿飯、栗子飯..等等</td>
								</tr>
								<tr>
									<th>美味甜品</th>
									<td>南瓜飯、牛蒡香菇飯、桂圓飯、麥片糙米飯、糙米飯、麻油飯、香椿飯、栗子飯..等等</td>
								</tr>
							</table>
							<h3>一週膳食範例</h3>
							<div id='calendar'></div>
								<div style='clear: both'></div>
						</div>
					</div>
				</div>




		<!-- 各項button 連結請自行找 <a href= "填入自己的頁面連結 (溫腥提醒:記得用動態) "> ================== -->


		<!-- 這裡是JS專區 =========================================================== -->

		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/front/js/navbar/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/front/js/navbar/usebootstrap.js"></script>

		<!-- 這裡是JS專區 =========================================================== -->
		<script
	src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/moment.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/jquery-ui.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/fullcalendar.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/scheduler.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front/js/fullcalendar-scheduler/zh-tw.js"></script>
<script>

	$(function() { // document ready

		$('#calendar').fullCalendar({
			schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
			//height: 450,
// 			contentHeight: 470,
			locale : 'zh-tw',
			firstDay:1,
			aspectRatio : 1.85,
			// scrollTime: '00:00',
			header : {
				left : '',
				center : 'title',
				right : ''
			},
			defaultView : 'timelineWeek',
			views : {
				timelineWeek : {
					slotDuration : {
						days : 1
					},

				}
			},
			slotLabelFormat : [ 'ddd M/D',
			// top level of text
			],

			eventDurationEditable : false,
			resourceAreaWidth : 100,
			resourceLabelText : '',
			resources : [ {
				id : '早餐',
				title : '早餐',
				eventBackgroundColor:'#FFFFFF',
				eventBorderColor:'#FFFFFF',
				eventTextColor:'#FF7744'
			}, {
				id : '中餐',
				title : '中餐'
			}, {
				id : '晚餐',
				title : '晚餐'
			}, ],
			events:[
				<%for (int i = 0; i < menuList.size(); i++) {%>
                 {
				id:'<%=i%>',
				resourceId:'<%=menuList.get(i).getMealTime()%>',
				start:'<%=menuList.get(i).getMenuDate()%>',
				title:'<%=menuList.get(i).getDishName()%>',
				editable:false,
                 },
				<%}%>
			], 
		});
	
	});

</script>
<style>
#calendar {
	float: right;
	width: 900px;
}






</style>
</body>
</html>
