'use strict';

angular.module(appCon.appName).controller('configsubjectsetController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder, MethodService) {
var $http,$state,$modal,$cookieStore;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$scope.hidesearchdet = true;
$scope.subjectsetdet = true;	

 $scope.getallsubjectsetdata = function()
{
	$http ({
		url:appCon.globalCon.serviceBaseURL+"subjectSet/getAllSubjectSetBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
		}).success(function(result){
             $scope.item = result.responseVO.subjectSetVOs;
			 if($scope.item.length > 10)
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
								 
	$scope.createsubjectset = function(){ 
    $scope.errormsg = false;
	var data = $scope.SubjectSetVO,datasubject = $scope.SubjectVO;
	datasubject.schoolId = $cookieStore.get('schoolId');
	data.subjectList = {"SubjectVO":$scope.SubjectVO};
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"SubjectSetVO":$scope.SubjectSetVO}};
	$scope.errormsg = true;
		console.log($scope.data);
	$injector.get('configmanagementservice')['saveSubjectSet']($scope.data).then(        		
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

$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"SubjectSetVO":updata}};
	
	$injector.get('configmanagementservice')['updateSubjectSet']($scope.updatedata).then(        		
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
	
$scope.subjectset = setname;
$scope.subject= setlist;
$scope.updatedata = true;
$scope.hidesearchdet = false;
$scope.subjectsetdet = false;
$http({
		url:appCon.globalCon.serviceBaseURL+"subjectSet/getSubjectSetById?subjectSetId="+id,
		method:'GET'
	}).success(function(result){
		
		var getupdata = result.responseVO.SubjectSetVO;
		delete getupdata.createdDate;
		delete getupdata.updatedDate;
		$scope.UpdateVO = getupdata;
	});


}

$scope.updatereturnsearch =function()
{
	$scope.hidesearchdet = true;
	$scope.subjectsetdet = true;
	$scope.updatedata = false;
	$scope.errormsg = false;
}

$scope.backtosearch = function(){
	
	$scope.search="";
	$scope.hidesearchdet = false;
	$scope.subjectsetdet = false;
	$scope.errormsg = false;
}
$scope.returnsearch = function(){
	$scope.search="";
	$scope.hidesearchdet = true;
	$scope.subjectsetdet = true;
	$scope.errormsg = false;
}

$scope.clearcontent = function(){
	$scope.createForm.$setPristine();
	$scope.SubjectSetVO = "";
	$scope.errormsg = false;
}
$scope.go = function(){
	   $scope.errormsg = false;
		$scope.updatedata = false;	
		$scope.hidesearchdet = true;
		$scope.subjectsetdet = true;
		
	}

$scope.deletesubjctset = function(deletesubjectsetid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deletesubjectset.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletesubjectsetok = function() {
				  deleletconformsubjectset(deletemediumid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleletconformsubjectset(deleteid){
	
	$http({
		url:appCon.globalCon.serviceBaseURL+"subjectSet/deleteSubjectSet?subjectSetId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}

}]);
angular.module(appCon.appName).directive('numericOnlySubjectset', function(){
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
