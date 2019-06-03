'use strict';

angular.module(appCon.appName).controller('highlightMenu', ['$scope', '$location',
                                                              function($scope,$location) {
	
	$scope.getClass = function (path) {
		  return ($location.path().substr(0, path.length) === path) ? 'active' : '';
		}

}]);


angular.module(appCon.appName).controller('forgotPassword', ['$scope', '$location',
                                                            function($scope,$location) {
	
	
}]);

