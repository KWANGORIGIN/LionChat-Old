<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="chartContainer" style="height:400px;"></div>
<div style='margin-left:30%'>
    <button id='StartButton' class='btn btn-success' type='submit' value='Start'>Start Live Chart</button>
    <button id='StopButton' class='btn btn-danger' type='submit' value='Stop'>Stop Live Chart</button>
</div><br />

<script type="text/javascript">

   $(function () {
       var dps = [{ x: 1, y: 15 }, { x: 2, y: 20 }, { x: 3, y: 18 }, { x: 4, y: 22 }, { x: 5, y: 20 }];   //dataPoints.

       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Live Chart with Start/Stop Controls"
           },
           axisY: {
               title: "Units"
           },
           data: [{
               type: "spline",
               dataPoints: dps
           }]
       });

       chart.render();

       // Updating the Chart
       var xVal = dps.length + 1;
       var yVal = 20;
       var updateInterval = 500;

       var updateChart = function () {
           yVal = yVal + Math.round(5 + Math.random() * (-5 - 5));
           dps.push({ x: xVal, y: yVal });
           xVal++;
           chart.render();
       };

       var timeoutId;

       function startLiveChart() {
           timeoutId = setInterval(function () { updateChart() }, updateInterval);
           $('#StartButton').unbind('click', startLiveChart);
           $('#StopButton').bind('click', stopLiveChart);
       }

       function stopLiveChart() {
           clearTimeout(timeoutId);
           $('#StopButton').unbind('click', stopLiveChart);
           $('#StartButton').bind('click', startLiveChart);
       }

       $('#StartButton').bind('click', startLiveChart);

   });
</script>