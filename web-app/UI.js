function UI(){
	
	this.timer = null;
	
	this.drawer = new Drawer();
	
	this.url = "http://localhost:5984/sma/currentEnv"
} 

UI.prototype.start = function(){
	
	// Loop
	window.setInterval((this._loop.bind(this)), 1000);
};

/*
UI.prototype._loop = function(){
	
	// Load new state
	
	$.get({
		url: this.url + this.urlCurrentState,
		success: function(data){
		
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
		},
		error: function(){
			alert('Error while loading current state');
		}
	});
	
};*/

UI.prototype._loop = function(){
	
	$.get(this.url, {}, $.noop(), 'jsonp')
	
	.done((function(data){
		this.drawer.refresh(data);	
	}).bind(this))
	
	.fail(function(){
		alert('Error while loading initial environnement\n'+this.url);
	});
};


