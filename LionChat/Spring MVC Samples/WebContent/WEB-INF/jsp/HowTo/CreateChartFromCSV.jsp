<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="chartContainer"></div>

<script>
var dataPoints = [];
 
function getDataPointsFromCSV(csv) {
    var dataPoints = csvLines = points = [];
    csvLines = csv.split(/[\r?\n|\r|\n]+/);
        
    for (var i = 0; i < csvLines.length; i++)
        if (csvLines[i].length > 0) {
            points = csvLines[i].split(",");
            dataPoints.push({ 
                x: parseFloat(points[0]), 
                y: parseFloat(points[1]) 		
	    });
	}
    return dataPoints;
}
	 
$.get("/restfull-service/datapoints?xstart=5&ystart=10&length=50&type=csv", function(data) {
    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        zoomEnabled: true,
        theme: "light2",
    	title: {
	   	 text: "CanvasJS Charts in Spring MVC using CSV & AJAX",
        },
        data: [{
		    type: "line",
		    dataPoints: getDataPointsFromCSV(data)
	}]
    });
    chart.render();
});
</script>