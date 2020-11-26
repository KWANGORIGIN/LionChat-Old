<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title} | CanvasJS Chart using Spring Web MVC</title>
<link href="<c:url value="/assets/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/assets/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
<link href="<c:url value="/assets/css/style.css" />" rel="stylesheet">
<!-- scripts -->
<script src="<c:url value="/assets/script/jquery-1.11.1.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/assets/script/bootstrap.min.js" />"	type="text/javascript"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="/assets/script/html5shiv.min.js"></script>
      <script src="/assets/script/respond.min.js"></script>
    <![endif]-->
<script src="https://canvasjs.com/assets/script/canvasjs.min.js" type="text/javascript"></script>
<script>
	$(function() {
		// #sidebar-toggle-button
		$('#sidebar-toggle-button').on('click', function() {
			$('#sidebar').toggleClass('sidebar-toggle');
			$('#page-content-wrapper').toggleClass('page-content-toggle');
			fireResize();
		});

		// sidebar collapse behavior
		$('#sidebar').on('show.bs.collapse', function() {
			$('#sidebar').find('.collapse.in').collapse('hide');
		});

		// To make current link active
		(function addActiveToNestedList () {
			var pageURL = $(location).attr('href');
			var URLSplits = pageURL.split('/');
		
			
			var routeURL = '/' + URLSplits[URLSplits.length - 2] + '/' + URLSplits[URLSplits.length - 1];
			var activeNestedList = $(
					'.sub-menu > li > a[href$="' + routeURL + '"]').parent();
			
			if (activeNestedList.length !== 0
					&& !activeNestedList.hasClass('active')) {
				$('.sub-menu > li').removeClass('active');
				activeNestedList.addClass('active');
			}
		})();

		function fireResize() {
			if (document.createEvent) { // W3C
				var ev = document.createEvent('Event');
				ev.initEvent('resize', true, true);
				window.dispatchEvent(ev);
			} else { // IE
				element = document.documentElement;
				var event = document.createEventObject();
				element.fireEvent("onresize", event);
			}
		}

	})
</script>
</head>

<body>
	<!-- header -->
	<jsp:include page="header.jsp" />
	<!-- /header -->

	<!-- sidebar -->
	<jsp:include page="sidebar.jsp" />
	<!-- /sidebar -->

	<div id="page-content-wrapper" class="page-content-toggle">
		<div class="container-fluid">
			<h1>${title}</h1>

			<div class="row">
				<div id="content" class="col-md-8 col-md-offset-1 col-xs-12">
					<jsp:include page="${partial}" /></div>
			</div>
			<!-- /row -->
		</div>

		<!-- footer -->
		<jsp:include page="footer.jsp" />
		<!-- /footer -->
	</div>
</body>
</html>