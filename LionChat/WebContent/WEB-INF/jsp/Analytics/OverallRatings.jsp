<div id='chartContainer'></div>

<script type="text/javascript">
    $(function () {
        CanvasJS.addColorSet("blues",
            [//colorSet Array
                "#000088",
                "#0e0e5d",
                "#77aaff",
                "#44aaff",
                "#034c80",
                "#25629b",
                "#a6ced9"
            ].map((a) => ({sort: Math.random(), value: a}))
                .sort((a, b) => a.sort - b.sort)
                .map((a) => a.value));
        var chart = new CanvasJS.Chart("chartContainer", {
            colorSet: [
                "blues"
            ],
            title: {
                text: "Average Ratings"
            },
            animationEnabled: true,
            legend: {
                verticalAlign: "center",
                horizontalAlign: "left",
                fontSize: 20,
                fontFamily: "Helvetica",
                reversed: true
            },
            theme: "light2",
            data: [
                {
                    type: "pie",
                    indexLabelFontFamily: "Garamond",
                    indexLabelFontSize: 20,
                    indexLabel: "{label}\u2B50 (#percent%)",
                    startAngle: -20,
                    showInLegend: true,
                    toolTipContent: "{rating}\u2B50 (#percent%)",
                    legendText: "{rating} \u2B50",
                    percentFormatString: "#0.##",
                    dataPoints: ${dataPoints}
           }
           ]
       });
       chart.render();
   });
</script>