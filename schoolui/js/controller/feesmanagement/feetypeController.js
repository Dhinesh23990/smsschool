'use strict';

angular.module(appCon.appName).controller('feetypeController',
					['$injector',  '$scope', 'DTOptionsBuilder', 'MethodService',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$filter,$cookieStore;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.isOpen = false;

$scope.hidesearchdet = true;
$scope.hidetable = true;
$scope.showupdatefrm = false;

$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;

  $scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('info',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
		
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
						
$scope.getallfeetype = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/getAllFeeTypeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
		//$scope.items = result.responseVO.feeTypeVOs;

	});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"feeType/getAllFeeTypeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.feeTypeVOs;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
		})
		$scope.items = itemsdata;
		if($scope.items.length > 0)
			$scope.show_datatables = true;
		
		$scope.checktotalRecords = result.responseVO.totalRecords;
		$scope.show_begin = begin+1;
		if($scope.checktotalRecords > end)
			$scope.show_end = end;
		else
			$scope.show_end = $scope.checktotalRecords;
		
		if($scope.checktotalRecords > 10)
			$scope.abovedata = true;
		else
			$scope.abovedata = false;
	});
    
  });
  
$scope.create = function(){ 

	$scope.errormsg = false;
	var data = $scope.feeTypeVOs;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"feeTypeVOs":$scope.feeTypeVOs}};
		
	$injector.get('feesmanagementservice')['saveFeeType']($scope.data).then(        		
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
	
    $scope.hidetable= false;
	$scope.showupdatefrm = true;
	$scope.hidesearchdet = false; 
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/getFeeTypeById?feeTypeId="+id,
		method:'GET'
	}).success(function(result){
		var getupdata = result.responseVO.FeeTypeVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
	$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"FeeTypeVO":updata}};
	console.log("updated");
	console.log($scope.updatedata);	
	$injector.get('feesmanagementservice')['updateFeeType']($scope.updatedata).then(        		
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

/*$http.get("mock/fees/feetype.json").success(function(data){
	if(data.Status == "Ok")
		$scope.items = data.feestypeVO;
});*/

$scope.backtosearch = function(){
	$scope.feeTypeVOs="";
	$scope.search="";
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	 $scope.errormsg = false;
}
$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
		  var defaultForm = {
              feeType : "",
			  recurringPerYear:"",
			  remDuration:""
          };
		$scope.feeTypeVOs = defaultForm;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	 $scope.errormsg = false;
}

$scope.updatesearch = function(){
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.showupdatefrm = false;
	
}

$scope.goclear = function(){
	
	$scope.createForm.$setPristine();
		  var defaultForm = {
              feeType : "",
			  recurringPerYear:"",
			  remDuration:""
          };
		$scope.feeTypeVOs = defaultForm;
	  $scope.errormsg = false;
	  $scope.createForm.$setPristine();
	  $scope.feeTypeVOs="";
		
	}
	
	$scope.go = function(){
	   $scope.errormsg = false;
		$scope.showupdatefrm = false;	
		$scope.hidesearchdet = true;
		$scope.hidetable = true;
		
		
	}


$scope.deleteFeeType = function(deletefeeTypeId){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deleteFeeType.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deleteFeeTypeOk = function() {
				  deleteFeeType(deletefeeTypeId);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}
function deleteFeeType(delfeeTypeId){
	console.log(delfeeTypeId);
	$http({
		url:appCon.globalCon.serviceBaseURL+"feeType/deleteFeeType?feeTypeId="+delfeeTypeId,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}



}]);

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