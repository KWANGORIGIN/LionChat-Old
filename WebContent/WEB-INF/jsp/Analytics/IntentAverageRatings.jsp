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
                text: "Intent Average Ratings"
            },
            axisY:{
                minimum: 0,
                maximum: 5,
                suffix: "\u2B50"
			},
			axisX:{
				title: "Classified Intent of Users Question"
			},
            data: [{
                type: "column",
                dataPoints: ${dataPoints}
            }]
        });
        chart.render();
    });
</script>