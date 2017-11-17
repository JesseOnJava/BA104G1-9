<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.hcworkshifts.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% MemberService memSvc = new MemberService();
   MemberVO memVO = (MemberVO)memSvc.getOneMemByNo("MEM0002");
   session.setAttribute("memVO",memVO);
%>
<jsp:useBean id="crdSvc" scope="page" class="com.thecared.model.ThecaredService"/>
<jsp:useBean id="empPhSvc" scope="page" class="com.employee_photo.model.EmpPhotosService"/>


<% String memNo = memVO.getMemNo();
request.setCharacterEncoding("UTF-8");
	
%>


<%@ include file="/front/navbar.jsp" %>
<!DOCTYPE html>
<html lang="">
	<head>
	
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>長照服務</title>
<!-- 		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- 		<link href="css/takecare.css" rel="stylesheet"> -->
<!-- 		<!--[if lt IE 9]--> -->
<!-- 			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<!-- 			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script> -->



        <style type="text/css">
            .introduction{
                background-color:rgba(255, 238, 170, 0.29);
            }
            .instruction{
                background-color:rgba(255, 238, 170, 0.29);


            }
            .instruction hr{
                border:0; 
                height:1px; 
                background-color:#d4d4d4;
                color:#d4d4d4   ;
            }
            .instruction-head{
                margin-bottom: 30px;
            }
            .instruction img{
                width: 485px;
                height:301px;
            }
            .instruction .imgblock,.instruction .text-item{
                margin-bottom: 30px;
            }
            .emplist .emp-img{
            	height:200px;
            }
            .emplist .emp-img img{
           		max-width:100%;
				max-height:100%;
				margin:auto;
				display:block;
            }
            #showfinal-emp{
            	width:250px;
            	height:200px;
            }
            #showfinal-emp img{
           		max-width:100%;
				max-height:100%;
				margin:auto;
				display:block;
            }
            .hc_order .modal-footer{
            	
            }
            
        </style>

	</head>
	<body>


