'use strict';

angular.module(appCon.appName).controller('leaveperiodController',
					['$injector',  '$scope', 'DTOptionsBuilder', 'MethodService',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.hidetable = true;
$scope.showupdatefrm = false;
$scope.errormsg = false;

$('input[name="birthdate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		maxDate:new Date()
});

$('input[name="todate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		maxDate:new Date()
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

$http({
		url:appCon.globalCon.serviceBaseURL+"leaveMaster/getAllLeaveMasterBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
	}).success(function(result){
		$scope.leavemasters=result.responseVO.leaveMasterVOs;
		
	});

$scope.getAllleaveperiodData = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"leavePeriod/getAllLeavePeriodBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var leaveperioddata= result.responseVO.leavePeriodVOs;
		angular.forEach(leaveperioddata,function(value,key){
			value.startDate =  $filter('date')(value.startDate, "dd-MM-yyyy");
			value.endDate =  $filter('date')(value.endDate, "dd-MM-yyyy");
		});
		$scope.items = leaveperioddata;
		if($scope.items.length > 10){
			$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
		}else{
			$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
		}
		
	});
}
$scope.create = function(){ 

	$scope.errormsg = false;
	var data = $scope.LeavePeriodVO;
	data.startDate =  $filter('date')($scope.startDate, "dd/MM/yyyy");
	data.endDate =  $filter('date')($scope.endDate, "dd/MM/yyyy");
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"LeavePeriodVO":$scope.LeavePeriodVO}};
		
	$injector.get('staffattendanceservice')['saveLeavePeriod']($scope.data).then(        		
		 //Request Success 
		function(result) {
			
			if(result.data.statusFlag == 'Ok')
				$state.go($state.current, {}, {reload: true});
			if(result.data.statusFlag == 'Error')
				$scope.errormsg = true;
		},		
		function(error){
			console.log('failure');
		});
		
	
}
$scope.edit = function(id){
	console.log(id);
    $scope.hidetable= false;
	$scope.showupdatefrm = true;
	$scope.hidesearchdet = false; 
	$http({
		url:appCon.globalCon.serviceBaseURL+"leavePeriod/getLeavePeriodById?leavePeriodId="+id,
		method:'GET'
	}).success(function(result){
		console.log(result.responseVO.LeavePeriodVO);
		var getupdata = result.responseVO.LeavePeriodVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$('#dobbirth').data('daterangepicker').setStartDate($filter('date')(getupdata.startDate, "MM/dd/yyyy"));
		$('#dateOfJoining').data('daterangepicker').setStartDate($filter('date')(getupdata.endDate, "MM/dd/yyyy"));
		
		$scope.LeavePeriodVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
		var updata = $scope.LeavePeriodVO;
		updata.startDate =  $filter('date')($scope.upstartDate, "dd/MM/yyyy");
			updata.endDate =  $filter('date')($scope.upendDate, "dd/MM/yyyy");

	$scope.updatedata = {"SMSRequest":{"LeavePeriodVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('staffattendanceservice')['updateLeavePeriod']($scope.updatedata).then(        		
		 //Request Success 
		function(result) {
			console.log(result);
			if(result.data.statusFlag == 'Ok')
				$state.go($state.current, {}, {reload: true});
			if(result.data.statusFlag == 'Error')
				$scope.errormsg = true;
		},
		function(error){
			console.log('failure');
		});
	}

$scope.backtosearch = function(){

	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	$scope.errormsg = false;
	}


$scope.returnsearch = function(){

	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.errormsg = false;
}
$scope.goclear = function(){
	  $scope.errormsg = false;
	  $scope.createForm.$setPristine();
	  $scope.LeavePeriodVO="";
		}
 
$scope.updatesearch = function(){
	$scope.errormsg = false;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.showupdatefrm = false;
}
$scope.go = function(){
	   $scope.errormsg = false;
		$scope.showupdatefrm = false;	
		$scope.hidesearchdet = true;
		$scope.hidetable = true;
}
	
	$scope.deleteClass = function(deleteclassid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deleteclass.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deleteclassok = function() {
				  deleteClass(deleteclassid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleteClass(deleteid){
	console.log(deleteid);
	$http({
		url:appCon.globalCon.serviceBaseURL+"leavePeriod/deleteLeavePeriod?leavePeriodId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
$scope.finalstep = function(){
	$state.go('configclass');
}


}]);

angular.module(appCon.appName).directive('numericOnlyClass', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^a-zA-Z]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});