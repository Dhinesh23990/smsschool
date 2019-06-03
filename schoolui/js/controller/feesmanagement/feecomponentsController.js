'use strict';

angular.module(appCon.appName).controller('feecomponentsController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter=$injector.get('$filter');
$scope.hidesearchdet = true;
$scope.hidetable = true;
$scope.showupdatefrm = false;


$scope.getallcomponentdata = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"feeComponent/getAllFeeComponentBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=50",
		method:'GET'
	}).success(function(result){
		
		$scope.items = result.responseVO.feeComponentVOs;
		console.log($scope.items);
		if($scope.items.length > 10){
			$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",true);
		}else{
			$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						
		}
		
	});
}
$scope.createcomponent = function(){ 

	$scope.errormsg = false;
	var data = $scope.FeeComponentVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"FeeComponentVO":$scope.FeeComponentVO}};
	console.log($scope.data);
		
	$injector.get('feesmanagementservice')['saveFeeComponent']($scope.data).then(        		
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
		url:appCon.globalCon.serviceBaseURL+"feeComponent/getFeeComponentById?feeComponentId="+id,
		method:'GET'
	}).success(function(result){
		console.log(result.responseVO);
		var getupdata = result.responseVO.FeeComponentVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"FeeComponentVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('feesmanagementservice')['updateFeeComponent']($scope.updatedata).then(        		
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
						
//list of student details

/*$http.get("mock/fees/feesComponents.json").then(function(data){
	$scope.items = data.Class;
});


$http.get("mock/fees/feesComponents.json").success(function(data){
	if(data.Status == "Ok")
		$scope.items = data.feesComponentsVO;
});*/

$scope.backtosearch = function(){
	$scope.FeeComponentVO="";
	$scope.search="";
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	$scope.errormsg = false;
}
$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
		  var defaultForm = {
              feeTypeId : "",
			  feeComponent:""
          };
		$scope.FeeComponentVO = defaultForm;
		$scope.errormsg = false;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
}

$scope.updatesearch = function(){
 $scope.errormsg = true;
	$scope.hidesearchdet = true;
		
	$scope.hidetable = true;
	$scope.showupdatefrm = false;
	
}
$scope.goclear = function(){
	$scope.createForm.$setPristine();
		   var defaultForm = {
              feeTypeId : "",
			  feeComponent:""
          };
		$scope.FeeComponentVO = defaultForm;
	  $scope.errormsg = false;
		
	}
	
	$scope.go = function(){
	   $scope.errormsg = false;
		$scope.showupdatefrm = false;	
		$scope.hidesearchdet = true;
		$scope.hidetable = true;
		
		
	}
	
	
	$scope.deleteComponent = function(deletecomponentId){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletecomponent.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletecomponentok = function() {
				  deleteComponent(deletecomponentId);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}
function deleteComponent(deleteId){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeComponent/deleteFeeComponent?feeComponentId="+deleteId,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}




//fee type list
$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/getAllFeeTypeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		
		$scope.feetypes = result.responseVO.feeTypeVOs;
		
		
	});

//$scope.feetypes = [{value:"Annual Fees",key:"Annual Fees"},{value:"Monthly Fees",key:"Monthly Fees"},{value:"Miscellaneous Fees",key:"Miscellaneous Fees"},{value:"Term Fees",key:"Term Fees"}];




angular.module(appCon.appName).directive('myDirective', function ($http) {
    return {
        restrict: 'A',
        scope: true,
        link: function (scope, element, attr) {

            element.bind('change', function () {
                var formData = new FormData();
                formData.append('file', element[0].files[0]);
				console.log("fileupload");
				console.log(formData);
				
            });

        }
    };
});
}]);