<!-- 內容 ============================================================================== -->

        <!-- 引言 --------------------------------------------------------------- -->
        <div class="container introduction">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <div class="text-item text-center">
                        <h1>長照服務</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Non nulla fuga nam aliquam voluptate, ratione eaque dolorum quos quibusdam nihil, est explicabo molestiae optio, voluptatibus. Voluptates explicabo quis itaque repudiandae, cum, vel. Optio, expedita modi magnam explicabo animi dolorem cupiditate. Amet quia praesentium repudiandae, fugit reprehenderit ullam alias omnis fuga earum cum quis ab. Quaerat nostrum quis fugiat veritatis itaque?</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- 服務流程 ------------------------------------------------- -->
        <div class="container instruction">
            <div class="row instruction-head">
                <div class="col-xs-12 col-sm-4">
                    <br>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-4 text-center">
                    <h2>如何獲得服務?</h2>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <br>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6">
                    <div class="imgblock text-center">
                        <img src="<%=request.getContextPath()%>/front/homeCare/image/loughing-elder.jpg" >
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="panel panel-success text-item">
                      <div class="panel-heading">
                        <h3 class="panel-title">填寫會員基本資料</h3>
                      </div>
                      <div class="panel-body text-center">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatum ducimus, quis necessitatibus nobis accusantium explicabo iure impedit animi voluptas illum fugit porro itaque culpa inventore unde, aliquam, ad architecto enim.</p>
                        <a href='#註冊' data-toggle="modal" class="btn btn-primary">還沒註冊嗎?</a>
                      </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-sm-push-6">
                    <div class="imgblock text-center">
                        <img src="<%=request.getContextPath()%>/front/homeCare/image/a001.jpg">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-sm-pull-6">
                    <div class="panel panel-success text-item">
                      <div class="panel-heading">
                        <h3 class="panel-title">選擇服務日期&時段</h3>
                      </div>
                      <div class="panel-body text-center">
                        <div class="row">
                            <div class="text-item">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.<br>
                                Fugiat odio nam, praesentium quis, <br>
                                sapiente natus quibusdam iste numquam nulla <br>
                                repellat qui reprehenderit? Amet, perferendis <br>
                                animi. Asperiores iure, excepturi fugit delectus!</p>
								<c:if test="${not empty errorMsgs}">
										<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color:red">${message}</li>
										</c:forEach>
										</ul>
								</c:if>
								<% request.removeAttribute("errorMsgs"); %>
                                
                            </div>
                            <a href='#modal-step1' data-toggle="modal" class="btn btn-primary">選擇日期</a>
                        </div>
                      </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-6">
                    <div class="imgblock text-center">
                        <img src="<%=request.getContextPath()%>/front/homeCare/image/a002.jpg">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="panel panel-success text-item">
                      <div class="panel-heading">
                        <h3 class="panel-title">選擇照護人員&被照護者</h3>
                      </div>
                      <div class="panel-body text-center">
                        <div class="row">
                            <div class="text-item">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.<br><br>
                                Fugiat odio nam, praesentium quis, <br>
                                sapiente natus quibusdam iste numquam nulla <br>
                                repellat qui reprehenderit? Amet, perferendis <br><br><br>
                                animi. Asperiores iure, excepturi fugit delectus!</p>
                                <a href='#modal-step2' data-toggle="modal" class="btn btn-primary">選擇照護人員</a>
                                <a href='<%= request.getContextPath()%>/back/' data-toggle="modal" class="btn btn-primary">選擇照護人員(跳頁)</a>
                            </div>
                        </div>
                      </div>
                    </div>
                </div>
            </div>
        </div>



        <!-- 跳窗 ------------------------------ -->
        <div class="modal fade" id="modal-step1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">選擇服務日期</h4>
                    </div>
                    
                    <form id='getAllByDateTime' action = "<%=request.getContextPath()%>/HcWorkshifts/HcWorkshiftsServlet.do" method="post">
                    
	                    <div class="modal-body">
	                        
	                        <div class="form-group has-success">
	                          <label class="control-label" for="servDate">請點選日期</label>
	                          <input type="text" value="${param.servDate}" class="form-control" id="servDate" name="servDate" aria-describedby="helpBlock2" readonly>
	                         </div>
	                        
	                         <div class="form-group has-success">
	                          <label class="control-label" for="servTime">請點選服務時段</label>
	                          <select class="form-control" id="servTime" name="servTime" aria-describedby="helpBlock2">
	                            <option value="" selected>請選擇服務時段</option>
	                          	<option value="早"> 上午(08:00~12:00)</option>
	                          	<option value="中"> 下午 (13:30~17:30)</option>
	                          	<option value="晚"> 晚上 (18:00~22:00)</option>
	                          </select>
	                          <span id="helpBlock2" class="help-block">貼心提醒:照護服務僅提供會員使用喔!<a href="#">點我加入會員</a></span>
	                        </div>
	                        <input type="hidden" name="action" value="getAllByDateTime">
	                        
	                    </div>
	                    <div class="modal-footer">                      
	<!--                         <input type="submit" class="btn btn-primary" data-dismiss="modal" id="datenloc-check" value="確認"> -->
	                        <input type="submit" class="btn btn-primary"  id="datenloc-check" value="確認">
	                        
	                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal-step2">
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
                                		$('.input-img').attr('src',$('.chose-img${s.index}').attr('src'));
                                		
                                		$('#empNo-final').attr('value','${hcWorkShiftsVO.getEmpNo()}');
                                		$('#empName-final').html('${hcWorkShiftsVO.getEmpNo()}');
                                		
                                	};
                                </script> 
                            </div>
							</c:forEach>
