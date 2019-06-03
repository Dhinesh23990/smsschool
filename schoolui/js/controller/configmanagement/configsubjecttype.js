'use strict';

angular.module(appCon.appName).controller('configsubjecttypeController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$cookieStore;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$scope.hidesearchdet = true;
$scope.subjecttypedet = true;	
$scope.getallsubjettypedata = function()
{
	$http ({
		url:appCon.globalCon.serviceBaseURL+"subjectType/getAllSubjectTypeBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
		}).success(function(result){
             $scope.subjecttype = result.responseVO.subjectTypeVOs;
			 if($scope.subjecttype.length > 10)
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
                     			 }
								 
	
$scope.createsubjecttype = function(){ 
   
   $scope.errormsg = false;
	var data = $scope.SubjectTypeVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"SubjectTypeVO":$scope.SubjectTypeVO}};
		$injector.get('configmanagementservice')['saveSubjectType']($scope.data).then(        		
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

$scope.edit = function(id)
{
	

$scope.updatedata = true;
$scope.hidesearchdet = false;
$scope.subjecttypedet = false;
$http({
		url:appCon.globalCon.serviceBaseURL+"subjectType/getSubjectTypeById?subjectTypeId="+id,
		method:'GET'
	}).success(function(result){
				var getupdata = result.responseVO.SubjectTypeVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});

}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"SubjectTypeVO":updata}};
	
	$injector.get('configmanagementservice')['updateSubjectType']($scope.updatedata).then(        		
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

$scope.updatereturnsearch =function()
{
	$scope.hidesearchdet = true;
	$scope.subjecttypedet = true;
	$scope.updatedata = false;
	$scope.errormsg = false;
}

$scope.backtosearch = function(){
	
	
	$scope.hidesearchdet = false;
	$scope.subjecttypedet = false;
	$scope.errormsg = false;
}
$scope.returnsearch = function(){
	
	$scope.hidesearchdet = true;
	$scope.subjecttypedet = true;
	$scope.errormsg = false;
}

$scope.clearcontent = function(){
	 
	 $scope.createForm.$setPristine();
	  $scope.SubjectTypeVO="";

}

$scope.go = function(){
	   $scope.errormsg = false;
		$scope.updatedata = false;	
		$scope.hidesearchdet = true;
		$scope.subjecttypedet = true;
		
	}

$scope.deletesubjecttype = function(deletesubjecttypeid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletesubjecttype.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletesubjecttypeok = function() {
				  deleletconformsubjecttype(deletesubjecttypeid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleletconformsubjecttype(deleteid){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"subjectType/deleteSubjectType?subjectTypeId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
		

}]);
angular.module(appCon.appName).directive('numericOnlySubjecttype', function(){
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



