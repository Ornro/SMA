function UI(){
	
	this.timer = null;
	
	this.drawer = new Drawer();
	
	this.url = "http://localhost:8080/display"
} 

UI.prototype.start = function(){
	
	// Loop
	window.setInterval((this._loop.bind(this)), 200);
};

UI.prototype._loop = function(){
	
	$.get(this.url)
	
	.done((function(data){
		this.drawer.refresh(data);
	}).bind(this))
	
	.fail(function(data){
        console.log("Error while loading");
	});
};


