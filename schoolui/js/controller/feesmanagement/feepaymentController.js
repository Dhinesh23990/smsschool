'use strict';

angular.module(appCon.appName).controller('feepaymentController',
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

//list of student details

/*$http.get("mock/fees/feetype.json").then(function(data){
	$scope.items = data.Class;
});*/
$scope.getallpayment = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"paymentMode/getAllPaymentModeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize=50",
		method:'GET'
	}).success(function(result){
		
		$scope.items = result.responseVO.paymentModeVOs;
		if($scope.items.length > 10){
			$scope.dtOptions = DTOptionsBuilder.newOptions()
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
}
$scope.create = function(){ 

	$scope.errormsg = false;
	var data = $scope.paymentModeVOs;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"paymentModeVOs":$scope.paymentModeVOs}};
		
	$injector.get('feesmanagementservice')['savePaymentMode']($scope.data).then(        		
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
		url:appCon.globalCon.serviceBaseURL+"paymentMode/getPaymentModeById?paymentModeId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.PaymentModeVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"paymentModeVOs":updata}};
	console.log($scope.updatedata);	
	$injector.get('feesmanagementservice')['updatePaymentMode']($scope.updatedata).then(        		
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
	$scope.paymentModeVOs = "";
	$scope.search="";
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
}
$scope.returnsearch = function(){

	$scope.createForm.$setPristine();
		   var defaultForm = {
              payName : ""
          };
	$scope.paymentModeVOs = defaultForm;
	 $scope.errormsg = false;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
}

$scope.updatesearch = function(){
	
	$scope.hidesearchdet = true;
		
	$scope.hidetable = true;
	$scope.showupdatefrm = false;
	
}

$scope.goclear = function(){
	  $scope.errormsg = false;
	 $scope.createForm.$setPristine();
		   var defaultForm = {
              payName : ""
          };
	$scope.paymentModeVOs = defaultForm;
		
	}
	
	$scope.go = function(){
	   $scope.errormsg = false;
		$scope.showupdatefrm = false;	
		$scope.hidesearchdet = true;
		$scope.hidetable = true;
		
		
	}


$scope.deletePayment = function(deletePaymentId){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletePayment.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletePaymentOk = function() {
				  deletePaymentmode(deletePaymentId);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}
function deletePaymentmode(deleteId){
	console.log(deleteId);
	$http({
		url:appCon.globalCon.serviceBaseURL+"paymentMode/deletePaymentMode?paymentModeId="+deleteId,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
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