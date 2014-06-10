function Drawer() {

	this.context = null;
	this.caseWidth = 20;
	this.caseHeight = 20;
	this.width = 0;
	this.height = 0;
}

/**
 * @param {
 * 	    size : { width, height },
 * 	    map: [ {
 * 	        type, x, y
 * 	    }, ... ]
 * }
 */
Drawer.prototype.refresh = function(data){
	if(!this.context) this._initContext(data.size);
	
	this._clearEnv();
	$.each(data.map, (function(i, elmt){
		this._drawCase(elmt);
	}).bind(this));
	
};

Drawer.prototype._clearEnv = function(){
	this.context.clearRect(0,0, this.width, this.height);
};

Drawer.prototype._drawCase = function(element){

    var color = "#ffffff";
    switch(element.type){
        case "WALL": color = '#000000'; break;
        case "AGENT": color = '#ff00ff'; break;
        case "AGENT_HOLDING": color = "#550055"; break;
        case "DEFAULT": color = '#eeeeee'; break;
        case "BOX": color = '#0000aa'; break;
        case "STOCK": color = '#00aa00'; break;
        default: color = '#ffffff'; break;
    }

	this.context.fillStyle = color;
	this.context.fillRect(
		element.x * this.caseWidth,
		element.y * this.caseHeight,
		this.caseWidth,
		this.caseHeight
	);

    if(element.type == "BOX"){
        this.context.strokeStyle = "#000000";
        this.context.lineWidth = 2;
        this.context.strokeRect(
            element.x * this.caseWidth,
            element.y * this.caseHeight,
            this.caseWidth,
            this.caseHeight
        );
    }

    if(element.type == "AGENT_HOLDING"){
        this.context.strokeStyle = "#000000";
        this.context.lineWidth = 2;
        this.context.strokeRect(
            element.x * this.caseWidth + 2,
            element.y * this.caseHeight + 2,
            this.caseWidth - 4,
            this.caseHeight - 4
        );
    }
};

Drawer.prototype._initContext = function(size){
	
	this.width = size.width * this.caseWidth;
	this.height = size.height * this.caseHeight;
	
	var canvas = document.getElementById('map');
	canvas.width = this.width;
	canvas.height = this.height;
	canvas.style.border = "1px solid black";
	this.context = canvas.getContext('2d');
	
};