<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="chartContainer"></div>

<script>
var dataPoints = []; 

$.get("/restfull-service/datapoints?xstart=5&ystart=10&length=50&type=xml", function(data) {
    $(data).find("point").each(function () {
            var $dataPoint = $(this);
            var x = $dataPoint.find("x").text();
            var y = $dataPoint.find("y").text();
            dataPoints.push({x: parseFloat(x), y: parseFloat(y)});
    });

    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        zoomEnabled: true,
        theme: "light2",
    	title: {
                text: "CanvasJS Charts in Spring MVC using XML & AJAX",
            },
            data: [{
                 type: "line",
                 dataPoints: dataPoints,
              }]
    });
chart.render();
});
</script>