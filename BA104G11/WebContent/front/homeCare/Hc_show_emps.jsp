<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.member.model.*" %>

<jsp:useBean id="crdSvc" scope="page" class="com.thecared.model.ThecaredService"/>
<jsp:useBean id="empPhSvc" scope="page" class="com.employee_photo.model.EmpPhotosService"/>

<!-- 不可快取 -->
<% response.setDateHeader("Expires",0); %>

<% MemberService memSvc = new MemberService();
   MemberVO memVO = (MemberVO)memSvc.getOneMEMBER("MEM0002");
   session.setAttribute("memVO",memVO);
%>

<%@ include file="/front/navbar.jsp" %>
<!DOCTYPE html>
<html lang="zh-TW">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>長照訂單</title>
<!-- 		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- 		<link href="css/takecare.css" rel="stylesheet"> -->
		
<!-- 			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<!-- 			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script> -->
		
	</head>
	
		<style type="text/css">
		    .emplist .emp-img{
            	height:200px;
            }
            .emplist .emp-img img{
           		max-width:100%;
				max-height:100%;
				margin:auto;
				display:block;
            }
            .hc_order .modal-footer{
            	border-top:0px;
            }
		</style>
	<body>




<!-- 各項服務內容 ============================================================================== -->

            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">選擇照護人員</h4>
                    </div>
                    <div class="modal-body">
                        
                        <div class="form-group has-success">
                          <label class="control-label" for="caredNo">照護對象</label>
							<select size="1" class="form-control" name="caredNo" id="caredNo" aria-describedby="helpBlock2">
								<option value="" selected>請選擇</option>
								<c:forEach var="crdVO" items="${crdSvc.getAllByMemNo('MEM0002')}" > 
									<option value="${crdVO.caredNo}">${crdVO.caredName}
								</c:forEach>   
							</select>                     

                        </div>
                        
                        <div class="carer form-group has-success">
                        <label class="control-label" for="cared">選擇照護人員</label>
                        <input type="text"  class="form-control" id="empNo" name="empNo" aria-describedby="helpBlock2" readonly>
                        
<%--                         	<c:if test='${hcWorkShiftsVOList} == null || ${hcWorkShiftsVOList}.equals("")'>還沒選擇日期喔!</c:if> --%>
					       <div class="col-xs-12 col-sm-6">
							    <a href="#" class="thumbnail" ">
							        <img src="<%=request.getContextPath()%>/noData/noPic.jpg" alt="圖片載入中">
							        <div class="caption">
							            <h2>由我們幫你找最優秀的照護人員</h2>
							            <p>經驗豐富</p>
							        </div>
							    </a>
							</div>
							<c:forEach var="hcWorkShiftsVO" items="${hcWorkShiftsVOList}" varStatus="s" > 
							<div class="col-xs-12 col-sm-6" id>
                                <a href="javascript:produceEmp${s.index}()" class="thumbnail emplist" id="emp-btn${s.index}" aria-describedby="helpBlock2">
									<c:choose>
									    <c:when test="${empPhSvc.getAllByEMPNO(hcWorkShiftsVO.empNo).size()!= 0}">
									    <div class="emp-img">
									        <img src="<%=request.getContextPath()%>/DBGifReader4?emp_photo_no=${empPhSvc.getAllByEMPNO(hcWorkShiftsVO.getEmpNo()).get(0).getEmpPhtoNo()}"  alt="圖片連線中" class="chose-img${s.index}"> 
									    </div>
									    </c:when>

									    <c:otherwise>
									    	<div class="emp-img">
									        <img src="<%=request.getContextPath()%>/noData/noPic.jpg" alt="圖片連線中" class="chose-img${s.index}">
									        </div>
									    </c:otherwise>
									</c:choose>

								
                                    <div class="caption">
                                        <h2>${hcWorkShiftsVO.getEmpNo()}</h2>
                                        <p>經驗豐富</p>
                                    </div>
                                </a>
                                <script>
                                	function produceEmp${s.index}(){
                                		console.log('${hcWorkShiftsVO.getEmpNo()}');                                		
                                		$("[name='empNo']").val('${hcWorkShiftsVO.getEmpNo()}');
                                	};
                                </script> 
                            </div>
							</c:forEach>
<!-- 							須加入無人員處理 -->

                        </div>
                    </div>
                    
                    <form METHOD = "post" action="<%=request.getContextPath()%>/front/homeCare/Hc_order.jsp">
                    <div class="modal-footer">                      
                        <input type="text" name="servDate" value="${param.servDate}">
                        <input type="text" name="servTime" value="${param.servTime}">
                        <input type="text" name="caredNo" value="${param.caredNo}">
                        <input type="text" name="empNo" value="${param.empNo}">
                        <button type="submit" class="btn btn-primary" >確認</button>
                    </div>
                    </form>
                </div>
            </div>




		
		
		<%@ include file="/front/footerbar.jsp" %>  
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script>
			$(function() { 
				$("#caredNo").val("${param.caredNo}");
				
				$('#caredNo').change(function(){
					console.log($(this).find(':selected').attr('value'));
					$("[name='caredNo']").val($(this).find(':selected').attr('value'));
				
				});
				
			})
		</script>
	</body>
</html>