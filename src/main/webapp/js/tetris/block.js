class BaseBlock{
    constructor(){
        this.index = 0;
    }
}
class Line extends BaseBlock{
    constructor(){
        super();
        this.store[0]=[[1], [1], [1], [1]];
        this.store[1]=[[1, 1, 1, 1]];
        this.index = parseInt(Math.random()*2);
    }
}
class Diamod extends BaseBlock{
    constructor(){
        super();
        this.store[0]=[[1, 1],[1, 1]];
    }
}
class Two extends BaseBlock{
    constructor(){
        super();
        this.store[0]=[[1, null], [1, 1], [null, 1]];
        this.store[1]=[[null, 1, 1],[1, 1, null]];
        this.index = parseInt(Math.random()*2);
    }
}
class Five extends BaseBlock{
    constructor(){
        super();
        this.store[0]=[[null, 1], [1, 1], [1, null]];
        this.store[1]=[[1, 1, null], [null, 1, 1]];
        this.index = parseInt(Math.random()*2);
    }
}
class Lo extends BaseBlock{
    constructor(){
        super();
        this.store[0]=[[1, 1, 1], [null, null, 1]];
        this.store[1]=[[1, 1], [1, null], [1, null]];
        this.store[2]=[[1, null, null], [1, 1, 1]];
        this.store[3]=[[null, 1], [null, 1], [1, 1]];
        this.index = parseInt(Math.random()*4);
    }
}
class Jo extends BaseBlock{
    constructor(){
        super();
        this.store[0]=[[null, null, 1], [1, 1, 1]];
        this.store[1]=[[1, null], [1, null], [1, 1]];
        this.store[2]=[[1, 1, 1], [1, null, null]];
        this.store[3]=[[1, 1], [null, 1], [null, 1]];
        this.index = parseInt(Math.random()*4);
    }
}
class To extends BaseBlock{
    constructor(){
        super();
        this.store[0]=[[null, 1], [1, 1], [null, 1]];
        this.store[1]=[[1, 1, 1], [null, 1, null]];
        this.store[2]=[[1, null], [1, 1], [1, null]];
        this.store[3]=[[null, 1, null], [1, 1, 1]];
        this.index = parseInt(Math.random()*4);
    }
}


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