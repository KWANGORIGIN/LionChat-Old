<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("label", "Atlanta 1996");  map.put("y", 44); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Sydney 2000");  map.put("y", 37); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Athens 2004");  map.put("y", 34); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Beijing 2008");  map.put("y", 36); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "London 2012");  map.put("y", 46); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Brazil 2016");  map.put("y", 46); list.add(map);
String dataPoints1 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("label", "Atlanta 1996");  map.put("y", 16); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Sydney 2000");  map.put("y", 28); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Athens 2004");  map.put("y", 32); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Beijing 2008");  map.put("y", 51); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "London 2012");  map.put("y", 38); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Brazil 2016");  map.put("y", 26); list.add(map);
String dataPoints2 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("label", "Atlanta 1996");  map.put("y", 1); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Sydney 2000");  map.put("y", 11); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Athens 2004");  map.put("y", 9); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Beijing 2008");  map.put("y", 19); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "London 2012");  map.put("y", 29); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Brazil 2016");  map.put("y", 27); list.add(map);
String dataPoints3 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("label", "Atlanta 1996");  map.put("y", 26); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Sydney 2000");  map.put("y", 32); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Athens 2004");  map.put("y", 28); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Beijing 2008");  map.put("y", 23); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "London 2012");  map.put("y", 24); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Brazil 2016");  map.put("y", 19); list.add(map);
String dataPoints4 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("label", "Atlanta 1996");  map.put("y", 7); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Sydney 2000");  map.put("y", 8); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Athens 2004");  map.put("y", 9); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Beijing 2008");  map.put("y", 13); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "London 2012");  map.put("y", 13); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Brazil 2016");  map.put("y", 9); list.add(map);
String dataPoints5 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("label", "Atlanta 1996");  map.put("y", 20); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Sydney 2000");  map.put("y", 13); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Athens 2004");  map.put("y", 13); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Beijing 2008");  map.put("y", 16); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "London 2012");  map.put("y", 11); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Brazil 2016");  map.put("y", 17); list.add(map);
String dataPoints6 = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Gold Won in Olympics (Till 2016)"
           },
           subtitles: [
               {
                   text: "Click on Legend to Hide/Unhide Data Series"
               }
           ],
           animationEnabled: true,
           axisY: {
               titleFontFamily: "arial",
               titleFontSize: 12,
               includeZero: false
           },
           toolTip: {
               shared: true
           },
           data: [
           {
               type: "spline",
               name: "US",
               showInLegend: true,
               dataPoints: <%out.print(dataPoints1);%>
           },
           {
               type: "spline",
               name: "China",
               showInLegend: true,
               dataPoints: <%out.print(dataPoints2);%>
           },
           {
               type: "spline",
               name: "Britain",
               showInLegend: true,
               dataPoints: <%out.print(dataPoints3);%>
           },
           {
               type: "spline",
               name: "Russia",
               showInLegend: true,
               dataPoints: <%out.print(dataPoints4);%>
           },
           {
               type: "spline",
               name: "S Korea",
               showInLegend: true,
               dataPoints: <%out.print(dataPoints5);%>
           },
           {
               type: "spline",
               name: "Germany",
               showInLegend: true,
               dataPoints: <%out.print(dataPoints6);%>
           }
           ],
           legend: {
               cursor: "pointer",
               itemclick: function (e) {
                   if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                       e.dataSeries.visible = false;
                   }
                   else {
                       e.dataSeries.visible = true;
                   }
                   chart.render();
               }
           }
       });

       chart.render();
   });
</script>