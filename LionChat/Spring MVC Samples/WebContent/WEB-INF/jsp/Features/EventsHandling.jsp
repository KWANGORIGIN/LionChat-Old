<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("y", 71); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 55); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 50); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 65); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 95); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 68); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 28); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 34); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 14); list.add(map);
String dataPoints1 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("y", 55); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 50); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 65); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 95); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 68); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 28); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 34); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 14); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 71); list.add(map);
String dataPoints2 = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>	
<script type="text/javascript">

function onClick(e) {
	alert("Type: " + e.dataSeries.type + ", dataPoint { x:" + e.dataPoint.x + ", y: " + e.dataPoint.y + " }");
};

$(function () {

	var chart = new CanvasJS.Chart("chartContainer",
	{
   		title: {
   			text: "Try Clicking Any DataPoint"
   		},
   		data: [
		{
			click: onClick,
			cursor: "pointer",
       		type: "column",
       		dataPoints: <%out.print(dataPoints1);%>
		},
		{
			click: onClick,
			cursor: "pointer",
			type: "line",
			dataPoints: <%out.print(dataPoints2);%>
		}]
	});

	chart.render();
});
</script>