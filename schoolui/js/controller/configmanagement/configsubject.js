'use strict';

angular.module(appCon.appName).controller('subjectmgmtController',
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

$scope.getAllSubjectData = function(){
	
$http({
		url:appCon.globalCon.serviceBaseURL+"subject/getAllSubjectBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;		
	});
}

 $scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"subject/getAllSubjectBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.subjectVOs;
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

$scope.createsubject = function(){ 

	$scope.errormsg = false;
	var data = $scope.SubjectVO;
	data.schoolId = $cookieStore.get('schoolId');
	$scope.data = {"SMSRequest":{"SubjectVO":$scope.SubjectVO}};
		
	$injector.get('configmanagementservice')['saveSubject']($scope.data).then(        		
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
		url:appCon.globalCon.serviceBaseURL+"subject/getSubjectById?subjectId="+id,
		method:'GET'
	}).success(function(result){
		console.log(result.responseVO.SubjectVO);
		var getupdata = result.responseVO.SubjectVO;
		getupdata.createdDate = $filter('date')(getupdata.createdDate, "dd/MM/yyyy");
		getupdata.updatedDate = $filter('date')(getupdata.updatedDate, "dd/MM/yyyy");
		$scope.UpdateVO = getupdata;
	});
	
}
$scope.update = function(){
		$scope.errormsg = false;
	var updata = $scope.UpdateVO;
	$scope.updatedata = {"SMSRequest":{"SubjectVO":updata}};
	console.log($scope.updatedata);	
	$injector.get('configmanagementservice')['updateSubject']($scope.updatedata).then(        		
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
//list of student details

/*$http.get("mock/subject/subject.json").then(function(data){
	$scope.items = data.Class;
});


$http.get("mock/config/subject.json").success(function(data){
	console.log(data);
	if(data.Status == "Ok")
		$scope.items = data.subjectView;
	console.log(data.subjectView);
});*/


$scope.backtosearch = function(){
	$scope.SubjectVO="";
	$scope.hidesearchdet = false;
	$scope.hidetable = false;
	$scope.errormsg = false;
	
}

$scope.returnsearch = function(){
	$scope.createForm.$setPristine();
		  var defaultForm = {
              subjectName : ""  
          };
	$scope.SubjectVO = defaultForm;
	
	$scope.hidesearchdet = true;
	$scope.hidetable = true;
	$scope.errormsg = false;
}
$scope.goclear = function(){
	 $scope.errormsg = false;
	  $scope.createForm.$setPristine();
	   var defaultForm = {
              subjectName : ""  
          };
	$scope.SubjectVO = defaultForm;
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







$scope.deleteSubject = function(deletesubjectid){

	$scope.$modalInstance = $modal.open({
		 scope: $scope,
		 templateUrl: 'deleteSubject.html',
		 controller: function($scope, $modalInstance, $rootScope, $state) {
			  $scope.deletesubjectok = function() {
				  deleteSubject(deletesubjectid);
			  };
			  $scope.okcancel = function() {
				  $modalInstance.close();
			  };
		  }
	  });
}

function deleteSubject(deleteid){
	console.log(deleteid);
	$http({
		url:appCon.globalCon.serviceBaseURL+"subject/deleteSubject?subjectId="+deleteid,
		method:'DELETE'
	}).success(function(result){
		$state.go($state.current, {}, {reload: true});
		
	});
}
$scope.finalstep = function(){
	$state.go('configsubject');
}


}]);
angular.module(appCon.appName).directive('alphanumericOnlySubject', function(){
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