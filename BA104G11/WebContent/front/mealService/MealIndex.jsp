<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>送餐服務主頁</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/usebootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/newstyle_footer.css">
<!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front/css/mealService/mealIndex.css">
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
					<c:choose>
						<c:when test="${isLogin!=true}">
							<li><a href="<%=request.getContextPath()%>/front/Login.jsp">登入
									/ 註冊</a></li>
						</c:when>
						<c:when test="${isLogin==true}">
							<li><a
								href="<%=request.getContextPath()%>/member/member.do?action=logout">登出</a></li>
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


		<div class="container center-block">
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

				<!-- 右邊顯示 -->

				<div class="col-xs-12 col-sm-10">
					<div class="container c1">
						<div class="row">
							<div class="col-xs-12 col-sm-6 block_1-1 col-fixed">
								<h3>
									我們是餐桌上 <br> 的營養飲食管家
								</h3>
								<p>
									創辦人過去長照個案中，深切感受到病人及病人家屬各項居家照護的不便，希望大家都能「過好生活、好好吃飯、體驗美好事物」，願望渺小卻很真實。開創提供長照病人全方位調配營養餐服務，以安心、同理心為出發點、並以醫護專業為後盾，致力提供
									對長照病人營養餐點外送服務。</p>
							</div>
							<div class="col-xs-12 col-sm-6 block_1-2 col-fixed">
								<h3>
									讓人更健康 <br> 生活更美好 <br> 是我們存在的價值
								</h3>
								<p>透過全方位的守護健康，讓人快樂的追求人生夢想，享受生命的美好！</p>
							</div>
						</div>


						<div class="row">
							<div class="col-xs-12 col-sm-6 block_2-1 col-fixed ">
								<img
									src="<%=request.getContextPath()%>/img/mealService/sample.jpg">
							</div>

							<div class="col-xs-12 col-sm-6 block_2-2 col-fixed">
								<h3>服務內容</h3>
								<p>
									每餐營養師設計餐點含有 <br> 元氣米食、健康主食、滋養湯品、季節食蔬、養身飲品、甜品各一份 <br>
									<br> 可透過病人身體狀況,選擇適合的套餐 <br> 另外也會因當令食材、產地氣候、彈性調整內容
								</p>
							</div>
						</div>

						<!-- 套餐連結 -->
						<div class="container CNTR3">
							<div class="row">
								<div class="col-xs-12 col-sm-3 ">
									<div class="item" onclick="location.href='<%= request.getContextPath()%>/front/mealService/mealIntroA.jsp'">
										<img
											src="<%=request.getContextPath()%>/DBGifReaderForSetMeal?picNo=1&smNo=10" height="200" width="200">
										<h2>活力養身餐</h2>
										<p>適合一般病人</p>
									</div>
								</div>
								<div class="col-xs-12 col-sm-3">
									<div class="item" onclick="location.href='<%= request.getContextPath()%>/front/mealService/mealIntroB.jsp'">
										<img
											src="<%=request.getContextPath()%>/DBGifReaderForSetMeal?picNo=1&smNo=20" height="200" width="200">
										<h2>疾病健康餐</h2>
										<p>適合有慢性疾病病人</p>
									</div>
								</div>
								<div class="col-xs-12 col-sm-3">
									<div class="item" onclick="location.href='<%= request.getContextPath()%>/front/mealService/mealIntroC.jsp'">
										<img
											src="<%=request.getContextPath()%>/DBGifReaderForSetMeal?picNo=1&smNo=30" height="100" width="100">
										<h2>術後調理餐</h2>
										<p>適合術後病人</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
</body>

</html>