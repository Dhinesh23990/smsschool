'use strict';

angular.module(appCon.appName).controller('leaveentitlementController',
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

$http({
		url:appCon.globalCon.serviceBaseURL+"teacher/getAllTeacher?schoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
	}).success(function(result){
		$scope.liststaffs = result.responseVO.teacherList;
	});

$http({
		url:appCon.globalCon.serviceBaseURL+"leaveMaster/getAllLeaveMasterBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=100",
		method:'GET'
	}).success(function(result){
		console.log(result);
		$scope.leavemasters = result.responseVO.leaveMasterVOs;
	});
	
$scope.changemasterdata = function(LeaveEntitlementVO){
	console.log(LeaveEntitlementVO);
	if(LeaveEntitlementVO.leaveMasterId && LeaveEntitlementVO.staffId){
		$scope.setquaterdata(LeaveEntitlementVO);
		
	}else{
		$scope.quaterdata = false;
	}
}

$scope.changestaffdata = function(LeaveEntitlementVO){
	if(LeaveEntitlementVO.leaveMasterId && LeaveEntitlementVO.staffId){
		$scope.setquaterdata(LeaveEntitlementVO);
	}else{
		$scope.quaterdata = false;
	}
}

$scope.setquaterdata = function(LeaveEntitlementVO){
	$http({
			url:appCon.globalCon.serviceBaseURL+"leavePeriod/getAllLeavePeriodByMasterId?schoolId="+$cookieStore.get('schoolId')+"&leaveMasterId="+LeaveEntitlementVO.leaveMasterId,
			method:'GET'
		}).success(function(result){
			$scope.selectall = true;
			$scope.fst = true;
			console.log(result);
			$scope.quaterdata = true;
			var leavemasterdatas = result.responseVO.leavePeriodVOs;
			angular.forEach(leavemasterdatas,function(value,key){
				value.startDate = $filter('date')(value.startDate, "dd-MM-yyyy");
				value.endDate = $filter('date')(value.endDate, "dd-MM-yyyy");
				value.checkdata = true;
			})
			$scope.leavemasterdatas = leavemasterdatas;
			console.log($scope.leavemasterdatas);
		});
}
	
$scope.getAllleaveentitleData = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"leaveEntitlement/getAllLeaveEntitlementBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		
		var leavedata=result.responseVO.leaveEntitlementVOs;
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

	console.log($scope.leavemasterdatas);
	console.log($scope.LeaveEntitlementVO);
	$scope.errormsg = false;
	var dataperiod = $scope.leavemasterdatas,periodid,count;
	angular.forEach(dataperiod,function(value,key){
		//if(value.checkdata)
		periodid = value.id;
		count=value.leaveCount; 
	});
	var data = $scope.LeaveEntitlementVO;
	data.schoolId = $cookieStore.get('schoolId');
	data.leavePeriodId = periodid;
	data.leaveCount = count;
	$scope.data = {"SMSRequest":{"LeaveEntitlementVO":$scope.LeaveEntitlementVO}};
		console.log($scope.data);
	$injector.get('staffattendanceservice')['saveLeaveEntitlement']($scope.data).then(        		
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
	
$scope.deleteleaveentitle = function(deleteclassid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deleteleaveentitle.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deleteclassok = function() {
				  deleteleave(deleteclassid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleteleave(deleteid){
	$http({
		url:appCon.globalCon.serviceBaseURL+"leaveEntitlement/deleteLeaveEntitlement?leaveEntitlementId="+deleteid,
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