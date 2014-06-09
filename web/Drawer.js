function Drawer() {

	this.context = null;
	this.caseWidth = 10;
	this.caseHeight = 10;
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
        case "DEFAULT": color = '#eeeeee'; break;
        default: color = '#ff0000'; break;
    }

	this.context.fillStyle = color;
	this.context.fillRect(
		element.x * this.caseWidth,
		element.y * this.caseHeight,
		this.caseWidth,
		this.caseHeight
	);
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