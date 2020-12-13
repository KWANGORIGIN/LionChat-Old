<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="sidebar" class="sidebar-toggle">
	<ul class="nav nav-sidebar">
		<li><a href="${pageContext.request.contextPath}/"> <i class="fa fa-home" aria-hidden="true" style="color: #000088"></i>
				<span style="color: #000088">HOME</span>
		</a></li>
		<li role="separator" class="divider"></li>
		<!--  analytics  -->
		<li data-toggle="collapse" href="#view-analytics" aria-expanded="false"
			aria-controls="view-analytics"><a href="#"> <i class="fa fa-wrench"
				aria-hidden="true" style="color: #000088"></i> <span style="color: #000088">ANALYTICS</span>
		</a></li>
		<li>
			<ul id="view-analytics" class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'view-analytics') ? 'in' : ''}">
				<!-- Use the controller to specify which jsp to display -->
				<li><a href="${pageContext.request.contextPath}/view-analytics/overall-ratings" style="color: #000044">Overall Ratings</a></li>
				<li><a href="${pageContext.request.contextPath}/view-analytics/intent-average-ratings" style="color: #000044">Intent Average Ratings</a></li>
				<li><a href="${pageContext.request.contextPath}/view-analytics/frequently-asked-questions" style="color: #000044">Frequently Asked Questions</a></li>
				<li><a href="${pageContext.request.contextPath}/view-analytics/commonly-misclassified-intents" style="color: #000044">Commonly Misclassified Intent</a></li>
			</ul>
		</li>
		<!--  /analytics  -->
		<li role="separator" class="divider"></li>
	</ul>
</div>