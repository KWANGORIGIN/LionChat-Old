<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<div id='chartContainer1' onclick="enlarge(1)"
	style='width: 49%; height: 300px; display: inline-block;'></div>
<div id='chartContainer2' onclick="enlarge(2)"
	style='width: 49%; height: 300px; display: inline-block;'></div>
<div id='chartContainer3' onclick="enlarge(3)"
	style='width: 49%; height: 300px; display: inline-block;'></div>
<div id='chartContainer4' onclick="enlarge(4)"
	style='width: 49%; height: 300px; display: inline-block;'></div>

<script type="text/javascript">
function enlarge(n) {
	for (i = 1; i <= 4; i++){
		if(i == n){
			continue;
		}
		chart[i-1].container.remove()
	}

	chart[n-1].container.style.visibility = 'visible'
	chart[n-1].container.style.width = '100%'
	chart[n-1].container.style.height = '100%'
	chart[n-1].render()

}
var chart = [null, null, null, null];
$(function () {
	function compareDataPointYDescending(dataPoint1, dataPoint2) {
		return dataPoint2.y - dataPoint1.y;
	}
	
	chart[0] = new CanvasJS.Chart("chartContainer1", {
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
            dataPoints: ${dataPoints1},
        }]
    });
	chart[0].options.data[0].dataPoints.sort(compareDataPointYDescending);
	chart[0].render();

	chart[1] = new CanvasJS.Chart("chartContainer2", {
        title: {
            text: "Average Overall Ratings"
        },
        animationEnabled: true,
        legend: {
            verticalAlign: "center",
            horizontalAlign: "left",
            fontSize: 20,
            fontFamily: "Helvetica",
            reversed: true
        },
        theme: "light2",
        data: [
        {
            type: "pie",
            indexLabelFontFamily: "Garamond",
            indexLabelFontSize: 20,
            indexLabel: "{label}\u2B50 (#percent%)",
            startAngle: -20,
            showInLegend: true,
            toolTipContent: "{rating}\u2B50 (#percent%)",
            legendText: "{rating} \u2B50",
            percentFormatString: "#0.##",
            dataPoints: ${dataPoints2}
        }
        ]
    });
	chart[1].options.data[0].dataPoints.sort(compareDataPointYDescending);
	chart[1].render();

	chart[2] = new CanvasJS.Chart("chartContainer3", {
        theme: "light2",
        animationEnabled: true,
        title: {
            text: "Frequently Asked Questions"
        },
        axisY:{
            minimum: 0,
            suffix: " Ratings"
		},
		axisX:{
			title: "Classified Intent of Users Question"
		},
        data: [{
            type: "column",
            dataPoints: ${dataPoints3}
        }]
    });
	chart[2].options.data[0].dataPoints.sort(compareDataPointYDescending);
	chart[2].render();

	chart[3] = new CanvasJS.Chart("chartContainer4", {
        theme: "light2",
        animationEnabled: true,
        title: {
            text: "Frequently Misclassified Questions By Intent"
        },
        axisY:{
            minimum: 0,
			title: "1\u2B50 Ratings"
		},
		axisX:{
			title: "Misclassified Intent of Users Question"
		},
        data: [{
            type: "column",
            dataPoints: ${dataPoints4}
        }]
    });
	chart[3].options.data[0].dataPoints.sort(compareDataPointYDescending);
	chart[3].render();
});
</script>