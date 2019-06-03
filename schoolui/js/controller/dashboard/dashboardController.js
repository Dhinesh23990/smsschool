'use strict';

angular.module(appCon.appName).controller('dashboardcontroller', ['$injector',  '$scope', 'DTOptionsBuilder',
                                                              function($injector, $scope, DTOptionsBuilder) {
	var $http,$state,$cookieStore;
	$http = $injector.get('$http');
	$state = $injector.get('$state');
	$cookieStore = $injector.get('$cookieStore');
	
	if($cookieStore.get('userProfile')){
		angular.element('.appWrap').removeClass('core');
	}
	
}]);