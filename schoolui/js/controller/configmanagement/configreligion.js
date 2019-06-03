'use strict';

angular.module(appCon.appName).controller('configreligionController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder, $cookieStore) {
var $http,$state,$modal,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.updatehidesearchdet = false;
$scope.tablehidesearchdet = true;
$scope.errormsg = false;	
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

$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;

$scope.getAllReligionData = function(){
	$http({
			url:appCon.globalCon.serviceBaseURL+"religion/getAllReligionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
			method:'GET'
		}).success(function(result){
			$scope.totalRecords = result.responseVO.totalRecords;
		});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"religion/getAllReligionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.religionVOs;
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
	
$scope.createreligion = function(){ 
		$scope.createmyForm.$setPristine()
		var data = $scope.ReligionVO;
		data.schoolId = $cookieStore.get('schoolId');
		$scope.data = {"SMSRequest":{"ReligionVO":$scope.ReligionVO}};
		
		//console.log($scope.data);	
		$injector.get('configmanagementservice')['saveReligion']($scope.data).then(        		
			 //Request Success 
			function(result) {
				
				console.log(result.data.statusFlag);
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
    $scope.updatemyForm.$setPristine()
	$scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 	 
	 $scope.errormsg = false;
	//$scope.updateCountryname = languagename;
	$http({
		url:appCon.globalCon.serviceBaseURL+"religion/getReligionById?religionId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.ReligionVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
}	


$scope.updatereligion = function(){
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"ReligionVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateReligion']($scope.updatedata).then(        		
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

	

$scope.deletereligion = function(deleteReligion){
 
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletereligion.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletereligionok = function() {
				  deleletok(deleteReligion);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleletok(deleteid){
	console.log(deleteid);
	$http({
		url:appCon.globalCon.serviceBaseURL+"religion/deleteReligion?religionId="+deleteid,
		method:'DELETE'
	}).success(function(result){		
		$state.go($state.current, {}, {reload: true});
		
	});
}	
	
	
	
	
	

$scope.backtosearch = function(){    
	$scope.ReligionVO="";
	$scope.search="";	
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
}

$scope.returnsearch = function(){
	$scope.createmyForm.$setPristine();
	  var defaultForm = {
		  religionName : ""  
	  };
	$scope.ReligionVO = defaultForm;	
    $scope.errormsg = false;
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
	
}

   $scope.updatereturnsearch = function(){ 
       $scope.errormsg = false;
	   $scope.updatehidesearchdet = false;
	   $scope.hidesearchdet = true;
	   $scope.tablehidesearchdet = true;
	   
   }
   $scope.cancel = function(){
	//  $scope.errormsg = false;
		$scope.updatehidesearchdet = false;	
		$scope.hidesearchdet = true;
		$scope.tablehidesearchdet = true;
		$scope.errormsg = false;
		
	}
	 $scope.clear = function(){		 
	    $scope.createmyForm.$setPristine()
	    var defaultForm = {
		  religionName : ""  
		};
		$scope.ReligionVO = defaultForm; 
		$scope.errormsg = false;
    //    $scope.errormsg = false;		
	}

$scope.finalstep = function(){
	
	console.log("ok ok");
	$state.go('studentmanagement');
}


}]);




angular.module(appCon.appName).directive('numericOnlyReligion', function(){
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
