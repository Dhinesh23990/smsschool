'use strict';

angular.module(appCon.appName).controller('teacherattendancemgmtController',
					['$injector',  '$scope', 'DTOptionsBuilder',
        function($injector, $scope, DTOptionsBuilder) {
var $http,$state,$modal,$cookieStore,$filter;
$http = $injector.get('$http');
$state = $injector.get('$state');
$modal = $injector.get('$modal');
$cookieStore = $injector.get('$cookieStore');
$filter = $injector.get('$filter');
$scope.hidesearchdet = true;
$scope.isOpen = false;
$scope.setlistandgridview = 'listview';
$scope.listandgridview ="List View";
$scope.showsearchbutton = true;
$scope.studentlistviewdetails = true;
$scope.studentgridviewdetails = false;
$scope.day_view = false;
$scope.consolidated_view = false;

$('input[name="birthdate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		maxDate:new Date()
});

$('input[name="todate"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
		maxDate:new Date()
});

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

$scope.dayoptions= [{value:"Morning",key:"Morning"},{value:"Afternoon",key:"Afternoon"}];
$scope.day = "Morning";

$scope.listviewdet = function(){
	if($scope.setlistandgridview != "listview"){
		$scope.studentlistviewdetails = true;
		$scope.studentgridviewdetails = false;
		$scope.day_view = false;
		$scope.consolidated_view = false;
		$scope.setlistandgridview = "listview";
		$scope.listandgridview ="List View";
		$scope.attenddancedate = "";
		$scope.day = "";
		$scope.staffId = "";
		$scope.fromdate = "";
		$scope.todate = "";
		$scope.constaffId = "";
		angular.element("#grid_view").removeClass("activeview");
		angular.element("#list_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#grid_view").addClass("hoverview").addClass("defaultcol");
		angular.element("#list_view").addClass("activeview");
	}
}

$scope.gridviewdet = function(){
	if($scope.setlistandgridview == "listview"){
		$scope.listandgridview ="Grid View";
		$scope.studentlistviewdetails = false;
		$scope.studentgridviewdetails = true;
		$scope.day_view = false;
		$scope.consolidated_view = false;
		$scope.setlistandgridview = "gridview";
		$scope.attenddancedate = "";
		$scope.day = "";
		$scope.staffId = "";
		$scope.fromdate = "";
		$scope.todate = "";
		$scope.constaffId = "";
		angular.element("#grid_view").removeClass("hoverview").removeClass("defaultcol");
		angular.element("#list_view").removeClass("activeview");
		angular.element("#grid_view").addClass("activeview");
		angular.element("#list_view").addClass("hoverview").addClass("defaultcol");
	}
}
	

$http({
	url:appCon.globalCon.serviceBaseURL+"teacher/getAllTeacher?schoolId="+$cookieStore.get('schoolId')+'&pageIndex=0&pageSize=100',
	method:'GET'
}).success(function(result){
	var allTeacherdata = result.responseVO.teacherList;
	$scope.teachernames = allTeacherdata;
});


$scope.showclsandsec = function(){

	$scope.showsearchbutton = false;
	//$scope.showhidebutton = true;
}
$scope.returnsearch = function(){

	//$scope.showhidebutton = false;
	$scope.showsearchbutton = true;
}


//show by search
$scope.dtOptions = DTOptionsBuilder.newOptions()
						.withOption('lengthChange',false)
						.withOption('searching',false)
						.withOption('sort',false)
						.withOption('filter',false)
						.withOption("paging",false);
						
						
						
$scope.showbysearch = function(){
	
	$scope.attendancetable = true;
	$scope.date = $filter('date')($scope.attenddancedate, "yyyy/MM/dd");
	if(angular.isUndefined($scope.staffId) || $scope.staffId === null){
		$scope.staffId="ALL_TEACHERS";
	}
	$http({
		url:appCon.globalCon.serviceBaseURL+"staffAttendance/getAllTeachersByFromToDate?schoolId="+$cookieStore.get('schoolId')+"&startdate="+$scope.date+"&staffId="+$scope.staffId+"&dayAfter="+$scope.day,
		method:'GET'
	}).success(function(result){
		var allstaffdata = result.responseVO.staffAttendanceList;
		angular.forEach(allstaffdata,function(value,key){
			if(value.selectLeaveMasterId)
				value.morning = true;
			else 
				value.morning = false;
			
		});
		
		$scope.items = allstaffdata;
		$scope.day_view = true;
		$scope.consolidated_view = false;
	});
}


$scope.conshowbysearch = function(){
	
	$scope.Fromdate = $filter('date')($scope.fromdate, "yyyy/MM/dd");
	$scope.Todate = $filter('date')($scope.todate, "yyyy/MM/dd");
	if(angular.isUndefined($scope.constaffId) || $scope.constaffId === null){
		$scope.constaffId="ALL_TEACHERS";
	}
	$http({
		url:appCon.globalCon.serviceBaseURL+"staffAttendance/getAllTeachersByFromToDate?schoolId="+$cookieStore.get('schoolId')+"&startdate="+$scope.Fromdate+"&staffId="+$scope.constaffId+"&endDate="+$scope.Todate,
		method:'GET'
	}).success(function(result){
		var consstaffdata = result.responseVO.staffAttendanceList;
		$scope.conitems = consstaffdata;
		$scope.day_view = false;
		$scope.consolidated_view = true;
	});
}


}]);


