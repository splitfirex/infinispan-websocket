'use strict';
importScripts('/webjars/sockjs-client/sockjs.min.js', '../libs/stomp.js');
var stompClient = null;

onmessage = function (oEvent) {
    if (oEvent.data.type == "connect")
        connect(oEvent.data.cluster);

    if (oEvent.data.type == "message")
        sendName(oEvent.data.uuid, oEvent.data.class, oEvent.data.content);

    if (oEvent.data.type == "disconnect")
        disconnect();
};

function connect(clustername) {
    var socket = new SockJS('/websocket-test');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/listeners/receive/updates', function (greeting) {
            postMessage({
                type: "message", content: JSON.parse(greeting.body), status: "Updated"
            })
            ;
        });
        stompClient.subscribe('/listeners/receive/creates', function (greeting) {
            postMessage({
                type: "message", content: JSON.parse(greeting.body), status: "Created"
            })
            ;
        });
        stompClient.subscribe('/listeners/connected', function (greeting) {
            postMessage({
                type: "connected", content: JSON.parse(greeting.body), status: "created"
            })
            ;
        });
        stompClient.send("/app/connect", {}, JSON.stringify({
            'uuid': "CLUSTER_NAME",
            'cClass': null,
            'content': clustername
        }));
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendName(uuid, aClass, content) {
    stompClient.send("/app/send", {},
        JSON.stringify({
            'uuid': uuid,
            'cClass': aClass,
            'content': content
        }));
    //JSON.stringify({'name': $("#name").val()}));
}