'use strict';

angular.module(appCon.appName).controller('configclassController',
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

$scope.getAllClassData = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
	});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"class/getAllClassBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.classVOs;
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
$scope.createclass = function(){ 

	$scope.errormsg = false;
	var data = $scope.ClassVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"ClassVO":$scope.ClassVO}};
		
	$injector.get('configmanagementservice')['saveClass']($scope.data).then(        		
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
		url:appCon.globalCon.serviceBaseURL+"class/getClassById?classId="+id,
		method:'GET'
	}).success(function(result){
		console.log(result.responseVO.ClassVO);
		var getupdata = result.responseVO.ClassVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"ClassVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateClass']($scope.updatedata).then(        		
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

$scope.backtosearch = function(){
    $scope.ClassVO="";
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	$scope.errormsg = false;
	}


$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
		  var defaultForm = {
              className : ""  
          };
	$scope.ClassVO = defaultForm;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.errormsg = false;
}
$scope.goclear = function(){
	  $scope.errormsg = false;
	  $scope.createForm.$setPristine();
	   var defaultForm = {
              className : ""  
          };
	$scope.ClassVO = defaultForm;
		}
 
$scope.updatesearch = function(){
	$scope.errormsg = false;
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.showupdatefrm = false;
}
$scope.go = function(){
	   $scope.errormsg = false;
		$scope.showupdatefrm = false;	
		$scope.hidesearchdet = true;
		$scope.hidetable = true;
}
	
	$scope.deleteClass = function(deleteclassid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deleteclass.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deleteclassok = function() {
				  deleteClass(deleteclassid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleteClass(deleteid){
	console.log(deleteid);
	$http({
		url:appCon.globalCon.serviceBaseURL+"class/deleteClass?classId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
$scope.finalstep = function(){
	$state.go('configclass');
}


}]);

angular.module(appCon.appName).directive('numericOnlyClass', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^a-zA-Z0-9 ]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});