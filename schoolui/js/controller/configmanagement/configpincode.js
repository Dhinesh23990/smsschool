'use strict';

angular.module(appCon.appName).controller('configpincodeController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$scope.hidesearchdet = true;
$scope.searchview = true;
$scope.pincodedet = true;	
$scope.pincodelist = true;

$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false);

$http.get("mock/config/pincode.json").success(function(data){

	if(data.Status == "Ok")
		$scope.pincode = data.pinVO;

       if($scope.pincode.length > 10){
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





$scope.updatereturnsearch =function()
{
	$scope.pincodedet = true;
 $scope.hidesearchdet = true;
     $scope.searchview = true;
$scope.updatedata = false;
}

$scope.backtosearch = function(){
    $scope.citylist = true;
	$scope.search="";
	$scope.hidesearchdet = false;
	$scope.createform = true;
     $scope.pincodedet = false;
   $scope.searchview = false;
	
}
$scope.returnsearch = function(){
	  $scope.step1.$setValidity();
	$scope.step1.$setPristine();
	$scope.step1.$setUntouched();
		  var defaultForm = {
              countryName : ""  
          };
	$scope.CountryVO = defaultForm;	
	  $scope.search="";
	 $scope.hidesearchdet = true;
     $scope.searchview = true;
	$scope.pincodedet = true;

}

$scope.clearcontent = function(){
	$scope.search = "";
}

$scope.viewsearch = function()
{   
    $scope.citylist = true;
	$scope.pincodedet = false;
	$scope.updatedata = true;
	$scope.hidesearchdet = false;
	$scope.searchview = false;
}
$scope.deletemedium = function(){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletemedium.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletemediumok = function() {
				  $modalInstance.close();
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}



}]);
