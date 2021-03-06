<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.hcorder.modal.*"%>

<%@ include file="/front/navbar.jsp"%>
<%@ include file="/front/member/Breadcrumbs_Main.jsp"%>

<jsp:useBean id="hcOrderSvc"
	class="com.hcorder.modal.HcOrderMasterService" />
<jsp:useBean id="hcDetailSvc"
	class="com.hcorder.modal.HcOrderDetailService" />
<jsp:useBean id="empSvc" class="com.employee.model.EmployeeService" />
<jsp:useBean id="theCaredSvc" class="com.thecared.model.ThecaredService" />

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