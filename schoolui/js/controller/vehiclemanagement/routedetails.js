'use strict';

angular.module(appCon.appName).controller('routedetails',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
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


//list of student details

/*$http.get("mock/class/class.json").then(function(data){
	$scope.items = data.Class;
});


$http.get("mock/config/class.json").success(function(data){
	if(data.Status == "Ok")
		$scope.items = data.ClassView;
});*/
$scope.getAllRouteData = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		
		$scope.items = result.responseVO.classVOs;
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
$scope.createclass = function(){ 

	$scope.errormsg = false;
	var data = $scope.ClassVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"ClassVO":$scope.ClassVO}};
		
	$injector.get('configmanagementservice')['saveClass']($scope.data).then(        		
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
		url:appCon.globalCon.serviceBaseURL+"class/getClassById?classId="+id,
		method:'GET'
	}).success(function(result){
		console.log(result.responseVO.ClassVO);
		var getupdata = result.responseVO.ClassVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"ClassVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateClass']($scope.updatedata).then(        		
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
	  $scope.ClassVO="";
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
		url:appCon.globalCon.serviceBaseURL+"class/deleteClass?classId="+deleteid,
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