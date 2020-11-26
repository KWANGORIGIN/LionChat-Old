<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("labels", "Apple");	map.put("y",6); list.add(map);	
map = new HashMap<Object,Object>();	map.put("labels", "Mango");	map.put("y",4); list.add(map);	
map = new HashMap<Object,Object>();	map.put("labels", "Orange"); map.put("y",5); list.add(map);	
map = new HashMap<Object,Object>();	map.put("labels", "Banana"); map.put("y",7); list.add(map);	
map = new HashMap<Object,Object>();	map.put("labels", "Pineapple");	map.put("y",4);	list.add(map);	
map = new HashMap<Object,Object>();	map.put("labels", "Pears");	map.put("y",6);	list.add(map);	
map = new HashMap<Object,Object>(); map.put("labels", "Grapes"); map.put("y",7); list.add(map);	
map = new HashMap<Object,Object>();	map.put("labels", "Lychee"); map.put("y",5); list.add(map);	
map = new HashMap<Object,Object>();	map.put("labels", "Jackfruit");	map.put("y",4);list.add(map);	

String dataPoints = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           theme: "light2",
           animationEnabled: true,
           title: {
               text: "Column Chart with Reversed Y Axis"
           },
           axisY: {
               title: "Reversed Axis",
               reversed: true
           },
           data: [
           {
               type: "column",
               dataPoints: <%out.print(dataPoints);%>
           }
           ]
       });
       chart.render();
   });
</script>