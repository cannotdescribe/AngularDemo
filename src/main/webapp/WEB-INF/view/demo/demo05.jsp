<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>测试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../js/frame/jquery.min.js"></script>
    <script src="../js/frame/myFrame/ttsInit.js"></script>
</head>
<body>
    <input id="text_key_1" type="text"/>
    <input id="text_val_1" type="text"/>
    <br/>
    <input id="text_key_2" type="text"/>
    <input id="text_val_2" type="text"/>
    <button id="commit">确定</button>

    <br/>
    <input id="r_text_key" type="text"/>
    <button id="r_commit">确定</button>
<hr/>
<%--<button id="btn1">暂停</button>--%>
<button id="start">开始</button>

<button id="cancel">停止</button>
</body>
<script>
    var alarmTts = new CycleTts();

    $("#start").click(function(){
        alarmTts.start();
    });
    function isEmpty(v){
        return v==null || v=="";
    }
    $("#commit").click(function(){
        var k1 = $("#text_key_1").val();
        var v1 = $("#text_val_1").val();
        var k2 = $("#text_key_2").val();
        var v2 = $("#text_val_2").val();
        if(!isEmpty(k1) && !isEmpty(v1)){
            alarmTts.addCycle(k1, v1);
        }

        if(!isEmpty(k2) && !isEmpty(v2)){
            alarmTts.addCycle(k2, v2);
        }
    });
    $("#r_commit").click(function(){
        alarmTts.removeCycle($("#r_text_key").val());
    });

    $("#cancel").click(function(){
        alarmTts.cancel();
    });

    window.onbeforeunload=function(e){
        alarmTts.cancel();
    }
</script>
</html>
