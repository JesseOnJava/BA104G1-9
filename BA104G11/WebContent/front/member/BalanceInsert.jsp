<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.balance.model.*"%>

<%@ include file="/front/navbar.jsp"%>

<%@ include file="/front/member/Breadcrumbs_Main.jsp"%>
<jsp:useBean id="balanceSvc" class="com.balance.model.BalanceService" />

<%
	String memNo = request.getParameter("memNo");
	List<BalanceVO> balanceVO = balanceSvc.getBalanceByMemNo(memNo);
	pageContext.setAttribute("balanceVO", balanceVO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加值</title>
	<style type="text/css">
		.mb20{
			margin-bottom: 20px;	
		}
	</style>
</head>
<body>


		<h1 class="text-center">信用卡加值</h1>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-sm-offset-3" >
				
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
					
				  		<input type="hidden" class="form-control" id="topupWay"  name="topupWay" value="CREDIT">
					
				  		<div class="input-group mb20">
				  		<span class="input-group-addon" id="basic-addon1">會員編號</span>${memberVO.memNo}
				  		<input type="hidden" class="form-control" id="memNo" name="memNo" value ="${memberVO.memNo}" >			  
						</div>

				  		<div class="input-group mb20">
				  		<span class="input-group-addon" id="basic-addon1">儲值金額</span>
				  		<input type="text" class="form-control"  id="topupValue" name="topupValue" value="" >
						</div>



				  		<div class="input-group mb20">
				  		<span class="input-group-addon" id="basic-addon1">卡號</span>
				  		<input type="text" class="form-control" id="creditCard" placeholder="如：1111222233334444" >
				  		</div>	

				  		<div class="input-group mb20">
				  		<span class="input-group-addon" id="basic-addon1">到期年月</span>
				  		<input type="text" class="form-control" id="yymm" placeholder="如：2212">
						</div>

				  		<div class="input-group mb20">
				  		<span class="input-group-addon" id="basic-addon1">檢查碼</span>
				  		<input type="text" class="form-control" id="code" placeholder="如：123" >
				  		</div>	



						<p>
					  		<input type="hidden" name="action" value="balance_insert">
							<input type="hidden" name="memNo" value="${memberVO.memNo}" >
							<input type="submit" class="btn-danger" value="確認新增">	
						</p>
				  	</form>
	<div>1. 一元等於一點。</div>			  	
	<div>2. 完成付款程序後，將會直接進行加值，無法取消該筆交易，請詳細確認購買資料。
	確認付款完成後，詳情請至會員專區/我的錢包 網頁中查詢儲值狀態。</div>
	
<!--	<div>3. 確認付款完成後，系統會自動加值金額在您的帳號中。儲值成功後將立即以簡訊通知您：餘額等資訊。
	您也可以在 會員專區/我的錢包中 查詢是否已儲值完成。</div>
-->	
				</div>
			</div>
				  		

			<button class="btn-success" onclick="myFunction()">
				<div class="col-xs-12 col-sm-6">
					<div class="btn"><span >magic</span>
					</div>
				</div>
			</button>
		</div>
		
<br><br><br><br>
	<script type="text/JavaScript">
		function myFunction() {
		    document.getElementById("topupValue").value = "6000";
		    document.getElementById("creditCard").value = "0000887700007788";
		    document.getElementById("yymm").value = "1221";
		    document.getElementById("code").value = "321";
		}
	</script>
</body>
<%@ include file="/front/footerbar.jsp"%>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath()%>/front/js/navbar/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front/js/navbar/usebootstrap.js"></script>
</html>