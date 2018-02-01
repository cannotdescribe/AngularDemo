<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>测试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../js/frame/jquery.min.js"></script>
</head>
<body>
<input type="text" id="ii" />
<button id="commit">确定</button>
<hr/>
<button id="btn1">暂停</button>
<button id="btn2">开始</button>
<button id="btn3">停止</button>
</body>
<script>
    var bigStr = "伊尔伊尔伊尔倒萨大啊啊啊啊啊啊啊" +
        "伊尔伊尔伊尔倒萨大啊啊啊啊啊啊啊" +
        "伊尔伊尔伊尔倒萨大啊啊啊啊啊啊啊。";


    var msgBig = new window.SpeechSynthesisUtterance(bigStr);
    window.speechSynthesis.speak(msgBig);


</script>
</html>
