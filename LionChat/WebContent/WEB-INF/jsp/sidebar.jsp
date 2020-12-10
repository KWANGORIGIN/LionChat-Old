<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="sidebar" class="sidebar-toggle">
	<ul class="nav nav-sidebar">
		<li><a href="/"> <i class="fa fa-home" aria-hidden="true"></i>
				<span>HOME</span>
		</a></li>
		<li role="separator" class="divider"></li>
		<!--  analytics  -->
		<li data-toggle="collapse" href="#view-analytics" aria-expanded="false"
			aria-controls="view-analytics"><a href="#"> <i class="fa fa-wrench"
				aria-hidden="true"></i> <span>ANALYTICS</span>
		</a></li>
		<li>
			<ul id="view-analytics" class="sub-menu collapse ${fn:contains(pageContext.request.requestURI,'view-analytics') ? 'in' : ''}">
				<!-- Use the controller to specify which jsp to display -->
				<li><a href="${pageContext.request.contextPath}/view-analytics/overall-ratings">Overall Ratings</a></li>
				<li><a href="${pageContext.request.contextPath}/view-analytics/intent-average-ratings">Intent Average Ratings</a></li>
				<li><a href="${pageContext.request.contextPath}/view-analytics/frequently-asked-questions">Frequently Asked Questions</a></li>
				<li><a href="${pageContext.request.contextPath}/view-analytics/commonly-misclassified-intents">Commonly Misclassified Intent</a></li>
			</ul>
		</li>
		<!--  /analytics  -->
		<li role="separator" class="divider"></li>
	</ul>
</div>