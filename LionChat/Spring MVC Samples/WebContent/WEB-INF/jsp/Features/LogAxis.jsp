<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();

map = new HashMap<Object,Object>(); map.put("x", 1971);  map.put("y", 2300);  map.put("name", "Intel 4004"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1972);  map.put("y", 3500);  map.put("name", "Intel 8008"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1974);  map.put("y", 4100);  map.put("name", "Motorola 6800"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1974);  map.put("y", 4500);  map.put("name", "Intel 8080"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1974);  map.put("y", 5000);  map.put("name", "RCA 1802"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1975);  map.put("y", 3510);  map.put("name", "MOS Technology 6502"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1976);  map.put("y", 6500);  map.put("name", "Intel 8085"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1976);  map.put("y", 8500);  map.put("name", "Zilog Z80"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1978);  map.put("y", 9000);  map.put("name", "Motorola 6809"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1978);  map.put("y", 29000);  map.put("name", "Intel 8086"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1979);  map.put("y", 29000);  map.put("name", "Intel 8088"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1979);  map.put("y", 68000);  map.put("name", "Motorola 68000"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1982);  map.put("y", 55000);  map.put("name", "Intel 80186"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1982);  map.put("y", 134000);  map.put("name", "Intel 80286"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1985);  map.put("y", 25000);  map.put("name", "ARM 1"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1985);  map.put("y", 275000);  map.put("name", "Intel 80386"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1986);  map.put("y", 25000);  map.put("name", "ARM 2"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1989);  map.put("y", 300000);  map.put("name", "ARM 3"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1989);  map.put("y", 1180235);  map.put("name", "Intel 80486"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1991);  map.put("y", 30000);  map.put("name", "ARM 6"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1991);  map.put("y", 1350000);  map.put("name", "R4000"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1993);  map.put("y", 3100000);  map.put("name", "Pentium"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1994);  map.put("y", 578977);  map.put("name", "ARM 7"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1995);  map.put("y", 5500000);  map.put("name", "Pentium Pro"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1996);  map.put("y", 4300000);  map.put("name", "AMD K5"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1997);  map.put("y", 7500000);  map.put("name", "Pentium II"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1997);  map.put("y", 8800000);  map.put("name", "AMD K6"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1999);  map.put("y", 9500000);  map.put("name", "Pentium III"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1999);  map.put("y", 21300000);  map.put("name", "AMD K6 III"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 1999);  map.put("y", 22000000);  map.put("name", "AMD K7"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2000);  map.put("y", 42000000);  map.put("name", "Pentium 4"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2002);  map.put("y", 105900000);  map.put("name", "AMD K8"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2003);  map.put("y", 54300000);  map.put("name", "Barton"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2003);  map.put("y", 291000000);  map.put("name", "Core 2 Duo"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2003);  map.put("y", 410000000);  map.put("name", "Itanium 2 Madison 6M "); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2004);  map.put("y", 592000000);  map.put("name", "Itanium 2 with 9MB cache "); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2006);  map.put("y", 220000000);  map.put("name", "Itanium 2 McKinley "); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2006);  map.put("y", 241000000);  map.put("name", "Cell"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2006);  map.put("y", 1700000000);  map.put("name", "Dual-Core Itanium 2"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2007);  map.put("y", 26000000);  map.put("name", "ARM Cortex-A9"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2007);  map.put("y", 463000000);  map.put("name", "AMD K10 quad-core 2M L3 "); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2007);  map.put("y", 789000000);  map.put("name", "POWER 6"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2008);  map.put("y", 47000000);  map.put("name", "Atom"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2008);  map.put("y", 731000000);  map.put("name", "Core i7 (Quad)"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2008);  map.put("y", 758000000);  map.put("name", "AMD K10 quad-core 6M L3 "); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2008);  map.put("y", 1900000000);  map.put("name", "Six-Core Xeon 7400"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2009);  map.put("y", 904000000);  map.put("name", "Six-Core Opteron 2400 "); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2010);  map.put("y", 1000000000);  map.put("name", "16-Core SPARC T3"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2010);  map.put("y", 1170000000);  map.put("name", "Six-Core Core i7"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2010);  map.put("y", 1200000000);  map.put("name", "8-core POWER7 32M L3"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2010);  map.put("y", 1400000000);  map.put("name", "Quad-core z196"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2010);  map.put("y", 2000000000);  map.put("name", "Quad-Core Itanium Tukwila"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2010);  map.put("y", 2300000000L);  map.put("name", "8-Core Xeon Nehalem-EX"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2011);  map.put("y", 1160000000);  map.put("name", "Quad-Core + GPU Core i7"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2011);  map.put("y", 2270000000L);  map.put("name", "Six-Core Core i7/8-Core Xeon E5"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2011);  map.put("y", 2600000000L);  map.put("name", "10-Core Xeon Westmere-EX"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2012);  map.put("y", 1200000000);  map.put("name", "8-Core AMD Bulldozer"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2012);  map.put("y", 1303000000);  map.put("name", "Quad-Core + GPU AMD Trinity"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2012);  map.put("y", 1400000000);  map.put("name", "Quad-Core + GPU Core i7"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2012);  map.put("y", 2100000000);  map.put("name", "8-core POWER7+ 80M L3"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2012);  map.put("y", 2750000000L);  map.put("name", "Six-core zEC12"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2012);  map.put("y", 3100000000L);  map.put("name", "8-Core Itanium Poulson"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2012);  map.put("y", 5000000000L);  map.put("name", "62-Core Xeon Phi"); list.add(map);
map = new HashMap<Object,Object>(); map.put("x", 2013);  map.put("y", 5000000000L);  map.put("name", "Xbox One Main SoC"); list.add(map);
String dataPoints = gsonObj.toJson(list);
%>

<div id='chartContainer'></div>
<script type="text/javascript">

   $(function () {
       var chart = new CanvasJS.Chart("chartContainer", {
           zoomEnabled: true,
           zoomType: "xy",
           title: {
               text: "Growth in Microprocessor Transistor Count"
           },
           subtitles: [
               {
                   text: "Try Zooming and Panning"
               }
           ],
           axisX: {
               title: "Year",
               valueFormatString: "####"
           },
           axisY: {
               title: "No of Transistors per Chip",
               logarithmic: true,
               includeZero: false,
           },
           toolTip: {
               content: "{x}<br/><span style='\"'color: {color};'\"'>{name}</span> - {y}"
           },
           data: [{
               type: "line",
               xValueFormatString: "####",
               dataPoints: <%out.print(dataPoints);%>
           }]
       });

       chart.render();
   });
</script>