<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>那js写的1024</title>
        <style>
            #gameBody{
                border:1px solid #d3d3d3;
            }
        </style>
    </head>
    <body>
        <canvas id="gameBody" height="512px" width="512px"></canvas>
        <button id="reset">重置</button>
        <p>老规矩，这么几行代码没必要加什么注解</p>
    </body>
    <script type="application/javascript">

        function CgBlock(x, y, los){
            this.x = x;
            this.y = y;
            this.los = los;
            this.value = 2;
        };
        CgBlock.prototype={
            draw: function(ctx){
                var fontSize = 40;
                var value = this.value+"";
                var positionX = this.x*this.los;
                var positionY = this.y*this.los;
                ctx.font=fontSize+"px Georgia";
                ctx.fillText(value, positionX+this.los/2-(fontSize*value.length/4), positionY+this.los/2+fontSize/4);
                ctx.beginPath();
                ctx.rect(positionX, positionY, this.los, this.los);
                ctx.stroke();
            },
            left:function(){
                this.x = this.x-1;
            },
            right:function(){
                this.x = this.x+1;
            },
            top:function(){
                this.y = this.y-1;
            },
            bottom:function(){
                this.y = this.y+1;
            }
        };

        function Factory(gameBody){
            this.body = gameBody;
            this.block = document.getElementById("gameBody");
            this.ctx = this.block.getContext("2d");
            this.ctx.lineWidth = 1;
            this.reset();
        };
        Factory.prototype={
            reset:function(){
                this.cgBlocks = [[],[],[],[]];
                this.randomProduce();
                this.randomProduce();
                this.draw();
            },
            draw:function(){
                var _this = this;
                this.ctx.clearRect(0,0, 512, 512);
                for(var i=0; i<4; i++){
                    for(var j=0; j<4; j++){
                        if(this.cgBlocks[i][j]!= null || this.cgBlocks[i][j]!= undefined){
                            this.cgBlocks[i][j].draw(_this.ctx);
                        }
                    }
                }
            },
            randomPosition: function(v){
                var randomStore = [];
                for(var i=0; i<4; i++){
                    for(var j=0; j<4; j++){
                        if(this.cgBlocks[i][j]== null || this.cgBlocks[i][j]== undefined){
                            randomStore.push({x:i, y:j});
                        }
                    }
                }
                if(randomStore == 0){
                    return false;
                }else{
                    var index= parseInt(Math.random()*randomStore.length);
                    return randomStore[index];
                }
            },
            createBlocks: function(position,v){
                this.cgBlocks[position.x][position.y] = new CgBlock(position.x, position.y, 128);
                if(v != null || v != undefined){
                    this.cgBlocks[position.x][position.y].value = v;
                }
                this.draw();
            },
            randomProduce:function(v){
                var pos = this.randomPosition(v);
                if(pos == false){
                    alert("游戏结束.");
                }else{
                    this.createBlocks(pos, v);
                }
            },
            getLeft: function(node){
                if(this.cgBlocks[node.x-1]!= null && this.cgBlocks[node.x-1]!=undefined){
                    return this.cgBlocks[node.x-1][node.y];
                }else{
                    return true;
                }
            },
            getRight: function(node){
                if(this.cgBlocks[node.x+1]!= null && this.cgBlocks[node.x+1]!=undefined){
                    return this.cgBlocks[node.x+1][node.y];
                }else{
                    return true;
                }
            },
            getTop: function(node){
                if(this.cgBlocks[node.y-1]!= null && this.cgBlocks[node.y-1]!=undefined){
                    return this.cgBlocks[node.x][node.y-1];
                }else{
                    return true;
                }
            },
            getBottom: function(node){
                if(this.cgBlocks[node.y+1]!= null && this.cgBlocks[node.y+1]!=undefined){
                    return this.cgBlocks[node.x][node.y+1];
                }else{
                    return true;
                }
                return this.cgBlocks[node.x][node.y+1];
            },
            canLeft: function(node){
                var n = this.getLeft(node);
                return n==null || n==undefined;
            },
            canRight: function(node){
                var n = this.getRight(node);
                return n==null || n==undefined;
            },
            canTop: function(node){
                var n = this.getTop(node);
                return n==null || n==undefined;
            },
            canBottom: function(node){
                var n = this.getBottom(node);
                return n==null || n==undefined;
            },
            clear: function(node){
                this.cgBlocks[node.x][node.y] = null;
            },
            left: function(node){
                this.cgBlocks[node.x][node.y] = null;
                node.left();
                this.cgBlocks[node.x][node.y] = node;
            },
            right: function(node){
                this.cgBlocks[node.x][node.y] = null;
                node.right();
                this.cgBlocks[node.x][node.y] = node;
            },
            top: function(node){
                this.cgBlocks[node.x][node.y] = null;
                node.top();
                this.cgBlocks[node.x][node.y] = node;
            },
            bottom: function(node){
                this.cgBlocks[node.x][node.y] = null;
                node.bottom();
                this.cgBlocks[node.x][node.y] = node;
            },
            clickTop:function(){
                for(var i=0; i<4; i++){
                    for(var j=0; j<4; j++){
                        var thisNode = this.cgBlocks[i][j];
                        if(thisNode!= null || thisNode!= undefined){
                            while (this.canTop(thisNode)) {
                                this.top(thisNode)
                            }
                            var top = this.getTop(thisNode);
                            if(top.value == thisNode.value){
                                top.value = top.value*2;
                                this.clear(thisNode);
                            }
                        }
                    }
                };
                this.draw();
            },
            clickRight: function(){
                for(var i=3; i>=0; i--){
                    for(var j=0; j<4; j++){
                        var thisNode = this.cgBlocks[i][j];
                        if(thisNode!= null || thisNode!= undefined){
                            while (this.canRight(thisNode)) {
                                this.right(thisNode)
                            }
                            var right = this.getRight(thisNode);
                            if(right.value == thisNode.value){
                                right.value = right.value*2;
                                this.clear(thisNode);
                            }
                        }
                    }
                };
                this.draw();
            },
            clickBottom: function(){
                for(var i=0; i<4; i++){
                    for(var j=3; j>=0; j--){
                        var thisNode = this.cgBlocks[i][j];
                        if(thisNode!= null || thisNode!= undefined){
                            while (this.canBottom(thisNode)) {
                                this.bottom(thisNode)
                            }
                            var bottom = this.getBottom(thisNode);
                            if(bottom.value == thisNode.value){
                                bottom.value = bottom.value*2;
                                this.clear(thisNode);
                            }
                        }
                    }
                };
                this.draw();
            },
            clickLeft:function(){
                for(var i=0; i<4; i++){
                    for(var j=0; j<4; j++){
                        var thisNode = this.cgBlocks[i][j];
                        if(thisNode!= null || thisNode!= undefined) {
                            while (this.canLeft(thisNode)) {
                                this.left(thisNode)
                            }
                            var left = this.getLeft(thisNode);
                            if(left.value == thisNode.value){
                                left.value = left.value*2;
                                this.clear(thisNode);
                            }
                        }
                    }
                };
                this.draw();
            }
        };

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
