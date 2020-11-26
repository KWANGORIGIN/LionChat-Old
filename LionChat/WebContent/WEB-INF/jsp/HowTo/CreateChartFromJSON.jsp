<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="chartContainer"></div>

<script type="text/javascript">
    $(document).ready(function () {
    	$.getJSON("/restfull-service/datapoints?xstart=1&ystart=10&length=100&type=json", function(data) {
            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                zoomEnabled: true,
                theme: "light2",//light1
                title: {
                    text: "CanvasJS Charts in Spring MVC using JSON & AJAX"
                },
                data: [
                {
                    // Change type to "bar", "splineArea", "area", "spline", "pie",etc.
                    type: "line",
                    dataPoints: data
                    }
                ]
            });

            chart.render();
        });

    });
</script>