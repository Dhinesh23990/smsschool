'use strict';

angular.module(appCon.appName).controller('smslogcontroller',
					['$injector',  '$scope', 'DTOptionsBuilder','$stateParams',
        function($injector, $scope, DTOptionsBuilder,$stateParams) {
var $http,$state,$modal,$filter;
$filter = $injector.get('$filter');
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$scope.hidesearchdet = true;
$scope.updatehidesearchdet = false;
$scope.tablehidesearchdet = true;
$scope.undeliveredtablehdet = false;
$scope.search = true;
$scope.setlistandgridview = 'listview';
$scope.listandgridview ="List View";
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
$scope.traspage = function(){
	
	$state.go('configsms');
	if($scope.setlistandgridview == "listview")
	{
		$scope.listandgridview ="Grid View";		
		$scope.setlistandgridview = "gridview";
		$scope.tablehidesearchdet = false;
		$scope.undeliveredtablehdet = true;
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").removeClass("activeview");
		angular.element("#grid_view").addClass("activeview");
		angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
	} 
}


$scope.deliverviewdet = function(){
	if($scope.setlistandgridview != "listview"){
		$scope.setlistandgridview = "listview";
		$scope.tablehidesearchdet = true;
		$scope.undeliveredtablehdet = false;
		$scope.listandgridview ="List View";
		angular.element("#grid_view").removeClass("activeview");
		angular.element("#list_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#grid_view").addClass("hoverview").addClass("defaultcol");
		angular.element("#list_view").addClass("activeview");
	}
}


$scope.undeliverviewdet = function(){
	
 	if($scope.setlistandgridview == "listview"){
		$scope.listandgridview = "Grid View";		
		$scope.setlistandgridview = "gridview";
		$scope.tablehidesearchdet = false;
		$scope.undeliveredtablehdet = true;
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").removeClass("activeview");
		angular.element("#grid_view").addClass("activeview");
		angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
	} 
}


$scope.clearallsearchvalue = function(){
	$scope.searchgrid = "";
}

  $scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;
  
  
$scope.viewsmsdata = function(){  

    $http({
				url:appCon.globalCon.serviceBaseURL+"sms/getAllSmsHistoryBySmsLogId?smsLogId="+$stateParams.Allsmsid+"&status=DELIVERED&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
				method:'GET'
			}).success(function(result){		
   				$scope.totalRecords = result.responseVO.totalRecords;
		
		});
			
		$http({
			url:appCon.globalCon.serviceBaseURL+"sms/getAllSmsHistoryBySmsLogId?smsLogId="+$stateParams.Allsmsid+"&status=UNDELIVERED&pageIndex=0&pageSize="+appCon.globalCon.paginationSize,
			method:'GET'
			}).success(function(result){		
				$scope.untotalRecords = result.responseVO.totalRecords;

		});
  }
  
  
  $scope.$watch('currentPage + numPerPage', function() {
	   
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"sms/getAllSmsHistoryBySmsLogId?smsLogId="+$stateParams.Allsmsid+"&status=DELIVERED&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.smsLogList;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
			if(value.admissionNumber)
				value.admissionNumber = value.admissionNumber;
			else
				value.admissionNumber = '---';
			
			if(value.standard)
				value.standard = value.standard;
			else
				value.standard = '---';
		})
		$scope.items = itemsdata;
		if($scope.items.length > 0)
			$scope.show_datatables = true;
		
		$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('info',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
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
  
  $scope.uncurrentPage = 1
  ,$scope.unmaxSize = 5;

 $scope.$watch('uncurrentPage + numPerPage', function() {
	   
    var begin = (($scope.uncurrentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
	
     $http({
		url:appCon.globalCon.serviceBaseURL+"sms/getAllSmsHistoryBySmsLogId?smsLogId="+$stateParams.Allsmsid+"&status=UNDELIVERED&pageIndex="+begin+"&pageSize="+appCon.globalCon.paginationSize,
		method:'GET'
	}).success(function(result){
		var itemsdata = result.responseVO.smsLogList;
		var index_id = begin+1;
		angular.forEach(itemsdata,function(value,key){
			value.indexId = index_id;
			index_id++;
			if(value.admissionNumber)
				value.admissionNumber = value.admissionNumber;
			else
				value.admissionNumber = '---';
			
			if(value.standard)
				value.standard = value.standard;
			else
				value.standard = '---';
		})
		$scope.undelivered = itemsdata;
		if($scope.undelivered.length > 0)
			$scope.unshow_datatables = true;
		
		$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('info',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
		$scope.checktotalRecords = result.responseVO.totalRecords;
		$scope.show_begin = begin+1;
		if($scope.checktotalRecords > end)
			$scope.show_end = end;
		else
			$scope.show_end = $scope.checktotalRecords;
		
		if($scope.checktotalRecords > 10)
			$scope.unabovedata = true;
		else
			$scope.unabovedata = false;
	});
    
  });



}]);



