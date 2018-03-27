<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>测试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../js/frame/jquery.min.js"></script>
</head>
<body>
<button id="btn1">暂停</button>
<button id="btn2">开始</button>
<button id="btn3">停止</button>
</body>
<script>
    var msg = new window.SpeechSynthesisUtterance(
        '力微任重久神疲，再竭衰庸定不支。\n' +
        '苟利国家生死以，岂因祸福避趋之！\n' +
        '谪居正是君恩厚，养拙刚于戍卒宜。\n' +
        '戏与山妻谈故事，试吟断送老头皮。'
    );
    msg.onend = function(e){
        console.log("--------------end---------------");
    };
    msg.lang = 'zh';
    msg.voice = speechSynthesis.getVoices().filter(function(voice) {
        return voice.name == 'Whisper';
    })[0];
    window.speechSynthesis.speak(msg);

    $("#btn1").click(function(){
        window.speechSynthesis.pause();
    });
    $("#btn2").click(function(){
        window.speechSynthesis.resume()
    });
    $("#btn3").click(function(){
        window.speechSynthesis.cancel();
    });
    //        var pa = {
    //            peo:{
    //                name: "wanghui"
    //            },
    //            age:12
    //        }
    //        var constantize = (obj)=>{
    //            Object.freeze(obj);
    //            Object.keys(obj).forEach((key, i)=>{
    //                if(typeof obj[key] === "object"){
    //                    constantize(obj[key]);
    //                }
    //            })
    //        };
    //        constantize(pa);
    //        pa.age = 32;
</script>
</html>
