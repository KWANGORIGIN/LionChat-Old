<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<div id='chartContainer1' style="height: 400px; width: 100%;"></div>
<div id='chartContainer2' style="height: 400px; width: 100%;"></div>

<script type="text/javascript">
dataPoints1=${dataPoints1}
dataPoints2=${dataPoints2}
   $(function () {
	   
       //-------- Chart 1 ---------------
       var chart1 = new CanvasJS.Chart("chartContainer1", {
           zoomEnabled: true,
           animationEnabled: true,
           title: {
               text: "Chart1 - Try Zooming / Panning"
           },
           data: [{
               type: "line",

               dataPoints: dataPoints1
           }],
           rangeChanged: syncHandler
       });
       chart1.render();

       //-------- Chart 2 ---------------
       var chart2 = new CanvasJS.Chart("chartContainer2", {
           zoomEnabled: true,
           animationEnabled: true,
           title: {
               text: "Chart2 - Try Zooming / Panning"
           },
           data: [{
               type: "line",

               dataPoints: dataPoints2
           }],
           rangeChanged: syncHandler
       });

       chart2.render();

       //--------------------Sync Chart-------------------
       var charts = [chart1, chart2];

       function syncHandler(e) {

           for (var i = 0; i < charts.length; i++) {
               var chart = charts[i];

               if (!chart.options.axisX) chart.options.axisX = {};
               if (!chart.options.axisY) chart.options.axisY = {};

               if (e.trigger === "reset") {

                   chart.options.axisX.viewportMinimum = chart.options.axisX.viewportMaximum = null;
                   chart.options.axisY.viewportMinimum = chart.options.axisY.viewportMaximum = null;
                   chart.render();
               } else if (chart !== e.chart) {

                   chart.options.axisX.viewportMinimum = e.axisX[0].viewportMinimum;
                   chart.options.axisX.viewportMaximum = e.axisX[0].viewportMaximum;

                   chart.options.axisY.viewportMinimum = e.axisY[0].viewportMinimum;
                   chart.options.axisY.viewportMaximum = e.axisY[0].viewportMaximum;
                   chart.render();
               }
           }
       }
   });
</script>