<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("x", 1910);  map.put("y", 5); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1920);  map.put("y", 9); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1930);  map.put("y", 17); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1940);  map.put("y", 32); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1950);  map.put("y", 22); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1960);  map.put("y", 14); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1970);  map.put("y", 25); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1980);  map.put("y", 18); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1990);  map.put("y", 20);list.add(map);	

String dataPoints = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           theme: "light2",
           animationEnabled: true,
           title: {
               text: "Stripline on  Axis X"
           },
           axisX: {
               stripLines: [
               {
                   value: 1940,
                   label: "1940",
                   showOnTop: true,
                   labelPlacement: "outside"
               }
               ],
               valueFormatString: "####"
           },
           data: [
           {
               type: "splineArea",
               xValueFormatString: "####",
               dataPoints: <%out.print(dataPoints);%>
           }
           ]
       });
       chart.render();
   });
</script>