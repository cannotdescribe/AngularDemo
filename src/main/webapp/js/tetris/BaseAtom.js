class BaseAtom{
    constructor(x, y, los){
        this.x = x;
        this.y = y;
        this.los = los;
    }
    draw(ctx){
        var positionX = this.x*this.los;
        var positionY = this.y*this.los;
        ctx.beginPath();
        ctx.rect(positionX, positionY, this.los, this.los);
        ctx.stroke();
    }
    left(){
        this.x = this.x-1;
    }
    right(){
        this.x = this.x+1;
    }
    top(){
        this.y = this.y-1;
    }
    bottom(){
        this.y = this.y+1;
    }
}