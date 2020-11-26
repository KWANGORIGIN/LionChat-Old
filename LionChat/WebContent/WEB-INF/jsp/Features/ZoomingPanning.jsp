<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<jsp:scriptlet><![CDATA[

Gson gsonObj = new Gson();
String dataPoints = "";

Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
int xVal = 0,yVal = 20;

for(int i = 0; i <= 1000; i++){
	yVal = (int) (yVal +  Math.round(5 + Math.random() *(-5-5)));
	map = new HashMap<Object,Object>();	map.put("x", xVal++); map.put("y",yVal); list.add(map);
}
dataPoints = gsonObj.toJson(list);
]]></jsp:scriptlet>

<div id='chartContainer'></div>
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           theme: "light2",
           zoomEnabled: true,
           animationEnabled: true,
           title: {
           	text: "Try Zooming & Panning"
           },
           subtitles:[
               {
               	text: "Line Chart with 1000 Data Points"
               }
           ],
           data: [
           {
               type: "line",

               dataPoints: <%out.print(dataPoints);%>
           }
           ]
       });
       chart.render();
   });
</script>