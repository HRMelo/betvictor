var stompClient = null;

function setConnected(connected) {
    $("#connectMonitor").prop("disabled", connected);
    $("#disconnectMonitor").prop("disabled", !connected);
    if (connected) {
        $("#action-monitor").show();
    }
    else {
        $("#action-monitor").hide();
    }
    $("#monitor").html("");
}

function connect() {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/monitor', function (greeting) {
            showMessage(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'username': $("#name").val(), 'text': $("#text").val()}));
}

function showMessage(monitor) {
    $("#monitor").append("<tr><td>" + monitor.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connectMonitor" ).click(function() { connect(); });
    $( "#disconnectMonitor" ).click(function() { disconnect(); });
});