<!-- 							須加入無人員處理 -->

                        </div>
                    </div>
                    <div class="modal-footer">                      
                        <button type="button" class="btn btn-primary" data-dismiss="modal">確認</button>
                    </div>
                </div>
            </div>
        </div>


        <!-- form -------------------------------->


        <div class="container order-list">
            <div class="row">
                <div class="col-xs-12 col-sm-4">
                    <br>
                    <hr>
                </div>
                <div class="col-xs-12 col-sm-4 text-center">
                    <h2>立即取得服務</h2>
                </div>
                <div class="col-xs-12 col-sm-4">
                    <br>
                    <hr>
                </div>

            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-2"></div>
                <div class="col-xs-12 col-sm-8">
                    <div class="form-group">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">                      
                        <h4 class="modal-title">  </h4>
                    </div>
                    <form METHOD="post" ACTION="<%=request.getContextPath()%>/HcOrder/HcOrderController.do" class="hc_order">
	                    <div class="modal-body">	                        
	                            <div class="form-group ">
	                              <label class="control-label" for="servDate-final">服務日期</label>
	                              <input type="text" value="${param.servDate}" class="form-control" name="servDate" id="servDate-final" aria-describedby="helpBlock2" readonly>
	
	                              <label class="control-label" for="servTime-fianl">服務時段</label>
									<select id="servTime-final"  class="form-control" name="servTime"  aria-describedby="helpBlock2">
										<option value="">請選擇服務時段</option>
										<option value="早"> 上午(08:00~12:00)</option>
										<option value="中"> 下午 (13:30~17:30)</option>
										<option value="晚"> 晚上 (18:00~22:00)</option>
									</select>
	
	                              <label class="control-label" for="cared-fianl">服務對象</label>	                              
									<select size="1" class="form-control" name="caredNo"  id="caredNo-final" aria-describedby="helpBlock2">
										<option value="" selected>請選擇</option>
										<c:forEach var="crdVO" items="${crdSvc.getAllByMemNo('MEM0002')}" > 
											<option value="${crdVO.caredNo}">${crdVO.caredName}
										</c:forEach>   
									</select>    
	
	                              <label class="control-label" for="empNo-final">照護人員</label>
	                              <div id="showfinal-emp">
	                              
	                              	<c:choose>
									    <c:when test='${not empty param.empNo}'>
									        <img src="<%=request.getContextPath()%>/DBGifReader4?emp_photo_no=${empPhSvc.getAllByEMPNO(param.empNo).get(0).getEmpPhtoNo()}"  alt="圖片連線中" class="input-img"> 
									    
									    </c:when>
									    <c:when test='${not empty param.empNo && empPhSvc.getAllByEMPNO(param.empNo).size()==0}'>
									        <img src="<%=request.getContextPath()%>/noData/noPic.jpg" alt="圖片連線中" class="input-img"  alt="圖片連線中" class="input-img"> 
									    
									    </c:when>
									    

									    <c:otherwise>
									      	  <img src="" alt="還未選擇服務人員" class="input-img">									        
									    </c:otherwise>
									</c:choose>
	                              
	                              </div>
	                              
	                              
	                              <span id='empName-final'>${param.empNo}(${not empty param.empNo})</span>
	                              
	                              <input type="hidden" class="form-control" id="empNo-final" name="empNo" value="${param.empNo}" aria-describedby="helpBlock2">
	                              <input type="hidden" class="form-control" name="memNo" value="${memVO.mem_no}" aria-describedby="helpBlock2">
	                              <input type="hidden" class="form-control" name="action" value="add_hc_order" aria-describedby="helpBlock2">
		                         </div>	                        
	                    </div>
	                    <div class="modal-footer">
	                    	<span id="helpBlock2" class="help-block pull-left">還沒儲值嗎?<a href="#">點我儲值</a></span>                      
	                        <button type="submit" class="btn btn-primary" >確認送出</button>                        
	                    </div>
                    </form>
                </div>
            </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-2"></div>
            </div>
        </div>



        




<%@ include file="/front/footerbar.jsp" %>        
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script src="<%=request.getContextPath()%>/front/homeCare/laydate/laydate.js"></script> 
<script>
    laydate.render({
        elem: '#servDate' 
        ,min: '0'
        ,max: '2017-12-31'
        ,done: function(value, date, endDate){
            $('#servDate-final').attr('value',value);
            console.log(value); 
            console.log(date); 
        }
    });
    
    laydate.render({
        elem: '#servDate-final' 
        ,min: '0'
        ,max: '2017-12-31'
        ,done: function(value, date, endDate){
            $('#servDate').attr('value',value);
            console.log(value); 
            console.log(date); 
        }
    });
    

           
    $(function() { 
    	console.log('${param.servDate}'+'!!!');
    	console.log('${param.servTime}'+'!!!');
    	console.log('${param.caredNo}'+'!!!');
    	console.log('${param.empNo}'+'!!!');
    	
    	$('#datenloc-check').on('chang', function(){
			$('#getAllByDateTime').submit();	
		});    	
		
    	$("#servTime").val("${param.servTime}");
    	$("#servTime-final").val("${param.servTime}");
    	$("#caredNo").val("${param.caredNo}");
    	$("#caredNo-final").val("${param.caredNo}");
    	$("#empNo-final").val("${param.empNo}");
    	
    	$('#caredNo').change(function(){
    		console.log($(this).find(':selected').attr('value'));
    		$('#caredNo-final').val('${param.caredNo}');

    	});
    	$('#caredNo-final').change(function(){
    		console.log($(this).find(':selected').attr('value'));
    		$('#caredNo').val($(this).find(':selected').attr('value'));

    	});
    });

</script>



	</body>
</html>