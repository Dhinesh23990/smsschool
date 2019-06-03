'use strict';

angular.module(appCon.appName).controller('confignationalitycontroler',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder,$cookieStore) {
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


$scope.getAllNationalityData = function(){
	$http({
			url:appCon.globalCon.serviceBaseURL+"nationality/getAllNationalityBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
			method:'GET'
		}).success(function(result){
			$scope.totalRecords = result.responseVO.totalRecords;
	});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"nationality/getAllNationalityBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.nationalityVOs;
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

$scope.createNationalityname = function(){ 
		
		var data = $scope.NationalityVO;
		data.schoolId = $cookieStore.get('schoolId');
		$scope.data = {"SMSRequest":{"NationalityVO":$scope.NationalityVO}};
		
		//console.log($scope.data);	
		$injector.get('configmanagementservice')['saveNationality']($scope.data).then(        		
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
    console.log(id);
	$scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 
	//$scope.updateCountryname = languagename;
	$http({
		url:appCon.globalCon.serviceBaseURL+"nationality/getNationalityById?nationalityId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.NationalityVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
}	

$scope.updateNationalityname = function(){
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"NationalityVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateNationality']($scope.updatedata).then(        		
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



$scope.deletenationality = function(deleteNationality){
 
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletelnationality.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletenationalityok = function() {
				  deleletok(deleteNationality);
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
		url:appCon.globalCon.serviceBaseURL+"nationality/deleteNationality?nationalityId="+deleteid,
		method:'DELETE'
	}).success(function(result){		
		$state.go($state.current, {}, {reload: true});
		
	});
}

$scope.backtosearch = function(){ 

    $scope.NationalityVO="";
	$scope.search="";	
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
}
$scope.returnsearch = function(){	
	$scope.createmyForm.$setPristine();
		  var defaultForm = {
              nationalityName : ""  
          };
	$scope.NationalityVO = defaultForm;
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
	$scope.errormsg = false;
}

$scope.updatereturnsearch = function(){
	   $scope.errormsg = false;	   
	   $scope.updatehidesearchdet = false;
	   $scope.hidesearchdet = true;
	   $scope.tablehidesearchdet = true;
   }	
   $scope.cancel = function(){
	//    $scope.errormsg = false;
	    $scope.updatehidesearchdet = false;
	    $scope.createmyForm.$setPristine()
	//    $scope.BloodGroupVO="";
		$scope.hidesearchdet = true;
	    $scope.tablehidesearchdet = true;
	}
	$scope.clear = function(){
	  //  $scope.errormsg = false;
	    $scope.createmyForm.$setPristine()
	    var defaultForm = {
              nationalityName : ""  
          };
		$scope.NationalityVO = defaultForm;
		$scope.errormsg = false;		
	}

/*$scope.deletereligion = function(){

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
}  */





$scope.finalstep = function(){
	console.log("ok ok");
	$state.go('studentmanagement');
}


}]);



angular.module(appCon.appName).directive('numericOnlyNationality', function(){
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