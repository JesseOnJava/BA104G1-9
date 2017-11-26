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
		<title>訂購餐點Q&A</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front/css/navbar/bootstrap.css" media="screen">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front/css/navbar/usebootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/front/css/navbar/newstyle_footer.css">
		<!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
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
					<a href="#" class="navbar-brand">
						<img src="">有我罩你
					</a>
					<!-- logo 區 稍後會補上 ================================================== -->
					<button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse" id="navbar-main">
					
					
					<!-- 各項button 連結請自行找 <a href= "填入自己的頁面連結 (溫腥提醒:記得用動態) "> ================== -->
					<ul class="nav navbar-nav">
						
						<!-- 關於我們 ================================================== -->
						<li>
							<a href="#">關於我們</a>
						</li>
						<!-- 關於我們 ================================================== -->
						<!-- 最新消息 ================================================== -->
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">最新消息 <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="themes">
								<li><a href="#">最新消息</a></li>
								<li><a href="#">保健資訊</a></li>
							</ul>
						</li>
						<!-- 最新消息 ================================================== -->
						<!-- 長照服務 ================================================== -->
						<li>
							<a href="#">長照服務</a>
						</li>
						<!-- 長照服務 ================================================== -->
						<!-- 派車服務 ================================================== -->
						<li>
							<a href="#">派車服務</a>
						</li>
						<!-- 派車服務 ================================================== -->
						<!-- 送餐服務 ================================================== -->
						<li>
							<a href="<%= request.getContextPath()%>/front/mealService/MealIndex.jsp">送餐服務</a>
						</li>
						<!-- 送餐服務 ================================================== -->
						<!-- 商城服務 ================================================== -->
						<li>
							<a href="#">商&nbsp&nbsp&nbsp&nbsp城</a>
						</li>
						<!-- 商城服務 ================================================== -->
						<!-- 聯絡我們 ================================================== -->
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">聯絡我們 <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="themes">
								<li><a href="#">線上客服</a></li>
								<li><a href="#">意見回饋</a></li>
							</ul>
						</li>
						<!-- 聯絡我們 ================================================== -->
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						<!-- 會員專區 ================================================== -->
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">會員專區 <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="themes">
								<li><a href="#">會員資料管理</a></li>
								<li><a href="#">我的錢包</a></li>
								<li><a href="#">訂單管理</a></li>
								<li><a href="#">我的最愛</a></li>
							</ul>
						</li>
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
					<!-- 	我是sidebar -->
					<div class="col-xs-12 col-sm-2">
						<div class="panel-group">
							<div class="panel panel-primary">
								<div class="panel-heading"><a href="<%= request.getContextPath()%>/front/mealService/MealIndex.jsp" style="color:white">送餐服務</a></div>
								<div class="panel-body">
									<a href="#collapseOne" data-toggle="collapse" data-parent="#accordion">餐點介紹</a>
									<div id="collapseOne" class="panel panel-collapse collapse in">
										<div class=" panel-body" style="padding: 0px">
											<div style="margin: 10px"><a href="<%= request.getContextPath()%>/front/mealService/mealIntroA.jsp">活力養身餐</a></div>
											<div style="margin: 10px"><a href="<%= request.getContextPath()%>/front/mealService/mealIntroB.jsp">疾病健康餐</a></div>
											<div style="margin: 10px"><a href="<%= request.getContextPath()%>/front/mealService/mealIntroC.jsp">術後調理餐</a></div>
										</div>
									</div>
								</div>
								<div class="panel-body"><a href="<%= request.getContextPath()%>/front/mealService/ChooseMeal2.jsp">訂購餐點</a></div>
								<div class="panel-body"><a href="<%= request.getContextPath()%>/front/mealService/OrderQ&A.jsp">訂購須知</a></div>
							</div>
						</div>
					</div>
					<!-- sidebar結束 -->
					<!-- 我是內容區 -->
					<div class="col-xs-12 col-sm-10">
						<h1 class="text-center">訂餐Q&A</h1>
						<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
							<!-- 區塊1 -->
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="panel1" onclick="">
									<h4 class="panel-title">
									<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="aaa">
										訂餐說明
									</a>
									</h4>
								</div>
								<div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel1">
									<div class="panel-body">
										<p>
											<span style="color:#333333;">【線上訂購】</span><br>
											① 完成線上訂購<br>
											② 客服人員將在24小時內主動與您聯繫後出餐<br>
											③ 指定地點等待美味餐點(住家或醫院)<br>
											④ 收到餐點若發現有瑕疵狀況，請儘速與本公司聯繫並請低溫保存
										</p>
									</div>
								</div>
							</div>
							<!-- 區塊2 -->
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="panel2">
									<h4 class="panel-title">
									<a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
										配送區域
									</a>
									</h4>
								</div>
								<div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
									<div class="panel-body">
										<p>大台北地區</p>
									</div>
								</div>
							</div>
							<!-- 區塊3 -->
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="panel3">
									<h4 class="panel-title">
									<a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
										收合項目標題三
									</a>
									</h4>
								</div>
								<div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
									<div class="panel-body">
										內容三置入在這裡
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 	內容區結束 -->
				</div>
			</div>
			
			<!-- 是footer bar 不要亂刪 感謝～～ ==================================================================== -->
			<div class="navbar navbar-default navbar-fixed">
				<div class="container">
					<p class="navbar-text text-center">BA104G1&nbsp ©&nbsp 2017 &nbsp&nbsp 有我罩你全家股份有限公司 </p>
				</div>
			</div>
			<!-- 是footer bar 不要亂刪 感謝～～ ==================================================================== -->
			<!-- 這裡是JS專區 =========================================================== -->
			<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
			<script src="<%=request.getContextPath()%>/front/js/navbar/bootstrap.min.js"></script>
			<script src="<%=request.getContextPath()%>/front/js/navbar/usebootstrap.js"></script>
			<!-- 這裡是JS專區 =========================================================== -->
		</body>
		<%-- <jsp:include page="/front/footerbar.jsp" /> --%>
	</html>