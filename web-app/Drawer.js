function Drawer() {

	this.context = null;
	this.caseWidth = 0;
	this.caseHeight = 0;
	this.width = 0;
	this.height = 0;
}

/**
 * @param {
 * 		width,
 * 		height,
 * 		caseWidth,
 * 		caseHeight
 * }
 */
Drawer.prototype.drawMap = function(element){
	
	this.width = element.width;
	this.height = element.height;
	
	var canvas = document.getElementById('map');
	canvas.width = this.width;
	canvas.height = this.height;
	canvas.style.border = "1px solid black";
	this.context = canvas.getContext('2d');
	
	this.caseWidth = element.caseWidth;
	this.caseHeight = element.caseHeight;
	
};

Drawer.prototype.clearEnv = function(){
	this.context.clearRect(0,0, this.width, this.height);
};

/**
 * @param {
 * 		color,
 * 		positionX,
 * 		positionY
 * }
 */
Drawer.prototype.drawCase = function(element){
	
	this.context.fillStyle = element.color;
	this.context.fillRect(
		element.positionX * this.caseWidth,
		element.positionY * this.caseHeight,
		this.caseWidth,
		this.caseHeight
	);
};
