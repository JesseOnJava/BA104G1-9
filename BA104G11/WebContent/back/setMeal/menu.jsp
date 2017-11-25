<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dish.model.*"%>
<%@ page import="com.menu.model.*"%>
<%
	MenuService menuSvc = new MenuService();
    Integer smNo=(Integer)request.getAttribute("smNo");
	List<MenuVO> menuList = menuSvc.getAll(smNo);
	pageContext.setAttribute("menuList", menuList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link
	href="<%=request.getContextPath()%>/back/css/fullcalendar-scheduler/fullcalendar.min.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/back/css/fullcalendar-scheduler/scheduler.min.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/back/js/fullcalendar-scheduler/moment.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back/js/fullcalendar-scheduler/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back/js/fullcalendar-scheduler/jquery-ui.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back/js/fullcalendar-scheduler/fullcalendar.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back/js/fullcalendar-scheduler/scheduler.min.js"></script>
<script
	src="<%=request.getContextPath()%>/back/js/fullcalendar-scheduler/zh-tw.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(function() { // document ready
    
		/* initialize the external events
		-----------------------------------------------------------------*/
       
		$('#external-events .fc-event').each(function() {

			// store data so the calendar knows to render an event upon drop
			$(this).data('event', {
				title : $.trim($(this).text()), // use the element's text as the event title
				stick : true, // maintain when user navigates (see docs on the renderEvent method)

			});

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
			//  original position after the drag
			});

		});

		/* initialize the calendar
		-----------------------------------------------------------------*/

		$('#calendar')
				.fullCalendar(
						{
							schedulerLicenseKey : 'CC-Attribution-NonCommercial-NoDerivatives',
							
					
							locale : 'zh-tw',
							editable : true,
							droppable : true,
							aspectRatio : 1.8,
							firstDay:1,
							header : {
								left : '',
								center : 'title',
								right : 'today prev,next'
							},
							defaultView : 'timelineWeek',
							views : {
								timelineWeek : {
									slotDuration : {
										days : 1
									},

								}
							},
							slotLabelFormat : [ 'ddd M/D',
							// top level of text
							],
							contentHeight: "auto",
							eventDurationEditable : false,
							resourceAreaWidth : 100,
							resourceLabelText : 'aa',
							resources : [ {
								id : '早餐',
								title : '早餐'
							}, {
								id : '中餐',
								title : '中餐'
							}, {
								id : '晚餐',
								title : '晚餐'
							}, ],

							events:[
								<%for (int i = 0; i < menuList.size(); i++) {%>
		                         {
								id:'<%=i%>',
								resourceId:'<%=menuList.get(i).getMealTime()%>',
								start:'<%=menuList.get(i).getMenuDate()%>',
								title:'<%=menuList.get(i).getDishName()%>',
								editable:false,
								color:'red',
								className:"myClass"
		                         },
								<%}%>
							], 
						    	
							drop : function(date, jsEvent, ui, resourceId,resourceId) {
						

								// is the "remove after drop" checkbox checked?
								if ($('#drop-remove').is(':checked')) {
									// if so, remove the element from the "Draggable Events" list
									$(this).remove();
								}
							},
							eventReceive : function(event) { // called when a proper external event is dropped
							    console.log('eventReceive', event);
							
							},
							eventDrop : function(event) { // called when an event (already on the calendar) is moved

								console.log('eventDrop', event);
							},
							eventDragStop: function(event, jsEvent) {

							    var trashEl = $('#event-delete');
							    var ofs = trashEl.offset();

							    var x1 = ofs.left;
							    var x2 = ofs.left + trashEl.outerWidth(true);
							    var y1 = ofs.top;
							    var y2 = ofs.top + trashEl.outerHeight(true);
							    if (jsEvent.pageX >= x1 && jsEvent.pageX<= x2 &&
							            jsEvent.pageY >= y1 && jsEvent.pageY <= y2) {
							        
							            $('#calendar').fullCalendar('removeEvents', event._id);
							        }

								
				                
								
// 							   
							}				
						});
		
		
		
		
		
		
		
		
		
		
		
		
		var objArray=[];
		
		
		$("#myBtn").click(function(){
			
			var events = $('#calendar').fullCalendar('clientEvents');
			//var events2= $('#calendar').fullCalendar('getResourceEvents','早餐');
			//console.log(events);
			//console.log(events2);
			$.each( events, function( key, value ) {
				var aaa = {
						date:null,
						time:null,
						dish:null
					};
					aaa.date = moment(value.start).format('YYYY-MM-DD');
					aaa.time = value.resourceId;
					aaa.dish = value.title;
				  objArray.push(aaa);
				  console.log(aaa);
				  console.log(objArray);

// 				  console.log(value.resourceId);
// 				  console.log(value.title);
// 				  console.log(moment(value.start).format('YYYY-MM-DD'));
				});
			
		     console.log(JSON.stringify(objArray));
		     console.log(typeof JSON.stringify(objArray) );
		     var jsonStr=JSON.stringify(objArray);
		    console.log(jsonStr);
		    $("input[name=test]").val(jsonStr);
		    console.log($("input[name=test]").val());
		    $("#form1").submit();
		});
		
		
		
		
		
	});
	
