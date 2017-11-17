<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.hcorder.modal.*" %>

<jsp:useBean id="HcOrderDetailService" scope="page" class="com.hcorder.modal.HcOrderDetailService"/>






<%@ include file="/front/navbar.jsp" %>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>長照訂單</title>

		

		
	</head>
	<body>





<!-- 各項服務內容 ============================================================================== -->

	
	<c:if test="${not empty hcOrderMasterVO.orderNo}">
	新增訂單成功${hcOrderMasterVO.orderNo}
	
	</c:if>


	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">	



                <table class="table table-hover">
                    <caption>名細清單</caption>
                    <thead>
                        <tr>
                            <th>訂單編號</th>
                            <th>下定日期</th>
                            <th>照護對象</th>
                            <th>服務日期</th>
                            <th>服務時段</th>
                            <th>服務人員</th>
                            <th>訂單狀態</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="hcOrderDetailVO" items="${HcOrderDetailService.getAllByOrderNo(hcOrderMasterVO.orderNo)}">
                        <tr>
                            <td>${hcOrderDetailVO.orderDatailNo}</td>
                            <td>${hcOrderMasterVO.orderDate}</td>
                            <td>${hcOrderMasterVO.caredNo}</td>
                            <td>${hcOrderDetailVO.serviceDate}</td>
                            <td>${hcOrderDetailVO.serviceTime}</td>
                            <td>${hcOrderDetailVO.empNo}</td>
                            <td>${hcOrderDetailVO.orderDetailStataus}</td>
                        </tr>
                     </c:forEach>

                    </tbody>
                </table>

			</div>
		</div>
	</div>



<%@ include file="/front/footerbar.jsp" %>    
	</body>
</html>