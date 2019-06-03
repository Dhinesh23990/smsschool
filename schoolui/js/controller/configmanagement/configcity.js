'use strict';

angular.module(appCon.appName).controller('configcityController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder,$cookieStore) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore =$injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.searchview = true;
$scope.citydet = true;	
$scope.citylist = true;
$scope.noresultsfound = true;
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



$http({
		url:appCon.globalCon.serviceBaseURL+"country/getAllCountryBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var datat = result.responseVO.countryVOs;
		$scope.countrydatass = [];
			angular.forEach(datat,function(value, key){
				$scope.countrydatass.push({value:value.countryName,key:value.id});
			});
			
			console.log($scope.countrydatass);
		
	});
						
$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;

  
  
$scope.getAllCityData = function(){
$http({
		url:appCon.globalCon.serviceBaseURL+"city/getAllCityBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
 });

}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"city/getAllCityBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.cityVOs;
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
/*
		$http({
				url:appCon.globalCon.serviceBaseURL+"country/getAllCountryBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
				method:'GET'
			}).success(function(result){
				var datat = result.responseVO.countryVOs;
				$scope.countrydatass = [];
					angular.forEach(datat,function(value, key){
						$scope.countrydatass.push({value:value.countryName,key:value.countryName});
					});
					
					console.log($scope.countrydatass);
				
			});

  */$scope.updatecitydata = function(){
	 var updatecitydata = $scope.UpdateVO; 
		updatecitydata.cityName ="";
 }
  $scope.updatecity = function(countryname){
	console.log(countryname);
	
	$http({
   		   url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+countryname,
				method:'GET'
			}).success(function(result){
				console.log(result);
				var statepush = result.responseVO.stateVOs,updatecitydata = $scope.UpdateVO; 
					updatecitydata.state ="";
					updatecitydata.cityName ="";
				$scope.stateval = [];
					angular.forEach(statepush,function(value, key){
						$scope.stateval.push({value:value.stateName,key:value.stateName});
					});
					
					console.log($scope.stateval);
				
			});
	
	
}

 $scope.clearcitydata = function(){
	 var clearcitydata = $scope.CityVO; 
		clearcitydata.cityName ="";
 }
$scope.getcity = function(countryname){
	console.log(countryname);
	
	$http({
   		   url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+countryname,
				method:'GET'
			}).success(function(result){
				console.log(result);
				var statepush = result.responseVO.stateVOs,clearcitydata = $scope.CityVO; 
					clearcitydata.state ="";
					clearcitydata.cityName ="";
				$scope.stateval = [];
					angular.forEach(statepush,function(value, key){
						$scope.stateval.push({value:value.stateName,key:value.id});
					});
					
					console.log($scope.stateval);
				
			});
	
	
}


$scope.createcity = function(){ 
  
	$scope.errormsg = false;
	var data = $scope.CityVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"CityVO":$scope.CityVO}};
	
		
	$injector.get('configmanagementservice')['saveCity']($scope.data).then(        		
		 //Request Success 
		function(result) {
			console.log("dfdsfds");
			if(result.data.statusFlag == 'Ok')
				$state.go($state.current, {}, {reload: true});
			if(result.data.statusFlag == 'Error')
				$scope.errormsg = true;
		},		
		function(error){
			console.log('failure');
		});
		
	
}	
$scope.update = function(){
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"CityVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateCity']($scope.updatedata).then(        		
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
	$scope.edit = function(id){
		
    $scope.citydet= false;
	$scope.updatedata = true;
	$scope.hidesearchdet = false; 
		
	$http({
		url:appCon.globalCon.serviceBaseURL+"city/getCityById?cityId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.CityVO;
		$http({
   		   url:appCon.globalCon.serviceBaseURL+"state/getAllStateByCountryName?countryName="+getupdata.countryId,
				method:'GET'
			}).success(function(result){
				console.log(result);
				var statepush = result.responseVO.stateVOs; 
					
				$scope.stateval = [];
					angular.forEach(statepush,function(value, key){
						$scope.stateval.push({value:value.stateName,key:value.id});
					});
					
					console.log($scope.stateval);
					getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
					getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
					$scope.UpdateVO = getupdata;
				
			});
		
		
	});
}


	
$scope.deletecity = function(deleteCity){
  
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletecity.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletecityok = function() {
				  deleletok(deleteCity);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleletok(deleteid){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"city/deleteCity?cityId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		console.log(result);
		$state.go($state.current, {}, {reload: true});
		
	});
}





$scope.updatereturnsearch =function()
{
	$scope.citydet = true;
    $scope.hidesearchdet = true;
    $scope.searchview = true;
    $scope.updatedata = false;
	$scope.errormsg = false;
	
}

$scope.backtosearch = function(){
	$scope.CityVO="";
	$scope.hidesearchdet = false;
	$scope.createform = true;
	$scope.createcityform.$setPristine();
    $scope.citydet = false;
	
  // $scope.searchview = false;
	
}

$scope.returnsearch = function(){
	$scope.createcityform.$setPristine();
	var defaultForm = {
	  countryId : "",
	  stateId : "",  
	  cityName : ""  			  
	};
	$scope.CityVO = defaultForm;
	//$scope.createform = false;
	$scope.hidesearchdet = true;
	$scope.citydet = true;
    
}

$scope.clearcontent = function(){
	$scope.errormsg = false;
	$scope.createcityform.$setPristine();	
	var defaultForm = {
              countryId : "",
              stateId : "",  
              cityName : ""  			  
          };
	$scope.CityVO = defaultForm;
	
}
$scope.go = function(){
	   
		$scope.updatedata = false;	
		$scope.hidesearchdet = true;
		$scope.citydet = true;
		$scope.errormsg = false;
	
		
	}

$scope.viewsearch = function()
{   
    $scope.citylist = true;
	$scope.citydet = false;
	$scope.updatedata = true;
	$scope.hidesearchdet = false;
	$scope.searchview = false;
	$scope.errormsg = false;
	
}
}]);




angular.module(appCon.appName).directive('numericOnlyCity', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^a-zA-Z1]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});