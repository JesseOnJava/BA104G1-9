<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.hcorder.model.*"%>

<jsp:useBean id="hcOrderSvc"
	class="com.hcorder.modal.HcOrderMasterService" />
<jsp:useBean id="hcDetailSvc"
	class="com.hcorder.modal.HcOrderDetailService" />
<jsp:useBean id="empSvc" class="com.employee.model.EmployeeService" />
<jsp:useBean id="theCaredSvc" class="com.thecared.model.ThecaredService" />

<% HcOrderMasterVO hcOrder = hcOrderSvc.getOne("20171001-500001"); 
MemberService MemberService = new MemberService();
 memberVO memberVO = MemberService.getOneMEMBER("MEM0002");  %>
<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/usebootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front/css/navbar/newstyle_footer.css">
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style type="text/css">
</style>
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
					<li><a href="#">送餐服務</a></li>
					<!-- 送餐服務 ================================================== -->


					<!-- 商城服務 ================================================== -->
					<li><a
						href="#">商&nbsp&nbsp&nbsp&nbsp城</a>
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
							<li><a
								href="<%=request.getContextPath()%>/front/member/MemberInfo.jsp">會員資料管理</a></li>
							<li><a
								href="<%=request.getContextPath()%>/front/member/MemberHcOrder.jsp">訂單管理</a></li>
						</ul></li>
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
		<div class="row">
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
			<div class="container-fluid">
				<div class="row">
					<ol class="breadcrumb">
						<li><a href="#">首頁</a></li>

						<li>會員專區</li>

						<li class="active">訂單管理</li>
					</ol>
					<ul class="nav nav-tabs" id="navList">
						<li data-name="loginLogTab" class="active"><a
							href="<%=request.getContextPath()%>/front/member/MemberHcOrder.jsp">
								<i class="fa fa-user"></i>長照訂單
						</a></li>
						<li data-name="receiveLogTab"}><a
							href="<%=request.getContextPath()%>/front/member/MemberCarOrder.jsp">
								<i class="fa fa-briefcase"></i>派車訂單
						</a></li>
						<li data-name="socketInputTab"><a
							href="<%=request.getContextPath()%>/front/member/MemberMealOrder.jsp">
								<i class="fa fa-briefcase"></i>派餐訂單
						</a></li>
						<li data-name="socketOutputTab"><a
							href="<%=request.getContextPath()%>/front/member/MemberHcOrder.jsp">
								<i class="fa fa-briefcase"></i>儲值紀錄
						</a></li>
					</ul>
					<table class="table table-hover">
						<thead>
							<tr>
								<th class="text-center">
									<div class="col-sm-2 ">訂單編號</div>
									<div class="col-sm-2">訂購日期</div>
									<div class="col-sm-2">被照護者編號</div>
									<div class="col-sm-2">訂單狀態</div>
									<div class="col-sm-2 col-sm-offset-1">申訴</div>
								</th>
							</tr>
						</thead>
						<tbody>



							<tr>
								<td>

									<div class="panel-group" id="accordion2" role="tablist"
										aria-multiselectable="true">

										<c:forEach var="hcOrder"
											items="${hcOrderSvc.getByMemNo(memberVO.memNo)}"
											varStatus="s">

											<div class="panel panel-default">
												<div class="panel-heading" role="tab" id="panel${s.index}">
													<h4 class="panel-title text-center">
														<div class="row">
															<div class="col-xs-12 col-sm-2">
																<a href="#aaa${s.index}" data-parent="#accordion2"
																	data-toggle="collapse" role="button"
																	aria-expanded="true" aria-controls="aaa">
																	${hcOrder.orderNo} </a>
															</div>
															<div class="col-xs-12 col-sm-2">${hcOrder.orderDate}</div>
															<div class="col-xs-12 col-sm-2">${theCaredSvc.getOneTHECARED(hcOrder.caredNo).caredName}</div>
															<div class="col-xs-12 col-sm-2">${hcOrder.orderStatus}</div>
															<div class="col-sm-2 col-sm-offset-1"><span class="btn btn-info"><a></a></span></div>
														</div>
													</h4>
												</div>


												<div id="aaa${s.index}" class="panel-collapse collapse"
													role="tabpanel" aria-labelledby="panel${s.index}">
													<div class="panel-body">
														<table class="table table-hover text-center">
															<thead>
																<tr>
																	<th class="text-center">明細編號</th>
																	<th class="text-center">服務日期</th>
																	<th class="text-center">服務時段</th>
																	<th class="text-center">服務人員</th>
																	<th class="text-center">明細狀態</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="detail"
																	items="${hcDetailSvc.getAllByOrderNo(hcOrder.orderNo)}">

																	<tr>
																		<td>${detail.orderDetailNo}</td>
																		<td>${detail.serviceDate}</td>
																		<td>${detail.serviceTime}</td>
																		<td>${empSvc.findByPrimaryKey(detail.empNo).empName}</td>
																		<td>${detail.orderDetailStataus}</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>

											</div>
										</c:forEach>
									</div>


								</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			<div class="navbar navbar-default navbar-fixed-bottom">
				<div class="container">
					<p class="navbar-text text-center">BA104G1&nbsp ©&nbsp 2017
						&nbsp&nbsp 有我罩你全家股份有限公司</p>
				</div>
			</div>
			<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
			<script
				src="<%=request.getContextPath()%>/front/js/navbar/bootstrap.min.js"></script>
			<script
				src="<%=request.getContextPath()%>/front/js/navbar/usebootstrap.js"></script>
</html>