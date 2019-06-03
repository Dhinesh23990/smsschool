'use strict';

angular.module(appCon.appName).controller('configtimetableController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore =$injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.searchview = true;
$scope.statedet = true;	
$scope.statelist = true;

$http({
		url:appCon.globalCon.serviceBaseURL+"country/getAllCountryBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var datat = result.responseVO.countryVOs;
		$scope.countrydatass = [];
			angular.forEach(datat,function(value, key){
				$scope.countrydatass.push({value:value.countryName,key:value.id});
			});
			
	});
$scope.gotodashboard = function()
		{
			
			angular.element("#slimScrollDiv").find('ul li:first').remove('active');
			angular.element("#slimScrollDiv").find('ul li:first').addClass('active');
			angular.element("#slimScrollDiv").find('ul').parent('li').removeClass('active');
		
			$state.go('dashboard');
			
		}	

$scope.clearcitydata = function(){
	/* var clearcitydata = $scope.StateVO; 
	 clearcitydata.stateName ="";  */
 }
 
$scope.clearupdatecitydata = function(){
/*	 var clearcitydata = $scope.UpdateVO; 
		clearcitydata.stateName ="";  */
 }
	
$scope.gatallstatedata = function(){
	
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"state/getAllStateBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.state = result.responseVO.stateVOs;
		if($scope.state.length > 10)
			 {
				 $scope.dtOptins = DTOptionsBuilder.newOptions()
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
	
	/**/
		
	}
	
		          /*State Create */
	$scope.createstate = function(){ 
    $scope.errormsg = false;
	var data = $scope.StateVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"StateVO":$scope.StateVO}};
	
		
	$injector.get('configmanagementservice')['saveState']($scope.data).then(        		
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


		/*State Update */

$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"StateVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateState']($scope.updatedata).then(        		
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
	    
		/*State Edit */

$scope.edit = function(id){
	$scope.searchview = false;
     $scope.updatedata = true;
    $scope.hidesearchdet = false;
     $scope.statedet = false;
	$http({
		url:appCon.globalCon.serviceBaseURL+"state/getStateById?stateId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.StateVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
	
}	
	
	/*hide / show state function */
	
$scope.updatereturnsearch =function()
{
	$scope.statedet = true;
	$scope.hidesearchdet = true;
    $scope.searchview = true;
	$scope.updatedata = false;
	$scope.errormsg = false;
}

$scope.backtosearch = function(){
	$scope.createForm.$setPristine();
	  $scope.StateVO="";
    $scope.statelist = true;
	$scope.hidesearchdet = false;
	$scope.create = true;
    $scope.statedet = false;
   $scope.searchview = false;
   $scope.errormsg = false;
	
}
$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
	  $scope.StateVO="";
	  $scope.search="";
	 $scope.hidesearchdet = true;
     $scope.searchview = true;
	$scope.statedet = true;
	$scope.errormsg = false;

}

$scope.clearcontent = function(){
	$scope.createForm.$setPristine();
	  $scope.StateVO="";
	  $scope.errormsg = false;
}
$scope.go = function(){
	   $scope.errormsg = false;
		$scope.updatedata = false;	
		$scope.hidesearchdet = true;
		$scope.statedet = true;
		
	}

$scope.viewsearch = function()
{   
    $scope.statelist = true;
	$scope.statedet = false;
	$scope.updatedata = true;
	$scope.hidesearchdet = false;
	$scope.searchview = false;
	$scope.errormsg = false;
}
           
		   /*Delete State*/
$scope.deletestate = function(deletestateid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletestates.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletestateok = function() {
				  deleletconformstate(deletestateid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

                                     

function deleletconformstate(deleteid){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"state/deleteState?stateId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
		

}]);

  /*State Directive*/
  
angular.module(appCon.appName).directive('numericOnlyState', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^a-zA-Z ]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});