</script>

<style>
body {
	margin-top: 40px;
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

#wrap {
	width: 1100px;
	margin: 0 auto;
}

#external-events {
	float: left;
	width: 150px;
	padding: 0 10px;
	border: 1px solid #ccc;
	background: #eee;
	text-align: left;
}

#external-events h4 {
	font-size: 16px;
	margin-top: 0;
	padding-top: 1em;
}

#external-events .fc-event {
	margin: 10px 0;
	cursor: pointer;
}

#external-events p {
	margin: 1.5em 0;
	font-size: 11px;
	color: #666;
}

#external-events p input {
	margin: 0;
	vertical-align: middle;
}

#calendar {
	float: right;
	width: 900px;
}
</style>
</head>
<body>

   
	<jsp:useBean id="dishSvc" scope="page"
		class="com.dish.model.DishService" />
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<div id='external-events'>
					<h4>菜色</h4>
					<div class="panel-group" id="accordion">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse1">米食</a>
								</h4>
							</div>
							<div id="collapse1" class="panel-collapse collapse ">
								<div class="panel-body">
									<c:forEach var="dishVO" items="${dishSvc.getByType('米食')}">
										<div class='fc-event'>${dishVO.dishName}</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="panel panel-success">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse2">主食</a>
								</h4>
							</div>
							<div id="collapse2" class="panel-collapse collapse">
								<div class="panel-body">
									<c:forEach var="dishVO" items="${dishSvc.getByType('主食')}">
										<div class='fc-event'>${dishVO.dishName}</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="panel panel-info">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse3"> 湯品</a>
								</h4>
							</div>
							<div id="collapse3" class="panel-collapse collapse">
								<div class="panel-body">
									<c:forEach var="dishVO" items="${dishSvc.getByType('湯品')}">
										<div class='fc-event'>${dishVO.dishName}</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="panel panel-warning">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse4"> 湯品</a>
								</h4>
							</div>
							<div id="collapse4" class="panel-collapse collapse">
								<div class="panel-body">
									<c:forEach var="dishVO" items="${dishSvc.getByType('蔬食')}">
										<div class='fc-event'>${dishVO.dishName}</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="panel panel-danger">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse5"> 湯品</a>
								</h4>
							</div>
							<div id="collapse5" class="panel-collapse collapse">
								<div class="panel-body">
									<c:forEach var="dishVO" items="${dishSvc.getByType('甜品')}">
										<div class='fc-event'>${dishVO.dishName}</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse6"> 湯品</a>
								</h4>
							</div>
							<div id="collapse6" class="panel-collapse collapse">
								<div class="panel-body">
									<c:forEach var="dishVO" items="${dishSvc.getByType('飲品')}">
										<div class='fc-event'>${dishVO.dishName}</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="event-delete">
					<img src="<%=request.getContextPath()%>/front/image/share/01.png">
				</div>
			</div>
			<div class="col-xs-12 col-sm-9">
			<h1>${smName}</h1>
			<div id='calendar'></div>
			<form id="form1" method="post" action="<%=request.getContextPath()%>/Menu.do">
			<input type="hidden" name="action" value="insertMenu">
			<input type="hidden" name="test">
			<input type="hidden" name="smNo" value="${smNo}">
			<input id="myBtn" type="button" value="save">			
			</form>		
			</div>
				<div style='clear: both'></div>
		</div>
		
	</div>


	


		<!-- 			<button class="btn mybtn">確認修改</button> -->
		<div style='clear: both'></div>

	</div>
</body>
</html>


