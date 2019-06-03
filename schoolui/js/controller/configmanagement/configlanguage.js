'use strict';

angular.module(appCon.appName).controller('configlanguagecontroler',
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
  
$scope.getAllLanguageData = function(){
	$http({
			url:appCon.globalCon.serviceBaseURL+"language/getAllLanguageBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
			method:'GET'
		}).success(function(result){
			$scope.totalRecords = result.responseVO.totalRecords;
		});
}
$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"language/getAllLanguageBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.languageVOs;
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

	
$scope.createlanguage = function(){ 
		
		var data = $scope.LanguageVO;
		data.schoolId = $cookieStore.get('schoolId');
		$scope.data = {"SMSRequest":{"LanguageVO":$scope.LanguageVO}};
		
		//console.log($scope.data);	
		$injector.get('configmanagementservice')['saveLanguage']($scope.data).then(        		
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
		url:appCon.globalCon.serviceBaseURL+"language/getLanguageById?languageId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.LanguageVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
}	



$scope.updatelanguage = function(){
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"LanguageVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateLanguage']($scope.updatedata).then(        		
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

$scope.deletelanguage = function(deleteLanguage){
 
	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletelanguage.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletelanguageok = function() {
				  deleletok(deleteLanguage);
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
		url:appCon.globalCon.serviceBaseURL+"language/deleteLanguage?languageId="+deleteid,
		method:'DELETE'
	}).success(function(result){		
		$state.go($state.current, {}, {reload: true});
		
	});
}


$scope.backtosearch = function(){ 
    $scope.LanguageVO = "";
	$scope.search="";	
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
	$scope.errormsg = false;
}

$scope.returnsearch = function(){
	$scope.createmyForm.$setPristine();
	   var defaultForm = {
              languageName : ""  
          };
	$scope.LanguageVO = defaultForm;
	$scope.hidesearchdet = true;
	$scope.errormsg = false;
	$scope.tablehidesearchdet = true;
}

/*	$scope.edit = function(ReligionName){
		
		$scope.tablehidesearchdet= false;
		$scope.updatehidesearchdet = true;
		$scope.hidesearchdet = false; 
		
		$scope.updateReligionName = ReligionName;
		
	}  */

   $scope.updatereturnsearch = function(){      
       $scope.createmyForm.$setPristine();	
       $scope.LanguageVO = "";
	   $scope.errormsg = false;
	   $scope.updatehidesearchdet = false;
	   $scope.hidesearchdet = true;
	   $scope.tablehidesearchdet = true;
   }
   $scope.cancel = function(){
	    $scope.errormsg = false;
		$scope.updatehidesearchdet = false;	
		$scope.hidesearchdet = true;
		$scope.tablehidesearchdet = true;		
	}

	$scope.clear = function(){
		
	    $scope.createmyForm.$setPristine();
		$scope.errormsg = false;
	      var defaultForm = {
              languageName : ""  
          };
		$scope.LanguageVO = defaultForm;
		
	}

$scope.finalstep = function(){
	console.log("ok ok");
	$state.go('studentmanagement');
}


}]);

angular.module(appCon.appName).directive('numericOnlyLanguage', function(){
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