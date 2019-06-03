'use strict';

angular.module(appCon.appName).controller('configmothertongueController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.updatehidesearchdet = false;
$scope.tablehidesearchdet = true;

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

$scope.mothertonquedata =function(){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"motherTongue/getAllMotherTongueBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
		
	});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"motherTongue/getAllMotherTongueBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.motherTongueVOs;
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
$scope.createmothertongue = function(){ 
   
   $scope.errormsg = false;
	var data = $scope.MotherTongueVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"MotherTongueVO":$scope.MotherTongueVO}};
		$injector.get('configmanagementservice')['saveMotherTongue']($scope.data).then(        		
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
	$scope.MotherTongueVO = "";		
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
	$scope.errormsg = false;
}
$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
	   var defaultForm = {
              motherTongueName : ""  
          };
	$scope.MotherTongueVO = defaultForm;
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
	$scope.errormsg = false;
}

$scope.edit = function(id){
	
    $scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 
	$scope.errormsg = false;
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"motherTongue/getMotherTongueById?motherTongueId="+id,
		method:'GET'
	}).success(function(result){
				var getupdata = result.responseVO.MotherTongueVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"MotherTongueVO":updata}};
	
	$injector.get('configmanagementservice')['updateMotherTongue']($scope.updatedata).then(        		
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



   $scope.updatereturnsearch = function(){   
	   $scope.updatehidesearchdet = false;
	   $scope.hidesearchdet = true;
	   $scope.tablehidesearchdet = true;
		$scope.errormsg = false;
   }
    
	$scope.go = function(){
		$scope.updatehidesearchdet = false;	
		$scope.hidesearchdet = true;
		$scope.tablehidesearchdet = true;
		$scope.errormsg = false;
	}
   $scope.clearcontent = function(){
	 $scope.createForm.$setPristine();
	  var defaultForm = {
              motherTongueName : ""  
          };
	$scope.MotherTongueVO = defaultForm;
	  $scope.errormsg = false;

}

$scope.deletemothertongue = function(deletesubjecttypeid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletemothertongue.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletemothertongueok = function() {
				  deleletconformmothertongue(deletesubjecttypeid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleletconformmothertongue(deleteid){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"motherTongue/deleteMotherTongue?motherTongueId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
		

}]);
angular.module(appCon.appName).directive('numericOnlyMothertongue', function(){
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {

            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue ? inputValue.replace(/[^a-zA-Z+a-zA-z]/g,'') : null;

                if (transformedInput!=inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});



