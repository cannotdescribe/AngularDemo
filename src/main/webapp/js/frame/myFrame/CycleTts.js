function CycleTts(){
    // 为1表示正在cycle, 为0 表示正在 single
    this.state = -1;
    this.cycle = {};
    this.single = [];
    this.intervalTime = 2000;
    this.cycleMsg = null;
    this.singleMsg = null;
    this.pauseTime = 1000;
    this.__pauseId = null

    this.__timeout__1 = null;
    this.__timeout__2 = null;
    this.__timeout__3 = null;
}
CycleTts.prototype={
    start:function(){
        this.state = 1;
        this.__cycleSpeak();
        this.__singleSpeak();
    },
    setIntervalTime:function(time){
        this.intervalTime = time;
    },
    addCycle:function(key, value){
        this.cycle[key] = value;
    },
    addSingle:function(text){
        this.single.push(text);
    },
    removeCycle:function(key){
        delete this.cycle[key];
    },
    __cycleSpeak:function(){
        if(this.state == -1){
            return ;
        }
        var cycle = this.cycle, _this = this;
        _this.state = 1;
        var val = "";
        for(var key in cycle){
            val += cycle[key];
        }
        _this.cycleMsg = new window.SpeechSynthesisUtterance(val);
        _this.cycleMsg.onend = function(){
            _this.__timeout__1 = window.setTimeout(function(){
                _this.__cycleSpeak();
            }, _this.intervalTime);
        };
        window.speechSynthesis.speak(_this.cycleMsg);
    },
    __singleSpeak: function(){
        if(this.state == -1){
            return ;
        }
        var _this =this;
        var inside = _this.single;
        _this.single = [];
        _this.__timeout__2 = window.setTimeout(function(){
            if(inside.length ==0){
                _this.__singleSpeak();
                return;
            }
            _this.cycleMsg.onend = function(){};
            if(_this.state == 1){
                window.speechSynthesis.cancel();
            }
            _this.state = 0;

            var message = "";
            for(var key in inside){
                message += inside[key];
            }
            _this.__timeout__3 = window.setTimeout(function(){
                var ms = new window.SpeechSynthesisUtterance(message);
                window.clearTimeout(_this.__pauseId);
                ms.onend = function(){
                    _this.__pauseId = window.setTimeout(function(){
                        _this.__cycleSpeak();
                    }, _this.intervalTime);
                };
                window.speechSynthesis.speak(ms);
                _this.__singleSpeak();
            }, 500);

        }, _this.intervalTime);
    },
    pause: function(){
        window.speechSynthesis.pause();
    },
    resume: function(){
        window.speechSynthesis.resume();
    },
    cancel: function(){
        var _this = this;
        this.state = -1;
        window.clearTimeout(_this.__pauseId);
        window.clearTimeout(_this.__timeout__1);
        window.clearTimeout(_this.__timeout__2);
        window.clearTimeout(_this.__timeout__3);
        this.cycleMsg.onend = function(){};
        window.speechSynthesis.cancel();
    }
};