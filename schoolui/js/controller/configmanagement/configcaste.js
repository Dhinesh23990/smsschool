'use strict';

angular.module(appCon.appName).controller('configcastecontroller',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder,$cookieStore) {
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
$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;
						
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

$scope.getAllCateData = function(){
$http({
		url:appCon.globalCon.serviceBaseURL+"caste/getAllCasteBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
	});
}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"caste/getAllCasteBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.casteVOs;
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
$scope.createcaste = function(){ 
		
		var data = $scope.CasteVO;
		data.schoolId = $cookieStore.get('schoolId');
		$scope.data = {"SMSRequest":{"CasteVO":$scope.CasteVO}};
		
		//console.log($scope.data);	
		$injector.get('configmanagementservice')['saveCaste']($scope.data).then(        		
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
		
    $scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 
		
	$http({
		url:appCon.globalCon.serviceBaseURL+"caste/getCasteById?casteId="+id,
		method:'GET'
	}).success(function(result){		
		var getupdata = result.responseVO.CasteVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
}

$scope.update = function(){
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"CasteVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateCaste']($scope.updatedata).then(        
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

		
$scope.deletecaste = function(deleteCaste){
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletecaste.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletecasteok = function() {
				  deleletok(deleteCaste);
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
		url:appCon.globalCon.serviceBaseURL+"caste/deleteCaste?casteId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		console.log(result);
		$state.go($state.current, {}, {reload: true});
		
	});
}

$scope.backtosearch = function(){ 

    $scope.CasteVO="";
	$scope.search="";	
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
	$scope.errormsg = false;
}
$scope.returnsearch = function(){
	$scope.createmyForm.$setPristine();
		  var defaultForm = {
              casteName : ""  
          };
	$scope.CasteVO = defaultForm;
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
	
	    $scope.updatehidesearchdet = false;
	    $scope.createmyForm.$setPristine()
		$scope.errormsg = false;
		$scope.hidesearchdet = true;
	    $scope.tablehidesearchdet = true;
	}
	$scope.clear = function(){
	    $scope.CasteVO="";
		  var defaultForm =  {
              CasteName : ""  
          };
		$scope.CasteVO = angular.copy(defaultForm);
		$scope.createmyForm.$setPristine();
		$scope.errormsg = false;

	}
	


$scope.finalstep = function(){
	$state.go('studentmanagement');
}


}]);


angular.module(appCon.appName).directive('numericOnlyCaste', function(){
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


