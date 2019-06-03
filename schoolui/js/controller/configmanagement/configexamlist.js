'use strict';

angular.module(appCon.appName).controller('configexamlistController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$scope.hidesearchdet = true;
$scope.updatehidesearchdet = false;
$scope.tablehidesearchdet = true;
	
$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false);
						
//list of student details



$http.get("mock/config/config.json").success(function(data){
	if(data.Status == "Ok"){
		$scope.items = data.configVO;
		console.log($scope.items.length);
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
	}
});

$scope.backtosearch = function(){ 
	$scope.search="";	
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
}

$scope.backtosearch1 = function(){ 
	$scope.search="";	
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
	$scope.returnsearch = true;
}
$scope.returnsearch = function(){
	
	
	$scope.myForm.$setPristine()
	$scope.configVO="";
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
}

$scope.edit = function(ReligionName){
	
    $scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 
	
	$scope.updateReligionName = ReligionName;
	
}

   $scope.updatereturnsearch = function(){   
	   $scope.updatehidesearchdet = false;
	   $scope.hidesearchdet = true;
	   $scope.tablehidesearchdet = true;
   }
   $scope.go = function(){
		$scope.updatehidesearchdet = false;	
		$scope.hidesearchdet = true;
		$scope.tablehidesearchdet = true;
		
	}
	 $scope.goclear = function(){
	    $scope.myForm.$setPristine()
	    $scope.configVO.ReligionName = "";
		
		
	}

$scope.deletereligion = function(){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletereligion.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletereligionok = function() {
				  $modalInstance.close();
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}




$scope.finalstep = function(){
	console.log("ok ok");
	$state.go('studentmanagement');
}


}]);

