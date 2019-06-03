'use strict';

angular.module(appCon.appName).controller('configexamsessioncontroler',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
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
$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;
  
$('#timepicker1').timepicki();
$('#timepicker2').timepicki();
  
$scope.getAllExamSessionBySchoolId = function(){
	$http({
		url:appCon.globalCon.serviceBaseURL+"examSession/getAllExamSessionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		$scope.totalRecords = result.responseVO.totalRecords;
	});

}

$scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"examSession/getAllExamSessionBySchoolId?shoolId="+$cookieStore.get('schoolId')+"&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.examSessionVOs;
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

$scope.createexamsession = function(){ 
	$scope.errormsg = false;
	var startTime  = angular.element("#timepicker1").val();
	var endTime  = angular.element("#timepicker2").val();
	var data = $scope.ExamSessionVO;
	data.schoolId = $cookieStore.get('schoolId');
	data.startTime = startTime;
	data.endTime = endTime;
	console.log(data);
	$scope.data = {"SMSRequest":{"ExamSessionVO":$scope.ExamSessionVO}};	
	$injector.get('configmanagementservice')['saveExamSession']($scope.data).then(     
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

$scope.examsessionstarttime = function(){
	$scope.startTime ="startTime";
}

$scope.examsessionendtime = function(){
	$scope.endTime ="endTime";
}

$scope.backtosearch = function(){ 
	$scope.hidesearchdet = false;
	$scope.tablehidesearchdet = false;
}
$scope.returnsearch = function(){
	$scope.examsessionform.$setPristine();
	var defaultForm = {
		examSessionName : ""		  
	};
	$scope.ExamSessionVO = defaultForm;
	$scope.ExamSessionVO=""; 
	angular.element("#timepicker1").val("");
	angular.element("#timepicker2").val("");	
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
}

$scope.edit = function(exam_id){
	
    $scope.tablehidesearchdet= false;
	$scope.updatehidesearchdet = true;
	$scope.hidesearchdet = false; 
	$http({
		url:appCon.globalCon.serviceBaseURL+"examSession/getExamSessionById?examSessionId="+exam_id,
		method:'GET'
	}).success(function(result){		
		var getupdata = result.responseVO.ExamSessionVO;
		console.log(getupdata);
		var start_time = getupdata.startTime.Substring(0,2);
		var end_time = getupdata.startTime.Substring(2,4);
		var am = getupdata.startTime.Substring(4,6);
		var start_time1 = getupdata.endTime.Substring(0,2);
		var end_time1= getupdata.endTime.Substring(2,4);
		var am1 = getupdata.endTime.Substring(4,6);
		$('#timepicker1').timepicki({start_time: [start_time, end_time, am]});
		$('#timepicker2').timepicki({start_time: [start_time1, end_time1, am1]});
		$scope.startTime = "startTime";
		$scope.endTime = "endTime";
		$scope.UpdateVO = getupdata;
	});
	
}


$scope.update = function(){
	var updata = $scope.UpdateVO;
	var startTime  = angular.element("#timepicker1").val();
	var endTime  = angular.element("#timepicker2").val();
	updata.startTime = startTime;
	updata.endTime = endTime;
	$scope.updatedata = {"SMSRequest":{"ExamSessionVO":updata}};
	$injector.get('configmanagementservice')['updateExamSession']($scope.updatedata).then(      
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
}
$scope.go = function(){
	$scope.updatehidesearchdet = false;	
	$scope.hidesearchdet = true;
	$scope.tablehidesearchdet = true;
	
}

$scope.deletereligion = function(){

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
}

$scope.goclear = function(){
	$scope.examsessionform.$setPristine();
	$scope.ExamSessionVO.examSessionName="";
	angular.element("#timepicker1").val("");
	angular.element("#timepicker2").val("");
}



}]);

