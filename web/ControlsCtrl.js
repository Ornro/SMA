function ControlsCtrl($scope, $http){
	
	var urlBase = 'http://localhost:7070/';
	var urlRead = urlBase + '';
	var urlPlay = urlBase + '';
	var urlPause = urlBase + '';
	var urlChangeDelay = urlBase + '';
	var urlChangeMode = urlBase + '';
	var urlNextStep = urlBase + '';

	$scope.currentState = {
		state : null,
		delay: null,
		mode: null
	};
	
	function reload(){
		$http.get(urlRead).success(function(data){
			$scope.currentState = data;
		});
	};
	
	$scope.toggleState = function(){
		if($scope.currentState.mode == 'pause'){
			
			$http.get(urlPlay).success(function(){
				$scope.reload();
			});
		} else if($scope.currentState.mode == 'play'){	
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
	
	reload();
	
	
}
