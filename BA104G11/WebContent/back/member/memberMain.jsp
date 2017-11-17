<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/back/production/BA104G1_navbar_sidebar.jsp"%>
<link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.js"></script>

<html>
<head>
<title>會員管理</title>

<style>
table#table-1 {
	width: 930px;
	background-color: #DADFDF;
	margin-top: 5px;
	margin-bottom: 10px;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>


<!-- page content -->
<div class="right_col" role="main">
	<div class="">

		<div class="page-title">
			<div class="title_left">
				<h3>首頁</h3>
			</div>

			<div class="title_right">
				<div
					class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
					<!--                   <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                  </div> -->
				</div>
			</div>
		</div>


		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<table id="table-1">
							<tr>
								<td><h3>會員管理頁面</h3></td>
							</tr>
						</table>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false"><i
									class="fa fa-wrench"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Settings 1</a></li>
									<li><a href="#">Settings 2</a></li>
								</ul></li>
							<!-- XX -->
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>

						<div class="clearfix"></div>
					</div>
					<div class="x_content">



						
						
						
						
						
						
						
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<!-- /page content -->

<%@ include file="/back/production/BA104G1_footer.jsp"%>