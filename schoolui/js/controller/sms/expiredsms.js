'use strict';

angular.module(appCon.appName).controller('expiredsmscontroller',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$filter = $injector.get('$filter');
$cookieStore = $injector.get('$cookieStore');
$scope.show = false;

$scope.ComposeSMSVO = {distributionType:"checkdata"};

	$http ({
		url:appCon.globalCon.serviceBaseURL+"school/getSchoolById?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
		}).success(function(result){
			var wholedata = result.responseVO.SchoolVO;
			 wholedata.smsStartDate =  $filter('date')(wholedata.smsStartDate, "dd-MM-yyyy"); 
			 wholedata.smsEndDate =  $filter('date')(wholedata.smsEndDate, "dd-MM-yyyy");
			 $scope.schooldata =wholedata;
			$scope.balancecount = wholedata.smsTotalCount - wholedata.smsBalanceCount ;
	
		});

$scope.gotodashboard = function()
{		
	angular.element("#slimScrollDiv").find('ul li:first').remove('active');
	angular.element("#slimScrollDiv").find('ul li:first').addClass('active');
	angular.element("#slimScrollDiv").find('ul').parent('li').removeClass('active');
	angular.element("#slimScrollDiv").find('ul li').removeClass('open');
	//angular.element("#ddremove").style.display = 'none';
	var yourUl = document.getElementById("ddremove");
	yourUl.style.display = yourUl.style.display === 'none' ? '' : 'none';
	$state.go('dashboard');		
}

}]);



