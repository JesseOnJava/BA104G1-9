<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%@ include file="/front/navbar.jsp"%>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">



<head>
</head>
<body>
	<div class="container-fluid">
	<div class="row">
		<div class="col-sm-12">
			<ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li>登入/註冊</li>
				<li class="active">註冊會員</li>
			</ol>
		</div>
		<div class="col-xs-12 col-sm-8 col-sm-offset-2">
			<form class="well form-horizontal"
				action="<%=request.getContextPath()%>/member/member.do?action=insert"
				method="post" id="contact_form">
				<fieldset>
					<!-- Form Name -->
					<legend>
						<center>
							<h2>
								<b>有我罩你會員註冊</b>
							</h2>
						</center>
					</legend>
					<br>
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-3 control-label">會員姓名</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input name="memName"
									placeholder="Name" class="form-control" type="text">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">會員帳號</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-edit"></i></span> <input name="memId"
									placeholder="account" class="form-control" type="text">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">會員密碼</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-edit"></i></span> <input name="memPwd"
									placeholder="password" class="form-control" type="password">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">再次確認密碼</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-edit"></i></span> <input name="memPWD"
									placeholder="enter passwork again" class="form-control"
									type="password">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">會員性別</label>
						<div class="col-md-6  selectContainer">
							<div class="buying-selling-group" id="buying-selling-group"
								data-toggle="buttons">
								<label class="col-sm-offset-1 btn btn-default buying-selling">
									<input type="radio" name="memGender" id="option1"
									autocomplete="off" value="M"> <span class="radio-dot"></span>
									<span class="buying-selling-word">男</span>
								</label> <label class="btn btn-default buying-selling "> <input
									type="radio" name="memGender" id="option2" autocomplete="off"
									value="F"> <span class="radio-dot"></span> <span
									class="buying-selling-word">女</span>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">會員電話</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input
									name="memPhone" placeholder="+886" class="form-control"
									type="text">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3  control-label">會員EMail</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input
									name="memEmail" placeholder="E-Mail Address"
									class="form-control" type="email">
							</div>
						</div>
					</div>
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-3 control-label">會員地址</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-home"></i></span> <input name="address"
									placeholder="Address" class="form-control" type="text">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">信用卡號</label>
						<div class="col-md-6 inputGroupContainer">
							<div class="input-group col-sm-12">
								<input name="creditcard" placeholder="card" class="form-control"
									type="text">
							</div>

						</div>
					</div>
					<!-- ------------------------------------------------神奇小按鈕----------------------------------------------------------------------- -->
					<div class="form-group">
						<label class="col-sm-1"></label>
						<div class="col-sm-1">
							<span class="btn btn-default" />
						</div>
					</div>
					<!-- ----------------------------------------------------------------------------------------------------------------------- -->
					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 col-sm-4 col-sm-offset-1 control-label"></label>
						<div class="col-md-4">
							<button type="submit" class="btn btn-warning">
								SUBMIT <span class="glyphicon glyphicon-send"></span>
							</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	

	<%@ include file="/front/footerbar.jsp"%>
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front/js/navbar/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front/js/navbar/usebootstrap.js"></script>
</body>
</html>