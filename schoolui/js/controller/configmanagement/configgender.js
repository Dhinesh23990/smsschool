'use strict';

angular.module(appCon.appName).controller('gendermgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder', 'MethodService',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
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

$scope.getallgenderdata = function()
{
	$http ({
		url:appCon.globalCon.serviceBaseURL+"gender/getAllGenderBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
		}).success(function(result){
            $scope.totalRecords = result.responseVO.totalRecords;
		});
   }
						
$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"gender/getAllGenderBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
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
$scope.edit = function(id)
{
	

$scope.showupdatefrm = true;
$scope.hidesearchdet = false;
$scope.hidetable = false;
$http({
		url:appCon.globalCon.serviceBaseURL+"gender/getGenderById?genderId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.GenderVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});


}

$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.showupdatefrm = {"SMSRequest":{"GenderVO":updata}};
	
	$injector.get('configmanagementservice')['updategender']($scope.showupdatefrm).then(        		
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





$scope.creategender = function(){ 
    $scope.errormsg = false;
	var data = $scope.GenderVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"GenderVO":$scope.GenderVO}};
	
		
	$injector.get('configmanagementservice')['saveGender']($scope.data).then(        		
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

$scope.backtosearch = function(){
    $scope.GenderVO = "";	
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	$scope.errormsg = false;
	
}

$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
	   var defaultForm = {
              gender : ""  
          };
	$scope.GenderVO = defaultForm;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.errormsg = false;
}

$scope.updatesearch = function(){
	
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.showupdatefrm = false;
	$scope.errormsg = false;
}
$scope.showupdateform = function()
{
	
	$scope.showupdatefrm = true;
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	
}

$scope.clearcontent = function(){
	
	$scope.createForm.$setPristine();
	   var defaultForm = {
              gender : ""  
          };
	$scope.GenderVO = defaultForm;
	$scope.GenderVO = "";	
	  $scope.errormsg = false;
}
$scope.go = function(){
	   $scope.errormsg = false;
		$scope.showupdatefrm = false;	
		$scope.hidesearchdet = true;
		$scope.hidetable = true;
		
	}

$scope.deletegender = function(deletegenderid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletegender.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletegenderok = function() {
				  deleletconformgender(deletegenderid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleletconformgender(deleteid){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"gender/deleteGender?genderId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
		

}]);


angular.module(appCon.appName).directive('numericOnlyGender', function(){
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

