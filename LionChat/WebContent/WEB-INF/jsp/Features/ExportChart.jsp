<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("y", 35);  map.put("legendText", "Health");  map.put("label", "Health"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 20);  map.put("legendText", "Finance");  map.put("label", "Finance"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 18);  map.put("legendText", "Career");  map.put("label", "Career"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 15);  map.put("legendText", "Education");  map.put("label", "Education"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 5);  map.put("legendText", "Family");  map.put("label", "Family"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 7);  map.put("legendText", "Real Estate");  map.put("label", "Real Estate"); list.add(map);
String dataPoints = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           title: {
               text: "Top Categories of New Year's Resolution"
           },
           exportFileName: "Pie Chart",
           exportEnabled: true,
           animationEnabled: true,
           legend: {
               verticalAlign: "bottom",
               horizontalAlign: "center"
           },
           data: [
           {
               type: "pie",
               showInLegend: true,
               toolTipContent: "{legendText}: <strong>{y}%</strong>",
               indexLabel: "{label} {y}%",
               dataPoints: <%out.print(dataPoints);%>
           }
           ]
       });
       chart.render();
   });
</script>