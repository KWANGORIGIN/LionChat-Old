<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("x", 0);  map.put("y", 0); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 11);  map.put("y", 8.2); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 47);  map.put("y", 41.7); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 56);  map.put("y", 16.7); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 120);  map.put("y", 31.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 131);  map.put("y", 18.2); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 171);  map.put("y", 31.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 189);  map.put("y", 61.1); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 221);  map.put("y", 40.6); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 232);  map.put("y", 18.2); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 249);  map.put("y", 35.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 253);  map.put("y", 12.5); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 264);  map.put("y", 16.4); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 280);  map.put("y", 37.5); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 303);  map.put("y", 24.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 346);  map.put("y", 23.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 376);  map.put("y", 11.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 388);  map.put("y", 8.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 430);  map.put("y", 1.9); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 451);  map.put("y", 4.8); list.add(map);
String dataPoints1 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("x", 0);  map.put("y", 0); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 11);  map.put("y", 90); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 47);  map.put("y", 1590); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 56);  map.put("y", 1740); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 120);  map.put("y", 3740); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 131);  map.put("y", 3940); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 171);  map.put("y", 5190); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 189);  map.put("y", 6290); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 221);  map.put("y", 7590); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 232);  map.put("y", 7790); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 249);  map.put("y", 8390); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 253);  map.put("y", 8440); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 264);  map.put("y", 8620); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 280);  map.put("y", 9220); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 303);  map.put("y", 9780); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 346);  map.put("y", 10780); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 376);  map.put("y", 11120); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 388);  map.put("y", 11220); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 430);  map.put("y", 11300); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 451);  map.put("y", 11400); list.add(map);
String dataPoints2 = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Speed And Distance Time Graph"
           },
           animationEnabled: true,
           toolTip: {
               shared: true,
               content: function (e) {
                   var body;
                   var head;
                   head = "<span style = 'color:DodgerBlue; '><strong>" + (e.entries[0].dataPoint.x) + " sec</strong></span><br/>";

                   body = "<span style= 'color:" + e.entries[0].dataSeries.color + "'> " + e.entries[0].dataSeries.name + "</span>: <strong>" + e.entries[0].dataPoint.y + "</strong>  m/s<br/> <span style= 'color:" + e.entries[1].dataSeries.color + "'> " + e.entries[1].dataSeries.name + "</span>: <strong>" + e.entries[1].dataPoint.y + "</strong>  m";

                   return (head.concat(body));
               }
           },
           axisY: {
               title: "Speed",
               includeZero: false,
               suffix: " m/s",
               lineColor: "#369EAD"
           },
           axisY2: {
               title: "Distance",
               includeZero: false,
               suffix: " m",
               lineColor: "#C24642"
           },
           axisX: {
               title: "Time",
               suffix: " s"
           },
           data: [
           {
               type: "spline",
               name: "Speed",
               dataPoints: <%out.print(dataPoints1);%>
           },
           {
               type: "spline",
               axisYType: "secondary",
               name: "Distance Covered",
               dataPoints: <%out.print(dataPoints2);%>
           }
           ]
       });

       chart.render();
   });
</script>