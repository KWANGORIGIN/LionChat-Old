<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id='chartContainer'></div>

<script type="text/javascript">
$(function () {
    var chart = new CanvasJS.Chart("chartContainer", {
        theme: "light2",
        zoomEnabled: true,
        animationEnabled: true,
        title: {
            text: "Line Chart with Data-Points from DataBase"
        },
        data: [
        {
            type: "line",
            dataPoints: ${dataPoints},
        }
        ]
    });
    chart.render();
});

</script>