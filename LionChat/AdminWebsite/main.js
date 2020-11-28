function getIntent(){

    var url = "http://localhost:8000";
    var endpoint = "/intent";

    var http = new XMLHttpRequest();

    var payloadObj ={"utterance": document.getElementById("userInput").value};
    var payloadJSON =JSON.stringify(payloadObj);

    http.open("POST", url+endpoint,true);
    http.setRequestHeader("Content-type", "application/json");

    http.onreadystatechange = function(){
        var DONE = 4;
        var OK = 200;
        if(http.readyState == DONE && http.status == OK && http.responseText){

            //JSON string
            var response = http.responseText;

            var responseObj = JSON.parse(response);

            document.getElementById("intent").innerHTML = responseObj["intent"];
        }
    };

    //Send request
    http.send(payloadJSON);
}