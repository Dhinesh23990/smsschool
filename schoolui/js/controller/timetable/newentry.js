'use strict';

angular.module(appCon.appName).controller('newentryController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore =$injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.searchview = true;
$scope.statedet = true;	
$scope.statelist = true;

$scope.clearcitydata = function(){
	/* var clearcitydata = $scope.StateVO; 
	 clearcitydata.stateName ="";  */
 }
 
$scope.clearupdatecitydata = function(){
/*	 var clearcitydata = $scope.UpdateVO; 
		clearcitydata.stateName ="";  */
 }
	
$scope.gatallstatedata = function(){
	
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.state = result.responseVO.stateVOs;
		if($scope.state.length > 10)
			 {
				 $scope.dtOptins = DTOptionsBuilder.newOptions()
				  .withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withPaginationType("full_numbers");
		}else{
			$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
		}
	});
	
	/**/
		
	}
	


                                     
	

}]);
