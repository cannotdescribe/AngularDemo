function Tts(){
};
Tts.prototype={
    speak: function(message, end){
        var msg = new SpeechSynthesisUtterance(message);
        msg.onend = end;
        speechSynthesis.speak(msg);
    },
    stop: function(){
        speechSynthesis.stop();
    },
    pause: function(){
        speechSynthesis.pause();
    },
    resume: function(){
        speechSynthesis.resume();
    },
    getVoices:function(){
        speechSynthesis.getVoices();
    }
};