'use strict';

angular.module(appCon.appName).controller('configgradlimitcontroler',
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



$http.get("mock/config/Grade.json").success(function(data){
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



$scope.Status = [{value:"Pass",key:"Pass"},{value:"Fail",key:"Fail"}];

$scope.backtosearch = function(){ 
	$scope.search="";	
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
}
$scope.returnsearch = function(){
	$scope.myForm.$setPristine()
	$scope.search="";
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
}

$scope.edit = function(ReligionName){
	
    $scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 
	
	$scope.configVO.Grade = updateconfigVO;
	$scope.configVO.MinMark = updateconfigVO;
	$scope.configVO.MaxMark = updateconfigVO;
	$scope.configVO.Status = updateconfigVO;
	
	
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

$scope.deletegradlimit = function(){

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
$scope.go = function(){
		$scope.updatehidesearchdet = false;	
		$scope.hidesearchdet = true;
		$scope.tablehidesearchdet = true;
		
	}



$scope.finalstep = function(){
	console.log("ok ok");
	$state.go('studentmanagement');
}


}]);

