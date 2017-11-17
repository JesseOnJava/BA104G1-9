<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/front/css/member/login.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/front/js/member/login.js"></script>


</head>
<body>
<c:if test="${not empty errorMsgs}">
  <font color='red'>請修正以下錯誤:
  <ul> 
  <c:forEach var="message" items="${errorMsgs}">
    <li>${message}</li>
  </c:forEach>
  </ul>
  </font>
</c:if>


	<div class="container">	
		<div class="row">

			<!-- Mixins-->
			<!-- Pen Title-->
			<div class="pen-title">
				<h1>有我罩你</h1>
			</div>
			<div class="container">
				<div class="card"></div>
				<div class="card">	
					<h1 class="title">員工登入</h1>
					
					<form action="<%= request.getContextPath()%>/employee/employee.do" method="post">
						<input type="hidden" name="action" value="login">
						<div class="input-container">
							<input type="text" id="Username" name="empId" required="required" /> <label
								for="Username">員工帳號</label>
							<div class="bar"></div>
						</div>
						<div class="input-container">
							<input type="password" id="Password" name="empPwd" required="required" /> <label
								for="Password">員工密碼</label>
							<div class="bar"></div>
						</div>
						<div class="button-container">
							<button>
								<span>Go</span>
							</button>
						</div>
						<div class="col-xs-12 col-sm-6"><div class="btn"><span>demo1</span></div></div>
					
						<div class="footer">
							<a href="#">Forgot your password?</a>
						</div>
					</form>
					
				</div>
				<div class="card alt">
					<div class="toggle"></div>
					<h1 class="title">
						Register
						<div class="close"></div>
					</h1>
					<form>
						<div class="input-container">
							<input type="text" id="Username" required="required" /> <label
								for="Username">Username</label>
							<div class="bar"></div>
						</div>
						<div class="input-container">
							<input type="password" id="Password" required="required" /> <label
								for="Password">Password</label>
							<div class="bar"></div>
						</div>
						<div class="input-container">
							<input type="password" id="Repeat Password" required="required" />
							<label for="Repeat Password">Repeat Password</label>
							<div class="bar"></div>
						</div>
						<div class="button-container">
							<button>
								<span>Next</span>
							</button>
						</div>
						
					</form>
				</div>
			</div>
			<!-- Portfolio-->
			<a id="portfolio" href="http://pkshopy.com/farooqshad/"
				title="View my portfolio!"><i class="fa fa-link"></i></a>
		</div>
	</div>


</body>
</html>