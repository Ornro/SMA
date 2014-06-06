function Drawer() {

	this.context = null;
	this.caseWidth = 10;
	this.caseHeight = 10;
	this.width = 0;
	this.height = 0;
}

Drawer.prototype.clearEnv = function(){
	this.context.clearRect(0,0, this.width, this.height);
};

/**
 * @param {
 * 		env : {
 *			size : {
 *				width,
 *				height
 *			},
 *			map : {
 *				"1" : [
 *					{
 *						location : { x, y }
 *					}
 *				]
 *			}
 *		}
 * }
 */
Drawer.prototype.refresh = function(data){
	if(!this.context) this._initContext(data.env.size);

	var cases = [];
	$.each(data.env.map, function(){
		cases.push(this[0]);
	});
	
	$.each(cases, (function(i, elmt){
		this._drawCase(elmt);
	}).bind(this));
	
};

Drawer.prototype._drawCase = function(element){
	
	this.context.fillStyle = '#009900';
	this.context.fillRect(
		element.location.x * this.caseWidth,
		element.location.y * this.caseHeight,
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