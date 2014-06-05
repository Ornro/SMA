function UI(){
	
	this.timer = null;
	
	this.drawer = new Drawer();
} 

UI.prototype.start = function(){
	
	// 1. Load initial environnement
	this._loadInitialState();
	
	
	// 2. Loop 
	this.timer = window.setInterval((this._loop.bind(this)), 1000);
	
};

UI.prototype._loop = function(){
	
	// Load new state
	
	this.drawer.clearEnv();
	
	var max = Math.floor(Math.random() * 20);
	for(var i=0; i<max; i++){
		var element = {
			color: '#ff0000',
			positionX: Math.floor(Math.random() * 30),
			positionY: Math.floor(Math.random() * 10)
		};
		this.drawer.drawCase(element);
	};
	
};

UI.prototype._loadInitialState = function(){
	
	// Here load json in ajax
	
	// STUB
	var element = {
		width: 300,
		height: 100,
		caseWidth: 10,
		caseHeight: 10
	};
	
	this.drawer.drawMap(element);
	
};


