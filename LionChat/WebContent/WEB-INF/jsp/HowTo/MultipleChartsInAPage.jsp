<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("x", 0);  map.put("y", 101.3); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 152);  map.put("y", 99.49); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 457);  map.put("y", 95.91); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 762);  map.put("y", 92.46); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 914);  map.put("y", 90.81); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1067);  map.put("y", 89.15); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2743);  map.put("y", 72.4); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 3048);  map.put("y", 69.64); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 4572);  map.put("y", 57.16); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 6096);  map.put("y", 46.61); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 7620);  map.put("y", 37.65); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 9144);  map.put("y", 30.13); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 10668);  map.put("y", 23.93); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 12192);  map.put("y", 18.82); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 18288);  map.put("y", 7.24); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 21336);  map.put("y", 4.49); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 24384);  map.put("y", 2.8); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 27432);  map.put("y", 1.76);list.add(map);	
String dataPoints1 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("y", 72.48);  map.put("legendText", "Google");  map.put("label", "Google"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 10.39);  map.put("legendText", "Bing");  map.put("label", "Bing"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 7.78);  map.put("legendText", "Yahoo!");  map.put("label", "Yahoo!"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 7.14);  map.put("legendText", "Baidu");  map.put("label", "Baidu"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 0.22);  map.put("legendText", "Ask");  map.put("label", "Ask"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 0.15);  map.put("legendText", "AOL");  map.put("label", "AOL"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 1.84);  map.put("legendText", "Others");  map.put("label", "Others");list.add(map);	
String dataPoints2 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("y", 297571);  map.put("label", "Venezuela"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 267017);  map.put("label", "Saudi"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 175200);  map.put("label", "Canada"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 154580);  map.put("label", "Iran"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 116000);  map.put("label", "Russia"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 97800);  map.put("label", "UAE"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 20682);  map.put("label", "US"); list.add(map);
map = new HashMap<Object,Object>(); map.put("y", 20350);  map.put("label", "China");list.add(map);	
String dataPoints3 = gsonObj.toJson(list);

list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("x", 883593000000L);  map.put("y", 1872000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 915129000000L);  map.put("y", 2140000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 946665000000L);  map.put("y", 7289000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 978287400000L);  map.put("y", 4830000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1009823400000L);  map.put("y", 2009000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1041359400000L);  map.put("y", 2840000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1072895400000L);  map.put("y", 2396000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1104517800000L);  map.put("y", 1613000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1136053800000L);  map.put("y", 2821000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1167589800000L);  map.put("y", 2000000); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1199125800000L);  map.put("y", 1397000);list.add(map);	
String dataPoints4 = gsonObj.toJson(list);
%>

<div id='chartContainer1' style='width: 49%; height: 300px;display: inline-block;'></div>
<div id='chartContainer2' style='width: 49%; height: 300px;display: inline-block;'></div>
<br /><br />
<div id='chartContainer3' style='width: 49%; height: 300px;display: inline-block;'></div>
<div id='chartContainer4' style='width: 49%; height: 300px;display: inline-block;'></div>
<script type="text/javascript">

   $(function () {
       var chart1 = new CanvasJS.Chart("chartContainer1",{
           title:{
               text: "Elevation and Atmospheric Pressure"
           },
           axisY :{
               title: "Atmospheric Pressure",
               titleFontSize: 18,
               suffix: " kPa"
           },
           animationEnabled: true,
           axisX :{
               title: "Elevations Above Sea Level",
               suffix: " m"
           },
           data: [
           {
               markerSize: 0,
               toolTipContent: "<span style='\"color: {color};'\"'><strong>Elevation</strong></span> {x} m<br/><span style='\"'color: {color};'\"'><strong>Pressure</strong></span> {y} kPa",
       type: "spline",
       showInLegend: true,
       legendText: "Computed for 15 deg. C at Sea Level",
       dataPoints: <%out.print(dataPoints1);%>
   }

   ]
   });

   chart1.render();

   var chart2 = new CanvasJS.Chart("chartContainer2", {
       title: {
           text: "Desktop Search Engine Market Share, Jul-2016"
       },
       animationEnabled: true,
       legend: {
           verticalAlign: "center",
           horizontalAlign: "left",
           fontSize: 18,
           fontFamily: "Helvetica"
       },
       theme: "light2",
       data: [
       {
           type: "pie",
           indexLabelFontFamily: "Garamond",
           indexLabelFontSize: 20,
           indexLabel: "{label} {y}%",
           startAngle: -20,
           showInLegend: true,
           toolTipContent: "{legendText} {y}%",
           dataPoints: <%out.print(dataPoints2);%>
       }
       ]
   });
   chart2.render();

   var chart3 = new CanvasJS.Chart("chartContainer3",{
       theme: "light2",
       animationEnabled: true,
       title: {
           text: "Top Oil Reserves"
       },
       axisY: {
           title: "Reserves(MMbbl)"
       },
       data: [{
           type: "column",
           showInLegend: true,
           legendMarkerColor: "grey",
           legendText: "MMbbl = one million barrels",
           dataPoints: <%out.print(dataPoints3);%>
       }]
   });
   chart3.render();

   var chart4 = new CanvasJS.Chart("chartContainer4",{
    title:{
        text: "Music Album Sales by Year"
    },
    theme: "light2",
    animationEnabled: true,
    axisY: {
        title: "Units Sold",
        valueFormatString: "#0,,.",
        suffix: " m"
    },
    data: [
       {
           toolTipContent: "{y} units",
           type: "splineArea",
           markerSize: 5,
           xValueType: "dateTime",
           dataPoints: <%out.print(dataPoints4);%>
       }
    ]
});
   chart4.render();

   });
</script>