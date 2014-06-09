<%--
  Created by IntelliJ IDEA.
  User: nath
  Date: 09/06/14
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> :( S.M.A. :( </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="UI.js"></script>
    <script src="Drawer.js"></script>
    <script src="lib/jquery.js"></script>

    <script src="lib/angularjs.js"></script>
    <script src="ControlsCtrl.js"></script>
    <script>
        window.onload = function(){
            new UI().start();
        };
    </script>
</head>
<body>
<div ng-app ng-controller="ControlsCtrl">
		<span ng-show="currentState.state == 'play'">
			<input type="button" ng-click="toggleState()" value="Pause" />
		</span>
		<span ng-show="currentState.state == 'pause'">
			<input type="button" ng-click="toggleState()" value="Play" />
		</span>

    Mode:
    <label>
        Auto
        <input type="radio" ng-model="currentState.mode" value="auto" ng-change="modeHasChanged()" />
    </label>
    <label>
        Step by step
        <input type="radio" ng-model="currentState.mode" value="step" ng-change="modeHasChanged()" />
    </label>


    <label>
        Delay : <input type="text" size="4" ng-model="currentState.delay" /> ms
    </label>
    <input type="button" ng-click="changeDelay()" value="Ok" />

    <input type="button" value="Next step" ng-click="nextStep()"/>
</div>
<canvas id="map"></canvas>
</body>
</html>