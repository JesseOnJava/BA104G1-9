<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    
  <!-- 頁面標題 ================================================== -->
    <title>有我罩你</title>
  <!-- 頁面標題 ================================================== -->
    
    <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/css/navbar/bootstrap.css" media="screen">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/css/navbar/usebootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/css/navbar/newstyle_footer.css">
  <!-- NavBar的CSS 專區 不能刪喔 ================================================== -->
    
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
			        <li class="dropdown">
			          <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">照護服務 <span class="caret"></span></a>
			          <ul class="dropdown-menu" aria-labelledby="themes">
			            <li><a href="<%=request.getContextPath()%>/front/homeCare/Hc_order.jsp">短期照護服務</a></li>
			            <li><a href="<%=request.getContextPath()%>/front/homeCare/Hc_order_long.jsp">長期照護服務</a></li>
			            <li><a href="#">Q&A</a></li>
			          </ul>
			        </li>
					<!-- 長照服務 ================================================== -->

					<!-- 派車服務 ================================================== -->
					<li><a href="#">派車服務</a></li>
					<!-- 派車服務 ================================================== -->


					<!-- 送餐服務 ================================================== -->
					<li><a href="#">送餐服務</a></li>
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
							<li><a href="<%=request.getContextPath()%>/front/member/MemberInfo.jsp">會員資料管理</a></li>
							<li><a href="<%=request.getContextPath()%>/front/member/MyWallet.jsp">我的錢包</a></li>
							<li><a href="<%=request.getContextPath()%>/front/member/MemberHcOrder.jsp">訂單管理</a></li>
						</ul>
					</li>
					<!-- 會員專區 ================================================== -->
					<c:if test="${memberVO!=null}">
						<li>${memberVO.memName}你好</li>
					</c:if>
					<!-- 登入 / 註冊 ================================================== -->

					
					<c:choose>
						<c:when test="${memberVO==null}">
							<li><a href="<%=request.getContextPath()%>/front/Login.jsp">登入
									/ 註冊</a></li>
						</c:when>
						<c:when test="${memberVO!=null}">
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




<!-- 各項button 連結請自行找 <a href= "填入自己的頁面連結 (溫腥提醒:記得用動態) "> ================== -->


<!-- 這裡是JS專區 =========================================================== -->

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/front/js/navbar/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front/js/navbar/usebootstrap.js"></script>

<!-- 這裡是JS專區 =========================================================== -->

</body>
</html>
