<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("x", 1330540200000L);  map.put("y", 30000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1333218600000L);  map.put("y", 35000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1335810600000L);  map.put("y", 30000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1338489000000L);  map.put("y", 30400); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1341081000000L);  map.put("y", 20900); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1343759400000L);  map.put("y", 31000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1346437800000L);  map.put("y", 30200); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1349029800000L);  map.put("y", 30000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1351708200000L);  map.put("y", 33000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1354300200000L);  map.put("y", 38000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1356978600000L);  map.put("y", 38900); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1359657000000L);  map.put("y", 39000); list.add(map);
String dataPoints1 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("x", 1330540200000L); map.put("y", 20100); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1333218600000L);  map.put("y", 16000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1335810600000L);  map.put("y", 14000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1338489000000L);  map.put("y", 18000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1341081000000L);  map.put("y", 18000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1343759400000L);  map.put("y", 21000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1346437800000L);  map.put("y", 22000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1349029800000L);  map.put("y", 25000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1351708200000L);  map.put("y", 23000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1354300200000L);  map.put("y", 25000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1356978600000L);  map.put("y", 26000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1359657000000L);  map.put("y", 25000); list.add(map);
String dataPoints2 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("x", 1330540200000L);  map.put("y", 10100); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1333218600000L);  map.put("y", 6000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1335810600000L);  map.put("y", 3400); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1338489000000L);  map.put("y", 4000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1341081000000L);  map.put("y", 9000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1343759400000L);  map.put("y", 3900); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1346437800000L);  map.put("y", 4200); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1349029800000L);  map.put("y", 5000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1351708200000L);  map.put("y", 14300); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1354300200000L);  map.put("y", 12300); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1356978600000L);  map.put("y", 8300); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1359657000000L);  map.put("y", 6300); list.add(map);
String dataPoints3 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("x", 1330540200000L);  map.put("y", 1700); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1333218600000L);  map.put("y", 2600); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1335810600000L);  map.put("y", 1000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1338489000000L);  map.put("y", 1400); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1341081000000L);  map.put("y", 900); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1343759400000L);  map.put("y", 1000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1346437800000L);  map.put("y", 1200); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1349029800000L);  map.put("y", 5000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1351708200000L);  map.put("y", 1300); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1354300200000L);  map.put("y", 2300); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1356978600000L);  map.put("y", 2800); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1359657000000L);  map.put("y", 1300); list.add(map);
String dataPoints4 = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>	
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Annual Expenses"
           },
           animationEnabled: true,
           axisY: {
               includeZero: false,
               prefix: "$ "
           },
           toolTip: {
               shared: true,
               content: "<span style='\"'color: {color};'\"'><strong>{name}</strong></span> <span style='\"'color: dimgrey;'\"'>$ {y}</span> "
           },
           legend: {
               fontSize: 13
           },
           data: [
           {
               type: "splineArea",
               showInLegend: true,
               name: "Salaries",
               markerSize: 0,
               color: "rgba(54,158,173,.6)",
               xValueType: "dateTime",
               dataPoints: <%out.print(dataPoints1);%>
           },
           {
               type: "splineArea",
               showInLegend: true,
               name: "Office Cost",
               markerSize: 0,
               color: "rgba(134,180,2,.7)",
               xValueType: "dateTime",
               dataPoints: <%out.print(dataPoints2);%>
           },
           {
               type: "splineArea",
               showInLegend: true,
               name: "Entertainment",
               markerSize: 0,
               color: "rgba(194,70,66,.6)",
               xValueType: "dateTime",
               dataPoints: <%out.print(dataPoints3);%>
           },
           {
               type: "splineArea",
               showInLegend: true,
               markerSize: 0,
               color: "rgba(127,96,132,.6)",
               name: "Maintenance",
               xValueType: "dateTime",
               dataPoints: <%out.print(dataPoints4);%>
           }

           ]
       });

       chart.render();
   });
</script>