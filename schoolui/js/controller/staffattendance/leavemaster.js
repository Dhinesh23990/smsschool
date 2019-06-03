'use strict';

angular.module(appCon.appName).controller('leavemasterController',
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

$scope.carryNextCycies = [{value:"Yes",key:"true"},{value:"No",key:"false"}]

$scope.getAllleaveData = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"leaveMaster/getAllLeaveMasterBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		
		var leavedata=result.responseVO.leaveMasterVOs;
		/*angular.forEach(leavedata,function(value,key){
			value.leaveType =  $filter('date')(value.leaveType, "dd-MM-yyyy");
			value.leaveFreq =  $filter('date')(value.leaveFreq, "dd-MM-yyyy");
			value.dateOfJoining =  $filter('date')(value.dateOfJoining, "dd-MM-yyyy");*/
		$scope.items = leavedata;
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
	var data = $scope.LeaveMasterVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"LeaveMasterVO":$scope.LeaveMasterVO}};
		
	$injector.get('staffattendanceservice')['saveLeaveMaster']($scope.data).then(        		
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
		url:appCon.globalCon.serviceBaseURL+"leaveMaster/getLeaveMasterById?leaveMasterId="+id,
		method:'GET'
	}).success(function(result){
		//console.log(result.responseVO);
		var getupdata = result.responseVO.LeaveMasterVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		console.log(getupdata.carryNextCycle);
		$scope.LeaveMasterVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.LeaveMasterVO;
	$scope.updatedata = {"SMSRequest":{"LeaveMasterVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('staffattendanceservice')['updateLeaveMaster']($scope.updatedata).then(        		
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
	  $scope.LeaveMasterVO="";
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
		url:appCon.globalCon.serviceBaseURL+"leaveMaster/deleteLeaveMaster?leaveMasterId="+deleteid,
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