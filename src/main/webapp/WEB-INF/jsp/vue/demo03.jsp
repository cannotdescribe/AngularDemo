<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>这可能是俄罗斯方块</title>
        <style>
            #gameBody{
                border:1px solid #d3d3d3;
            }
        </style>
    </head>
    <body>
        <canvas id="gameBody" height="512px" width="512px"></canvas>
        <button id="reset">重置</button>
    </body>
    <script type="application/javascript">



        var factory = new Factory();
        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==38){
                factory.clickTop();
            }else if(e && e.keyCode==39){
                factory.clickRight();
            }else if(e && e.keyCode==40){
                factory.clickBottom();
            }else if(e && e.keyCode==37){
                factory.clickLeft();
            }
            if(e && (e.keyCode==38 ||e.keyCode==39 || e.keyCode==40||e.keyCode==37)){
                factory.randomProduce();
            }
        }
        var reset = document.getElementById("reset");
        reset.onclick = function(){
            factory.reset();
        }
    </script>
</html>
