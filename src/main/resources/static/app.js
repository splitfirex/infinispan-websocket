var socketClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {
    socketClient = new Worker("webworkers/socketClient.js");
    socketClient.postMessage({type: "connect", cluster: $("#clusteName").val()});

    socketClient.onmessage = function (e) {
        if (e.data.type == "message")
            printMessage(e.data);
        if (e.data.type == "connected") {
            e.data.content.forEach(function (element) {
                printMessage({content: element[Object.keys(element)[0]]});
            });
            setConnected(true);
        }
    }

}

function disconnect() {
    socketClient.postMessage({type: "disconnect"});
    setConnected(false);
}

function sendName() {
    socketClient.postMessage({
        type: "message",
        uuid: $("#uuid").val(), class: $("#class").val(), content: $("#json").val()
    });
}

function printMessage(message) {
    $("#greetings").append("<tr><td>" + (message.status == undefined ? "Updated" : message.status) + " " +
        JSON.stringify(message.content) + "</td></tr>"
    )
    ;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});

