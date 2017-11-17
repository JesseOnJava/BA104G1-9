<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/front/navbar.jsp" %>
<jsp:useBean id="crdSvc" scope="page" class="com.thecared.model.ThecaredService"/>
<% MemberService memSvc = new MemberService();
   MemberVO memVO = (MemberVO)memSvc.findByPrimaryKey("MEM0002");
   session.setAttribute("memVO",memVO);
%>


<span>請選擇服務時間</span>
<div class="container">
	<div class="panel-body calender-container text-center">
		 <form action="<%=request.getContextPath()%>/HcOrder/HcOrderController.do" class="hc_order" method="post" id="hc_order">		 
		    <div id="test-n1" class=" center-block calender-body"></div>
		    
			<select size="1" class="form-control" name="caredNo"  id="caredNo-final" aria-describedby="helpBlock2">
				<option value="" selected>請選擇</option>
				<c:forEach var="crdVO" items="${crdSvc.getAllByMemNo('MEM0002')}" > 
					<option value="${crdVO.caredNo}">${crdVO.caredName}
				</c:forEach>   
			</select>   
			<input type="hidden" name="add_hc_order_many">
			
		    <input type="submit" >
		    
		 </form>
	  
	</div>
</div>






<%@ include file="/front/footerbar.jsp" %>

<script src="<%=request.getContextPath()%>/front/css/homeCare/laydate/laydate2.js"></script>
		<script type="text/javascript">
			lay('#version').html('-v'+ laydate.v);
	
			laydate.render({
			  elem: '#test-n1'
			  ,position: 'static'
			  ,showBottom: false
			});

		</script>

</body>
</html>