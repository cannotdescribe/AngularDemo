<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script>
//        let ws = new WebSocket("/websocket");
        let ws = new WebSocket("ws://localhost:8080/websocket");
        ws.onopen = function(evt) {
            console.log("open: ", evt);
        };
        ws.onclose = function(evt) {
            console.log("close: ", evt);
        };
        ws.onmessage = function(evt) {
            console.log("message: ", evt);
        };
        ws.onerror = function(evt) {
            console.log("error: ", evt);
        };
    </script>
</head>
<body>

</body>
</html>
