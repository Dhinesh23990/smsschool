'use strict';

angular.module(appCon.appName).factory('authService', ['$injector', '$q', '$state', '$modal', '$timeout', 'aclService', '$rootScope', '$location', '$interval', function ($injector, $q, $state, $modal, $timeout, aclService, $rootScope, $location, $interval) {

	var _removeUserData = function () {
		sessionStorage.clear();
		window.localStorage.removeItem('userPermission_'+appContextPath);
		appCon.cookie.removeItem('userProfile');
		//appCon.cookie.removeItem('userInfo');
		appCon.cookie.removeItem('dashboard');
		appCon.cookie.setItem('userProfile', false);
		
		var $injector = angular.injector(['ng']),
			$rootScope = $injector.get('$rootScope'),
			$interval = $injector.get('$interval');
		$interval.cancel($rootScope.Timer);
		if(appCon.globalCon.sessionExpiration && appCon.globalCon.sessionExpiration.sessionTimeOutValue){
			$rootScope.initSessionTimeoutInterval=appCon.globalCon.sessionExpiration.sessionTimeOutValue;
		}			
		$rootScope.userProfile = false;		
		service.removePermissions();
		service.setUser(null);
	};
	
	/** @property roles {Array} */
	var service = {

		// Current user cached info
		currentUser: {},
		
		// Check if current user is logged in
		isAuthenticated : function () {
			return !!(service.currentUser && service.currentUser.loggedIn);
		},

		/**
		 * Get current user via request to the server.
		 */
		requestCurrentUser: function (forceReload) {
			forceReload = forceReload || false;
			if (!forceReload && service.isAuthenticated()) {
				return $q.when(service.currentUser);
			}
			else {
				/**get user status from proxy layer*/
				return $injector.get('users').getLoginStatus().then(function (response) {
					if(response.data && response.data.status === 'success'){
						return service.setUser(response.data.successData.userProfile);
					}
				});
			}
		},

		setUser: function (data) {
			//clear all object properties
			angular.forEach(service.currentUser, function (value, key) {
				delete service.currentUser[key];
			});
			$rootScope.userProfile = data;
			appCon.cookie.setItem('userProfile', JSON.stringify(data));
			return;
		},

		setPermissions: function () {
			return $injector.get('users').getPermissions().then(function (response) {
				if(response.data.status==='success'){
					localStorage.setItem('userPermission_'+appContextPath, JSON.stringify(response.data.successData.permissions));
					aclService.setPermissions(response.data.successData.permissions);
				}
			});
		},
		
		removePermissions: function () {
			aclService.removePermissions();
			return;
		},
		
		checkUserStatus: function () {
			if (localStorage.getItem('userProfile')) {
				return $q.when(true);
			}
			return $q.when(false);
		},
		
		logout: function (unauthorizedHook, nextTarget, loggedOutErrorMsg) {			
			$interval.cancel($rootScope.Timer);	
			nextTarget = nextTarget || '/'+appCon.globalCon.authentication.page;
			_removeUserData();
			if (unauthorizedHook) {
				service.popup = null;
				$location.path(nextTarget);
			} else {
				$injector.get('users').logout().then(function () {
					$location.path(nextTarget);
					if(loggedOutErrorMsg){
						$rootScope.loggedOutMsg = loggedOutErrorMsg;
					}else{
						$rootScope.loggedOutMsg = 'LOGGED_OUT';
					}
				});
			}
		}
	};
	return service;
}]);