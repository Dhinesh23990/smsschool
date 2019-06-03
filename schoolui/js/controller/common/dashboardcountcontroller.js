'use strict';

angular.module(appCon.appName).controller('dashboardcountcontroller',
					['$injector',  '$scope',
        function($injector, $scope) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$filter = $injector.get('$filter');
$cookieStore = $injector.get('$cookieStore');

$scope.getAlldashboardcount = function(){
$http({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllDashboardCount?schoolId="+$cookieStore.get('schoolId'),
		method:'GET'
	}).success(function(result){
		
		var count_det = result.responseVO;
		$scope.classandsectioncountdet = result.responseVO.genderClassAndSectionCount;
		$scope.schooldata = result.responseVO.SchoolVO;
		$scope.smspurchasedate = $filter('date')(result.responseVO.SchoolVO.smsStartDate, "dd/MM/yyyy");
		$scope.smsenddate = $filter('date')(result.responseVO.SchoolVO.smsEndDate, "dd/MM/yyyy");
		if(count_det.totalBoysCount > 0){
			$scope.boyspersentper = $filter('number')((count_det.BoysPresentStudents/count_det.totalBoysCount)*100, 0);
			$scope.boysabsentper =  $filter('number')((count_det.BoysAbsentStudents/count_det.totalBoysCount)*100, 0);
		}else{
			$scope.boyspersentper = 0;
			$scope.boysabsentper = 0;
		}
		if(count_det.totalgirlsCount > 0){
			$scope.girlspersentper = $filter('number')((count_det.GirlsPresentStudents/count_det.totalgirlsCount)*100, 0);
			$scope.girlsabsentper = $filter('number')((count_det.GirlsAbsentStudents/count_det.totalgirlsCount)*100, 0);
		}else{
			$scope.girlspersentper = 0;
			$scope.girlsabsentper = 0;
		}
		$scope.countdata = count_det;
	});
}
$scope.getAllAdmindashboardcount = function(){

	$http({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllSuperAdminDashboardCount",
		method:'GET'
	}).success(function(result){
		$scope.allschooldata = result.responseVO;
	});
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"dashboard/getAllSMSCount",
		method:'GET'
	}).success(function(result){
		var allsmsdata = result.responseVO.SmsVO;

		angular.forEach(allsmsdata,function(value,key){
			value.smsStartDate = $filter('date')(value.smsStartDate, "dd/MM/yyyy");
			value.smsEndDate = $filter('date')(value.smsEndDate, "dd/MM/yyyy");
			//setsmsdata.push(value);
			$scope.allsmsdata = value;
		});
		
	});
	
} 

$scope.gotodashboard = function()
{		
	angular.element("#slimScrollDiv").find('ul li:first').remove('active');
	angular.element("#slimScrollDiv").find('ul li:first').addClass('active');
	angular.element("#slimScrollDiv").find('ul').parent('li').removeClass('active');
	$state.go('dashboard');		
}	


  

}]);



