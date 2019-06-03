'use strict';
/**
 *Login directive
 */
angular.module(appCon.appName).directive('login', function() {
	return {
        restrict: 'EA',
        scope: true,
        controller: 'loginController'
    };
});

angular.module(appCon.appName).controller('loginController', ['$injector', '$attrs', '$element', '$scope', '$modal', '$parse', 'ngDialog', 'md5',
  function($injector, $attrs, $element, $scope, $modal, $parse, ngDialog, md5) {
	var $rootScope, $state, $cookieStore, $location, $http, $interval, authService, aclService,$modal; 
    
	/* remember username */
	if(localStorage.getItem('user.userName')){
		$scope.data = {
			'j_username' : localStorage.getItem('user.userName'),
			'rememberUsername' : true
		};
	}
	
    $http = $injector.get('$http');
    $rootScope = $injector.get('$rootScope');
    $state = $injector.get('$state');
	$modal = $injector.get('$modal');
    var $modalStack = $injector.get('$modalStack');
    $modalStack = $modalStack.dismissAll();
    
    $cookieStore = $injector.get('$cookieStore');
    $location = $injector.get('$location');
    $interval = $injector.get('$interval');
    authService = $injector.get('authService');
    aclService = $injector.get('aclService');
   
    $rootScope.afterLoginLoading = false;
	$scope.loading = false;
	var submitData = $element.children('login-submit'),
        formTag = $attrs.formId ? $attrs.formId : ($element.find('form') ? $element.find('form').attr('name') : ''),
        serviceName, operationName, callback, callbackFunc;
	
	/* Submit login request */
    if (submitData.attr('service')) {
    	serviceName = submitData.attr('service');
        operationName = submitData.attr('operation');
    }
    
    $scope[formTag+'Submitted'] = false;
	$scope.wrongusers = false;
	$scope.show_message = false;
	
	if($cookieStore.get('userProfile')){
		angular.element('.appWrap').removeClass('core');
	}
	
	$scope.loginmockuser = function() {
    
		$scope.data={j_username:$scope.j_username,j_password:$scope.j_password};
		//$rootScope.loggedOutMsg = '';
    	
    	/*callback = submitData.attr('callback');
    	$scope[formTag+'Submitted'] = true;
    	if ($scope[formTag].$valid && serviceName && operationName) {
        	$scope.loading=true;
        	$rootScope.afterLoginLoading = true;
        	// Check if encryption mode is enabled from configuration
        	// If yes need to encrypt password
        	/*if (appCon.globalCon.authentication.enablePasswordEncryption && appCon.globalCon.authentication.enablePasswordEncryption.toString() === 'true') {
        		var password = _.get($scope.data, 'j_password'), 
        			//sKey = uid(), 
        			md5Password = MD5(password);
        			//newPassword = md5Password + sKey;
        		
                //newPassword = MD5(newPassword);
                $scope.data.j_password = md5Password; 
                //$scope.data.skey = sKey;
        	}*/
        	
        	/* API call */
          //  $injector.get(serviceName)[operationName]($scope.data).then(        		
            	
            	/* Request Success */
            //	function(result) {
            		//console.log(result);
            		//var returnCallback = true;
					/*var result;
				result = {data:{status: "OK", userRole: "ROLE_USER", fullName: "", firstName: "", lastName: "", loginAttempt: "0", oid: "3478b3c9-43cb-4178-9505-3916658b3987", token: "b5fc3f6e-d860-4821-8891-1c8a5d7db478" }};*/
					//result.push(data);
					var md5Password = md5.createHash($scope.j_password);
					console.log(md5Password,"md5Password")
					$http({
						url:appCon.globalCon.serviceLoginBaseURL+"login?userName="+$scope.j_username+"&password="+md5Password+"&roleId=roleSchoolAdmin"+"&authType=BASIC",
						method:'GET'
					}).success(function(result){
						if (result.statusFlag == 'Ok'){
            			$rootScope.userProfile = {};
						angular.element('.appWrap').removeClass('core');
						$scope.wrongusers = false;
            			if(result.statusFlag == 'Ok') {
							appCon.cookie.setItem('loginusertoken', result.responseVO.userDetailsVO.token);
							var data = {status: "OK", userRole: result.responseVO.userDetailsVO.roleName, fullName: "", firstName: "", lastName: "", loginAttempt: "0", oid: result.responseVO.userDetailsVO.id, token: result.responseVO.userDetailsVO.token, contactEmail:result.responseVO.userDetailsVO.contactEmail,customerId:result.responseVO.userDetailsVO.customerId,userName:result.responseVO.userDetailsVO.userName};
							$rootScope.userProfile = data;
							$cookieStore.put('userProfile', data);
							$cookieStore.put('schoolId', result.responseVO.userDetailsVO.schoolId);
							localStorage.setItem("userProfile", JSON.stringify(data));
							localStorage.setItem('loginusertoken', result.responseVO.userDetailsVO.token);
							localStorage.setItem('dashboard', 'SCHOOLADMIN');
							localStorage.setItem('schoolId', result.responseVO.userDetailsVO.schoolId);
							localStorage.setItem('schoolId', result.responseVO.userDetailsVO.schoolId);
							localStorage.setItem('demourl', 'schooldiary');
							//$interval(function(){},500);
            				//$element.hide();	            			
	            			//remember user name
	                    	/*if(angular.isDefined($scope.data.rememberUsername) && $scope.data.rememberUsername == true){
	                    		localStorage.setItem('user.userName', $scope.data.j_username);
	                    	}else{
	                    		localStorage.removeItem('user.userName');
	                    	}*/
	            			
	            			//$cookieStore.put('userInfo', result.data.successData);
	            			
	            			/*if(callback){
		        				callbackFunc = $parse(callback)($scope);
		        				returnCallback = callbackFunc(result, $scope.data);
		        			}*/
	            			/*if(returnCallback === true){
		            			authService.requestCurrentUser(true).then(function(){
		            				authService.setPermissions().then(function(){
		            					$rootScope.canPerform = aclService.canPerform; 
					        			$rootScope.canAccess = aclService.canAccess;
		                    			
		                        		appCon.dashboard = _.get(result.data.successData, appCon.globalCon.dashboards.dashboardKey);
					                    
		                        		 Set default dashboard landing page if undefined 
		                        		if((appCon.dashboard === 'undefined' || appCon.dashboard === null ) && appCon.globalCon.defaultDashboard !== ''){
											appCon.dashboard = appCon.globalCon.defaultDashboard;
										}
										appCon.cookie.setItem('dashboard', appCon.dashboard);
										if(appCon.globalCon.dashboards && appCon.globalCon.dashboards[appCon.dashboard] && appCon.globalCon.dashboards[appCon.dashboard].authTrueUrlLandingPage){
											$location.path('/'+appCon.globalCon.dashboards[appCon.dashboard].authTrueUrlLandingPage);	
										}else{
											$location.path('/'+appCon.globalCon.defaultLandingPage);
										}
					        			
										$rootScope.afterLoginLoading = false;
		            				});
		            			});
	            			}*/
	                    	appCon.dashboard = _.get(data, appCon.globalCon.dashboards.dashboardKey);
	                    	console.log(appCon.dashboard);
	                    	appCon.cookie.setItem('dashboard', appCon.dashboard);
	                    	localStorage.setItem('dashboard', appCon.dashboard);
	                    	$location.path('/dashboard');
							//appCon.cookie.setItem('dashboard', 'dashboard');
                    		/* Set default dashboard landing page if undefined */
                    		/*if((appCon.dashboard === 'undefined' || appCon.dashboard === null ) && appCon.globalCon.defaultDashboard !== ''){
								appCon.dashboard = appCon.globalCon.defaultDashboard;
							}
							appCon.cookie.setItem('dashboard', appCon.dashboard);
							if(appCon.globalCon.dashboards && appCon.globalCon.dashboards[appCon.dashboard] && appCon.globalCon.dashboards[appCon.dashboard].authTrueUrlLandingPage){
								console.log("oh my godddddd");
								console.log(appCon.globalCon.dashboards[appCon.dashboard].authTrueUrlLandingPage);
								$location.path('/'+appCon.globalCon.dashboards[appCon.dashboard].authTrueUrlLandingPage);	
							}else{
								$location.path('/'+appCon.globalCon.defaultLandingPage);
							}*/
	            			/********* Session Timeout ****************/	            			
	            			/*if (appCon.globalCon.sessionExpiration && appCon.globalCon.authentication && appCon.globalCon.authentication.required) {	            				
	        	                //if ($cookieStore.get('userProfile')) {
				                   if(!appCon.globalCon.mock || appCon.globalCon.mock === 'false'){
	        	                		appCon.globalCon.sessionExpiration.sessionTimeOutValue = appCon.globalCon.sessionExpiration.sessionTimeoutInSec - 60;
	        	                		appCon.globalCon.sessionExpiration.sessionBeforeAlertValue = appCon.globalCon.sessionExpiration.sessionTimeOutValue - (appCon.globalCon.sessionExpiration.sessionTimeOutValue - appCon.globalCon.sessionExpiration.alertBeforeSec);
	        	                		var $modalStack = $injector.get('$modalStack');
					                    $rootScope.initSessionTimeoutInterval = appCon.globalCon.sessionExpiration.sessionTimeOutValue;
					                    $rootScope.Timer = $interval(function(){
						        			$rootScope.sessionTimeoutValue = $rootScope.initSessionTimeoutInterval;
						        			if($rootScope.sessionTimeoutValue == 0){
						        				$interval.cancel($rootScope.Timer);
						        				$modalStack.dismissAll();
						        				$rootScope.logout('SESSION_EXPIRED');
				                                $rootScope.initSessionTimeoutInterval = appCon.globalCon.sessionExpiration.sessionTimeOutValue;
						        			}else if($rootScope.sessionTimeoutValue == appCon.globalCon.sessionExpiration.sessionBeforeAlertValue){
							        			 $modal.open({
							 	                    template: appCon.prepareSessionExpireDialogContainer,
							 	                    backdrop: 'static',
							 	                    keyboard: false,
							 	                    controller: function($scope, $modalInstance, $rootScope, $state) {							 	                    	
							 	                    	//$scope.commonConfirmationDialogTitle = 'Session Expiration Alert';							                          
							                            $scope.ok = function() {
							                            	$injector.get(appCon.globalCon.sessionExpiration.service)[appCon.globalCon.sessionExpiration.operation]().then(function(result) {
							                            		$rootScope.initSessionTimeoutInterval = appCon.globalCon.sessionExpiration.sessionTimeOutValue;
								                                $modalInstance.close();
							                                });
							                            	
							                            };
							                            $scope.cancel = function() {
							                            	$interval.cancel($rootScope.Timer);
									        				$modalStack.dismissAll();
							                                $rootScope.logout();
							                                $rootScope.initSessionTimeoutInterval=appCon.globalCon.sessionExpiration.sessionTimeOutValue;
							                            };
							 	                    }
							 	                });
						        			}						        			
						        			$rootScope.initSessionTimeoutInterval--;
						        		},1000);
				                    }
	        	                //}
		                    }*/
							$scope.loading=false;
							} else if (result.status === 'Error' ){
	                		$scope.loginError = result.status;
	                		$scope.loading=false;
	                		var shortMessage = result.data.errorData.ResponseError[0].shortMessage.toLowerCase();
	                		if(shortMessage.indexOf('password expired') >= 0 ){
	                			if(angular.isDefined($scope.data.rememberUsername) && $scope.data.rememberUsername == true){
		                    		localStorage.setItem('user.userName', $scope.data.j_username);
		                    	}else{
		                    		localStorage.removeItem('user.userName');
		                    	}
	                			$rootScope.afterLoginLoading = true;
	                		}else{
	                			$rootScope.afterLoginLoading = false;
	                		}
	                		$scope.data = {};
	                		if(localStorage.getItem('user.userName')){
	                			$scope.data = {
	                				'j_username' : localStorage.getItem('user.userName'),
	                				'rememberUsername' : true
	                			};
	                		}
	                		$scope[formTag+'Submitted'] = false;
	                		$scope[formTag].$setPristine(true);
	                		if(callback){
		        				callbackFunc = $parse(callback)($scope);
		        				return callbackFunc(result, $scope.data);
		        			}
	                	}
            		}
					else{
						if(result.ErrorMessage == 'User Status Inactive'){
							 $scope.$modalInstance = $modal.open({
								 scope: $scope,
								 templateUrl: 'inactiveschool.html',
								 controller: function($scope, $modalInstance, $rootScope, $state) {
									  $scope.activeschoolok = function() {
										  $modalInstance.close();
									  };
								  }
							});
						}else{
							$scope.wrongusers = true;
						}
					}
					
			});
            	//},
                /* Request Failure */
                /*function(error){
            		$scope.loading=false;
                	$scope.data = {};
                	if(localStorage.getItem('user.userName')){
                		$scope.data = {
                			'j_username' : localStorage.getItem('user.userName'),
                			'rememberUsername' : true
                		};
                	}
                	$scope.loginError = error.data;
                	$element.show();
                	$scope[formTag+'Submitted'] = false;
                	$rootScope.afterLoginLoading = false;
                	$scope[formTag].$setPristine(true);
                	if(callback){
        				callbackFunc = $parse(callback)($scope);
	        			return callbackFunc(error, $scope.data);
        			}
                }*/
         //   );
        };
    //};
	
	$scope.forgetpassword = function(){
		$scope.loading = true;
		$scope.show_message = false;
		$http({
			url:appCon.globalCon.serviceLoginBaseURL+"forgetpassword?emailAddress="+$scope.emailAddress+"&roleId=roleSchoolAdmin"+"&authType=BASIC",
			method:'GET'
		}).success(function(result){
			$scope.show_message = true;
			if (result.statusFlag == 'Ok'){
				$scope.loading = false;
				$scope.message = "Successfully sent the Username and Password in your email-id";
			}
			else{
				$scope.loading = false;
				if(result.ErrorMessage == "Invalid Username or Password"){
					$scope.message = "Incorrect your email-id";
				}
				if(result.ErrorMessage == "User Status Inactive"){
					$scope.message = "Inactive your school, So please contact as schoolsdiarycontact@gmail.com";
				}
			}
		});
	}
    
}]);