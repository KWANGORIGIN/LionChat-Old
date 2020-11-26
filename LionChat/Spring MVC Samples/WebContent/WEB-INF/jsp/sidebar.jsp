<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="sidebar" class="sidebar-toggle">
	<ul class="nav nav-sidebar">
		<li><a href="/"> <i class="fa fa-home" aria-hidden="true"></i>
				<span>HOME</span>
		</a></li>
		<li role="separator" class="divider"></li>
		<!-- chart types -->
		<li data-toggle="collapse" href="#chart-types" aria-expanded="false"
			aria-controls="chart-types"><a href="#"> 
			<i class="fa fa-area-chart" aria-hidden="true"></i><span>CHART TYPES</span>
		</a></li>
		<li>
			<ul id="chart-types" class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'chart-types') ? 'in' : ''}">
				<li><a href="${pageContext.request.contextPath}/chart-types/column">Column Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/line">Line Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/step-line">Step Line Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/spline">Spline Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/step-area">Step Area Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/area">Area Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/spline-area">Spline Area Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/bar">Bar Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/pie">Pie Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/doughnut">Doughnut Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/bubble">Bubble Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/scatter">Scatter Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/stacked-column">Stacked Column Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/stacked-column100">Stacked Column 100% Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/stacked-area">Stacked Area Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/stacked-area100">Stacked Area 100% Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/stacked-bar">Stacked Bar Chart</a></li>
				<li><a href="${pageContext.request.contextPath}/chart-types/stacked-bar100">Stacked Bar 100% Chart</a></li>
<%-- 				<li><a href="chart-types/column.jsp">Range Column Chart</a></li> --%>
<%-- 				<li><a href="chart-types/column.jsp">Range Bar Chart</a></li> --%>
<%-- 				<li><a href="chart-types/column.jsp">Range Area Chart</a></li> --%>
<%-- 				<li><a href="chart-types/column.jsp">Range Spline Area Chart</a></li> --%>
<%-- 				<li><a href="chart-types/ohlc.jsp">OHLC Chart</a></li> --%>
<%-- 				<li><a href="chart-types/candlestick.jsp">Candlestick Chart</a></li> --%>
			</ul>
		</li>
		<!-- /chart types -->
		<li role="separator" class="divider"></li>
		<!--  features -->
		<li data-toggle="collapse" href="#features" aria-expanded="false"
			aria-controls="features"><a href="#"> <i class="fa fa-flask"
				aria-hidden="true"></i> <span>FEATURES</span>
		</a></li>
		<li>
			<ul id="features" class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'features') ? 'in' : ''}">
				<li><a href="${pageContext.request.contextPath}/features/zooming-panning">Zooming &amp; Panning</a></li>
				<li><a href="${pageContext.request.contextPath}/features/export-chart">Export Chart as Image</a></li>
				<li><a href="${pageContext.request.contextPath}/features/events-handling">Event Handling</a></li>
				<li><a href="${pageContext.request.contextPath}/features/log-axis">Logarithmic Axis</a></li>
				<li><a href="${pageContext.request.contextPath}/features/date-time">Date-Time Axis</a></li>
				<li><a href="${pageContext.request.contextPath}/features/reverse-axis">Reverse Axis</a></li>
				<li><a href="${pageContext.request.contextPath}/features/striplines">StripLines</a></li>
				<li><a href="${pageContext.request.contextPath}/features/multiple-y-axis">Multiple Y Axis</a></li>
				<li><a href="${pageContext.request.contextPath}/features/multi-series-chart">Multiseries Charts</a></li>
			</ul>
		</li>
		<!--  /features -->
		<li role="separator" class="divider"></li>
		<!--  how to  -->
		<li data-toggle="collapse" href="#how-to" aria-expanded="false"
			aria-controls="how-to"><a href="#"> <i class="fa fa-wrench"
				aria-hidden="true"></i> <span>HOW TO</span>
		</a></li>
		<li>
			<ul id="how-to" class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'how-to') ? 'in' : ''}">
				<li><a href="${pageContext.request.contextPath}/how-to/enable-disable-ds">Hide / Show DataSeries</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/live-chart">Dynamic / Live Chart</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/sync-multiple-charts">Sync Multiple Charts</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/datapoints-from-user-input">Add Data Points From User Input</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/multiple-charts-in-a-page">Multiple Charts in a Page</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/play-stop-live-chart">Play / Stop Live Chart</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/create-chart-from-json">Charts using JSON &amp; AJAX</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/create-chart-from-xml">Charts using XML &amp; AJAX</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/create-chart-from-csv">Charts using CSV &amp; AJAX</a></li>
                <li><a href="${pageContext.request.contextPath}/how-to/data-from-database">Render Data From Database</a></li>
			</ul>
		</li>
		<!--  /how to  -->
		<li role="separator" class="divider"></li>
	</ul>
</div>