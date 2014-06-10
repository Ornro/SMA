function ControlsCtrl($scope, $http){
	
	var urlBase = 'http://localhost:8080/engine?';
	var urlRead = urlBase + 'currentSet';
	var urlPlay = urlBase + 'play';
	var urlPause = urlBase + 'pause';
	var urlChangeDelay = urlBase + 'delay';
	var urlChangeMode = urlBase + 'mode';
	var urlNextStep = urlBase + 'step';

	$scope.currentState = {
		state : null,
		delay: null,
		mode: null
	};
	
	$scope.reload = function(){
		$http.get(urlRead).success(function(data){
			$scope.currentState = data;
		});
	};
	
	$scope.toggleState = function(){
		if($scope.currentState.state == 'pause'){
			
			$http.get(urlPlay).success(function(){
				$scope.reload();
			});
		} else if($scope.currentState.state == 'play'){
			$http.get(urlPause).success(function(){
				$scope.reload();
			});
		}
	};
	
	$scope.changeDelay = function(){
		$http.get(urlChangeDelay+'='+$scope.currentState.delay).success(function(){
			$scope.reload();
		});
	};
	
	$scope.modeHasChanged = function(){
		$http.get(urlChangeMode+'='+$scope.currentState.mode).success(function(){
			$scope.reload();
		});
	};
	
	$scope.nextStep = function(){
		$http.get(urlNextStep).success(function(){
			$scope.reload();
		});
	};
	
	$scope.reload();
	
	
}
