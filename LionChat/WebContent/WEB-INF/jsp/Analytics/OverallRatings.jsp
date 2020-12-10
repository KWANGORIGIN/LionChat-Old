<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<div id='chartContainer'></div>

<script type="text/javascript">
   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Average Ratings"
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
               dataPoints: ${dataPoints}
           }
           ]
       });
       chart.options.data[0].dataPoints.sort((dataPoint1, dataPoint2) => {return dataPoint2.y-dataPoint1.y});
       chart.render();
   });
</script>