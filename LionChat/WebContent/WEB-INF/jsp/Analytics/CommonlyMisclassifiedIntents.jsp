<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<div id='chartContainer'></div>
<script type="text/javascript">
    $(function () {
        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2",
            animationEnabled: true,
            title: {
                text: "Frequently Misclassified Questions By Intent"
            },
            axisY:{
                minimum: 0,
                suffix: "   1\u2B50 Ratings"
			},
			axisX:{
				title: "Misclassified Intent of Users Question"
			},
            data: [{
                type: "column",
                dataPoints: ${dataPoints}
            }]
        });
        chart.options.data[0].dataPoints.sort((dataPoint1, dataPoint2) => {return dataPoint2.y-dataPoint1.y});
        chart.render();
    });
</script>