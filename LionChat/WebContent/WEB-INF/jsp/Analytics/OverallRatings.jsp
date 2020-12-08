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
               indexLabel: "{label}\u2B50 {y}%",
               startAngle: -20,
               showInLegend: true,
               toolTipContent: "{rating}\u2B50 {y}%",
               legendText: "{rating} \u2B50",
               dataPoints: ${dataPoints}
           }
           ]
       });
       chart.render();
   });
</script>